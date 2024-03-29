package com.kandktech.ezivizi.welcome_screen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.kandktech.ezivizi.AnimationClass;
import com.kandktech.ezivizi.FirstPageActivity;
import com.kandktech.ezivizi.R;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

public class WelcomeScreenActivity extends AppCompatActivity {

    public static String deviceId = null;
    ImageView imageView;
    public static long imgName = System.currentTimeMillis();


    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);



        deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

//        imgName = System.currentTimeMillis();


        AnimationClass.Startanimation(4000);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setAnimation(AnimationClass.animation);
        AnimationClass.animation.setAnimationListener(new Animation.AnimationListener() {
                                                          @Override
                                                          public void onAnimationStart(Animation animation) {

                                                          }

                                                          @Override
                                                          public void onAnimationEnd(Animation animation) {

                                                              String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                                                              String rationale = "Please provide Read and Write Storage permission to save QRCode.";
                                                              Permissions.Options options = new Permissions.Options()
                                                                      .setRationaleDialogTitle("Info")
                                                                      .setSettingsDialogTitle("Warning");

                                                              Permissions.check(WelcomeScreenActivity.this, permissions, rationale, options, new PermissionHandler() {
                                                                  @Override
                                                                  public void onGranted() {
                                                                      startActivity(new Intent(getApplicationContext(), FirstPageActivity.class));
                                                                  }

                                                                  @Override
                                                                  public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                                                                      // permission denied, block the feature.
                                                                  }
                                                              });

                                                          }

                                                          @Override
                                                          public void onAnimationRepeat(Animation animation) {

                                                          }
                                                      }
        );


    }


}
