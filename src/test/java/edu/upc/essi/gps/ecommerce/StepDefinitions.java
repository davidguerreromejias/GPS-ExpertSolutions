package edu.upc.essi.gps.ecommerce;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StepDefinitions {

    private ProductsRepository productsRepository = new ProductsRepository();
    private ProductsService productsService = new ProductsService(productsRepository);
    private List<Product> resultProducts;
    private Exception exception;

    @Given("^un producte \"([^\"]*)\"$")
    public void I_create_a_product(String productName) throws Throwable {
        productsService.newProduct(productName);
    }

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

    @When("^intento crear un producte \"([^\"]*)\"$")
    public void I_try_to_create_a_product(String productName) throws Throwable {
        try {
            productsService.newProduct(productName);
        } catch (Exception e) {
            this.exception = e;
        }
    }

    @Then("^obtinc un error que diu: \"([^\"]*)\"$")
    public void there_is_a_failure_reporting(String msg) throws Throwable {
        assert (this.exception != null);
        assertEquals(msg, this.exception.getMessage());
    }

    @When("^llisto els productes ordenats per nom$")
    public void I_list_the_products_by_name() throws Throwable {
        this.resultProducts = productsService.listProductsByName();
    }

    @When("^I look for the product \"([^\"]*)\"$")
    public void I_look_for_the_product(String productName) throws Throwable {
        Product result = productsService.findByName(productName);
        this.resultProducts = new LinkedList<Product>();
        this.resultProducts.add(result);
    }

    @Then("^I get a product$")
    public void I_get_a_product() throws Throwable {
        assertEquals(1,this.resultProducts.size());
    }

    private Product theProduct(){
        assertEquals(1,this.resultProducts.size());
        return this.resultProducts.get(0);
    }

    @And("^the product name is \"([^\"]*)\"$")
    public void the_product_name_is(String productName) throws Throwable {
        assertEquals(productName,theProduct().getName());
    }

    @And("^the product has an id$")
    public void the_product_has_an_id() throws Throwable {
        assertNotNull(theProduct().getId());
    }
}
