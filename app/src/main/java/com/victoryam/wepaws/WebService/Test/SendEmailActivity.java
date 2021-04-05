package com.victoryam.wepaws.WebService.Test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.victoryam.wepaws.R;
import com.victoryam.wepaws.WebService.Model.NonQueryResultModel;
import com.victoryam.wepaws.WebService.WebServiceManager;

import java.util.concurrent.ExecutionException;

public class SendEmailActivity extends AppCompatActivity {

    private ConnectivityManager mConnMgr;

    private EditText mEmailTo, mSubject, mContent;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        mConnMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        mEmailTo = (EditText) findViewById(R.id.emailToET);
        mSubject = (EditText) findViewById(R.id.subjectET);
        mContent = (EditText) findViewById(R.id.contentET);
        mTextView = (TextView) findViewById(R.id.textView);
    }

    private boolean is_network_available() {
        if (mConnMgr != null) {
            NetworkInfo networkInfo = mConnMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        }
        Toast.makeText(this, "Network Not Available", Toast.LENGTH_LONG);
        return false;
    }

    public void send_email(View v) throws ExecutionException, InterruptedException {
        String emailTo, subject, content;
        NonQueryResultModel sendEmailResult = null;

        emailTo = mEmailTo.getText().toString();
        subject = mSubject.getText().toString();
        content = mContent.getText().toString();

        if (is_network_available()) {
            sendEmailResult = new WebServiceManager().send_email(emailTo, subject, content);
            StringBuilder result = new StringBuilder();
            result.append("Is Success: " + sendEmailResult.getIsSuccess() + '\n');
            result.append("Info: " + sendEmailResult.getInfo() + '\n');

            mTextView.setText(result);
        }
    }
}