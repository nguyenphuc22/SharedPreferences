package com.example.sharedpre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText edtAccount,edtPassword;
    CheckBox cbSave;
    // We are assuming the login functionality by defaulting to a correct account and password
    String correctAccount = "PhucVr";
    String correctPassword = "12345";
    // Now let's get to the main part.
    // We will use shared preferences to save the correct account and password so that next time, users do not need
    // to input it again
    SharedPreferences sharedPreferences;
    public void login (View view) {
        String sAccount = edtAccount.getText().toString();
        String sPassword = edtPassword.getText().toString();
        // we check to see if the login is successful
        if ( sAccount.equals(correctAccount) && sPassword.equals(correctPassword) ) {

            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
            // we check if checkbox is checked, we store the account , password and state checkbox
            // we don't want to save it, what should we do ?
            // we just remove it :D
            if (cbSave.isChecked()) {

                sharedPreferences.edit().putString("account",sAccount).commit();
                sharedPreferences.edit().putString("password",sPassword).commit();
                sharedPreferences.edit().putBoolean("save",cbSave.isChecked()).commit();
            } else {

                sharedPreferences.edit().remove("account").commit();
                sharedPreferences.edit().remove("password").commit();
                sharedPreferences.edit().remove("save").commit();

            }

        } else {

            Toast.makeText(this,"Login Fail",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtAccount = findViewById(R.id.edtAccount);
        edtPassword = findViewById(R.id.edtPassword);
        cbSave = findViewById(R.id.cbSave);
        // SharedPreferences are where you can store them as key - value
        // key used to call , value is the value to be stored
        sharedPreferences = getApplication().getSharedPreferences("PhucVr",MODE_PRIVATE);

        // let's get data from shared preferences
        // Here the key we have to input is the same as the one you saved
        // I recommend you to coppy it.
        // defValue you should give it any parameter because if the sharedPreferences is not saved
        // the value you give will be returned
        edtAccount.setText(sharedPreferences.getString("account",""));
        edtPassword.setText(sharedPreferences.getString("password",""));
        cbSave.setChecked(sharedPreferences.getBoolean("save",false));
    }
}