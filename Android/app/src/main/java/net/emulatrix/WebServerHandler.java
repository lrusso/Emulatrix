package net.emulatrix;

import android.os.Environment;

import java.io.*;
import java.net.*;

class WebServerHandler extends Thread
	{
	private BufferedReader in;
	private PrintWriter out;
	private Socket toClient;
  
	WebServerHandler(Socket s)
		{
		toClient = s;
		}

	public void run()
		{
		String headerBase = "HTTP/1.1 %code%\n" + "Server: Emulatrix/1.5.5\n" + "Content-Length: %length%\n" + "Connection: close\n" + "Content-Type: %fileType%; charset=iso-8859-1\n\n";
		
		String header = headerBase;
		header = header.replace("%code%", "403 Forbidden");
		header = headerBase.replace("%code%", "200 OK");

		String documentName = "";
		String filePath = "";
		String fileName = "";

		try
			{
			in = new BufferedReader(new InputStreamReader(toClient.getInputStream()));

			// Receive data
			while (true)
				{
				String s = in.readLine().trim();
				if (s.equals(""))
					{
					break;
					}
				if (s.substring(0, 3).equals("GET"))
					{
					int location = s.indexOf(" HTTP/");
					documentName = s.substring(5,location);
					documentName = documentName.replaceAll("[/]+","/");
					}
				}
			}
			catch (Exception e)
			{
			WebServer.remove(toClient);
			try
				{
				toClient.close();
				}
				catch (Exception ex){}
			}

		fileName = documentName;
		filePath = getEmulatrixFolder() + "/" + documentName;
		
		fileName = replacer(fileName);
		filePath = replacer(filePath);
		
		try
			{
			byte[] iso88591Data = filePath.getBytes("ISO-8859-1");
			filePath = new String(iso88591Data);
			}
			catch (UnsupportedEncodingException e)
			{
			}
		try
			{
			byte[] iso88591Data = fileName.getBytes("ISO-8859-1");
			fileName = new String(iso88591Data);
			}
			catch (UnsupportedEncodingException e)
			{
			}

		File f = new File(filePath);
		
		if (f.exists() & !f.isDirectory())
			{
			try
				{
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
				BufferedOutputStream out = new BufferedOutputStream(toClient.getOutputStream());
				ByteArrayOutputStream tempOut = new ByteArrayOutputStream();
		  
				byte[] buf = new byte[4096];
				int count = 0;
				while ((count = in.read(buf)) != -1)
					{
					tempOut.write(buf, 0, count);
					}

				tempOut.flush();
				header = header.replace("%length%", ""+tempOut.size());
				
				if (filePath.toUpperCase().endsWith(".HTM"))
					{
					header = header.replace("%fileType%", "text/html");
					}
				else if (filePath.toUpperCase().endsWith(".HTML"))
					{
					header = header.replace("%fileType%", "text/html");
					}
				else if (filePath.toUpperCase().endsWith(".PNG"))
					{
					header = header.replace("%fileType%", "image/png");
					}
				else if (filePath.toUpperCase().endsWith(".CSS"))
					{
					header = header.replace("%fileType%", "text/css");
					}
				else if (filePath.toUpperCase().endsWith(".JS"))
					{
					header = header.replace("%fileType%", "application/javascript");
					}
				else if (filePath.toUpperCase().endsWith(".WASM"))
					{
					header = header.replace("%fileType%", "application/wasm");
					}
				else
					{
					header = header.replace("%fileType%", "application/octet-stream");
					}
				
				out.write(header.getBytes());
				out.write(tempOut.toByteArray());
				out.flush();
				}
				catch(Exception e)
				{
				}
				catch(OutOfMemoryError e)
				{
				}
			}

		WebServer.remove(toClient);
		try	{toClient.close();} catch (IOException e1) {}
		}
	
	private String replacer(String data)
		{
		try
			{
			data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
			data = data.replaceAll("\\+", "%2B");
			data = URLDecoder.decode(data, "utf-8");
			}
			catch (Exception e)
			{
			}
		return data;
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
	}