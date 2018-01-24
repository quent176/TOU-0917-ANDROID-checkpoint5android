package fr.wcs.wcstravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SearchFlightActivity extends AppCompatActivity {

    EditText mDepartureDate, mReturnDate, mDeparturePlace, mArrivalPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);

        mDeparturePlace = findViewById(R.id.edit_departure_place);
        mArrivalPlace = findViewById(R.id.edit_arrival_place);

    }
}
