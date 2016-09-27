/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopApplicant;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class ApplicantData implements Serializable {

    /**
     * Creates a new instance of ApplicantData
     */
    public ApplicantData() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private CoopApplicant applicant, selectedApplicant;
    private List<CoopApplicant> applicantList;
    private DataModel<CoopApplicant> applicantModel;
    @ManagedProperty(value = "#{membershipType}")
    private MembershipType membershipType;

    /*
     * getter setter
     */
    public CoopApplicant getApplicant() {
        if (applicant == null) {
            applicant = new CoopApplicant();
        }
        return applicant;
    }

    public void setApplicant(CoopApplicant applicant) {
        this.applicant = applicant;
    }

    public CoopApplicant getSelectedApplicant() {
        if (selectedApplicant == null) {
            selectedApplicant = new CoopApplicant();
        }
        return selectedApplicant;
    }

    public void setSelectedApplicant(CoopApplicant selectedApplicant) {
        this.selectedApplicant = selectedApplicant;
    }

    public List<CoopApplicant> getApplicantList() {
        if (getMembershipType().getTypeValue().equals(0)) {
            applicantList = entityManagerFactory.createEntityManager().createQuery("SELECT a FROM CoopApplicant a WHERE a.applicationStat = 'A' AND "
                    + "a.applicantType = 'REGULAR' AND "
                    + "a.applicantNo NOT IN (SELECT b.applicantNo.applicantNo FROM CoopMemberApplicant b) "
                    + "ORDER BY a.applicantNo").getResultList();
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            applicantList = entityManagerFactory.createEntityManager().createQuery("SELECT a FROM CoopApplicant a WHERE a.applicationStat = 'A' AND "
                    + "a.applicantType = 'ASSOCIATE' AND "
                    + "a.applicantNo NOT IN (SELECT c.applicantNo.applicantNo FROM CoopAssociateApplicant c) "
                    + "ORDER BY a.applicantNo").getResultList();
        }
        return applicantList;
    }

    public void setApplicantList(List<CoopApplicant> applicantList) {
        this.applicantList = applicantList;
    }

    public DataModel<CoopApplicant> getApplicantModel() {
        if (applicantModel == null) {
            applicantModel = new ListDataModel<>(getApplicantList());
        }
        return applicantModel;
    }

    public void setApplicantModel(DataModel<CoopApplicant> applicantModel) {
        this.applicantModel = applicantModel;
    }

    public MembershipType getMembershipType() {
        if (membershipType == null) {
            membershipType = new MembershipType();
        }
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("applicantData", null);
    }

    public void dataTableLoad() {
        CoopApplicant a = (CoopApplicant) entityManagerFactory.createEntityManager().createQuery("SELECT a FROM CoopApplicant a WHERE a.applicantNo = '" + getSelectedApplicant().getApplicantNo() + "'").getResultList().get(0);
        setApplicant(a);
    }

}
