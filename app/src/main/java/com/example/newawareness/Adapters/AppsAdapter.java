package com.example.newawareness.Adapters;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newawareness.R;

import java.util.List;


public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppsViewHolder> {
    private List<ApplicationInfo> data;
    private OnNoteListner onNoteListner;
    private List<ApplicationInfo> applist;
    private PackageManager packageManager;


    public AppsAdapter(Context context,List<ApplicationInfo> data,OnNoteListner onNoteListner){
        this.onNoteListner=onNoteListner;
        this.applist=data;
        packageManager=context.getPackageManager();

    }

    @NonNull
    @Override
    public AppsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.installed_apps_itemview,parent,false);
        return new AppsViewHolder(view,onNoteListner);
    }

    @Override
    public void onBindViewHolder(@NonNull AppsViewHolder holder, int position) {
        ApplicationInfo data= applist.get(position);

        holder.AppsName.setText(data.loadLabel(packageManager));
        holder.AppsLabel.setImageDrawable(data.loadIcon(packageManager));



    }

    @Override
    public int getItemCount() {
        return applist.size();
    }

    public class AppsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView AppsLabel;
        TextView AppsName;
        OnNoteListner onNoteListner;
        public AppsViewHolder(@NonNull View itemView,OnNoteListner onNoteListner) {
            super(itemView);
            AppsLabel=(ImageView)itemView.findViewById(R.id.Appicon);
            AppsName=(TextView)itemView.findViewById(R.id.Appname);
            this.onNoteListner=onNoteListner;
           itemView.setOnClickListener(this);
    }

        @Override
        public void onClick(View view) {
            onNoteListner.onNoteClick(getAbsoluteAdapterPosition());
        }
    }
    public interface OnNoteListner{
        void onNoteClick(int position);
    }


}

