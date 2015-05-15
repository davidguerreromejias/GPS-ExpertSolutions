package edu.upc.essi.gps.ecommerce;

import static edu.upc.essi.gps.utils.Validations.*;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class PosController {

    private final ProductsService productsService;
    private final String shop;
    private final int posNumber;
    private String currentSaleAssistantName;
    private Sale currentSale;
    private Date currentDate;
    private Discount discPerc;
    private LinkedList<Sale> ventesRealitzades;
    private final LinkedList<QuadramentInvalid> quadramentsInvalids = new LinkedList<>();
    private int initialCash;
    private historicSales historic;
    private setDiscountCollection setDiscountCollection;
    //coses pel gestor
    private String currentGestorName;
    private Date dateLoginGestor;
    private LinkedList<Discount> discMxNCollection = new LinkedList<>();

    public PosController(String shop, int posNumber, ProductsService productsService) {
        this.shop = shop;
        this.posNumber = posNumber;
        this.productsService = productsService;
        this.discPerc = new Discount("none",100);
        this.setDiscountCollection = new setDiscountCollection();
    }

    public void gestorLogin(String gestorName) {
        checkNotNull(gestorName, "gestorName");
        Date data = new Date(2015,05,13);
        if (this.currentGestorName != null)
            throw new IllegalStateException("hi ha una sessió iniciada pel gestor " + this.currentGestorName);
        this.currentGestorName = gestorName;
        this.dateLoginGestor = data;
    }

    public void login(String saleAssistantName) {
        checkNotNull(saleAssistantName, "saleAssistantName");
        Date data = new Date(2015,05,13);
        if (this.currentSaleAssistantName != null)
            throw new IllegalStateException("Aquest tpv està en ús per " + this.currentSaleAssistantName);
        this.currentSaleAssistantName = saleAssistantName;
        this.currentDate = data;
        this.ventesRealitzades = new LinkedList();
        this.initialCash = 0;
    }

    public void login(String saleAssistantName, int initCash) {
        checkNotNull(saleAssistantName, "saleAssistantName");
        if (this.currentSaleAssistantName != null)
            throw new IllegalStateException("Aquest tpv està en ús per " + this.currentSaleAssistantName);
        this.currentSaleAssistantName = saleAssistantName;
        this.ventesRealitzades = new LinkedList();
        this.initialCash = initCash;
    }

    public void startSale() {
        if (this.currentSale != null) throw new IllegalStateException("Aquest tpv ja té una venta iniciada");
        this.currentSale = new Sale(shop, posNumber, currentSaleAssistantName);
    }

    public String getCurrentSaleAssistantName() {
        return currentSaleAssistantName;
    }

    public String getCurrentGestorName() {
        return this.currentGestorName;
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
        currentSale.addNProducts(p, amount);
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
            if(sl.getDiscount().getTypeOfDiscount().equals("percentatge")){
                sb.append("-").append(sl.getDiscount().getAmountDiscount()).append("% ").append(sl.getTotalPriceRaw()-sl.getTotalPrice()).append("€\n");
                sb.append(sl.getTotalPrice()).append("€\n");
            }
            else if(sl.getDiscount().getTypeOfDiscount().equals("m x n")){
                int n = sl.getDiscount().getN();
                int m = sl.getDiscount().getM();
                int difference = (m - n) * sl.getUnitPrice();
                sb.append(sl.getDiscount().getM()).append("x").append(n).append(" -").append(difference).append("€\n");
                sb.append(sl.getTotalPrice()).append("€\n");
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
            if (canvi < 0) throw new RuntimeException("La quantitat rebuda és inferior a l'import de la venda.");
            ventesRealitzades.add(currentSale);
            return "El canvi és: " + canvi + endMessage;
        }
    }

    public void tancarTorn(int n){
        int t = getTotalTorn();
        if(n!=t){

        }
        else{

        }
    }

    public int getTotalTorn(){
        int total = 0;
        for(Sale l : ventesRealitzades) {
            total += l.getTotal();
        }
        return total+initialCash;
    }

    public void afegirQuadramentInvalid(int cashRegister){
        int x = cashRegister-getTotalTorn();
        quadramentsInvalids.add(new QuadramentInvalid(this.shop, this.posNumber, this.currentSaleAssistantName, x));
    }

    public void createPercDiscount(String type, int quant) {
        discPerc = new Discount(type,quant);
    }
    public void applyPercDiscount(String typeOfDisc){
        if(getCurrentSale() == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        currentSale.setPercActiveDiscount(discPerc.getTypeOfDiscount(), discPerc.getAmountDiscount());
    }

    public void applyMxNDiscount(int m, int n){
        if(getCurrentSale() == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        Iterator<Discount> it = discMxNCollection.iterator();
        boolean found = false;
        Discount discMxN = new Discount("m x n");
        while (it.hasNext() && !found){
            discMxN = it.next();
            if(discMxN.getM() == m && discMxN.getN() == n) found = true;
        }
        if (found) currentSale.setMxNActiveDiscount(discMxN.getTypeOfDiscount(),discMxN.getM(), discMxN.getN());

    }

    public void createHistorial(String shop){
        historic = new historicSales();
        historic.setShop(shop);
    }
    public void saveSale(){
        historic.setSale(currentSale, currentDate);
    }

    public void StopApplyingDiscount(){
        if(getCurrentSale() == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        currentSale.noActiveDiscount();
    }

    public void createMxNDisc(String type, int m, int n) {
        Discount discMxN = new Discount(type, m, n);
        discMxNCollection.add(discMxN);
    }

    public void addTypeDiscount(String shop, int discount, String tipoProd) {
        if (currentGestorName == null) throw new IllegalStateException("No hi ha cap sessio de gestor iniciada");
        setDiscount sd = new setDiscount(tipoProd, discount, shop);
        this.setDiscountCollection.addSetDiscount(sd);
    }
}
