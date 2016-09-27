/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopOrgUnit;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class OrgUnitList implements Serializable {

    /**
     * Creates a new instance of OrgUnitList
     */
    public OrgUnitList() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private List<CoopOrgUnit> orgUnitList;
    private DataModel<CoopOrgUnit> orgUnitModel;

    /*
     * getter setter
     */
    public List<CoopOrgUnit> getOrgUnitList() {
        orgUnitList = entityManagerFactory.createEntityManager().createQuery("SELECT o FROM CoopOrgUnit o WHERE o.ouShortName LIKE 'PT%' AND o.dateDissolved IS NULL OR o.ouShortName = 'NO DATA' ORDER BY o.ouShortName").getResultList();
        return orgUnitList;
    }

    public void setOrgUnitList(List<CoopOrgUnit> orgUnitList) {
        this.orgUnitList = orgUnitList;
    }

    public DataModel<CoopOrgUnit> getOrgUnitModel() {
        if (orgUnitModel == null) {
            orgUnitModel = new ListDataModel<>(getOrgUnitList());
        }
        return orgUnitModel;
    }

    public void setOrgUnitModel(DataModel<CoopOrgUnit> orgUnitModel) {
        this.orgUnitModel = orgUnitModel;
    }

}
