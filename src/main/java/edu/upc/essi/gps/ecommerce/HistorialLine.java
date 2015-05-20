package edu.upc.essi.gps.ecommerce;

/**
 * Created by laperlablancaIV on 20/5/15.
 */
public class HistorialLine {
    private String data;

    public String getAssistantShop() {
        return assistantShop;
    }

    public void setAssistantShop(String assistantShop) {
        this.assistantShop = assistantShop;
    }

    private String assistantShop;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    private Sale sale;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    HistorialLine(Sale sale, String data, String assistantShop){
        this.data = data;
        this.assistantShop = assistantShop;
        this.sale = sale;
    }
}
