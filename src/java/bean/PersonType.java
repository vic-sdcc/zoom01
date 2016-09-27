/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class PersonType implements Serializable {

    /**
     * Creates a new instance of PersonType
     */
    public PersonType() {
    }

    private Integer tv;
    private ArrayList<personData> al;
    @ManagedProperty(value = "#{familyCreate}")
    private FamilyCreate familyCreate;


    /*
     * getter setter
     */
    public Integer getTv() {
        return tv;
    }

    public void setTv(Integer tv) {
        this.tv = tv;
    }

    public ArrayList<personData> getAl() {
        if (al == null) {
            al = new ArrayList<>();
        }
        return al;
    }

    public void setAl(ArrayList<personData> al) {
        this.al = al;
    }

    public FamilyCreate getFamilyCreate() {
        if (familyCreate == null) {
            familyCreate = new FamilyCreate();
        }
        return familyCreate;
    }

    public void setFamilyCreate(FamilyCreate familyCreate) {
        this.familyCreate = familyCreate;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personType", null);
    }

    public void pType(int value) {
        setTv(value);
        setAl(getFamilyCreate().getParentBean().getParentAL());
    }

    public void sbType(int value) {
        setTv(value);
        setAl(getFamilyCreate().getSiblingBean().getSiblingAL());
    }

    public void spType(int value) {
        setTv(value);
        setAl(getFamilyCreate().getSpouseBean().getSpouseAL());
    }

    public void cType(int value1,int value2) {
        setTv(value2);
        setAl(getFamilyCreate().getSpouseBean().getNewFamilyAL().get(value1));
    }

}
