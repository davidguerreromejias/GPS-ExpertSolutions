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

    private boolean existsSetDiscount (String set, String shop) {
        for (setDiscount sd : setDiscountController) {
            if (sd.getSetObj().equals(set) && sd.getShop().equals(shop)) return true;
        }
        return false;
    }

    private setDiscount getSetDiscountFromSet (String set, String shop) {
        setDiscount result = new setDiscount();
        for (setDiscount sd : setDiscountController) {
            if (sd.getSetObj().equals(set) && sd.getShop().equals(shop)) result = sd;
        }
        return result;
    }

    public void addSetDiscount(setDiscount sd) throws RuntimeException {
        if (sd.getDiscount() <= 0 || sd.getDiscount() > 100)
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
            if ( aux.getSetObj().equals(sd.getSetObj()) && aux.getShop().equals(sd.getShop()) && !trobat) {
                trobat=true;
                it.remove();
            }
        }
    }

    public void deleteSetDiscount(String set) {
        Iterator<setDiscount> it = setDiscountController.iterator();
        setDiscount aux;
        Boolean trobat=false;
        while( it.hasNext() && !trobat){
            aux = it.next();
            if ( aux.getSetObj().equals(set) && !trobat ) {
                trobat=true;
                it.remove();
            }
        }
    }

    public float getSetDiscount (String set, String shop) {
        if (!existsSetDiscount(set, shop))
            throw new IllegalArgumentException("No existeix cap descompte de pels productes de tipus" + set);
        return getSetDiscountFromSet(set, shop).getDiscount();
    }

    public boolean existsSetDiscount(String set) {
        for (setDiscount sd : setDiscountController) {
            if (sd.getSetObj().equals(set)) return true;
        }
        return false;
    }

    public StringBuilder SetDiscountList() {
        if (setDiscountController.isEmpty())
            throw new IllegalArgumentException("Actualment no existeix cap descompte del tipus percentatge i per tipus de producte");

        StringBuilder sb = new StringBuilder();
        sb.append("--Tipus Descompte--  --Descompte--  --Tipus Producte--\n");
        String espai = " , ";
        for(setDiscount s : setDiscountController){
            sb.append(s.getTipus()).append(espai).append(s.getDiscount()).append(espai).append(s.getSetObj()).append("€\n");
        }
        return sb;
    }
}
