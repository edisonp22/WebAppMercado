package master.presentacion.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import master.logica.entidades.Rol;
import master.logica.entidades.RolUsuario;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosRolUsuario;
import master.logica.servicios.ServiciosRoles;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class RolUsuarioCtrl implements Serializable {

    private ArrayList<RolUsuario> lstRolesUsuarios;
    private RolUsuario rolUsuario;
    private RolUsuario rolUsuarioSel;
    private ArrayList<Rol> lstRoles;
    private ArrayList<Usuario> lstUsuarios;
    private int idRol;
    private int idUsuario;

    public RolUsuarioCtrl() {
        rolUsuario = new RolUsuario();
        rolUsuarioSel = new RolUsuario();
    }

    @PostConstruct
    public void init() {
        obtenerRolesUs();
        obtenerRoles();
        obtenerUsuarios();
    }

    public void obtenerRolesUs() {
        try {
            lstRolesUsuarios = ServiciosRolUsuario.listarRolesUsuarioDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerRolesUs() dice: " + e.getMessage());
        }
    }

    public void obtenerRoles() {
        try {
            lstRoles = ServiciosRoles.obtenerRolesDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerRolesUs() dice: " + e.getMessage());
        }
    }

    public void obtenerUsuarios() {
        try {
            lstUsuarios = ServiciosUsuario.obtenerUsuariosDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerRolesUs() dice: " + e.getMessage());
        }
    }

    public void registrar() {
        try {
            rolUsuario.getIdRol().setIdRol(idRol);
            rolUsuario.getIdUsuario().setIdPersona(idUsuario);
            String respuesta = ServiciosRolUsuario.registrarRolUsuario(rolUsuario);
            Util.addSuccessMessage(respuesta);
            obtenerRolesUs();
            resetearFitrosTabla("frmRolUsuario:tblRolesUsuarios");
            rolUsuario = new RolUsuario();
            idRol = 0;
            idUsuario = 0;
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void registrar() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters y Setter">
    public void setRolUsuarioSel(RolUsuario rolUsuarioSel) {
        this.rolUsuarioSel = rolUsuarioSel;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<RolUsuario> getLstRolesUsuarios() {
        return lstRolesUsuarios;
    }

    public void setLstRolesUsuarios(ArrayList<RolUsuario> lstRolesUsuarios) {
        this.lstRolesUsuarios = lstRolesUsuarios;
    }

    public ArrayList<Rol> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(ArrayList<Rol> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public ArrayList<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(ArrayList<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public RolUsuario getRolUsuarioSel() {
        return rolUsuarioSel;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>
}
