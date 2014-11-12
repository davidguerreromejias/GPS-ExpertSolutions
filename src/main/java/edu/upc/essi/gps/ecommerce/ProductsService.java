package edu.upc.essi.gps.ecommerce;

import edu.upc.essi.gps.utils.Comparators;

import java.util.List;

public class ProductsService {

    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public void newProduct(String name){
        long id = productsRepository.newId();
        Product result = new Product(id,name);
        productsRepository.insert(result);
    }

    public List<Product> listProducts(){
        return productsRepository.list();
    }

    public List<Product> listProductsByName(){
        return productsRepository.list(Comparators.byName);
    }

    public Product findByName(String productName) {
        return productsRepository.findByName(productName);
    }
}
