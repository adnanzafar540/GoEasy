package com.example.newawareness;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.newawareness.Database.DatabaseClass;
import com.example.newawareness.Objects.ObjectSituation;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.example.newawareness.Utilities.Utilities.getActionItemFromIndexNumber;
import static com.example.newawareness.Utilities.Utilities.getActionList;
import static com.example.newawareness.Utilities.Utilities.getHeadphoneItemFromIndexNumber;
import static com.example.newawareness.Utilities.Utilities.getHeadphoneList;
import static com.example.newawareness.Utilities.Utilities.getIndexPhysicalActivitydetected;
import static com.example.newawareness.Utilities.Utilities.getPhysicalActivityItemFromIndexNumber;
import static com.example.newawareness.Utilities.Utilities.getPhysicalActivityList;
import static com.example.newawareness.Utilities.Utilities.getWeatherItemFromIndexNumber;
import static com.example.newawareness.Utilities.Utilities.getWeatherList;

public class MainActivity extends AppCompatActivity  {

    String wheather;
    long start = 0L;
    Switch Switch;
    DatabaseClass mdatabaseHelper;
    EditText Situation_Name;
    TextView tv_Headphone, tv_Weather, tv_PhysicalActivity, tv_location;
    Button btn_Date, btn_Time, btn_Action, btn_addSituation, btn_ShowSituations;
    ObjectSituation object_situation;
    TimeDate timeDate;
    boolean is_situationNameExist;
    boolean is_actionSelected;
    boolean is_WeatherSelected;
    boolean is_activitySelected;
    boolean is_locationSelected;
    boolean is_HeadphonESelected;
    boolean is_DateSelected;
    boolean is_TimeSelected;
    private static final String FENCE_RECEIVER_ACTION = BuildConfig.APPLICATION_ID +
            "FENCE_RECEIVER_ACTION";
    private static final String FENCE_KEY_PhysicalActivity = "PhysicalActivity";
    private PendingIntent mPendingIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.virtual_assistance);
        mdatabaseHelper = new DatabaseClass(this);
        object_situation = new ObjectSituation();
        timeDate = new TimeDate();
        getWidgets();
        Click_Listners();
        FirebaseApp.initializeApp(this);

    }
    public void getWidgets() {
        tv_Headphone = (TextView) findViewById(R.id.tv_headphone);
        Situation_Name = (EditText) findViewById(R.id.etv_situation_name);
        tv_Weather = (TextView) findViewById(R.id.tv_WeatherState);
        tv_PhysicalActivity = (TextView) findViewById(R.id.tv_PhysicalActivityState);
        tv_location = (TextView) findViewById(R.id.tv_LocationState);
        btn_Date = (Button) findViewById(R.id.btn_date);
        btn_Time = (Button) findViewById(R.id.btn_time);
        btn_Action = (Button) findViewById(R.id.btn_Acction);
        btn_addSituation = (Button) findViewById(R.id.btn_addSituation);
        btn_ShowSituations = (Button) findViewById(R.id.btn_showSituations);
    }
    public void Click_Listners() {
        Situation_Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String SituationName = Situation_Name.getText().toString();
                object_situation.setSituationname(SituationName);
                is_situationNameExist = true;
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        btn_addSituation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object_situation.setSwitchActive(true);
                ArrayList<AwarenessFence> list = new ArrayList<AwarenessFence>();
                if ((is_situationNameExist && is_actionSelected) && (is_activitySelected || is_DateSelected || is_HeadphonESelected || is_locationSelected || is_TimeSelected || is_WeatherSelected
                )) {
                    if (is_HeadphonESelected) {
                        list.add(FenceCreateUtilitesActivity.createHeadphonrFence(object_situation.getHeadphone() + 1));
                    }
                    if (is_activitySelected) {
                        list.add(FenceCreateUtilitesActivity.createphysicalactivityFence(getIndexPhysicalActivitydetected(object_situation.getActivity())));
                    }
                   /* if (is_locationSelected) {
                        list.add(FenceCreateUtilites.createLocationFence(object_situation.getLongi(), object_situation.getLat(), 500L,start
                                , MainActivity.this));
                    }**/
                    if (is_TimeSelected) {
                        long timeinmilli=object_situation.getTime();
                        list.add(FenceCreateUtilitesActivity.createTimeDateFence(object_situation.getTime(), object_situation.getTime()+60000l, MainActivity.this));
                    }
                    AwarenessFence finalFence = FenceCreateUtilitesActivity.getFinalFence(list);
                    mdatabaseHelper.insertData(object_situation);
                    registerUpdateFinalFence(finalFence, MainActivity.this);
                    Toast.makeText(MainActivity.this, "Situation Saved", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    finishAffinity();
                    startActivity(i);
                } else if (is_situationNameExist && is_actionSelected) {
                    Toast.makeText(MainActivity.this, "Choose Atleast one state ", Toast.LENGTH_LONG).show();
                } else if ((is_actionSelected) && (is_activitySelected || is_DateSelected || is_HeadphonESelected || is_locationSelected || is_TimeSelected || is_WeatherSelected)) {
                    Toast.makeText(MainActivity.this, "Please Enter Situation Name", Toast.LENGTH_LONG).show();
                } else if ((is_situationNameExist) && (is_activitySelected || is_DateSelected || is_HeadphonESelected || is_locationSelected || is_TimeSelected || is_WeatherSelected)) {
                    Toast.makeText(MainActivity.this, "Please Select Action ", Toast.LENGTH_LONG).show();
                } else if (is_situationNameExist) {
                    Toast.makeText(MainActivity.this, "Please Choose Atleast one state and Select Action ", Toast.LENGTH_LONG).show();
                } else if (is_actionSelected) {
                    Toast.makeText(MainActivity.this, "Please Choose Atleast one state and Enter Situation Name", Toast.LENGTH_LONG).show();
                } else if (is_activitySelected || is_DateSelected || is_HeadphonESelected || is_locationSelected || is_TimeSelected || is_WeatherSelected) {
                    Toast.makeText(MainActivity.this, "Please Enter Situation Name and Select Action ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter Situation Name, Atleast choose one state and Select Action  ", Toast.LENGTH_LONG).show();

                }
            }
        });
        btn_ShowSituations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SavedSituationsActivity.class);
                startActivity(i);
            }
        });

        tv_Headphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHeadphoneList();
                AlertDialog.Builder Builder;
                Builder = new AlertDialog.Builder(MainActivity.this);
                Builder.setTitle("Select Headphone State");
                Builder.setItems(getHeadphoneList(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                        tv_Headphone.setText(getHeadphoneItemFromIndexNumber(index));
                        object_situation.setHeadphone(index);
                        is_HeadphonESelected = true;
                        object_situation.isIs_HeadphonESelected(true);
                    }
                });
                AlertDialog alert = Builder.create();
                alert.show();
            }
        });

        tv_Weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    getWeatherList();
                    AlertDialog.Builder Builder;
                    Builder = new AlertDialog.Builder(MainActivity.this);
                    Builder.setTitle("Select Weather State");
                    Builder.setItems(getWeatherList(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            String Weathertxt = getWeatherItemFromIndexNumber(i);
                            object_situation.setWeather_txt(Weathertxt);
                            tv_Weather.setText(getWeatherItemFromIndexNumber(i));
                            object_situation.setWeather(i);
                            is_WeatherSelected = true;
                            object_situation.setIs_WeatherSelected(true);
                        }
                    });
                    AlertDialog alert = Builder.create();
                    alert.show();
            }
        });

        tv_PhysicalActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                permissions();
                AlertDialog.Builder Builder;
                Builder = new AlertDialog.Builder(MainActivity.this);
                Builder.setTitle("Select PhysicalActivity State");
                Builder.setItems(getPhysicalActivityList(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv_PhysicalActivity.setText(getPhysicalActivityItemFromIndexNumber(i));
                        object_situation.setActivity(i);
                        is_activitySelected = true;
                    }
                });
                AlertDialog alert = Builder.create();
                alert.show();
            }
        });

        btn_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_DateSelected) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMint) {
                        String minute = String.format("%02d", selectedMint);
                        String hour = String.format("%02d", selectedHour);
                        String Time = (hour + ":" + minute);
                        timeDate.setTime(Time);
                        String TimeDate = timeDate.getDate() + "/" + timeDate.getTime();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy/HH:mm");
                        long timeInMillisecond = 0L;
                        LocalDateTime dateTime = LocalDateTime.parse(TimeDate, formatter);
                        timeInMillisecond = dateTime.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
                        timeInMillisecond=timeInMillisecond-5*3600000;
                        btn_Time.setText(Time);
                        object_situation.setTime(timeInMillisecond);
                        Toast.makeText(MainActivity.this, "Time has been set", Toast.LENGTH_LONG).show();
                        is_TimeSelected = true;
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, onTimeSetListener, 20, 8, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
                }else{
                    Toast.makeText(MainActivity.this, "Please Set Date First", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                            mMonth = mMonth + 1;
                            String month1;
                            String day1;
                            String year;
                            if (mDay < 10 && mMonth < 10) {
                                day1 = String.valueOf("0" + mDay);
                                month1 = String.valueOf("0" + mMonth);
                                year = String.valueOf(mYear);
                                String day = String.valueOf(mDay);
                                String month = String.valueOf(mMonth);
                                String date = (month1 + "-" + day1 + "-" + year);
                                btn_Date.setText(date);
                                object_situation.setDate(date);
                                timeDate.setDate(date);
                                is_DateSelected = true;
                            } else if (mDay < 10) {
                                day1 = String.valueOf("0" + mDay);
                                year = String.valueOf(mYear);
                                String day = String.valueOf(mDay);
                                String month = String.valueOf(mMonth);
                                String date = (month + "-" + day1 + "-" + year);
                                btn_Date.setText(date);
                                object_situation.setDate(date);
                                timeDate.setDate(date);
                                is_DateSelected = true;
                            } else if (mMonth < 10) {
                                month1 = String.valueOf("0" + mMonth);
                                year = String.valueOf(mYear);
                                String day = String.valueOf(mDay);
                                String month = String.valueOf(month1);
                                String date = (month1 + "-" + day + "-" + year);
                                btn_Date.setText(date);
                                object_situation.setDate(date);
                                timeDate.setDate(date);
                                is_DateSelected = true;
                            } else {
                                month1 = String.valueOf(mYear);
                                year = String.valueOf(mMonth);
                                String day = String.valueOf(mDay);
                                String date = (month1 + "-" + day + "-" + year);
                                btn_Date.setText(date);
                                object_situation.setDate(date);
                                timeDate.setDate(date);
                                is_DateSelected = true;
                            }
                            is_DateSelected = true;
                        }
                    };
                    Calendar calendar = Calendar.getInstance();
                    int mYear = calendar.get(Calendar.YEAR);
                    int mMonth = calendar.get(Calendar.MONTH);
                    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, onDateSetListener, mYear, mMonth, mDay);
                    datePickerDialog.setTitle("Select Date");
                    datePickerDialog.show();
            }
        });
        tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivityForResult(intent, 1); }
        });
        btn_Action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActionList();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Make your selection");
                builder.setItems(getActionList(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        getActionItemFromIndexNumber(i);
                        object_situation.setAction(i);

                        if (getActionItemFromIndexNumber(i) == getActionItemFromIndexNumber(0)) {
                            Intent intent = new Intent(MainActivity.this, InstalledAppsActivity.class);
                            startActivityForResult(intent, 5);
                        }
                        if (getActionItemFromIndexNumber(i) == getActionItemFromIndexNumber(1)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Please Set Your Notification");
                            final EditText input = new EditText(MainActivity.this);
                            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                            builder.setView(input);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String m_Text = input.getText().toString();
                                    object_situation.setNotification(m_Text);
                                }
                            });
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel(); }
                            });
                            builder.show();
                            btn_Action.setText("Notification");
                            is_actionSelected = true;
                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5) {
            String nameOfApp = data.getStringExtra("nameOfApp");
            String nameOfAppPakage = data.getStringExtra("nameOfAppPakage");
            object_situation.setPakagename(nameOfAppPakage);
            btn_Action.setText("Open" + " " + nameOfApp);
            object_situation.setAppname(nameOfApp);
            is_actionSelected = true;
        }
        if (resultCode == 1) {
            String Location = data.getStringExtra("nameOfLocation");
            Double Lat = data.getDoubleExtra("Lat", 0);
            Double Lng = data.getDoubleExtra("Lng", 0);
            object_situation.setLat(Lat);
            object_situation.setLongi(Lng);
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(Lat, Lng, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String cityName = addresses.get(0).getLocality();
            String countryName = addresses.get(0).getCountryName();
            object_situation.setCity_name(cityName);
            object_situation.setCountry_name(countryName);
            //findWeather(cityName,countryName);
            object_situation.setLocationname(Location);
            tv_location.setText(Location);
            is_locationSelected = true;
        }
    }
    public void registerUpdateFinalFence(AwarenessFence FinalFence, Context context) {
        FenceReceiverActivity mFenceReceiver = new FenceReceiverActivity(this);
        mPendingIntent = PendingIntent.getBroadcast(context, 0,
                new Intent(FENCE_RECEIVER_ACTION), 0);
        registerReceiver(mFenceReceiver, new IntentFilter(FENCE_RECEIVER_ACTION));
        Awareness.getFenceClient(context).updateFences(new FenceUpdateRequest.Builder()
                .addFence(String.valueOf(mdatabaseHelper.latestPrimarykey()), FinalFence, mPendingIntent)
                .build())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(FENCE_KEY_PhysicalActivity, "Successfully registered.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(FENCE_KEY_PhysicalActivity, "Could not be registered: " + e.getLocalizedMessage());
                    }
                });
    }
       public void checkExistanceforphysicalAction(){
        if(is_activitySelected){
            object_situation.setActivity(-1);
        }
        else{
            return;
       }} public void checkExistanceforHeadphone(){
        if(is_activitySelected){
            object_situation.setHeadphone(-1);
        }
        else{
            return;
       }}
       public void permissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
                   != PackageManager.PERMISSION_GRANTED) {
           }
       }
}




