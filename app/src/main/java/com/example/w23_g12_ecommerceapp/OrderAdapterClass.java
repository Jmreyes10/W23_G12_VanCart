package com.example.w23_g12_ecommerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapterClass extends RecyclerView.Adapter<OrderAdapterClass.ViewHolder>{

    List<OrderModel> orderModelList;
    Context context;
    OrderHistoryDBHelper orderHistoryDBHelper;

    public OrderAdapterClass(List<OrderModel> orderModelList, Context context) {
        this.orderModelList = orderModelList;
        this.context = context;
        orderHistoryDBHelper = new OrderHistoryDBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderModel orderModel = orderModelList.get(position);

        holder.prodID.setText("Order ID: " + Integer.toString(orderModel.getId()));
        holder.prodName.setText("Product Name: " + orderModel.getProdName());
        holder.prodQty.setText("Product Quantity: " + orderModel.getProdQuantity());
        holder.prodPrice.setText("Product Price: " + orderModel.getProdPrice());

    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView prodID, prodName, prodQty, prodPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            prodID = itemView.findViewById(R.id.orderID);
            prodName = itemView.findViewById(R.id.prodName);
            prodQty = itemView.findViewById(R.id.prodQty);
            prodPrice = itemView.findViewById(R.id.prodPrice);

        }
    }
}
