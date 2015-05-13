package edu.upc.essi.gps.ecommerce;

import static edu.upc.essi.gps.utils.Validations.*;
import java.util.LinkedList;
import java.util.List;

public class PosController {

    private final ProductsService productsService;
    private final String shop;
    private final int posNumber;
    private String currentSaleAssistantName;
    private Sale currentSale;
    private Discount discPerc;
    private final LinkedList<Sale> ventesRealitzades = new LinkedList();

    public PosController(String shop, int posNumber, ProductsService productsService) {
        this.shop = shop;
        this.posNumber = posNumber;
        this.productsService = productsService;
    }

    public void login(String saleAssistantName) {
        checkNotNull(saleAssistantName, "saleAssistantName");
        if (this.currentSaleAssistantName != null)
            throw new IllegalStateException("Aquest tpv està en ús per " + this.currentSaleAssistantName);
        this.currentSaleAssistantName = saleAssistantName;
    }

    public void startSale() {
        if (this.currentSale != null) throw new IllegalStateException("Aquest tpv ja té una venta iniciada");
        this.currentSale = new Sale(shop, posNumber, currentSaleAssistantName);
    }

    public String getCurrentSaleAssistantName() {
        return currentSaleAssistantName;
    }

    public Sale getCurrentSale() {
        return currentSale;
    }

    public void addProductByBarCode(int barCode) {
        if (currentSale == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        Product p = productsService.findByBarCode(barCode);
        currentSale.addProduct(p);
    }

    public void addProductByBarCode(int barCode, int amount) {
        if (currentSale == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        Product p = productsService.findByBarCode(barCode);
        currentSale.addNProducts(p, amount);
    }

    public void addProductByName(String nom, int amount) {
        if (currentSale == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        Product p = productsService.findByName(nom);
        currentSale.addNProducts(p,amount);
    }

    public String getCustomerScreenMessage() {
        String welcomeMessage = "Li donem la benvinguda a Joguets i Joguines!";
        if (currentSale == null) return welcomeMessage;
        if (currentSale.isEmpty()) {
            return welcomeMessage + "\nL'atén " + currentSale.getSaleAssistantName();
        }
        StringBuilder sb = new StringBuilder();
        for (SaleLine sl : currentSale.getLines()) {
            sb.append(sl.getProductName()).append(" - ")
                    .append(sl.getUnitPrice()).append("€/u x ").append(sl.getAmount()).append("u = ")
                    .append(sl.getTotalPriceRaw()).append("€\n");
            if(discPerc.getTypeOfDiscount().equals("percentatge")){
                sb.append("-").append(sl.getDiscount().getAmountDiscount()).append("% ").append(sl.getTotalPriceRaw()-sl.getTotalPrice()).append("€\n");
                sb.append(sl.getTotalPrice());
            }
        }
        sb.append("---\n").append("Total: ").append(currentSale.getTotal()).append("€");
        return sb.toString();
    }

    public String cashPayment(int delivered, String paymentForm) {
        String endMessage = "€ i la venta ha estat finalitzada i enrigistrada correctament.";
        if (getCurrentSale() == null) throw new IllegalStateException("No es pot cobrar una venta si no està iniciada");
        else if (getCurrentSale().isEmpty())
            throw new IllegalStateException("No es pot cobrar una venta sense cap producte");
        else {
            int canvi = delivered - getCurrentSale().getTotal();
            if (paymentForm == "efectiu") {
                if (canvi < 0) throw new RuntimeException("La quantitat rebuda és inferior a l'import de la venda.");
            }
            return "El canvi és: " + canvi + endMessage;
        }
    }

    public void createPercDiscount(String type, int quant) {
        discPerc = new Discount(type,quant);
    }
    public void applyDiscount(){
        if(getCurrentSale() == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        currentSale.setActiveDiscount(discPerc.getTypeOfDiscount(),discPerc.getAmountDiscount());
    }
}
