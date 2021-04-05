package com.victoryam.wepaws;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.victoryam.wepaws.WebService.Model.NonQueryResultModel;
import com.victoryam.wepaws.WebService.WebServiceManager;

import java.util.concurrent.ExecutionException;

public class CreateAccountFragment extends Fragment {
    EditText createAccountEditText;
    EditText createPasswordEditText;

    TextView createStatusTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_account, container, false);

        createAccountEditText = (EditText) view.findViewById(R.id.create_account_username_edittext);
        createPasswordEditText = (EditText) view.findViewById(R.id.create_account_password_edittext);

        createStatusTextView = (TextView) view.findViewById(R.id.create_account_status);

        Button createAccountButton = (Button) view.findViewById(R.id.create_account_create_button);
        createAccountButton.setOnClickListener(new createAccountButtonClicked(this.getContext()));

        return view;
    }

    private class createAccountButtonClicked implements View.OnClickListener {
        private Context mContext;

        private createAccountButtonClicked(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onClick(View v) {
            String userName = String.valueOf(createAccountEditText.getText());
            String password = String.valueOf(createPasswordEditText.getText());

            NonQueryResultModel nonQueryResultModel = new NonQueryResultModel();

            WebServiceManager webServiceManager = new WebServiceManager();
            try {
                nonQueryResultModel = webServiceManager.create_account(userName, password);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (nonQueryResultModel.getIsSuccess() == 1) { // Success
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Name", userName);
                editor.apply();

                Navigation.findNavController(v).navigate(R.id.PreferenceFragment);
            } else if (nonQueryResultModel.getIsSuccess() == 0) { // Fail
                createStatusTextView.setText("Create Account Fail: Username Exists");
                createAccountEditText.setTextColor(getResources().getColor(R.color.light_red));
            }
        }
    }
}