package edu.upc.essi.gps.ecommerce;

import edu.upc.essi.gps.utils.Repository;

import java.util.ArrayList;

/**
 * Created by Miquel on 09/05/2015.
 */
public class DiscountsCollection{
    private ArrayList<ProductDiscount> prodsDiscPerc;
    private ArrayList<ProductDiscount> prodsDiscRegal;

    public DiscountsCollection(){}

    public DiscountsCollection(ArrayList<ProductDiscount> ps){
        prodsDiscPerc = ps;
    }

    public ArrayList<ProductDiscount> getProdsDisc(){
        return prodsDiscPerc;
    }

    public void addProd(ProductDiscount p){
        prodsDiscPerc.add(p);
    }

    public ProductDiscount getProdByName(String name, String tipus){
        if (tipus == "percentatge"){
            int size = prodsDiscPerc.size();
            for (int i = 0; i<size; ++i){
                if (prodsDiscPerc.get(i).getProduct().getName() == name) return prodsDiscPerc.get(i);
            }
        }
        return null;
    }

    public void deleteProd(ProductDiscount p){

    }

    public void setProdsDisc(){}
}
