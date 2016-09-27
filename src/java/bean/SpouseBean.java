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
public class SpouseBean implements Serializable {

    /**
     * Creates a new instance of SpouseBean
     */
    public SpouseBean() {
        System.out.print("SpouseBean constructed");
    }

    private CoopPerson spouse, children;
    private CoopPersonMem mySpouseM, myChildM;
    private CoopPersonAssociate mySpouseA, myChildA;
    private CoopPersonNonMember mySpouseN, myChildN;
    private CoopFamily fam;
    private Integer c2, c3;
    private String fId2 = "", spId = "", spouseDetail = "", spouseCheck = "",
            chId = "", childDetail = "", childCheck = "";
    private ArrayList<personData> spouseAL;
    private ArrayList<ArrayList<personData>> newFamilyAL;
    @ManagedProperty(value = "#{applicantPerson}")
    private ApplicantPerson applicantPerson;

    /*
     * getter setter
     */
    public CoopPerson getSpouse() {
        if (spouse == null) {
            spouse = new CoopPerson();
        }
        return spouse;
    }

    public void setSpouse(CoopPerson spouse) {
        this.spouse = spouse;
    }

    public CoopPerson getChildren() {
        if (children == null) {
            children = new CoopPerson();
        }
        return children;
    }

    public void setChildren(CoopPerson children) {
        this.children = children;
    }

    public CoopPersonMem getMySpouseM() {
        if (mySpouseM == null) {
            mySpouseM = new CoopPersonMem();
        }
        return mySpouseM;
    }

    public void setMySpouseM(CoopPersonMem mySpouseM) {
        this.mySpouseM = mySpouseM;
    }

    public CoopPersonMem getMyChildM() {
        if (myChildM == null) {
            myChildM = new CoopPersonMem();
        }
        return myChildM;
    }

    public void setMyChildM(CoopPersonMem myChildM) {
        this.myChildM = myChildM;
    }

    public CoopPersonAssociate getMySpouseA() {
        if (mySpouseA == null) {
            mySpouseA = new CoopPersonAssociate();
        }
        return mySpouseA;
    }

    public void setMySpouseA(CoopPersonAssociate mySpouseA) {
        this.mySpouseA = mySpouseA;
    }

    public CoopPersonAssociate getMyChildA() {
        if (myChildA == null) {
            myChildA = new CoopPersonAssociate();
        }
        return myChildA;
    }

    public void setMyChildA(CoopPersonAssociate myChildA) {
        this.myChildA = myChildA;
    }

    public CoopPersonNonMember getMySpouseN() {
        if (mySpouseN == null) {
            mySpouseN = new CoopPersonNonMember();
        }
        return mySpouseN;
    }

    public void setMySpouseN(CoopPersonNonMember mySpouseN) {
        this.mySpouseN = mySpouseN;
    }

    public CoopPersonNonMember getMyChildN() {
        if (myChildN == null) {
            myChildN = new CoopPersonNonMember();
        }
        return myChildN;
    }

    public void setMyChildN(CoopPersonNonMember myChildN) {
        this.myChildN = myChildN;
    }

    public ArrayList<personData> getSpouseAL() {
        if (spouseAL == null) {
            spouseAL = new ArrayList<>();
        }
        return spouseAL;
    }

    public void setSpouseAL(ArrayList<personData> spouseAL) {
        this.spouseAL = spouseAL;
    }

    public ArrayList<ArrayList<personData>> getNewFamilyAL() {
        if (newFamilyAL == null) {
            //newFamilyAL = new ArrayList<>();
            newFamilyAL = new ArrayList<>();
        }
        return newFamilyAL;
    }

    public void setNewFamilyAL(ArrayList<ArrayList<personData>> newFamilyAL) {
        this.newFamilyAL = newFamilyAL;
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("spouseBean", null);
    }

    public void addSpouse() {
        if (getSpouseAL().size() < 12) {
            getSpouseAL().add(new personData());
            getNewFamilyAL().add(new ArrayList<personData>());
        }
    }

    public void removeSpouse(int value1) {
        if (getSpouseAL().size() >= 0) {
            getSpouseAL().remove(value1);
            getNewFamilyAL().remove(value1);
        }
    }

