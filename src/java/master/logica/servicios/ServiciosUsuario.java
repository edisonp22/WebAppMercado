package master.logica.servicios;

import accesoDatos.AccesoDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import master.logica.entidades.Usuario;

public class ServiciosUsuario {

    public static Usuario buscarUsuarioDadoId(int idUsuario) throws Exception {
        Usuario usuario = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM master.f_seleccionar_usuario_dado_id(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setInt(1, idUsuario);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdPersona(resultSet.getInt("sr_id_persona"));
                usuario.setNombres(resultSet.getString("chv_nombres"));
                usuario.setApellidos(resultSet.getString("chv_apellidos"));
                usuario.setCelular(resultSet.getString("chv_celular"));
                usuario.setFoto(resultSet.getString("chv_foto"));
                usuario.setCedula(resultSet.getString("chv_cedula"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                usuario.setPassword(resultSet.getString("chv_password"));
                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    public static ArrayList<Usuario> obtenerUsuariosDadoEstado(String estado) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        AccesoDatos accesoDatos;
        PreparedStatement prstm;
        Usuario usuario;
        ResultSet resultSet;
        String sql;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_seleccionar_usuarios(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, estado);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdPersona(resultSet.getInt("sr_id_persona"));
                usuario.setCedula(resultSet.getString("chv_cedula"));
                usuario.setNombres(resultSet.getString("chv_nombres"));
                usuario.setApellidos(resultSet.getString("chv_apellidos"));
                usuario.setCelular(resultSet.getString("chv_celular"));
                usuario.setFoto(resultSet.getString("chv_foto"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                usuario.setPassword(resultSet.getString("chv_password"));
                usuario.setEstadoLogico(resultSet.getString("ch_estado_logico"));
                usuario.setFechaRegistro(resultSet.getDate("ts_fecha_registro"));
                usuario.setFechaActualizacion(resultSet.getDate("ts_fecha_actualizacion"));
                usuario.setFechaBaja(resultSet.getDate("ts_fecha_baja"));
                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
                usuario.setSexo(resultSet.getString("ch_sexo"));
                lst.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lst;
    }

    public static String registrarUsuario(Usuario usuario) throws Exception {
        String respuesta;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "select * from master.f_insertar_persona(?,?,?,?,?,?,?,?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, usuario.getCedula());
            prstm.setString(2, usuario.getNombres());
            prstm.setString(3, usuario.getApellidos());
            prstm.setString(4, usuario.getCelular());
            prstm.setString(5, usuario.getNick());
            prstm.setString(6, usuario.getMail());
            prstm.setString(7, usuario.getPassword());
            prstm.setString(8, usuario.getSexo());
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

    public static Usuario buscarUsuarioDadoCedula(String cedula) throws Exception {
        Usuario usuario = null;
        AccesoDatos accesoDatos;
        String sql;
        PreparedStatement prstm;
        ResultSet resultSet;
        try {
            accesoDatos = new AccesoDatos();
            sql = "SELECT * FROM master.f_buscar_persona_usuario_dado_cedula(?)";
            prstm = accesoDatos.creaPreparedSmt(sql);
            prstm.setString(1, cedula);
            resultSet = accesoDatos.ejecutaPrepared(prstm);
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdPersona(resultSet.getInt("sr_id_persona"));
                usuario.setNombres(resultSet.getString("chv_nombres"));
                usuario.setApellidos(resultSet.getString("chv_apellidos"));
                usuario.setCelular(resultSet.getString("chv_celular"));
                usuario.setFoto(resultSet.getString("chv_foto"));
                usuario.setCedula(resultSet.getString("chv_cedula"));
                usuario.setRuc(resultSet.getString("chv_ruc"));
                usuario.setNick(resultSet.getString("chv_nick"));
                usuario.setMail(resultSet.getString("chv_mail"));
                usuario.setPassword(resultSet.getString("chv_password"));
                usuario.setUltimoAcceso(resultSet.getDate("ts_ultimo_acceso"));
                usuario.setUltimaIp(resultSet.getString("chv_ultima_ip"));
                usuario.setEstadoCivil(resultSet.getString("ch_estado_civil"));
                usuario.setSexo(resultSet.getString("ch_sexo"));
                usuario.setFechaNacimiento(resultSet.getDate("ts_fecha_nacimiento"));
            }
            accesoDatos.desconectar();
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }
    
}
