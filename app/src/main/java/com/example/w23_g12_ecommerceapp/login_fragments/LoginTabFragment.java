package com.example.w23_g12_ecommerceapp.login_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.w23_g12_ecommerceapp.MenuActivity;
import com.example.w23_g12_ecommerceapp.R;

public class LoginTabFragment extends Fragment {

    EditText editTextEmailLogin;
    EditText editTextPswLogin;
    Button btnLogin;
    TextView txtViewLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        btnLogin = view.findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener((View v) -> {
                //Toast.makeText(getContext(),"Login Button Clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), MenuActivity.class);
                startActivity(intent);
        });
        return view;
    }
}