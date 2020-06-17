package net.emulatrix;

import android.content.Context;
import android.os.Environment;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JavaScriptInterface {

    private Context context;

    public JavaScriptInterface(Context context)
        {
        this.context = context;
        }

    public static String getBase64StringFromBlobUrl(String blobUrl)
        {
        if(blobUrl.startsWith("blob"))
            {
            return "javascript: var xhr = new XMLHttpRequest();" +
                    "xhr.open('GET', '"+ blobUrl +"', true);" +
                    "xhr.setRequestHeader('Content-type','application/octet-stream');" +
                    "xhr.responseType = 'blob';" +
                    "xhr.onload = function(e) {" +
                    "    if (this.status == 200) {" +
                    "        var blobStateFile = this.response;" +
                    "        var reader = new FileReader();" +
                    "        reader.readAsDataURL(blobStateFile);" +
                    "        reader.onloadend = function() {" +
                    "            base64data = reader.result;" +
                    "            Android.getBase64FromBlobData(base64data);" +
                    "        }" +
                    "    }" +
                    "};" +
                    "xhr.send();";
            }
        return "javascript: console.log('It is not a Blob URL');";
        }

    @JavascriptInterface
    public void getBase64FromBlobData(String base64Data) throws IOException
        {
        try
            {
            String path = Environment.getExternalStorageDirectory() + File.separator  + "Download";

            // Creates the folder
            File folder = new File(path);
            folder.mkdirs();

            // Getting the format and content that the file will have
            String fileNameRAW = GlobalVars.filename;

            String fileName = "";
            if (fileNameRAW.indexOf(".")>0)
                {
                fileName = fileNameRAW.substring(0,fileNameRAW.lastIndexOf("."));
                }
                else
                {
                fileName = fileNameRAW;
                }

            String fileFormat = "";
            if (fileNameRAW.indexOf(".")>0)
                {
                fileFormat = fileNameRAW.substring(fileNameRAW.lastIndexOf("."),fileNameRAW.length());
                }

            boolean fileCanBeCreated = false;
            int counter = 0;
            while(fileCanBeCreated==false)
                {
                if (counter==0)
                    {
                    File fileChecker = new File(folder, fileName + fileFormat);
                    if (fileChecker.exists()==false)
                        {
                        fileName = fileName + fileFormat;
                        fileCanBeCreated = true;
                        }
                        else
                        {
                        counter = counter + 1;
                        }
                    }
                    else
                    {
                    File fileChecker = new File(folder, fileName + "(" + counter + ")" + fileFormat);
                    if (fileChecker.exists()==false)
                        {
                        fileName = fileName + "(" + counter + ")" + fileFormat;
                        fileCanBeCreated = true;
                        }
                        else
                        {
                        counter = counter + 1;
                        }
                    }
                }

            // Patch for Android 8.0 and above
            String header = "data:application/octet-stream;base64,";
            int located = base64Data.indexOf(header);
            base64Data = base64Data.substring(located + header.length(),base64Data.length());

            // Writing the file
            byte[] pdfAsBytes = Base64.decode(base64Data, 0);
            FileOutputStream fOut;
            fOut = new FileOutputStream(path + "/" + fileName, false);
            fOut.write(pdfAsBytes);
            fOut.flush();
            fOut.close();
            }
            catch(Exception e)
            {
            }
        }
    }