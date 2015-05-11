package edu.upc.essi.gps.ecommerce;

import java.util.*;

/**
 * Created by laperlablancaIV on 11/5/15.
 */

class saleOf{
    String saleAssistant;
    Sale sale;
}

public class historicSales {
    private Map<Date, ArrayList> historic = new TreeMap<Date, ArrayList>();

    public Map<Date, ArrayList> getHistoric() {
        return historic;
    }

    public void setHistoric(Map<Date, ArrayList> historic) {
        this.historic = historic;
    }

    public void setSale(Sale sale, Date dateSale){
        ArrayList<saleOf> sales = new ArrayList<saleOf>();
        String saleA = sale.getSaleAssistantName();
        saleOf sOf = new saleOf();
        sOf.saleAssistant = saleA;
        sOf.sale = sale;
        sales.add(sOf);
        historic.put(dateSale, sales);
    }

}
