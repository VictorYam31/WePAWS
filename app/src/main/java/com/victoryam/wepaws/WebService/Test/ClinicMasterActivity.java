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
import com.victoryam.wepaws.WebService.Model.ClinicMasterModel;
import com.victoryam.wepaws.WebService.Model.WildSearchModel;
import com.victoryam.wepaws.WebService.WebServiceManager;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ClinicMasterActivity extends AppCompatActivity {

    private ConnectivityManager mConnMgr;

    private RecyclerView mRecyclerView;
    //private MyAdapterTest myAdapter;

    private EditText mClinicName,mDistrictID,mOvernight;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_master);

        mConnMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        mClinicName = (EditText) findViewById(R.id.clinicNameET);
        mDistrictID = (EditText)findViewById(R.id.districtET);
        mOvernight = (EditText)findViewById(R.id.overnightET) ;
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

    public void search_clinic_master(View v) throws ExecutionException, InterruptedException {
        String clinicName,districtID,overnight;
        List<ClinicMasterModel> clinicMasterList;

        clinicName = mClinicName.getText().toString();
        districtID = mDistrictID.getText().toString();
        overnight = mOvernight.getText().toString();



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


            clinicMasterList = new WebServiceManager().get_clinic_master(clinicName,districtID,overnight);
            System.out.println(clinicMasterList.size());
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < clinicMasterList.size(); i++) {
                result.append("Clinic Name: " + clinicMasterList.get(i).getClinicName() + '\n');
                result.append(" Address: " + clinicMasterList.get(i).getClinicAddress() + '\n');
                result.append(" Phone: " + clinicMasterList.get(i).getPhone() + '\n');
                result.append(" Desc: " + clinicMasterList.get(i).getClinicDesc() + '\n');

            }
            //mTextView.setText(result.toString());
            mTextView.setText(result);
        }
    }
}