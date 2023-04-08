package com.example.w23_g12_ecommerceapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {

    private Context context;
    private int layoutResourceId;
    private List<Order> data;

    public OrderAdapter(Context context, int layoutResourceId, List<Order> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new OrderHolder();
            holder.name = (TextView) convertView.findViewById(R.id.order_name);
            //holder.status=(TextView) convertView.findViewById(R.id.order_status);
            //holder.address=(TextView) convertView.findViewById(R.id.order_status);

            convertView.setTag(holder);
        } else {
            holder = (OrderHolder) convertView.getTag();
        }

        // Get the order at this position
        Order currentOrder = data.get(position);


        // Set the name of the customer in the TextView
        holder.name.setText(currentOrder.getCustomerName());
        //holder.status.setText(currentOrder.getDeliveryStatus());
//        holder.address.setText(currentOrder.getCustomerAddress());
//        Button deliveredButton = convertView.findViewById(R.id.order_delivered_button);
//        deliveredButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deliveredButton.setText("Delivered\u2713");
//            }
//        });



        return convertView;
    }

    static class OrderHolder {
        TextView status;
        TextView address;
        TextView name;
    }
}
