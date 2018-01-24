package fr.wcs.wcstravel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SearchFlightActivity extends AppCompatActivity {

    EditText mEditDepartureDate, mEditReturnDate, mEditDeparturePlace, mEditArrivalPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);

        mEditDeparturePlace = findViewById(R.id.edit_departure_place);
        mEditArrivalPlace = findViewById(R.id.edit_arrival_place);
        mEditDepartureDate = findViewById(R.id.edit_departure_date);
        mEditReturnDate = findViewById(R.id.edit_return_date);
        Button buttonSearch = (Button) findViewById(R.id.button_search_trip);

        //Ajout du datepicker pour la date
        final MyEditTextDatePicker datePicker = new MyEditTextDatePicker(this, R.id.edit_departure_date);
        final MyEditTextDatePicker datePicker2 = new MyEditTextDatePicker(this, R.id.edit_return_date);

        mEditDepartureDate.setFocusable(false);
        mEditReturnDate.setFocusable(false);

        TextView testmessage = (TextView) findViewById(R.id.test_message);
        testmessage.setText(datePicker.dateGiver().toString());

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TravelModel travelModel = new TravelModel(mEditDeparturePlace.getText().toString(), mEditArrivalPlace.getText().toString(), datePicker.dateGiver(), datePicker2.dateGiver());
            }
        });

    }
}
