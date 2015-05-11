package edu.upc.essi.gps.ecommerce;

import java.util.*;

/**
 * Created by laperlablancaIV on 11/5/15.
 */

public class historicSales {

    private Map<Date, TreeMap> historic = new TreeMap<Date, TreeMap>();

    public Map<Date, TreeMap> getHistoric() {
        return historic;
    }

    public void setHistoric(Map<Date, TreeMap> historic) {
        this.historic = historic;
    }

    public void setSale(Sale sale, Date dateSale){
        TreeMap<String, Sale> sales = new TreeMap<String, Sale>();
        String saleA = sale.getSaleAssistantName();
        sales.put(saleA, sale);
        historic.put(dateSale, sales);
    }

    public Map<Date, TreeMap> getSalesByDate(Date data){
        return historic.get(data);
    }

    public Map<Date, TreeMap> getSaleByDateAndAssistant(String assistant, Date data){
        return (Map<Date, TreeMap>) historic.get(data).get(assistant);
    }

}
