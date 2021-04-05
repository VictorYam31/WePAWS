package com.victoryam.wepaws;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.victoryam.wepaws.Utils.Utility;
import com.victoryam.wepaws.WebService.Model.NonQueryResultModel;
import com.victoryam.wepaws.WebService.WebServiceManager;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class PreferenceFragment extends Fragment {
    Utility utility;

    TextView profileUserNameTextView;
    TextView loginStatusTextView;
    TextView profileUserTextView;
    TextView profilePasswordTextView;
    TextView createAccount;

    EditText userNameEditText;
    EditText passwordEditText;

    Button loginButton;
    Button logoutButton;

    Spinner languageSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preference, container, false);
        utility = new Utility();

        profileUserNameTextView = (TextView) view.findViewById(R.id.profile_username);
        loginStatusTextView = (TextView) view.findViewById(R.id.profile_status);
        profileUserTextView = (TextView) view.findViewById(R.id.profile_username_text);
        profilePasswordTextView = (TextView) view.findViewById(R.id.profile_password);
        createAccount = (TextView) view.findViewById(R.id.profile_create_account);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_PreferenceFragment_to_CreateAccountFragment);
            }
        });

        userNameEditText = (EditText) view.findViewById(R.id.profile_username_edittext);
        passwordEditText = (EditText) view.findViewById(R.id.profile_password_edittext);

        loginButton = (Button) view.findViewById(R.id.profile_login_button);
        loginButton.setOnClickListener(new loginButtonClicked(this.getContext()));

        logoutButton = (Button) view.findViewById(R.id.profile_logout_button);
        logoutButton.setOnClickListener(new logoutButtonClicked(this.getContext()));

        languageSpinner = view.findViewById(R.id.preference_language_spinner);
        initLanguageFooter();

        loadPreference();
        return view;
    }

    private void loadPreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String name = preferences.getString("Name", "");
        if (!name.equals("")) { // Success
            setLoginInputInvisible();
            profileUserNameTextView.setText(name);
        }
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

            if (userName.equals("")) {
                loginStatusTextView.setText("Username cannot be null");
                loginStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
                return;
            } else if (password.equals("")) {
                loginStatusTextView.setText("Password cannot be null");
                loginStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
                return;
            }

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

                setLoginInputInvisible();
                profileUserNameTextView.setText(userName);

            } else if (nonQueryResultModel.getIsSuccess() == 0) { //Fail
                int info = nonQueryResultModel.getInfo();
                switch (info) {
                    case 1:
                        loginStatusTextView.setText("Login Account Fail: Wrong Password");
                        break;
                    case 2:
                        loginStatusTextView.setText("Login Account Fail: Username dose not exist");
                        break;
                    case 3:
                        loginStatusTextView.setText("Login Account Fail: Error Unknown");
                        break;
                }

                loginStatusTextView.setVisibility(View.VISIBLE);
                loginStatusTextView.setTextColor(getResources().getColor(R.color.light_red));
            }
        }
    }

    private class logoutButtonClicked implements View.OnClickListener {
        Context mContext;

        public logoutButtonClicked(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onClick(View v) {
            utility.deleteUsernameFromSharePreference(mContext);

            setLoginInputVisible();
            profileUserNameTextView.setText("Guest");
        }
    }

    private void setLoginInputVisible() {
        profileUserTextView.setVisibility(View.VISIBLE);
        profilePasswordTextView.setVisibility(View.VISIBLE);
        userNameEditText.setVisibility(View.VISIBLE);
        passwordEditText.setVisibility(View.VISIBLE);
        createAccount.setVisibility(View.VISIBLE);

        loginButton.setVisibility(View.VISIBLE);
        logoutButton.setVisibility(View.INVISIBLE);
    }

    private void setLoginInputInvisible() {
        profileUserTextView.setVisibility(View.GONE);
        profilePasswordTextView.setVisibility(View.GONE);
        userNameEditText.setVisibility(View.GONE);
        passwordEditText.setVisibility(View.GONE);
        createAccount.setVisibility(View.GONE);

        loginButton.setVisibility(View.GONE);
        logoutButton.setVisibility(View.VISIBLE);
    }

    private void initLanguageFooter() {
        String[] items = new String[]{
                getResources().getString(R.string.preference_language_en), getResources().getString(R.string.preference_language_zh)
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.preference_spinner_item, items);
        languageSpinner.setAdapter(adapter);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        languageSpinner.setSelection(pref.getInt("lang", 0), false);
        languageSpinner.setOnItemSelectedListener(new languageSpinnerOnItemSelected());
    }

    private class languageSpinnerOnItemSelected implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.v("item", (String) parent.getItemAtPosition(position));
//                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
            String languageCode;
            if (position == 0) {
                languageCode = "en";
            } else {
                languageCode = "zh";
            }
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
            pref.edit().putInt("lang", position).apply();
            setLocale(getActivity(), languageCode);
            getActivity().finish();
            getActivity().startActivity(getActivity().getIntent());
        }

        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    public static void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
