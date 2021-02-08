package dev.el_nico.jardineria;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import dev.el_nico.jardineria.dao.hibernate.ConexionJardineriaHql;
import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.excepciones.ExcepcionDatoNoValido;
import dev.el_nico.jardineria.excepciones.ExcepcionFormatoIncorrecto;
import dev.el_nico.jardineria.modelo.Cliente;
import dev.el_nico.jardineria.modelo.DetallePedido;
import dev.el_nico.jardineria.modelo.Pedido;
import dev.el_nico.jardineria.modelo.Producto;

/**
 * Clase Main de la práctica 2º de Acceso a Datos, donde
 * había que aprender a usar la biblioteca ORM Hibernate
 * y un par más de cosas
 */
public class MainAdP2 {

    private static Session session;

    public static void main(String[] args) {
        try (ConexionJardineriaHql daos = new ConexionJardineriaHql()) {

            // login
            String user, pass;
            do {
                System.out.print("usuario: ");
                user = System.console().readLine();
                System.out.print("contraseña: ");
                pass = new String(System.console().readPassword());
            } while (!daos.login(user, pass));

            System.out.println("\nLogin OK!\n=========");

            // bucle principal
            int orden = 0;
            do {
                imprMenu();
                orden = pedirOrden();
                acatar(orden, daos);
            } while (orden != 0);

            // logout
            System.out.println("\nCloseando conexión...\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // llama a la funcion que corresponda
    private static void acatar(int orden, ConexionJardineriaHql s) {
        switch (orden) {
            case 0: return;
            case 1: pedirDatosYGuardarCliente(s); break;
            case 2: pedirCodigoYMostrarCliente(s); break;
            case 3: mostrarTodosLosClientes(s); break;
            case 4: pedirPalabraYBuscarClientes(s); break;
            case 5: pedirCodigoYEditarProducto(s); break;
            case 6: mostrarDetallesDePedidos(s); break;
            case 7: mostrarEmpleadoDelMes(s); break;
        }
    }

    private static void mostrarEmpleadoDelMes(ConexionJardineriaHql s) {
        Integer mes, anio;
        boolean bien = false;
        Calendar cal = null;
        do {
            mes = pedirEntero("mes (0 - 11)", true);
            if (mes == null) {
                System.out.println("acción cancelada");
                return;
            }
            anio = pedirEntero("año", true);
            if (anio == null) {
                System.out.println("acción cancelada");
                return;
            }
            try {
                cal = new Calendar.Builder().setDate(anio, mes, 1).build();
                if (mes >= 0 && mes < 12) {
                    bien = true;
                } else {
                    System.out.println("El mes debe ser un entero entre 0 y 11");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("mes o año no válidos");
            }
        } while (!bien);

        List<Cliente> clientes = s.clientes().todos();
        List<Pedido> pedidos = s.pedidos().todos();

        @SuppressWarnings("unchecked")
        List<DetallePedido> detalles = session.createQuery("from Detalle").list();

        Map<Integer, Integer> ventas = new TreeMap<>();
        Map<Integer, Double> map = new TreeMap<>();
        for (Cliente c : clientes) {
            if (c.getCodigoEmpleadoRepVentas().isPresent()) {
                map.put(c.getCodigoEmpleadoRepVentas().get(), 0.0);
                ventas.put(c.getCodigoEmpleadoRepVentas().get(), 0);
            }
            for (Pedido p : pedidos) {
                Calendar fechaPedido = p.getFecha().pedido();
                if (p.getCodigoCliente() == c.getCodigo() 
                        && fechaPedido.get(Calendar.MONTH) == mes 
                        && fechaPedido.get(Calendar.YEAR) == anio) {
                    for (DetallePedido d : detalles) {
                        if (d.getCodigoPedido() == p.getCodigo() && c.getCodigoEmpleadoRepVentas().isPresent()) {
                            map.put(c.getCodigoEmpleadoRepVentas().get(), map.get(c.getCodigoEmpleadoRepVentas().get()) + d.getCantidad() * d.getPrecioUnidad());
                            if (d.getNumeroLinea() == 1) {
                                ventas.put(c.getCodigoEmpleadoRepVentas().get(), 1 + ventas.get(c.getCodigoEmpleadoRepVentas().get()));
                            }
                        }
                    }
                }
            }
        }

        Entry<Integer, Double> empleado = map.entrySet().stream().sorted((e1,e2)->{return e2.getValue().compareTo(e1.getValue());}).findFirst().orElse(null);
        Integer totalv = ventas.get(empleado.getKey());

        if (empleado != null && empleado.getValue() > 0.0) {
            String nombre = (String) session
                    .createSQLQuery("select nombre from empleado where codigo_empleado=" + empleado.getKey())
                    .uniqueResult();
            System.out.println("\nEl empleado del mes de " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.forLanguageTag("es-ES")) + " de " + anio + " es " + nombre + " (cód. " + empleado.getKey() + ") con " + totalv + " ventas que suman un beneficio de " + empleado.getValue() + "€");
        } else {
            System.out.println("No hay datos de ventas para el mes y año proporcionados.");
        }
    }

    @SuppressWarnings("unchecked")
    private static void mostrarDetallesDePedidos(ConexionJardineriaHql s) {
        Integer codigo = null;
        do {
            if (codigo != null) {
                System.out.println("no hay ningún cliente con ese código");
            }
            codigo = pedirEntero("código del cliente", true);
            if (codigo == null) {
                System.out.println("acción cancelada");
                return;
            }
        } while (!s.clientes().uno(codigo).isPresent());
        List<DetallePedido> detalles = session.createQuery("from Detalle where codigo_pedido in (from Pedido p where codigo_cliente=:c) order by codigo_pedido, numero_linea").setParameter("c", codigo).list();
        List<Producto> productos = s.productos().todos();

        double subtotal = 0.0;
        double total = 0.0;
        int pedidoActual = -1;

        String fechastring = "";

        System.out.println();
        for (DetallePedido d : detalles) {

            Optional<Pedido> fecha = s.pedidos().uno(d.getCodigoPedido());
            if (fecha.isPresent()) {
                Calendar cal = fecha.get().getFecha().pedido();
                fechastring = cal.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.forLanguageTag("es-ES")) + " de " + cal.get(Calendar.YEAR);
            } else {
                fechastring = "";
            }

            if (pedidoActual != d.getCodigoPedido()) {
                pedidoActual = d.getCodigoPedido();
                if (subtotal > 0.0) {
                    System.out.println(" --> Subtotal: " + subtotal + " en " + fechastring  + "\n");
                    total += subtotal;
                    subtotal = 0.0;
                }
            }

            System.out.print(d);
            Producto este = productos.stream().filter(p -> p.getCodigoProducto().equals(d.getCodigoProducto())).findAny().orElse(null);
            
            if (este != null) {
                System.out.println(" | Nombre: " + este.getNombre() + " | Gama: " + este.getGama());
            } else {
                System.out.println(" | Algún error raro hay por haí porque no se ha podido encontrar el producto");
            }
            subtotal += d.getCantidad() * d.getPrecioUnidad();
        }
        if (subtotal > 0.0) {
            System.out.println(" --> Subtotal: " + subtotal + " en " + fechastring + "\n==========================");
            total += subtotal;
            subtotal = 0.0;
        }
        if (total > 0.0) {
            System.out.println(" ==> Total: " + total);
        } else {
            System.out.println("El cliente de código " + codigo + " no tiene ningún pedido registrado.");
        }
    }

    private static void pedirCodigoYEditarProducto(ConexionJardineriaHql c) {
        Optional<Producto> optproducto;
        String codigo_producto = "";
        boolean error = false;
        do {
            if (error) {
                System.out.println("No hay ningún producto con código " + codigo_producto);
            }
            codigo_producto = pedirString("código del producto", true);
            if (codigo_producto == null) {
                System.out.println("acción cancelada");
                return;
            }
            optproducto = c.productos().uno(codigo_producto);
        } while (error = !optproducto.isPresent());

        // aqui hay producto si o si
        Producto producto = optproducto.get();
        
        Producto.Builder modificador = new Producto.Builder(producto);
        String nombre = pedirString("nombre", true),
               gama = pedirString("gama", true),
               dimensiones = pedirString("dimensiones", true),
               proveedor = pedirString("proveedor", true),
               descripcion = pedirString("descripcion", true);
        Integer cantidad = pedirEntero("cantidad_en_stock", true);
        Double precioventa = pedirDouble("precio_venta"),
               precioprov = pedirDouble("precio_proveedor");

        if (nombre != null) modificador.conNombre(nombre);
        if (gama != null) modificador.conGama(gama);
        if (dimensiones != null) modificador.conDimensiones(dimensiones);
        if (proveedor != null) modificador.conProveedor(proveedor);
        if (descripcion !=null) modificador.conDescripcion(descripcion);
        if (cantidad != null) modificador.conCantidadEnStock(cantidad);
        if (precioventa != null) modificador.conPrecioVenta(precioventa);
        if (precioprov != null) modificador.conPrecioProveedor(precioprov);

        try {
            c.productos().modificar(modificador.buildOrThrow());
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("No se ha podido modificar el producto. Es probable que haya fallado el constraint Gama");
        } catch (ExcepcionDatoNoValido e) {
            e.printStackTrace();
        }
    }

    private static void pedirPalabraYBuscarClientes(ConexionJardineriaHql c) {
        String cri = pedirString("palabra clave", true);
        if (cri == null) {
            System.out.println("acción cancelada");
            return;
        } else {
            cri = cri.toLowerCase();
        }
        final String criterio = cri;
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

    private static void mostrarTodosLosClientes(ConexionJardineriaHql c) {
        List<Cliente> clientes = c.clientes().todos();
        clientes.sort((c1, c2) -> c1.getNombre().compareTo(c2.getNombre()));
        
        if (clientes.size() > 0) System.out.println();
        for (Cliente cliente : clientes) {
            System.out.println(cliente.infoResumen());
        }
    }

    private static void pedirCodigoYMostrarCliente(ConexionJardineriaHql c) {
        Integer codigo = pedirEntero("código del cliente", true);
        if (codigo == null) {
            System.out.println("acción cancelada");
            return;
        } else if (c.clientes().uno(codigo).isPresent()) {
            System.out.println(c.clientes().uno(codigo).get());
        } else {
            System.out.println("No hay ningún cliente con código " + codigo);
        }
    }

    private static void pedirDatosYGuardarCliente(ConexionJardineriaHql c) {
        Integer codigo_cliente     = pedirEntero("codigo_cliente (null para cancelar)", true);
        if (codigo_cliente == null) {
            System.out.println("acción cancelada");
            return;
        }
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
        } catch (HibernateException e) {
            System.out.println("\nError!\n======\nExcepción Hibernate!!! vaya por dios");
        }    
    }

    // Devuelve un entero entre 0 y 7
    private static int pedirOrden() {
        boolean input_ok = false;
        int orden = 0;
        do {
            try {
                orden = pedirEntero("orden", false);
                if (orden < 0 || orden > 7) {
                    System.out.println("Orden no válida.");
                } else {
                    input_ok = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número del 0 al 7.");
            }
        } while (!input_ok);

        return orden;
    }

    // Imprime el menú
    private static void imprMenu() {
        System.out.println("\nÓrdenes válidas:\n================\n"
                + "0 -> Salir\n1 -> Añadir cliente\n2 -> Mostrar un cliente\n"
                + "3 -> Mostrar todos los clientes\n4 -> Buscar clientes\n"
                + "5 -> Editar un producto\n"
                + "6 -> Mostrar detalles de pedidos\n"
                + "7 -> Mostrar empleado del mes\n");
    }

    /**
     * Pide un entero que puede ser null o no según el parámetro booleano.
     */
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

    /** 
     * Pide un double. si el string proporcionado está en blanco,
     *  devuelve null. Si no, intenta parsearlo y devolverlo.
     */
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

    /**
     * Devuelve un string que puede ser null o no según el parámetro booleano.
     */
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
