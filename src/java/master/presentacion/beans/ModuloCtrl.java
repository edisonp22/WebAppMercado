package master.presentacion.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import master.logica.entidades.Modulo;
import master.logica.servicios.ServiciosModulo;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class ModuloCtrl implements Serializable {

    private ArrayList<Modulo> lstModulos;
    private Modulo modulo;
    private Modulo moduloSel;

    public ModuloCtrl() {
        modulo = new Modulo();
        moduloSel = new Modulo();
        lstModulos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        obtenerModulos();
    }

    public void obtenerModulos() {
        try {
            lstModulos = ServiciosModulo.obtenerModulosDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerModulos() dice: " + e.getMessage());
        }
    }

    public void registrar() {
        try {
            String msgBd = ServiciosModulo.registrarModulo(modulo);
            Util.addSuccessMessage(msgBd);
            obtenerModulos();
            resetearFitrosTabla("frmGestionModulos:tblModulos");
            modulo = new Modulo();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void registrar() dice: " + e.getMessage());
        }
    }

    public void actualizar() {
        try {
            String msgBd = ServiciosModulo.actualizarModulo(moduloSel);
            Util.addSuccessMessage(msgBd);
            obtenerModulos();
            resetearFitrosTabla("frmGestionModulos:tblModulos");
            moduloSel = new Modulo();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizar() dice: " + e.getMessage());
        }
    }

    public void eliminar() {
        try {
            String msgBd = ServiciosModulo.eliminarModulo(moduloSel.getIdModulo());
            Util.addSuccessMessage(msgBd);
            obtenerModulos();
            resetearFitrosTabla("frmGestionModulos:tblModulos");
            moduloSel = new Modulo();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgEliminar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void eliminar() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    public ArrayList<Modulo> getLstModulos() {
        return lstModulos;
    }

    public void setLstModulos(ArrayList<Modulo> lstModulos) {
        this.lstModulos = lstModulos;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Modulo getModuloSel() {
        return moduloSel;
    }

    public void setModuloSel(Modulo moduloSel) {
        this.moduloSel = moduloSel;
    }

}
