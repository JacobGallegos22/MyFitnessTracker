package com.example.myfitnesstracker;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class BackendVolley extends Application {
  private static BackendVolley instance;
  private RequestQueue requestQueue;
  private ImageLoader imageLoader;
  private static Context ctx;
  private static Context context;

  public void onCreate() {
    super.onCreate();
    BackendVolley.context = getApplicationContext();
    ctx = context;
    requestQueue = getRequestQueue();
  }

  public static Context getAppContext() {
    return BackendVolley.context;
  }

  private BackendVolley(Context context) {

  }

  public static synchronized BackendVolley getInstance(Context context) {
    if (instance == null) {
      instance = new BackendVolley(context);
    }
    return instance;
  }

  public RequestQueue getRequestQueue() {
    if (requestQueue == null) {
      // getApplicationContext() is key, it keeps you from leaking the
      // Activity or BroadcastReceiver if someone passes one in.
      requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
    }
    return requestQueue;
  }

  public <T> void addToRequestQueue(Request<T> req) {
    getRequestQueue().add(req);
  }

  public ImageLoader getImageLoader() {
    return imageLoader;
  }
}
