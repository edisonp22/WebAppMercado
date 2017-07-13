package master.presentacion.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosUsuario;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class UsuarioCtrl implements Serializable {

    private ArrayList<Usuario> lstUsuarios;
    private Usuario usuario;
    private Usuario usuarioSel;

    public UsuarioCtrl() {
        usuario = new Usuario();
        usuarioSel = new Usuario();
    }

    @PostConstruct
    public void init() {
        obtenerUsuarios();
    }

    public void obtenerUsuarios() {
        try {
            lstUsuarios = ServiciosUsuario.obtenerUsuariosDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerUsuarios() dice: " + e.getMessage());
        }
    }

    public void insertarUsuario() {
        try {
            String msg = ServiciosUsuario.registrarUsuario(usuario);
            Util.addSuccessMessage(msg);            
            obtenerUsuarios();
            resetearFitrosTabla("frmUsuarios:tblUsuarios");
            usuario = new Usuario();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarUsuario() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters y Setter">  
    public ArrayList<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSel() {
        return usuarioSel;
    }

    public void setUsuarioSel(Usuario usuarioSel) {
        this.usuarioSel = usuarioSel;
    }

    public void setLstUsuarios(ArrayList<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>
}
