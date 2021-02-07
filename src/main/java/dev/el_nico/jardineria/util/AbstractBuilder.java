package dev.el_nico.jardineria.util;

import java.util.Optional;

import dev.el_nico.jardineria.excepciones1.NicoExcepcion;

/**
 * Builder para hacerme la vida más fácil, pero al gfinal no
 */
public abstract class AbstractBuilder<T> {

    protected T este;

    protected AbstractBuilder() {}

    /**
     * Intenta construir.
     * @return {@code Optional.of(este)}, que contendrá una instancia
     *         válida si el buildeo ha sido posible; o {@code null}, si
     *         ha habido algún error.
     */
    public Optional<T> build() {
        try {
            buildOrThrow();
            return Optional.of(este);
        } catch (NicoExcepcion e) {
            return Optional.empty();
        }
    }

    /**
     * Intenta construir.
     * @return Una instancia válida.
     * @throws NicoExcepcion Una excepción si ha habido algún error.
     */
    public abstract T buildOrThrow() throws NicoExcepcion;
}
