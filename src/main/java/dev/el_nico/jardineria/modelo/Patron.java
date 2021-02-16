package dev.el_nico.jardineria.modelo;

/**
 * Expresiones regulares
 */
public enum Patron {

    EMAIL("\\w+@\\w+[.]\\w+"),
    NIE("[a-zA-Z]\\d{7}[a-zA-Z]"),
    DNI("\\d{8}[a-zA-Z]");

    private String regex;

    Patron(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return regex;
    }
}
