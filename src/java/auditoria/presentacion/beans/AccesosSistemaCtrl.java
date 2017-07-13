package auditoria.presentacion.beans;

import auditoria.logica.entidades.AccesoSistema;
import auditoria.logica.servicios.ServiciosAccesosUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import recursos.Util;

@ManagedBean
@ViewScoped
public class AccesosSistemaCtrl implements Serializable {

    private ArrayList<AccesoSistema> lstAccesos;

    public AccesosSistemaCtrl() {
        lstAccesos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        obtenerAccesos();
    }

    public void obtenerAccesos() {
        try {
            lstAccesos = ServiciosAccesosUsuario.obtenerAccesosSistema();
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerAccesos() dice: " + e.getMessage());
        }
    }

    public ArrayList<AccesoSistema> getLstAccesos() {
        return lstAccesos;
    }

    public void setLstAccesos(ArrayList<AccesoSistema> lstAccesos) {
        this.lstAccesos = lstAccesos;
    }

}
