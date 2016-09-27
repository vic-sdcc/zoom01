/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class MembershipType implements Serializable {

    /**
     * Creates a new instance of MembershipType
     */
    public MembershipType() {
    }

    private Integer typeValue;
    private boolean updateM, updateA;
    private String memberType;

    /*
     * getter setter
     */
    public Integer getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(Integer typeValue) {
        this.typeValue = typeValue;
    }

    public boolean isUpdateM() {
        return updateM;
    }

    public void setUpdateM(boolean updateM) {
        this.updateM = updateM;
    }

    public boolean isUpdateA() {
        return updateA;
    }

    public void setUpdateA(boolean updateA) {
        this.updateA = updateA;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("membershipType", null);
    }

    public void assignType(TabChangeEvent event) {
        if ("Regular".equals(event.getTab().getTitle())) {
            setTypeValue(0);
        }
        if ("Associate".equals(event.getTab().getTitle())) {
            setTypeValue(1);
        }
    }

    public void assignTypeM() {
        setTypeValue(0);
        setUpdateA(false);
        setUpdateM(true);
    }

    public void assignTypeA() {
        setTypeValue(1);
        setUpdateM(false);
        setUpdateA(true);
    }
    
    public void member() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        setMemberType(params.get("type"));
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

}
