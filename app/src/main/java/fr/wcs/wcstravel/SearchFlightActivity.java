package fr.wcs.wcstravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFlightActivity extends AppCompatActivity {

    private EditText mEditDepartureDate, mEditReturnDate;
    private AutoCompleteTextView mEditDeparturePlace;
    private Spinner mEditArrivalPlace;
    private DatabaseReference mAirportDB;

    private ArrayAdapter<String> airportAdapterDeparture = null;
    private ArrayList<String> listAirportDeparture = new ArrayList<>();

    private ArrayAdapter<String> airportAdapterArrival = null;
    private ArrayList<String> listAirportArrival = new ArrayList<>();

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

        //Auto Suggestion Airports from Database
        mAirportDB.orderByChild("value").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listAirportDeparture.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    AirportModel airportModel = data.getValue(AirportModel.class);
                    listAirportDeparture.add(airportModel.getLabel());
                    //Spinner getting only NYC
                    if(airportModel.getLabel().contains("NYC")) {
                        listAirportArrival.add(airportModel.getLabel());
                    }
                }
                //add  to autocompletionview and remove NYC from the list
                airportAdapterDeparture = new ArrayAdapter<String>(SearchFlightActivity.this, R.layout.search_box, R.id.tvHintCompletion, listAirportDeparture);
                mEditDeparturePlace.setAdapter(airportAdapterDeparture);
                for (int i = 0; i < listAirportDeparture.size(); i++)
                {
                    if (listAirportDeparture.get(i).contains("NYC")){
                        listAirportDeparture.remove(i);
                    }
                }
                //Spinner getting only NYC - see above with Firebase
                airportAdapterArrival = new ArrayAdapter<String>(SearchFlightActivity.this, R.layout.support_simple_spinner_dropdown_item, listAirportArrival);
                mEditArrivalPlace.setAdapter(airportAdapterArrival);
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
                if (mEditDeparturePlace.getText().toString().trim().isEmpty() || mEditArrivalPlace.getSelectedItem().toString().trim().isEmpty()) {
                    Toast.makeText(SearchFlightActivity.this, "Veuillez remplir le lieu de départ et d'arrivée", Toast.LENGTH_SHORT).show();
                } else {
                    TravelModel travelModel = new TravelModel(mEditDeparturePlace.getText().toString(), mEditArrivalPlace.getSelectedItem().toString(), datePicker.getTheDate(), datePicker2.getTheDate());
                    Intent intent = new Intent(SearchFlightActivity.this, ResultFlightActivity.class);
                    intent.putExtra("TheTravel", travelModel);
                    startActivity(intent);
                }
            }
        });

    }
}
