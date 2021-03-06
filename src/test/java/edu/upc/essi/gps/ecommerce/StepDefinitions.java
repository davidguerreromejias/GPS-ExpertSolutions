package edu.upc.essi.gps.ecommerce;

import cucumber.api.PendingException;
import cucumber.api.java.ca.Aleshores;
import cucumber.api.java.ca.Donat;
import cucumber.api.java.ca.I;
import cucumber.api.java.ca.Quan;

import java.util.*;
import java.lang.Math;

import static org.junit.Assert.*;

public class StepDefinitions {

    private ProductsService productsService = new ProductsService(new ProductsRepository());
    private Exception exception;
    private PosController posController;

    public void tryCatch(Runnable r){
        try {
            r.run();
            this.exception = null;
        } catch (Exception e){
            this.exception = e;
        }
    }

    @Aleshores("^obtinc un error que diu: \"([^\"]*)\"$")
    public void checkErrorMessage(String msg) throws Throwable {
        assertNotNull(this.exception);
        assertEquals(msg, this.exception.getMessage());
    }

    @Aleshores("^hi ha una sessió iniciada pel gestor (.*)$")
    public void checkGestorName(String gestorName) throws Throwable {
        assertEquals(gestorName, this.posController.getCurrentGestorName());
    }

    @Donat("^que estem al tpv número (\\d+) de la botiga \"([^\"]*)\"$")
     public void setupPos(int posNumber, String shop) throws Throwable {
        this.posController = new PosController(shop,posNumber, productsService);
    }

    @Donat("^al iniciar el torn hi havia (\\d+)€ a la caixa$")
    public void setInitCash(int x){
        this.posController.setInitialCash(x);
    }

    @Quan("^vull tancar el torn i a la caixa hi ha (\\d+)€$")
    public void tryTancarTorn(int n) throws Throwable {
        tryCatch(() -> this.posController.tryTancarTorn(n));
    }

    @Aleshores("^el sistema confirma el quadrament i tanca el torn$")
    public void checkQuadramentValid() throws Throwable {
        assertEquals(0, this.posController.getDifTancarTorn());
    }

    @Aleshores("^el tpv està en ús per en \"([^\"]*)\"$")
    public void checkCurrentSaleAssistantName(String saleAssistantName) throws Throwable {
        assertEquals(saleAssistantName, this.posController.getCurrentSaleAssistantName());
    }

    @Aleshores("^la venta actual és de'n \"([^\"]*)\" al tpv (\\d+) de la botiga \"([^\"]*)\"$")
    public void checkCurrentSaleData(String saleAssistant, int posNumber, String shop) throws Throwable {
        Sale s = this.posController.getCurrentSale();
        assertNotNull(s);
        assertEquals(shop, s.getShop());
        assertEquals(posNumber, s.getPosNumber());
        assertEquals(saleAssistant, s.getSaleAssistantName());
    }

    @Aleshores("^el sistema imprimeix$")
    public void checkTiquet(String t) throws Throwable {
        assertEquals(t, this.posController.getCurrentTiquet());
    }


    @Quan("^inicio el torn al tpv com a \"([^\"]*)\" i hi ha (\\d+)€ a la caixa$")
     public void login(String saleAssistantName, int d) throws Throwable {
        tryCatch(() -> this.posController.login(saleAssistantName));
        this.posController.setInitialCash(d);
    }

    @Quan("^indico que no vull repetir el quadrament$")
    public void repetirQuadrament(){

    }

    @Quan("^indico que vull repetir el quadrament i a la caixa hi ha (\\d+)€$")
    public void repetirQuadrament(int x) throws Throwable {
        tryCatch(() -> this.posController.tryTancarTorn(x));
    }

    @Donat("^que no hi ha un torn iniciat$")
    public void checkNoHiHaTorn() throws Throwable {
        assertNull(this.posController.getCurrentSaleAssistantName());
    }

    @Quan("inicio la sessio com a (.*)$")
    public void loginGestor(String gestorName) throws Throwable {
        tryCatch(() -> this.posController.gestorLogin(gestorName));
    }

