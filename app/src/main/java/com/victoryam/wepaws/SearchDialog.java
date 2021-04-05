package com.victoryam.wepaws;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchDialog extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        NavController navController = NavHostFragment.findNavController(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.information_dialog, null);
        TextView infoTitle = (TextView) view.findViewById(R.id.information_dialog_textview);
        ListView infoList = (ListView) view.findViewById(R.id.information_dialog_listview);
        InfoAdapter infoAdapter = new InfoAdapter(this.getContext(), getResources().getStringArray(R.array.information_dialog_reference_websites_array), getResources().getStringArray(R.array.information_dialog_reference_websites_url));
        infoList.setAdapter(infoAdapter);

        builder.setView(view);
        builder.setPositiveButton(getResources().getString(R.string.search_dialog_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }

    public class InfoAdapter extends BaseAdapter {

        private Context mContext;
        private String[] info;
        private String[] urls;

        public InfoAdapter(Context c, String[] info, String[] urls) {
            this.mContext = c;
            this.info = info;
            this.urls = urls;
        }

        @Override
        public int getCount() {
            return this.info.length;
        }

        @Override
        public Object getItem(int i) {
            return this.info[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.search_component_1, null);

            TextView infoText = (TextView) view.findViewById(R.id.search_component_1_name);
            infoText.setTextSize(20);
            Switch s = (Switch) view.findViewById(R.id.search_component_1_switch);
            infoText.setText((String) this.getItem(i));
            infoText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v("clicked info", (String) getItem(i));
                    Uri uri = Uri.parse(urls[i]);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            s.setVisibility(View.GONE);

            return view;
        }
    }

}
