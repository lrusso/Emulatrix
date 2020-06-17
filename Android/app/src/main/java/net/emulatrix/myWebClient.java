package net.emulatrix;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.MailTo;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class myWebClient extends WebViewClient
	{
    public void onPageStarted(WebView view, String url, Bitmap favicon)
    	{
        super.onPageStarted(view, url, favicon);
    	}

    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
    	{
		try
			{
			if(request.getUrl().toString().startsWith("mailto:"))
				{
				MailTo mt = MailTo.parse(request.getUrl().toString());
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("text/plain");
				i.putExtra(Intent.EXTRA_EMAIL, new String[]{mt.getTo()});
				i.putExtra(Intent.EXTRA_SUBJECT, mt.getSubject());
				i.putExtra(Intent.EXTRA_CC, mt.getCc());
				i.putExtra(Intent.EXTRA_TEXT, mt.getBody());
				view.getContext().startActivity(i);
				view.reload();
				return true;
				}
			}
			catch(Exception e)
			{
			return true;
			}

		view.loadUrl(request.getUrl().toString());
        return true;
    	}

    public void onPageFinished(WebView view, String url)
    	{
        super.onPageFinished(view, url);
    	}
	}