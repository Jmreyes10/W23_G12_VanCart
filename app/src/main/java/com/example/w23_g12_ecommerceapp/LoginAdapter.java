package com.example.w23_g12_ecommerceapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.w23_g12_ecommerceapp.login_fragments.LoginTabFragment;
import com.example.w23_g12_ecommerceapp.login_fragments.SignupTabFragment;

public class LoginAdapter extends FragmentStateAdapter {
    Lifecycle lifecycle;
    int totalTabs;

    public LoginAdapter(FragmentManager fm, Lifecycle lifecycle, int totalTabs){
        super(fm, lifecycle);
        this.totalTabs = totalTabs;
    }

    public int getItemCount() {
        return totalTabs;
    }

    public Fragment createFragment(int position){
        switch (position){
            case 0:
                LoginTabFragment loginTab = new LoginTabFragment();
                return loginTab;
            case 1:
                SignupTabFragment signupTab = new SignupTabFragment();
                return signupTab;
            default:
                return null;
        }
    }

}
