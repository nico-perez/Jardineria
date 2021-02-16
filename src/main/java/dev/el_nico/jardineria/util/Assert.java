package dev.el_nico.jardineria.util;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import dev.el_nico.jardineria.excepciones.ExcepcionDatoNoValido;
import dev.el_nico.jardineria.excepciones.ExcepcionFormatoIncorrecto;

public class Assert {
    
    private Assert(){}

    /**
     * Comprueba que la variable varcharContents tiene una longitud
     * menor o igual que maxLength. Si no es así, lanza una excepción.
     * @param dbName El nombre de la variable, en la base de datos,
     *                     que varcharContents representa en el programa.
     * @param maxLength La longitud máxima que puede tener el string (e.g. 
     *                  una variable VARCHAR(5) tiene longitud máxima de 5).
     * @param vcContents El string cuya longitud se va a comprobar. 
     * @throws ExcepcionDatoNoValido Si la longitud de varcharContents es
     *                               mayor que maxLength.
     */
    public static void varcharLength(@NonNull String dbName, @Nullable String vcContents, int maxLength) throws ExcepcionDatoNoValido {
        if (vcContents != null) {
            int length = vcContents.length();
            if (length > maxLength) {
                throw new ExcepcionDatoNoValido(new StringBuilder().append(dbName).append(" es VARCHAR(")
                        .append(maxLength).append("), pero la longitud es ").append(length).toString());
            }
        }
    }

    /**
     * Comprueba que el objeto object no es null. Si lo es, lanza
     * una excepción.
     * @param dbName El nombre de la variable en la base de datos.
     * @param object El bixo.
     * @throws ExcepcionDatoNoValido Si object es null.
     */
    public static <T> void notNull(@NonNull String dbName, @Nullable T object) throws ExcepcionDatoNoValido {
        if (object == null) {
            throw new ExcepcionDatoNoValido(new StringBuilder().append(dbName)
                    .append(" es NOT NULL, pero el parámetro de la aserción sí lo es").toString());
        }
    }

    /**
     * Comprueba que el String string cumple la expresión regular
     * regex. Si no, lanza una excepción.
     * @param dbName El nombre de la variable en la base de datos.
     * @param regex Expresión regular.
     * @param string La cadena que debería cumplir la expresión regular.
     * @throws ExcepcionFormatoIncorrecto Si la expresión no se cumple.
     */
    public static void regexMatch(@NonNull String dbName, @NonNull String regex, @NonNull String string) throws ExcepcionFormatoIncorrecto {
        if (!string.matches(regex)) {
            throw new ExcepcionFormatoIncorrecto(
                    new StringBuilder().append(dbName).append(" debería cumplir el patrón ").append(regex)
                            .append(", pero es ").append(string).toString());
        }
    }
}
