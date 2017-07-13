/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catastro.logica.entidades;

import java.sql.Timestamp;

/**
 *
 * @author Geovanny
 */
public class ExencionEspecial {

    private int idExccencion;
    private String excencion;
    private String descripcion;
    private String porcentajeTexto;
    private Double porcentajeNumerico;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;

    public int getIdExccencion() {
        return idExccencion;
    }

    public void setIdExccencion(int idExccencion) {
        this.idExccencion = idExccencion;
    }

    public String getExcencion() {
        return excencion;
    }

    public void setExcencion(String excencion) {
        this.excencion = excencion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPorcentajeTexto() {
        return porcentajeTexto;
    }

    public void setPorcentajeTexto(String porcentajeTexto) {
        this.porcentajeTexto = porcentajeTexto;
    }

    public Double getPorcentajeNumerico() {
        return porcentajeNumerico;
    }

    public void setPorcentajeNumerico(Double porcentajeNumerico) {
        this.porcentajeNumerico = porcentajeNumerico;
    }

    public String getEstadoLogico() {
        return estadoLogico;
    }

    public void setEstadoLogico(String estadoLogico) {
        this.estadoLogico = estadoLogico;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Timestamp getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Timestamp fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

}