    @Donat("^que en \"([^\"]*)\" ha iniciat el torn al tpv$")
    public void hasLoggedIn(String saleAssistantName) throws Throwable {
        this.posController.login(saleAssistantName);
    }
    @Donat("^que en \"([^\"]*)\" ha iniciat un altre torn al tpv$")
    public void newHasLoggedIn(String saleAssistantName) throws Throwable {
        this.posController.login(saleAssistantName);
    }
    @Donat("^que en \"([^\"]*)\" ha tancat el torn al tpv$")
    public void tencaTorn(String saleAssistantName) throws Throwable {
        this.posController.setCurrentSaleAssistantName(null);
    }
    @Donat("que en \"([^\"]*)\" ha iniciat sessio$")
    public void hasLoggedInSession(String gestorName) throws Throwable {
        tryCatch(() -> this.posController.gestorLogin(gestorName));
    }

    @Quan("^inicio una nova venta$")
    public void tryStartSale() throws Throwable {
        tryCatch(() -> this.posController.startSale());
    }

    @Donat("^que hi ha una venta iniciada$")
    public void saleStarted() throws Throwable {
        this.posController.startSale();
    }

    @Donat("^que hi ha una venta iniciada en data \"([^\"]*)\"$")
    public void saleStartedOnDate(String data) throws Throwable {
        this.posController.setCurrentDate(data);
        this.posController.startSale();
    }

    @Donat("^que hi ha una venta pagada$")
    public void salePayed() throws Throwable {
        this.posController.startSale();
        this.posController.salePayed();
    }

    @Donat("^un producte amb nom \"([^\"]*)\", preu (\\d+)€, iva (\\d+)% i codi de barres (\\d+) i que pertany als tipus \"([^\"]*)\"$")
    public void productCreated(String productName, int price, int vatPct, int barCode, String tipus) throws Throwable {
        this.productsService.newProduct(productName, price, vatPct, barCode, tipus);
    }

    @Quan("^afegeixo el producte de codi de barres (\\d+) a la venta$")
    public void addProductByBarCode(int barCode) throws Throwable {
        this.posController.addProductByBarCode(barCode);
    }

    @Quan("^afegeixo el producte de codi de barres (\\d+) a la venta amb quantitat (\\d+)$")
    public void addProductByBarCode(int barCode, int amount) throws Throwable {
        this.posController.addProductByBarCode(barCode, amount);
    }

    @Quan("^busco un producte que es digui \"([^\"]*)\"$")
    public void buscarProductes(String s) throws Throwable {
        this.posController.buscarProductes(s);
    }

    @Quan("^afegeixo el (\\d+) element dels resultats a la venta amb quantitat (\\d+)$")
    public void afegirIessimProducteDeCercaAVenta(int i, int q) throws Throwable {
        long p = this.posController.getIndexIessimDeCerca(i);
        this.posController.addProductById(p, q);
    }

    @Quan("^afegeixo el producte de nom \"([^\"]*)\" a la venta amb quantitat (\\d+)$")
    public void addProductByName(String nom, int amount) throws Throwable {
        this.posController.addProductByName(nom, amount);
    }

    @Donat("^que he afegit el producte de codi de barres (\\d+) a la venta$")
    public void productByBarCodeAdded(int barCode) throws Throwable {
        this.posController.addProductByBarCode(barCode);
    }

    @Aleshores("^la venta té (\\d+) (?:línia|línies)$")
    public void la_venta_té_n_linies(int expectedNumberOfLines) throws Throwable {
        assertEquals(expectedNumberOfLines, this.posController.getCurrentSale().getLines().size());
    }

    @Aleshores("^el resultat de la cerca és$")
    public void checkResultatCercaProductes(String msg) throws Throwable {
        assertEquals(this.posController.getResultatCercaProductes(), msg);
    }

