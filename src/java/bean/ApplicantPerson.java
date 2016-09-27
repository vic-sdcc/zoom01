/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopChild;
import model.CoopFamily;
import model.CoopNonMember;
import model.CoopPerson;
import model.CoopPersonAssociate;
import model.CoopPersonMem;
import model.CoopPersonNonMember;
import model.CoopPersonType;
import service.CoopChildFacadeREST;
import service.CoopFamilyFacadeREST;
import service.CoopNonMemberFacadeREST;
import service.CoopPersonAssociateFacadeREST;
import service.CoopPersonFacadeREST;
import service.CoopPersonMemFacadeREST;
import service.CoopPersonNonMemberFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class ApplicantPerson implements Serializable {

    /**
     * Creates a new instance of ApplicantPerson
     */
    public ApplicantPerson() {
        System.out.print("ApplicantPerson constructed");
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private CoopPersonType personType;
    @EJB
    private CoopPersonFacadeREST personREST;
    private CoopPerson person;
    @EJB
    private CoopNonMemberFacadeREST nonMemberREST;
    private CoopNonMember nonMember;
    @EJB
    private CoopPersonNonMemberFacadeREST personNonMemberREST;
    private CoopPersonNonMember personNonMember;
    @EJB
    private CoopPersonMemFacadeREST personMemREST;
    private CoopPersonMem personMem;
    @EJB
    private CoopPersonAssociateFacadeREST personAssociateREST;
    private CoopPersonAssociate personAssociate;
    @EJB
    private CoopFamilyFacadeREST familyREST;
    private CoopFamily family, family2;
    @EJB
    private CoopChildFacadeREST childREST;
    private CoopChild child;
    private String perId = "";
    @ManagedProperty(value = "#{applicantData}")
    private ApplicantData applicantData;
    @ManagedProperty(value = "#{memberData}")
    private MemberData memberData;
    @ManagedProperty(value = "#{associateData}")
    private AssociateData associateData;
    @ManagedProperty(value = "#{membershipType}")
    private MembershipType membershipType;
    private dateFormat dFormat;

    /*
     * getter setter
     */
    public CoopPersonType getPersonType() {
        if (personType == null) {
            personType = new CoopPersonType();
        }
        return personType;
    }

    public void setPersonType(CoopPersonType personType) {
        this.personType = personType;
    }

    public CoopPersonFacadeREST getPersonREST() {
        return personREST;
    }

    public void setPersonREST(CoopPersonFacadeREST personREST) {
        this.personREST = personREST;
    }

    public CoopPerson getPerson() {
        if (person == null) {
            person = new CoopPerson();
        }
        return person;
    }

    public void setPerson(CoopPerson person) {
        this.person = person;
    }

    public CoopNonMemberFacadeREST getNonMemberREST() {
        return nonMemberREST;
    }

    public void setNonMemberREST(CoopNonMemberFacadeREST nonMemberREST) {
        this.nonMemberREST = nonMemberREST;
    }

    public CoopNonMember getNonMember() {
        if (nonMember == null) {
            nonMember = new CoopNonMember();
        }
        return nonMember;
    }

    public void setNonMember(CoopNonMember nonMember) {
        this.nonMember = nonMember;
    }

    public CoopPersonNonMemberFacadeREST getPersonNonMemberREST() {
        return personNonMemberREST;
    }

    public void setPersonNonMemberREST(CoopPersonNonMemberFacadeREST personNonMemberREST) {
        this.personNonMemberREST = personNonMemberREST;
    }

    public CoopPersonNonMember getPersonNonMember() {
        if (personNonMember == null) {
            personNonMember = new CoopPersonNonMember();
        }
        return personNonMember;
    }

    public void setPersonNonMember(CoopPersonNonMember personNonMember) {
        this.personNonMember = personNonMember;
    }

    public CoopPersonMemFacadeREST getPersonMemREST() {
        return personMemREST;
    }

    public void setPersonMemREST(CoopPersonMemFacadeREST personMemREST) {
        this.personMemREST = personMemREST;
    }

    public CoopPersonMem getPersonMem() {
        if (personMem == null) {
            personMem = new CoopPersonMem();
        }
        return personMem;
    }

    public void setPersonMem(CoopPersonMem personMem) {
        this.personMem = personMem;
    }

    public CoopPersonAssociateFacadeREST getPersonAssociateREST() {
        return personAssociateREST;
    }

    public void setPersonAssociateREST(CoopPersonAssociateFacadeREST personAssociateREST) {
        this.personAssociateREST = personAssociateREST;
    }

    public CoopPersonAssociate getPersonAssociate() {
        if (personAssociate == null) {
            personAssociate = new CoopPersonAssociate();
        }
        return personAssociate;
    }

    public void setPersonAssociate(CoopPersonAssociate personAssociate) {
        this.personAssociate = personAssociate;
    }

    public CoopFamilyFacadeREST getFamilyREST() {
        return familyREST;
    }

    public void setFamilyREST(CoopFamilyFacadeREST familyREST) {
        this.familyREST = familyREST;
    }

    public CoopFamily getFamily() {
        if (family == null) {
            family = new CoopFamily();
        }
        return family;
    }

    public void setFamily(CoopFamily family) {
        this.family = family;
    }

    public CoopFamily getFamily2() {
        if (family2 == null) {
            family2 = new CoopFamily();
        }
        return family2;
    }

    public void setFamily2(CoopFamily family2) {
        this.family2 = family2;
    }

    public CoopChildFacadeREST getChildREST() {
        return childREST;
    }

    public void setChildREST(CoopChildFacadeREST childREST) {
        this.childREST = childREST;
    }

    public CoopChild getChild() {
        if (child == null) {
            child = new CoopChild();
        }
        return child;
    }

    public void setChild(CoopChild child) {
        this.child = child;
    }

    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
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

    public MembershipType getMembershipType() {
        if (membershipType == null) {
            membershipType = new MembershipType();
        }
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public dateFormat getdFormat() {
        if (dFormat == null) {
            dFormat = new dateFormat();
        }
        return dFormat;
    }

    public void setdFormat(dateFormat dFormat) {
        this.dFormat = dFormat;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("applicantPerson", null);
    }

    public void cpm(ArrayList<personData> alVar, int x, CoopPersonMem cpmVar) {
        alVar.get(x).setPersonId(cpmVar.getPersonId().getPersonId());
        alVar.get(x).setPersonType("REGULAR");
        alVar.get(x).setPersonTypeId(1);
        alVar.get(x).setLastName(cpmVar.getMemNo().getLastName());
        alVar.get(x).setFirstName(cpmVar.getMemNo().getFirstName());
        alVar.get(x).setMiddleName(cpmVar.getMemNo().getMiddleName());
        alVar.get(x).setBirthdate(cpmVar.getMemNo().getBirthdate());
        alVar.get(x).setGender(cpmVar.getMemNo().getGender());
        alVar.get(x).setMemNo(cpmVar.getMemNo().getMemNo());
    }

    public void cpa(ArrayList<personData> alVar, int x, CoopPersonAssociate cpaVar) {
        alVar.get(x).setPersonId(cpaVar.getPersonId().getPersonId());
        alVar.get(x).setPersonType("ASSOCIATE");
        alVar.get(x).setPersonTypeId(2);
        alVar.get(x).setLastName(cpaVar.getAssociateNo().getLastName());
        alVar.get(x).setFirstName(cpaVar.getAssociateNo().getFirstName());
        alVar.get(x).setMiddleName(cpaVar.getAssociateNo().getMiddleName());
        alVar.get(x).setBirthdate(cpaVar.getAssociateNo().getBirthdate());
        alVar.get(x).setGender(cpaVar.getAssociateNo().getGender());
        alVar.get(x).setMemNo(cpaVar.getAssociateNo().getMemNo());
    }

    public void cpnm(ArrayList<personData> alVar, int x, CoopPersonNonMember cpnmVar) {
        alVar.get(x).setPersonId(cpnmVar.getPersonId().getPersonId());
        alVar.get(x).setPersonType("NON-MEMBER");
        alVar.get(x).setPersonTypeId(3);
        alVar.get(x).setLastName(cpnmVar.getNonMemberId().getLastName());
        alVar.get(x).setFirstName(cpnmVar.getNonMemberId().getFirstName());
        alVar.get(x).setMiddleName(cpnmVar.getNonMemberId().getMiddleName());
        alVar.get(x).setBirthdate(cpnmVar.getNonMemberId().getBirthdate());
        alVar.get(x).setGender(cpnmVar.getNonMemberId().getGender());
        alVar.get(x).setMemNo(cpnmVar.getNonMemberId().getNonMemberId().toString());
    }

}
