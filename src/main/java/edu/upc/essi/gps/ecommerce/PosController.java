package edu.upc.essi.gps.ecommerce;

import javax.jws.soap.SOAPBinding;

import static edu.upc.essi.gps.utils.Validations.*;

import java.lang.reflect.Array;
import java.util.*;
import java.lang.Math;

public class PosController {

    private final ProductsService productsService;
    private final String shop;
    private final int posNumber;
    private UsersCollection UsersCollection;

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

    public String currentSaleTiquet;

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    private int canvi;
    private String currentDate;
    private Sale lastSale;
    private LinkedList<Integer> inputQuadraments = new LinkedList<>();
    private int difUltimQuadrament;
    private boolean tornTancat;
    private LinkedList<Sale> ventesRealitzades;
    private final LinkedList<QuadramentInvalid> quadramentsInvalids = new LinkedList<>();
    private int initialCash;
    private setDiscountCollection setDiscountCollection;
    private LinkedList<Product> buscaProductes = new LinkedList<>();
    //coses pel gestor
    private String currentGestorName;
    private Date dateLoginGestor;
    private LinkedList<Discount> discCollection = new LinkedList<>();
    private LinkedList<Discount> regalCollection = new LinkedList<>();

    private String llista; //servirà per no haver de repetir la lectura dels descomptes

    private historicSales historicSales;
    String change;

    private ArrayList<ArrayList<String>> CollectionRegal = new ArrayList<>();

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

    public String getDifTancarTorn(){
        int n = Math.abs(difUltimQuadrament);
        if(n==0) return "El torn s'ha tancat correctament";
        else if(n<=10) return "Hi ha 10 o menys euros de diferència en el quadrament";
        else return "Hi ha més de 10 euros de diferència en el quadrament";
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
        this.historicSales = new historicSales();
        this.currentSaleAssistantName = null;
        this.UsersCollection = new UsersCollection();
    }

    public PosController(String shop) {
        this.shop = shop;
        this.setDiscountCollection = new setDiscountCollection();
        this.posNumber = -1; //es un gestor el que està dins, els canvis seràn per tots els tpv
        this.productsService = null; //nomes serà per fer gestions, no per vendre res, per tant tampoc necessitem el productService
        this.currentSaleAssistantName = null;
        this.UsersCollection = new UsersCollection();
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
        this.tornTancat = false;
    }


