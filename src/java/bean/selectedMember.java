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
import javax.persistence.Query;
import model.CoopAssociate;
import model.CoopMember;

/**
 *
 * @author vic
 */
@ManagedBean
@RequestScoped
public class selectedMember implements Serializable {

    /**
     * Creates a new instance of selectedMember
     */
    public selectedMember() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    private List<CoopMember> memberList;
    private DataModel<CoopMember> memberModel;
    private List<CoopAssociate> associateList;
    private DataModel<CoopAssociate> associateModel;

    public List<CoopMember> getMemberList() {
        if (memberList == null) {
            Query m = entityManagerFactory.createEntityManager().createNamedQuery("CoopMember.findAll");
            memberList = m.getResultList();
        }
        return memberList;
    }

    public void setMemberList(List<CoopMember> memberList) {
        this.memberList = memberList;
    }

    public DataModel<CoopMember> getMemberModel() {
        if (memberModel == null) {
            memberModel = new ListDataModel<>(getMemberList());
        }
        return memberModel;
    }

    public void setMemberModel(DataModel<CoopMember> memberModel) {
        this.memberModel = memberModel;
    }

    public List<CoopAssociate> getAssociateList() {
        if (associateList == null) {
            Query a = entityManagerFactory.createEntityManager().createNamedQuery("CoopAssociate.findAll");
            associateList = a.getResultList();
        }
        return associateList;
    }

    public void setAssociateList(List<CoopAssociate> associateList) {
        this.associateList = associateList;
    }

    public DataModel<CoopAssociate> getAssociateModel() {
        if (associateModel == null) {
            associateModel = new ListDataModel<>(getAssociateList());
        }
        return associateModel;
    }

    public void setAssociateModel(DataModel<CoopAssociate> associateModel) {
        this.associateModel = associateModel;
    }

    public CoopMember regular(String memNo) {
        setMemberList(null);
        setMemberModel(null);
        for (int i = 0; i != getMemberModel().getRowCount(); i++) {
            if (getMemberList().get(i).getMemNo().equals(memNo)) {
                return getMemberList().get(i);
            }
        }
        return null;
    }

    public CoopAssociate associate(String memNo) {
        setAssociateList(null);
        setAssociateModel(null);
        for (int i = 0; i != getAssociateModel().getRowCount(); i++) {
            if (getAssociateList().get(i).getMemNo().equals(memNo)) {
                return getAssociateList().get(i);
            }
        }
        return null;
    }

}
