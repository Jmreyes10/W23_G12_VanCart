package com.example.w23_g12_ecommerceapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    private CharSequence activityTitle;
    private CharSequence itemTitle;
    private String[] tagTitles;
//    TextView txtViewUsername;
//    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        itemTitle = activityTitle = getTitle();
        tagTitles = getResources().getStringArray(R.array.Tags);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slider1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


//        txtViewUsername=findViewById(R.id.txtViewUsername);
//
//        DB = new DBHelper(this);
//        Bundle bundle=getIntent().getExtras();
//        String name=bundle.getString("NAME","NOTHING");
//        txtViewUsername.setText("Welcome "+name);


        //Create elements of the list
        ArrayList<DrawerItem> items = new ArrayList<DrawerItem>();
        items.add(new DrawerItem(tagTitles[0], R.drawable.icon_feature01));
        items.add(new DrawerItem(tagTitles[1], R.drawable.icon_feature02));
        items.add(new DrawerItem(tagTitles[2], R.drawable.icon_feature03));
        items.add(new DrawerItem(tagTitles[3], R.drawable.icon_feature04));
        items.add(new DrawerItem(tagTitles[4], R.drawable.icon_feature05));
        items.add(new DrawerItem(tagTitles[5], R.drawable.icon_feature06));

        // Relate the adapter and the DrawerItemClickListener
        drawerList.setAdapter(new DrawerListAdapter(this, items));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Crete ActionBarDrawerToggle for opening and closing
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(itemTitle);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(activityTitle);

            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MenuActivity.this, "SelectItem called", Toast.LENGTH_SHORT).show();
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        // Start the selected feature activity after closing the drawer
        drawerList.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerList);

        switch (position){
            case 0:
//                startActivity(new Intent(this, Feature01Activity.class));
                break;
            case 1:
                startActivity(new Intent(this, Feature02Activity.class));
                break;
            case 2:
                startActivity(new Intent(this, Feature03Activity.class));
                break;
            case 3:
                startActivity(new Intent(this, Feature04Activity.class));
                break;
            case 4:
                startActivity(new Intent(this, Feature05Activity.class));
                break;
            case 5:
                startActivity(new Intent(this, Feature06Activity.class));
                break;
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
}