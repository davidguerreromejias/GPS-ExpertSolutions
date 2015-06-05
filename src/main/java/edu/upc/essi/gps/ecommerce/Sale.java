package edu.upc.essi.gps.ecommerce;

import java.lang.reflect.Array;
import java.util.*;

class SaleLine{
    private long productId;
    private String productName;
    private int unitPrice;
    private int amount;
    private Discount discount;
    private int effectiveN;
    private String ProductRegal;
    private boolean teDiscConjAplicat;

    public SaleLine(Product product, int amount) {
        this.productId = product.getId();
        this.productName = product.getName();
        this.unitPrice = product.getPrice();
        this.amount = amount;
        this.discount = new Discount("None",100);
        this.ProductRegal = null;
        this.teDiscConjAplicat = false;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getAmount() {
        return amount;
    }

    public boolean getTeDiscConjAplicat(){
        return teDiscConjAplicat;
    }

    public void setTeDiscConjAplicat(boolean b){
        teDiscConjAplicat = b;
    }

    public int getTotalPriceRaw() {
        if (ProductRegal != null) return 0;
        return unitPrice * amount;
    }

    public int getTotalPrice() {
        if(discount.getTypeOfDiscount().equals("percentatge")) {
            double discdouble = discount.getAmountDiscount();
            discdouble = discdouble / 100;
            double totalPrice = (unitPrice * amount) - (unitPrice * amount * discdouble);
            return (int) totalPrice;
        }
        else if(discount.getTypeOfDiscount().equals("m x n")){
            return unitPrice * effectiveN;
        }
        else if (ProductRegal != null) return 0;
        else return unitPrice * amount;
    }

    public void setDiscount(Discount d){
        discount = d;
        effectiveN = d.getN();
    }

    public int getEffectiveN() {return effectiveN;}

    public void setEffectiveN(int effectiveN){this.effectiveN = effectiveN;}

    public Discount getDiscount(){
        return discount;
    }

    public void setProductRegal(String s){
        ProductRegal = s;
    }

    public String getProductRegal(){return ProductRegal;}


    public void incrAmount(){amount++;}

    public void decrAmount(){amount--;}
}

public class Sale {
    private final String shop;
    private final int posNumber;
    private final String saleAssistantName;
    private final LinkedList<SaleLine> lines = new LinkedList<>();
    private String paymentForm;
    private final Date data;
    private LinkedList<SaleLine> candidatsADescompteMxN = new LinkedList<>();

    public void addCandidat() {
        candidatsADescompteMxN.add(lines.getLast());
    }

    public boolean isEstaPagada() {
        return estaPagada;
    }

    public void setEstaPagada(boolean estaPagada) {
        this.estaPagada = estaPagada;
    }

    private boolean estaPagada;

    public String getPaymentForm() {
        return paymentForm;
    }

    public void setPaymentForm(String paymentForm) {
        this.paymentForm = paymentForm;
    }

    ;

    public void addProduct(Product p) {
        lines.add(new SaleLine(p, 1));
    }

    public void addNProducts(Product p, int amount) {
        lines.add(new SaleLine(p, amount));
    }


    public Sale(String shop, int posNumber, String saleAssistantName) {
        this.shop = shop;
        this.posNumber = posNumber;
        this.saleAssistantName = saleAssistantName;
        this.paymentForm = "efectiu";
        this.estaPagada = false;
        data = new Date();
    }

    public String getShop() {
        return shop;
    }

    public int getPosNumber() {
        return posNumber;
    }

    public String getSaleAssistantName() {
        return saleAssistantName;
    }

    public List<SaleLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public int getTotal() {
        int res = 0;
        for (SaleLine l : lines) {
            res += l.getTotalPrice();
        }
        return res;
    }

    public boolean isEmpty() {
        return lines.isEmpty();
    }

    public void deleteLine(String nomProd) {
        for (SaleLine l : lines) if (nomProd == l.getProductName()) lines.remove(l);
    }

