package edu.upc.essi.gps.ecommerce;

import javafx.geometry.Pos;

import javax.xml.crypto.Data;

import static edu.upc.essi.gps.utils.Validations.*;

import java.util.*;

public class PosController {

    private final ProductsService productsService;
    private final String shop;
    private final int posNumber;
    private String currentSaleAssistantName;
    private Sale currentSale;

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    private String currentDate;



    private LinkedList<Sale> ventesRealitzades;
    private final LinkedList<QuadramentInvalid> quadramentsInvalids = new LinkedList<>();
    private int initialCash;
    private historicSales historic;
    private setDiscountCollection setDiscountCollection;
    private boolean ultimTornTancatCorrectament;
    private Discount discPerc;
    private LinkedList<Product> buscaProductes = new LinkedList<>();
    //coses pel gestor
    private String currentGestorName;
    private Date dateLoginGestor;
    private LinkedList<Discount> discMxNCollection = new LinkedList<>();
    private LinkedList<Discount> discPercCollection = new LinkedList<>();
    private LinkedList<Sale> historial = new LinkedList<>();
    String change;

    public String getChangeCard() {
        return changeCard;
    }

    String changeCard;

    public int getVentesRealitzadesId(int posNumber) {
        boolean trobat = false;
        int i = 0;
        int aux = 0;
        while(trobat == false){
            aux = this.ventesRealitzades.get(i).getPosNumber();
            if(aux == posNumber) trobat = true;
            ++i;
        }
        return aux;
    }

    public  Sale getVentesRealitzadesSale(int posNumber) {
        boolean trobat = false;
        int i = 0;
        int aux = 0;
        while(trobat == false){
            aux = this.ventesRealitzades.get(i).getPosNumber();
            if(aux == posNumber) trobat = true;
            ++i;
        }
        return this.ventesRealitzades.get(i);
    }

    public PosController(String shop, int posNumber, ProductsService productsService) {
        this.shop = shop;
        this.posNumber = posNumber;
        this.productsService = productsService;
        //this.discPerc = new Discount("none",100);
        this.setDiscountCollection = new setDiscountCollection();
        this.currentSaleAssistantName = null;
        this.ultimTornTancatCorrectament = true;
    }

    public PosController(String shop) {
        this.shop = shop;
        this.setDiscountCollection = new setDiscountCollection();
        this.posNumber = -1; //es un gestor el que està dins, els canvis seràn per tots els tpv
        this.productsService = null; //nomes serà per fer gestions, no per vendre res, per tant tampoc necessitem el productService
        this.currentSaleAssistantName = null;
        this.ultimTornTancatCorrectament = true;
    }

    public void gestorLogin(String gestorName) {
        checkNotNull(gestorName, "gestorName");
        String data1 = new String("2015,05,18");
        Date data = new Date(2015,05,13);
        if (this.currentGestorName != null) {
            throw new IllegalStateException("hi ha una sessió iniciada pel gestor " + this.currentGestorName);
        }
        this.currentGestorName = gestorName;
        this.setCurrentDate(data1);
        this.dateLoginGestor = data;
    }

    public void login(String saleAssistantName) {
        checkNotNull(saleAssistantName, "saleAssistantName");
        Date data = new Date(2015,05,13);
        String data1 = new String("2015,05,18");
        if (this.currentSaleAssistantName != null)
            throw new IllegalStateException("Aquest tpv està en ús per " + this.currentSaleAssistantName);
        this.currentSaleAssistantName = saleAssistantName;
        this.currentDate = data1;
        this.ventesRealitzades = new LinkedList();
        this.initialCash = 0;
    }

    public void buscarProductes(String s){
        StringBuffer stb = new StringBuffer(s);
        buscaProductes.clear();
        List<Product> all = productsService.listProductsByName();
        for(Product p : all){
            if(p.getName().contains(stb)){
                buscaProductes.add(p);
            }
        }
    }

    public void salePayed(){
        this.getCurrentSale().setEstaPagada(true);
    }

    public void salePayedWithCard(){
        this.getCurrentSale().setEstaPagada(true);
        this.getCurrentSale().setPaymentForm("targeta");
    }

