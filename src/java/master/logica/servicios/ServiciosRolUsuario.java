/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Rol;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;

/**
 *
 * @author Geovanny
 */
public class ServiciosRolUsuario {
    
    public static ArrayList<RolUsuario> listarRolesUsuario(int idUsuario) throws Exception {
        ArrayList<RolUsuario> lst = new ArrayList<>();
        RolUsuario rolUsuario;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_roles_usuario(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rolUsuario = new RolUsuario();
                rolUsuario.setIdRol(new Rol());
                rolUsuario.setIdUsuario(new Usuario());
                rolUsuario.getIdRol().setIdRol(resultSet.getInt("int_id_rol"));
                rolUsuario.getIdRol().setNombre(resultSet.getString("rol"));
                rolUsuario.getIdRol().setIcono(resultSet.getString("icono_rol"));
                rolUsuario.getIdUsuario().setIdPersona(resultSet.getInt("int_id_usuario"));
                rolUsuario.getIdRol().getIdModulo().setNombre(resultSet.getString("modulo"));
                rolUsuario.getIdRol().getIdModulo().setIdModulo(resultSet.getInt("int_id_modulo"));
                lst.add(rolUsuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    public static ArrayList<RolUsuario> listarRolesUsuarioDadoEstado(String estado) throws Exception {
        ArrayList<RolUsuario> lst = new ArrayList<>();
        RolUsuario rolUsuario;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_roles_usuarios_dado_estado(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                rolUsuario = new RolUsuario();
                rolUsuario.setIdRol(new Rol());
                rolUsuario.setIdUsuario(new Usuario());
                rolUsuario.setIdRol(ServiciosRoles.obtenerRolDadoCodigo(resultSet.getInt("int_id_rol")));
                rolUsuario.setIdUsuario(ServiciosUsuario.buscarUsuarioDadoId(resultSet.getInt("int_id_usuario")));
                rolUsuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                lst.add(rolUsuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }
    
    public static String registrarRolUsuario(RolUsuario ru) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_rol_usuario(?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, ru.getIdRol().getIdRol());
            prstm.setInt(2, ru.getIdUsuario().getIdPersona());
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            if (resultSet.next()) {
                respuesta = resultSet.getString(1);
                return respuesta;
            } else {
                return null;
            }

        } catch (Exception e) {
            throw e;
        }

    }
    
    
}
