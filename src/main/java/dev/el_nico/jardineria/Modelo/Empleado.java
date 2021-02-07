package dev.el_nico.jardineria.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

import dev.el_nico.jardineria.excepciones.NicoExcepcion;
import dev.el_nico.jardineria.util.AbstractBuilder;

@Entity
public class Empleado {

    private @Id Integer codigo_empleado;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String extension;
    private String email;
    private String codigo_oficina;
    private Integer codigo_jefe;
    private String puesto;

    public Integer getCodigo() {
        return codigo_empleado;
    }

    public static class Builder extends AbstractBuilder<Empleado> {

        // TODO :I

        public Builder(Integer codigo, String nombre, String apellido1, String apellido2, String extension,
        String email, String codigo_oficina, Integer codigo_jefe, String puesto) {
            este = new Empleado();
            este.codigo_empleado = codigo;
            este.nombre = nombre;
            este.apellido1 = apellido1;
            este.apellido2 = apellido2;
            este.extension = extension;
            este.email = email;
            este.codigo_oficina = codigo_oficina;
            este.codigo_jefe = codigo_jefe;
            este.puesto = puesto;
        }

        @Override
        public Empleado buildOrThrow() throws NicoExcepcion {
            return este;
        }
        
    }
}