    public long getIndexIessimDeCerca(int i){
        if(buscaProductes.isEmpty()) throw new RuntimeException("No s'ha realitzat cap cerca o aquesta no ha produït cap resultat");
        if(i > buscaProductes.size()) throw new RuntimeException("L'índex demanat excedeix el nombre de resultats de la cerca");
        return buscaProductes.get(i-1).getId();
    }

    public void setInitialCash(int s){ this.initialCash = s;}

    public void startSale() {
        if (this.currentSale != null) throw new IllegalStateException("Aquest tpv ja té una venta iniciada");
        this.currentSale = new Sale(shop, posNumber, currentSaleAssistantName);
    }

    public void newSale(){
        Sale s = new Sale(shop, posNumber, currentSaleAssistantName);
    }

    public void endSale(){
        this.currentSale = null;
    }

    public String getResultatCercaProductes(){
        if(buscaProductes.isEmpty()) throw new RuntimeException("No s'ha realitzat cap cerca o aquesta no ha produït cap resultat");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(Product p : buscaProductes){
            i++;
            sb.append(i).append(" : ").append(p.getName()).append(" - ").append(p.getPrice()).append("€/u\n");
        }
        sb.append("---\n").append(i).append(" productes trobats");
        return sb.toString();
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
        ArrayList<String> types = p.getTypesList();
        Iterator<Discount> it = discPercCollection.iterator();
        boolean found = false;
        Discount discCjt = new Discount("");
        while (it.hasNext() && !found){
            discCjt = it.next();
            String SubTypeOfDisc = discCjt.getSubType();
            for(int i = 0; i < types.size() && !found; ++i) {
                System.out.println(types.get(i)+"---------------");
                System.out.println(SubTypeOfDisc);
                if (SubTypeOfDisc.equals(types.get(i))){
                    found = true;
                }
            }
        }
        if (found){
            currentSale.applyDiscountAtLastLine(discCjt);
        }
    }

