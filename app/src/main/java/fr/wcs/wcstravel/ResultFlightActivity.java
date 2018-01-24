package fr.wcs.wcstravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.wcs.wcstravel.Models.ResultTripModel;
import fr.wcs.wcstravel.Models.SearchTravelModel;
import fr.wcs.wcstravel.Models.TravelModel;

public class ResultFlightActivity extends AppCompatActivity {

    TravelModel mTravelModel;
    TextView mTextResultDeparturePlace, mTextResultArrivalPlace, mTextResultDepartureDate, mTextResultReturnDate;
    String mResultDeparturePlace, mResultArrivalPlace, mResultDepartureDate, mResultReturnDate;
    List<ResultTripModel> mResultTripModelList = new ArrayList<>();
    ResultTravelAdapter mResultTravelAdapter;

    DatabaseReference mTravelDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_flight);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mTravelDB = database.getReference("checkpoint5/travels");

        mTextResultDeparturePlace = findViewById(R.id.result_departure_place);
        mTextResultArrivalPlace = findViewById(R.id.result_arrival_place);
        mTextResultDepartureDate = findViewById(R.id.result_departure_date);
        mTextResultReturnDate = findViewById(R.id.result_return_date);

        //récupération de l'objet via Intent
        mTravelModel = getIntent().getExtras().getParcelable("TheTravel");

        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mResultDeparturePlace = mTravelModel.getDeparturePlace();
        mResultArrivalPlace = mTravelModel.getArrivalPlace();
        mResultDepartureDate = sdf.format(mTravelModel.getDepartureDate().getTime());
        mResultReturnDate = sdf.format(mTravelModel.getReturnDate().getTime());

        mTextResultDeparturePlace.setText(mResultDeparturePlace);
        mTextResultArrivalPlace.setText(mResultArrivalPlace);
        mTextResultDepartureDate.setText(mResultDepartureDate);
        mTextResultReturnDate.setText(mResultReturnDate);

        RecyclerView recyclerView = findViewById(R.id.recycler_list);
        mResultTravelAdapter = new ResultTravelAdapter(mResultTripModelList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mResultTravelAdapter);

        FillRecycler();
    }

    public void FillRecycler() {
        //remplir resulttripmodellist
        mTravelDB.orderByChild("departure_date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mResultTripModelList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    SearchTravelModel searchTravelModel = data.getValue(SearchTravelModel.class);
                    if(searchTravelModel.getDeparture_date().equals(mResultDepartureDate)
                            && searchTravelModel.getReturn_date().equals(mResultReturnDate)
                            && searchTravelModel.getTravel().contains("NYC")
                            && searchTravelModel.getTravel().contains("LAX")){
                        mResultTripModelList.add(new ResultTripModel(searchTravelModel.getAirline(),searchTravelModel.getPrice()));
                        mResultTravelAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
