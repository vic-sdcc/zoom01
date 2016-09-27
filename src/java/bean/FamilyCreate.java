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
public class FamilyCreate implements Serializable {

    /**
     * Creates a new instance of FamilyCreate
     */
    public FamilyCreate() {
        System.out.print("FamilyCreate constructed");
        newSet();
    }

    @ManagedProperty(value = "#{applicantPerson}")
    private ApplicantPerson applicantPerson;
    @ManagedProperty(value = "#{parentBean}")
    private ParentBean parentBean;
    @ManagedProperty(value = "#{siblingBean}")
    private SiblingBean siblingBean;
    @ManagedProperty(value = "#{spouseBean}")
    private SpouseBean spouseBean;

    /*
     * getter setter
     */
    public ApplicantPerson getApplicantPerson() {
        if (applicantPerson == null) {
            applicantPerson = new ApplicantPerson();
        }
        return applicantPerson;
    }

    public void setApplicantPerson(ApplicantPerson applicantPerson) {
        this.applicantPerson = applicantPerson;
    }

    public ParentBean getParentBean() {
        if (parentBean == null) {
            parentBean = new ParentBean();
        }
        return parentBean;
    }

    public void setParentBean(ParentBean parentBean) {
        this.parentBean = parentBean;
    }

    public SiblingBean getSiblingBean() {
        if (siblingBean == null) {
            siblingBean = new SiblingBean();
        }
        return siblingBean;
    }

    public void setSiblingBean(SiblingBean siblingBean) {
        this.siblingBean = siblingBean;
    }

    public SpouseBean getSpouseBean() {
        if (spouseBean == null) {
            spouseBean = new SpouseBean();
        }
        return spouseBean;
    }

    public void setSpouseBean(SpouseBean spouseBean) {
        this.spouseBean = spouseBean;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("familyCreate", null);
        getParentBean().beanclear();
        getSiblingBean().beanclear();
        getSpouseBean().beanclear();
        getApplicantPerson().beanclear();
    }

    public void clearAL() {
        getParentBean().getParentAL().clear();
        getSiblingBean().getSiblingAL().clear();
        getSpouseBean().getSpouseAL().clear();
        getSpouseBean().getNewFamilyAL().clear();
    }

    public void setAL() {
        getParentBean().getParentAL();
    }

    public void newSet() {
        getParentBean().getParentAL().add(new personData());
        getParentBean().getParentAL().add(new personData());
    }

    public void initMap() {
        getParentBean().initMap();
        getSiblingBean().initMap();
        getSpouseBean().initMap();
    }

    public void familyLoad() {
        parentPerson();
        siblingPerson();
        mainPerson();
        //spouseChildPerson();
    }

    /*
     f - mem 32
     m - mem 33
     sbx2 - nm 26,27
     spx2 - mem 34,35
     chx3 - nm 19,20,21

     family		child
     22-34		19-20
     22-35		21
     32-33		22-26-27
     */
    public void parentPerson() {
        for (int i = 0; i <= 1; i++) {
            if (getParentBean().getParentAL().get(i).getPersonTypeId().equals(1)) {
                try {
                    queryPm(getParentBean().getParentAL(), i);
                    //try to map both person if existing
                    //.edit data
                } catch (Exception e) {
                    System.out.print("FamilyCreate.java parentPerson()-1 " + e);
                    /*
                     getApplicantPerson().getPersonType().setPersonTypeId(1);
                     if (i == 0) {
                     createPersonRow(getParentBean().getFather());
                     createPersonMemRow(getParentBean().getFather());
                     } else {
                     createPersonRow(getParentBean().getMother());
                     createPersonMemRow(getParentBean().getMother());
                     }
                     */
                }
            }
            if (getParentBean().getParentAL().get(i).getPersonTypeId().equals(2)) {
                try {
                    queryPa(getParentBean().getParentAL(), i);
                    //try to map both person if existing
                    //.edit data
                } catch (Exception e) {
                    System.out.print("FamilyCreate.java parentPerson()-2 " + e);
                    /*
                     getApplicantPerson().getPersonType().setPersonTypeId(2);
                     if (i == 0) {
                     createPersonRow(getParentBean().getFather());
                     createPersonAssociateRow(getParentBean().getFather());
                     } else {
                     createPersonRow(getParentBean().getMother());
                     createPersonAssociateRow(getParentBean().getMother());
                     }
                     */
                }
            }
            if (getParentBean().getParentAL().get(i).getPersonTypeId().equals(3)) {
                try {
                    queryPn(getParentBean().getParentAL(), i);
                    //try to map both person if existing
                } catch (Exception e) {
                    System.out.print("FamilyCreate.java parentPerson()-3 " + e);
                    getApplicantPerson().getPersonType().setPersonTypeId(3);
                    if (i == 0) {
                        createPersonRow(getParentBean().getFather());
                        getApplicantPerson().getNonMember().setGender('M');
                        createNonMember(i, getParentBean().getParentAL());
                        createPersonNonMemberRow(getParentBean().getFather());
                    } else {
                        createPersonRow(getParentBean().getMother());
                        getApplicantPerson().getNonMember().setGender('F');
                        createNonMember(i, getParentBean().getParentAL());
                        createPersonNonMemberRow(getParentBean().getMother());
                    }
                }
            }
        }
        createFamilyRow(getParentBean().getFather(), getParentBean().getMother());
    }

