package com.example.cwl.contactbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private EditText addName, addTel, addAddr;
    Contact c = new Contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();
    }

    private void init()
    {
        addName = (EditText)findViewById(R.id.addName);
        addTel = (EditText)findViewById(R.id.addTel);
        addAddr = (EditText)findViewById(R.id.addAddr);
    }

    public void OK(View v)
    {
        ContactDAO dao = new ContactDAO_impl(AddActivity.this);
        c.name = addName.getText().toString();
        c.tel = addTel.getText().toString();
        c.addr = addAddr.getText().toString();
        dao.add(c);

        finish();
    }
}
