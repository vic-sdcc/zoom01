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
import model.CoopTenure;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class TenureList implements Serializable {

    /**
     * Creates a new instance of TenureList
     */
    public TenureList() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private List<CoopTenure> tenureList;
    private DataModel<CoopTenure> tenureModel;

    /*
     * getter setter
     */
    public List<CoopTenure> getTenureList() {
        tenureList = entityManagerFactory.createEntityManager().createQuery("SELECT t FROM CoopTenure t ORDER by t.tenureValue").getResultList();
        return tenureList;
    }

    public void setTenureList(List<CoopTenure> tenureList) {
        this.tenureList = tenureList;
    }

    public DataModel<CoopTenure> getTenureModel() {
        if (tenureModel == null) {
            tenureModel = new ListDataModel<>(getTenureList());
        }
        return tenureModel;
    }

    public void setTenureModel(DataModel<CoopTenure> tenureModel) {
        this.tenureModel = tenureModel;
    }

}
