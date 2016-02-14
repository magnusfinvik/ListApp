package com.example.magnusfinvik.listapp;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            inPortrait();
        }
        setContentView(R.layout.activity_main);
    }

    private void inPortrait() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        MyListFragment myListFragment = new MyListFragment();
        transaction.replace(R.id.main_frame,myListFragment);
        transaction.commit();
        getFragmentManager().executePendingTransactions();
    }

    public void closeInfo(View view) {
        inPortrait();
    }
}
