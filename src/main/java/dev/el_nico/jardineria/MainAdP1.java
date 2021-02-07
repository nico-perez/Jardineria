package dev.el_nico.jardineria;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import dev.el_nico.jardineria.dao1.sql.ConexionJardineria;
import dev.el_nico.jardineria.excepciones1.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.excepciones1.ExcepcionDatoNoValido;
import dev.el_nico.jardineria.excepciones1.ExcepcionFormatoIncorrecto;
import dev.el_nico.jardineria.modelo1.Cliente;
import dev.el_nico.jardineria.modelo1.Producto;

/**
 * Clase Main de la práctica 1ª de Acceso a Datos, donde
 * había que implementar el patrón DAO y conectarse con 
 * una base de datos SQL mediante JDBC.
 */
public class MainAdP1 {

    public static void main(String[] args) {
        try (ConexionJardineria c = new ConexionJardineria()) {

            // login
            String user, pass;
            do {
                System.out.print("usuario: ");
                user = System.console().readLine();
                System.out.print("contraseña: ");
                pass = new String(System.console().readPassword());
            } while (!c.login(user, pass));

            System.out.println("\nLogin OK!\n=========");

            // bucle principal
            int orden = 0;
            do {
                imprMenu();
                orden = pedirOrden();
                acatar(orden, c);
            } while (orden != 0);

            // logout
            System.out.println("\nCloseando conexión...\n");
        } catch (SQLException e) {
            System.out.println("Excepcion al closear;");
            e.printStackTrace();
            System.out.println();
        }
    }

    // llama a la funcion que corresponda
    private static void acatar(int orden, ConexionJardineria c) {
        switch (orden) {
            case 0: return;
            case 1: pedirDatosYGuardarCliente(c); break;
            case 2: pedirCodigoYMostrarCliente(c); break;
            case 3: mostrarTodosLosClientes(c); break;
            case 4: pedirPalabraYBuscarClientes(c); break;
            case 5: pedirCodigoYEditarProducto(c); break;
        }
    }

    private static void pedirCodigoYEditarProducto(ConexionJardineria c) {
        Optional<Producto> optproducto;
        String codigo_producto = "";
        boolean error = false;
        do {
            if (error) {
                System.out.println("No hay ningún producto con código " + codigo_producto);
            }
            codigo_producto = pedirString("código del producto", false);
            optproducto = c.productos().uno(codigo_producto);
        } while (error = !optproducto.isPresent());

        // aqui hay producto si o si
        Producto producto = optproducto.get();

        Producto.Builder mod = new Producto.Builder(producto);
        String nuevo_nombre = pedirString("nombre", true);
        String nueva_gama = pedirString("gama", true);
        String nuevas_dimen = pedirString("dimensiones", true);
        String nuevo_proveedor = pedirString("proveedor", true);
        String nueva_desc = pedirString("descripcion", true);
        Integer nueva_cantidad = pedirEntero("cantidad_en_stock", true);
        Double nuevo_precio_venta = pedirDouble("precio_venta");
        Double nuevo_precio_prov = pedirDouble("precio_proveedor");

        if (nuevo_nombre != null) mod.conNombre(nuevo_nombre);
        if (nueva_gama != null) mod.conGama(nueva_gama);
        if (nuevas_dimen != null) mod.conDimensiones(nuevas_dimen);
        if (nuevo_proveedor != null) mod.conProveedor(nuevo_proveedor);
        if (nueva_desc != null) mod.conDescripcion(nueva_desc);
        if (nueva_cantidad != null) mod.conCantidadEnStock(nueva_cantidad);
        if (nuevo_precio_venta != null) mod.conPrecioVenta(nuevo_precio_venta);
        if (nuevo_precio_prov != null) mod.conPrecioProveedor(nuevo_precio_prov);

        c.productos().modificar(producto);
    }

    private static void pedirPalabraYBuscarClientes(ConexionJardineria c) {
        String criterio = pedirString("palabra clave", false).toLowerCase();
        List<Cliente> clientes = c.clientes().todos();
        clientes.removeIf(
            cli -> {
                return !cli.getNombre().toLowerCase().contains(criterio)
                    && !cli.getContacto().nombre().orElse("").toLowerCase().contains(criterio)
                    && !cli.getContacto().apellido().orElse("").toLowerCase().contains(criterio);
            }
        );
        clientes.sort((c1, c2) -> c1.getNombre().compareTo(c2.getNombre()));

        if (clientes.size() > 0) System.out.println();
        for (Cliente cliente : clientes) {
            System.out.println(cliente.infoResumen());
        }
    }