    @Aleshores("^línia de venta (\\d+) és de (\\d+) unitats de \"([^\"]*)\" a (\\d+)€ cada una per un total de (\\d+)€$")
    public void línia_de_venta_és_de_unitats_de_a_€_cada_una_per_un_total_de_€(int lineNumber, int units, String productName, int unitPrice, int totalPrice) throws Throwable {
        SaleLine sl = this.posController.getCurrentSale().getLines().get(lineNumber - 1);
        assertEquals(units,sl.getAmount());
        assertEquals(unitPrice,sl.getUnitPrice());
        assertEquals(totalPrice,sl.getTotalPriceRaw());
        assertEquals(productName, sl.getProductName());
    }

    @Aleshores("^la última línia de la venta és de (\\d+) unitats de \"([^\"]*)\" a (\\d+)€ cada una per un total de (\\d+)€")
    public void línia_de_venta_és_de_unitats_de_a_€_cada_una_per_un_total_de_€(int units, String productName, int unitPrice, int totalPrice) throws Throwable {
        List<SaleLine> lines = this.posController.getCurrentSale().getLines();
        int n = lines.size()-1;
        SaleLine sl = lines.get(n);
        assertEquals(units,sl.getAmount());
        assertEquals(unitPrice,sl.getUnitPrice());
        assertEquals(totalPrice,sl.getTotalPriceRaw());
        assertEquals(productName, sl.getProductName());
    }

    @Aleshores("^el total de la venta actual és de (\\d+)€$")
    public void el_total_de_la_venta_actual_és_de_€(int saleTotal) throws Throwable {
        assertEquals(saleTotal, this.posController.getCurrentSale().getTotal());
    }

    @Aleshores("^la pantalla del client del tpv mostra$")
    public void la_pantalla_del_client_del_tpv_mostra(String msg) throws Throwable {
        assertEquals(msg, this.posController.getCustomerScreenMessage());
    }

    @Quan("^indico que el client ha entregat (\\d+)€ per a pagar en (.*)metàlic$")
    public void cashPayment(int delivered, final String paymentForm) throws Throwable {
        tryCatch(() -> this.posController.cashPayment(delivered, paymentForm));
    }

    @Quan("^indico que el client ha entregat la targeta per a pagar en (.*)targeta$")
    public void cardPayment(final String paymentForm) throws Throwable {
        tryCatch(() -> this.posController.cardPayment(paymentForm));
    }

    @Aleshores("^el tpv mostra el següent: (.*)$")
    public void checkChange(String expectedChange) throws Throwable {
        assertEquals(expectedChange, this.posController.getChange());
    }

    @Aleshores("^el tpv ens mostra el següent: (.*)$")
    public void checkCardPayment(String expectedChange) throws Throwable {
        assertEquals(expectedChange, this.posController.getChangeCard());
    }

    @Aleshores("^línia de venta (\\d+) és de (\\d+) unitats de \"([^\"]*)\" a (\\d+)€ cada una amb un descompte de tipus " +
            "\"([^\"]*)\" del \"([^\"]*)\" per pertànyer a \"([^\"]*)\" per un total de (\\d+)€$")
    public void línia_de_venta_és_de_unitats_de_a_€_cada_una_per_un_total_de_€_amb_descompte(int lineNumber, int units, String productName,
                                                                                             int unitPrice, String typeDesc, String amountDesc,
                                                                                             String cjt,int totalPrice) throws Throwable {
        SaleLine sl = this.posController.getCurrentSale().getLines().get(lineNumber - 1);
        assertEquals(units,sl.getAmount());
        assertEquals(unitPrice,sl.getUnitPrice());
        assertEquals(totalPrice, sl.getTotalPrice());
        assertEquals(productName,sl.getProductName());
        assertEquals(typeDesc,sl.getDiscount().getTypeOfDiscount());
        String aux;
        if(sl.getDiscount().getTypeOfDiscount().equals("percentatge")){
            aux = Integer.toString(sl.getDiscount().getAmountDiscount())+ "%";

        }
        else {
            aux = Integer.toString(sl.getDiscount().getM())+"x"+Integer.toString(sl.getDiscount().getN());
        }
        assertEquals(amountDesc,aux);
        assertEquals(cjt, sl.getDiscount().getConjuntAAplicar());
    }

