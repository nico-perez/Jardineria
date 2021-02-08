package dev.el_nico.jardineria.dao.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import dev.el_nico.jardineria.dao.IDao;
import dev.el_nico.jardineria.excepciones.ExcepcionClienteNoEncontrado;
import dev.el_nico.jardineria.excepciones.ExcepcionCodigoYaExistente;
import dev.el_nico.jardineria.excepciones.ExcepcionDatoNoValido;
import dev.el_nico.jardineria.modelo.Pedido;

public class PedidosSqlDao implements IDao<Pedido> {

    private Connection sql;

    private ConexionJardineriaSql daos;

    public PedidosSqlDao(ConexionJardineriaSql daos) {
        this.daos = daos;
        sql = daos.getConnection();
    }

    @Override
    public Optional<Pedido> uno(Object id) {
        try (PreparedStatement stat = sql.prepareStatement("select * from pedido where codigo_pedido=?;")) {
            stat.setInt(1, (int)id);
            ResultSet res = stat.executeQuery();
            if (res.next()) {
                return sacarPedidoDeResultSet(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Pedido> todos() {
        try (PreparedStatement stat = sql.prepareStatement("select * from pedido;")) {
            ResultSet resultados = stat.executeQuery();
            List<Pedido> lista = new ArrayList<>();
            while (resultados.next()) {
                sacarPedidoDeResultSet(resultados).ifPresent(c -> lista.add(c));
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();    
            return new ArrayList<Pedido>(0);
        }
    }

    @Override
    public void guardar(Pedido t) throws Exception {
        
        try (PreparedStatement count_cod_pedido = sql.prepareStatement("select count(*) from pedido where codigo_pedido=?;");
             PreparedStatement count_cod_cliente = sql.prepareStatement("select count(*) from cliente where codigo_cliente=?;");
             PreparedStatement insert_into_pedido = sql.prepareStatement("insert into pedido values(?,?,?,?,?,?,?);")) {
            count_cod_pedido.setInt(1, t.getCodigo());
            ResultSet rs = count_cod_pedido.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) != 0) {
                    // ya hay un pedido con el codigo pedido tal
                    throw new ExcepcionCodigoYaExistente("Ya hay un pedido con código " + t.getCodigo());
                } else {

                    count_cod_cliente.setInt(1, t.getCodigoCliente());
                    ResultSet rscodcl = count_cod_cliente.executeQuery();
                    if (rscodcl.next()) {
                        if (rscodcl.getInt(1) < 1) {
                            // no hay cliente al que referir
                            throw new ExcepcionClienteNoEncontrado("No hay cliente con código " + t.getCodigoCliente()); 
                        } else {

                            insert_into_pedido.setInt(1, t.getCodigo());
                            insert_into_pedido.setDate(2, new Date(t.getFecha().pedido().getTimeInMillis()));
                            insert_into_pedido.setDate(3, new Date(t.getFecha().esperada().getTimeInMillis()));
                            t.getFecha().entrega().ifPresentOrElse(e -> {
                                try {
                                    insert_into_pedido.setDate(4, new Date(e.getTimeInMillis()));
                                } catch (SQLException e2) {
                                    e2.printStackTrace();
                                }
                            }, () -> {
                                try {
                                    insert_into_pedido.setNull(4, Types.DATE);
                                } catch (SQLException e2) {
                                    e2.printStackTrace();
                                }
                            });
                            insert_into_pedido.setString(5, t.getEstado());
                            t.getComentarios().ifPresentOrElse(c -> {
                                try {
                                    insert_into_pedido.setString(6, c);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }, () -> {
                                try {
                                    insert_into_pedido.setNull(6, Types.VARCHAR);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }); // try catch try catch trty cahtch try catch try cathc
                            insert_into_pedido.setInt(7, t.getCodigoCliente());

                            insert_into_pedido.executeUpdate();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void modificar(Pedido t) {
        
    }

    @Override
    public void eliminar(Pedido t) {
        Optional<Pedido> p = uno(t.getCodigo());
        if (p.isPresent() && p.get().equals(t)) {
            // eliminar
            try (PreparedStatement ps = sql.prepareStatement("delete from pedido where codigo_pedido=?;")) {
                ps.setInt(1, t.getCodigo());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // nada 
        }
    }
    
    private Optional<Pedido> sacarPedidoDeResultSet(ResultSet sqlQuery) throws SQLException {
        if (sqlQuery != null) {
            
            int codigo_pedido = sqlQuery.getInt("codigo_pedido");
            Date date_fecha_pedido = sqlQuery.getDate("fecha_pedido");
            Date date_fecha_esperada = sqlQuery.getDate("fecha_esperada");
            Date date_fecha_entrega = sqlQuery.getDate("fecha_entrega");
            Calendar fecha_pedido = date_fecha_pedido == null ? null : new Calendar.Builder().setInstant(date_fecha_pedido).build();
            Calendar fecha_esperada = date_fecha_esperada == null ? null : new Calendar.Builder().setInstant(date_fecha_esperada).build();
            Calendar fecha_entrega = date_fecha_entrega == null ? null : new Calendar.Builder().setInstant(date_fecha_entrega).build();
            String estado = sqlQuery.getString("estado");
            String comentarios = sqlQuery.getString("comentarios");
            int codigo_cliente = sqlQuery.getInt("codigo_cliente");

            Pedido pedido;
            try {
                pedido = new Pedido.Builder(codigo_pedido, fecha_pedido, fecha_esperada, estado, daos.clientes().uno(codigo_cliente).orElse(null))
                        .conFechaDeEntrega(fecha_entrega).conComentarios(comentarios).buildOrThrow();
            } catch (ExcepcionDatoNoValido e) {
                pedido = null;
            }

            return Optional.ofNullable(pedido);
        } else {
            return Optional.empty();
        }
    }
}
