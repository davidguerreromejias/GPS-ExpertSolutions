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

    public void addSale(Sale sale, String assistant, String data){
        HistorialLine hl = new HistorialLine(sale, data, assistant);
        historial.add(hl);
    }

    public ArrayList<HistorialLine> visualitzarPerData(String data){
        ArrayList<HistorialLine> totes = new ArrayList();
        for (int i = 0; i < this.historial.size(); i++) {
            if(data.equals(historial.get(i).getData())){
                totes.add(historial.get(i));
            }
        }
        return totes;
    }

    public ArrayList<HistorialLine> visualitzarPerVenedor(String venedor){
        ArrayList<HistorialLine> totes = new ArrayList();
        for (int i = 0; i < this.historial.size(); i++) {
            if(venedor.equals(historial.get(i).getAssistantShop())){
                totes.add(historial.get(i));
            }
        }
        return totes;
    }

    public ArrayList<HistorialLine> visualitzarTotHistorial(){
        ArrayList<HistorialLine> totes = new ArrayList();
        for (int i = 0; i < this.historial.size(); i++) {
                totes.add(historial.get(i));
        }
        return totes;
    }

}