    public void addProductById(long id, int amount){
        if (currentSale == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        Product p = productsService.findById(id);
        currentSale.addNProducts(p, amount);
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
                int amountDisc = sl.getDiscount().getAmountDiscount();
                if (amountDisc == 100){
                    sb.append("REGAL ");
                }
                else {
                    sb.append("-").append(amountDisc).append("% ");
                }
                sb.append(sl.getTotalPriceRaw() - sl.getTotalPrice()).append("€\n");
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

    public void cashPayment(int delivered, String paymentForm) {
        String endMessage = "€ i la venta ha estat finalitzada.";
        if (getCurrentSale() == null) throw new IllegalStateException("No es pot cobrar una venta si no està iniciada");
        else if (getCurrentSale().isEmpty())
            throw new IllegalStateException("No es pot cobrar una venta sense cap producte");
        else {
            int canvi = delivered - getCurrentSale().getTotal();
            if (canvi < 0) throw new RuntimeException("La quantitat rebuda és inferior a l'import de la venda.");
            ventesRealitzades.add(currentSale);
            change = "El canvi és: " + canvi + endMessage;
        }
    }

    public void cardPayment(String paymentForm) {
        String endMessage = "i la venta ha estat finalitzada.";
        if (getCurrentSale() == null) throw new IllegalStateException("No es pot cobrar una venta si no està iniciada");
        else if (getCurrentSale().isEmpty())
            throw new IllegalStateException("No es pot cobrar una venta sense cap producte");
        else {
            salePayedWithCard();
            ventesRealitzades.add(currentSale);
            changeCard = "Targeta acceptada " + endMessage;
        }
    }

    public void afegirVenta(){
        ventesRealitzades.add(currentSale);
    }

    public String tancarTorn(int n){
        if(this.currentSaleAssistantName == null){ throw new RuntimeException("No hi ha cap torn iniciat"); }
        int t = getTotalTorn();
        int x = n-t;
        if(x<0) x = x*-1;
        if(x!=0){
            afegirQuadramentInvalid(this.currentSaleAssistantName,n-t);
            //donar la opcio de tornar a fer quadrament o registrar quadrament invalid

            this.ultimTornTancatCorrectament = false;
            if(x<10) return "Hi ha una diferència de menys de 10€ en el quadrament del torn";
            else return "Hi ha una diferència de 10€ o més en el quadrament del torn";
        }
        else{
            this.ultimTornTancatCorrectament = true;
            return "Torn tancat correctament";
        }
    }

    public void tornTancat(){
        this.currentSaleAssistantName = null;
    }

    public int getDiffUltimQuadramentInvalid(){
        return this.quadramentsInvalids.getLast().getDiferencia();
    }

    public boolean getTancamentUltimTorn(){
        return this.ultimTornTancatCorrectament;
    }

    public String getQuadramentsInvalids(){
        if(quadramentsInvalids.isEmpty()) throw new RuntimeException("No hi ha quadraments invàlids registrats al sistema");
        StringBuilder sb = new StringBuilder();
        sb.append("--Botiga--  --Caixa--  --Venedor--  --Quantitat--\n");
        String espai = " , ";
        for(QuadramentInvalid q : quadramentsInvalids){
            sb.append(q.getShop()).append(espai).append(q.getPosNum()).append(espai).append(q.getSaleAssistantName()).append(espai).append(q.getDiferencia()).append("€\n");
        }
        sb.append("---\n").append(quadramentsInvalids.size()).append(" quadraments invàlids registrats");
        return sb.toString();
    }

    public int getTotalTorn(){
        int total = 0;
        for(Sale l : ventesRealitzades) {
            if(l.getPaymentForm().equals("efectiu")) total += l.getTotal();
        }
        return total+initialCash;
    }

    public void afegirQuadramentInvalid(String name, int diff){
        quadramentsInvalids.add(new QuadramentInvalid(this.shop, this.posNumber, name, diff));
    }

    public void createPercDiscount(String type, int amount) {
        Discount discPerc = new Discount(type, amount);
        discPercCollection.add(discPerc);
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
        if (found) currentSale.applyDiscountAtLastLine(discMxN);
    }

    public void createHistorial(){
        this.historic = new historicSales();
    }
    public void setSaleHistorial(HistorialLine hl){
        historic.addSale(hl);
    }

    public void createMxNDisc(String type, int m, int n) {
        Discount discMxN = new Discount(type, m, n);
        discMxNCollection.add(discMxN);
    }

    public void addTypeDiscount(int discount, String tipoProd) {
        if (currentGestorName == null) throw new IllegalStateException("No hi ha cap sessio de gestor iniciada");
        setDiscount sd = new setDiscount(tipoProd, discount, this.shop);
        this.setDiscountCollection.addSetDiscount(sd);
        float aux = setDiscountCollection.getSetDiscount(tipoProd, this.shop);
        if (aux != discount) throw new IllegalStateException("No 'ha afegit el descompte");
    }

    public float getDiscountBySetProduct(String setProduct) {
        return setDiscountCollection.getSetDiscount(setProduct, this.shop);
    }



    public void deletedTypeDiscount(String setProducts) {
        setDiscountCollection.deleteSetDiscount(setProducts);
    }

    public boolean existSetProduct(String setProduct) {
        return setDiscountCollection.existsSetDiscount(setProduct);
    }


    public void deleteLine(String nomProd) {
        currentSale.deleteLine(nomProd);
    }

    public void assignaDescompte(String nomP){
        currentSale.assignaDescompte(getDiscPerc(), nomP);
    }

    private Discount getDiscPerc(){return discPerc;}

    public void setDiscPerc(String tipus, int amount){
        discPerc = new Discount(tipus, amount);
    }

    public String getChange(){ return change;}

    public String visualitzaVentesPerData(String data){
        ArrayList<HistorialLine> aux = new ArrayList();
        aux = historic.visualitzarPerData(data);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aux.size(); i++) {
            sb.append(i+1).append(" : ").append(aux.get(i).getSale().getTotal()).append(" - Realitzada per ").append(aux.get(i).getAssistantShop()).append(".\n");
            sb.append("---\n");
        }
        return sb.toString();
    }

    public void createCjtDiscount(String type, String subType, int amount) {
        Discount discPerc = new Discount(type, subType, amount);
        discPercCollection.add(discPerc);
    }

    public void afegirRegal(String nomP){
        discPerc = new Discount("percentatge", 100);
        List<SaleLine> lines = currentSale.getLines();
        //lines.getLa
        currentSale.addProduct(productsService.findByName(nomP));
        currentSale.assignaDescompte(discPerc, nomP);
    }
}

