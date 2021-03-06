package edu.upc.essi.gps.ecommerce;

import edu.upc.essi.gps.domain.Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Miquel on 09/05/2015.
 */
public class Discount implements Entity{
    private int amountDiscount;
    private String typeOfDiscount;
    private int m;
    private int n;
    private String conjuntAAplicar;
    private Product regal;

    public Discount(){}

    public Discount(String typeOfDiscount, int amountDiscount){
        this.amountDiscount = amountDiscount;
        this.typeOfDiscount = typeOfDiscount;
    }

    public Discount(String typeOfDiscount, String conjuntAAplicar,int m, int n){
        this.typeOfDiscount = typeOfDiscount;
        this.m = m;
        this.n = n;
        this.conjuntAAplicar = conjuntAAplicar;
    }

    public Discount(String typeOfDiscount, String conjuntAAplicar, int amountDiscount){
        this.amountDiscount = amountDiscount;
        this.typeOfDiscount = typeOfDiscount;
        this.conjuntAAplicar = conjuntAAplicar;
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

    public String getConjuntAAplicar(){
        return conjuntAAplicar;
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

    public void setRegal(Product r){ regal = r;}

    public Product getRegal(){ return regal;}

}
