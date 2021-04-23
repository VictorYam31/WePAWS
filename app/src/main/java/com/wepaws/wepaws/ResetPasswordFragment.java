package com.wepaws.wepaws;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wepaws.wepaws.Utils.Utility;
import com.wepaws.wepaws.WebService.Model.NonQueryResultModel;
import com.wepaws.wepaws.WebService.WebServiceManager;

import java.util.concurrent.ExecutionException;

public class ResetPasswordFragment extends Fragment {
    Utility utility;
    EditText resetEmailEditText;
    TextView resetStatusTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reset_password, container, false);
        utility = new Utility();

        resetEmailEditText = (EditText) view.findViewById(R.id.reset_email_edittext);
        resetStatusTextView = (TextView) view.findViewById(R.id.reset_password_status);

        Button resetPasswordButton = (Button) view.findViewById(R.id.reset_password_button);
        resetPasswordButton.setOnClickListener(new ResetPasswordFragment.resetPasswordButtonClicked(this.getContext()));

        return view;
    }

    private class resetPasswordButtonClicked implements View.OnClickListener {
        private Context mContext;

        private resetPasswordButtonClicked(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onClick(View v) {
            resetStatusTextView.setText("");
            String emailAddress = String.valueOf(resetEmailEditText.getText());

            if (emailAddress.equals("")) {
                resetStatusTextView.setText(getResources().getString(R.string.preference_login_email_null));
                resetStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
                return;
            }

            NonQueryResultModel nonQueryResultModel = new NonQueryResultModel();
            WebServiceManager webServiceManager = new WebServiceManager();

       /*     try {
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

                createStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
                createAccountEditText.setTextColor(getResources().getColor(R.color.light_red));
            }*/
        }
    }
}