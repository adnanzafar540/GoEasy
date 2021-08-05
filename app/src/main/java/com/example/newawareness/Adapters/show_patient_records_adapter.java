package com.example.newawareness.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newawareness.Dataholder;
import com.example.newawareness.R;

import java.util.List;

public class show_patient_records_adapter extends RecyclerView.Adapter<show_patient_records_adapter.ViewHolder> {
    List<Dataholder> list;
    Context context;
    public show_patient_records_adapter( List<Dataholder> list,Context context) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.show_records_itemview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dataholder dataholder=list.get(position);
        holder.date.setText(String.valueOf(dataholder.getDate()));
        holder.patient_name.setText(dataholder.getPatientName());
        holder.doctor_name.setText(dataholder.getDoctorName());
        holder.desccription.setText(dataholder.getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        EditText date;
        EditText patient_name;
        EditText doctor_name;
        EditText desccription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_record);
            patient_name = itemView.findViewById(R.id.patient_name_record);
            doctor_name = itemView.findViewById(R.id.doctor_name_record);
            desccription=itemView.findViewById(R.id.description_record);

        }
    }
}