    private static void mostrarTodosLosClientes(ConexionJardineria c) {
        List<Cliente> clientes = c.clientes().todos();
        clientes.sort((c1, c2) -> c1.getNombre().compareTo(c2.getNombre()));
        
        if (clientes.size() > 0) System.out.println();
        for (Cliente cliente : clientes) {
            System.out.println(cliente.infoResumen());
        }
    }

    private static void pedirCodigoYMostrarCliente(ConexionJardineria c) {
        int codigo = pedirEntero("código del cliente", false);
        if (c.clientes().uno(codigo).isPresent()) {
            System.out.println(c.clientes().uno(codigo).get());
        } else {
            System.out.println("No hay ningún cliente con código " + codigo);
        }
    }

    private static void pedirDatosYGuardarCliente(ConexionJardineria c) {
        int codigo_cliente     = pedirEntero("codigo_cliente (no nulo)", false);
        String nombre_cliente     = pedirString("nombre_cliente (no nulo)", false);
        String nombre_contacto    = pedirString("nombre_contacto", true);
        String apellido_contacto  = pedirString("apellido_contacto", true);
        String telefono           = pedirString("telefono (no nulo)", false);
        String fax                = pedirString("fax (no nulo)", false);
        String linea_direccion1   = pedirString("linea_direccion 1 (no nulo)", false);
        String linea_direccion2   = pedirString("linea_direccion 2", true);
        String ciudad             = pedirString("ciudad (no nulo)", false);
        String region             = pedirString("region", true);
        String pais               = pedirString("pais", true);
        String codigo_postal      = pedirString("codigo_postal", true);
        Integer rep_ventas        = pedirEntero("codigo_empleado_rep_ventas", true);
        Double limite_credito     = pedirDouble("limite_credito");

        Cliente cliente;
        try {
            cliente = new Cliente.Builder(codigo_cliente, nombre_cliente, telefono, fax, linea_direccion1,
                    ciudad).conNombreDeContacto(nombre_contacto).conApellidoDeContacto(apellido_contacto)
                            .conLineaDireccion2(linea_direccion2).conRegion(region).conPais(pais)
                            .conCodigoPostal(codigo_postal)
                            .conEmpleadoRepVentas(c.empleados().uno(rep_ventas).orElse(null))
                            .conLimiteCredito(limite_credito).buildOrThrow();
            c.clientes().guardar(cliente);
            System.out.println("\nCliente añadido OK!\n===================");
        } catch (ExcepcionCodigoYaExistente e) {
            System.out.println("\nError!\n======\nNo se ha añadido nada porque ya había un cliente con código " + codigo_cliente + ".");
        } catch (ExcepcionDatoNoValido | ExcepcionFormatoIncorrecto e) {
            System.out.println("\nError!\n======\nNo se ha añadido nada porque alguno de los datos es incorrecto.");
        } catch (SQLException e) {
            System.out.println("\nError!\n======\nExcepción SLQ!!! vaya por dios");
        }    
    }

    // Devuelve un entero entre 0 y 5
    private static int pedirOrden() {
        boolean input_ok = false;
        int orden = 0;
        do {
            try {
                orden = pedirEntero("orden", false);
                if (orden < 0 || orden > 5) {
                    System.out.println("Orden no válida.");
                } else {
                    input_ok = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número del 1 al 5.");
            }
        } while (!input_ok);

        return orden;
    }

    // Imprime el menú
    private static void imprMenu() {
        System.out.println("\nÓrdenes válidas:\n================\n"
                + "0 -> Salir\n1 -> Añadir cliente\n2 -> Mostrar un cliente\n"
                + "3 -> Mostrar todos los clientes\n4 -> Buscar clientes\n"
                + "5 -> Editar un producto\n");
    }

    private static Integer pedirEntero(String msj, boolean puedeSerNull) {
        while (true) {
            System.out.print(msj + " (entero): ");
            int entero;
            try {
                String s = System.console().readLine();
                if (puedeSerNull && StringUtils.isBlank(s)) {
                    return null;
                } else {
                    entero = Integer.parseInt(s);
                    return entero;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un entero.");
            }
        }
    }

    private static Double pedirDouble(String msj) {
        while (true) {
            System.out.print(msj + " (double): ");
            double comaflot;
            try {
                String s = System.console().readLine();
                if (StringUtils.isBlank(s)) {
                    return null;
                } else {
                    comaflot = Double.parseDouble(s);
                    return comaflot;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un double.");
            }
        }
    }

    private static String pedirString(String msj, boolean puedeEstarVacio) {
        String s;
        boolean error = false;
        do {
            if (error) System.out.println("El string no puede estar vacío.");
            System.out.print(msj + " (string): ");
            s = System.console().readLine();
        } while (puedeEstarVacio ? false : (error = StringUtils.isBlank(s)));
        return StringUtils.isBlank(s) ? null : s.trim();
    }
}
