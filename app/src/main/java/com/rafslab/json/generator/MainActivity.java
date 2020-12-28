package com.rafslab.json.generator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TabHost;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;
import com.rafslab.json.generator.model.DataJSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ListAdapter.ListAdapterListener {
    private RecyclerView recyclerView;
    private List<DataJSON> dataJSONList = new ArrayList<>();
    private ListAdapter adapter;
    private Toolbar toolbar;
    private Menu menu;
    private SharedPreferences.Editor sortPrefsEdit;
    private SharedPreferences myPrefs;
    public static final String SHARED_KEY = "sortPrefs";
    public static final String ADD_KEY = "addKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.create_json_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myPrefs = getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        adapter = new ListAdapter(this, dataJSONList, this);
        DataJSON dataJSON = new DataJSON();
        dataJSON.setFieldName("Field Name");
        dataJSON.setFieldValue("Field Value");
        dataJSONList.add(dataJSON);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setUpdateDataJSON(int position) {
        DataJSON dataJSON = new DataJSON();
        dataJSON.setFieldName("Field Name");
        dataJSON.setFieldValue("Field Value");
        dataJSONList.add(dataJSON);
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(dataJSONList.size() - 1);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            showDialogSettings();
        }
        if (item.getItemId() == R.id.add) {
            DataJSON dataJSON = new DataJSON();
            dataJSON.setFieldName("Field Name");
            dataJSON.setFieldValue("Field Value");
            dataJSONList.add(dataJSON);
            adapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(dataJSONList.size() - 1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        this.menu = menu;
        return true;
    }
    private void showDialogSettings(){
        sortPrefsEdit = myPrefs.edit();
        View customLayout = LayoutInflater.from(this).inflate(R.layout.file_selection, null);
        final MaterialRadioButton jsonObject = customLayout.findViewById(R.id.object);
        final MaterialRadioButton jsonArray = customLayout.findViewById(R.id.array);
        jsonObject.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (jsonArray.isChecked()) {
                    jsonArray.setChecked(false);
                }
                sortPrefsEdit.putString(ADD_KEY, "Object");
            }
        });
        jsonArray.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (jsonObject.isChecked()) {
                    jsonObject.setChecked(false);
                }
                sortPrefsEdit.putString(ADD_KEY, "Array");
            }
        });
        jsonObject.setChecked(myPrefs.getString(ADD_KEY, "").equals("Object"));
        jsonArray.setChecked(myPrefs.getString(ADD_KEY, "").equals("Array"));
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog).setView(customLayout).setPositiveButton("Save", (dialog, which) -> sortPrefsEdit.apply()).setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show().getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
    }
}