    @Donat("^la botiga \"([^\"]*)\"")
    public void createHistorial(String shop) throws Throwable{
        this.posController = new PosController(shop);
    }

    @Quan("^a la botiga \"([^\"]*)\" la venta (\\d+) amb data \"([^\"]*)\" ha estat pagada i finalitzada")
    public void saveSale(int postNumber, String shop, String data){
        Sale x = new Sale(shop, postNumber, data);
        assertEquals(postNumber,this.posController.getVentesRealitzadesId(postNumber));
    }

    @Aleshores("^la venta (\\d+) es guarda al historial amb data \"([^\"]*)\"")
    public void saveInHistorial(int postNumber, String data){
        assertEquals(postNumber, this.posController.getCurrentSale().getPosNumber());
        assertEquals(data, this.posController.getCurrentDate());
    }

    @Aleshores("^la venta te un vendor \"([^\"]*)\"")
    public void checkSaleAssistant(String saleAssistant){
        assertEquals(saleAssistant, this.posController.getCurrentSaleAssistantName());
    }


    @Donat("^que hi ha un descompte de tipus (.*) on m és (\\d+) i n és (\\d+) definit en el sistema pels productes de tipus \"([^\"]*)\"$")
    public void createMxNDiscount(String type,int mvalue, int nvalue,String conjunt) throws Throwable {
        tryCatch(() -> this.posController.createLogin("gestor", "Pere", "password0"));
        tryCatch(() -> this.posController.gestorLogin("Pere"));
        this.posController.addTypeDiscountMXN(type, conjunt, mvalue, nvalue);
        this.posController.logoutSistema("Pere");
    }


    @Aleshores("^línia de venta (\\d+) és de (\\d+) unitats de \"([^\"]*)\" a (\\d+)€ cada una amb un descompte de tipus " +
            "\"([^\"]*)\" de (\\d+) x (\\d+) per un total de (\\d+)€$")
    public void línia_de_venta_és_de_unitats_de_a_€_cada_una_per_un_total_de_€_amb_descompte_de_m_x_n(int lineNumber, int units, String productName,
                                                                                             int unitPrice, String typeDesc, int m, int n,
                                                                                             int totalPrice) throws Throwable {
        SaleLine sl = this.posController.getCurrentSale().getLines().get(lineNumber - 1);
        assertEquals(units,sl.getAmount());
        assertEquals(unitPrice, sl.getUnitPrice());
        assertEquals(totalPrice,sl.getTotalPrice());
        assertEquals(productName, sl.getProductName());
        assertEquals(typeDesc, sl.getDiscount().getTypeOfDiscount());
        assertEquals(m, sl.getDiscount().getM());
        assertEquals(n, sl.getDiscount().getN());
    }

    @Donat("^que estem a la botiga \"([^\"]*)\"$")
    public void loginShopAsGestor(String shop) throws Throwable {
        this.posController = new PosController(shop);
    }

    @Donat("^que s'ha afegit un descompte del tipus (.*) del (\\d+)% als productes de (.*)$")
    public void addSetDiscountRerefons(String tipoDescompte, int discount, String setProducts) throws Throwable {
        this.posController.addTypeDiscount(tipoDescompte, discount, setProducts);
    }

    @Quan("^afegeixo un descompte del tipus (.*) del (\\d+)% als productes de (.*)$")
    public void addSetDiscount(String tipoDescompte, int discount, String setProducts) throws Throwable {
        tryCatch(() -> this.posController.addTypeDiscount(tipoDescompte, discount, setProducts));
    }

    @Donat("^que existeix un descompte del tipus (.*) del (\\d+)% als productes de (.*)$")
    public void addSetDiscount2(String tipoDescompte, int discount, String setProducts) throws Throwable {
        this.posController.addTypeDiscount(tipoDescompte, discount, setProducts);
    }

