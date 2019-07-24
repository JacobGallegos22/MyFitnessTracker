package com.example.myfitnesstracker;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPost extends AsyncTask<String, Void, String> {

  private Context mContext;
  private HttpListener httpListener;
  private String urlStr = "";
  private String params;
  private String basePath = "http://10.0.2.2:8081";

  public HttpPost(Context context, String url, HttpListener httpListener, String params) {
    this.mContext = context;
    this.urlStr = url;
    this.httpListener = httpListener;
    this.params = params;

  }

  @Override
  protected String doInBackground(String... params) {
    try {

      URL obj = new URL(basePath + urlStr);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("Content-Type", "application/json");
      con.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(con.getOutputStream());
      wr.writeBytes(this.params);
      wr.flush();
      wr.close();

      //add request header
      int responseCode = con.getResponseCode();
      //get the input string
      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

    } catch (Exception e){
      //Handle this request
      e.printStackTrace();

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

