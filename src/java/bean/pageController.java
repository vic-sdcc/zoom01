/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class pageController implements Serializable{

    public pageController() {

    }

    private String viewTabStr;

    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pageController", null);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public void viewTabName(TabChangeEvent event) {
        setViewTabStr(event.getTab().getTitle());
    }

    public String getViewTabStr() {
        if (viewTabStr == null) {
            viewTabStr = "Personal";
        }
        return viewTabStr;
    }

    public void setViewTabStr(String viewTabStr) {
        this.viewTabStr = viewTabStr;
    }
}
