package com.vokasi.sqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnStore,btnGetAll;
    EditText etName;
    TextView tvnames;

    DatabaseHelper databaseHelper;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStore=findViewById(R.id.btnStore);
        btnGetAll=findViewById(R.id.btnget);
        tvnames=findViewById(R.id.tvnames);
        etName=findViewById(R.id.etname);

        databaseHelper=new DatabaseHelper(this);

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //databaseHelper.addStudentDetail(
                //        etName.getText().toString());
                etName.setText("");
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //arrayList=databaseHelper.getAllStudentsList();
                tvnames.setText(arrayList.get(0));
                for (int i=1;i<arrayList.size();i++){
                    tvnames.setText(tvnames.getText().toString()
                            +", "+arrayList.get(i));
                }
            }
        });


    }
}
