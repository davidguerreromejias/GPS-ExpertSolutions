package edu.upc.essi.gps.ecommerce;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class SaleLine{
    private long productId;
    private String productName;
    private int unitPrice;
    private int amount;

    public SaleLine(Product product, int amount) {
        this.productId = product.getId();
        this.productName = product.getName();
        this.unitPrice = product.getPrice();
        this.amount = amount;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotalPrice() {
        return unitPrice * amount;
    }
}

public class Sale {
    private final String shop;
    private final int posNumber;
    private final String saleAssistantName;
    private final List<SaleLine> lines = new LinkedList<>();

    public void addProduct(Product p) {
        lines.add(new SaleLine(p,1));
    }

    public Sale(String shop, int posNumber, String saleAssistantName) {
        this.shop = shop;
        this.posNumber = posNumber;
        this.saleAssistantName = saleAssistantName;
    }

    public String getShop() {
        return shop;
    }

    public int getPosNumber() {
        return posNumber;
    }

    public String getSaleAssistantName() {
        return saleAssistantName;
    }

    public List<SaleLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public int getTotal() {
        int res = 0;
        for(SaleLine l : lines){
            res += l.getTotalPrice();
        }
        return res;
    }

    public boolean isEmpty() {
        return lines.isEmpty();
    }
}