    @Quan("^afegeixo un descompte del tipus (.*) pels productes de tipus (.*) tal que quan en compres (\\d+) en pagues (\\d+)$")
    public void addSetDiscountMXN(String tipusDescompte, String setProducts, int m, int n) throws Throwable {
        this.posController.addTypeDiscountMXN(tipusDescompte, setProducts, m, n);
    }

    @Donat("^que s'ha afegit un descompte del tipus (.*) pels productes de tipus (.*) tal que quan en compres (\\d+) en pagues (\\d+)$")
    public void addSetDiscountMXN3(String tipusDescompte, String setProducts, int m, int n) throws Throwable {
        this.posController.addTypeDiscountMXN(tipusDescompte, setProducts, m, n);
    }

    @Donat("^que existeix un descompte del tipus (.*) pels productes de tipus (.*) tal que quan en compres (\\d+) en pagues (\\d+)$")
    public void addSetDiscountMXN2(String tipusDescompte, String setProducts, int m, int n) throws Throwable {
        this.posController.addTypeDiscountMXN(tipusDescompte, setProducts, m, n);
    }

    @Aleshores("existeix un descompte del tipus (.*) pels productes de tipus (.*) tal que quan en compres (\\d+) en pagues (\\d+)$")
    public void checkSetDiscountAdded(String tipusDescompte, String setProduct, int m, int n) throws Throwable {
        assertEquals(true, this.posController.getDiscountBySetProduct(setProduct, tipusDescompte));
    }

    @Aleshores("^existeix un descompte del tipus (.*) al sistema del (\\d+)% pels productes de (.*)$")
    public void checkSetDiscountAdded(String tipusDescompte, int discount, String setProduct) throws Throwable {
        assertEquals(true, this.posController.getDiscountBySetProduct(setProduct, tipusDescompte));
    }

    @Quan("és vol crear un nou login al sistema del tipus (.*) pel treballador anomenat (.*) amb el password (.*)$")
    public void login(String tipusLogin, String name, String password) throws Throwable {
        tryCatch(() -> this.posController.createLogin(tipusLogin, name, password));
    }

    @Donat("un administrador del sistema ha creat un nou login al sistema del tipus (.*) pel treballador anomenat (.*) amb el password (.*)$")
    public void adminCreateLogin(String tipusLogin, String name, String password) throws Throwable {
        tryCatch(() -> this.posController.adminCreateLogin(tipusLogin, name, password));
    }

    @Quan("que existeix un login de tipus (.*) pel treballador anomenat (.*) amb el password (.*)$")
    public void login2(String tipusLogin, String name, String password) throws Throwable {
        tryCatch(() -> this.posController.createLogin(tipusLogin, name, password));
    }

    @Aleshores("existeix un login del tipus (.*) pel treballador anomenat (.*)$")
    public void checkLoginCreated(String tipusLogin, String name) throws Throwable {
        assertEquals(true, this.posController.existsLogin(tipusLogin, name));
    }

    @Quan("elimino el producte (.*) de la venta$")
    public void eliminarLinia(String nomProd)throws Throwable{
        tryCatch(() -> this.posController.deleteLine(nomProd));
    }
    @Aleshores("la venta només li queden (\\d+) productes$")
    public void línia_de_venta_es_unitats(int numProd) throws Throwable{
        int size = this.posController.getCurrentSale().getLines().size();
        assertEquals(numProd, size);
    }

    @Quan("vull obtenir un llistat dels quadraments invàlids$")
    public void obtenirLlistatQuadraments() throws Throwable {
        tryCatch(() -> this.posController.getQuadramentsInvalids());
    }

    @Quan ("vull obtenir un llistat dels descomptes per tipus de productes i de tipus de descompte (.*) que hi ha actius al sistema$")
    public void obtenirLListatDescomptesPerTipus(String type) throws Throwable{
        tryCatch(() -> this.posController.getSetDiscountList(type));
    }

