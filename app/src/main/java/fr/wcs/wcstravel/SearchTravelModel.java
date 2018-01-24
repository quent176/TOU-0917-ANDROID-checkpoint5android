package fr.wcs.wcstravel;

/**
 * Created by apprenti on 24/01/18.
 */

public class SearchTravelModel {

    private String airline;
    private String departure_date;
    private String price;
    private String return_date;
    private String travel;

    public SearchTravelModel(String airline, String departure_date, String price, String return_date, String travel) {
        this.airline = airline;
        this.departure_date = departure_date;
        this.price = price;
        this.return_date = return_date;
        this.travel = travel;
    }

    public SearchTravelModel(){
        //firebase
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }
}
