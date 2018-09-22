package com.example.hassan.cs478project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    final String TAG = "Second Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final EditText et =  findViewById(R.id.editText);
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {       //Runs on return or done key
                if (isNameValid(et.getText().toString().trim()) ) {
                    Intent newI = new Intent();
                    newI.putExtra("res_string",et.getText().toString().trim());
                    setResult(Activity.RESULT_OK,newI);
                }
                else {
                    Intent intentC = new Intent();
                    intentC.putExtra("res_string",et.getText().toString().trim());
                    setResult(Activity.RESULT_CANCELED,intentC);
                }
                finish();
                return false;
            }
        });
    }

    public boolean isNameValid(String name) { // Function to check if name entered was valid
        int count = 0;
        if (name.isEmpty()) {
            return false;
        }
        for (int i = 0; i < name.length();i++) {

            if (name.charAt(i) == ' ') {
                while (name.charAt(i) == ' ') {             // Counting the space blocks
                    i++;
                }
                count++;
                if (count > 1) {                    // Making sure there is only first and last name
                    return false;
                }
            }
            else if (!Character.isLetter(name.charAt(i))) {
                return false;
            }
        }
        if (count == 0)             // Checking if their was no space at all
            return false;
        return true;
    }
}

