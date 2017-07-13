package master.presentacion.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import master.logica.entidades.Menu;
import master.logica.entidades.Modulo;
import master.logica.entidades.ModuloGrupoMenu;
import master.logica.entidades.NodoMenu;
import master.logica.servicios.ServiciosMenu;
import master.logica.servicios.ServiciosModulo;
import master.logica.servicios.ServiciosModuloGrupoMenu;
import master.logica.servicios.ServiciosNodoMenu;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

@ManagedBean
@ViewScoped
public class MenuCtrl implements Serializable {

    private ArrayList<Menu> lstMenusModulos;
    private ArrayList<Menu> lstSubmenus;
    private ArrayList<Menu> lstItems;
    private ArrayList<ModuloGrupoMenu> lstModulosGruposMenus;
    private ArrayList<Modulo> lstModulos;
    private Menu menu;
    private Menu menuSel;
    private Modulo modulo;
    private int idModuloSel;
    private int idMenuPadre;
    private int idSubmenu;

    public MenuCtrl() {
        menu = new Menu();
        menuSel = new Menu();
        modulo = new Modulo();
        lstMenusModulos = new ArrayList<>();
        lstSubmenus = new ArrayList<>();
        lstItems = new ArrayList<>();
        lstModulosGruposMenus = new ArrayList<>();
        lstModulos = new ArrayList<>();

    }

    @PostConstruct
    public void init() {
        obtenerMenusModulos();
        obtenerModulos();

    }

    public void obtenerMenusModulos() {
        try {
            lstModulosGruposMenus = ServiciosModuloGrupoMenu.obtenerModulosMenusDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerMenusModulos() dice: " + e.getMessage());
        }
    }

    public void obtenerModulos() {
        try {
            lstModulos = ServiciosModulo.obtenerModulosDadoEstado("A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerModulos() dice: " + e.getMessage());
        }
    }

    public void obtenerSubmenusDadoPadre() {
        try {
            lstSubmenus = ServiciosMenu.obtenerMenusDadoPadreEstado(idMenuPadre, "A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerSubmenusDadoPadre() dice: " + e.getMessage());
        }
    }

    public void obtenerItemsDadoSubmenu() {
        try {
            lstItems = ServiciosMenu.obtenerMenusDadoPadreEstado(idSubmenu, "A");
        } catch (Exception e) {
            Util.addErrorMessage("public void obtenerItemsDadoSubmenu() dice: " + e.getMessage());
        }
    }

    public void insertarMenuCeroDadoModulo() {
        try {
            modulo.setIdModulo(idModuloSel);
            String msg = ServiciosMenu.registrarMenuCeroDadoModulo(menu, modulo);
            Util.addSuccessMessage(msg);
            obtenerMenusModulos();
            resetearFitrosTabla("frmMenuNivelCero:tblMenus");
            menu = new Menu();
            modulo = new Modulo();
            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarMenuCeroDadoModulo() dice: " + e.getMessage());
        }
    }

    public void insertarMenuDadoPadre() {
        try {
            menu.setCodigoPadre(idMenuPadre);
            String msg = ServiciosMenu.registrarMenuDadoPadre(menu);
            Util.addSuccessMessage(msg);
            obtenerSubmenusDadoPadre();
            resetearFitrosTabla("frmSubmenus:tblMenus");
            menu = new Menu();

            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarMenuCeroDadoModulo() dice: " + e.getMessage());
        }
    }
    
        public void insertarItemsDadoSubmenu() {
        try {
            menu.setCodigoPadre(idSubmenu);
            String msg = ServiciosMenu.registrarMenuDadoPadre(menu);
            Util.addSuccessMessage(msg);
            obtenerItemsDadoSubmenu();
            resetearFitrosTabla("frmItems:tblMenus");
            menu = new Menu();

            DefaultRequestContext.getCurrentInstance().execute("PF('dlgRegistrar').hide()");
        } catch (Exception e) {
            Util.addErrorMessage("public void insertarMenuCeroDadoModulo() dice: " + e.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Metodo para resetear el datatable">    
    public void resetearFitrosTabla(String id) {
        DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(id);
        table.reset();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters y Setter">   
    public ArrayList<Menu> getLstMenusModulos() {
        return lstMenusModulos;
    }

    public int getIdSubmenu() {
        return idSubmenu;
    }

    public void setIdSubmenu(int idSubmenu) {
        this.idSubmenu = idSubmenu;
    }

    public int getIdMenuPadre() {
        return idMenuPadre;
    }

    public void setIdMenuPadre(int idMenuPadre) {
        this.idMenuPadre = idMenuPadre;
    }

    public int getIdModuloSel() {
        return idModuloSel;
    }

    public void setIdModuloSel(int idModuloSel) {
        this.idModuloSel = idModuloSel;
    }

    public void setLstMenusModulos(ArrayList<Menu> lstMenusModulos) {
        this.lstMenusModulos = lstMenusModulos;
    }

    public ArrayList<Menu> getLstSubmenus() {
        return lstSubmenus;
    }

    public void setLstSubmenus(ArrayList<Menu> lstSubmenus) {
        this.lstSubmenus = lstSubmenus;
    }

    public ArrayList<Menu> getLstItems() {
        return lstItems;
    }

    public void setLstItems(ArrayList<Menu> lstItems) {
        this.lstItems = lstItems;
    }

    public ArrayList<ModuloGrupoMenu> getLstModulosGruposMenus() {
        return lstModulosGruposMenus;
    }

    public void setLstModulosGruposMenus(ArrayList<ModuloGrupoMenu> lstModulosGruposMenus) {
        this.lstModulosGruposMenus = lstModulosGruposMenus;
    }

    public ArrayList<Modulo> getLstModulos() {
        return lstModulos;
    }

    public void setLstModulos(ArrayList<Modulo> lstModulos) {
        this.lstModulos = lstModulos;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenuSel() {
        return menuSel;
    }

    public void setMenuSel(Menu menuSel) {
        this.menuSel = menuSel;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
//</editor-fold>
}
