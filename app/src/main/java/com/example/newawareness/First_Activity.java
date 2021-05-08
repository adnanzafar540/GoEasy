package com.example.newawareness;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newawareness.Utilities.Utilities;

import static com.example.newawareness.Utilities.Utilities.getHeadphoneItemFromIndexNumber;
import static com.example.newawareness.Utilities.Utilities.getHeadphoneList;

public class Activity_Fence_Selection extends AppCompatActivity {


    EditText Situation_Name;
    TextView tv_Headphone, tv_Weather, tv_PhysicalActivity, tv_Time, tv_location;
    Button btn_Date,btn_Time;
    Object_Situation object_situation=new Object_Situation();
    public void getWidgets(){
        tv_Headphone=(TextView)findViewById(R.id.tv_headphone);
        tv_Weather=(TextView)findViewById(R.id.tv_WeatherState);
        tv_PhysicalActivity=(TextView)findViewById(R.id.tv_PhysicalActivityState);
        tv_location=(TextView)findViewById(R.id.tv_LocationState);
        tv_Time=(TextView)findViewById(R.id.tv_timestate);
        btn_Date=(Button)findViewById(R.id.btn_date);
        btn_Time=(Button)findViewById(R.id.btn_time);
    }
    private void Click_Listners(){
        tv_Headphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHeadphoneList();
                AlertDialog.Builder Builder;
                Builder = new AlertDialog.Builder(Activity_Fence_Selection.this);
                Builder.setTitle("Select Headphone State");
                Builder.setItems(getHeadphoneList(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                        tv_Headphone.setText(getHeadphoneItemFromIndexNumber(index));
                        object_situation.setHeadphone(index);


                    }

                });
                AlertDialog alert = Builder.create();
                alert.show();
            }
        });
            }
        }