    public void siblingPerson() {
        for (int i = 0; i < getSiblingBean().getSiblingAL().size(); i++) {
            if (getSiblingBean().getSiblingAL().get(i).getPersonTypeId().equals(1)) {
                try {
                    queryPm(getSiblingBean().getSiblingAL(), i);
                    //try to map both person if existing
                    //.edit data
                } catch (Exception e) {
                    System.out.print("FamilyCreate.java siblingPerson()-1 " + e);
                }
            }
            if (getSiblingBean().getSiblingAL().get(i).getPersonTypeId().equals(2)) {
                try {
                    queryPa(getSiblingBean().getSiblingAL(), i);
                    //try to map both person if existing
                    //.edit data
                } catch (Exception e) {
                    System.out.print("FamilyCreate.java siblingPerson()-2 " + e);
                }
            }
            if (getSiblingBean().getSiblingAL().get(i).getPersonTypeId().equals(3)) {
                try {
                    queryPn(getSiblingBean().getSiblingAL(), i);
                    //try to map both person if existing
                } catch (Exception e) {
                    System.out.print("FamilyCreate.java siblingPerson()-3 " + e);
                    getApplicantPerson().getPersonType().setPersonTypeId(3);
                    createPersonRow(getApplicantPerson().getPerson());
                    getApplicantPerson().getNonMember().setGender(getSiblingBean().getSiblingAL().get(i).getGender());
                    createNonMember(i, getSiblingBean().getSiblingAL());
                    createPersonNonMemberRow(getApplicantPerson().getPerson());
                    createChildRow();
                }
            }
        }
    }

    public void queryPm(ArrayList<personData> pm, int i) {
        getApplicantPerson().setPersonMem((CoopPersonMem) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery("SELECT m FROM CoopPersonMem m JOIN m.nonMemberId mm WHERE "
                + "CONCAT(UPPER(mm.firstName),UPPER(mm.middleName),UPPER(mm.lastName),mm.birthdate) = "
                + "CONCAT (UPPER('" + pm.get(i).getFirstName() + "'),"
                + "UPPER('" + pm.get(i).getMiddleName() + "'),"
                + "UPPER('" + pm.get(i).getLastName() + "'),"
                + "'" + getApplicantPerson().getdFormat().formatDate(pm.get(i).getBirthdate(), "yyyy-MM-dd") + "')").getResultList().get(0));
    }

    public void queryPa(ArrayList<personData> pa, int i) {
        getApplicantPerson().setPersonAssociate((CoopPersonAssociate) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery("SELECT a FROM CoopPersonAssociate a JOIN a.nonMemberId aa WHERE "
                + "CONCAT(UPPER(aa.firstName),UPPER(aa.middleName),UPPER(aa.lastName),aa.birthdate) = "
                + "CONCAT (UPPER('" + pa.get(i).getFirstName() + "'),"
                + "UPPER('" + pa.get(i).getMiddleName() + "'),"
                + "UPPER('" + pa.get(i).getLastName() + "'),"
                + "'" + getApplicantPerson().getdFormat().formatDate(pa.get(i).getBirthdate(), "yyyy-MM-dd") + "')").getResultList().get(0));
    }

    public void queryPn(ArrayList<personData> pn, int i) {
        getApplicantPerson().setPersonNonMember((CoopPersonNonMember) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery("SELECT nm FROM CoopPersonNonMember nm JOIN nm.nonMemberId nmm WHERE "
                + "CONCAT(UPPER(nmm.firstName),UPPER(nmm.middleName),UPPER(nmm.lastName),nmm.birthdate) = "
                + "CONCAT (UPPER('" + pn.get(i).getFirstName() + "'),"
                + "UPPER('" + pn.get(i).getMiddleName() + "'),"
                + "UPPER('" + pn.get(i).getLastName() + "'),"
                + "'" + getApplicantPerson().getdFormat().formatDate(pn.get(i).getBirthdate(), "yyyy-MM-dd") + "')").getResultList().get(0));
    }

    public void queryExist() {
        getApplicantPerson().setPersonNonMember((CoopPersonNonMember) getApplicantPerson().entityManagerFactory.createEntityManager().createQuery("SELECT nm FROM CoopPersonNonMember nm JOIN nm.nonMemberId nmm "
                + "WHERE CONCAT(UPPER(nmm.firstName),UPPER(nmm.middleName),UPPER(nmm.lastName),nmm.birthdate) = "
                + "CONCAT(UPPER('" + getApplicantPerson().getApplicantData().getApplicant().getFirstName() + "'),"
                + "UPPER('" + getApplicantPerson().getApplicantData().getApplicant().getMiddleName() + "'),"
                + "UPPER('" + getApplicantPerson().getApplicantData().getApplicant().getLastName() + "'),"
                + "'" + getApplicantPerson().getdFormat().formatDate(getApplicantPerson().getApplicantData().getApplicant().getBirthdate(), "yyyy-MM-dd") + "')").getResultList().get(0));
    }