    @Quan ("vull obtenir un llistat dels noms dels (.*)")
    public void obtenirLListatLogins(String tipoLogin) throws Throwable{
        tryCatch(() -> this.posController.getListLogins(tipoLogin));
    }

    @Quan ("vull obtenir un llistat dels usuaris del sistema")
    public void obtenirAllLListatLogins() throws Throwable{
        tryCatch(() -> this.posController.getAllListLogins());
    }

    @Quan ("vull obtenir las dades de qui està amb la sessió iniciada")
    public void obtenirActiveUsers() throws Throwable{
        tryCatch(() -> this.posController.getActiveUsers());
    }

    @Quan ("un usuari accedeix al sistema posa el nom d'usuari (.*) amb el password (.*)$")
    public void loginSistema(String nom, String password) {
        tryCatch(() -> this.posController.loginSistema(nom, password));
    }

    @Donat ("que l'usuari (.*) accedeix al sistema amb el password (.*)")
    public void loginSistema6(String nom, String password) {
        tryCatch(() -> this.posController.loginSistema(nom, password));
    }

    @Quan ("en (.*) tanca sessió$")
    public void logoutSistema(String nom) {
        tryCatch(() -> this.posController.logoutSistema(nom));
        tryCatch(() -> this.posController.gestorLogOut(nom));
    }

    @Donat ("que en (.*) tanca sessió$")
    public void logoutSistema2(String nom) {
        tryCatch(() -> this.posController.logoutSistema(nom));
    }

    @Donat ("que en (.*) tanca sessió com a gestor$")
    public void logoutSistemaGestor(String nom) {
        tryCatch(() -> this.posController.logoutSistema(nom));
        tryCatch(() -> this.posController.gestorLogOut(nom));
    }

    @Donat ("que un usuari a accedit al sistema amb el nom d'usuari (.*) amb el password (.*)$")
    public void loginSistema3(String nom, String password) {
        tryCatch(() -> this.posController.loginSistema(nom, password));
    }

    @Quan ("un usuari intenta accedir al sistema amb el nom d'usuari (.*) amb el password (.*)$")
    public void loginSistema4(String nom, String password) {
        tryCatch(() -> this.posController.loginSistema(nom, password));
    }


    @Donat ("que ha iniciat sessió al sistema l'usuari (.*) amb el password (.*)")
    public void loginSistema2(String nom, String password) {
        tryCatch(() -> this.posController.loginSistema(nom, password));
    }

    @Quan ("l'usuari (.*) intenta accedir al sistema amb el password (.*)")
    public void loginSistema5(String nom, String password) {
        tryCatch(() -> this.posController.loginSistema(nom, password));
    }

    @Aleshores("el (.*) amb el nom (.*) ha iniciat sessió$")
    public void checkUserActive(String tipusLogin, String name) throws Throwable {
        assertEquals(true, this.posController.userActive(tipusLogin, name));
    }

    @Aleshores("el (.*) amb el nom (.*) ha tancat sessió$")
    public void checkUserNotActive(String tipusLogin, String name) throws Throwable {
        assertEquals(true, this.posController.userNotActive(tipusLogin, name));
    }



    @Quan ("vull obtenir un llistat dels descomptes per tipus de productes que hi ha actius al sistema$")
    public void obtenirTotLListatDescomptesPerTipus() throws Throwable {
        tryCatch(() -> this.posController.getAllSetDiscountList());
    }

    @Aleshores("el sistema em mostra un llistat descomptes per tipus de productes que hi ha actius al sistema$")
    public void checkLListatDescomptesPerTipus(String msg){
        assertEquals(msg, this.posController.getLlista());

    }

    @Aleshores("el sistema em mostra el llistat de descomptes$")
    public void checkLListatDescomptesPerTipus2(String msg){
        assertEquals(msg, this.posController.getLlista());
    }

    @Aleshores("el sistema em mostra el llistat de (.*) amb els seus respectius noms$")
    public void checkLListatLogins(String nom, String msg){
        assertEquals(msg, this.posController.getLlista());
    }

