package br.edu.infnet.ReceitaFacil.system;

public class Convertion {
    public static double kilogramsToGrams(double kilograms) {
        return kilograms * 1000;
    }

    public static double gramsToKilograms(double grams) {
        return grams / 1000;
    }
    public static double litersToMilliliters(double liters) {
        return liters * 1000;
    }

    public static double millilitersToLiters(double milliliters) {
        return milliliters / 1000;
    }

    public static double litersToCups(double liters) {
        return liters * 4.22675;
    }

    public static double cupsToLiters(double cups) {
        return cups / 4.22675;
    }

    public static double litersToTablespoons(double liters) {
        return liters * 67.628;
    }

    public static double tablespoonsToLiters(double tablespoons) {
        return tablespoons / 67.628;
    }

    public static double millilitersToCups(double milliliters) {
        return milliliters * 0.00422675;
    }

    public static double cupsToMilliliters(double cups) {
        return cups / 0.00422675;
    }

    public static double millilitersToTablespoons(double milliliters) {
        return milliliters * 0.067628;
    }

    public static double tablespoonsToMilliliters(double tablespoons) {
        return tablespoons / 0.067628;
    }

    public static double cupsToTablespoons(double cups) {
        return cups * 16;
    }

    public static double tablespoonsToCups(double tablespoons) {
        return tablespoons / 16;
    }
}