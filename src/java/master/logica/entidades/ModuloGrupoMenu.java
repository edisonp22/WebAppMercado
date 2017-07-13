package master.logica.entidades;

import java.util.Date;

public class ModuloGrupoMenu {

    private Modulo codigoModulo;
    private Menu codigoMenu;
    private String estadoLogico;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private Date fechaBaja;

    public ModuloGrupoMenu() {
        codigoMenu = new Menu();
        codigoModulo = new Modulo();
    }

    public Modulo getCodigoModulo() {
        return codigoModulo;
    }

    public void setCodigoModulo(Modulo codigoModulo) {
        this.codigoModulo = codigoModulo;
    }

    public Menu getCodigoMenu() {
        return codigoMenu;
    }

    public void setCodigoMenu(Menu codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

    public String getEstadoLogico() {
        return estadoLogico;
    }

    public void setEstadoLogico(String estadoLogico) {
        this.estadoLogico = estadoLogico;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

}
