package fr.wcs.wcstravel.Models;

/**
 * Created by apprenti on 24/01/18.
 */

public class AirportModel {

    private String label;
    private String value;

    public AirportModel(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public AirportModel(){
        //for Firebase
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
