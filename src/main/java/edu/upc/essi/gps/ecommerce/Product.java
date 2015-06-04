package edu.upc.essi.gps.ecommerce;

import edu.upc.essi.gps.domain.Entity;
import edu.upc.essi.gps.domain.HasName;

import java.util.ArrayList;

public class Product implements Entity, HasName {

    private long id;
    private String name;
    private final int price;
    private final int vatPct;
    private final int barCode;
    private ArrayList<String> typesList;
    private float popularity;

    public Product(long id, String name, int price, int vatPct, int barCode, ArrayList<String> typesList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vatPct = vatPct;
        this.barCode = barCode;
        this.typesList = typesList;
        this.popularity = 0;
    }

    public Product(){
        this.price = 0;
        this.vatPct = 0;
        this.barCode = 0;
    }

    public long getId() {
        return id;
    }

    public void incrementPopularity(){
        ++this.popularity;
    }

    public String getName() {
        return name;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getPrice() {
        return price;
    }

    public int getVatPct() {
        return vatPct;
    }

    public int getBarCode() {
        return barCode;
    }

    public int getBaseImposable(){ return price*(100-vatPct)/100;}

    public ArrayList<String> getTypesList() {return typesList;}
}
