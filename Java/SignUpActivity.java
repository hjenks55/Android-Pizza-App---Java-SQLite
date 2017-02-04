package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText email, password, passwordConfirmed, address;
    Button cancel, ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = (EditText) findViewById(R.id.editText4);
        password = (EditText) findViewById(R.id.editText5);
        passwordConfirmed = (EditText) findViewById(R.id.editText6);
        address = (EditText) findViewById(R.id.editText7);

        //Back cancel button to home page
        cancel = (Button) findViewById(R.id.button7);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

        ok = (Button) findViewById(R.id.button8);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                //Validate sign up form
                //level 1 - email
                final String emailString = email.getText().toString();
                if (!isValidEmail(emailString)) {
                    email.setError("Invalid Email. Must end with @.com");
                } else {
                    //level 2 - password
                    final String passString = password.getText().toString();
                    if (!isValidPassword(passString)) {
                        password.setError("Invalid Password. Must be more then 6 characters");
                    } else {
                        //level 3 - password confirm
                        final String passConfirmString = passwordConfirmed.getText().toString();
                        if (!passwordMatchesConfirm(passConfirmString, passString)) {
                            passwordConfirmed.setError("Passwords do not match");
                        } else {
                            //Create user
                            //writeFile();
                            //readFile();

                            writeObjectFile();
                            readObjectFile();

                            String message = "You have successfully created an account!";
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                }
            }
        });
    }

    // validating email id
    private boolean isValidEmail(String emailString) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailString);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String passString) {
        if (passString != null && passString.length() > 6) {
           return true;
        }
        return false;
    }

    private boolean passwordMatchesConfirm(String passConfirmString, String passString) {
        if (passConfirmString.equals(passString)) {
            //Check password matches confirm password
                return true;
        }
        return false;
    }


    public void writeFile() {
        // add-write text into file
        try {
            FileOutputStream fileout = openFileOutput("userEmails.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(email.getText().toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(this, "User Created!",
                    Toast.LENGTH_SHORT).show();

            //IMPORTANT!!********SET GLOBAL VARIABLE ISLOGGEDIN TO TRUE **************
            ((MyApplication) this.getApplication()).setLoggedIn(true);

            Toast.makeText(this, ((MyApplication) this.getApplication()).getStringLoggedIn(),
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("userEmails.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[100];  // declare this int constant = 100
            String s="";
            int charRead;
            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            Toast.makeText(this, s,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    public void writeObjectFile(){
        try {
            FileOutputStream out ;

            email = (EditText) findViewById(R.id.editText4);
            password = (EditText) findViewById(R.id.editText5);
            address = (EditText) findViewById(R.id.editText7);

            Customer c = new Customer(email.getText().toString(),password.getText().toString(),address.getText().toString());
            out=openFileOutput("myobjfile", MODE_PRIVATE);
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(c);
            oout.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void readObjectFile() {

        FileInputStream inputStream;
        try {
            inputStream =  openFileInput("myobjfile");
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Customer c = new Customer((Customer)ois.readObject());

            TextView txtUsername =(TextView) findViewById(R.id.textView17);
            TextView txtPassword =(TextView) findViewById(R.id.textView18);
            TextView txtAddress =(TextView) findViewById(R.id.textView20);

            txtUsername.setText(c.getUsername());
            txtPassword.setText(c.getPassword());
            txtAddress.setText(c.getAddress());

        } catch ( Exception ioe ) {
        }
    }
}







