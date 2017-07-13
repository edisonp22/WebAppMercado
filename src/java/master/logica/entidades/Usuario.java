/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master.logica.entidades;

import java.util.Date;

/**
 *
 * @author Geovanny
 */
public class Usuario extends Persona {

    private String nick;
    private String mail;
    private String password;
    private Date ultimoAcceso;
    private String ultimaIp;

    public Usuario() {
    }

    public Usuario(String nick, String mail, String password, Date ultimoAcceso, String ultimaIp) {
        this.nick = nick;
        this.mail = mail;
        this.password = password;
        this.ultimoAcceso = ultimoAcceso;
        this.ultimaIp = ultimaIp;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getUltimaIp() {
        return ultimaIp;
    }

    public void setUltimaIp(String ultimaIp) {
        this.ultimaIp = ultimaIp;
    }

}
