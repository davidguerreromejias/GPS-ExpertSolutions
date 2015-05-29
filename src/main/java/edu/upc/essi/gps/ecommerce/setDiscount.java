package edu.upc.essi.gps.ecommerce;

/**
 * Created by GerardDuch on 14/05/15.
 */
public class setDiscount {
    private String setObj;
    private float discount;
    private String shop;
    private String tipus;
    private int compres;
    private int pagues;


    public setDiscount() {}

    //Creadora per setDiscount de tipus percentatge
    public setDiscount(String setObj, float discount, String shop, String discountTipo) {
        this.setObj = setObj;
        this.discount = discount;
        this.shop = shop;
        this.tipus = discountTipo;
    }

    //Creadora per setDiscount de tipus mxn
    public setDiscount(String setObj, int m, int n, String shop, String discountTipo) {
        this.setObj = setObj;
        this.compres = m;
        this.pagues = n;
        this.shop = shop;
        this.tipus = discountTipo;
    }

    public int getCompres() {
        return compres;
    }

    public int getPagues() {
        return pagues;
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
