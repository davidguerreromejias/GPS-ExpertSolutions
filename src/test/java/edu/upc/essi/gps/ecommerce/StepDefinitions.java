package edu.upc.essi.gps.ecommerce;

import cucumber.api.PendingException;
import cucumber.api.java.ca.Aleshores;
import cucumber.api.java.ca.Donat;
import cucumber.api.java.ca.I;
import cucumber.api.java.ca.Quan;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static edu.upc.essi.gps.ecommerce.Utils.*;

public class StepDefinitions {

    private ProductsService productsService = new ProductsService(new ProductsRepository());
    private List<Product> resultProducts;
    private Exception exception;
    private PosController posController;

    @And("^llisto els productes$")
    public void
    I_list_the_products() throws Throwable {
        this.resultProducts = productsService.listProducts();
    }

    @Then("^obtinc una llista amb (\\d+) elements?$")
    public void I_get_a_list_of_elements(int numberOfElements) throws Throwable {
        assertEquals(numberOfElements, resultProducts.size());
    }

    @And("^l'element número (\\d+) és el producte \"([^\"]*)\"$")
    public void the_element_at_position_is_the_product(int position, String productName) throws Throwable {
        assert (resultProducts.size() >= position);
        assertEquals(productName, resultProducts.get(position - 1).getName());
    }

    @Then("^obtinc un error que diu: \"([^\"]*)\"$")
    public void there_is_a_failure_reporting(String msg) throws Throwable {
        assertNotNull(this.exception);
        assertEquals(msg, this.exception.getMessage());
    }

    @When("^llisto els productes ordenats per nom$")
    public void I_list_the_products_by_name() throws Throwable {
        this.resultProducts = productsService.listProductsByName();
    }

    @I("^que estem al tpv número (\\d+) de la botiga \"([^\"]*)\"$")
    public void que_estem_al_tpv_número_de_la_botiga(int posNumber, String shop) throws Throwable {
        this.posController = new PosController(shop,posNumber, productsService);
    }

    @Aleshores("^el tpv està en ús per en \"([^\"]*)\"$")
    public void el_tpv_està_en_ús_per_en(String saleAssistantName) throws Throwable {
        assertEquals(saleAssistantName, this.posController.getCurrentSaleAssistantName());
    }

    @Aleshores("^la venta actual és de'n \"([^\"]*)\" al tpv (\\d+) de la botiga \"([^\"]*)\"$")
    public void la_venta_actual_és_de_n_al_tpv_de_la_botiga(String saleAssistant, int posNumber, String shop) throws Throwable {
        Sale s = this.posController.getCurrentSale();
        assertNotNull(s);
        assertEquals(shop, s.getShop());
        assertEquals(posNumber, s.getPosNumber());
        assertEquals(saleAssistant, s.getSaleAssistantName());
    }

    @I("^que en \"([^\"]*)\" ha iniciat el torn al tpv$")
    public void que_en_ha_iniciat_el_torn_al_tpv(String saleAssistantName) throws Throwable {
        this.posController.login(saleAssistantName);
    }

    @Aleshores("^el sistema retorna el missatge d'error \"([^\"]*)\"$")
    public void el_sistema_retorna_el_missatge_d_error(String message) throws Throwable {
        assertEquals(message, this.exception.getMessage());
    }

    @Quan("^inicio el torn al tpv com a \"([^\"]*)\"$")
    public void inicio_el_torn_al_tpv(String saleAssistantName) throws Throwable {
        this.posController.login(saleAssistantName);
    }

    @Quan("^intento iniciar el torn al tpv com a \"([^\"]*)\"$")
    public void intento_iniciar_el_torn_al_tpv(String saleAssistantName) throws Throwable {
        this.exception = tryCatch(() -> this.posController.login(saleAssistantName));
    }

    @Quan("^inicio una nova venta$")
    public void inicio_una_nova_venta() throws Throwable {
        this.posController.startSale();
    }

    @Donat("^que hi ha una venta iniciada$")
    public void que_hi_ha_una_venta_iniciada() throws Throwable {
        this.posController.startSale();
    }

    @Quan("^intento iniciar una venta$")
    public void intento_iniciar_una_venta() throws Throwable {
        this.exception = tryCatch(() -> this.posController.startSale());
    }

    @Donat("^un producte amb nom \"([^\"]*)\", preu (\\d+)€, iva (\\d+)% i codi de barres (\\d+)$")
    public void un_producte_amb_nom_preu_€_iva_i_codi_de_barres(String productName, int price, int vatPct, int barCode) throws Throwable {
        this.productsService.newProduct(productName, price, vatPct, barCode);
    }

    @Quan("^escanejo el codi de barres (\\d+)$")
    public void escanejo_el_codi_de_barres(int barCode) throws Throwable {
        this.posController.scanBarCode(barCode);
    }

    @Aleshores("^la venta té (\\d+) (?:línia|línies)$")
    public void la_venta_té_n_linies(int expectedNumberOfLines) throws Throwable {
        assertEquals(expectedNumberOfLines,this.posController.getCurrentSale().getLines().size());
    }

    @I("^línia de venta (\\d+) és de (\\d+) unitats de \"([^\"]*)\" a (\\d+)€ cada una per un total de (\\d+)€$")
    public void línia_de_venta_és_de_unitats_de_a_€_cada_una_per_un_total_de_€(int lineNumber, int units, String productName, int unitPrice, int totalPrice) throws Throwable {
        SaleLine sl = this.posController.getCurrentSale().getLines().get(lineNumber - 1);
        assertEquals(units,sl.getAmount());
        assertEquals(unitPrice,sl.getUnitPrice());
        assertEquals(totalPrice,sl.getTotalPrice());
        assertEquals(productName, sl.getProductName());
    }

    @I("^el total de la venta actual és de (\\d+)€$")
    public void el_total_de_la_venta_actual_és_de_€(int saleTotal) throws Throwable {
        assertEquals(saleTotal,this.posController.getCurrentSale().getTotal());
    }

    @I("^la pantalla del client del tpv mostra$")
    public void la_pantalla_del_client_del_tpv_mostra(String msg) throws Throwable {
        assertEquals(msg, this.posController.getCustomerScreenMessage());
    }
}
