package com.ishmit.androidtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    RadioButton btn1, btn2;
    TextInputEditText text, multinumber;
    TextInputLayout customText, textbox1;
    Chip send, senddef;
    public String message;
    TextInputEditText input1;
    LinearLayout defhide, customhide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (RadioButton) findViewById(R.id.btn1);
        btn2 = (RadioButton) findViewById(R.id.btn2);
        text = (TextInputEditText) findViewById(R.id.textfield);
        customText = (TextInputLayout) findViewById(R.id.customText);
        send = (Chip) findViewById(R.id.send);
        input1 = (TextInputEditText) findViewById(R.id.input1);
        senddef = (Chip) findViewById(R.id.senddef);
        customhide = (LinearLayout) findViewById(R.id.customhide);
        defhide = (LinearLayout) findViewById(R.id.defhide);
        multinumber = (TextInputEditText) findViewById(R.id.multinumber);
        textbox1 = (TextInputLayout) findViewById(R.id.textbox1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                // Check which radiobutton was pressed
                if (checked) {
                    customhide.setVisibility(View.INVISIBLE);
                    defhide.setVisibility(View.VISIBLE);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                // Check which radiobutton was pressed
                if (checked) {
                    customhide.setVisibility(View.VISIBLE);
                    defhide.setVisibility(View.INVISIBLE);
                }
            }
        });



            senddef.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String input = multinumber.getText().toString();
           String[] numbers = input.split("\\s+");
           String mobile = input1.getText().toString();
           if (mobile != null && !mobile.isEmpty())  {
               msg(mobile, message);
           }
           else if (numbers != null) {
               for (String number : numbers) {
                       msg(number, message);

               }
           }
       }

   });

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String input = multinumber.getText().toString();
                    String[] numbers = input.split("\\s+");

                    String mobile = input1.getText().toString();
                    String cus = text.getText().toString();
                    if (mobile != null && !mobile.isEmpty())  {
                        msg(mobile, cus);
                    }
                    else if(numbers!=null){

                        for (String number : numbers) {

                        msg(number,cus);
                        }

                    }
                }
            });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.privacy) {
            Toast.makeText(this, "Privacy Policy", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.disclaimer) {
            Toast.makeText(this, "Disclaimer", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.tandc) {
            Toast.makeText(this, "Terms and conditions", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.others) {
            Toast.makeText(this, "Other Apps", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Hello! I am from Whats-app InDirect 😀";
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        }
        if (id == R.id.def) {
            Intent i = new Intent(getApplicationContext(), DefaultMessage.class);
            startActivity(i);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        message = sharedPreferences.getString("myDataKey", "Hello! I am from Whats-app InDirect 😀");

    }

    private void msg(String number, String message) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + ("91" + number) + "&text=" + message));
            startActivity(intent);
        } catch (Exception e6) {
            e6.printStackTrace();
        }
    }
    }