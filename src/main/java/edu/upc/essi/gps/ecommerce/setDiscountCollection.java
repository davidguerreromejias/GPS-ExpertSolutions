package edu.upc.essi.gps.ecommerce;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by GerardDuch on 14/05/15.
 */
public class setDiscountCollection {
    private ArrayList<setDiscount> setDiscountController;

    public setDiscountCollection() {
        this.setDiscountController = new ArrayList<setDiscount>();
    }

    private setDiscount getSetDiscountFromSet (String set, String shop, String type) {
        setDiscount result = new setDiscount();
        for (setDiscount sd : setDiscountController) {
            if (sd.getSetObj().equals(set) && sd.getShop().equals(shop) && sd.getTipus().equals(type)) result = sd;
        }
        return result;
    }

    public void addSetDiscount(setDiscount sd) throws RuntimeException {
        if (sd.getTipus().equals("percentatge") &&  sd.getDiscount() <= 0 || sd.getDiscount() > 100)
            throw new IllegalArgumentException("El descompte ha de ser més gran que 0 i més petit o igual que 100");
        eliminaSetDiscount(sd); //si ja existia un descompte d'aquest tipo, l'elimina
        setDiscountController.add(sd);
    }

    private void eliminaSetDiscount(setDiscount sd) {
        Iterator<setDiscount> it = setDiscountController.iterator();
        setDiscount aux;
        Boolean trobat=false;
        while( it.hasNext() && !trobat){
            aux = it.next();
            if ( aux.getSetObj().equals(sd.getSetObj()) && aux.getShop().equals(sd.getShop())
                    && aux.getTipus().equals(sd.getTipus()) && !trobat) {
                trobat=true;
                it.remove();
            }
        }
    }

    public void deleteSetDiscount(String set, String type) {
        Iterator<setDiscount> it = setDiscountController.iterator();
        setDiscount aux;
        Boolean trobat=false;
        while( it.hasNext() && !trobat){
            aux = it.next();
            if ( aux.getSetObj().equals(set) && aux.getTipus().equals(type) && !trobat ) {
                trobat=true;
                it.remove();
            }
        }
    }

    public boolean getSetDiscount (String set, String shop, String type) {
        if (!existSetDiscount(set, type))
            throw new IllegalArgumentException("No existeix cap descompte del tipus " + type + " pels productes de tipus " + set);
        return true;
    }

    public boolean existSetDiscount(String set, String type) {
        for (setDiscount sd : setDiscountController) {
            if (sd.getSetObj().equals(set) && sd.getTipus().equals(type)) return true;
        }
        return false;
    }

    public String SetDiscountList(String type) {
        if (setDiscountController.isEmpty())
            throw new IllegalArgumentException("Actualment no existeix cap descompte del tipus percentatge i per tipus de producte");

        StringBuilder sb = new StringBuilder();

        if (type.equals("percentatge")) {
            sb.append("--Tipus Descompte--  --Descompte--  --Tipus Producte--\n");
            String espai = " , ";
            for (setDiscount s : setDiscountController) {
                if (s.getTipus().equals(type))
                    sb.append(s.getTipus()).append(espai).append(s.getDiscount()).append("%").append(espai).append(s.getSetObj()).append("\n");
            }
        }

        if (type.equals("m x n")) {
            sb.append("--Tipus Descompte--  --Compres--  --En pagues--  --Tipus Producte--\n");
            String espai = " , ";
            for (setDiscount s : setDiscountController) {
                if (s.getTipus().equals(type))
                    sb.append(s.getTipus()).append(espai).append(s.getCompres()).append("x").append(s.getPagues()).append(espai).append(s.getSetObj()).append("\n");
            }
        }
        return sb.toString();
    }

    public String allSetDiscountList() {
        if (setDiscountController.isEmpty())
            throw new IllegalArgumentException("Actualment no existeix cap descompte del tipus percentatge i per tipus de producte");

        StringBuilder sb = new StringBuilder();

        sb.append("--Tipus Descompte--  --Descompte--  --Tipus Producte--\n");
        String espai = " , ";
        for (setDiscount s : setDiscountController) {
            if (s.getTipus().equals("percentatge"))
                sb.append(s.getTipus()).append(espai).append(s.getDiscount()).append("%").append(espai).append(s.getSetObj()).append("\n");
        }
        sb.append("\n");
        sb.append("--Tipus Descompte--  --Compres--  --En pagues--  --Tipus Producte--\n");
        for (setDiscount s : setDiscountController) {
            if (s.getTipus().equals("m x n"))
                sb.append(s.getTipus()).append(espai).append(s.getCompres()).append("x").append(s.getPagues()).append(espai).append(s.getSetObj()).append("\n");
        }

        return sb.toString();
    }
}
