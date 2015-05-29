package edu.upc.essi.gps.ecommerce;

import static edu.upc.essi.gps.utils.Validations.*;

import java.util.*;

public class PosController {

    private final ProductsService productsService;
    private final String shop;
    private final int posNumber;

    public void setCurrentSaleAssistantName(String currentSaleAssistantName) {
        this.currentSaleAssistantName = currentSaleAssistantName;
    }

    private String currentSaleAssistantName;
    private Sale currentSale;

    public String getMessage() {
        return message;
    }


    private String message;

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    private String currentDate;

    private LinkedList<Integer> inputQuadraments = new LinkedList<>();
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
    //private LinkedList<Discount> discMxNCollection = new LinkedList<>();
    private LinkedList<Discount> discCollection = new LinkedList<>();
    private LinkedList<Discount> regalCollection = new LinkedList<>();


    //public LinkedList<Discount> getDiscPercCollection(){return discPercCollection;}

    private historicSales historicSales;
    String change;

    private ArrayList<ProductDiscount> CollectionPerc = new ArrayList<>();
    private ArrayList<ProductDiscount> CollectionRegal = new ArrayList<>();

    public String getChangeCard() {
        return changeCard;
    }

    String changeCard;

    public void setInputTancarTorn(int x){
       inputQuadraments.add(x);
    }

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
        this.setDiscountCollection = new setDiscountCollection();
        this.currentSaleAssistantName = null;
        this.ultimTornTancatCorrectament = true;
        this.historicSales = new historicSales();
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

    public void applyDiscount(Product p){
        ArrayList<String> types = p.getTypesList();
        Iterator<Discount> it = discCollection.iterator();
        boolean found = false;
        Discount disc = new Discount("");
        while (it.hasNext() && !found){
            disc = it.next();
            String SubTypeOfDisc = disc.getSubType();
            for(int i = 0; i < types.size() && !found; ++i) {
                if (SubTypeOfDisc.equals(types.get(i))){
                    found = true;
                }
            }
        }
        if (found){
            if(disc.getTypeOfDiscount().equals("percentatge")) {
                currentSale.applyDiscountAtLastLine(disc,0);
            }
            else if(disc.getTypeOfDiscount().equals("m x n")){
                currentSale.addCandidat();
                currentSale.tryApplyDiscMxN(disc);
            }
        }
    }

