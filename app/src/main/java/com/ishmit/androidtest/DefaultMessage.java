package com.ishmit.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

public class DefaultMessage extends AppCompatActivity {

    TextInputEditText def;
    Chip save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_message);
        def = (TextInputEditText) findViewById(R.id.defaultext);
        save = (Chip) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String DefaultMessage = def.getText().toString();
                editor.putString("myDataKey", DefaultMessage);
                editor.apply();
                Toast.makeText(DefaultMessage.this, "Message save Successfully !", Toast.LENGTH_SHORT).show();
            }
        });


    }
}