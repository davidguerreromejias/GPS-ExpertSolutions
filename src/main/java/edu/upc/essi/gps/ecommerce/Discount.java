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
    private String shop;

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

    public Discount(String type){
        this.typeOfDiscount = type;
    }

    //Creadora per Discount de tipus percentatge
    public Discount(String setObj, int amountDiscount, String shop, String discountTipo) {
        this.conjuntAAplicar = setObj;
        this.amountDiscount = amountDiscount;
        this.shop = shop;
        this.typeOfDiscount = discountTipo;
    }

    //Creadora per Discount de tipus mxn
    public Discount(String setObj, int m, int n, String shop, String discountTipo) {
        this.conjuntAAplicar = setObj;
        this.m = m;
        this.n = n;
        this.shop = shop;
        this.typeOfDiscount = discountTipo;
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

    public String getShop(){return shop;}
}
