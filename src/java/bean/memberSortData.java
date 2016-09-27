/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class memberSortData {

    private boolean alphabetical;
    private boolean memberNumber;
    private boolean membershipDate;
    private boolean orgUnit;

    public memberSortData() {
    }
    
    public void beanclear() {
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("memberSortData", null);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public boolean isAlphabetical() {
        return alphabetical;
    }

    public void setAlphabetical(boolean alphabetical) {
        this.alphabetical = alphabetical;
    }

    public boolean isMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(boolean memberNumber) {
        this.memberNumber = memberNumber;
    }

    public boolean isMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(boolean membershipDate) {
        this.membershipDate = membershipDate;
    }

    public boolean isOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(boolean orgUnit) {
        this.orgUnit = orgUnit;
    }
    
}
