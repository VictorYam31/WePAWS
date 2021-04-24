package com.wepaws.wepaws;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
    TextView resetBackTextView;
    CountDownTimer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reset_password, container, false);
        utility = new Utility();

        resetEmailEditText = (EditText) view.findViewById(R.id.reset_email_edittext);
        resetStatusTextView = (TextView) view.findViewById(R.id.reset_password_status);
        resetBackTextView = (TextView) view.findViewById(R.id.reset_password_back);

        resetBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                Navigation.findNavController(view).popBackStack();
            }
        });

        Button resetPasswordButton = (Button) view.findViewById(R.id.reset_password_button);
        resetPasswordButton.setOnClickListener(new ResetPasswordFragment.resetPasswordButtonClicked(this.getContext()));

        timer = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                resetBackTextView.setText(getResources().getString(R.string.preference_reset_back) + " (" + millisUntilFinished / 1000 + ")");
            }

            public void onFinish() {
                Navigation.findNavController(view).popBackStack();
            }
        };

        return view;
    }

    private class resetPasswordButtonClicked implements View.OnClickListener {
        private Context mContext;

        private resetPasswordButtonClicked(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onClick(View v) {
            InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(mContext.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

            resetStatusTextView.setText("");
            resetBackTextView.setVisibility(View.GONE);

            String emailAddress = String.valueOf(resetEmailEditText.getText());

            if (emailAddress.equals("")) {
                resetStatusTextView.setText(getResources().getString(R.string.preference_login_email_null));
                resetStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
                return;
            }

            NonQueryResultModel nonQueryResultModel = new NonQueryResultModel();
            WebServiceManager webServiceManager = new WebServiceManager();

            try {
                nonQueryResultModel = webServiceManager.reset_password(emailAddress);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (nonQueryResultModel.getIsSuccess() == 1) {
                // Success
                resetStatusTextView.setText(getResources().getString(R.string.preference_reset_success));
                resetStatusTextView.setTextColor(getResources().getColor(R.color.grey));
                resetBackTextView.setVisibility(View.VISIBLE);
                timer.start();
            } else if (nonQueryResultModel.getIsSuccess() == 0) { // Fail
                int info = nonQueryResultModel.getInfo();
                switch (info) {
                    case 1:
                        resetStatusTextView.setText(getResources().getString(R.string.preference_reset_fail_r0));
                        break;
                }
                resetStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
            }
        }
    }
}

