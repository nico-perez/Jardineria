package dev.el_nico.jardineria.dao.gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.el_nico.jardineria.excepciones.ExcepcionDatoNoValido;
import dev.el_nico.jardineria.modelo.Cliente;
import dev.el_nico.jardineria.modelo.Pedido;
import dev.el_nico.jardineria.util.adapter.OptionalAdapterFactory;

public class GsonDao {

    private File archivoJson;
    
    private SortedMap<Integer, Cliente> clientes = new TreeMap<>();
    private SortedMap<Integer, Pedido> pedidos = new TreeMap<>();

    public GsonDao(String rutaJson) {

        archivoJson = new File(rutaJson);
        byte[] contenido_bytes = new byte[(int) archivoJson.length()];
        boolean todo_bien = true;

        try (FileInputStream fis = new FileInputStream(archivoJson)) {
            fis.read(contenido_bytes);
        } catch (FileNotFoundException e) {
            todo_bien = false;
            System.err.println("Archivo no encontrado!!!!!!!!!!");
            e.printStackTrace();
        } catch (IOException e) {
            todo_bien = false;
            System.err.println("Algo ha pasado aquí");
            e.printStackTrace();
        }

        if (todo_bien) {
            String contenido_json = new String(contenido_bytes);
            // Deserializa el array leído del json

            // usando el adapter jejejeje
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new OptionalAdapterFactory()).create();
            FormatoAlmacenamiento contenido = gson.fromJson(contenido_json, FormatoAlmacenamiento.class);
            
            if (contenido != null) {
            Cliente[] arrayClientes = contenido.clientes;
            if (arrayClientes != null) {
                for (int i = 0; i < arrayClientes.length; ++i) {
                    try {
                        // Comprueba que el cliente deserializado es válido
                        Cliente chequeado = new Cliente.Builder(arrayClientes[i]).buildOrThrow();
                        clientes.put(chequeado.getCodigo(), chequeado);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            Pedido[] arrayPedidos = contenido.pedidos;
            if (arrayPedidos != null) {
                for (int i = 0; i < arrayPedidos.length; ++i) {
                    try {
                        Pedido chequeado = new Pedido.Builder(arrayPedidos[i]).buildOrThrow();
                        if (clientes.containsKey(chequeado.getCodigoCliente())) {
                            pedidos.put(chequeado.getCodigo(), chequeado);
                        } else {
                            throw new ExcepcionDatoNoValido("cliente no existe");
                        }
                    } catch (ExcepcionDatoNoValido e) {
                        System.err.println("Un cliente deserializado no tenía datos correctos");
                        e.printStackTrace();
                    }
                }
            }
        }
        }
    }

    public List<Cliente> clientes() {
        return new ArrayList<>(clientes.values());
    }

    public List<Pedido> pedidos() {
        return new ArrayList<>(pedidos.values());
    }


	public void guardar() {

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new OptionalAdapterFactory()).create();

        String jsonJard = gson.toJson(new FormatoAlmacenamiento((Cliente[]) new ArrayList<Cliente>(clientes.values()).toArray(), (Pedido[]) new ArrayList<Pedido>(pedidos.values()).toArray()));
        try (FileOutputStream fos = new FileOutputStream(archivoJson)) {
            fos.write(jsonJard.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    private static class FormatoAlmacenamiento {
        public Cliente[] clientes;
        public Pedido[] pedidos;
        public FormatoAlmacenamiento(Cliente[] clientes, Pedido[] pedidos) {
            this.clientes = (Cliente[]) clientes;
            this.pedidos = (Pedido[]) pedidos;
        }
    }
}
