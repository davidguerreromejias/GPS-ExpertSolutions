package edu.upc.essi.gps.ecommerce;

import edu.upc.essi.gps.domain.Entity;
import edu.upc.essi.gps.domain.HasName;

public class Product implements Entity, HasName {

    private long id;

    private String name;

    private float popularity;

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPopularity() {
        return popularity;
    }
}
