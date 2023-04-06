package com.example.w23_g12_ecommerceapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FAQsAdapter extends RecyclerView.Adapter<FAQsAdapter.FAQViewHolder> {
    private List<FAQ> faqsList;

    public FAQsAdapter(List<FAQ> faqsList) {
        this.faqsList = faqsList;
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
        return new FAQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder holder, int position) {
        FAQ faq = faqsList.get(position);
        holder.faqQuestionTextView.setText(faq.getQuestion());
        holder.faqAnswerTextView.setText(faq.getAnswer());
    }

    @Override
    public int getItemCount() {
        return faqsList.size();
    }

    static class FAQViewHolder extends RecyclerView.ViewHolder {
        TextView faqQuestionTextView;
        TextView faqAnswerTextView;

        FAQViewHolder(View itemView) {
            super(itemView);
            faqQuestionTextView = itemView.findViewById(R.id.faqQuestionTextView);
            faqAnswerTextView = itemView.findViewById(R.id.faqAnswerTextView);
        }
    }
}

