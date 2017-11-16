package com.example.cwl.contactbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    private Contact c;
    private EditText etName, etTel, etAddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();
    }

    private void init()
    {
        etName = (EditText)findViewById(R.id.etName);
        etTel = (EditText)findViewById(R.id.etTel);
        etAddr = (EditText)findViewById(R.id.etAddr);

        Intent in = getIntent();
        int id = in.getIntExtra("id", 0);

        ContactDAO dao = new ContactDAO_impl(EditActivity.this);
        c = dao.getOne(id);

        etName.setText(c.name);
        etTel.setText(c.tel);
        etAddr.setText(c.addr);
    }

    public void clickBack(View view) {
        finish();
    }

    public void clickUpdate(View view) {
        ContactDAO dao = new ContactDAO_impl(EditActivity.this);
        c.name = etName.getText().toString();
        c.tel = etTel.getText().toString();
        c.addr = etAddr.getText().toString();
        dao.edit(c);

        finish();
    }
}
