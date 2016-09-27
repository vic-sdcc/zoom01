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
import model.CoopOrgUnit;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class OrgUnitData implements Serializable {

    /**
     * Creates a new instance of OrgUnitData
     */
    public OrgUnitData() {
    }

    private CoopOrgUnit orgUnit;

    /*
     * getter setter
     */
    public CoopOrgUnit getOrgUnit() {
        if (orgUnit == null) {
            orgUnit = new CoopOrgUnit();
        }
        return orgUnit;
    }

    public void setOrgUnit(CoopOrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("orgUnitData", null);
    }

}
