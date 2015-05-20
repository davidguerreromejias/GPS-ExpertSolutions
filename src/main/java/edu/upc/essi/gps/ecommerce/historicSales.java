package edu.upc.essi.gps.ecommerce;

import java.util.*;

/**
 * Created by laperlablancaIV on 11/5/15.
 */

public class historicSales {

    private Map<String, TreeMap> historic = new TreeMap<String, TreeMap>();
    private String shop;

    public historicSales(Map<String, TreeMap> historic) {
        this.historic = historic;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public Map<String, TreeMap> getHistoric() {
        return historic;
    }

    public void setHistoric(Map<String, TreeMap> historic) {
        this.historic = historic;
    }

    public void setSale(Sale sale, String dateSale){
        TreeMap<String, Sale> sales = new TreeMap<String, Sale>();
        String saleA = sale.getSaleAssistantName();
        sales.put(saleA, sale);
        historic.put(dateSale, sales);
    }

    public Map<String, TreeMap> getSalesByDate(String data){
        return historic.get(data);
    }

    public Map<Date, TreeMap> getSaleByDateAndAssistant(String assistant, String data){
        return (Map<Date, TreeMap>) historic.get(data).get(assistant);
    }

}
