package edu.upc.essi.gps.ecommerce;

/**
 * Created by GerardDuch on 14/05/15.
 */
public class setDiscount {
    private String setObj;
    private float discount;
    private String shop;
    private String tipus;

    public setDiscount() {}

    public setDiscount(String setObj, float discount, String shop, String discountTipo) {
        this.setObj = setObj;
        this.discount = discount;
        this.shop = shop;
        this.tipus = discountTipo;
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

    public String getTipus() {
        return tipus;
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
