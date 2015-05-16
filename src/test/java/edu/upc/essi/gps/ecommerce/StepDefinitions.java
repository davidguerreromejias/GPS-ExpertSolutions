package edu.upc.essi.gps.ecommerce;

import cucumber.api.java.ca.Aleshores;
import cucumber.api.java.ca.Donat;
import cucumber.api.java.ca.Quan;

import java.util.List;

import static org.junit.Assert.*;

public class StepDefinitions {

    private ProductsService productsService = new ProductsService(new ProductsRepository());
    private Exception exception;
    private PosController posController;
    private String change;

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
        //this.exception = new RuntimeException("No es pot cobrar una venta sense cap producte");
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
    public void tancarTorn(int n) throws Throwable {
        tryCatch(() -> this.posController.tancarTorn(n));
    }

    @Aleshores("^el sistema m'informa que el quadrament de la caixa és invàlid$")
     public void checkQuadramentInvalid() throws Throwable {
        assertFalse(this.posController.getTancamentUltimTorn());
    }

    @Aleshores("^el sistema confirma el quadrament i tanca el torn$")
    public void checkQuadramentValid() throws Throwable {
        assertTrue(this.posController.getTancamentUltimTorn());
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

    @Quan("^inicio el torn al tpv com a \"([^\"]*)\"$")
     public void login(String saleAssistantName) throws Throwable {
        tryCatch(() -> this.posController.login(saleAssistantName));
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

    @Donat("^un producte amb nom \"([^\"]*)\", preu (\\d+)€, iva (\\d+)% i codi de barres (\\d+)$")
    public void productCreated(String productName, int price, int vatPct, int barCode) throws Throwable {
        this.productsService.newProduct(productName, price, vatPct, barCode);
    }

    @Quan("^afegeixo el producte de codi de barres (\\d+) a la venta$")
    public void addProductByBarCode(int barCode) throws Throwable {
        this.posController.addProductByBarCode(barCode);
    }

    @Quan("^afegeixo el producte de codi de barres (\\d+) a la venta amb quantitat (\\d+)$")
    public void addProductByBarCode(int barCode, int amount) throws Throwable {
        this.posController.addProductByBarCode(barCode, amount);
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
        tryCatch(() -> this.change = this.posController.cashPayment(delivered, paymentForm));
    }

    @Aleshores("^el tpv mostra el següent: (.*)$")
    public void checkChange(String expectedChange) throws Throwable {
        assertEquals(expectedChange, change);
    }

    @Donat("^que hi ha un descompte definit en el sistema de tipus (.*) d'un (\\d+)%$")
    public void createPercDiscount(String type, int amountDisc) throws Throwable {
        this.posController.createPercDiscount(type, amountDisc);
    }

    @Quan("^apreto sobre el descompte (\\d+)% existent$")
    public void applyDiscountPerc(int amount) throws Throwable{
        this.posController.applyPercDiscount(amount);
    }

    @Aleshores("^línia de venta (\\d+) és de (\\d+) unitats de \"([^\"]*)\" a (\\d+)€ cada una amb un descompte de tipus " +
            "\"([^\"]*)\" del (\\d+)% per un total de (\\d+)€$")
    public void línia_de_venta_és_de_unitats_de_a_€_cada_una_per_un_total_de_€_amb_descompte(int lineNumber, int units, String productName,
                                                                                             int unitPrice, String typeDesc, int amountDesc,
                                                                                             int totalPrice) throws Throwable {
        SaleLine sl = this.posController.getCurrentSale().getLines().get(lineNumber - 1);
        assertEquals(units,sl.getAmount());
        assertEquals(unitPrice,sl.getUnitPrice());
        assertEquals(totalPrice, sl.getTotalPrice());
        assertEquals(productName,sl.getProductName());
        assertEquals(typeDesc,sl.getDiscount().getTypeOfDiscount());
        assertEquals(amountDesc, sl.getDiscount().getAmountDiscount());
    }

    @Donat("^la botiga \"([^\"]*)\"")
    public void createHistorial(String shop) throws Throwable{
        this.posController = new PosController(shop);
        this.posController.createHistorial(shop);
    }

    @Quan("^la venta (\\d+) ha estat pagada i finalitzada")
    public void saveSale(int postNumber){
        assertEquals(postNumber, (int) this.posController.getVentesRealitzadesId(postNumber));
    }

    @Aleshores("^la venta (\\d+) es guarda al historial amb data \"([^\"]*)\"")
    public void saveInHistorial(int postNumber, String data){
        this.posController.setSaleHistorial(this.posController.getVentesRealitzadesSale(postNumber), data);
        assertEquals(postNumber, this.posController.getCurrentSale().getPosNumber());
        assertEquals(data, this.posController.getCurrentDate());
    }

    @Aleshores("^la venta te un vendor \"([^\"]*)\"")
    public void checkSaleAssistant(String saleAssistant){
        assertEquals(saleAssistant, this.posController.getCurrentSaleAssistantName());
    }

    @Quan("^apreto sobre el descompte (\\d+)% existent una altra vegada$")
    public void StopApplyingDiscount(int amount) throws Throwable{
        this.posController.StopApplyingDiscount();
    }

    @Donat("^que hi ha un descompte definit en el sistema de tipus (.*) on m és (\\d+) i n és (\\d+)$")
    public void createMxNDiscount(String type,int mvalue, int nvalue) throws Throwable {
        this.posController.createMxNDisc(type, mvalue, nvalue);
    }

    @Quan("^apreto sobre el descompte m x n (\\d+) x (\\d+) existent$")
    public void applyDiscountMxN(int m, int n) throws Throwable{
        this.posController.applyMxNDiscount(m, n);
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

    @Donat("^que s'ha afegit un descompte del (\\d+)% als productes de (.*)$")
    public void addSetDiscountRerefons(int discount, String setProducts) throws Throwable {
        this.posController.addTypeDiscount(discount, setProducts);
    }

    @Quan("^afageix un descompte del (\\d+)% als productes de (.*)$")
    public void addSetDiscount(int discount, String setProducts) throws Throwable {
        this.posController.addTypeDiscount(discount, setProducts);
    }

    @Quan("^borrar el descompte dels productes de (.*)$")
    public void deleteSetDiscount(String setProducts) throws Throwable {
        this.posController.deletedTypeDiscount(setProducts);
    }

    @Aleshores("^existeix un descompte al sistema del (\\d+)% pels productes de (.*)$")
    public void checkSetDiscountAdded(int discount, String setProduct) throws Throwable {
        assertEquals(discount, (int) this.posController.getDiscountBySetProduct(setProduct));
    }

    @Aleshores("^no existeix un descompte al sistema pels productes de (.*)$")
    public void checkSetDiscountDeleted(String setProduct) throws Throwable {
        assertEquals(false, this.posController.existSetProduct(setProduct));
    }

    @Quan("que en (.*) ha iniciat sessio com a gestor$")
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

    @Aleshores("el producte (.*) val (\\d+)€$")
    public void comprova_preu(String nomP, int preuP){
        List<SaleLine> lines = this.posController.getCurrentSale().getLines();
        for(SaleLine l : lines) {
            if(l.getProductName()==nomP) assertEquals(preuP, l.getTotalPrice());
        }
    }
}
