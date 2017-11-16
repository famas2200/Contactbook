package com.example.cwl.contactbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private Contact c;
    private TextView tvID, tvName, tvTel, tvAddr;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
    }

    private void init()
    {
        tvID = (TextView)findViewById(R.id.tvID);
        tvName = (TextView)findViewById(R.id.tvName);
        tvTel = (TextView)findViewById(R.id.tvTel);
        tvAddr = (TextView)findViewById(R.id.tvAddr);

        Intent in = getIntent();
        id = in.getIntExtra("id", 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ContactDAO dao = new ContactDAO_impl(DetailActivity.this);
        c = dao.getOne(id);
        tvID.setText(String.valueOf(c.id));
        tvName.setText(c.name);
        tvTel.setText(c.tel);
        tvAddr.setText(c.addr);
    }

    public void clickBack(View view)
    {
        finish();
    }

    public void clickDelete(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setTitle("確認刪除");
        builder.setMessage("請確認是否刪除本筆資料?");
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ContactDAO dao = new ContactDAO_impl(DetailActivity.this);
                dao.delete(c);

                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void clickUpdate(View view) {
        Intent in = new Intent(DetailActivity.this, EditActivity.class);
        in.putExtra("id", c.id);
        startActivity(in);
    }
}
