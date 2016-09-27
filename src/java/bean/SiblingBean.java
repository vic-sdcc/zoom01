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
import model.CoopPerson;
import model.CoopPersonAssociate;
import model.CoopPersonMem;
import model.CoopPersonNonMember;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class SiblingBean implements Serializable {

    /**
     * Creates a new instance of SiblingBean
     */
    public SiblingBean() {
        System.out.print("SiblingBean constructed");
    }

    private CoopPerson siblings;
    private CoopPersonMem mySiblingsM;
    private CoopPersonAssociate mySiblingsA;
    private CoopPersonNonMember mySiblingsN;
    private Integer c1;
    private String sbId = "", siblingDetail = "", siblingCheck = "";
    private ArrayList<personData> siblingAL;
    @ManagedProperty(value = "#{applicantPerson}")
    private ApplicantPerson applicantPerson;

    public CoopPerson getSiblings() {
        if (siblings == null) {
            siblings = new CoopPerson();
        }
        return siblings;
    }

    public void setSiblings(CoopPerson siblings) {
        this.siblings = siblings;
    }

    public CoopPersonMem getMySiblingsM() {
        if (mySiblingsM == null) {
            mySiblingsM = new CoopPersonMem();
        }
        return mySiblingsM;
    }

    public void setMySiblingsM(CoopPersonMem mySiblingsM) {
        this.mySiblingsM = mySiblingsM;
    }

    public CoopPersonAssociate getMySiblingsA() {
        if (mySiblingsA == null) {
            mySiblingsA = new CoopPersonAssociate();
        }
        return mySiblingsA;
    }

    public void setMySiblingsA(CoopPersonAssociate mySiblingsA) {
        this.mySiblingsA = mySiblingsA;
    }

    public CoopPersonNonMember getMySiblingsN() {
        if (mySiblingsN == null) {
            mySiblingsN = new CoopPersonNonMember();
        }
        return mySiblingsN;
    }

    public void setMySiblingsN(CoopPersonNonMember mySiblingsN) {
        this.mySiblingsN = mySiblingsN;
    }

    public ArrayList<personData> getSiblingAL() {
        if (siblingAL == null) {
            siblingAL = new ArrayList<>();
        }
        return siblingAL;
    }

    public void setSiblingAL(ArrayList<personData> siblingAL) {
        this.siblingAL = siblingAL;
    }

    public ApplicantPerson getApplicantPerson() {
        if (applicantPerson == null) {
            applicantPerson = new ApplicantPerson();
        }
        return applicantPerson;
    }

    public void setApplicantPerson(ApplicantPerson applicantPerson) {
        this.applicantPerson = applicantPerson;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("siblingBean", null);
    }

    public void addSiblings() {
        if (getSiblingAL().size() < 12) {
            getSiblingAL().add(new personData());
        }
    }

    public void removeSiblings(int value) {
        if (getSiblingAL().size() >= 0) {
            getSiblingAL().remove(value);
        }
    }

    public void initMap() {
        //get person id from coopchild (using family id)
        sbId = "SELECT sb.personId FROM CoopChild sb WHERE sb.familyId.familyId = '" + getApplicantPerson().getFamily().getFamilyId() + "' AND sb.personId.personId <> '" + getApplicantPerson().getPersonNonMember().getPersonId().getPersonId() + "'";
        c1 = getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(sbId).getResultList().size();
        if (c1 > 0) {
            for (int i = 0; i != c1; i++) {
                getSiblingAL().add(new personData());
                setSiblings((CoopPerson) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(sbId).getResultList().get(i));
                siblingCheck = "SELECT z.personTypeId.personTypeId FROM CoopPerson z WHERE z.personId = '" + getSiblings().getPersonId() + "'";
                if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(siblingCheck).getResultList().get(0).equals(1)) {
                    siblingDetail = "SELECT x FROM CoopPersonMem x WHERE x.personId.personId = '" + siblings.getPersonId() + "'";
                    setMySiblingsM((CoopPersonMem) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(siblingDetail).getResultList().get(0));
                    getApplicantPerson().cpm(getSiblingAL(), i, getMySiblingsM());
                } else if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(siblingCheck).getResultList().get(0).equals(2)) {
                    siblingDetail = "SELECT x FROM CoopPersonAssociate x WHERE x.personId.personId = '" + getSiblings().getPersonId() + "'";
                    setMySiblingsA((CoopPersonAssociate) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(siblingDetail).getResultList().get(0));
                    getApplicantPerson().cpa(getSiblingAL(), i, getMySiblingsA());
                } else if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(siblingCheck).getResultList().get(0).equals(3)) {
                    siblingDetail = "SELECT x FROM CoopPersonNonMember x WHERE x.personId.personId = '" + getSiblings().getPersonId() + "'";
                    setMySiblingsN((CoopPersonNonMember) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(siblingDetail).getResultList().get(0));
                    getApplicantPerson().cpnm(getSiblingAL(), i, getMySiblingsN());
                }
            }
        }
    }

}
