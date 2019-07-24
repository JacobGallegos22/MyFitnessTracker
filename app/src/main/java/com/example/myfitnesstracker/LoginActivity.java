package com.example.myfitnesstracker;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.navigation.Navigation;

public class LoginActivity extends AppCompatActivity implements RegisterFragment.OnFragmentInteractionListener, LoginFragment.OnFragmentInteractionListener {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

  }

  @Override
  public void onFragmentInteraction(Uri uri) {

  }

  @Override
  public void onPointerCaptureChanged(boolean hasCapture) {

  }
}