    @Donat("que en (.*) ha iniciat sessió com a gestor$")
    public void gestorLogin(String gestorName) throws Throwable {
        tryCatch(() -> this.posController.gestorLogin(gestorName));
    }

    @Aleshores("el sistema em mostra el llistat usuaris del sistema$")
    public void checkLListatLogins2(String msg){
        assertEquals(msg, this.posController.getLlista());
    }

    @Aleshores("el sistema em mostra l'usuari que està amb la sessió iniciada$")
    public void checkLListatLogins3(String msg){
        assertEquals(msg, this.posController.getLlista());
    }

    @Aleshores("el sistema em mostra un llistat de quadraments invàlids que és$")
    public void checkQuadraments(String msg) {
        assertEquals(msg, this.posController.getQuadramentsInvalids());
    }

    @Aleshores("el producte (.*) val (\\d+)€$")
    public void comprova_preu(String nomP, int preuP){
        List<SaleLine> lines = this.posController.getCurrentSale().getLines();
        for(SaleLine l : lines) {
            if(l.getProductName()==nomP) assertEquals(preuP, l.getTotalPrice());
        }
    }

    @Aleshores("el sistema mostra el missatge$")
    public void checkQuadramentInvalid(String msg) throws Throwable{
        assertEquals(msg, this.posController.getDifTancarTorn());
    }

    @Donat("que el producte amb codi de barres (.*) ha estat afegit a la venta actual amb la quantitat (\\d+)")
        public void producte_afegit_a_la_venta_actual(int barCode,int amount) throws Throwable {
        this.posController.addProductByBarCode(barCode, amount);
        }

    @Donat("^que en \"([^\"]*)\" ha tancat el seu torn amb un quadrament invàlid de (\\d+)€ negatius$")
    public void afegirTornAmbQuadramentInvalidNeg(String assistant, int dif) throws Throwable{
        login(assistant, 0);
        saleStarted();
        producte_afegit_a_la_venta_actual(1111111, 2);
        cashPayment(20, "efectiu");
        tryTancarTorn(15);
        this.posController.tancarTorn();
    }

    @Donat("^que en \"([^\"]*)\" ha tancat el seu torn amb un quadrament invàlid de (\\d+)€$")
    public void afegirTornAmbQuadramentInvalid(String assistant, int dif) throws Throwable{
        login(assistant, 0);
        saleStarted();
        producte_afegit_a_la_venta_actual(1111111,2);
        cashPayment(20, "efectiu");
        tryTancarTorn(30);
        this.posController.tancarTorn();
    }

    @Donat("^que el client ha pagat (\\d+)€ en \"([^\"]*)\"")
    public void pagarVenta(int payed, String form) throws Throwable{
        if(form.equals("efectiu")) this.posController.cashPayment(payed, form);
        else if(form.equals("targeta")) this.posController.cardPayment(form);
    }

    @Donat("que hi ha una venta pagada per un import de (\\d+)€ en data \"([^\"]*)\"")
        public void saleOfX(int preuTotal, String data) throws Throwable{
            this.posController.setCurrentDate(data);
            this.posController.startSale();
            int randomNum = (int)(Math.random() * 1000);
            this.productsService.newProduct(this.posController.getSaltString(), preuTotal, 21 , randomNum , "platja");
            this.posController.addProductByBarCode(randomNum);
            this.posController.finishSale();
    }

    @Donat("que s'ha efectuat una venta on s'han venut (\\d+) unitats del producte (\\d+) per un import de (\\d+)€")
    public void saleOfProduct(int amount,int codiB, int preuTotal) throws Throwable{
        this.posController.startSale();
        this.posController.addProductByBarCode(codiB, amount);
        this.posController.finishSale();
    }

    @Donat("^que hi ha un descompte de tipus (.*) definit en el sistema pels productes de tipus (.*) d'un (\\d+)%$")
    public void createCjtDiscount(String type, String conjuntAAplicar, int amountDisc) throws Throwable {
        tryCatch(() -> this.posController.createLogin("gestor", "Pere", "password0"));
        tryCatch(() -> this.posController.gestorLogin("Pere"));
        this.posController.addTypeDiscount(type, amountDisc, conjuntAAplicar);
        this.posController.logoutSistema("Pere");
    }


