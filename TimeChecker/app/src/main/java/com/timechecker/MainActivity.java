package com.timechecker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText
            etFromTime,
            etToTime,
            etCheckTime;
    private Button btnCheck;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        etFromTime = findViewById(R.id.etFromTime);
        etToTime = findViewById(R.id.etToTime);
        etCheckTime = findViewById(R.id.etCheckTime);
        btnCheck = findViewById(R.id.btnCheck);
        tvResult = findViewById(R.id.tvResult);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String
                        etFromTimeValue = etFromTime.getText().toString().trim(),
                        etToTimeValue = etToTime.getText().toString().trim(),
                        etCheckTimeValue = etCheckTime.getText().toString().trim();
                if (etFromTimeValue.equalsIgnoreCase("")
                        || etToTimeValue.equalsIgnoreCase("")
                        || etCheckTimeValue.equalsIgnoreCase(""))
                    return;

                check(Integer.parseInt(etFromTimeValue), Integer.parseInt(etToTimeValue), Integer.parseInt(etCheckTimeValue));
            }
        });
    }

    private void check(int fromTime, int toTime, int checkTime) {
        try {
            // E.g. 11:00
            String fromTimeStr = DateUtils.createTimeFormatString_HH_mm(fromTime);
            String toTimeStr = DateUtils.createTimeFormatString_HH_mm(toTime);
            String checkTimeStr = DateUtils.createTimeFormatString_HH_mm(checkTime);

            // E.g. 31/12/2021 11:00
            String fromDate = DateUtils.getCurrentDateTime("dd/MM/yyyy") + " " + fromTimeStr;
            String toDate = DateUtils.getCurrentDateTime("dd/MM/yyyy") + " " + toTimeStr;
            String checkDate = DateUtils.getCurrentDateTime("dd/MM/yyyy") + " " + checkTimeStr;

            // Create calendar objects to compare date time
            Calendar fromCalendar = DateUtils.getCalendarObjectFromStringDate(fromDate, "dd/MM/yyyy HH:mm");
            Calendar toCalendar = DateUtils.getCalendarObjectFromStringDate(toDate, "dd/MM/yyyy HH:mm");
            Calendar checkCalendar = DateUtils.getCalendarObjectFromStringDate(checkDate, "dd/MM/yyyy HH:mm");

            String displayResult = "";
            if (checkCalendar.after(fromCalendar) && checkCalendar.before(toCalendar)) {
                displayResult = checkTimeStr + " is between " + fromTimeStr + " and " + toTimeStr;
            } else {
                displayResult = checkTimeStr + " is not between " + fromTimeStr + " and " + toTimeStr;
            }
            tvResult.setText("Result :\n" + displayResult);

        } catch (Exception e) {
            e.printStackTrace();
            tvResult.setText("Result : Exception");
        }
    }
}
