/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.CoopAssociateApplicant;
import model.CoopMemberApplicant;
import service.CoopAssociateApplicantFacadeREST;
import service.CoopMemberApplicantFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class ApplicantRelation implements Serializable {

    /**
     * Creates a new instance of ApplicantRelation
     */
    public ApplicantRelation() {
    }

    @EJB
    private CoopMemberApplicantFacadeREST memberApplicantREST;
    @EJB
    private CoopAssociateApplicantFacadeREST associateApplicantREST;
    private CoopMemberApplicant memberApplicant;
    private CoopAssociateApplicant associateApplicant;
    @ManagedProperty(value = "#{applicantData}")
    private ApplicantData applicantData;
    @ManagedProperty(value = "#{memberData}")
    private MemberData memberData;
    @ManagedProperty(value = "#{associateData}")
    private AssociateData associateData;

    /*
     * getter setter
     */
    public CoopMemberApplicant getMemberApplicant() {
        if (memberApplicant == null) {
            memberApplicant = new CoopMemberApplicant();
        }
        return memberApplicant;
    }

    public void setMemberApplicant(CoopMemberApplicant memberApplicant) {
        this.memberApplicant = memberApplicant;
    }

    public CoopAssociateApplicant getAssociateApplicant() {
        if (associateApplicant == null) {
            associateApplicant = new CoopAssociateApplicant();
        }
        return associateApplicant;
    }

    public void setAssociateApplicant(CoopAssociateApplicant associateApplicant) {
        this.associateApplicant = associateApplicant;
    }

    public ApplicantData getApplicantData() {
        if (applicantData == null) {
            applicantData = new ApplicantData();
        }
        return applicantData;
    }

    public void setApplicantData(ApplicantData applicantData) {
        this.applicantData = applicantData;
    }

    public MemberData getMemberData() {
        if (memberData == null) {
            memberData = new MemberData();
        }
        return memberData;
    }

    public void setMemberData(MemberData memberData) {
        this.memberData = memberData;
    }

    public AssociateData getAssociateData() {
        if (associateData == null) {
            associateData = new AssociateData();
        }
        return associateData;
    }

    public void setAssociateData(AssociateData associateData) {
        this.associateData = associateData;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("applicantRelation", null);
    }

    public void applicantRelationLoad() {
        if (getApplicantData().getApplicant().getApplicantType().equals("REGULAR")) {
            getMemberApplicant().setApplicantNo(getApplicantData().getApplicant());
            getMemberApplicant().setMemNo(getMemberData().getMember());
            memberApplicantREST.create(getMemberApplicant());
        }
        if (getApplicantData().getApplicant().getApplicantType().equals("ASSOCIATE")) {
            getAssociateApplicant().setApplicantNo(getApplicantData().getApplicant());
            getAssociateApplicant().setAssociateNo(getAssociateData().getAssociate());
            associateApplicantREST.create(getAssociateApplicant());
        }
    }

}
