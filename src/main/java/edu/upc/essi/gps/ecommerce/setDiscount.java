package edu.upc.essi.gps.ecommerce;

/**
 * Created by GerardDuch on 14/05/15.
 */
public class setDiscount {
    private String setObj;
    private float discount;

    public setDiscount() {}

    public setDiscount(String setObj, float discount) {
        this.setObj = setObj;
        this.discount = discount;
    }

    public float getDiscount() {

        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getSetObj() {
        return setObj;
    }

    public void setSetObj(String setObj) {
        this.setObj = setObj;
    }
}
