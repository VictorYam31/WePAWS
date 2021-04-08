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

import com.victoryam.wepaws.Utils.Utility;
import com.victoryam.wepaws.WebService.Model.MasterModel;
import com.victoryam.wepaws.WebService.Model.NonQueryResultModel;
import com.victoryam.wepaws.WebService.WebServiceManager;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CreateAccountFragment extends Fragment {
    Utility utility;
    EditText createAccountEditText;
    EditText createEmailEditText;
    EditText createPasswordEditText;
    TextView createStatusTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_account, container, false);
        utility = new Utility();

        createAccountEditText = (EditText) view.findViewById(R.id.create_account_username_edittext);
        createEmailEditText = (EditText) view.findViewById(R.id.create_account_email_edittext);
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
            String emailAddress = String.valueOf(createEmailEditText.getText());
            String password = String.valueOf(createPasswordEditText.getText());

            if (userName.equals("")) {
                createStatusTextView.setText(getResources().getString(R.string.preference_login_password_null));
                createStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
                return;
            } else if (emailAddress.equals("")) {
                createStatusTextView.setText(getResources().getString(R.string.preference_login_email_null));
                createStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
                return;
            } else if (password.equals("")) {
                createStatusTextView.setText(getResources().getString(R.string.preference_login_password_null));
                createStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
                return;
            }

            NonQueryResultModel nonQueryResultModel = new NonQueryResultModel();
            WebServiceManager webServiceManager = new WebServiceManager();

            try {
                //nonQueryResultModel = webServiceManager.create_account(userName, password);
                nonQueryResultModel = webServiceManager.create_account(userName, emailAddress, password);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (nonQueryResultModel.getIsSuccess() == 1) { // Success
                utility.saveUsernameToSharePreference(mContext, userName);
                Navigation.findNavController(v).navigate(R.id.PreferenceFragment);
            } else if (nonQueryResultModel.getIsSuccess() == 0) { // Fail
                int info = nonQueryResultModel.getInfo();
                switch (info) {
                    case 1:
                        createStatusTextView.setText(getResources().getString(R.string.preference_create_profile_fail_r0));
                        break;
                    case 2:
                        createStatusTextView.setText(getResources().getString(R.string.preference_create_profile_fail_r1));
                        break;
                }

                //createStatusTextView.setText(getResources().getString(R.string.preference_create_profile_fail_r0));
                createStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
                createAccountEditText.setTextColor(getResources().getColor(R.color.light_red));
            }
        }
    }
}