package com.example.firstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    EditText itemName;
    EditText quantity;
    EditText cost;
    EditText description;
    EditText location;
    ToggleButton frozen;
    SharedPreferences sp;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button saveButton;
    Button clearButton;
    FloatingActionButton fab;
    ArrayAdapter<String> adapter;
    ArrayList sourceData;
    ListView items;
    static ArrayList<Item> itemArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        itemName = findViewById(R.id.ItemName);
        quantity = findViewById(R.id.Quantity);
        cost = findViewById(R.id.Cost);
        description = findViewById(R.id.Description);
        frozen = findViewById(R.id.toggleButton);
        location = findViewById(R.id.Location);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        saveButton = findViewById(R.id.savebutton);
        clearButton = findViewById(R.id.clearbutton);
        itemArrayList=new ArrayList<>();
        itemName.requestFocus();

        //set listeners for buttons
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        //set toolbar
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //set navigation menu
        navigationView.setNavigationItemSelectedListener(new MyNavigationListener());

        //set listview
        sourceData = new ArrayList<String>();
        items = findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sourceData);
        items.setAdapter(adapter);


        // set the value from shared preference
        sp = getSharedPreferences("Data", 0);

        adapter.notifyDataSetChanged();
        itemName.setText(sp.getString("itemName", ""));
        quantity.setText(sp.getString("quantity", ""));
        cost.setText(sp.getString("cost", ""));
        description.setText(sp.getString("description", ""));
        location.setText(sp.getString("location", ""));
        frozen.setChecked(sp.getBoolean("frozen", false));

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));


    }

    class MyBroadCastReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);
            StringTokenizer sT = new StringTokenizer(msg, ";");
            String deleteOrNot = sT.nextToken();
            if (deleteOrNot.equals("DeleteName")) {
                itemName.setText("");
                sp = getSharedPreferences("Data", 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("itemName");
                editor.apply();
            } else {
                String MitmeName = deleteOrNot;
                String Mquantity = sT.nextToken();
                String Mcost = sT.nextToken();
                String Mdescription = sT.nextToken();
                String Mfrozen = sT.nextToken();
                String Mlocation = sT.nextToken();
                itemName.setText(MitmeName);
                quantity.setText(Mquantity);
                cost.setText(Mcost);
                description.setText(Mdescription);
                frozen.setChecked(Boolean.parseBoolean(Mfrozen));
                location.setText(Mlocation);
            }

        }
    }

    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.nav_1:
                    saveItem();
                    break;
                case R.id.nav_2:
                    clear();
                    break;
                case R.id.nav_3:
                    saveToList();
                    break;
                case R.id.nav_4:
                    clearList();
                    break;
                case R.id.nav_5:
                    deleteLast();
                    break;
                case R.id.nav_6:
                    startActivity(new Intent(MainActivity.this,AnotherActivity.class));
                    break;
//                case R.id.nav_7:
//                    itemArrayList.clear();
//                    break;



            }
            drawerLayout.closeDrawer(GravityCompat.START);


            return true;
        }
    }

    public void saveItem() {
        Context context = getApplicationContext();
        CharSequence text = "New item" + " (" + itemName.getText() + ") " + "have been added.";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        sp = getSharedPreferences("Data", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("itemName", itemName.getText().toString());
        editor.putString("quantity", quantity.getText().toString());
        editor.putString("cost", cost.getText().toString());
        editor.putString("description", description.getText().toString());
        editor.putString("location", location.getText().toString());
        editor.putBoolean("frozen", frozen.isChecked());
        editor.commit();
        itemArrayList.add(new Item(itemName.getText().toString(),Integer.parseInt(quantity.getText().toString()),
                Double.parseDouble(cost.getText().toString()),
                description.getText().toString(),frozen.isChecked(),location.getText().toString()));

    }

    public void clear() {

        itemName.getText().clear();
        quantity.getText().clear();
        cost.getText().clear();
        description.getText().clear();
        location.getText().clear();
        frozen.setChecked(false);
        sp = getSharedPreferences("Data", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
        //asynchronous


    }

    public void updateQuantity(View view) {
        sp = getSharedPreferences("Data", 0);
        SharedPreferences.Editor editor = sp.edit();
        int itemQuantity = Integer.parseInt(sp.getString("quantity", "0"));
        itemQuantity = itemQuantity * 2;
        quantity.setText(String.valueOf(itemQuantity));
        editor.putString("quantity", String.valueOf(itemQuantity));
        editor.apply();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.option_1:
                saveItem();
                break;
            case R.id.option_2:
                clear();
                break;
            case R.id.option_3:
                saveToList();
                break;
            case R.id.option_4:
                clearList();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveToList() {
        sourceData.add(itemName.getText().toString());
        adapter.notifyDataSetChanged();

    }

    public void clearList() {
        sourceData.clear();
        adapter.notifyDataSetChanged();
    }
    public void deleteLast(){
        sourceData.remove(sourceData.size()-1);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}