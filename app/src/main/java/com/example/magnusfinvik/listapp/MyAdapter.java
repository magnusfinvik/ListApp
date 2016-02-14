package com.example.magnusfinvik.listapp;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Equipment>{
    private final Context context;
    private final ArrayList<Equipment> equipmentList;

    public MyAdapter(Context context, ArrayList<Equipment> equipmentList) {
        super(context,R.layout.itemlayout, equipmentList);
        this.equipmentList = equipmentList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.itemlayout, parent, false);
        TextView textIt_no = (TextView) rowView.findViewById(R.id.it_no);
        TextView textType = (TextView) rowView.findViewById(R.id.type);
        TextView textBrand = (TextView) rowView.findViewById(R.id.brand);
        TextView textModel = (TextView) rowView.findViewById(R.id.model);

        textIt_no.setText(equipmentList.get(position).getIt_no()+"\n");
        textType.setText(equipmentList.get(position).getType());
        textBrand.setText(equipmentList.get(position).getBrand());
        textModel.setText(equipmentList.get(position).getModel());

        return rowView;
    }

}
