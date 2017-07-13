package master.presentacion.beans;

import auditoria.logica.servicios.ServiciosAccesosUsuario;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.internal.objects.NativeFunction.function;
import master.logica.entidades.Persona;
import master.logica.entidades.Usuario;
import master.logica.servicios.ServiciosLogin;
import org.apache.taglibs.standard.functions.Functions;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import recursos.ObtenerIPs;
import recursos.Util;

@Named
@ManagedBean
@RequestScoped
public class LoginCtrl {

    private Usuario usuario;
    private Usuario usuarioSel;

    //private Usuario usuarioLogueado;
    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private String path;
    private String foto;
    private String cedula;
    //manejo de imagenes
    private String nombreImagen;
    private UploadedFile archivoImagen;
    //cargar configuracion del  path
    java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("recursos.rutasMedia");

    public LoginCtrl() {
        usuario = new Usuario();
        usuarioSel = new Usuario();
        //usuarioLogueado= new Usuario();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();

    }

//    @PostConstruct
//    public void init() {
//        obtenerPersona();
//
//    }
    public void login() {
        //String redireccion = null;
        try {
            Usuario usuarioLogueado = ServiciosLogin.loginUsuario(usuario.getMail(), usuario.getPassword());
            
            if (usuarioLogueado != null) {
                httpServletRequest.getSession().setAttribute("usLogueado", usuarioLogueado);
                httpServletRequest.getSession().setAttribute("foto", usuarioLogueado.getFoto());
                httpServletRequest.getSession().setAttribute("idUsuarioLog", usuarioLogueado.getIdPersona());
                httpServletRequest.getSession().setAttribute("nombre", usuarioLogueado.getNombres().toUpperCase() + " " + usuarioLogueado.getApellidos().toUpperCase());
                httpServletRequest.getSession().setAttribute("ultimoAcceso", usuarioLogueado.getUltimoAcceso());
                httpServletRequest.getSession().setAttribute("ultimaIp", usuarioLogueado.getUltimaIp());
                faceContext.getExternalContext().redirect("seguridad/rol.xhtml");
                //redireccion = "seguridad/rol";    
                registarAcceso();
                usuarioSel=usuarioLogueado;
                cedula=usuarioSel.getCedula();
                System.out.println(cedula);
                System.out.println(usuarioSel.getApellidos());
//                obtenerPersona();
            } else {
                faceContext.getExternalContext().redirect("login.jsf");
                //redireccion = "index";
            }
        } catch (Exception e) {
            Util.addErrorMessage("Iniciar session dice: " + e.getMessage());
        }
        // return redireccion;

    }

