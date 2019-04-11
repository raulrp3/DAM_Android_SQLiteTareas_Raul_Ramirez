package com.example.alumno_fp.app_sqlite;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

public class SaveActivity extends AppCompatActivity {

    public final Calendar calendar = Calendar.getInstance();
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    final int year = calendar.get(Calendar.YEAR);
    private final int CODE_SAVE = 1;

    EditText etTitle,etDescription,etDate;
    ImageButton imgbtnDate;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        initUI();

        imgbtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                String date = etDate.getText().toString().trim();
                if (validate(title) && validate(description) && validate(date)){
                    Intent intent = new Intent();
                    intent.putExtra("TITLE",title);
                    intent.putExtra("DESCRIPTION",description);
                    intent.putExtra("DATE",date);
                    setResult(CODE_SAVE,intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"¡Campos obligatorios!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initUI(){
        etDate = findViewById(R.id.et_date);
        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        imgbtnDate = findViewById(R.id.imgbtn_date);
        buttonSave = findViewById(R.id.button_save);
    }

    private void getDate(){
        DatePickerDialog date = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int currentMonth = month + 1;

                String formatDay = (dayOfMonth < 10)? "0" + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                String formatMonth = (currentMonth < 10)? "0" + String.valueOf(currentMonth) : String.valueOf(currentMonth);

                etDate.setText(formatDay + "/" + formatMonth + "/" + year);
            }
        },year,month,day);

        date.show();
    }

    private boolean validate(String string){
        boolean isValid = true;
        if (string.isEmpty()){
            isValid = false;
        }

        return isValid;
    }
}