    public void mainPerson() {
        if (getApplicantPerson().getMembershipType().getTypeValue().equals(0)) {
            getApplicantPerson().getPersonType().setPersonTypeId(1);
            try {
                queryExist();
                getApplicantPerson().setPerson(getApplicantPerson().getPersonNonMember().getPersonId());
                getApplicantPerson().getPerson().setPersonTypeId(getApplicantPerson().getPersonType());
                getApplicantPerson().getPersonREST().edit(getApplicantPerson().getPerson());
            } catch (Exception e) {
                System.out.print("FamilyCreate.java mainPerson()-1 " + e);
                //////createPersonRow();
                //////createPersonMemRow();
                if (getApplicantPerson().getPersonMem().getMemNo().getGender().equals('M')) {
                    getApplicantPerson().getFamily().setHusband(getApplicantPerson().getPerson());
                } else {
                    getApplicantPerson().getFamily().setWife(getApplicantPerson().getPerson());
                }
            }
        }
        if (getApplicantPerson().getMembershipType().getTypeValue().equals(1)) {
            getApplicantPerson().getPersonType().setPersonTypeId(2);
            try {
                queryExist();
                getApplicantPerson().setPerson(getApplicantPerson().getPersonNonMember().getPersonId());
                getApplicantPerson().getPerson().setPersonTypeId(getApplicantPerson().getPersonType());
                getApplicantPerson().getPersonREST().edit(getApplicantPerson().getPerson());
            } catch (Exception e) {
                System.out.print("FamilyCreate.java mainPerson()-2 " + e);
                //////createPersonRow();
                //////createPersonAssociateRow();
                if (getApplicantPerson().getPersonAssociate().getAssociateNo().getGender().equals('M')) {
                    getApplicantPerson().getFamily().setHusband(getApplicantPerson().getPerson());
                } else {
                    getApplicantPerson().getFamily().setWife(getApplicantPerson().getPerson());
                }
            }
        }
        createChildRow();
        //////createFamilyRow();
    }

    public void spouseChildPerson() {
        /*
         for (int i = 0; i < getSpouseBean().getSpouseAL().size(); i++) {
         for (int ii = i; ii < getSpouseBean().getChildAL().size(); ii++) {
         }
         }
         */
    }

    public void createPersonRow(CoopPerson thisPerson) {
        getApplicantPerson().getPerson().setPersonTypeId(getApplicantPerson().getPersonType());
        getApplicantPerson().getPersonREST().create(thisPerson);
    }

    public void createPersonMemRow(CoopPerson thisPerson) {
        getApplicantPerson().getPersonMem().setMemNo(getApplicantPerson().getMemberData().getMember());
        getApplicantPerson().getPersonMem().setPersonId(thisPerson);
        getApplicantPerson().getPersonMemREST().create(getApplicantPerson().getPersonMem());
    }

    public void createPersonAssociateRow(CoopPerson thisPerson) {
        getApplicantPerson().getPersonAssociate().setAssociateNo(getApplicantPerson().getAssociateData().getAssociate());
        getApplicantPerson().getPersonAssociate().setPersonId(thisPerson);
        getApplicantPerson().getPersonAssociateREST().create(getApplicantPerson().getPersonAssociate());
    }

    public void createPersonNonMemberRow(CoopPerson thisPerson) {
        getApplicantPerson().getPersonNonMember().setNonMemberId(getApplicantPerson().getNonMember());
        getApplicantPerson().getPersonNonMember().setPersonId(thisPerson);
        getApplicantPerson().getPersonNonMemberREST().create(getApplicantPerson().getPersonNonMember());
    }

    public void createNonMember(int x, ArrayList<personData> al) {
        getApplicantPerson().getNonMember().setLastName(al.get(x).getLastName());
        getApplicantPerson().getNonMember().setFirstName(al.get(x).getFirstName());
        getApplicantPerson().getNonMember().setMiddleName(al.get(x).getMiddleName());
        getApplicantPerson().getNonMember().setBirthdate(al.get(x).getBirthdate());
        getApplicantPerson().getNonMemberREST().create(getApplicantPerson().getNonMember());
    }

    public void createFamilyRow(CoopPerson thisPerson1, CoopPerson thisPerson2) {
        getParentBean().getMyParents().setHusband(thisPerson1);
        getParentBean().getMyParents().setWife(thisPerson2);
        getApplicantPerson().getFamilyREST().create(getParentBean().getMyParents());
    }

    public void createChildRow() {
        getApplicantPerson().getChild().setPersonId(getApplicantPerson().getPerson());
        getApplicantPerson().getChild().setFamilyId(getParentBean().getMyParents());
        getApplicantPerson().getChildREST().create(getApplicantPerson().getChild());
    }

}
