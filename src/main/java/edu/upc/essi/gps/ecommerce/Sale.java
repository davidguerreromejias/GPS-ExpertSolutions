package edu.upc.essi.gps.ecommerce;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class SaleLine{
    private long productId;
    private String productName;
    private int unitPrice;
    private int amount;
    private Discount discount;

    public SaleLine(Product product, int amount) {
        this.productId = product.getId();
        this.productName = product.getName();
        this.unitPrice = product.getPrice();
        this.amount = amount;
        this.discount = new Discount("None",100);
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

    public int getTotalPriceRaw() {
        return unitPrice * amount;
    }

    public int getTotalPrice() {
        if(discount.getTypeOfDiscount().equals("percentatge")) {
            double discdouble = discount.getAmountDiscount();
            discdouble = discdouble / 100;
            double totalPrice = unitPrice * amount * discdouble;
            return (int) totalPrice;
        }
        else if(discount.getTypeOfDiscount().equals("m x n")){
            return unitPrice * discount.getN();
        }
        else return unitPrice * amount;
    }

    public void setDiscount(Discount d){
        discount = d;
    }

    public Discount getDiscount(){
        return discount;
    }
}

public class Sale {
    private final String shop;
    private final int posNumber;
    private final String saleAssistantName;
    private final LinkedList<SaleLine> lines = new LinkedList<>();
    private Discount activeDiscount;
    private final String paymentForm;

    public void addProduct(Product p) {
        lines.add(new SaleLine(p,1));
        if(activeDiscount.getTypeOfDiscount().equals("percentatge")) {
            lines.getLast().setDiscount(activeDiscount);
        }
    }

    public void addNProducts(Product p, int amount) {
        lines.add(new SaleLine(p, amount));
        if(activeDiscount.getTypeOfDiscount().equals("percentatge")) {
            lines.getLast().setDiscount(activeDiscount);
        }
        else if(activeDiscount.getTypeOfDiscount().equals("m x n")) {
            lines.getLast().setDiscount(activeDiscount);
        }
    }

    public Sale(String shop, int posNumber, String saleAssistantName) {
        this.shop = shop;
        this.posNumber = posNumber;
        this.saleAssistantName = saleAssistantName;
        activeDiscount = new Discount("None", 0);
        this.paymentForm = "efectiu";
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

    public void setPercActiveDiscount(String type, int amount){
        activeDiscount = new Discount(type,amount);
    }

    public void setMxNActiveDiscount(String type, int m, int n){
        activeDiscount = new Discount(type,m,n);
    }

    public void noActiveDiscount(){
        activeDiscount = new Discount("none");
    }
}
