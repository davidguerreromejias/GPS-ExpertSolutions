package edu.upc.essi.gps.ecommerce;

import edu.upc.essi.gps.domain.Entity;

/**
 * Created by Miquel on 09/05/2015.
 */
public class Discount implements Entity{
    private int amountDiscount;
    private String typeOfDiscount;
    private int m;
    private int n;

    public Discount(String typeOfDiscount, int amountDiscount){
        this.amountDiscount = amountDiscount;
        this.typeOfDiscount = typeOfDiscount;
    }

    public Discount(String typeOfDiscount, int m, int n){
        this.typeOfDiscount = typeOfDiscount;
        this.m = m;
        this.n = n;
    }

    public Discount(String type){
        this.typeOfDiscount = type;
    }

    public String getTypeOfDiscount(){
        return typeOfDiscount;
    }

    public void setTypeOfDiscount(String type) {
        typeOfDiscount = type;
    }

    public int getAmountDiscount(){
        return amountDiscount;
    }

    public void setAmountDiscount(int disc){
        amountDiscount = disc;
    }

    @Override
    public long getId() {
        return 0;
    }

    public int getM(){
        return m;
    }

    public int getN(){
        return n;
    }
}
