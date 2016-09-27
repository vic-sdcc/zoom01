/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class Navigation implements Serializable {

    /**
     * Creates a new instance of Navigation
     */
    public Navigation() {
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("navigation", null);
    }

    public void navigateToMainCreate() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("create?faces-redirect=true");
    }

    public void navigateToWizard0() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("wizard0?faces-redirect=true");
    }

    public void navigateToWizard1() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("wizard1?faces-redirect=true");
    }

    public void navigateToMainUpdate() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("update?faces-redirect=true");
    }

    public void navigateToTabView0() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("tabView0?faces-redirect=true");
    }

    public void navigateToTabView1() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("tabView1?faces-redirect=true");
    }

    public void mainMember() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("view?faces-redirect=true");
    }

    public void viewRegular() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("viewRegularPage?faces-redirect=true");
    }

    public void viewAssociate() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("viewAssociatePage?faces-redirect=true");
    }

    public void printPage() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("printMemberPage?faces-redirect=true");
    }

    public void printRecord() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("printMemberRecord?faces-redirect=true");
    }

    public void viewMainPage() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("viewMultiple?faces-redirect=true");
    }

    public void printMember() {
        ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        configurableNavigationHandler.performNavigation("printMemberList?faces-redirect=true");
    }

}
