package com.example.magnusfinvik.listapp;


import android.app.ListFragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.*;
import java.util.*;


public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener {
    ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mylistfragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startDownload();
        MyAdapter myAdapter = new MyAdapter(getActivity(), equipmentList);
        setListAdapter(myAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            showDetailsLandscape(position);
        }else {
            showDetailsPortrait(position);
        }

    }

    private void showDetailsPortrait(int position) {
        MyDetailFragment details= new MyDetailFragment();
        this.getFragmentManager().beginTransaction().replace(R.id.main_frame, details).commit();
        getFragmentManager().executePendingTransactions();
        details.showDetails(equipmentList.get(position));

    }

    private void showDetailsLandscape(int position) {
        MyDetailFragment details = (MyDetailFragment) getFragmentManager().findFragmentById(R.id.detail_frag);
        details.showDetails(equipmentList.get(position));
    }


    private void startDownload() {
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
            connectToServer();
        }
    };

    private void connectToServer(){
        String myURL = "http://kark.hin.no:8088/d3330log_backend/getTestEquipment";
        HttpURLConnection conn;

        try{
            URL url = new URL(myURL);
            conn = (HttpURLConnection) url.openConnection();

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                readData(conn.getInputStream());
            }else{
                Log.d(this.getClass().toString(), "Fail to connect to server");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        Gson gson = new Gson();
        equipmentList = gson.fromJson(br, new TypeToken<List<Equipment>>(){}.getType());
    }

}
