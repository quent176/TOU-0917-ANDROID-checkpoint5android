package fr.wcs.wcstravel;

import java.util.Date;

public class TravelModel {

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
}
