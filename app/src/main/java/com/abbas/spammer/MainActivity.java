package com.abbas.spammer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences spams;
    Button add, remove, done;
    EditText spamMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAndRequestPermissions();

        spams = getSharedPreferences("SPAMS",MODE_PRIVATE);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        done = findViewById(R.id.done);
        spamMessages = findViewById(R.id.SpamMessage);

        done.setVisibility(View.GONE);
        spamMessages.setTextIsSelectable(false);


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spamMessages.setTextIsSelectable(true);
                remove.setVisibility(View.GONE);
                done.setVisibility(View.VISIBLE);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spamMessages.setTextIsSelectable(false);
                remove.setVisibility(View.VISIBLE);
                done.setVisibility(View.GONE);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSpammer addSpammer = new AddSpammer();
                Intent nextPage = new Intent(MainActivity.this, addSpammer.getClass());
                startActivity(nextPage);
            }
        });
    }
    private void checkAndRequestPermissions()
    {
        int sms = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        if (sms != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);
        }
    }
}