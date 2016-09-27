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
import model.CoopOccupation;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class OccupationList implements Serializable {

    /**
     * Creates a new instance of OccupationList
     */
    public OccupationList() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private List<CoopOccupation> occupationList;
    private DataModel<CoopOccupation> occupationModel;

    /*
     * getter setter
     */
    public List<CoopOccupation> getOccupationList() {
        occupationList = entityManagerFactory.createEntityManager().createQuery("SELECT o FROM CoopOccupation o ORDER BY o.unitName").getResultList();
        return occupationList;
    }

    public void setOccupationList(List<CoopOccupation> occupationList) {
        this.occupationList = occupationList;
    }

    public DataModel<CoopOccupation> getOccupationModel() {
        if (occupationModel == null) {
            occupationModel = new ListDataModel<>(getOccupationList());
        }
        return occupationModel;
    }

    public void setOccupationModel(DataModel<CoopOccupation> occupationModel) {
        this.occupationModel = occupationModel;
    }

}
