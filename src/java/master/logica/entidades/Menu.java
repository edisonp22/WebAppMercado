package master.logica.entidades;

import java.util.Date;

public class Menu {

    private int idMenu;
    private String nombre;
    private String descripcion;
    private String icono;
    private int codigoPadre;
    private String estadoLogico;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private Date fechaBaja;
    private boolean nivelCero;

    public Menu() {

    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public int getCodigoPadre() {
        return codigoPadre;
    }

    public void setCodigoPadre(int codigoPadre) {
        this.codigoPadre = codigoPadre;
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

    public boolean isNivelCero() {
        return nivelCero;
    }

    public void setNivelCero(boolean nivelCero) {
        this.nivelCero = nivelCero;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

}
