package com.example.myfitnesstracker;

public interface HttpListener {
  void onTaskDone(String responseData);

  void onError();
}
