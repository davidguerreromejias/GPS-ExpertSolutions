package edu.upc.essi.gps.ecommerce;

import cucumber.api.java.ca.Aleshores;
import cucumber.api.java.ca.Donat;
import cucumber.api.java.ca.Quan;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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

    @Donat("^s'ha fet una venta en efectiu per valor de (\\d+)€$")
    public void ferVenta(int v) throws Throwable {
        this.posController.startSale();
        this.posController.getCurrentSale().setTotalPrice(v);
        this.posController.afegirVenta();
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
        assertEquals(0,this.posController.getDifTancarTorn());
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

    @Donat("^que hi ha un descompte definit en el sistema de tipus (.*) d'un (\\d+)%$")
    public void createPercDiscount(String type, int amountDisc) throws Throwable {
        this.posController.createPercDiscount(type, amountDisc);
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
        assertEquals(cjt, sl.getDiscount().getSubType());
    }

   /* @Aleshores("^línia de venta (\\d+) és de (\\d+) unitats de \"([^\"]*)\" a (\\d+)€ cada una amb un descompte de tipus " +
            "\"([^\"]*)\" del (.*) per pertànyer a \"([^\"]*)\" per un total de (\\d+)€$")
    public void línia_de_venta_és_de_unitats_de_a_€_cada_una_per_un_total_de_€_amb_descompte_mxn(int lineNumber, int units, String productName,
                                                                                             int unitPrice, String typeDesc, String amountDesc,
                                                                                             String cjt,int totalPrice) throws Throwable {
        SaleLine sl = this.posController.getCurrentSale().getLines().get(lineNumber - 1);
        assertEquals(units,sl.getAmount());
        assertEquals(unitPrice,sl.getUnitPrice());
        assertEquals(totalPrice, sl.getTotalPrice());
        assertEquals(productName,sl.getProductName());
        assertEquals(typeDesc,sl.getDiscount().getTypeOfDiscount());
        assertEquals(amountDesc, sl.getDiscount().getAmountDiscount());
        assertEquals(cjt, sl.getDiscount().getSubType());
    }*/

    @Donat("^la botiga \"([^\"]*)\"")
    public void createHistorial(String shop) throws Throwable{
        this.posController = new PosController(shop);
        //this.posController.createHistorial(shop);
    }

    @Quan("^a la botiga \"([^\"]*)\" la venta (\\d+) amb data \"([^\"]*)\" ha estat pagada i finalitzada")
    public void saveSale(int postNumber, String shop, String data){
        Sale x = new Sale(shop, postNumber, data);
        //this.posController.setSaleHistorial(x, data);
        assertEquals(postNumber, (int) this.posController.getVentesRealitzadesId(postNumber));
    }

    @Aleshores("^la venta (\\d+) es guarda al historial amb data \"([^\"]*)\"")
    public void saveInHistorial(int postNumber, String data){
        //this.posController.setSaleHistorial(this.posController.getVentesRealitzadesSale(postNumber), data);
        assertEquals(postNumber, this.posController.getCurrentSale().getPosNumber());
        assertEquals(data, this.posController.getCurrentDate());
    }

    @Aleshores("^la venta te un vendor \"([^\"]*)\"")
    public void checkSaleAssistant(String saleAssistant){
        assertEquals(saleAssistant, this.posController.getCurrentSaleAssistantName());
    }


    @Donat("^que hi ha un descompte de tipus (.*) on m és (\\d+) i n és (\\d+) definit en el sistema pels productes de tipus \"([^\"]*)\"$")
    public void createMxNDiscount(String type,int mvalue, int nvalue,String conjunt) throws Throwable {
        this.posController.createMxNDisc(type, conjunt, mvalue, nvalue);
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
        assertEquals(productName,sl.getProductName());
        assertEquals(typeDesc,sl.getDiscount().getTypeOfDiscount());
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
        this.posController.addTypeDiscount(tipoDescompte, discount, setProducts);
    }

    @Quan("^afegeixo un descompte del tipus (.*) pels productes de tipus (.*) tal que quan en compres (\\d+) en pagues (\\d+)$")
    public void addSetDiscountMXN(String tipoDescompte, String setProducts, int m, int n) throws Throwable {
        this.posController.addTypeDiscountMXN(setProducts, m, n, tipoDescompte);
    }

    @Aleshores("existeix un descompte del tipus (.*) pels productes de tipus (.*) tal que quan en compres (\\d+) en pagues (\\d+)$")
    public void checkSetDiscountAdded(String tipusDescompte, String setProduct, int m, int n) throws Throwable {
        assertEquals(true, this.posController.getDiscountBySetProduct(setProduct, tipusDescompte));
    }

    @Aleshores("^existeix un descompte del tipus (.*) al sistema del (\\d+)% pels productes de (.*)$")
    public void checkSetDiscountAdded(String tipusDescompte, int discount, String setProduct) throws Throwable {
        assertEquals(true, this.posController.getDiscountBySetProduct(setProduct, tipusDescompte));
    }

    @Quan("que en (.*) ha iniciat sessió com a gestor$")
    public void gestorLogin(String gestorName) throws Throwable {
        tryCatch(() -> this.posController.gestorLogin(gestorName));
    }


    @Donat("que hi ha un descompte definit de tipus (.*) d'un (\\d+)%$")
    public void set_discPerc(String tipus, int amount){
        this.posController.setDiscPerc(tipus, amount);
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
    @Quan("apreto sobre el descompte 50% existent pel producte (.*) de la venta$")
    public void assignarDescompte(String nomP) throws Throwable{
        tryCatch(() -> this.posController.assignaDescompte(nomP));
    }

    @Quan("vull obtenir un llistat dels quadraments invàlids$")
    public void obtenirLlistatQuadraments() throws Throwable {
        tryCatch(() -> this.posController.getQuadramentsInvalids());
    }

    @Quan ("vull obtenir un llistat dels descomptes per tipus de productes i de tipus de descompte (.*) que hi ha actius al sistema$")
    public void obtenirLListatDescomptesPerTipus(String type) throws Throwable{
        tryCatch(() -> this.posController.getSetDiscountList(type));
    }

    @Quan ("vull obtenir un llistat dels descomptes per tipus de productes que hi ha actius al sistema$")
    public void obtenirTotLListatDescomptesPerTipus() throws Throwable{
        tryCatch(() -> this.posController.getAllSetDiscountList());
    }

    @Aleshores("el sistema em mostra un llistat descomptes per tipus de productes que hi ha actius al sistema$")
    public void checkLListatDescomptesPerTipus(String msg){
        assertEquals(msg, this.posController.getAllSetDiscountList());

    }

    @Aleshores("el sistema em mostra un llistat descomptes per tipus de productes i de tipus de descompte (.*) que hi ha actius al sistema$")
    public void checkLListatDescomptesPerTipus(String type, String msg){
        assertEquals(msg, this.posController.getSetDiscountList(type));
    }

    @Aleshores("el sistema em mostra un llistat de quadraments invàlids que és$")
    public void checkQuadraments(String msg){
        assertEquals(msg, this.posController.getQuadramentsInvalids());
    }

    @Aleshores("el producte (.*) val (\\d+)€$")
    public void comprova_preu(String nomP, int preuP){
        List<SaleLine> lines = this.posController.getCurrentSale().getLines();
        for(SaleLine l : lines) {
            if(l.getProductName()==nomP) assertEquals(preuP, l.getTotalPrice());
        }
    }

    @Aleshores("el sistema m'informa que el quadrament de la caixa és invàlid i la diferència és de (\\d+)€$")
    public void checkQuadramentInvalid(int dif) throws Throwable{
        int x = this.posController.getDifTancarTorn();
        if(x < 0) x = -x;
        assertEquals(dif,x);
        this.posController.tancarTorn();
    }

    @Donat("que el producte amb codi de barres (.*) ha estat afegit a la venta actual amb la quantitat (\\d+)")
        public void producte_afegit_a_la_venta_actual(int barCode,int amount) throws Throwable{
            this.posController.addProductByBarCode(barCode, amount);
        }

    @Donat("^en \"([^\"]*)\" ha tancat el seu torn amb un quadrament invàlid de (\\d+)€ negatius$")
    public void afegirTornAmbQuadramentInvalidNeg(String assistant, int dif) throws Throwable{
        login(assistant, 0);
        saleStarted();
        producte_afegit_a_la_venta_actual(1111111, 2);
        cashPayment(20, "efectiu");
        tryTancarTorn(15);
        this.posController.tancarTorn();
    }

    @Donat("^en \"([^\"]*)\" ha tancat el seu torn amb un quadrament invàlid de (\\d+)€$")
    public void afegirTornAmbQuadramentInvalid(String assistant, int dif) throws Throwable{
        login(assistant, 0);
        saleStarted();
        producte_afegit_a_la_venta_actual(1111111,2);
        cashPayment(20,"efectiu");
        tryTancarTorn(30);
        this.posController.tancarTorn();
    }

    @Donat("que la venta ha sigut pagada i guardada al historial.")
        public void saleOfX() throws Throwable{
            this.posController.finishSale();
    }

    @Donat("^que hi ha un descompte de tipus (.*) definit en el sistema pels productes de tipus (.*) d'un (\\d+)%$")
    public void createCjtDiscount(String type, String subType, int amountDisc) throws Throwable {
        this.posController.createCjtDiscount(type, subType, amountDisc);
    }


    @Quan("el gestor \"([^\"]*)\" introdueix la data \"([^\"]*)\"")
    public void visualitzaXData (String gestor, String data) throws Throwable{
        this.posController.visualitzaVentesPerData(data);
    }

    @Quan("el gestor \"([^\"]*)\" introdueix el venedor \"([^\"]*)\"")
    public void visualitzaXVenedor (String gestor, String venedor) throws Throwable{
        this.posController.visualitzaVentesPerVenedor(venedor);
    }

    @Aleshores("el resultat de la cerca per venedor és$")
    public void checkSalesXVenedor(String msg){
        assertEquals(msg, this.posController.getMessage());
    }

    @Aleshores("el resultat de la cerca per data és$")
    public void checkSalesXData(String msg){
        assertEquals(msg, this.posController.getMessage());
    }

    @Quan("el gestor \"([^\"]*)\" visualitza tot l'historial")
    public void visualitzaXTot (String gestor) throws Throwable{
        this.posController.visualitzaTotHistorial();
    }

    @Aleshores("el resultat de tot l'historial és$")
    public void checkSalesHistorial(String msg){
        assertEquals(msg, this.posController.getMessage());
    }

    @Quan("apreto aplicar descompte de tipus percentatge del (\\d+)% pel producte (.*)$")
    public void aplicarDescomptePerc(int amount, String nomP){
        this.posController.aplicarDescomptePerc(amount, nomP);
    }

    @Donat("que hi ha un descompte definit de tipus regal que inclou (.*)$")
    public void afegirRegalCollection(String regal){
        this.posController.afegirRegalCollection(regal);
    }

    @Quan("apreto aplicar descompte de tipus regal que inclou (.*) pel producte (.*)$")
    public void afegirRegal(String regal, String nomP){
        this.posController.afegirRegal(regal, nomP);
    }

    @Aleshores("els descomptes per productes son$")
    public void checkDiscPercProduct(String msg){
        assertEquals(msg, this.posController.visualitzaDescompteProducte());
    }

    @Aleshores("els productes que tenen regals son$")
    public void checkRegals(String msg){
        assertEquals(msg, this.posController.visualitzaRegals());
    }
}
