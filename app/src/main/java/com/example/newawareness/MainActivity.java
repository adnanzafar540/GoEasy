package com.example.newawareness;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newawareness.Database.DatabaseActivity;
import com.example.newawareness.Objects.ObjectSituationActivity;

import java.util.Calendar;

import static com.example.newawareness.Utilities.Utilities.getActionItemFromIndexNumber;
import static com.example.newawareness.Utilities.Utilities.getActionList;
import static com.example.newawareness.Utilities.Utilities.getHeadphoneItemFromIndexNumber;
import static com.example.newawareness.Utilities.Utilities.getHeadphoneList;
import static com.example.newawareness.Utilities.Utilities.getPhysicalActivityItemFromIndexNumber;
import static com.example.newawareness.Utilities.Utilities.getPhysicalActivityList;
import static com.example.newawareness.Utilities.Utilities.getWeatherItemFromIndexNumber;
import static com.example.newawareness.Utilities.Utilities.getWeatherList;

public class MainActivity extends AppCompatActivity {
    DatabaseActivity mdatabaseHelper;
    EditText Situation_Name;
    TextView tv_Headphone, tv_Weather, tv_PhysicalActivity,  tv_location;
    Button btn_Date, btn_Time, btn_Action, btn_addSituation, btn_ShowSituations;
    ObjectSituationActivity object_situation;
    boolean is_situationNameExist;
    boolean is_actionSelected;


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mdatabaseHelper = new DatabaseActivity(this);
        object_situation = new ObjectSituationActivity();
        getWidgets();
        Click_Listners();
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
        
        Situation_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SituationName = Situation_Name.getText().toString();
                object_situation.setSituationname(SituationName);
                is_situationNameExist=true;

            }
        });



        btn_addSituation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_situationNameExist && is_actionSelected){
                    mdatabaseHelper.insertData(object_situation);
                    Toast.makeText(MainActivity.this, "Situation Saved", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    finishAffinity();
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Please Enter Situation Name and Select Action ", Toast.LENGTH_LONG).show();

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
                        tv_Weather.setText(getWeatherItemFromIndexNumber(i));
                        object_situation.setWeather(i);
                    }
                });
                AlertDialog alert = Builder.create();
                alert.show();
            }
        });

        tv_PhysicalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder Builder;
                Builder = new AlertDialog.Builder(MainActivity.this);
                Builder.setTitle("Select PhysicalActivity State");
                Builder.setItems(getPhysicalActivityList(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv_PhysicalActivity.setText(getPhysicalActivityItemFromIndexNumber(i));
                        object_situation.setActivity(i);
                    }
                });
                AlertDialog alert = Builder.create();
                alert.show();
            }
        });

        btn_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMint) {
                        String minute = String.format("%02d", selectedMint);
                        String hour = String.format("%02d", selectedHour);
                        String Time = (hour + ":" + minute);
                        btn_Time.setText(Time);
                        Toast.makeText(MainActivity.this, "Date has been set", Toast.LENGTH_LONG).show();
                        object_situation.setMinutes(minute);
                        object_situation.setHours(hour);
                    }
                };

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, onTimeSetListener, 20, 8, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });

        btn_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        String year = String.valueOf(mYear);
                        String month = String.valueOf(mMonth);
                        String day = String.valueOf(mDay);
                        String date = (year + "-" + month + "-" + day);
                        btn_Date.setText(date);
                        object_situation.setDate(date);
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
                startActivityForResult(intent, 1);
            }
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
                            builder.setTitle("Title");
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
                                    dialog.cancel();
                                }
                            });
                            builder.show();
                            btn_Action.setText("Notification");
                            is_actionSelected=true;

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
            String NameOfApp = data.getStringExtra("nameOfAction");
            btn_Action.setText("Open" + " " + NameOfApp);
            object_situation.setAppname(NameOfApp);
            is_actionSelected=true;



        }

        if (resultCode == 1) {
            String Location = data.getStringExtra("nameOfLocation");
            Double Lat = data.getDoubleExtra("Lat", 0);
            Double Lng = data.getDoubleExtra("Lng", 0);
            object_situation.setLat(Lat);
            object_situation.setLongi(Lng);
            object_situation.setLocationname(Location);
            tv_location.setText(Location);
        }
    }
}





