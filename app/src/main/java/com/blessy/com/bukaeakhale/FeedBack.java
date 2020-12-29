package com.blessy.com.bukaeakhale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedBack extends AppCompatActivity {

    private EditText txtSenderName, txtSenderEmail, txtSenderFeedBack;
    private Button btnSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        txtSenderName = findViewById(R.id.txtSenderName);
        txtSenderEmail = findViewById(R.id.txtSenderEmail);
        txtSenderFeedBack = findViewById(R.id.txtSenderFeedBack);
        btnSendEmail = findViewById(R.id.btnSendEmail);


        btnSendEmail.setOnClickListener( (View v) -> {

            String name = txtSenderName.getText().toString();
            String email = txtSenderEmail.getText().toString();
            String feedback = txtSenderFeedBack.getText().toString();

            if(name.length() > 0 && email.length() > 0 && feedback.length() > 0){

                String [] toEmails = {"molorane.mothusi@gmail.com"};
                String [] from = {txtSenderEmail.getText().toString()};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, toEmails);
                intent.putExtra(Intent.EXTRA_CC, from);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Bible FeedBack: "+ txtSenderName.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, txtSenderFeedBack.getText().toString());
                intent.putExtra(Intent.EXTRA_SUBJECT, "Sotho Bible FeedBack");
                startActivity(Intent.createChooser(intent, "Choose application"));
                
            }else{
                Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
            }

        });
    }
}