package com.victoryam.wepaws;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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

    private int type;
    private String componentName;
    private int returnPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.type = this.getArguments().getInt("SearchDialogType");
        this.componentName = this.getArguments().getString("SearchDialogComponent");
        this.returnPosition = this.getArguments().getInt("ReturnPosition");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        NavController navController = NavHostFragment.findNavController(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view;

        switch (this.type) {
            case 0:
                String[] items = this.findComponentArray(this.componentName);
                boolean[] selection = new boolean[items.length];
                builder.setMultiChoiceItems(items, selection, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean b) {
                        Log.v("Made selection", items[position] + b);
                        selection[position] = b;
                    }
                });

                LayoutInflater inflater = requireActivity().getLayoutInflater();
                view = inflater.inflate(R.layout.search_dialog_0, null);
                TextView searchDialogTitle = (TextView) view.findViewById(R.id.search_dialog_0_title);
                searchDialogTitle.setText(getResources().getString(R.string.search_component_select) + " " + this.componentName);

                builder.setCustomTitle(view)
                        .setPositiveButton(getResources().getString(R.string.search_dialog_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Log.v("Positive", "clicked");
                                int position = 0;
                                List<String> selectedItems = new ArrayList<>();
                                for (boolean b : selection) {
                                    if (b) {
                                        selectedItems.add(items[position]);
                                    }
                                    position++;
                                }
                                selectedItems.add(0, String.valueOf(returnPosition));
                                navController.getPreviousBackStackEntry().getSavedStateHandle().set("key", selectedItems);
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.search_dialog_cancel), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.v("Negative", "clicked");
                            }
                        });
                break;
            case 1:
                break;
        }

        return builder.create();
    }

    private String[] findComponentArray(String componentName) {
        List<String> allComponentNames = Arrays.asList(getResources().getStringArray(R.array.all_search_components));
        switch (allComponentNames.indexOf(componentName)) {
            case 0:     // Animal
                return getResources().getStringArray(R.array.animal_type_names);
            case 1:     // District
                return getResources().getStringArray(R.array.hong_kong_district_names);
            case 2:     // Specialist
                return getResources().getStringArray(R.array.specialist_names);
            default:
                return null;
        }
    }

}
