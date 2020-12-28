package com.rafslab.json.generator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rafslab.json.generator.model.DataJSON;

import java.util.List;
import java.util.Objects;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private Context mContext;
    private List<DataJSON> dataJSONList;
    private LayoutInflater inflater;
    private ListAdapterListener listener;

    public ListAdapter(Context mContext, List<DataJSON> dataJSONList) {
        this.mContext = mContext;
        this.dataJSONList = dataJSONList;
        inflater = LayoutInflater.from(mContext);
    }

    public ListAdapter(Context mContext, List<DataJSON> dataJSONList, ListAdapterListener listener) {
        this.mContext = mContext;
        this.dataJSONList = dataJSONList;
        this.listener = listener;
        inflater = LayoutInflater.from(mContext);
    }
    public interface ListAdapterListener {
        void setUpdateDataJSON(int position);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListViewHolder(inflater.inflate(R.layout.item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        DataJSON dataJSON = dataJSONList.get(position);
        holder.fieldNameLayout.setHint(dataJSON.getFieldName());
        holder.fieldValueLayout.setHint(dataJSON.getFieldValue());
        String fieldName = Objects.requireNonNull(holder.fieldName.getText()).toString();
        String fieldValue = Objects.requireNonNull(holder.fieldValue.getText()).toString();
    }

    @Override
    public int getItemCount() {
        return dataJSONList.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        private TextInputLayout fieldNameLayout, fieldValueLayout;
        private TextInputEditText fieldName, fieldValue;
        private ImageButton addMoreField;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            fieldNameLayout = itemView.findViewById(R.id.input_field_name);
            fieldName = itemView.findViewById(R.id.field_name);
            fieldValue = itemView.findViewById(R.id.field_value);
            fieldValueLayout = itemView.findViewById(R.id.input_field_value);
        }
    }
}
