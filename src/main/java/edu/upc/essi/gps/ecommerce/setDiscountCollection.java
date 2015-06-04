package edu.upc.essi.gps.ecommerce;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by GerardDuch on 14/05/15.
 */
public class setDiscountCollection {
    private ArrayList<Discount> DiscountController;

    public setDiscountCollection() {
        this.DiscountController = new ArrayList<Discount>();
    }

    public Discount getDiscountFromSet (String set) {
        Discount result = null;
        for (Discount sd : DiscountController) {
            if (sd.getConjuntAAplicar().equals(set)) result = sd;
        }
        return result;
    }

    public void addSetDiscount(Discount disc) throws RuntimeException {
        if (disc.getTypeOfDiscount().equals("percentatge") &&  disc.getAmountDiscount() <= 0 || disc.getAmountDiscount() > 100)
            throw new IllegalArgumentException("El descompte ha de ser més gran que 0 i més petit o igual que 100");
        eliminaSetDiscount(disc); //si ja existia un descompte d'aquest tipus, l'elimina
        DiscountController.add(disc);
    }

    private void eliminaSetDiscount(Discount disc) {
        Iterator<Discount> it = DiscountController.iterator();
        Discount aux;
        Boolean trobat=false;
        while( it.hasNext() && !trobat){
            aux = it.next();
            if ( aux.getConjuntAAplicar().equals(disc.getConjuntAAplicar())
                    && aux.getTypeOfDiscount().equals(disc.getTypeOfDiscount()) && !trobat) {
                trobat=true;
                it.remove();
            }
        }
    }

    public void deleteDiscount(String set, String type) {
        Iterator<Discount> it = DiscountController.iterator();
        Discount aux;
        Boolean trobat=false;
        while( it.hasNext() && !trobat){
            aux = it.next();
            if ( aux.getConjuntAAplicar().equals(set) && aux.getTypeOfDiscount().equals(type) && !trobat ) {
                trobat=true;
                it.remove();
            }
        }
    }

    public boolean getSetDiscount (String set, String type) {
        if (!existSetDiscount(set, type))
            throw new IllegalArgumentException("No existeix cap descompte del tipus " + type + " pels productes de tipus " + set);
        return true;
    }

    public boolean existSetDiscount(String set, String type) {
        for (Discount sd : DiscountController) {
            if (sd.getConjuntAAplicar().equals(set) && sd.getTypeOfDiscount().equals(type)) return true;
        }
        return false;
    }

    public String SetDiscountList(String type) {
        if (DiscountController.isEmpty())
            throw new IllegalArgumentException("Actualment no existeix cap descompte del tipus " + type + " i per tipus de producte");

        StringBuilder sb = new StringBuilder();

        if (type.equals("percentatge")) {
            sb.append("--Tipus Descompte--  --Descompte--  --Tipus Producte--\n");
            String espai = " , ";
            for (Discount s : DiscountController) {
                if (s.getTypeOfDiscount().equals(type))
                    sb.append(s.getTypeOfDiscount()).append(espai).append(s.getAmountDiscount()).append("%").append(espai).append(s.getConjuntAAplicar()).append("\n");
            }
        }

        if (type.equals("m x n")) {
            sb.append("--Tipus Descompte--  --Compres--  --En pagues--  --Tipus Producte--\n");
            String espai = " , ";
            for (Discount s : DiscountController) {
                if (s.getTypeOfDiscount().equals(type))
                    sb.append(s.getTypeOfDiscount()).append(espai).append(s.getM()).append("x").append(s.getN()).append(espai).append(s.getConjuntAAplicar()).append("\n");
            }
        }
        return sb.toString();
    }

    public String allSetDiscountList() {
        if (DiscountController.isEmpty())
            throw new IllegalArgumentException("Actualment no existeix cap descompte del tipus percentatge i per tipus de producte");

        StringBuilder sb = new StringBuilder();

        sb.append("--Tipus Descompte--  --Descompte--  --Tipus Producte--\n");
        String espai = " , ";
        for (Discount s : DiscountController) {
            if (s.getTypeOfDiscount().equals("percentatge"))
                sb.append(s.getTypeOfDiscount()).append(espai).append(s.getAmountDiscount()).append("%").append(espai).append(s.getConjuntAAplicar()).append("\n");
        }
        sb.append("\n");
        sb.append("--Tipus Descompte--  --Compres--  --En pagues--  --Tipus Producte--\n");
        for (Discount s : DiscountController) {
            if (s.getTypeOfDiscount().equals("m x n"))
                sb.append(s.getTypeOfDiscount()).append(espai).append(s.getM()).append("x").append(s.getN()).append(espai).append(s.getConjuntAAplicar()).append("\n");
        }

        return sb.toString();
    }
}
