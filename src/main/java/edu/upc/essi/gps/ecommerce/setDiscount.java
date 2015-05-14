package edu.upc.essi.gps.ecommerce;

/**
 * Created by GerardDuch on 14/05/15.
 */
public class setDiscount {
    private String setObj;
    private float discount;
    private String shop;

    public setDiscount() {}

    public setDiscount(String setObj, float discount, String shop) {
        this.setObj = setObj;
        this.discount = discount;
        this.shop = shop;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
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
