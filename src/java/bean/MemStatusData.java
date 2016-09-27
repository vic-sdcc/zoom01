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
import model.CoopMemberStatus;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class MemStatusData implements Serializable {

    /**
     * Creates a new instance of MemStatusData
     */
    public MemStatusData() {
    }

    private CoopMemberStatus memberStatus;

    /*
     * getter setter
     */
    public CoopMemberStatus getMemberStatus() {
        if (memberStatus == null) {
            memberStatus = new CoopMemberStatus();
        }
        return memberStatus;
    }

    public void setMemberStatus(CoopMemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("memStatusData", null);
    }

}
