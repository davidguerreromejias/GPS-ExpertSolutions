package edu.upc.essi.gps.ecommerce;

import edu.upc.essi.gps.utils.Comparators;

import java.util.ArrayList;
import java.util.List;

public class ProductsService {

    private ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public void newProduct(String name, int price, int vatPct, int barCode, String setTypes){
        long id = productsRepository.newId();
        ArrayList<String> typesList = emplenaTypeList(setTypes);
        Product result = new Product(id,name, price, vatPct, barCode, typesList);
        productsRepository.insert(result);
    }

    private ArrayList<String> emplenaTypeList(String setTypes) {
        ArrayList<String> typesList = new ArrayList<String>();
        String newType = "";
        int i = 0;
        while ( i < setTypes.length() ) {
            int j = i;
            while (j < setTypes.length() && setTypes.charAt(j) != ',')  {
                newType += setTypes.charAt(j) ;
                ++j;
            }
            i=j+1;
            typesList.add(newType);
            newType= "";
        }
        return typesList;
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

    public Product findById(long id) {
        return productsRepository.findById(id);
    }

    public Product findByBarCode(int barCode) {
        return productsRepository.findByBarCode(barCode);
    }
}
