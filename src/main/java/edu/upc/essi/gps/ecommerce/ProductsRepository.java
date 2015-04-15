package edu.upc.essi.gps.ecommerce;

import edu.upc.essi.gps.utils.Matchers;
import edu.upc.essi.gps.utils.Repository;

import java.util.Comparator;


public class ProductsRepository extends Repository<Product> {

    public static Comparator<Product> byPopularity = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return new Float(o1.getPopularity()).compareTo(o2.getPopularity());
        }
    };

    public Product findByName(final String name) {
        return find(Matchers.nameMatcher(name));
    }

    public Product findByBarCode(final int barCode){
        return find((p) -> p.getBarCode() == barCode);
    }

    @Override
    protected void checkInsert(final Product entity) throws RuntimeException {
        if(findByName(entity.getName())!=null)
            throw new IllegalArgumentException("Ja existeix un producte amb aquest nom");
    }
}
