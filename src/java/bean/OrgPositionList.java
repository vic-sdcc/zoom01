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
import model.CoopOrgPosition;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class OrgPositionList implements Serializable {

    /**
     * Creates a new instance of OrgPositionList
     */
    public OrgPositionList() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private List<CoopOrgPosition> orgPositionList;
    private DataModel<CoopOrgPosition> orgPositionModel;

    /*
     * getter setter
     */
    public List<CoopOrgPosition> getOrgPositionList() {
        orgPositionList = entityManagerFactory.createEntityManager().createQuery("SELECT o FROM CoopOrgPosition o ORDER BY o.ouCode.ouShortName").getResultList();
        return orgPositionList;
    }

    public void setOrgPositionList(List<CoopOrgPosition> orgPositionList) {
        this.orgPositionList = orgPositionList;
    }

    public DataModel<CoopOrgPosition> getOrgPositionModel() {
        if (orgPositionModel == null) {
            orgPositionModel = new ListDataModel<>(getOrgPositionList());
        }
        return orgPositionModel;
    }

    public void setOrgPositionModel(DataModel<CoopOrgPosition> orgPositionModel) {
        this.orgPositionModel = orgPositionModel;
    }

}