    public void assignaDescompte(Discount d, String nomP, int effectiveN) {
        for (SaleLine l : lines)
            if (nomP == l.getProductName()) {
                l.setDiscount(d);
                l.setEffectiveN(effectiveN);
            }
    }

    public void applyDiscountAtLastLine(Discount d, int effectiveN) {
        lines.getLast().setDiscount(d);
        lines.getLast().setEffectiveN(effectiveN);
    }

    public void tryApplyDiscMxN(Discount d) {
        SaleLine lastLine = lines.getLast();
        int amountProduct = lastLine.getAmount();
        int unitPrice = lastLine.getUnitPrice();
        int m = d.getM();
        int n = d.getN();
        if (candidatsADescompteMxN.size() == 0) {
            if (m <= amountProduct) {
                int effectiveQ;
                effectiveQ = n * amountProduct/m + amountProduct%m;
                applyDiscountAtLastLine(d,effectiveQ);
            }
        }
        else {
            for (SaleLine l : candidatsADescompteMxN) {
                int sum = amountProduct + l.getAmount();
                if (m <= sum ) {
                    int unitatsAPagar = n * (sum/m) + sum%m;
                    int qGratis = sum - unitatsAPagar;
                    if (unitPrice >= l.getUnitPrice()) {
                        if(qGratis > l.getAmount()){
                            assignaDescompte(d, l.getProductName(), 0);
                            applyDiscountAtLastLine(d, unitatsAPagar - l.getAmount());
                            lines.getLast().setTeDiscConjAplicat(true);
                        }
                        else if(qGratis < l.getAmount()){
                            assignaDescompte(d, l.getProductName(),l.getAmount() - qGratis);
                        }
                        else {
                            assignaDescompte(d, l.getProductName(), 0);
                        }
                        l.setTeDiscConjAplicat(true);
                    }
                    else {
                        if(qGratis > amountProduct) {
                            applyDiscountAtLastLine(d, 0);
                            assignaDescompte(d, l.getProductName(), l.getAmount() - amountProduct);
                            l.setTeDiscConjAplicat(true);
                        }
                        else if(qGratis < amountProduct){
                            applyDiscountAtLastLine(d,amountProduct - qGratis);
                        }
                        else{
                            applyDiscountAtLastLine(d,0);
                        }
                        lines.getLast().setTeDiscConjAplicat(true);
                    }
                }
            }
        }
    }


    public boolean potAfegir(String nomP, int amount) {
        if (getNumberOfRegals(nomP)<amount) {
            return true;
        }
        else return false;
    }

    public int getNumberOfRegals(String nomP) {
        int count = 0;
        for (SaleLine l : lines) {
            if (l.getProductRegal() == nomP) count += l.getAmount();
        }
        return count;
    }

    public void addRegal(Product p, String nomP){
        String nomRegal = p.getName();
        boolean afegit = false;
        for (SaleLine l : lines) {
            if (nomRegal.equals(l.getProductName()) && l.getProductRegal().equals(nomP)){
                l.incrAmount();
                afegit = true;
            }
        }
        if(!afegit){
            SaleLine sl = new SaleLine(p, 1);
            sl.setProductRegal(nomP);
            lines.add(sl);
        }
    }


    public void setToRegal(String nomRegal, int amount, String nomP) {
        for (SaleLine l : lines) {
            boolean esborrat = false;
            while (!esborrat && amount > 0) {
                if (nomRegal.equals(l.getProductName())) {
                    if (l.getProductRegal().equals(null)) {
                        if (l.getAmount() == 1) {
                            esborrat = true;
                            lines.remove(l);
                        } else l.decrAmount();
                        amount--;

                        boolean incrementat = false;
                        for (SaleLine l2 : lines) {
                            if (!incrementat) {
                                if (nomRegal.equals(l2.getProductName())) {
                                    if (l2.getProductName().equals(nomP)) {
                                        l2.incrAmount();
                                        incrementat = true;
                                    }
                                }

                            }

                        }
                    }
                }
            }
        }

    }

}
