package fr.wcs.wcstravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ResultFlightActivity extends AppCompatActivity {

    TravelModel mTravelModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_flight);

        //récupération de l'objet via Intent
        mTravelModel = getIntent().getExtras().getParcelable("TheTravel");

        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

       // _editText.setText(sdf.format(mTravelModel.getDepartureDate().getTime()));
         System.out.println(sdf.format(mTravelModel.getDepartureDate().getTime()));
      //  _editText.setText(sdf.format(mTravelModel.getReturnDate().getTime()));
         System.out.println(sdf.format(mTravelModel.getReturnDate().getTime()));
    }
}