    public void addProductByBarCode(int barCode) {
        if (currentSale == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        Product p = productsService.findByBarCode(barCode);
        currentSale.addProduct(p);
        applyDiscount(p);
    }


    public void addProductByBarCode(int barCode, int amount) {
        if (currentSale == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        Product p = productsService.findByBarCode(barCode);
        currentSale.addNProducts(p, amount);
        applyDiscount(p);
    }

    public void addProductById(long id, int amount){
        if (currentSale == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        Product p = productsService.findById(id);
        currentSale.addNProducts(p, amount);
        applyDiscount(p);
    }

    public void addProductByName(String nom, int amount) {
        if (currentSale == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        Product p = productsService.findByName(nom);
        currentSale.addNProducts(p, amount);
        applyDiscount(p);
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
            if(sl.getDiscount().getTypeOfDiscount().equals("percentatge")) {
                int amountDisc = sl.getDiscount().getAmountDiscount();
                if (amountDisc == 100) {
                    sb.append("REGAL ");
                } else {
                    sb.append("-").append(amountDisc).append("% ");
                }
                sb.append("-").append(sl.getTotalPriceRaw() - sl.getTotalPrice()).append("€\n");
            }

            else if(sl.getDiscount().getTypeOfDiscount().equals("m x n")){
                int n = sl.getDiscount().getN();
                int m = sl.getDiscount().getM();
                int difference = (m - n) * sl.getUnitPrice();
                sb.append(sl.getDiscount().getM()).append("x").append(n).append(" -").append(difference).append("€\n");
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

    public String tancarTorn(){
        if(this.currentSaleAssistantName == null){ throw new RuntimeException("No hi ha cap torn iniciat"); }
        if(!inputQuadraments.isEmpty()){
            int q = inputQuadraments.poll();
            int a = q - getTotalTorn();
            if(a != 0){
                if(inputQuadraments.isEmpty()){
                    if(a < 0) a = -a;
                    afegirQuadramentInvalid(a);
                    if(a < 10) return "Hi ha 10 o menys euros de diferència en el quadrament del torn";
                    else return "Hi ha més de 10 euros de diferència en el quadrament del torn";
                }
                else return tancarTorn();
            }
            else{
                inputQuadraments.clear();
                return "El torn s'ha tancat correctament";
            }
        }
        else{
            throw new RuntimeException("No s'ha introduit la quantitat de diners de la caixa");
        }
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

    public void afegirQuadramentInvalid(int diff){
        quadramentsInvalids.add(new QuadramentInvalid(this.shop, this.posNumber, this.currentSaleAssistantName, diff));
    }

    public void afegirQuadramentInvalid(String name, int diff){
        quadramentsInvalids.add(new QuadramentInvalid(this.shop, this.posNumber, name, diff));
    }

    public void createPercDiscount(String type, int amount) {
        Discount discPerc = new Discount(type, amount);
        discCollection.add(discPerc);
    }

    public void createMxNDisc(String type,String subtype, int m, int n) {
        Discount discMxN = new Discount(type,subtype, m, n);
        discCollection.add(discMxN);
    }


    public void addTypeDiscount(String tipoDiscount, int discount, String tipoProd) {
        if (currentGestorName == null) throw new IllegalStateException("No hi ha cap sessio de gestor iniciada");
        setDiscount sd = new setDiscount(tipoProd, discount, this.shop, tipoDiscount);
        this.setDiscountCollection.addSetDiscount(sd);
        float aux = setDiscountCollection.getSetDiscount(tipoProd, this.shop, tipoDiscount);
        if (aux != discount) throw new IllegalStateException("No s'ha afegit el descompte");
    }

    public float getDiscountBySetProduct(String setProduct, String type) {
        return setDiscountCollection.getSetDiscount(setProduct, this.shop, type);
    }

    public void deleteLine(String nomProd) {
        currentSale.deleteLine(nomProd);
    }

    public void assignaDescompte(String nomP){
        currentSale.assignaDescompte(getDiscPerc(), nomP);
    }

    private Discount getDiscPerc(){return discPerc;}

    public void setDiscPerc(String tipus, int amount){
        Discount d = new Discount(tipus, amount);
        discCollection.add(d);
    }

    public String getChange(){ return change;}

    public void visualitzaVentesPerData(String data){
        ArrayList<HistorialLine> aux = new ArrayList();
        aux = historicSales.visualitzarPerData(data);
        StringBuilder sb = new StringBuilder();
        sb.append("Data ").append(data);
        for (int i = 0; i < aux.size(); i++) {
            sb.append("\n").append(i + 1).append(" : ").append(aux.get(i).getSale().getTotal()).append("€ - Realitzada per ").append(aux.get(i).getAssistantShop()).append(".\n");
            sb.append("---");
        }
        message = sb.toString();
    }

    public void visualitzaVentesPerVenedor(String venedor){
        ArrayList<HistorialLine> aux = new ArrayList();
        aux = historicSales.visualitzarPerVenedor(venedor);
        StringBuilder sb = new StringBuilder();
        sb.append(venedor);
        for (int i = 0; i < aux.size(); i++) {
            sb.append("\n").append(i + 1).append(" : ").append(aux.get(i).getSale().getTotal()).append("€ - Data ").append(aux.get(i).getData()).append(".\n");
            sb.append("---");
        }
        message = sb.toString();
    }

    public void visualitzaTotHistorial(){
        ArrayList<HistorialLine> aux = new ArrayList();
        aux = historicSales.visualitzarTotHistorial();
        StringBuilder sb = new StringBuilder();
        sb.append("Historial de ventes");
        for (int i = 0; i < aux.size(); i++) {
            sb.append("\n").append(i + 1).append(" : ").append(aux.get(i).getSale().getTotal()).append("€ - Realitzada per ").append(aux.get(i).getAssistantShop()).append(" en data "
            ).append(aux.get(i).getData()).append(".\n");
            sb.append("---");
        }
        message = sb.toString();
    }

    public void createCjtDiscount(String type, String subType, int amount) {
        Discount discPerc = new Discount(type, subType, amount);
        discCollection.add(discPerc);
    }

    public void afegirRegal(String regal, String nomP){
        Discount d = new Discount();
        int size = regalCollection.size();
        for (int i = 0; i<size; ++i)
            if (regalCollection.get(i).getRegal().getName() == regal) d = regalCollection.get(i);
        Product p = productsService.findByName(nomP);
        addProductDiscountRegal(p, d);

    }

    public void finishSale(){
        this.historicSales.addSale(this.currentSale, this.currentSaleAssistantName, this.currentDate);
        this.currentSale = null;
    }

    public String getSetDiscountList(String type) {
        return setDiscountCollection.SetDiscountList(type);
    }

    public void addProductDiscountPerc(Product p, Discount d){
        ProductDiscount pd = new ProductDiscount(d, p);
        int size = CollectionPerc.size();
        for (int i = 0; i<size; ++i){
            if (CollectionPerc.get(i).getProduct() == p){
                    CollectionPerc.remove(i);
            }
        }
        CollectionPerc.add(pd);
    }

    public void addProductDiscountRegal(Product p, Discount d){
        ProductDiscount pd = new ProductDiscount(d, p);
        int size = CollectionRegal.size();
        for (int i = 0; i<size; ++i){
            if (CollectionRegal.get(i).getProduct() == p){
                CollectionRegal.remove(i);
            }
        }
        CollectionRegal.add(pd);
    }

    public void aplicarDescomptePerc(int amount, String nomP){
        int size = discCollection.size();
        Discount d = new Discount();
        for (int i = 0; i < size; ++i)
            if (discCollection.get(i).getAmountDiscount() == amount) d = discCollection.get(i);
        Product p = productsService.findByName(nomP);
        addProductDiscountPerc(p, d);
    }

    public void afegirRegalCollection(String regal){
        Discount d = new Discount();
        d.setTypeOfDiscount("regal");
        Product p = productsService.findByName(regal);
        d.setRegal(p);
        regalCollection.add(d);
    }

    public String visualitzaDescompteProducte(){
        int size = CollectionPerc.size();
        StringBuilder sb = new StringBuilder();
        sb.append("Descomptes actuals per productes:");

        for (int i = 0; i < size; ++i) {
            sb.append("\n");
            sb.append(CollectionPerc.get(i).getProduct().getName());
            sb.append(": ").append(CollectionPerc.get(i).getDiscount().getAmountDiscount());
            sb.append("%");
        }
        return sb.toString();
    }

    public String visualitzaRegals(){
        int size = CollectionRegal.size();
        StringBuilder sb = new StringBuilder();
        sb.append("Productes que tenen regals:");

        for (int i = 0; i < size; ++i) {
            sb.append("\nPer la compra de ");
            sb.append(CollectionRegal.get(i).getProduct().getName());
            sb.append(" s'obté de regal ").append(CollectionRegal.get(i).getDiscount().getRegal().getName());
        }
        return sb.toString();
    }
}

