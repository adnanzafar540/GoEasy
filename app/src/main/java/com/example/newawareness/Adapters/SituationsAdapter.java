package com.example.newawareness.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newawareness.Objects.ObjectSituation;
import com.example.newawareness.OnItemClick;
import com.example.newawareness.R;
import com.example.newawareness.Utilities.Utilities;

import java.util.List;

public class SituationsAdapter extends RecyclerView.Adapter<SituationsAdapter.ViewHolder> {
    List<ObjectSituation> list=null;
    private OnItemClick mCallback;
    public SituationsAdapter(Context context, List<ObjectSituation> list, OnItemClick listener){
        this.list = list ;
        this.mCallback=listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.saved_situations_itemview, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ObjectSituation object_situation = list.get(position);
        if(object_situation.getHeadphone() == -1) {
            holder.Headphone.setVisibility(View.GONE);
            holder.Headphone.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }else{
      holder.Headphone.setText((Utilities.getHeadphoneItemFromIndexNumber(object_situation.getHeadphone())));}
      if(object_situation.getWeather() == -1) {
          holder.Weather.setVisibility(View.GONE);
          holder.Weather.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
      }else{
      holder.Weather.setText(String.valueOf(Utilities.getWeatherItemFromIndexNumber((object_situation.getWeather()))));}
        if(object_situation.getActivity() == -1) {
            holder.Physical.setVisibility(View.GONE);
            holder.Physical.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }else{
      holder.Physical.setText(String.valueOf(Utilities.getPhysicalActivityItemFromIndexNumber(object_situation.getActivity())));}
      holder.Action.setText(String.valueOf(Utilities.getActionItemFromIndexNumber(object_situation.getAction())));
      holder.SituationName.setText(object_situation.getSituationname());
      holder.aSwitch.setChecked(object_situation.getSwitchActive());
      holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              object_situation.setSwitchActive(mCallback.onClickswitchCheck(isChecked));
              mCallback.forcheckIDandSwitch(position+1,object_situation.getSwitchActive());
          }
      });
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              mCallback.onClickgetid(position);
           }
       });
    }
    @Override
    public int getItemCount() {
     int size=list.size();
     return size;
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView Headphone;
        TextView Weather;
        TextView Physical;
        TextView Action;
        TextView SituationName;
        Switch aSwitch;
        ViewHolder(View itemView) {
            super(itemView);
            Headphone = itemView.findViewById(R.id.headphon_state);
            Weather = itemView.findViewById(R.id.Weather_state);
            Physical = itemView.findViewById(R.id.PhysicalActivity_state);
            Action=itemView.findViewById(R.id.tv_action);
            SituationName=itemView.findViewById(R.id.tv_Situation_name);
            aSwitch=itemView.findViewById(R.id.IsSwitchActive);
        }}}