package com.victoryam.wepaws.WebService.Test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

public class CreateAccountActivity extends AppCompatActivity {

    private ConnectivityManager mConnMgr;

    private EditText mLogin,mPassword;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mConnMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        mLogin = (EditText) findViewById(R.id.loginET);
        mPassword = (EditText) findViewById(R.id.passwordET);
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

    public void create_account(View v) throws ExecutionException, InterruptedException {
        String login, password;
        NonQueryResultModel createAccountResult = null;

        login = mLogin.getText().toString();
        password = mPassword.getText().toString();

        if (is_network_available()) {
            createAccountResult = new WebServiceManager().create_account(login, password);
            StringBuilder result = new StringBuilder();
            result.append("Is Success: " + createAccountResult.getIsSuccess() + '\n');
            result.append("Info: " + createAccountResult.getInfo() + '\n');
            mTextView.setText(result);
        }
    }
}