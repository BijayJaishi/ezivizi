package com.kandktech.ezivizi.fragments;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kandktech.ezivizi.R;
import com.kandktech.ezivizi.welcome_screen.WelcomeScreenActivity;

import static android.content.Context.MODE_PRIVATE;

public class QRCodeFragment extends Fragment {

    View view;
    ImageView qrImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_qrcode, container, false);
        qrImg = view.findViewById(R.id.qrImg);

        try {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("qrImage",MODE_PRIVATE);
            Glide.with(getActivity()).load("/storage/emulated/0/Pictures/.ezvz/"+"_image_"+sharedPreferences.getLong("imgName",0)+".jpg").into(qrImg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

}
