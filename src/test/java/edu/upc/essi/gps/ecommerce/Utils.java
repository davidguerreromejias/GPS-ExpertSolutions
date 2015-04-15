package edu.upc.essi.gps.ecommerce;

import java.util.function.Consumer;

public final class Utils {

    private Utils(){}

    public static <T> Exception tryCatch(Runnable r){
        try {
            r.run();
            return null;
        } catch (Exception e){
            return e;
        }
    }
}
