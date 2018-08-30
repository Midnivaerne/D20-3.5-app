package com.aurora.d20_35_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.aurora.d20_35_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button DM_button = (Button) findViewById(R.id.DM_button);
        DM_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_DM = new Intent(MainActivity.this, DM_Activity.class);
                MainActivity.this.startActivity(intent_DM);
                Log.i("Content ", " Main layout to DM ");
            }
        });

        Button PC_button = (Button) findViewById(R.id.PC_button);
        PC_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_PC = new Intent(MainActivity.this, PC_Activity.class);
                MainActivity.this.startActivity(intent_PC);
                Log.i("Content ", " Main layout to PC ");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent intent_settings = new Intent(MainActivity.this, Settings_Activity.class);
                MainActivity.this.startActivity(intent_settings);
                Log.i("Content ", " Main layout to Settings ");
                break;
            case R.id.action_help:
                Intent intent_help = new Intent(MainActivity.this, Help_Activity.class);
                MainActivity.this.startActivity(intent_help);
                Log.i("Content ", " Main layout to Help ");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
