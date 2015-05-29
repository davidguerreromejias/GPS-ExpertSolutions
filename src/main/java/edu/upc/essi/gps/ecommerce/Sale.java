package edu.upc.essi.gps.ecommerce;

import java.util.*;

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
            double totalPrice = (unitPrice * amount) - (unitPrice * amount * discdouble);
            System.out.println(totalPrice);
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
    private String paymentForm;
    private int totalPrice; //variable per a realitzar proves, no �s coherent amb el m�tode getTotal()
    private boolean esProva;
    private final Date data;
    private LinkedList<SaleLine> candidatsADescompteMxN = new LinkedList<>();

    public void addCandidat(){
        candidatsADescompteMxN.add(lines.getLast());
    }

    public boolean isEstaPagada() {
        return estaPagada;
    }

    public void setEstaPagada(boolean estaPagada) {
        this.estaPagada = estaPagada;
    }

    private boolean estaPagada;

    public String getPaymentForm(){
        return paymentForm;
    }

    public void setPaymentForm(String paymentForm){this.paymentForm = paymentForm;};

    public void addProduct(Product p) {
        lines.add(new SaleLine(p,1));
    }

    public void addNProducts(Product p, int amount) {
        lines.add(new SaleLine(p, amount));
    }

    public void setTotalPrice(int x){
        this.totalPrice = x;
        this.esProva = true;
    }

    public Sale(String shop, int posNumber, String saleAssistantName) {
        this.shop = shop;
        this.posNumber = posNumber;
        this.saleAssistantName = saleAssistantName;
        this.paymentForm = "efectiu";
        this.esProva = false;
        this.estaPagada = false;
        data = new Date();
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
        if(!esProva) {
            int res = 0;
            for (SaleLine l : lines) {
                res += l.getTotalPrice();
            }
            return res;
        }
        else return totalPrice;
    }

    public boolean isEmpty() {
        return lines.isEmpty();
    }

    public void deleteLine(String nomProd){
        for(SaleLine l : lines) if (nomProd == l.getProductName()) lines.remove(l);
    }

    public void assignaDescompte(Discount d, String nomP){
        for(SaleLine l : lines)if (nomP == l.getProductName()) l.setDiscount(d);
        }

    public void applyDiscountAtLastLine(Discount d){
        lines.getLast().setDiscount(d);
    }

    public void tryApplyDiscMxN(Discount d){

    }

}
