package master.presentacion.beans;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import master.logica.entidades.Accion;
import master.logica.servicios.ServiciosAccion;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class AccionCtrl {

    private ArrayList<Accion> lstAcciones;
    private Accion accion;
    private Accion accionSel;

    public AccionCtrl() {
        accion = new Accion();
        accionSel = new Accion();
        lstAcciones = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        obtenerAcciones();
    }

    public void obtenerAcciones() {
        try {
            //lstAcciones = ServiciosAccion.obtenerAcciones();
            lstAcciones = ServiciosAccion.obtenerAccionesDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerAcciones() dice: " + e.getMessage());
        }
    }

    public void obtenerAccionesActivas() {
        try {
            lstAcciones = ServiciosAccion.obtenerAccionesDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerAcciones() dice: " + e.getMessage());
        }
    }

    public void registrar() {
        try {
            String respuesta = ServiciosAccion.registrarAlerta(accion);
            Util.addSuccessMessage(respuesta);
            obtenerAcciones();
            resetearFitrosTabla("frmAcciones:tblAcciones");
            accion = new Accion();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void registrar() dice: " + e.getMessage());
        }
    }

    public void actualizar() {
        try {
            String respuesta = ServiciosAccion.actualizarAlerta(accionSel);
            Util.addSuccessMessage(respuesta);
            obtenerAcciones();
            resetearFitrosTabla("frmAcciones:tblAcciones");
            accionSel = new Accion();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizar() dice: " + e.getMessage());
        }
    }

    public void eliminar() {
        try {
            String respuesta = ServiciosAccion.eliminarAccion(accionSel);
            Util.addSuccessMessage(respuesta);
            obtenerAcciones();
            resetearFitrosTabla("frmAcciones:tblAcciones");
            accionSel = new Accion();
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

    //<editor-fold defaultstate="collapsed" desc="Métodos Get y Set"> 
    public ArrayList<Accion> getLstAcciones() {
        return lstAcciones;
    }

    public void setLstAcciones(ArrayList<Accion> lstAcciones) {
        this.lstAcciones = lstAcciones;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Accion getAccionSel() {
        return accionSel;
    }

    public void setAccionSel(Accion accionSel) {
        this.accionSel = accionSel;
    }
    //</editor-fold> 
}
