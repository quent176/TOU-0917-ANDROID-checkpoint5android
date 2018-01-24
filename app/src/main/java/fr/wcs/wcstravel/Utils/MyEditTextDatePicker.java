package fr.wcs.wcstravel.Utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MyEditTextDatePicker  implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private EditText _editText;
    private int _day;
    private int _month;
    private int _birthYear;
    private Context _context;
    private Calendar myCalendar = Calendar.getInstance(TimeZone.getDefault());

    public MyEditTextDatePicker(Context context, int editTextViewID)
    {
        Activity act = (Activity)context;
        this._editText = (EditText)act.findViewById(editTextViewID);
        this._editText.setOnClickListener(this);
        this._context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateDisplay();
    }
    @Override
    public void onClick(View v) {

        DatePickerDialog dialog = new DatePickerDialog(_context, this,
                myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }

    // updates the date in the birth date EditText
    private void updateDisplay() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        _editText.setText(sdf.format(myCalendar.getTime()));
    }

    public Date getTheDate(){
        return myCalendar.getTime();
    }
}