package com.example.w23_g12_ecommerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private Context context;
    private List<Product> products;
    private TextView txtItemsCart;
    private List<Product> productsInCart;


    public ProductAdapter(Context context, List<Product> products, TextView txtItemsCart, List<Product> productsInCart) {
        this.context = context;
        this.products = products;
        this.productsInCart = productsInCart;
        this.txtItemsCart = txtItemsCart;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p = products.get(position);
        String url = p.getProdImgUrl();
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ivProduct);
        holder.tvName.setText(p.getProdName());
        holder.tvCategory.setText("Category: " + p.getProdCategory());
        holder.tvPrice.setText("Price: " + p.getProdPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProduct;
        TextView tvId, tvName, tvPrice, tvCategory, tvAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCategory = (TextView) itemView.findViewById(R.id.tvCategory);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvAddToCart = (TextView)  itemView.findViewById(R.id.tvAddToCart);
            tvAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productsInCart.add(products.get(getAdapterPosition()));
                    txtItemsCart.setText("Number of items in cart: "+productsInCart.size());
                    Toast.makeText(context,"Product added to cart",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
