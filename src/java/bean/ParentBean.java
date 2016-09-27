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
import model.CoopFamily;
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
public class ParentBean implements Serializable {

    /**
     * Creates a new instance of ParentBean
     */
    public ParentBean() {
        System.out.print("ParentBean constructed");
    }

    private CoopFamily myParents;
    private CoopPerson father, mother;
    private CoopPersonMem myFatherM, myMotherM;
    private CoopPersonAssociate myFatherA, myMotherA;
    private CoopPersonNonMember myFatherN, myMotherN;
    private String fatherDetail = "", fatherCheck = "", motherDetail = "", motherCheck = "";
    private ArrayList<personData> parentAL;
    @ManagedProperty(value = "#{applicantPerson}")
    private ApplicantPerson applicantPerson;

    /*
     * getter setter
     */
    public CoopFamily getMyParents() {
        if (myParents == null) {
            myParents = new CoopFamily();
        }
        return myParents;
    }

    public void setMyParents(CoopFamily myParents) {
        this.myParents = myParents;
    }

    public CoopPerson getFather() {
        if (father == null) {
            father = new CoopPerson();
        }
        return father;
    }

    public void setFather(CoopPerson father) {
        this.father = father;
    }

    public CoopPerson getMother() {
        if (mother == null) {
            mother = new CoopPerson();
        }
        return mother;
    }

    public void setMother(CoopPerson mother) {
        this.mother = mother;
    }

    public CoopPersonMem getMyFatherM() {
        if (myFatherM == null) {
            myFatherM = new CoopPersonMem();
        }
        return myFatherM;
    }

    public void setMyFatherM(CoopPersonMem myFatherM) {
        this.myFatherM = myFatherM;
    }

    public CoopPersonMem getMyMotherM() {
        if (myMotherM == null) {
            myMotherM = new CoopPersonMem();
        }
        return myMotherM;
    }

    public void setMyMotherM(CoopPersonMem myMotherM) {
        this.myMotherM = myMotherM;
    }

    public CoopPersonAssociate getMyFatherA() {
        if (myFatherA == null) {
            myFatherA = new CoopPersonAssociate();
        }
        return myFatherA;
    }

    public void setMyFatherA(CoopPersonAssociate myFatherA) {
        this.myFatherA = myFatherA;
    }

    public CoopPersonAssociate getMyMotherA() {
        if (myMotherA == null) {
            myMotherA = new CoopPersonAssociate();
        }
        return myMotherA;
    }

    public void setMyMotherA(CoopPersonAssociate myMotherA) {
        this.myMotherA = myMotherA;
    }

    public CoopPersonNonMember getMyFatherN() {
        if (myFatherN == null) {
            myFatherN = new CoopPersonNonMember();
        }
        return myFatherN;
    }

    public void setMyFatherN(CoopPersonNonMember myFatherN) {
        this.myFatherN = myFatherN;
    }

    public CoopPersonNonMember getMyMotherN() {
        if (myMotherN == null) {
            myMotherN = new CoopPersonNonMember();
        }
        return myMotherN;
    }

    public void setMyMotherN(CoopPersonNonMember myMotherN) {
        this.myMotherN = myMotherN;
    }

    public ArrayList<personData> getParentAL() {
        if (parentAL == null) {
            parentAL = new ArrayList<>();
        }
        return parentAL;
    }

    public void setParentAL(ArrayList<personData> parentAL) {
        this.parentAL = parentAL;
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("parentBean", null);
    }

