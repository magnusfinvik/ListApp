package com.example.magnusfinvik.listapp;

import android.app.Fragment;
import android.content.res.Configuration;
import android.graphics.*;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;

import java.io.IOException;
import java.net.*;


public class MyDetailFragment extends Fragment{
    private Equipment equip;
    private Bitmap bitmap;
    private int textSizeLandscape = 40;
    private int textSizePortrait = 60;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mydetailfragment,container,false);
    }

    public void showDetails(Equipment equipment) {
        equip = equipment;
        View view;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            view  = getActivity().findViewById(R.id.detail_frag);
        }else{
            view = getActivity().findViewById(R.id.main_frame);
            Button button = (Button) view.findViewById(R.id.closeButton);
            button.setVisibility(View.VISIBLE);
        }

        TextView textIt_no = (TextView) view.findViewById(R.id.it_no2);
        TextView textType = (TextView) view.findViewById(R.id.type2);
        TextView textBrand = (TextView) view.findViewById(R.id.brand2);
        TextView textModel = (TextView) view.findViewById(R.id.model2);
        TextView textE_id = (TextView) view.findViewById(R.id.e_id);
        TextView textDescription = (TextView) view.findViewById(R.id.description);
        TextView textAquired = (TextView) view.findViewById(R.id.aquired);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            textIt_no.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeLandscape);
            textType.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeLandscape);
            textBrand.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeLandscape);
            textAquired.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeLandscape);
            textModel.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeLandscape);
            textDescription.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeLandscape);
            textE_id.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeLandscape);
        }else{
            textIt_no.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePortrait);
            textType.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePortrait);
            textBrand.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePortrait);
            textAquired.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePortrait);
            textModel.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePortrait);
            textDescription.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePortrait);
            textE_id.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePortrait);
        }
        textIt_no.setText("Item nr: \t\t\t\t" +equipment.getIt_no());
        textType.setText("Type: \t\t\t\t\t" + equipment.getType());
        textBrand.setText("Brand: \t\t\t\t" + equipment.getBrand());
        textModel.setText("Model: \t\t\t\t" + equipment.getModel());
        textE_id.setText("Equipment ID: \t" + equipment.getE_id());
        textDescription.setText("Description: \t" + equipment.getDescription());
        textAquired.setText("Aquired: \t\t\t" + equipment.getAquired());

        downloadImageThread();

        ImageView image = (ImageView) view.findViewById(R.id.equipment_image);
        image.setImageBitmap(bitmap);
    }

    private void downloadImageThread(){
        Thread thread = new Thread(null, runInBackground, "Background");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private final Runnable runInBackground = new Runnable() {
        public void run() {
            downloadImage();
        }
    };

    private void downloadImage() {
        String myURL = equip.getImage_url();

        try {
            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}