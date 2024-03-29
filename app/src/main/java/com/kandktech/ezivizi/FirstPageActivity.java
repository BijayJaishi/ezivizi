package com.kandktech.ezivizi;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kandktech.ezivizi.adapter.TabAdapter;

public class FirstPageActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    TabAdapter tabAdapter;
    public static boolean qrScan = false;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("eraseData",MODE_PRIVATE);




        setContentView(R.layout.activity_first_page);

        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tab);

         /*
        setting toolbar in actionbar
        */
        setSupportActionBar(toolbar);

        /*
        setting title for toolbar
         */
        getSupportActionBar().setTitle(R.string.app_name);

        /*
        calling tabAdapter class and setting tabAdapter on it
         */

        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        /*
        setting tablayout with viewpager
         */
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);


        /*
        change fab icon based on tab selected
         */
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    public void qrScan(View view) {
        startActivity(new Intent(getApplicationContext(),QR_Scan.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {

            if (!sharedPreferences.getString("dataErase","").equals("1")){
                AlertDialog alertDialog = new AlertDialog.Builder(FirstPageActivity.this,AlertDialog.THEME_HOLO_LIGHT).create();


                alertDialog.setTitle(R.string.app_name);
                alertDialog.setMessage("All previously stored data will be erased when you uninstall EzVz!!");


                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("dataErase","1");
                                editor.apply();

                                dialog.dismiss();
                            }
                        });

                alertDialog.show();
                final Button neutralButton = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                LinearLayout.LayoutParams neutralButtonLL = (LinearLayout.LayoutParams) neutralButton.getLayoutParams();
                neutralButtonLL.gravity = Gravity.CENTER;
                neutralButton.setTextColor(getResources().getColor(R.color.black));
                neutralButton.setLayoutParams(neutralButtonLL);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
