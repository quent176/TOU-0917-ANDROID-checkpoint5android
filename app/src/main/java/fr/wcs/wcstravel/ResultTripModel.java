package fr.wcs.wcstravel;

/**
 * Created by apprenti on 24/01/18.
 */

public class ResultTripModel {

    private String company;
    private String price;

    public ResultTripModel(String company, String price) {
        this.company = company;
        this.price = price;
    }

    public ResultTripModel(){

    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
