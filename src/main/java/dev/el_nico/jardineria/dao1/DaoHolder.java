package dev.el_nico.jardineria.dao1;

import dev.el_nico.jardineria.modelo1.Cliente;
import dev.el_nico.jardineria.modelo1.DetallePedido;
import dev.el_nico.jardineria.modelo1.Empleado;
import dev.el_nico.jardineria.modelo1.Pedido;
import dev.el_nico.jardineria.modelo1.Producto;

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
