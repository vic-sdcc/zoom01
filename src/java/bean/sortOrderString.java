/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class sortOrderString extends memberSortData {

    private ArrayList<String> orderByStr;

    public sortOrderString() {

    }

    public void beanclear2() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sortOrderString", null);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public void alphabetical() {
        if (super.isAlphabetical()) {
            getOrderByStr().add("c.lastName, c.firstName, c.middleName");
        } else {
            getOrderByStr().remove("c.lastName, c.firstName, c.middleName");
        }
    }

    public void memberNumber() {
        if (super.isMemberNumber()) {
            getOrderByStr().add("c.memNo");
        } else {
            getOrderByStr().remove("c.memNo");
        }
    }

    public void membershipDate() {
        if (super.isMembershipDate()) {
            getOrderByStr().add("c.memDate");
        } else {
            getOrderByStr().remove("c.memDate");
        }
    }

    public void orgUnit() {
        if (super.isOrgUnit()) {
            getOrderByStr().add("c.ouCode.ouCode");
        } else {
            getOrderByStr().remove("c.ouCode.ouCode");
        }
    }

    public ArrayList<String> getOrderByStr() {
        if (orderByStr == null) {
            orderByStr = new ArrayList<>();
        }
        return orderByStr;
    }

    public void setOrderByStr(ArrayList<String> orderByStr) {
        this.orderByStr = orderByStr;
    }

}
