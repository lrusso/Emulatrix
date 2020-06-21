package net.emulatrix;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.OpenableColumns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main extends Activity
	{
	private WebView webView;
	private TextView textView;
	private Context myContext;
	private boolean playingGame = false;
	private static ValueCallback<Uri> mUploadMessage;  
	private static ValueCallback<Uri[]> mUploadMessage5;
	private final static int FILECHOOSER_RESULTCODE = 1;

	@Override protected void onCreate(Bundle savedInstanceState)
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		myContext = this;

		textView = (TextView) findViewById(R.id.privacyPolicy);
		textView.setOnClickListener(new View.OnClickListener()
			{
			@Override public void onClick(View v)
				{
				clickInPrivacy();
				}
			});

		webView = (WebView) findViewById(R.id.webView1);

		webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setTextZoom(100);

		webView.setBackgroundColor(Color.BLACK);

		webView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
		webView.setDownloadListener(new DownloadListener(){@Override public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {webView.loadUrl(JavaScriptInterface.getBase64StringFromBlobUrl(url));}});

		webView.setWebViewClient(new myWebClient());
        webView.setWebChromeClient(new WebChromeClient()
        	{
        	// For Android 3.0+
        	public void openFileChooser(ValueCallback<Uri> uploadMsg)
        		{
                mUploadMessage = uploadMsg;  
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);  
                i.addCategory(Intent.CATEGORY_OPENABLE);  
                i.setType("*/*");  
                startActivityForResult(Intent.createChooser(i,"File Chooser"), FILECHOOSER_RESULTCODE);  
        		}

            // For Android 3.0+
        	public void openFileChooser(ValueCallback uploadMsg, String acceptType)
        		{
        		mUploadMessage = uploadMsg;
        		Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        		i.addCategory(Intent.CATEGORY_OPENABLE);
        		i.setType("*/*");
        		startActivityForResult(Intent.createChooser(i,"File Browser"),FILECHOOSER_RESULTCODE);
        		}

            //For Android 4.1
        	public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
        		{
        		mUploadMessage = uploadMsg;  
        		Intent i = new Intent(Intent.ACTION_GET_CONTENT);  
        		i.addCategory(Intent.CATEGORY_OPENABLE);  
        		i.setType("*/*");  
        		startActivityForResult(Intent.createChooser(i,"File Chooser"),FILECHOOSER_RESULTCODE);
        		}

            // For Android 5.0
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg, FileChooserParams fileChooserParams)
            	{
        		mUploadMessage5 = uploadMsg;
        		Intent i = new Intent(Intent.ACTION_GET_CONTENT);  
        		i.addCategory(Intent.CATEGORY_OPENABLE);  
        		i.setType("*/*");  
        		startActivityForResult(Intent.createChooser(i,"File Chooser"),FILECHOOSER_RESULTCODE);
                return true;
            	}

			@Override public boolean onConsoleMessage(ConsoleMessage consoleMessage)
				{
				String stringMessage = consoleMessage.message();

				if (stringMessage.startsWith("SHOW_NAVIGATION_BAR"))
					{
					showNavigationBarAndPrivacyPolicy();
					playingGame = false;
					}
				else if (stringMessage.startsWith("FILENAME="))
					{
					GlobalVars.filename = stringMessage.substring(9,stringMessage.length());
					}

				return true;
				};
        	});  

        checkAppPermissions();
		}

	@Override public void onResume()
		{
		super.onResume();
		if (playingGame==true)
			{
			hideNavigationBarAndPrivacyPolicy();
			}
		}
	 @Override protected void onActivityResult(int requestCode, int resultCode, Intent intent)
	 	{
	 	try
			{
			if (playingGame==true)
				{
				hideNavigationBarAndPrivacyPolicy();
				}
			if (resultCode == RESULT_OK)
				{
				if(requestCode==FILECHOOSER_RESULTCODE)
					{
					Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
					if (playingGame==true)
						{
						String filenameToCheck = getFileName(result).toLowerCase();
						if (filenameToCheck.indexOf(".state")>-1)
							{
							mUploadMessage5.onReceiveValue(new Uri[]{result});
							mUploadMessage5 = null;
							}
						}
						else
						{
						String filenameToCheck = getFileName(result).toLowerCase();
						if (filenameToCheck.indexOf(".nes")>-1 |
							filenameToCheck.indexOf(".smc")>-1 |
							filenameToCheck.indexOf(".sfc")>-1 |
							filenameToCheck.indexOf(".srm")>-1 |
							filenameToCheck.indexOf(".gb")>-1 |
							filenameToCheck.indexOf(".gbc")>-1 |
							filenameToCheck.indexOf(".gba")>-1 |
							filenameToCheck.indexOf(".bin")>-1 |
							filenameToCheck.indexOf(".smd")>-1 |
							filenameToCheck.indexOf(".md")>-1 |
							filenameToCheck.indexOf(".zip")>-1)
							{
							hideNavigationBarAndPrivacyPolicy();
							playingGame = true;
							}
							else
							{
							showNavigationBarAndPrivacyPolicy();
							playingGame = false;
							}
						mUploadMessage5.onReceiveValue(new Uri[]{result});
						mUploadMessage5 = null;
						}
					}
					else
					{
					if (null == mUploadMessage) return;
					Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
					mUploadMessage.onReceiveValue(result);
					mUploadMessage = null;
					}
				}
				else
				{
				if (mUploadMessage5 != null)
					{
					mUploadMessage5.onReceiveValue(null);
					mUploadMessage5 = null;
					}

				if (mUploadMessage != null)
					{
					mUploadMessage.onReceiveValue(null);
					mUploadMessage = null;
					}
				}
			}
	 		catch(Exception e)
			{
			}
	 	}

	@Override public boolean onKeyUp(int keyCode, KeyEvent event)
		{
		try
			{
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
				{
				stopWebService();
				System.exit(0);
				}
			}
			catch(Exception e)
			{
			}
		return super.onKeyUp(keyCode, event);
		}

	private void hideNavigationBarAndPrivacyPolicy()
		{
		try
			{
			textView.setVisibility(View.GONE);
			View decorView = getWindow().getDecorView();
			int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
			decorView.setSystemUiVisibility(uiOptions);
			}
			catch(Exception e)
			{
			}
		}

	private void showNavigationBarAndPrivacyPolicy()
		{
		try
			{
			textView.setVisibility(View.VISIBLE);
			View decorView = getWindow().getDecorView();
			int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
			decorView.setSystemUiVisibility(uiOptions);
			}
			catch(Exception e)
			{
			}
		}

	private void checkAppPermissions()
		{
		try
			{
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
				{
				String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
												Manifest.permission.WRITE_EXTERNAL_STORAGE};
				requestPermissions(PERMISSIONS_STORAGE, 123);
				}
				else
				{
				startEmulatrix();
				}
			}
			catch(Exception e)
			{
			}
		}

	private void clickInPrivacy()
		{
		LayoutInflater inflater = LayoutInflater.from(this);
		View view=inflater.inflate(R.layout.privacy, null);

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle(getResources().getString(R.string.textPrivacy));
		alertDialog.setView(view);
		alertDialog.setPositiveButton(getResources().getString(R.string.textOK), new DialogInterface.OnClickListener()
			{
			public void onClick(DialogInterface dialog, int whichButton)
				{
				}
			});
		alertDialog.show();
		}

	public String getFileName(Uri uri)
		{
		String result = null;
		if (uri.getScheme().equals("content"))
			{
			Cursor cursor = getContentResolver().query(uri, null, null, null, null);
			try
				{
				if (cursor != null && cursor.moveToFirst())
					{
					result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
					}
				}
			finally
				{
				cursor.close();
				}
			}
		if (result == null)
			{
			result = uri.getPath();
			int cut = result.lastIndexOf('/');
			if (cut != -1)
				{
				result = result.substring(cut + 1);
				}
			}
		return result;
		}

	@Override public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
		{
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try
			{
			if (requestCode==123)
				{
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
					{
					startEmulatrix();
					}
					else
					{
					System.exit(0);
					}
				}
			}
    	    catch(Exception e)
			{
			}
		}

	private void startWebServer()
		{
		if (checkFolderExists("Android/data/net.emulatrix")==true)
			{
			startWebService();
			}
			else
			{
			createFolder("Android");
			createFolder("Android/data");
			createFolder("Android/data/net.emulatrix");
			if (checkFolderExists("Android/data/net.emulatrix")==true)
				{
				startWebService();
				}
			}
		}

	private void startWebService()
		{
		try
			{
			Intent serviceIntent = new Intent(this, WebServerService.class);
			startService(serviceIntent);
			}
			catch(Exception e)
			{
			}
		}

	private void stopWebService()
		{
		try
			{
			Intent serviceIntent = new Intent(this, WebServerService.class);
			stopService(serviceIntent);
			}
			catch(Exception e)
			{
			}
		}

	private boolean checkFolderExists(String path)
		{
		if (Environment.getExternalStorageDirectory().toString().endsWith("/"))
			{
			File f = new File(Environment.getExternalStorageDirectory() + path);
			if(f.isDirectory())
				{
				return true;
				}
			}
			else
			{
			File f = new File(Environment.getExternalStorageDirectory() + "/" + path);
			if(f.isDirectory())
				{
				return true;
				}
			}
		return false;
		}

	private void createFolder(String path)
		{
		try
			{
			if (Environment.getExternalStorageDirectory().toString().endsWith("/"))
				{
				File folder = new File(Environment.getExternalStorageDirectory() + path);
				folder.mkdirs();
				}
				else
				{
				File folder = new File(Environment.getExternalStorageDirectory() + "/" + path);
				folder.mkdirs();
				}
			}
			catch(Exception e)
			{
			}
		}

	private String getEmulatrixFolder()
		{
		String path = "Android/data/net.emulatrix";
		if (Environment.getExternalStorageDirectory().toString().endsWith("/"))
			{
			File f = new File(Environment.getExternalStorageDirectory() + path);
			if(f.isDirectory())
				{
				return Environment.getExternalStorageDirectory() + path;
				}
			}
			else
			{
			File f = new File(Environment.getExternalStorageDirectory() + "/" + path);
			if(f.isDirectory())
				{
				return Environment.getExternalStorageDirectory() + "/" + path;
				}
			}
		return "";
		}

	private void copyAssets()
		{
		if (getLastUpdateVersionCode()!=getCurrentVersionCode())
			{
			try
				{
				AssetManager assetManager = getAssets();
				String[] files = null;

				try
					{
					files = assetManager.list("");
					}
					catch (IOException e)
					{
					}

				if (files != null) for (String filename : files)
					{
					if (filename.startsWith("Emulatrix") || filename.startsWith("index.html"))
						{
						InputStream in = null;
						OutputStream out = null;
						try
							{
							in = assetManager.open(filename);
							File outFile = new File(getEmulatrixFolder(), filename);
							out = new FileOutputStream(outFile);
							copyFile(in, out);
							}
							catch(IOException e)
							{
							}
							finally
							{
							if (in != null)
								{
								try
									{
									in.close();
									}
									catch (IOException e)
									{
									}
								}
							if (out != null)
								{
								try
									{
									out.close();
									}
									catch (IOException e)
									{
									}
								}
							}
						}
					}
				setLastUpdateVersionCode();
				}
				catch(Exception e)
				{
				}
			}
		}

	private void copyFile(InputStream in, OutputStream out) throws IOException
		{
		byte[] buffer = new byte[1024];
		int read;
		while((read = in.read(buffer)) != -1)
			{
			out.write(buffer, 0, read);
			}
		}

	private int getCurrentVersionCode()
		{
		try
			{
			PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			int versionCode = pInfo.versionCode;
			return versionCode;
			}
		catch (Exception e)
			{
			}
		return 0;
		}

	private int getLastUpdateVersionCode()
		{
		try
			{
			SharedPreferences sharedPref =getPreferences(Context.MODE_PRIVATE);
			int versionCode = sharedPref.getInt("LAST_UPDATE_USING_VERSIONCODE", -1);
			return versionCode;
			}
			catch(Exception e)
			{
			}
		return -1;
		}

	private void setLastUpdateVersionCode()
		{
		try
			{
			SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPref.edit();
			editor.putInt("LAST_UPDATE_USING_VERSIONCODE", getCurrentVersionCode());
			editor.commit();
			}
			catch(Exception e)
			{
			}
		}

	private void startEmulatrix()
		{
		startWebServer();
		copyAssets();
		new Handler().postDelayed(new Runnable()
			{
			@Override public void run()
				{
				try
					{
					textView.setVisibility(View.VISIBLE);
					webView.loadUrl("http://127.0.0.1:4569/index.html");
					}
					catch(Exception e)
					{
					}
				}
			}, 700);
		}
	}