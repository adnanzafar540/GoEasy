package com.example.newawareness.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newawareness.Objects.ObjectSituation;
import com.example.newawareness.R;
import com.example.newawareness.Utilities.Utilities;

import java.util.List;

public class SituationsAdapter extends RecyclerView.Adapter<SituationsAdapter.ViewHolder> {

    List<ObjectSituation> list=null;


    public SituationsAdapter(Context context, List<ObjectSituation> list ){
        this.list = list ;



    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.saved_situations_itemview, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ObjectSituation object_situation = list.get(position);
      holder.Headphone.setText((Utilities.getHeadphoneItemFromIndexNumber(object_situation.getHeadphone())));
      holder.Weather.setText(String.valueOf(Utilities.getWeatherItemFromIndexNumber((object_situation.getWeather()))));
      holder.Physical.setText(String.valueOf(Utilities.getPhysicalActivityItemFromIndexNumber(object_situation.getActivity())));
      holder.Action.setText(String.valueOf(Utilities.getActionItemFromIndexNumber(object_situation.getAction())));
      holder.SituationName.setText(object_situation.getSituationname());

    }

    // total number of rows
    @Override
    public int getItemCount() {
     int size=list.size();
     return size;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView Headphone;
        TextView Weather;
        TextView Physical;
        TextView Action;
        TextView SituationName;

        ViewHolder(View itemView) {
            super(itemView);
            Headphone = itemView.findViewById(R.id.headphon_state);
            Weather = itemView.findViewById(R.id.Weather_state);
            Physical = itemView.findViewById(R.id.PhysicalActivity_state);
            Action=itemView.findViewById(R.id.Action_tv);
            SituationName=itemView.findViewById(R.id.Situationname_tv);

        }}}