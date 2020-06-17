package net.emulatrix;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class WebServerService extends Service
	{
    private WebServer serverLocalhost;

	@Override public IBinder onBind(Intent intent)
		{
		return null;
		}
	
	@Override public void onCreate()
		{
		}

	@Override public int onStartCommand(Intent intent, int flags, int startId)
		{
		super.onStartCommand(intent, flags, startId);
		startLocalhostServer();
		return START_STICKY;
		}

	@Override public void onDestroy()
		{
		stopLocalhostServer();
		}

    final Handler mHandlerLocalhostServer = new Handler()
    	{
    	@Override public void handleMessage(Message msg)
			{
    		Bundle b = msg.getData();
			}
    	};

    private void stopLocalhostServer()
    	{
    	if(serverLocalhost != null)
			{
    		serverLocalhost.stopServer();
    		serverLocalhost.interrupt();
			}
    	}
    private void startLocalhostServer()
		{
    	try
			{
    		serverLocalhost = new WebServer("127.0.0.1",4569, mHandlerLocalhostServer);
    		serverLocalhost.start();
			}
			catch (Exception e)
			{
			}
		}
	}