    public void registarAcceso() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String ipLocal = request.getRemoteAddr();
            String hostname = request.getRemoteHost();
            int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuarioLog");
            String registro = ServiciosAccesosUsuario.registrarAccesoSistema(intIdUsuario, ipLocal, ipLocal, hostname);
            System.out.println(registro);

        } catch (Exception e) {
            System.out.println("public void registarAcceso() dice: " + e.getMessage());
        }
    }

    public void cerrarSesion() {
        try {
            int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuarioLog");
            String registro = ServiciosAccesosUsuario.registrarSalidaSistema(intIdUsuario);
            System.out.println(registro);
            httpServletRequest.getSession().removeAttribute("usLogueado");
            httpServletRequest.getSession().removeAttribute("nombre");
            httpServletRequest.getSession().removeAttribute("ultimaIp");
            httpServletRequest.getSession().removeAttribute("ultimoAcceso");

            System.gc();  //limpiar todo
            FacesContext fc = FacesContext.getCurrentInstance();

            Util.addSuccessMessage("Sesión cerrada");
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();

            fc.getExternalContext().redirect("/WebAppArchidona_v2");
            fc.getExternalContext().invalidateSession();

        } catch (Exception e) {
            System.out.println("Cerra Sesion dice: " + e.getMessage());
            Util.addErrorMessage("Cerra Sesion dice: " + e.getMessage());
        }
    }

    // manejo de imagenes
    private byte[] getFileContents(InputStream in) {
        byte[] bytes = null;
        try {
            // write the inputStream to a FileOutputStream            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int read = 0;
            bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                bos.write(bytes, 0, read);
            }
            bytes = bos.toByteArray();
            in.close();
            in = null;
            bos.flush();
            bos.close();
            bos = null;
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bytes;
    }

    public boolean guardarArchivo(String nombre, byte[] contenido) {
        //int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuarioLog");
        String rutaImagenes = Configuracion.getString("rutaFotos");
        int longitudRelativa = Integer.valueOf(Configuracion.getString("logitudRelativa"));
        File f = new File(rutaImagenes + nombre);
        try {
            System.out.println("PATH: " + f.getAbsolutePath());
            //agrego el path del icono del rol
            usuario.setFoto(Functions.substring(f.getAbsolutePath(), longitudRelativa, f.getAbsolutePath().length()));
            path = usuario.getFoto();
            path = path.replace('\\', '/');
            //usuario.setFoto(path);
            System.out.println(path);
            System.out.println("cargar objeto fos ");
            FileOutputStream fos = new FileOutputStream(f);
            System.out.println("escribir fos ");
            fos.write(contenido);
            return true;
        } catch (Exception e) {
            Util.mostrarMensaje(e.getMessage());
            return false;
        }
    }

    public void cargarArchivoImagen(FileUploadEvent e) {
        System.out.println("Entra al método cargar imagen");
        UploadedFile file = e.getFile();
        this.archivoImagen = file;
        System.out.println(file.getContentType());
        //objImagen.setTipo(file.getContentType());
        System.out.println(file.getSize());
        System.out.println(file.getFileName());
        nombreImagen = file.getFileName();
        //byte[] contenido = file.getContents();
        byte[] contenido;
        try {
            contenido = this.getFileContents(e.getFile().getInputstream());
            if (guardarArchivo(file.getFileName(), contenido)) {
                foto = path;
                Util.addSuccessMessage("Imagen guardada con éxito!!");
            } else {
                Util.addErrorMessage("Error al cargar la imagen");
            }
        } catch (IOException ex) {
            Logger.getLogger(RolCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarImagen() {
        System.out.println(foto);

        try {

            int intIdUsuario = (int) httpServletRequest.getSession().getAttribute("idUsuarioLog");
            usuario.setIdPersona(intIdUsuario);
            usuario.setFoto(foto);
            System.out.println(usuario.getFoto());
            String respuesta = ServiciosLogin.actualizarFoto(usuario);
            Util.addSuccessMessage(respuesta);
//            obtenerPersona();
            //resetearFitrosTabla("frmRoles:tblRoles");
            usuario = new Usuario();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizarImagen').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizar() dice: " + e.getMessage());
        }
    }
    
    public void actualizarPerfil() {
        try {
            //usuario.setFechaNacimiento(recursos.ConvertirFechas.devolverFecha(fechaNacimiento));
            String msgBd = ServiciosLogin.actualizarPerfil(usuario);
            Util.addSuccessMessage(msgBd);
            //obtenerUsuarios();
            //resetearFitrosTabla("frmUsuarios:tblUsuarios");
            usuario = new Usuario();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgActualizar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void actualizarPerfil() dice: " + e.getMessage());
        }
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

       
    public Usuario getUsuarioSel() {
        return usuarioSel;
    }

    public void setUsuarioSel(Usuario usuarioSel) {
        this.usuarioSel = usuarioSel;
    }

    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public UploadedFile getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(UploadedFile archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public ResourceBundle getConfiguracion() {
        return Configuracion;
    }

    public void setConfiguracion(ResourceBundle Configuracion) {
        this.Configuracion = Configuracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public FacesContext getFaceContext() {
        return faceContext;
    }

    public void setFaceContext(FacesContext faceContext) {
        this.faceContext = faceContext;
    }

}
