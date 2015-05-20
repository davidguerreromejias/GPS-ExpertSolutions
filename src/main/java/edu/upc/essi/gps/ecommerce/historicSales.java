package edu.upc.essi.gps.ecommerce;

import java.util.*;

/**
 * Created by laperlablancaIV on 11/5/15.
 */

public class historicSales {

    private LinkedList<HistorialLine> historial;

    public historicSales() {
        historial = new LinkedList<>();
    }

    public void addSale(HistorialLine hl){
        historial.add(hl);
    }

    public ArrayList<HistorialLine> visualitzarPerData(String data){
        ArrayList<HistorialLine> totes = new ArrayList();
        for (int i = 0; i < this.historial.size(); i++) {
            if(data == historial.get(i).getData()){
                totes.add(historial.get(i));
            }
        }
        return totes;
    }

}
