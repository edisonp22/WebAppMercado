package catastro.logica.entidades;

import java.sql.Timestamp;

public class Calle {

    private int idCalle;
    private String nombre;
    private String estadoLogico;
    private Timestamp fechaRegistro;
    private Timestamp fechaActualizacion;
    private Timestamp fechaBaja;

    public int getIdCalle() {
        return idCalle;
    }

    public void setIdCalle(int idCalle) {
        this.idCalle = idCalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
