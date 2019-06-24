package com.example.repairgrab.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.example.repairgrab.R;

public class LoginActivity extends AppCompatActivity {

    private Spinner dropdownlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dropdownlist = findViewById(R.id.dropdownlist);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.spinner_item,R.layout.spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



    }


    public void clickToLoginAsRepairer(View view) {
        Intent intent = new Intent(this, RepairerHomeActivity.class);
        startActivity(intent);
        finish();
    }
}
