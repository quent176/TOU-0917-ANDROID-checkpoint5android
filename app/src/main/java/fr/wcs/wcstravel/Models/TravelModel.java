package fr.wcs.wcstravel.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class TravelModel implements Parcelable {

    private String departurePlace;
    private String arrivalPlace;
    private Date departureDate;
    private Date returnDate;

    public TravelModel(String departurePlace, String arrivalPlace, Date departureDate, Date returnDate) {
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public TravelModel() {
        //for firebase
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    protected TravelModel(Parcel in) {
        departurePlace = in.readString();
        arrivalPlace = in.readString();
        long tmpDepartureDate = in.readLong();
        departureDate = tmpDepartureDate != -1 ? new Date(tmpDepartureDate) : null;
        long tmpReturnDate = in.readLong();
        returnDate = tmpReturnDate != -1 ? new Date(tmpReturnDate) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(departurePlace);
        dest.writeString(arrivalPlace);
        dest.writeLong(departureDate != null ? departureDate.getTime() : -1L);
        dest.writeLong(returnDate != null ? returnDate.getTime() : -1L);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TravelModel> CREATOR = new Parcelable.Creator<TravelModel>() {
        @Override
        public TravelModel createFromParcel(Parcel in) {
            return new TravelModel(in);
        }

        @Override
        public TravelModel[] newArray(int size) {
            return new TravelModel[size];
        }
    };
}