    public void initMap() {
        //get person id of selected person
        getApplicantPerson().setPersonNonMember((CoopPersonNonMember) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery("SELECT nm FROM CoopPersonNonMember nm JOIN nm.nonMemberId nmm "
                + "WHERE CONCAT(UPPER(nmm.firstName),UPPER(nmm.middleName),UPPER(nmm.lastName),nmm.birthdate) = "
                + "CONCAT(UPPER('" + getApplicantPerson().getApplicantData().getApplicant().getFirstName() + "'),"
                + "UPPER('" + getApplicantPerson().getApplicantData().getApplicant().getMiddleName() + "'),"
                + "UPPER('" + getApplicantPerson().getApplicantData().getApplicant().getLastName() + "'),"
                + "'" + getApplicantPerson().getdFormat().formatDate(getApplicantPerson().getApplicantData().getApplicant().getBirthdate(), "yyyy-MM-dd") + "')").getResultList().get(0));
        //get family id of selected person from coopchild
        getApplicantPerson().setFamily((CoopFamily) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery("SELECT f FROM CoopChild c JOIN c.familyId f "
                + "WHERE c.personId.personId = '" + getApplicantPerson().getPersonNonMember().getPersonId().getPersonId() + "'").getResultList().get(0));
        father();
        mother();
    }

    public void father() {
        //get husband/father person id from coopfamily (using family id)
        setFather((CoopPerson) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery("SELECT f.husband FROM CoopFamily f "
                + "WHERE f.familyId = '" + getApplicantPerson().getFamily().getFamilyId() + "'").getResultList().get(0));
        fatherCheck = "SELECT z.personTypeId.personTypeId FROM CoopPerson z WHERE z.personId = '" + getFather().getPersonId() + "'";
        if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(fatherCheck).getResultList().get(0).equals(1)) {
            fatherDetail = "SELECT x FROM CoopPersonMem x WHERE x.personId.personId = '" + getFather().getPersonId() + "'";
            setMyFatherM((CoopPersonMem) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(fatherDetail).getResultList().get(0));
            getApplicantPerson().cpm(getParentAL(), 0, getMyFatherM());
        }
        if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(fatherCheck).getResultList().get(0).equals(2)) {
            fatherDetail = "SELECT x FROM CoopPersonAssociate x WHERE x.personId.personId = '" + getFather().getPersonId() + "'";
            setMyFatherA((CoopPersonAssociate) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(fatherDetail).getResultList().get(0));
            getApplicantPerson().cpa(getParentAL(), 0, getMyFatherA());
        }
        if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(fatherCheck).getResultList().get(0).equals(3)) {
            fatherDetail = "SELECT x FROM CoopPersonNonMember x WHERE x.personId.personId = '" + getFather().getPersonId() + "'";
            setMyFatherN((CoopPersonNonMember) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(fatherDetail).getResultList().get(0));
            getApplicantPerson().cpnm(getParentAL(), 0, getMyFatherN());
        }
    }

    public void mother() {
        //get wife/mother person id from coopfamily (using family id)
        setMother((CoopPerson) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery("SELECT f.wife FROM CoopFamily f "
                + "WHERE f.familyId = '" + getApplicantPerson().getFamily().getFamilyId() + "'").getResultList().get(0));
        motherCheck = "SELECT z.personTypeId.personTypeId FROM CoopPerson z WHERE z.personId = '" + getMother().getPersonId() + "'";
        if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(motherCheck).getResultList().get(0).equals(1)) {
            motherDetail = "SELECT x FROM CoopPersonMem x WHERE x.personId.personId = '" + getMother().getPersonId() + "'";
            setMyMotherM((CoopPersonMem) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(motherDetail).getResultList().get(0));
            getApplicantPerson().cpm(getParentAL(), 1, getMyMotherM());
        }
        if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(motherCheck).getResultList().get(0).equals(2)) {
            motherDetail = "SELECT x FROM CoopPersonAssociate x WHERE x.personId.personId = '" + getMother().getPersonId() + "'";
            setMyMotherA((CoopPersonAssociate) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(motherDetail).getResultList().get(0));
            getApplicantPerson().cpa(getParentAL(), 1, getMyMotherA());
        }
        if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(motherCheck).getResultList().get(0).equals(3)) {
            motherDetail = "SELECT x FROM CoopPersonNonMember x WHERE x.personId.personId = '" + getMother().getPersonId() + "'";
            setMyMotherN((CoopPersonNonMember) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(motherDetail).getResultList().get(0));
            getApplicantPerson().cpnm(getParentAL(), 1, getMyMotherN());
        }
    }

}
