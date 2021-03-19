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
import com.victoryam.wepaws.WebService.Model.WildSearchModel;
import com.victoryam.wepaws.WebService.WebServiceManager;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class WildSearchActivity extends AppCompatActivity {

    private ConnectivityManager mConnMgr;

    private RecyclerView mRecyclerView;
    //private MyAdapterTest myAdapter;

    private EditText mInput;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wild_search);

        mConnMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        mInput = (EditText) findViewById(R.id.editTextInput);
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

    public void wild_search(View v) throws ExecutionException, InterruptedException {
        String input;
        List<WildSearchModel> wildSearchList;

        input = mInput.getText().toString();

        if (is_network_available()) {
           /* ExecutorService executor = Executors.newCachedThreadPool();
            WildSearchTask task = new WildSearchTask(input);
            Future<List<WildSearchModel>> future = executor.submit(task);
            List<WildSearchModel> wildSearchList = future.get();
            executor.shutdown();

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < wildSearchList.size(); i++) {
                result.append(wildSearchList.get(i).getClinic_name());
                result.append(" Phone: " + wildSearchList.get(i).getPhone() + '\n');*/
            wildSearchList = new WebServiceManager().wild_search(input);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < wildSearchList.size(); i++) {
                result.append("Clinic Name: " + wildSearchList.get(i).getClinic_name() + '\n');
                result.append(" Phone: " + wildSearchList.get(i).getPhone() + '\n');
                result.append(" Review: " + wildSearchList.get(i).getReview() + '\n');
            }
            //mTextView.setText(result.toString());
            mTextView.setText(result);
        }
    }

}