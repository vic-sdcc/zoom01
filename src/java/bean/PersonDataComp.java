/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.CoopPerson;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class PersonDataComp extends personData implements Serializable {

    /**
     * Creates a new instance of PersonDataComp
     */
    public PersonDataComp() {
    }

    private List<CoopPerson> personProfileList;
    private Integer indexList;
    @ManagedProperty(value = "#{personType}")
    private PersonType personTypeClass;
    @ManagedProperty(value = "#{familyCreate}")
    private FamilyCreate familyCreate;

    /*
     * getter setter
     */
    public PersonType getPersonTypeClass() {
        if (personTypeClass == null) {
            personTypeClass = new PersonType();
        }
        return personTypeClass;
    }

    public void setPersonTypeClass(PersonType personTypeClass) {
        this.personTypeClass = personTypeClass;
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personDataComp", null);
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();
    }

    public String part(String q, String sel, String ord) {
        q = "SELECT m FROM " + sel + " m WHERE ";
        if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getLastName() != null) {
            q += "m.lastName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getLastName() + "' ";
        }
        if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getFirstName() != null) {
            q += "AND m.firstName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getFirstName() + "' ";
        }
        if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getMiddleName() != null) {
            q += "AND m.middleName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getMiddleName() + "' ";
        }
        q += "ORDER BY m." + ord;
        return q;
    }

    public void clearMe() {
        getPersonProfileList().clear();
    }

    public void personComplete() {
        String query = "";
        setPersonProfileList(null);
        if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getLastName() != null
                || getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getFirstName() != null
                || getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getMiddleName() != null) {
            try {
                if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getPersonTypeId().equals(1)) {
                    query = "SELECT m FROM CoopMember m WHERE ";
                    if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getLastName() != null) {
                        query += "m.lastName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getLastName() + "' ";
                    }
                    if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getFirstName() != null) {
                        query += "AND m.firstName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getFirstName() + "' ";
                    }
                    if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getMiddleName() != null) {
                        query += "AND m.middleName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getMiddleName() + "' ";
                    }
                    query += "ORDER BY m.memNo";
                    //part(query, "CoopMember", "memNo");
                }
                if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getPersonTypeId().equals(2)) {
                    query = "SELECT m FROM CoopAssociate m WHERE ";
                    if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getLastName() != null) {
                        query += "m.lastName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getLastName() + "' ";
                    }
                    if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getFirstName() != null) {
                        query += "AND m.firstName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getFirstName() + "' ";
                    }
                    if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getMiddleName() != null) {
                        query += "AND m.middleName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getMiddleName() + "' ";
                    }
                    query += "ORDER BY m.associateNo";
                    //part(query, "CoopAssociate", "associateNo");
                }
                if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getPersonTypeId().equals(3)) {
                    query = "SELECT m FROM CoopNonMember m WHERE ";
                    if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getLastName() != null) {
                        query += "m.lastName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getLastName() + "' ";
                    }
                    if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getFirstName() != null) {
                        query += "AND m.firstName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getFirstName() + "' ";
                    }
                    if (getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getMiddleName() != null) {
                        query += "AND m.middleName = '" + getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).getMiddleName() + "' ";
                    }
                    query += "ORDER BY m.nonMemberId";
                    //part(query, "CoopNonMember", "nonMemberId");
                }
                setPersonProfileList(entityManagerFactory.createEntityManager().createQuery(query).getResultList());
            } catch (Exception e) {
                /*
                 getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).setLastName(null);
                 getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).setFirstName(null);
                 getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).setMiddleName(null);
                 getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).setBirthdate(null);
                 getPersonTypeClass().getAl().get(getPersonTypeClass().getTv()).setGender(null);
                 */
                System.out.print(" " + e);
            }
        }
    }

    public List<CoopPerson> getPersonProfileList() {
        if (personProfileList == null) {
            personProfileList = new ArrayList<>();
        }
        return personProfileList;
    }

    public void setPersonProfileList(List<CoopPerson> personProfileList) {
        this.personProfileList = personProfileList;
    }

    public Integer getIndexList() {
        if (indexList == null) {
            indexList = 0;
        }
        return indexList;
    }

    public void setIndexList(Integer indexList) {
        this.indexList = indexList;
    }

}
