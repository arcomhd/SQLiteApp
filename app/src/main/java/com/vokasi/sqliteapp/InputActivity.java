package com.vokasi.sqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {
    EditText editNama;
    EditText editAlamat;
    DatabaseHelper databaseHelper;
    int id;
    String nama, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editNama=findViewById(R.id.editNama);
        editAlamat=findViewById(R.id.editAlamat);
        databaseHelper=new DatabaseHelper(this);
        Bundle extras = getIntent().getExtras();
        id=-1;

        if (extras != null) {
            getSupportActionBar().setTitle("Ubah Data");
            id=extras.getInt("id");
            nama=extras.getString("nama");
            alamat=extras.getString("alamat");
            editNama.setText(nama);
            editAlamat.setText(alamat);

        } else {
            getSupportActionBar().setTitle("Tambah Data");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    public void submitData(View view) {
        if(id==-1) {
            databaseHelper.addStudentDetail(
                    editNama.getText().toString()
                    , editAlamat.getText().toString());
        }else {
            databaseHelper.update(id,editNama.getText().toString(),
                    editAlamat.getText().toString());
        }
        onBackPressed();
    }

    public void kembali(View view) {
        onBackPressed();
    }
}
