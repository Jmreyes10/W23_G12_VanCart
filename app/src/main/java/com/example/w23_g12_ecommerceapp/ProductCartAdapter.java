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
import androidx.room.Room;

import com.example.w23_g12_ecommerceapp.dao.ProductDatabase;

import java.util.List;

public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ProductViewHolder>{

    private Context context;
    private List<Product> productsInCart;
    private List<Integer> prodQtty;

    public ProductCartAdapter(Context context, List<Product> productsInCart, List<Integer> prodQtty) {
        this.context = context;
        this.productsInCart = productsInCart;
        this.prodQtty = prodQtty;
    }


    @NonNull
    @Override
    public ProductCartAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_cart, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCartAdapter.ProductViewHolder holder, int position) {
        Product p = productsInCart.get(position);

        String url = p.getProdImgUrl();
        holder.tvProdCartName.setText(p.getProdName());
        holder.tvProdCartPrice.setText(String.valueOf(p.getProdPrice()));
        holder.tvProdCartQty.setText(String.valueOf(prodQtty.get(position)));
    }

    @Override
    public int getItemCount() {
        return productsInCart.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView tvProdCartName, tvProdCartPrice, tvProdCartQty, tvProdCartRemove;
        ImageView ivAddQtty, ivMinusQtty;

        ProductDatabase productDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                ProductDatabase.class,
                "products_db"
        ).allowMainThreadQueries().build();

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProdCartName = (TextView) itemView.findViewById(R.id.tvProdCartName);
            tvProdCartPrice = (TextView) itemView.findViewById(R.id.tvProdCartPrice);
            tvProdCartQty = (TextView) itemView.findViewById(R.id.tvProdCartQty);
            tvProdCartRemove = (TextView)  itemView.findViewById(R.id.tvProdCartRemove);
            ivAddQtty = (ImageView) itemView.findViewById(R.id.ivAddQtty);
            ivMinusQtty = (ImageView) itemView.findViewById(R.id.ivMinusQtty);

            ivAddQtty.setOnClickListener((View v) -> {
                int position = getAdapterPosition();
                prodQtty.set(position,prodQtty.get(position)+1);
                ProductCartAdapter.this.notifyDataSetChanged();
            });

            ivMinusQtty.setOnClickListener((View v) -> {
                int position = getAdapterPosition();
                prodQtty.set(position,Math.max(prodQtty.get(position)-1, 1));
                ProductCartAdapter.this.notifyDataSetChanged();
            });

            tvProdCartRemove.setOnClickListener((View v) -> {
                productDatabase.productDao().deleteProduct(productsInCart.get(getAdapterPosition()));
                productsInCart.remove(getAdapterPosition());
                ProductCartAdapter.this.notifyDataSetChanged();
                Toast.makeText(context,"Product removed from cart",Toast.LENGTH_SHORT).show();
                int rowCount = productDatabase.productDao().getRowCount();
                if(rowCount==0){
                    ((ViewCart)context).finish();
                }
            });
        }
    }
}
