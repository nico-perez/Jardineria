package dev.el_nico.jardineria.dao;

import dev.el_nico.jardineria.modelo.Cliente;
import dev.el_nico.jardineria.modelo.DetallePedido;
import dev.el_nico.jardineria.modelo.Empleado;
import dev.el_nico.jardineria.modelo.Pedido;
import dev.el_nico.jardineria.modelo.Producto;

public class DaoHolder {
    
    protected IDao<Cliente> clientesDao;
    protected IDao<Pedido> pedidosDao;
    protected IDao<Producto> productosDao;
    protected IDao<Empleado> empleadosDao;
    protected IDao<DetallePedido> detallesPedidoDao;

    protected DaoHolder() {}

    public DaoHolder(IDao<Cliente> clientesDao, IDao<Pedido> pedidosDao) {
        this.clientesDao = clientesDao;
        this.pedidosDao = pedidosDao;
    }

    public DaoHolder(IDao<Cliente> clientesDao, IDao<Pedido> pedidosDao, IDao<Producto> productosDao,
            IDao<Empleado> empleadosDao, IDao<DetallePedido> detallesPedidoDao) {
        this.clientesDao = clientesDao;
        this.pedidosDao = pedidosDao;
        this.productosDao = productosDao;
        this.empleadosDao = empleadosDao;
        this.detallesPedidoDao = detallesPedidoDao;
    }
    
    @SuppressWarnings("unchecked")
    public <T extends IDao<Cliente>> T clientes() {
        return (T) clientesDao;
    }

    @SuppressWarnings("unchecked")
    public <T extends IDao<Pedido>> T pedidos() {
        return (T) pedidosDao;
    }

    @SuppressWarnings("unchecked")
    public <T extends IDao<Producto>> T productos() {
        return (T) productosDao;
    }

    @SuppressWarnings("unchecked")
    public <T extends IDao<Empleado>> T empleados() {
        return (T) empleadosDao;
    }

    @SuppressWarnings("unchecked")
    public <T extends IDao<DetallePedido>> T detalles() {
        return (T) detallesPedidoDao;
    }
}