    public void addChild(int value1, int value2) {
        if (getNewFamilyAL().get(value1).size() < 13) {
            getNewFamilyAL().get(value1).add(new personData());
        }
    }

    public void removeChild(int value1, int value2) {
        if (getNewFamilyAL().get(value1).size() >= 0) {
            getNewFamilyAL().get(value1).remove(value2);
        }
    }

    public void initMap() {
        //get family id from coopfamily
        if (getApplicantPerson().getApplicantData().getApplicant().getGender().equals('M')) {
            spId = "SELECT sp.wife FROM CoopFamily sp WHERE sp.husband.personId = '" + getApplicantPerson().getPersonNonMember().getPersonId().getPersonId() + "'";
            fId2 = "SELECT f2 FROM CoopFamily f2 WHERE f2.husband.personId = '" + getApplicantPerson().getPersonNonMember().getPersonId().getPersonId() + "'";
        }
        if (getApplicantPerson().getApplicantData().getApplicant().getGender().equals('F')) {
            spId = "SELECT sp.husband FROM CoopFamily sp WHERE sp.wife.personId = '" + getApplicantPerson().getPersonNonMember().getPersonId().getPersonId() + "'";
            fId2 = "SELECT f2 FROM CoopFamily f2 WHERE f2.wife.personId = '" + getApplicantPerson().getPersonNonMember().getPersonId().getPersonId() + "'";
        }
        c2 = getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(spId).getResultList().size();
        if (c2 > 0) {
            for (int i = 0; i != c2; i++) {
                getSpouseAL().add(new personData());
                getApplicantPerson().setFamily2((CoopFamily) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(fId2).getResultList().get(i));
                setSpouse((CoopPerson) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(spId).getResultList().get(i));
                spouseCheck = "SELECT z.personTypeId.personTypeId FROM CoopPerson z WHERE z.personId = '" + getSpouse().getPersonId() + "'";
                if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(spouseCheck).getResultList().get(0).equals(1)) {
                    spouseDetail = "SELECT x FROM CoopPersonMem x WHERE x.personId.personId = '" + getSpouse().getPersonId() + "'";
                    setMySpouseM((CoopPersonMem) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(spouseDetail).getResultList().get(0));
                    getApplicantPerson().cpm(getSpouseAL(), i, getMySpouseM());
                }
                if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(spouseCheck).getResultList().get(0).equals(2)) {
                    spouseDetail = "SELECT x FROM CoopPersonAssociate x WHERE x.personId.personId = '" + getSpouse().getPersonId() + "'";
                    setMySpouseA((CoopPersonAssociate) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(spouseDetail).getResultList().get(0));
                    getApplicantPerson().cpa(getSpouseAL(), i, getMySpouseA());
                }
                if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(spouseCheck).getResultList().get(0).equals(3)) {
                    spouseDetail = "SELECT x FROM CoopPersonNonMember x WHERE x.personId.personId = '" + getSpouse().getPersonId() + "'";
                    setMySpouseN((CoopPersonNonMember) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(spouseDetail).getResultList().get(0));
                    getApplicantPerson().cpnm(getSpouseAL(), i, getMySpouseN());
                }
                //child id where family id = to person family husband/wife
                chId = "SELECT ch.personId FROM CoopChild ch WHERE ch.familyId.familyId = '" + getApplicantPerson().getFamily2().getFamilyId() + "'";
                c3 = getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(chId).getResultList().size();
                if (c3 > 0) {
                    for (int ii = 0; ii != c3; ii++) {
                        getNewFamilyAL().add(new ArrayList<personData>());
                        getNewFamilyAL().get(i).add(new personData());

                        setChildren((CoopPerson) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(chId).getResultList().get(ii));
                        childCheck = "SELECT z.personTypeId.personTypeId FROM CoopPerson z WHERE z.personId = '" + getChildren().getPersonId() + "'";
                        if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(childCheck).getResultList().get(0).equals(1)) {
                            childDetail = "SELECT x FROM CoopPersonMem x WHERE x.personId.personId = '" + getChildren().getPersonId() + "'";
                            setMyChildM((CoopPersonMem) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(childDetail).getResultList().get(0));
                            //getApplicantPerson().cpm(getChildAL(), ii, getMyChildM());
                            getNewFamilyAL().get(i).get(ii).setPersonId(getMyChildM().getPersonId().getPersonId());
                            getNewFamilyAL().get(i).get(ii).setPersonType("REGULAR");
                            getNewFamilyAL().get(i).get(ii).setPersonTypeId(1);
                            getNewFamilyAL().get(i).get(ii).setLastName(getMyChildM().getMemNo().getLastName());
                            getNewFamilyAL().get(i).get(ii).setFirstName(getMyChildM().getMemNo().getFirstName());
                            getNewFamilyAL().get(i).get(ii).setMiddleName(getMyChildM().getMemNo().getMiddleName());
                            getNewFamilyAL().get(i).get(ii).setBirthdate(getMyChildM().getMemNo().getBirthdate());
                            getNewFamilyAL().get(i).get(ii).setGender(getMyChildM().getMemNo().getGender());
                            getNewFamilyAL().get(i).get(ii).setMemNo(getMyChildM().getMemNo().getMemNo());
                        }
                        if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(childCheck).getResultList().get(0).equals(2)) {
                            childDetail = "SELECT x FROM CoopPersonAssociate x WHERE x.personId.personId = '" + getChildren().getPersonId() + "'";
                            setMyChildA((CoopPersonAssociate) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(childDetail).getResultList().get(0));
                            //getApplicantPerson().cpa(getChildAL(), ii, getMyChildA());
                            getNewFamilyAL().get(i).get(ii).setPersonId(getMyChildA().getPersonId().getPersonId());
                            getNewFamilyAL().get(i).get(ii).setPersonType("ASSOCIATE");
                            getNewFamilyAL().get(i).get(ii).setPersonTypeId(2);
                            getNewFamilyAL().get(i).get(ii).setLastName(getMyChildA().getAssociateNo().getLastName());
                            getNewFamilyAL().get(i).get(ii).setFirstName(getMyChildA().getAssociateNo().getFirstName());
                            getNewFamilyAL().get(i).get(ii).setMiddleName(getMyChildA().getAssociateNo().getMiddleName());
                            getNewFamilyAL().get(i).get(ii).setBirthdate(getMyChildA().getAssociateNo().getBirthdate());
                            getNewFamilyAL().get(i).get(ii).setGender(getMyChildA().getAssociateNo().getGender());
                            getNewFamilyAL().get(i).get(ii).setMemNo(getMyChildA().getAssociateNo().getMemNo());
                        }
                        if (getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(childCheck).getResultList().get(0).equals(3)) {
                            childDetail = "SELECT x FROM CoopPersonNonMember x WHERE x.personId.personId = '" + getChildren().getPersonId() + "'";
                            setMyChildN((CoopPersonNonMember) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery(childDetail).getResultList().get(0));
                            //getApplicantPerson().cpnm(getChildAL(), ii, getMyChildN());
                            getNewFamilyAL().get(i).get(ii).setPersonId(getMyChildN().getPersonId().getPersonId());
                            getNewFamilyAL().get(i).get(ii).setPersonType("NON-MEMBER");
                            getNewFamilyAL().get(i).get(ii).setPersonTypeId(3);
                            getNewFamilyAL().get(i).get(ii).setLastName(getMyChildN().getNonMemberId().getLastName());
                            getNewFamilyAL().get(i).get(ii).setFirstName(getMyChildN().getNonMemberId().getFirstName());
                            getNewFamilyAL().get(i).get(ii).setMiddleName(getMyChildN().getNonMemberId().getMiddleName());
                            getNewFamilyAL().get(i).get(ii).setBirthdate(getMyChildN().getNonMemberId().getBirthdate());
                            getNewFamilyAL().get(i).get(ii).setGender(getMyChildN().getNonMemberId().getGender());
                            getNewFamilyAL().get(i).get(ii).setMemNo(getMyChildN().getNonMemberId().getNonMemberId().toString());
                        }
                    }
                }
            }
        }
    }

}
