package edu.upc.essi.gps.ecommerce;

import edu.upc.essi.gps.domain.Entity;

/**
 * Created by Miquel on 09/05/2015.
 */
public class Discount implements Entity{
    private int amountDiscount;
    private String typeOfDiscount;

    public Discount(String typeOfDiscount, int amountDiscount){
        this.amountDiscount = amountDiscount;
        this.typeOfDiscount = typeOfDiscount;
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
}
