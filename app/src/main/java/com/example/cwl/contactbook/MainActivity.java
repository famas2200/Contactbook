package com.example.cwl.contactbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private Contact[] c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init()
    {
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ContactDAO dao = new ContactDAO_impl(MainActivity.this);
        c = dao.getList();
        String[] str = new String[c.length];

        for (int i = 0; i < c.length; i++)
        {
            str[i] = c[i].name + "," + c[i].tel + "," + c[i].addr;
        }

        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, str);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int id = c[position].id;

                Intent in = new Intent(MainActivity.this, DetailActivity.class);
                in.putExtra("id", id);
                startActivity(in);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("新增");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent in = new Intent(MainActivity.this, AddActivity.class);
        startActivity(in);

        return super.onOptionsItemSelected(item);
    }
}
