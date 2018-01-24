package fr.wcs.wcstravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFlightActivity extends AppCompatActivity {

    private EditText mEditDepartureDate, mEditReturnDate;
    private AutoCompleteTextView mEditDeparturePlace, mEditArrivalPlace;
    private DatabaseReference mAirportDB;

    private ArrayAdapter<String> airportAdapter = null;
    private AutoCompleteTextView autoCompleteSearchUsers;
    private ArrayList<String> listAirport = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mAirportDB = database.getReference("checkpoint5/airports");

        mEditDeparturePlace = findViewById(R.id.edit_departure_place);
        mEditArrivalPlace = findViewById(R.id.edit_arrival_place);
        mEditDepartureDate = findViewById(R.id.edit_departure_date);
        mEditReturnDate = findViewById(R.id.edit_return_date);
        Button buttonSearch = (Button) findViewById(R.id.button_search_trip);

        //Auto Suggestion Friends from Database
        mAirportDB.orderByChild("value").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listAirport.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    AirportModel airportModel = data.getValue(AirportModel.class);
                    listAirport.add(airportModel.getLabel());
                }
                airportAdapter = new ArrayAdapter<String>(SearchFlightActivity.this, R.layout.search_box, R.id.tvHintCompletion, listAirport);
                mEditDeparturePlace.setAdapter(airportAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Ajout du datepicker pour la date
        final MyEditTextDatePicker datePicker = new MyEditTextDatePicker(this, R.id.edit_departure_date);
        final MyEditTextDatePicker datePicker2 = new MyEditTextDatePicker(this, R.id.edit_return_date);

        mEditDepartureDate.setFocusable(false);
        mEditReturnDate.setFocusable(false);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditDeparturePlace.getText().toString().trim().isEmpty() || mEditArrivalPlace.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SearchFlightActivity.this, "Veuillez remplir le lieu de départ et d'arrivée", Toast.LENGTH_SHORT).show();
                } else {
                    TravelModel travelModel = new TravelModel(mEditDeparturePlace.getText().toString(), mEditArrivalPlace.getText().toString(), datePicker.getTheDate(), datePicker2.getTheDate());
                    Intent intent = new Intent(SearchFlightActivity.this, ResultFlightActivity.class);
                    intent.putExtra("TheTravel", travelModel);
                    startActivity(intent);
                }
            }
        });

    }
}