    public void gestorLogOut(String gestorName) {
        this.currentGestorName = null;
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
        this.tornTancat = false;
        this.lastSale = null;
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

    public void startSaleDate(String data) {
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

    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public void applyDiscount(Product p){
        ArrayList<String> types = p.getTypesList();
        Iterator<Discount> it = discCollection.iterator();
        boolean found = false;
        Discount disc = new Discount("");
        while (it.hasNext() && !found){
            disc = it.next();
            String conjuntAAplicar = disc.getConjuntAAplicar();
            for(int i = 0; i < types.size() && !found; ++i) {
                if (conjuntAAplicar.equals(types.get(i))){
                    found = true;
                }
            }
        }
        if (found){
            if(disc.getTypeOfDiscount().equals("percentatge")) {
                currentSale.applyDiscountAtLastLine(disc,0);
            }
            else if(disc.getTypeOfDiscount().equals("m x n")){
                currentSale.tryApplyDiscMxN(disc);
                currentSale.addCandidat();
            }
        }
    }

    public void addProductByBarCode(int barCode) {
        if (currentSale == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        Product p = productsService.findByBarCode(barCode);
        currentSale.addProduct(p);
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
                    .append(sl.getTotalPriceRaw()).append("€");
            if (sl.esRegal()) sb.append(" (REGAL)");
            sb.append("\n");
            if(sl.getDiscount().getTypeOfDiscount().equals("percentatge")) {
                int amountDisc = sl.getDiscount().getAmountDiscount();
                sb.append("-").append(amountDisc).append("% ").append("en ").append(sl.getDiscount().getConjuntAAplicar());
                sb.append(" -").append(sl.getTotalPriceRaw() - sl.getTotalPrice()).append("€\n");
            }


            else if(sl.getDiscount().getTypeOfDiscount().equals("m x n")){
                int n = sl.getDiscount().getN();
                int m = sl.getDiscount().getM();
                int difference = (m - n) * sl.getUnitPrice();
                sb.append(sl.getDiscount().getM()).append("x").append(n).append(" en ").append(sl.getDiscount().getConjuntAAplicar());
                if(!sl.getTeDiscConjAplicat()){
                    int countDisc = sl.getAmount()/m;
                    if(countDisc > 1)sb.append(" x").append(countDisc);
                    sb.append(" -").append(difference*countDisc).append("€\n");
                }
                else {
                    difference = (sl.getAmount() - sl.getEffectiveN()) * sl.getUnitPrice();
                    sb.append(" -").append(difference).append("€\n");
                }
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
            canvi = delivered - getCurrentSale().getTotal();
            if (canvi < 0) throw new RuntimeException("La quantitat rebuda és inferior a l'import de la venda.");
            ventesRealitzades.add(currentSale);
            change = "El canvi és: " + canvi + endMessage;
        }
        salePayed();
        this.lastSale = getCurrentSale();
        finishSale();
    }

    public void cardPayment(String paymentForm) {
        String endMessage = "i la venta ha estat finalitzada.";
        if (getCurrentSale() == null) throw new IllegalStateException("No es pot cobrar una venta si no està iniciada");
        else if (getCurrentSale().isEmpty())
            throw new IllegalStateException("No es pot cobrar una venta sense cap producte");
        else {
            salePayedWithCard();
            this.lastSale = getCurrentSale();
            ventesRealitzades.add(currentSale);
            finishSale();
            changeCard = "Targeta acceptada " + endMessage;
        }
    }

    public void afegirVenta(){
        ventesRealitzades.add(currentSale);
    }

    public void tryTancarTorn(int n){
        if(!tornTancat) {
            if (this.currentSaleAssistantName == null) {
                throw new RuntimeException("No hi ha cap torn iniciat");
            }
            difUltimQuadrament = n - getTotalTorn();
            if (difUltimQuadrament == 0) tancarTorn();
        }
    }

    public void tancarTorn(){
        if(difUltimQuadrament != 0) {
            quadramentsInvalids.add(new QuadramentInvalid(this.shop,this.posNumber,this.currentSaleAssistantName,difUltimQuadrament));
        }
        tornTancat = true;
        this.currentSaleAssistantName = null;
    }

    public String getCurrentTiquet(){
        if(this.currentSaleTiquet == null) return "shit";
        return this.currentSaleTiquet;
    }


    public String getQuadramentsInvalids(){
        if(quadramentsInvalids.isEmpty()) throw new RuntimeException("No hi ha quadraments invàlids registrats al sistema");
        StringBuilder sb = new StringBuilder();
        sb.append("--Botiga--  --Caixa--  --Venedor--  --Quantitat--\n");
        String espai = " , ";
        for(QuadramentInvalid q : quadramentsInvalids) {
            sb.append(q.getShop()).append(espai).append(q.getPosNum()).append(espai).append(q.getSaleAssistantName()).append(espai).append(q.getDiferencia()).append("€\n");
        }
        sb.append("---\n").append(quadramentsInvalids.size()).append(" quadraments invàlids registrats");
        return sb.toString();
    }

    private void afegirLiniaATiquet(StringBuilder sb, SaleLine sl, int iva21, int iva10, int iva2){
        if(!sl.esRegal()) {
            sb.append(sl.getProductName());
            Product p = productsService.findByName(sl.getProductName());
            for (int i = sl.getProductName().length(); i < 24; ++i) {
                sb.append(" ");
            }
            sb.append(" ");
            int n = sb.toString().length();
            int preu = p.getPrice();
            sb.append(preu);
            int m = sb.toString().length();
            for (int i = m - n; i < 9; ++i) {
                sb.append(" ");
            }
            sb.append(" ");
            n = sb.toString().length();
            int iva = p.getVatPct();
            if(iva == 21) iva21+=preu;
            else if(iva == 10) iva10+=preu;
            else if(iva == 2) iva2+=preu;
            sb.append(iva).append("%");
            m = sb.toString().length();
            for (int i = m - n; i < 9; ++i) {
                sb.append(" ");
            }
            sb.append(" ");
            n = sb.toString().length();
            sb.append(sl.getAmount());
            m = sb.toString().length();
            for (int i = m - n; i < 7; ++i) {
                sb.append(" ");
            }
            sb.append(" ");
            if(sl.getDiscount().getTypeOfDiscount().equals("m x n") || sl.getDiscount().getTypeOfDiscount().equals("percentatge")) sb.append("(");
            sb.append(sl.getTotalPriceRaw());
            if(sl.getDiscount().getTypeOfDiscount().equals("m x n") || sl.getDiscount().getTypeOfDiscount().equals("percentatge")) sb.append(")");
            sb.append("\n");
            if(sl.getDiscount().getTypeOfDiscount().equals("percentatge")){
                int j = sb.toString().length();
                sb.append("Descompte de ").append(sl.getDiscount().getAmountDiscount()).append("%");
                int k = sb.toString().length();
                for(int q = k-j; q < 53; ++q){sb.append(" ");}
                sb.append(sl.getTotalPrice()-sl.getTotalPriceRaw()).append("\n");
            }
            else if(sl.getDiscount().getTypeOfDiscount().equals("m x n")){
                int j = sb.toString().length();
                sb.append("Descompte de ").append(sl.getDiscount().getM()).append("x").append(sl.getDiscount().getN());
                int k = sb.toString().length();
                for(int q = k-j; q < 53; ++q){sb.append(" ");}
                sb.append(sl.getTotalPrice()-sl.getTotalPriceRaw()).append("\n");
            }
        }
        else{
            sb.append("De regal: ").append(sl.getAmount()).append(" unitats de ").append(sl.getProductName()).append("\n");
        }
    }

    public void createTiquet(){
        Sale s = this.lastSale;
        if(s == null) throw new RuntimeException("La venta no s'ha cobrat");
        if(!s.isEstaPagada()) throw new RuntimeException("La venta no s'ha cobrat");
        StringBuilder sb = new StringBuilder();
        sb.append("Joguets i Joguines\n");
        sb.append(getCurrentDate()).append("\n");
        sb.append("L'atén ").append(getCurrentSaleAssistantName()).append(" a la botiga ").append(this.shop).append("\n");
        sb.append("Caixa num ").append(this.posNumber).append("\n");
        sb.append("-----   Producte   -----|-- €/u --|-- IVA --|-- # --|-- Total --\n");
        int iva21 = 0;
        int iva10 = 0;
        int iva2 = 0;
        for(SaleLine sl : s.getLines()){
            afegirLiniaATiquet(sb,sl,iva21,iva10,iva2);
        }
        for(int i = 0; i < 64; ++i) sb.append("-");
        sb.append("\n");
        for(int i = 0; i < 53; ++i) sb.append(" ");
        sb.append(s.getTotal()).append("\n");
        sb.append("Pagat:                                               ");
        sb.append(canvi+s.getTotal()).append("\n");
        sb.append("Canvi:                                               ");
        sb.append(canvi);
        this.currentSaleTiquet = sb.toString();
    }


    public int getTotalTorn(){
        int total = 0;
        for(Sale l : ventesRealitzades) {
            if(l.getPaymentForm().equals("efectiu")) total += l.getTotal();
        }
        return total+initialCash;
    }

    public void createPercDiscount(String type, int amount) {
        Discount discPerc = new Discount(type, amount);
        discCollection.add(discPerc);
    }

    public void createMxNDisc(String type,String conjuntAAplicar, int m, int n) {
        Discount discMxN = new Discount(type,conjuntAAplicar, m, n);
        discCollection.add(discMxN);
    }


    public void addTypeDiscount(String tipoDiscount, int discount, String tipoProd) {
        if (currentGestorName == null) throw new IllegalStateException("No hi ha cap sessio de gestor iniciada");
        Discount disc = new Discount(tipoProd, discount, this.shop, tipoDiscount);
        this.setDiscountCollection.addSetDiscount(disc);
        Boolean aux = setDiscountCollection.existSetDiscount(tipoProd, tipoDiscount);
        if (!aux) throw new IllegalStateException("No s'ha afegit el descompte");
    }

    public void addTypeDiscountMXN( String setProducts, int m, int n, String tipoDescompte) {
        if (currentGestorName == null) throw new IllegalStateException("No hi ha cap sessio de gestor iniciada");
        Discount disc = new Discount(setProducts, m, n, this.shop,  tipoDescompte);
        this.setDiscountCollection.addSetDiscount(disc);
        Boolean aux = setDiscountCollection.existSetDiscount(setProducts, tipoDescompte);
        if (!aux) throw new IllegalStateException("No s'ha afegit el descompte");
    }

    public boolean getDiscountBySetProduct(String setProduct, String type) {
        return setDiscountCollection.getSetDiscount(setProduct, this.shop, type);
    }

    public void deleteLine(String nomProd) {
        currentSale.deleteLine(nomProd);
    }

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
        if (this.currentSaleAssistantName != null) throw new IllegalStateException("Un venedor no pot visualitzar l'historial.");
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

    public void createCjtDiscount(String type, String conjuntAAplicar, int amount) {
        Discount discPerc = new Discount(type, conjuntAAplicar, amount);
        discCollection.add(discPerc);
    }



    public void finishSale(){
        this.historicSales.addSale(this.currentSale, this.currentSaleAssistantName, this.currentDate);
        this.currentSale = null;
    }

    public void getSetDiscountList(String type) {
        llista = setDiscountCollection.SetDiscountList(type);
    }

    public String getLlista() {
        return llista;
    }






    public void afegirRegalCollection(String regal){
        Discount d = new Discount();
        d.setTypeOfDiscount("regal");
        Product p = productsService.findByName(regal);
        d.setRegal(p);
        regalCollection.add(d);
    }



    public String visualitzaRegals() {
        int size = CollectionRegal.size();
        StringBuilder sb = new StringBuilder();
        sb.append("Productes que tenen regals:");
        for (int i = 0; i < size; ++i) {
            ArrayList<String> repes = new ArrayList<>();
            sb.append("\nPer la compra de ");
            sb.append(CollectionRegal.get(i).get(0));
            sb.append(" s'obté de regal");

            int tam = CollectionRegal.get(i).size();
            for (int j = 1; j < tam; ++j) {
                int freq = Collections.frequency(CollectionRegal.get(i), CollectionRegal.get(i).get(j));
                if (freq > 1) {
                    if (!repes.contains(CollectionRegal.get(i).get(j))) {
                        repes.add(CollectionRegal.get(i).get(j));
                        if (j > 1) sb.append(",");
                        sb.append(" ").append(CollectionRegal.get(i).get(j)).append(" (" + freq + ")");
                    }
                }
                else {
                    if (j > 1) sb.append(",");
                    sb.append(" ").append(CollectionRegal.get(i).get(j));
                }
            }
        }

        return sb.toString();
    }

    public void getAllSetDiscountList() {
        llista = setDiscountCollection.allSetDiscountList();
    }

    public void createLogin(String tipusLogin, String name, String password) {
        UsersCollection.addLogin(tipusLogin, name, password);
    }

    public boolean existsLogin(String tipusLogin, String name) {
        if  (!UsersCollection.checkLogin(tipusLogin, name))
            throw new IllegalStateException("No existeix un " + tipusLogin + " amb el nom " + name);;
        return true;
    }

    public void getListLogins(String tipoLogin) {
        llista = UsersCollection.getListLogins(tipoLogin);
    }

    public void getAllListLogins() {
        llista = UsersCollection.getAllListLogins();
    }
    public void afegirRegalCollection(String nomProd, String nomRegal) {
        ArrayList<String> regals = new ArrayList<>();
        regals.add(nomProd);
        String[] out = nomRegal.split(", ");
        int number;
        for (int i = 0; i < out.length; ++i){
            if (out[i].contains("(")) {
                for (int j = 0; j < out[i].length(); ++j) {
                    char c = '(';
                    if (c == out[i].charAt(j)) {
                        number = Character.getNumericValue(out[i].charAt(j + 1));
                        out[i] = out[i].substring(0, out[i].length() - 4);
                        while (number > 1) {
                            regals.add(out[i]);
                            --number;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < out.length; ++i){
            regals.add(out[i]);
        }
        CollectionRegal.add(regals);

    }


    public void addProductByBarCode(int barCode, int amount) {
        if (currentSale == null) throw new IllegalStateException("No hi ha cap venta iniciada");
        Product p = productsService.findByBarCode(barCode);
        String nomP = p.getName();
        boolean esRegal = esRegal(nomP);
        boolean teRegals = teRegals(nomP);
        if (esRegal){
            boolean acabat = false;
            while(amount > 0 && !acabat){
                currentSale.addRegal(p);
                --amount;
                if (!esRegal(nomP)) acabat = true;
            }
        }
        else if (teRegals){
            updateRegals(nomP);
        }

        if ((!esRegal && !teRegals) || amount > 0){
            currentSale.addNProducts(p, amount);
            applyDiscount(p);}

    }


    public boolean esRegal(String nomP) {
        ArrayList<String> prods = new ArrayList<>();
        for (int i = 0; i < CollectionRegal.size(); ++i) {
            for (int j = 1; j < CollectionRegal.get(i).size(); ++j) {
                if (nomP.equals(CollectionRegal.get(i).get(j))) {
                    prods.add(CollectionRegal.get(i).get(0));
                }
            }
        }
        if (currentSale.potAfegir(prods, nomP)) return true;
        else return false;
    }

    public boolean teRegals(String nomP){
        for (int i = 0; i < CollectionRegal.size(); ++i)
            if (nomP.equals(CollectionRegal.get(i).get(0))) return true;
        return false;
    }

    public void updateRegals(String nomP){
        ArrayList<String> regalsP = new ArrayList<>();
        for (int i = 0; i < CollectionRegal.size(); ++i) {
            if (nomP.equals(CollectionRegal.get(i).get(0)))
                for (int j = 1; j < CollectionRegal.get(i).size(); ++j) {
                    regalsP.add(CollectionRegal.get(i).get(j));
                }
        }
        regalsP.retainAll(currentSale.getNoRegals());
        for (int i = 0; i < regalsP.size(); ++i)
            currentSale.setToRegal(regalsP.get(i));
    }

    public void loginSistema(String nom, String password) {
        System.out.println("AQUIIIIIII--->"+UsersCollection.getRol(nom,password));

        if (!UsersCollection.usuariCorrecte(nom,password))
            throw new IllegalStateException("El nom o la contrasenya és incorrecte");
        UsersCollection.addUserActive(nom, password);
        if (UsersCollection.getRol(nom,password).equals("gestor")) gestorLogin(nom);
        else login(nom);

    }

    public boolean userActive(String tipusLogin, String name, String password) {
        return UsersCollection.checkUserActive(tipusLogin, name, password);
    }


    public void getActiveUsers() {
        llista = UsersCollection.getActiveUsers();
    }

    public void logoutSistema(String nom) {
        UsersCollection.logout(nom);
    }

    public boolean userNotActive(String tipusLogin, String name) {
        return UsersCollection.checkUserNotActive(tipusLogin,name);
    }
}

