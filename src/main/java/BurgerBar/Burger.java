package BurgerBar;

public class Burger {

    private String name;
    private double preis;
    private boolean istVegan;

    //Konstruktor
    public Burger(String name, double preis, boolean istVegan) {
        this.name = name;
        this.preis = preis;
        this.istVegan = istVegan;
    }

    //Getter:
    public String getName() {
        return name;
    }

    public double getPreis() {
        return preis;
    }

    public boolean isIstVegan() {
        return istVegan;
    }

    public double berechneBruttoPreis() {
        return this.preis * 1.19;
    }

    @Override
    public String toString() {
        return name + " (" + preis + "€) " + " " + istVegan;
    }
}