    @Quan("demana les ventes per data \"([^\"]*)\"")
    public void visualitzaXData (String data) throws Throwable{
        this.posController.visualitzaVentesPerData(data);
    }

    @Quan("vull imprimir el tiquet")
    public void createTiquet() throws Throwable{
        tryCatch(() -> this.posController.createTiquet());
    }

    @Quan("demana les ventes del venedor \"([^\"]*)\"")
    public void visualitzaXVenedor (String venedor) throws Throwable{
        this.posController.visualitzaVentesPerVenedor(venedor);
    }

    @Aleshores("el resultat de la cerca per venedor és$")
    public void checkSalesXVenedor(String msg) {
        assertEquals(msg, this.posController.getMessage());
    }

    @Aleshores("el resultat de la cerca per data és$")
    public void checkSalesXData(String msg) {
        assertEquals(msg, this.posController.getMessage());
    }

    @Quan("demana visualitzar tot l'historial")
    public void visualitzaXTot() throws Throwable{
        tryCatch(() -> this.posController.visualitzaTotHistorial());
    }

    @Quan("demana visualitzar el producte més popular")
    public void visualitzaProductPopular() throws Throwable {
        tryCatch(() -> this.posController.visualitzaProductPopular());
    }

    @Quan("demana visualitzar el producte menys popular")
    public void visualitzaProductMenysPopular() throws Throwable {
        tryCatch(() -> this.posController.visualitzaProductMenysPopular());
    }

    @Aleshores("el resultat de tot l'historial és$")
    public void checkSalesHistorial(String msg){
        assertEquals(msg, this.posController.getMessage());
    }

    @Aleshores("la llista dels productes més venuts és$")
    public void checkProductPopular(String msg) {
        assertEquals(msg, this.posController.getMessage());
    }

    @Aleshores("la llista dels productes menys venuts és$")
    public void checkProductMenysPopular(String msg){
        assertEquals(msg, this.posController.getMessage());
    }


    @Donat("que hi ha un descompte definit de tipus regal que inclou (.*)$")
    public void afegirRegalCollection(String regal){
        this.posController.afegirRegalCollection(regal);
    }


    @Aleshores("els productes que tenen regals son$")
    public void checkRegals(String msg){
        assertEquals(msg, this.posController.visualitzaRegals());
    }


    @Donat("que hi ha un descompte de tipus regal que per la compra de (.*) et regalen (.*)$")
    public void afegirRegals(String nomProd, String nomRegals){
        this.posController.afegirRegalCollection(nomProd, nomRegals);
    }

    @Quan("afegeixo un descompte de tipus regal que per la compra de (.*) et regalen (.*)$")
    public void afegirRegalsQuan(String nomProd, String nomRegals){
        this.posController.afegirRegalCollection(nomProd, nomRegals);
    }


    @Aleshores("obtinc un error que em diu$")
    public void checkErrorGestor(String msg){
        assertEquals(msg, this.posController.getMessage());
    }


    @Donat("que hi ha un descompte definit de tipus regal que per la compra de (.*) et regalen (\\d+) uds de productes de tipus (.*)$")
    public void afegirRegalByTypeAdmin(String nomP, int amount, String type){
        this.posController.afegirRegalByTypeAdmin(nomP, amount, type);
    }



    @Quan("vol afegir un descompte de tipus regal que per la compra de (.*) et regalen (\\d+) uds de productes de tipus (.*)$")
    public void afegirRegalByType(String nomP, int amount, String type){
        this.posController.afegirRegalByType(nomP, amount, type);}

    @Aleshores("els productes que tenen regals per tipus son$")
    public void checkRegalsType(String msg){
        assertEquals(msg, this.posController.visualitzaRegalsTipus());}


}
