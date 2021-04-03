package com.victoryam.wepaws;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.victoryam.wepaws.WebService.Model.NonQueryResultModel;
import com.victoryam.wepaws.WebService.WebServiceManager;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class PreferenceFragment extends Fragment {
    Spinner languageSpinner;
    EditText userNameEditText;
    EditText passwordEditText;

    Button loginButton;

    TextView profileUserNameTextView;
    TextView loginStatusTextView;
    TextView profileUserTextView;
    TextView profilePasswordTextView;
    TextView createAccount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preference, container, false);

        userNameEditText = (EditText) view.findViewById(R.id.profile_username_edittext);
        passwordEditText = (EditText) view.findViewById(R.id.profile_password_edittext);

        profileUserNameTextView = (TextView) view.findViewById(R.id.profile_username);
        loginStatusTextView = (TextView) view.findViewById(R.id.profile_status);
        profileUserTextView = (TextView) view.findViewById(R.id.profile_username_text);
        profilePasswordTextView = (TextView) view.findViewById(R.id.profile_password);

        loginButton = (Button) view.findViewById(R.id.profile_login_button);
        loginButton.setOnClickListener(new loginButtonClicked(this.getContext()));

        createAccount = (TextView) view.findViewById(R.id.profile_create_account);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_PreferenceFragment_to_CreateAccountFragment);
            }
        });

        languageSpinner = view.findViewById(R.id.preference_language_spinner);
        initSpinnerFooter();

        loadPreference();
        return view;
    }

    private void loadPreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        preferences.edit().remove("Name");
//        String name = preferences.getString("Name", "");
//        if (!name.equals("")) { // Success
//            profileUserTextView.setVisibility(View.GONE);
//            profilePasswordTextView.setVisibility(View.GONE);
//            userNameEditText.setVisibility(View.GONE);
//            passwordEditText.setVisibility(View.GONE);
//            loginButton.setVisibility(View.GONE);
//            createAccount.setVisibility(View.GONE);
//
//            profileUserNameTextView.setText(name);
//        }
    }

    private class loginButtonClicked implements View.OnClickListener {
        Context mContext;

        public loginButtonClicked(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onClick(View v) {
            String userName = String.valueOf(userNameEditText.getText());
            String password = String.valueOf(passwordEditText.getText());
            NonQueryResultModel nonQueryResultModel = new NonQueryResultModel();

            WebServiceManager webServiceManager = new WebServiceManager();
            try {
                nonQueryResultModel = webServiceManager.verify_account(userName, password);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (nonQueryResultModel.getIsSuccess() == 1) { // Success
                loginStatusTextView.setText("Login Account Success.");
                loginStatusTextView.setVisibility(View.VISIBLE);
                loginStatusTextView.setTextColor(getResources().getColor(R.color.light_green));
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Name", userName);
                editor.apply();

                profileUserTextView.setVisibility(View.GONE);
                profilePasswordTextView.setVisibility(View.GONE);
                userNameEditText.setVisibility(View.GONE);
                passwordEditText.setVisibility(View.GONE);
                loginButton.setVisibility(View.GONE);
                createAccount.setVisibility(View.GONE);

                profileUserNameTextView.setText(userName);

            } else if (nonQueryResultModel.getIsSuccess() == 0) {
                loginStatusTextView.setText("Login Account Fail.");
                loginStatusTextView.setVisibility(View.VISIBLE);
                loginStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
            }
        }
    }

    private void initSpinnerFooter() {
        String[] items = new String[]{
                getResources().getString(R.string.preference_language_en), getResources().getString(R.string.preference_language_zh)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.preference_spinner_item, items);
        languageSpinner.setAdapter(adapter);
        languageSpinner.setOnItemSelectedListener(new languageSpinnerOnItemSelected());
    }

    private class languageSpinnerOnItemSelected implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.v("item", (String) parent.getItemAtPosition(position));
//                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
        }

        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    private void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(getActivity(), MainActivity.class);
        startActivity(refresh);
    }

}
