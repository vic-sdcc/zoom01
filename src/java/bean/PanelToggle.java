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

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class PanelToggle implements Serializable {

    /**
     * Creates a new instance of PanelToggle
     */
    public PanelToggle() {
    }

    private boolean add2, add3, add4;

    /*
     * getter setter
     */
    public boolean isAdd2() {
        return add2;
    }

    public void setAdd2(boolean add2) {
        this.add2 = add2;
    }

    public boolean isAdd3() {
        return add3;
    }

    public void setAdd3(boolean add3) {
        this.add3 = add3;
    }

    public boolean isAdd4() {
        return add4;
    }

    public void setAdd4(boolean add4) {
        this.add4 = add4;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("panelToggle", null);
    }

    public void toggle1() {
        if (isAdd2()) {
            setAdd2(false);
        } else {
            setAdd2(true);
        }
    }

    public void toggle2() {
        if (isAdd3()) {
            setAdd3(false);
        } else {
            setAdd3(true);
        }
    }

    public void toggle3() {
        if (isAdd4()) {
            setAdd4(false);
        } else {
            setAdd4(true);
        }
    }

}
