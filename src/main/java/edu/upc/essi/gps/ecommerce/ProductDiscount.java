package edu.upc.essi.gps.ecommerce;

/**
 * Created by federicobuldintobar on 28/05/15.
 */
public class ProductDiscount {
    private Discount disc;
    private Product prod;

    public ProductDiscount() {}

    public ProductDiscount(Discount d, Product p){
        disc = d;
        prod = p;
    }

    public void setDiscount(Discount d){
        disc = d;
    }

    public Discount getDiscount(){
        return disc;
    }

    public void setProduct(Product p){
        prod = p;
    }

    public Product getProduct(){
        return prod;
    }
}
