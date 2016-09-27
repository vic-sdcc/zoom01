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
import model.CoopMemberStatus;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class MemStatusList implements Serializable {

    /**
     * Creates a new instance of MemStatusList
     */
    public MemStatusList() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private List<CoopMemberStatus> memStatusList;
    private DataModel<CoopMemberStatus> memStatusModel;

    /*
     * getter setter
     */
    public List<CoopMemberStatus> getMemStatusList() {
        memStatusList = entityManagerFactory.createEntityManager().createQuery("SELECT m FROM CoopMemberStatus m ORDER BY m.statusName").getResultList();
        return memStatusList;
    }

    public void setMemStatusList(List<CoopMemberStatus> memStatusList) {
        this.memStatusList = memStatusList;
    }

    public DataModel<CoopMemberStatus> getMemStatusModel() {
        if (memStatusModel == null) {
            memStatusModel = new ListDataModel<>(getMemStatusList());
        }
        return memStatusModel;
    }

    public void setMemStatusModel(DataModel<CoopMemberStatus> memStatusModel) {
        this.memStatusModel = memStatusModel;
    }

}
