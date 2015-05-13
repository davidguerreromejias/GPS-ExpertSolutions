package edu.upc.essi.gps.ecommerce;

/**
 * Created by Usuario on 13/05/2015.
 */
public class QuadramentInvalid {
    private final String shop;
    private final int posNum;
    private final String saleAssistantName;
    private final int diferencia;

    public QuadramentInvalid(String shop, int posNum, String saleAssistantName, int diferencia){
        this.shop = shop;
        this.posNum = posNum;
        this.saleAssistantName = saleAssistantName;
        this.diferencia = diferencia;
    }

    public String getShop(){
        return this.shop;
    }

    public int getPosNum(){
        return this.posNum;
    }

    public String getSaleAssistantName(){
        return this.saleAssistantName;
    }

    public int getDiferencia(){
        return this.diferencia;
    }
}
