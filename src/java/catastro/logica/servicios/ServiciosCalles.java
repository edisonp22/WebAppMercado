/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.servicios;

import accesoDatos.AccesoDatos;
import catastro.logica.entidades.Calle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Geovanny
 */
public class ServiciosCalles {

    public static ArrayList<Calle> obtenerCalles() throws Exception {
        ArrayList<Calle> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Calle calle;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * from catastro.f_seleccionar_calles();";
            resultSet = accesoDatos.ejecutaQuery(sql);
            while (resultSet.next()) {
                calle = new Calle();
                calle.setIdCalle(resultSet.getInt("sr_id_calle"));
                calle.setNombre(resultSet.getString("chv_nombre"));
                calle.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                calle.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                calle.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                calle.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
                lst.add(calle);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static Calle obtenerCalleDadoCodigo(int codigo) throws Exception {
        Calle calle = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM catastro.f_seleccionar_calle_dado_codigo(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, codigo);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                calle = new Calle();
                calle.setIdCalle(resultSet.getInt("sr_id_calle"));
                calle.setNombre(resultSet.getString("chv_nombre"));
                calle.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                calle.setFechaRegistro(resultSet.getTimestamp("ts_fecha_registro"));
                calle.setFechaActualizacion(resultSet.getTimestamp("ts_fecha_actualizacion"));
                calle.setFechaBaja(resultSet.getTimestamp("ts_fecha_baja"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return calle;
    }
}
