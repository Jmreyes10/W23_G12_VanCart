//package com.example.w23_g12_ecommerceapp;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class DrawerListAdapter extends ArrayAdapter {
//    public DrawerListAdapter(Context context, List objects) {
//        super(context, 0, objects);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        if(convertView == null){
//            LayoutInflater inflater = (LayoutInflater)parent.getContext().
//                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.drawer_list_item, null);
//        }
//
//
//        TextView feature = (TextView) convertView.findViewById(R.id.feature);
//
//        DrawerItem item = (DrawerItem) getItem(position);
//        feature.setText(item.getFeature());
//
//        return convertView;
//    }
//
//}
