package com.example.myfitnesstracker;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGet extends AsyncTask<String, Void, String> {

  private Context mContext;
  private HttpListener httpListener;
  private String urlStr = "";
  private String basePath = "http://10.0.2.2:8081";

  public HttpGet(Context context, String url, HttpListener httpListener) {
    this.mContext = context;
    this.urlStr = url;
    this.httpListener = httpListener;
  }

  @Override
  protected String doInBackground(String... params) {
    try {

      URL mUrl = new URL(basePath + urlStr);
      HttpURLConnection httpConnection = (HttpURLConnection) mUrl.openConnection();
      httpConnection.setRequestMethod("GET");
      httpConnection.setRequestProperty("application/json", "0");
      httpConnection.setUseCaches(false);
      httpConnection.setAllowUserInteraction(false);
      httpConnection.setConnectTimeout(100000);
      httpConnection.setReadTimeout(100000);

      httpConnection.connect();

      int responseCode = httpConnection.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
          sb.append(line + "\n");
        }
        br.close();
        return sb.toString();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  @Override
  protected void onPostExecute(String s) {
    super.onPostExecute(s);

    if (httpListener != null && s != null) {
      httpListener.onTaskDone(s);
    } else
      httpListener.onError();
  }
}

