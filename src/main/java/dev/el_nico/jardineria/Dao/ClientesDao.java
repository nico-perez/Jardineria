package dev.el_nico.jardineria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import dev.el_nico.jardineria.excepciones.ExcepcionClienteDuplicado;
import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.modelo.Cliente;

public class ClientesDao implements IDao<Cliente> {

    private SortedMap<Integer, Cliente> clientes = new TreeMap<>();

    public ClientesDao() {
        try {
            guardar(new Cliente.Builder(1, "Federico", "111-222-2", "111-222-1", "C/ Falsa 123", "Zaragoza").buildOrThrow());
            guardar(new Cliente.Builder(2, "Saturno", "232-922-0", "232-100-1", "Avda. Solomillo 3", "Zaragoza").buildOrThrow());
            guardar(new Cliente.Builder(3, "Janomina", "112-121-1", "112-122-1", "C/ Y Reme Mortadelo 29", "Zaragoza").conCodigoPostal("303013").buildOrThrow());
            guardar(new Cliente.Builder(4, "Rémora", "111-473-3", "111-222-1", "13 Rue del Percebe", "Zaragoza").conLimiteCredito(69.0).conPais("España").buildOrThrow());
        } catch (Exception e) {
            System.err.println("movidon");
        }
    }

    @Override
    public Optional<Cliente> uno(Object id) {
        return Optional.ofNullable(clientes.get(id));
    }

    @Override
    public List<Cliente> todos() {
        return new ArrayList<Cliente>(clientes.values());
    }

    @Override
    public void guardar(Cliente cliente) throws ExcepcionClienteDuplicado, 
                                                ExcepcionCodigoYaExistente {
        // Se asegura de que el cliente no está duplicado.
        Optional<Cliente> otro = uno(cliente.getCodigo());
        if (otro.isPresent()) {
            Cliente.Contacto cto_este = cliente.getContacto(),
                             cto_otro = otro.get().getContacto();
            if (cto_otro.nombre().orElse("").equals(cto_este.nombre().orElse("")) &&
                cto_otro.apellido().orElse("").equals(cto_este.apellido().orElse("")) &&
                cto_otro.telefono().equals(cto_este.telefono())) {
                
                // Los campos código, nombre, apellido y teléfono coinciden,
                // así que es un duplicado de un cliente y eso
                throw new ExcepcionClienteDuplicado("Nombre, apellidos y teléfono coinciden con " +
                                                    "los de otro cliente. ¿Entrada duplicada?");                        
            } else {
                // El código de cliente ya existe.
                throw new ExcepcionCodigoYaExistente("El código de cliente ya existe en la base de datos.");
            }
        } else {
            clientes.put(cliente.getCodigo(), cliente);
        }
    }

    @Override
    public void modificar(Cliente t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void eliminar(Cliente t) {
        clientes.remove(t.getCodigo());
    }
}
