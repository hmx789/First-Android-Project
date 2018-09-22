package com.example.hassan.cs478project1;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "Main Activity";
    static final int ENTER_NAME = 1;
    String contact_name = "";
    int result = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {           // Starting second activity if button1 clicked
                //Log.i(TAG, "BUTTON1 CLICKED");
                Intent intent = new Intent(v.getContext(), SecondActivity.class);
                startActivityForResult(intent, ENTER_NAME);
            }

        });

        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG,"BUTTON2 CLICKED");
                if (contact_name.isEmpty() ) {      // If no contact name was entered
                    Toast.makeText(v.getContext(),"No contact name entered", Toast.LENGTH_LONG).show();

                }
                else if (result == RESULT_CANCELED) {  // if bad contact name
                    Toast.makeText(v.getContext(),"Incorrect Name Entered, name: " + contact_name, Toast.LENGTH_LONG).show();
                }
                else if (result == RESULT_OK) {     // Opening edit contacts with the name
                    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, contact_name);
                    startActivity(intent);
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ENTER_NAME)  {
            // Checking result
            if (resultCode == RESULT_OK) {
                contact_name = data.getStringExtra("res_string");
                result = RESULT_OK;
            }
            else if (resultCode == RESULT_CANCELED) {
                if (data != null) {
                    contact_name = data.getStringExtra("res_string");
                    result = RESULT_CANCELED;
                }
            }

        }
    }
}
