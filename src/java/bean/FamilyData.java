/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import model.CoopAssociate;
import model.CoopChild;
import model.CoopFamily;
import model.CoopMember;
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
import service.CoopPersonTypeFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class FamilyData implements Serializable {

    /**
     * Creates a new instance of FamilyData
     */
    public FamilyData() {
        System.out.print("FamilyData constructed");
        newSet();
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    @EJB
    private CoopPersonFacadeREST personREST;
    private CoopPerson person, father = null, mother = null, siblings = null, spouse = null, children = null;
    @EJB
    private CoopPersonTypeFacadeREST personTypeREST;
    private CoopPersonType personType;
    @EJB
    private CoopPersonMemFacadeREST personMemREST;
    private CoopPersonMem personMem, myFatherM = null, myMotherM = null, mySiblingsM = null, mySpouseM = null, myChildM = null;
    @EJB
    private CoopPersonAssociateFacadeREST personAssociateREST;
    private CoopPersonAssociate personAssociate, myFatherA = null, myMotherA = null, mySiblingsA = null, mySpouseA = null, myChildA = null;
    @EJB
    private CoopNonMemberFacadeREST nonMemberREST;
    private CoopNonMember nonMember;
    @EJB
    private CoopPersonNonMemberFacadeREST personNonMemberREST;
    private CoopPersonNonMember personNonMember, myFatherN = null, myMotherN = null, mySiblingsN = null, mySpouseN = null, myChildN = null, myPerson = null;
    @EJB
    private CoopFamilyFacadeREST familyREST;
    private CoopFamily family, familyTreeFamilyParents, familyTreeFamily, fam = null;
    @EJB
    private CoopChildFacadeREST childREST;
    private CoopChild child, familyTreeChild;
    private List<CoopPerson> personList, familyTreeList;
    private DataModel<CoopPerson> personModel, familyTreeModel;
    private personData pData, fatherData, motherData, siblingsData, spouseData, childData;
    private ArrayList<personData> parentsAL, siblingsAL, familyTree, familyTreeSiblings, familyTreeSpouses, familyTreeOldSpouses, familyTreeChildren, childSpouse;
    private ArrayList<String> childSpouseVal;
    private Integer fatherMemNo, motherMemNo, spouseMemNo, siblingMemNo, childMemNo, cntr = 0;
    private String pId = "", fName = "", lName = "", bDate = "", typeId = "";
    @ManagedProperty(value = "#{memberData}")
    private MemberData memberData;
    @ManagedProperty(value = "#{associateData}")
    private AssociateData associateData;
    @ManagedProperty(value = "#{applicantData}")
    private ApplicantData applicantData;
    @ManagedProperty(value = "#{membershipType}")
    private MembershipType membershipType;
    @ManagedProperty(value = "#{mode}")
    private Mode mode;
    private dateFormat dFormat;

    /*
     * getter setter
     */
    public CoopPerson getPerson() {
        if (person == null) {
            person = new CoopPerson();
        }
        return person;
    }

    public void setPerson(CoopPerson person) {
        this.person = person;
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

    public CoopPerson getSiblings() {
        if (siblings == null) {
            siblings = new CoopPerson();
        }
        return siblings;
    }

    public void setSiblings(CoopPerson siblings) {
        this.siblings = siblings;
    }

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

    public CoopPersonType getPersonType() {
        if (personType == null) {
            personType = new CoopPersonType();
        }
        return personType;
    }

    public void setPersonType(CoopPersonType personType) {
        this.personType = personType;
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

    public CoopPersonMem getMySiblingsM() {
        if (mySiblingsM == null) {
            mySiblingsM = new CoopPersonMem();
        }
        return mySiblingsM;
    }

    public void setMySiblingsM(CoopPersonMem mySiblingsM) {
        this.mySiblingsM = mySiblingsM;
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

    public CoopPersonAssociate getPersonAssociate() {
        if (personAssociate == null) {
            personAssociate = new CoopPersonAssociate();
        }
        return personAssociate;
    }

    public void setPersonAssociate(CoopPersonAssociate personAssociate) {
        this.personAssociate = personAssociate;
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

    public CoopPersonAssociate getMySiblingsA() {
        if (mySiblingsA == null) {
            mySiblingsA = new CoopPersonAssociate();
        }
        return mySiblingsA;
    }

    public void setMySiblingsA(CoopPersonAssociate mySiblingsA) {
        this.mySiblingsA = mySiblingsA;
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

    public CoopNonMember getNonMember() {
        if (nonMember == null) {
            nonMember = new CoopNonMember();
        }
        return nonMember;
    }

    public void setNonMember(CoopNonMember nonMember) {
        this.nonMember = nonMember;
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

    public CoopPersonNonMember getMySiblingsN() {
        if (mySiblingsN == null) {
            mySiblingsN = new CoopPersonNonMember();
        }
        return mySiblingsN;
    }

    public void setMySiblingsN(CoopPersonNonMember mySiblingsN) {
        this.mySiblingsN = mySiblingsN;
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

    public CoopPersonNonMember getMyPerson() {
        if (myPerson == null) {
            myPerson = new CoopPersonNonMember();
        }
        return myPerson;
    }

    public void setMyPerson(CoopPersonNonMember myPerson) {
        this.myPerson = myPerson;
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

    public CoopFamily getFam() {
        if (fam == null) {
            fam = new CoopFamily();
        }
        return fam;
    }

    public void setFam(CoopFamily fam) {
        this.fam = fam;
    }

    public CoopFamily getFamilyTreeFamilyParents() {
        if (familyTreeFamilyParents == null) {
            familyTreeFamilyParents = new CoopFamily();
        }
        return familyTreeFamilyParents;
    }

    public void setFamilyTreeFamilyParents(CoopFamily familyTreeFamilyParents) {
        this.familyTreeFamilyParents = familyTreeFamilyParents;
    }

    public CoopFamily getFamilyTreeFamily() {
        if (familyTreeFamily == null) {
            familyTreeFamily = new CoopFamily();
        }
        return familyTreeFamily;
    }

    public void setFamilyTreeFamily(CoopFamily familyTreeFamily) {
        this.familyTreeFamily = familyTreeFamily;
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

    public CoopChild getFamilyTreeChild() {
        if (familyTreeChild == null) {
            familyTreeChild = new CoopChild();
        }
        return familyTreeChild;
    }

    public void setFamilyTreeChild(CoopChild familyTreeChild) {
        this.familyTreeChild = familyTreeChild;
    }

    public List<CoopPerson> getPersonList() {
        return personList;
    }

    public void setPersonList(List<CoopPerson> personList) {
        this.personList = personList;
    }

    public List<CoopPerson> getFamilyTreeList() {
        if (familyTreeList == null) {
            familyTreeList = new ArrayList<>();
        }
        return familyTreeList;
    }

    public void setFamilyTreeList(List<CoopPerson> familyTreeList) {
        this.familyTreeList = familyTreeList;
    }

    public DataModel<CoopPerson> getPersonModel() {
        if (personModel == null) {
            personModel = new ListDataModel<>(getPersonList());
        }
        return personModel;
    }

    public void setPersonModel(DataModel<CoopPerson> personModel) {
        this.personModel = personModel;
    }

    public DataModel<CoopPerson> getFamilyTreeModel() {
        return familyTreeModel;
    }

    public void setFamilyTreeModel(DataModel<CoopPerson> familyTreeModel) {
        this.familyTreeModel = familyTreeModel;
    }

    public personData getPData() {
        if (pData == null) {
            pData = new personData();
        }
        return pData;
    }

    public void setPData(personData pData) {
        this.pData = pData;
    }

    public personData getFatherData() {
        if (fatherData == null) {
            fatherData = new personData();
        }
        return fatherData;
    }

    public void setFatherData(personData fatherData) {
        this.fatherData = fatherData;
    }

    public personData getMotherData() {
        if (motherData == null) {
            motherData = new personData();
        }
        return motherData;
    }

    public void setMotherData(personData motherData) {
        this.motherData = motherData;
    }

    public personData getSiblingsData() {
        if (siblingsData == null) {
            siblingsData = new personData();
        }
        return siblingsData;
    }

    public void setSiblingsData(personData siblingsData) {
        this.siblingsData = siblingsData;
    }

    public personData getSpouseData() {
        if (spouseData == null) {
            spouseData = new personData();
        }
        return spouseData;
    }

    public void setSpouseData(personData spouseData) {
        this.spouseData = spouseData;
    }

    public personData getChildData() {
        if (childData == null) {
            childData = new personData();
        }
        return childData;
    }

    public void setChildData(personData childData) {
        this.childData = childData;
    }

    public ArrayList<personData> getParentsAL() {
        if (parentsAL == null) {
            parentsAL = new ArrayList<>();
        }
        return parentsAL;
    }

    public void setParentsAL(ArrayList<personData> parentsAL) {
        this.parentsAL = parentsAL;
    }

    public ArrayList<personData> getSiblingsAL() {
        if (siblingsAL == null) {
            siblingsAL = new ArrayList<>();
        }
        return siblingsAL;
    }

    public void setSiblingsAL(ArrayList<personData> siblingsAL) {
        this.siblingsAL = siblingsAL;
    }

    public ArrayList<personData> getFamilyTree() {
        if (familyTree == null) {
            familyTree = new ArrayList<>();
        }
        return familyTree;
    }

    public void setFamilyTree(ArrayList<personData> familyTree) {
        this.familyTree = familyTree;
    }

    public ArrayList<personData> getFamilyTreeSiblings() {
        if (familyTreeSiblings == null) {
            familyTreeSiblings = new ArrayList<>();
        }
        return familyTreeSiblings;
    }

    public void setFamilyTreeSiblings(ArrayList<personData> familyTreeSiblings) {
        this.familyTreeSiblings = familyTreeSiblings;
    }

    public ArrayList<personData> getFamilyTreeSpouses() {
        if (familyTreeSpouses == null) {
            familyTreeSpouses = new ArrayList<>();
        }
        return familyTreeSpouses;
    }

    public void setFamilyTreeSpouses(ArrayList<personData> familyTreeSpouses) {
        this.familyTreeSpouses = familyTreeSpouses;
    }

    public ArrayList<personData> getFamilyTreeOldSpouses() {
        if (familyTreeOldSpouses == null) {
            familyTreeOldSpouses = new ArrayList<>();
        }
        return familyTreeOldSpouses;
    }

    public void setFamilyTreeOldSpouses(ArrayList<personData> familyTreeOldSpouses) {
        this.familyTreeOldSpouses = familyTreeOldSpouses;
    }

    public ArrayList<personData> getFamilyTreeChildren() {
        if (familyTreeChildren == null) {
            familyTreeChildren = new ArrayList<>();
        }
        return familyTreeChildren;
    }

    public void setFamilyTreeChildren(ArrayList<personData> familyTreeChildren) {
        this.familyTreeChildren = familyTreeChildren;
    }

    public ArrayList<personData> getChildSpouse() {
        if (childSpouse == null) {
            childSpouse = new ArrayList<>();
        }
        return childSpouse;
    }

    public void setChildSpouse(ArrayList<personData> childSpouse) {
        this.childSpouse = childSpouse;
    }

    public ArrayList<String> getChildSpouseVal() {
        if (childSpouseVal == null) {
            childSpouseVal = new ArrayList<>();
        }
        return childSpouseVal;
    }

    public void setChildSpouseVal(ArrayList<String> childSpouseVal) {
        this.childSpouseVal = childSpouseVal;
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

    public ApplicantData getApplicantData() {
        if (applicantData == null) {
            applicantData = new ApplicantData();
        }
        return applicantData;
    }

    public void setApplicantData(ApplicantData applicantData) {
        this.applicantData = applicantData;
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

    public Mode getMode() {
        if (mode == null) {
            mode = new Mode();
        }
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("familyData", null);
    }

    public void clearAL() {
        getFamilyTree().clear();
        getFamilyTreeSiblings().clear();
        getFamilyTreeSpouses().clear();
        getFamilyTreeChildren().clear();
        getChildSpouse().clear();
        getChildSpouseVal().clear();
    }

    public void setAL() {
        getFamilyTree();

        getFamilyTreeSiblings();
        getFamilyTreeSpouses();
        getFamilyTreeChildren();
        getChildSpouse();
        getChildSpouseVal();

    }

    public void newSet() {
        getFamilyTree().add(new personData());
        getFamilyTree().add(new personData());

        getFamilyTree().add(new personData());
        getFamilyTreeSiblings().add(new personData());
        getFamilyTreeSiblings().add(new personData());
        getFamilyTreeSiblings().add(new personData());
        getFamilyTreeSpouses().add(new personData());
        getFamilyTreeChildren().add(new personData());
        getChildSpouse().add(new personData());
        getChildSpouseVal().add(new String());

    }

    public void addSpouse() {
        if (getFamilyTreeSpouses().size() < 12) {
            getFamilyTreeSpouses().add(new personData());
        }
    }

    public void removeSpouse(int value) {
        if (getFamilyTreeSpouses().size() >= 0) {
            getFamilyTreeSpouses().remove(value);
        }
    }

    public void addSiblings() {
        if (getFamilyTreeSiblings().size() < 12) {
            getFamilyTreeSiblings().add(new personData());
        }
    }

    public void removeSiblings(int value) {
        if (getFamilyTreeSiblings().size() >= 0) {
            getFamilyTreeSiblings().remove(value);
        }
    }

    public void addChildren() {
        if (getFamilyTreeChildren().size() < 13) {
            getFamilyTreeChildren().add(new personData());
            getChildSpouse().add(new personData());
            getChildSpouseVal().add(new String());
        }
    }

    public void removeChildren(int value) {
        if (getFamilyTreeChildren().size() >= 0) {
            getFamilyTreeChildren().remove(value);
            getChildSpouse().remove(value);
            getChildSpouseVal().remove(value);
        }
    }

    public void classSingleton() {
        //PersonMember Singleton
        if (getPersonMem().getMemNo() == null) {
            getPersonMem().setMemNo(new CoopMember());
        }

        if (getPersonMem().getPersonId() == null) {
            getPersonMem().setPersonId(new CoopPerson());
        }

        if (getPersonMem().getPersonId().getPersonTypeId() == null) {
            getPersonMem().getPersonId().setPersonTypeId(new CoopPersonType());
        }

        //PersonAssociate Singleton
        if (getPersonAssociate().getAssociateNo() == null) {
            getPersonAssociate().setAssociateNo(new CoopAssociate());
        }

        if (getPersonAssociate().getPersonId() == null) {
            getPersonAssociate().setPersonId(new CoopPerson());
        }

        if (getPersonAssociate().getPersonId().getPersonTypeId() == null) {
            getPersonAssociate().getPersonId().setPersonTypeId(new CoopPersonType());
        }

        //PersonNonMember Singleton
        if (getPersonNonMember().getNonMemberId() == null) {
            getPersonNonMember().setNonMemberId(new CoopNonMember());
        }

        if (getPersonNonMember().getPersonId() == null) {
            getPersonNonMember().setPersonId(new CoopPerson());
        }

        if (getPersonNonMember().getPersonId().getPersonTypeId() == null) {
            getPersonNonMember().getPersonId().setPersonTypeId(new CoopPersonType());
        }
    }

    public void familyLoad() {
        if (getMembershipType().getTypeValue().equals(0)) {
            /*
             * =====================================================================
             * member
             * =====================================================================
             */
            getPersonMem().setMemNo(new CoopMember(getMemberData().getMember().getMemNo()));
            if (getPersonMem().getPersonId() == null) {
                System.out.println("Non Member");
                try {
                    //if member is existing in Non Member table
                    setPersonNonMember((CoopPersonNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonNonMember c "
                            + "WHERE LOWER(c.nonMemberId.lastName) = LOWER('" + getMemberData().getMember().getLastName() + "') "
                            + "AND LOWER(c.nonMemberId.firstName) = LOWER('" + getMemberData().getMember().getFirstName() + "') "
                            + "AND c.nonMemberId.birthdate = ?1").setParameter(1, getMemberData().getMember().getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                    getPersonMem().setPersonId(getPersonNonMember().getPersonId());
                    personNonMemberREST.remove(getPersonNonMember());
                    nonMemberREST.remove(getPersonNonMember().getNonMemberId());
                } catch (Exception e) {
                    getPersonMem().setPersonId(null);
                }
            }

            if (getPersonMem().getPersonId() == null) {
                System.out.println("Associate");
                try {
                    //if member is existing in Associate table
                    setPersonAssociate((CoopPersonAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonAssociate c "
                            + "WHERE LOWER(c.associateNo.lastName) = LOWER('" + getMemberData().getMember().getLastName() + "') "
                            + "AND LOWER(c.associateNo.firstName) = LOWER('" + getMemberData().getMember().getFirstName() + "') "
                            + "AND c.associateNo.birthdate = ?1").setParameter(1, getMemberData().getMember().getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                    getPersonMem().setPersonId(getPersonAssociate().getPersonId());
                    //personAssociateREST.remove(getPersonAssociate());
                } catch (Exception e) {
                    getPersonMem().setPersonId(null);
                }
            }

            if (getPersonMem().getPersonId() == null) {
                System.out.println("Member");
                try {
                    //if member is existing in Member table
                    setPersonMem((CoopPersonMem) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonMem c "
                            + "WHERE LOWER(c.memNo.lastName) = LOWER('" + getMemberData().getMember().getLastName() + "') "
                            + "AND LOWER(c.memNo.firstName) = LOWER('" + getMemberData().getMember().getFirstName() + "') "
                            + "AND c.memNo.birthdate = ?1").setParameter(1, getMemberData().getMember().getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                } catch (Exception e) {
                }
            }

            classSingleton();
            getPersonMem().getPersonId().setPersonTypeId(personTypeREST.find(1));
            //personREST.edit(getPersonMem().getPersonId());

            personMemREST.edit(getPersonMem());

            //if member is existing in Member table
            setPersonMem((CoopPersonMem) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonMem c "
                    + "WHERE c.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "'").getResultList().get(0));
            getPData().setPersonId(getPersonMem().getPersonId().getPersonId());
            getPData().setLastName(getMemberData().getMember().getLastName());
            getPData().setFirstName(getMemberData().getMember().getFirstName());
            getPData().setMiddleName(getMemberData().getMember().getMiddleName());
            getPData().setBirthdate(getMemberData().getMember().getBirthdate());
            getPData().setGender(getMemberData().getMember().getGender());
            getFamilyTree().set(2, getPData());
        } else if (getMembershipType().getTypeValue().equals(1)) {
            /*
             * =====================================================================
             * associate
             * =====================================================================
             */
            getPersonAssociate().setAssociateNo(new CoopAssociate(getAssociateData().getAssociate().getAssociateNo()));
            if (getPersonAssociate().getPersonId() == null) {
                System.out.println("Non Member");
                try {
                    //if member is existing in Non Member table
                    setPersonNonMember((CoopPersonNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonNonMember c "
                            + "WHERE LOWER(c.nonMemberId.lastName) = LOWER('" + getAssociateData().getAssociate().getLastName() + "') "
                            + "AND LOWER(c.nonMemberId.firstName) = LOWER('" + getAssociateData().getAssociate().getFirstName() + "') "
                            + "AND c.nonMemberId.birthdate = ?1").setParameter(1, getAssociateData().getAssociate().getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                    getPersonAssociate().setPersonId(getPersonNonMember().getPersonId());
                    personNonMemberREST.remove(getPersonNonMember());
                    nonMemberREST.remove(getPersonNonMember().getNonMemberId());
                } catch (Exception e) {
                    getPersonAssociate().setPersonId(null);
                }
            }
            if (getPersonAssociate().getPersonId() == null) {
                System.out.println("Associate");
                try {
                    //if member is existing in Associate table
                    setPersonAssociate((CoopPersonAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonAssociate c "
                            + "WHERE LOWER(c.associateNo.lastName) = LOWER('" + getAssociateData().getAssociate().getLastName() + "') "
                            + "AND LOWER(c.associateNo.firstName) = LOWER('" + getAssociateData().getAssociate().getFirstName() + "') "
                            + "AND c.associateNo.birthdate = ?1").setParameter(1, getAssociateData().getAssociate().getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                    getPersonAssociate().setPersonId(getPersonAssociate().getPersonId());
                    //personAssociateREST.remove(getPersonAssociate());
                } catch (Exception e) {
                    getPersonAssociate().setPersonId(null);
                }
            }

            if (getPersonAssociate().getPersonId() == null) {
                System.out.println("Member");
                try {
                    //if member is existing in Member table
                    setPersonMem((CoopPersonMem) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonMem c "
                            + "WHERE LOWER(c.memNo.lastName) = LOWER('" + getAssociateData().getAssociate().getLastName() + "') "
                            + "AND LOWER(c.memNo.firstName) = LOWER('" + getAssociateData().getAssociate().getFirstName() + "') "
                            + "AND c.memNo.birthdate = ?1").setParameter(1, getAssociateData().getAssociate().getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                } catch (Exception e) {
                }
            }

            classSingleton();
            getPersonAssociate().getPersonId().setPersonTypeId(personTypeREST.find(2));
            personAssociateREST.edit(getPersonAssociate());

            //if member is existing in Associate table
            setPersonAssociate((CoopPersonAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonAssociate c "
                    + "WHERE c.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "'").getResultList().get(0));
            getPData().setPersonId(getPersonAssociate().getPersonId().getPersonId());
            getPData().setLastName(getAssociateData().getAssociate().getLastName());
            getPData().setFirstName(getAssociateData().getAssociate().getFirstName());
            getPData().setMiddleName(getAssociateData().getAssociate().getMiddleName());
            getPData().setBirthdate(getAssociateData().getAssociate().getBirthdate());
            getPData().setGender(getAssociateData().getAssociate().getGender());
            getFamilyTree().set(2, getPData());

        }

        //if (getMode().isEditMode()) {
        //    selectedFamilyLoad();
        //}

        /*
         * =====================================================================
         * father
         * =====================================================================
         */
        setPersonNonMember(null);
        //put this class singleton here for no reason.
        classSingleton();
        getFamilyTree().get(0).setPersonId(0);
        if (getFamilyTree().get(0).getPersonTypeId() == null) {
            getFamilyTree().get(0).setPersonTypeId(0);
        }
        if (getFamilyTree().get(0).getLastName().length() > 0
                && getFamilyTree().get(0).getFirstName().length() > 0
                && getFamilyTree().get(0).getBirthdate().toString().length() > 0) {
            if (getFamilyTree().get(0).getPersonId() == 0 && (getFamilyTree().get(0).getPersonTypeId() == 3 || getFamilyTree().get(0).getPersonTypeId() == 0)) {
                System.out.println("Father Non Member");
                try {
                    //if father is existing in Non Member table
                    setPersonNonMember((CoopPersonNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonNonMember c "
                            + "WHERE LOWER(c.nonMemberId.lastName) = LOWER('" + getFamilyTree().get(0).getLastName() + "') "
                            + "AND LOWER(c.nonMemberId.firstName) = LOWER('" + getFamilyTree().get(0).getFirstName() + "') "
                            + "AND c.nonMemberId.birthdate = ?1").setParameter(1, getFamilyTree().get(0).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                    getFamilyTree().get(0).setPersonId(getPersonNonMember().getPersonId().getPersonId());
                } catch (Exception e) {
                    getFamilyTree().get(0).setPersonId(0);
                }
            }
            if (getFamilyTree().get(0).getPersonId() == 0 && (getFamilyTree().get(0).getPersonTypeId() == 2 || getFamilyTree().get(0).getPersonTypeId() == 0)) {
                System.out.println("Father Associate");
                try {
                    //if father is existing in Associate table
                    setPersonAssociate((CoopPersonAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonAssociate c "
                            + "WHERE LOWER(c.associateNo.lastName) = LOWER('" + getFamilyTree().get(0).getLastName() + "') "
                            + "AND LOWER(c.associateNo.firstName) = LOWER('" + getFamilyTree().get(0).getFirstName() + "') "
                            + "AND c.associateNo.birthdate = ?1").setParameter(1, getFamilyTree().get(0).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                    getFamilyTree().get(0).setPersonId(getPersonAssociate().getPersonId().getPersonId());
                } catch (Exception e) {
                    getFamilyTree().get(0).setPersonId(0);
                }
            }
            if (getFamilyTree().get(0).getPersonId() == 0 && (getFamilyTree().get(0).getPersonTypeId() == 1 || getFamilyTree().get(0).getPersonTypeId() == 0)) {
                System.out.println("Father Member");
                try {
                    //if father is existing in Member table
                    setPersonMem((CoopPersonMem) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonMem c "
                            + "WHERE LOWER(c.memNo.lastName) = LOWER('" + getFamilyTree().get(0).getLastName() + "') "
                            + "AND LOWER(c.memNo.firstName) = LOWER('" + getFamilyTree().get(0).getFirstName() + "') "
                            + "AND c.memNo.birthdate = ?1").setParameter(1, getFamilyTree().get(0).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                    getFamilyTree().get(0).setPersonId(getPersonMem().getPersonId().getPersonId());
                } catch (Exception e) {
                }
            }

            getPersonNonMember().getNonMemberId().setLastName(getFamilyTree().get(0).getLastName());
            getPersonNonMember().getNonMemberId().setFirstName(getFamilyTree().get(0).getFirstName());
            getPersonNonMember().getNonMemberId().setMiddleName(getFamilyTree().get(0).getMiddleName());
            getPersonNonMember().getNonMemberId().setBirthdate(getFamilyTree().get(0).getBirthdate());
            getPersonNonMember().getNonMemberId().setGender('M');
            if (getFamilyTree().get(0).getPersonId() == 0) {
                getPersonNonMember().getPersonId().setPersonTypeId(personTypeREST.find(3));
                nonMemberREST.create(getPersonNonMember().getNonMemberId());
                personREST.create(getPersonNonMember().getPersonId());
                getFamilyTree().get(0).setPersonId(getPersonNonMember().getPersonId().getPersonId());
                personNonMemberREST.create(getPersonNonMember());
            } else {
                nonMemberREST.edit(getPersonNonMember().getNonMemberId());
            }
        } else {
            CoopNonMember fatherNonMem = null;
            if (getFamilyTree().get(0).getPersonId() == 0) {
                //Check if father already exist in the nonmember
                try {
                    fatherNonMem = (CoopNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopNonMember c "
                            + "WHERE LOWER(c.lastName) = LOWER('" + getPersonMem().getPersonId().getPersonId().toString() + "') "
                            + "AND LOWER(c.firstName) = LOWER('FATHER') "
                            + "AND c.birthdate = ?1").setParameter(1, new Date(), TemporalType.TIMESTAMP).getResultList().get(0);
                } catch (Exception e) {
                    getPersonNonMember().getNonMemberId().setLastName(getPersonMem().getPersonId().getPersonId().toString());
                    getPersonNonMember().getNonMemberId().setFirstName("FATHER");
                    getPersonNonMember().getNonMemberId().setMiddleName("OF");
                    getPersonNonMember().getNonMemberId().setBirthdate(new Date());
                    getPersonNonMember().getNonMemberId().setGender('M');
                    nonMemberREST.create(getPersonNonMember().getNonMemberId());
                }
                //check if the ralation of father already exist
                try {
                    setPersonNonMember((CoopPersonNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonNonMember c "
                            + "WHERE c.nonMemberId.nonMemberId = " + fatherNonMem.getNonMemberId() + "").getResultList().get(0));
                } catch (Exception e) {
                    getPersonNonMember().getPersonId().setPersonTypeId(personTypeREST.find(3));
                    personREST.create(getPersonNonMember().getPersonId());
                    if (getPersonNonMember().getNonMemberId().getNonMemberId() == null) {
                        getPersonNonMember().setNonMemberId(fatherNonMem);
                    }
                    personNonMemberREST.create(getPersonNonMember());
                }
                getFamilyTree().get(0).setPersonId(getPersonNonMember().getPersonId().getPersonId());
            }
        }

        /*
         * =====================================================================
         * mother
         * =====================================================================
         */
        setPersonNonMember(null);
        //put this class singleton here for no reason.
        classSingleton();
        getFamilyTree().get(1).setPersonId(0);
        if (getFamilyTree().get(1).getPersonTypeId() == null) {
            getFamilyTree().get(1).setPersonTypeId(0);
        }
        if (getFamilyTree().get(1).getLastName().length() > 0
                && getFamilyTree().get(1).getFirstName().length() > 0
                && getFamilyTree().get(1).getBirthdate().toString().length() > 0) {
            if (getFamilyTree().get(1).getPersonId() == 0 && (getFamilyTree().get(1).getPersonTypeId() == 3 || getFamilyTree().get(1).getPersonTypeId() == 0)) {
                System.out.println("Mother Non Member");
                try {
                    //if father is existing in Non Member table
                    setPersonNonMember((CoopPersonNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonNonMember c "
                            + "WHERE LOWER(c.nonMemberId.lastName) = LOWER('" + getFamilyTree().get(1).getLastName() + "') "
                            + "AND LOWER(c.nonMemberId.firstName) = LOWER('" + getFamilyTree().get(1).getFirstName() + "') "
                            + "AND c.nonMemberId.birthdate = ?1").setParameter(1, getFamilyTree().get(1).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                    getFamilyTree().get(1).setPersonId(getPersonNonMember().getPersonId().getPersonId());
                } catch (Exception e) {
                    getFamilyTree().get(1).setPersonId(0);
                }
            }
            if (getFamilyTree().get(1).getPersonId() == 0 && (getFamilyTree().get(1).getPersonTypeId() == 2 || getFamilyTree().get(1).getPersonTypeId() == 0)) {
                System.out.println("Mother Associate");
                try {
                    //if father is existing in Associate table
                    setPersonAssociate((CoopPersonAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonAssociate c "
                            + "WHERE LOWER(c.associateNo.lastName) = LOWER('" + getFamilyTree().get(1).getLastName() + "') "
                            + "AND LOWER(c.associateNo.firstName) = LOWER('" + getFamilyTree().get(1).getFirstName() + "') "
                            + "AND c.associateNo.birthdate = ?1").setParameter(1, getFamilyTree().get(1).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                    getFamilyTree().get(1).setPersonId(getPersonAssociate().getPersonId().getPersonId());
                } catch (Exception e) {
                    getFamilyTree().get(1).setPersonId(0);
                }
            }
            if (getFamilyTree().get(1).getPersonId() == 0 && (getFamilyTree().get(1).getPersonTypeId() == 1 || getFamilyTree().get(1).getPersonTypeId() == 0)) {
                System.out.println("Mother Member");
                try {
                    //if father is existing in Member table
                    setPersonMem((CoopPersonMem) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonMem c "
                            + "WHERE LOWER(c.memNo.lastName) = LOWER('" + getFamilyTree().get(1).getLastName() + "') "
                            + "AND LOWER(c.memNo.firstName) = LOWER('" + getFamilyTree().get(1).getFirstName() + "') "
                            + "AND c.memNo.birthdate = ?1").setParameter(1, getFamilyTree().get(1).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                    getFamilyTree().get(1).setPersonId(getPersonMem().getPersonId().getPersonId());
                } catch (Exception e) {
                }
            }
            getPersonNonMember().getNonMemberId().setLastName(getFamilyTree().get(1).getLastName());
            getPersonNonMember().getNonMemberId().setFirstName(getFamilyTree().get(1).getFirstName());
            getPersonNonMember().getNonMemberId().setMiddleName(getFamilyTree().get(1).getMiddleName());
            getPersonNonMember().getNonMemberId().setBirthdate(getFamilyTree().get(1).getBirthdate());
            getPersonNonMember().getNonMemberId().setGender('F');
            if (getFamilyTree().get(1).getPersonId() == 0) {

                getPersonNonMember().getPersonId().setPersonTypeId(personTypeREST.find(3));
                nonMemberREST.create(getPersonNonMember().getNonMemberId());
                personREST.create(getPersonNonMember().getPersonId());
                getFamilyTree().get(1).setPersonId(getPersonNonMember().getPersonId().getPersonId());
                personNonMemberREST.create(getPersonNonMember());
            } else {
                nonMemberREST.edit(getPersonNonMember().getNonMemberId());
            }
        } else {
            CoopNonMember motherNonMem = null;
            if (getFamilyTree().get(1).getPersonId() == 0) {
                //Check if mother already exist in the nonmember
                try {
                    motherNonMem = (CoopNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopNonMember c "
                            + "WHERE LOWER(c.lastName) = LOWER('" + getPersonMem().getPersonId().getPersonId().toString() + "') "
                            + "AND LOWER(c.firstName) = LOWER('MOTHER') "
                            + "AND c.birthdate = ?1").setParameter(1, new Date(), TemporalType.TIMESTAMP).getResultList().get(0);
                } catch (Exception e) {
                    getPersonNonMember().getNonMemberId().setLastName(getPersonMem().getPersonId().getPersonId().toString());
                    getPersonNonMember().getNonMemberId().setFirstName("MOTHER");
                    getPersonNonMember().getNonMemberId().setMiddleName("OF");
                    getPersonNonMember().getNonMemberId().setBirthdate(new Date());
                    getPersonNonMember().getNonMemberId().setGender('F');
                    nonMemberREST.create(getPersonNonMember().getNonMemberId());
                }//check if the ralation of mother already exist
                try {
                    setPersonNonMember((CoopPersonNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonNonMember c "
                            + "WHERE c.nonMemberId.nonMemberId = " + motherNonMem.getNonMemberId() + "").getResultList().get(0));
                } catch (Exception e) {
                    getPersonNonMember().getPersonId().setPersonTypeId(personTypeREST.find(3));
                    personREST.create(getPersonNonMember().getPersonId());
                    if (getPersonNonMember().getNonMemberId().getNonMemberId() == null) {
                        getPersonNonMember().setNonMemberId(motherNonMem);
                    }
                    personNonMemberREST.create(getPersonNonMember());
                }
                getFamilyTree().get(1).setPersonId(getPersonNonMember().getPersonId().getPersonId());
            }
        }

        /*
         * =====================================================================
         * family
         * =====================================================================
         */
        try {
            setFamilyTreeFamilyParents((CoopFamily) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopFamily c "
                    + "WHERE c.husband.personId =" + getFamilyTree().get(0).getPersonId() + " "
                    + "AND c.wife.personId =" + getFamilyTree().get(1).getPersonId() + " ").getResultList().get(0));
        } catch (Exception e) {
            getFamilyTreeFamilyParents().setHusband(personREST.find(getFamilyTree().get(0).getPersonId()));
            getFamilyTreeFamilyParents().setWife(personREST.find(getFamilyTree().get(1).getPersonId()));
            familyREST.create(getFamilyTreeFamilyParents());
        }

        try {
            //do nothing if child data not exist
            CoopChild memberChild = (CoopChild) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopChild c "
                    + "WHERE c.personId.personId =" + getFamilyTree().get(2).getPersonId() + "").getResultList().get(0);
            childREST.remove(memberChild);
        } catch (Exception e) {
        }

        //create person childID
        getFamilyTreeChild().setFamilyId(getFamilyTreeFamilyParents());
        getFamilyTreeChild().setPersonId(personREST.find(getPersonMem().getPersonId().getPersonId()));
        childREST.create(getFamilyTreeChild());

        /*
         * =====================================================================
         * siblings
         * =====================================================================
         */
        setPersonNonMember(null);
        //put this class singleton here for no reason.
        classSingleton();
        for (int i = 0; i != getFamilyTreeSiblings().size(); i++) {
            getFamilyTreeSiblings().get(i).setPersonId(0);
            if (getFamilyTreeSiblings().get(i).getPersonTypeId() == null) {
                getFamilyTreeSiblings().get(i).setPersonTypeId(0);
            }
            if (getFamilyTreeSiblings().get(i).getLastName().length() > 0
                    && getFamilyTreeSiblings().get(i).getFirstName().length() > 0
                    && getFamilyTreeSiblings().get(i).getBirthdate().toString().length() > 0) {

                //System.out.println("Sibling" + i + " Name: " + getFamilyTreeSiblings().get(i).getLastName() + ", "
                //        + "" + getFamilyTreeSiblings().get(i).getFirstName() + " "
                //        + "" + getFamilyTreeSiblings().get(i).getBirthdate());
                if (getFamilyTreeSiblings().get(i).getPersonId() == 0 && (getFamilyTreeSiblings().get(i).getPersonTypeId() == 3 || getFamilyTreeSiblings().get(i).getPersonTypeId() == 0)) {
                    System.out.println("Sibling" + i + " Non Member");
                    try {
                        //if father is existing in Non Member table
                        setPersonNonMember((CoopPersonNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonNonMember c "
                                + "WHERE LOWER(c.nonMemberId.lastName) = LOWER('" + getFamilyTreeSiblings().get(i).getLastName() + "') "
                                + "AND LOWER(c.nonMemberId.firstName) = LOWER('" + getFamilyTreeSiblings().get(i).getFirstName() + "') "
                                + "AND c.nonMemberId.birthdate = ?1").setParameter(1, getFamilyTreeSiblings().get(i).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                        getFamilyTreeSiblings().get(i).setPersonId(getPersonNonMember().getPersonId().getPersonId());
                    } catch (Exception e) {
                        getFamilyTreeSiblings().get(i).setPersonId(0);
                    }
                }
                if (getFamilyTreeSiblings().get(i).getPersonId() == 0 && (getFamilyTreeSiblings().get(i).getPersonTypeId() == 2 || getFamilyTreeSiblings().get(i).getPersonTypeId() == 0)) {
                    System.out.println("Sibling" + i + " Associate");
                    try {
                        //if father is existing in Associate table
                        setPersonAssociate((CoopPersonAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonAssociate c "
                                + "WHERE LOWER(c.associateNo.lastName) = LOWER('" + getFamilyTreeSiblings().get(i).getLastName() + "') "
                                + "AND LOWER(c.associateNo.firstName) = LOWER('" + getFamilyTreeSiblings().get(i).getFirstName() + "') "
                                + "AND c.associateNo.birthdate = ?1").setParameter(1, getFamilyTreeSiblings().get(i).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                        getFamilyTreeSiblings().get(i).setPersonId(getPersonAssociate().getPersonId().getPersonId());
                    } catch (Exception e) {
                        getFamilyTreeSiblings().get(i).setPersonId(0);
                    }
                }
                if (getFamilyTreeSiblings().get(i).getPersonId() == 0 && (getFamilyTreeSiblings().get(i).getPersonTypeId() == 1 || getFamilyTreeSiblings().get(i).getPersonTypeId() == 0)) {
                    System.out.println("Sibling" + i + " Member");
                    try {
                        //if father is existing in Member table
                        setPersonMem((CoopPersonMem) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonMem c "
                                + "WHERE LOWER(c.memNo.lastName) = LOWER('" + getFamilyTreeSiblings().get(i).getLastName() + "') "
                                + "AND LOWER(c.memNo.firstName) = LOWER('" + getFamilyTreeSiblings().get(i).getFirstName() + "') "
                                + "AND c.memNo.birthdate = ?1").setParameter(1, getFamilyTreeSiblings().get(i).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                        getFamilyTreeSiblings().get(i).setPersonId(getPersonMem().getPersonId().getPersonId());
                    } catch (Exception e) {
                    }
                }

                getPersonNonMember().getNonMemberId().setLastName(getFamilyTreeSiblings().get(i).getLastName());
                getPersonNonMember().getNonMemberId().setFirstName(getFamilyTreeSiblings().get(i).getFirstName());
                getPersonNonMember().getNonMemberId().setMiddleName(getFamilyTreeSiblings().get(i).getMiddleName());
                getPersonNonMember().getNonMemberId().setBirthdate(getFamilyTreeSiblings().get(i).getBirthdate());
                getPersonNonMember().getNonMemberId().setGender(getFamilyTreeSiblings().get(i).getGender());
                getFamilyTreeSiblings().get(i).setGender(getFamilyTreeSiblings().get(i).getGender());
                getFamilyTreeSiblings().get(i).setMiddleName(getFamilyTreeSiblings().get(i).getMiddleName());
                if (getFamilyTreeSiblings().get(i).getPersonId() == 0) {
                    getPersonNonMember().getPersonId().setPersonTypeId(personTypeREST.find(3));
                    nonMemberREST.create(getPersonNonMember().getNonMemberId());
                    personREST.create(getPersonNonMember().getPersonId());
                    getFamilyTreeSiblings().get(i).setPersonId(getPersonNonMember().getPersonId().getPersonId());
                    personNonMemberREST.create(getPersonNonMember());
                } else {
                    nonMemberREST.edit(getPersonNonMember().getNonMemberId());
                }

                //create family siblings
                getFamilyTreeChild().setFamilyId(getFamilyTreeFamilyParents());
                getFamilyTreeChild().setPersonId(personREST.find(getFamilyTreeSiblings().get(i).getPersonId()));
                childREST.create(getFamilyTreeChild());
            }
        }

        /*
         * =====================================================================
         * spouse
         * =====================================================================
         */
        setPersonNonMember(null);
        //put this class singleton here for no reason.
        classSingleton();
        if (getFamilyTreeSpouses().size() > 0) {
            for (int i = 0; i != getFamilyTreeSpouses().size(); i++) {
                getFamilyTreeSpouses().get(i).setPersonId(0);
                if (getFamilyTreeSpouses().get(i).getPersonTypeId() == null) {
                    getFamilyTreeSpouses().get(i).setPersonTypeId(0);
                }
                if (getMemberData().getMember().getGender().equals('M') || getAssociateData().getAssociate().getGender().equals('M')) {
                    getFamilyTreeSpouses().get(i).setGender('F');
                    getFamilyTreeFamily().setHusband(personREST.find(getFamilyTree().get(2).getPersonId()));
                } else {
                    getFamilyTreeSpouses().get(i).setGender('M');
                    getFamilyTreeFamily().setWife(personREST.find(getFamilyTree().get(2).getPersonId()));
                }

                //this is where you gonna make changes
                if (getFamilyTreeSpouses().get(i).getLastName().length() > 0
                        && getFamilyTreeSpouses().get(i).getFirstName().length() > 0
                        && getFamilyTreeSpouses().get(i).getBirthdate().toString().length() > 0) {

                    if (getFamilyTreeSpouses().get(i).getPersonId() == 0 && (getFamilyTreeSpouses().get(i).getPersonTypeId() == 3 || getFamilyTreeSpouses().get(i).getPersonTypeId() == 0)) {
                        System.out.println("Spouse" + i + " Non Member");
                        try {
                            //if father is existing in Non Member table
                            setPersonNonMember((CoopPersonNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonNonMember c "
                                    + "WHERE LOWER(c.nonMemberId.lastName) = LOWER('" + getFamilyTreeSpouses().get(i).getLastName() + "') "
                                    + "AND LOWER(c.nonMemberId.firstName) = LOWER('" + getFamilyTreeSpouses().get(i).getFirstName() + "') "
                                    + "AND c.nonMemberId.birthdate = ?1").setParameter(1, getFamilyTreeSpouses().get(i).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                            getFamilyTreeSpouses().get(i).setPersonId(getPersonNonMember().getPersonId().getPersonId());
                        } catch (Exception e) {
                            getFamilyTreeSpouses().get(i).setPersonId(0);
                        }
                    }
                    if (getFamilyTreeSpouses().get(i).getPersonId() == 0 && (getFamilyTreeSpouses().get(i).getPersonTypeId() == 2 || getFamilyTreeSpouses().get(i).getPersonTypeId() == 0)) {
                        System.out.println("Spouse" + i + " Associate");
                        try {
                            //if father is existing in Associate table
                            setPersonAssociate((CoopPersonAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonAssociate c "
                                    + "WHERE LOWER(c.associateNo.lastName) = LOWER('" + getFamilyTreeSpouses().get(i).getLastName() + "') "
                                    + "AND LOWER(c.associateNo.firstName) = LOWER('" + getFamilyTreeSpouses().get(i).getFirstName() + "') "
                                    + "AND c.associateNo.birthdate = ?1").setParameter(1, getFamilyTreeSpouses().get(i).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                            getFamilyTreeSpouses().get(i).setPersonId(getPersonAssociate().getPersonId().getPersonId());
                        } catch (Exception e) {
                            getFamilyTreeSpouses().get(i).setPersonId(0);
                        }
                    }
                    if (getFamilyTreeSpouses().get(i).getPersonId() == 0 && (getFamilyTreeSpouses().get(i).getPersonTypeId() == 1 || getFamilyTreeSpouses().get(i).getPersonTypeId() == 0)) {
                        System.out.println("Spouse" + i + " Member");
                        try {
                            //if father is existing in Member table
                            setPersonMem((CoopPersonMem) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonMem c "
                                    + "WHERE LOWER(c.memNo.lastName) = LOWER('" + getFamilyTreeSpouses().get(i).getLastName() + "') "
                                    + "AND LOWER(c.memNo.firstName) = LOWER('" + getFamilyTreeSpouses().get(i).getFirstName() + "') "
                                    + "AND c.memNo.birthdate = ?1").setParameter(1, getFamilyTreeSpouses().get(i).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                            getFamilyTreeSpouses().get(i).setPersonId(getPersonMem().getPersonId().getPersonId());
                        } catch (Exception e) {
                        }
                    }

                    getFamilyTreeFamily().setFamilyId(null);

                    getPersonNonMember().getNonMemberId().setLastName(getFamilyTreeSpouses().get(i).getLastName());
                    getPersonNonMember().getNonMemberId().setFirstName(getFamilyTreeSpouses().get(i).getFirstName());
                    getPersonNonMember().getNonMemberId().setMiddleName(getFamilyTreeSpouses().get(i).getMiddleName());
                    getPersonNonMember().getNonMemberId().setBirthdate(getFamilyTreeSpouses().get(i).getBirthdate());
                    getPersonNonMember().getNonMemberId().setGender(getFamilyTreeSpouses().get(i).getGender());

                } else if (getFamilyTreeSpouses().size() == 1
                        && getFamilyTreeSpouses().get(0).getLastName().length() <= 0
                        && getFamilyTreeSpouses().get(0).getFirstName().length() <= 0
                        && getFamilyTreeSpouses().get(0).getBirthdate().toString().length() <= 0) {

                    try {
                        //if father is existing in Non Member table
                        setPersonNonMember((CoopPersonNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonNonMember c "
                                + "WHERE LOWER(c.nonMemberId.lastName) = LOWER('" + getFamilyTree().get(2).getPersonId().toString() + "') "
                                + "AND LOWER(c.nonMemberId.firstName) = LOWER('Spouse') "
                                + "AND c.nonMemberId.birthdate = ?1").setParameter(1, new Date(), TemporalType.TIMESTAMP).getResultList().get(0));
                        getFamilyTreeSpouses().get(i).setPersonId(getPersonNonMember().getPersonId().getPersonId());
                        getPersonNonMember().getNonMemberId().setLastName(getFamilyTreeSpouses().get(i).getLastName());
                        getPersonNonMember().getNonMemberId().setFirstName(getFamilyTreeSpouses().get(i).getFirstName());
                        getPersonNonMember().getNonMemberId().setMiddleName(getFamilyTreeSpouses().get(i).getMiddleName());
                        getPersonNonMember().getNonMemberId().setBirthdate(getFamilyTreeSpouses().get(i).getBirthdate());
                        getPersonNonMember().getNonMemberId().setGender(getFamilyTreeSpouses().get(i).getGender());
                    } catch (Exception e) {
                        getFamilyTreeSpouses().get(i).setPersonId(0);
                        getPersonNonMember().getNonMemberId().setLastName(getFamilyTree().get(2).getPersonId().toString());
                        getPersonNonMember().getNonMemberId().setFirstName("Spouse");
                        getPersonNonMember().getNonMemberId().setMiddleName("of");
                        getPersonNonMember().getNonMemberId().setBirthdate(new Date());
                        getPersonNonMember().getNonMemberId().setGender(getFamilyTreeSpouses().get(i).getGender());
                    }

                }

                if (getFamilyTreeSpouses().get(i).getPersonId() == 0) {
                    getPersonNonMember().getPersonId().setPersonTypeId(personTypeREST.find(3));
                    nonMemberREST.create(getPersonNonMember().getNonMemberId());
                    personREST.create(getPersonNonMember().getPersonId());
                    getFamilyTreeSpouses().get(i).setPersonId(getPersonNonMember().getPersonId().getPersonId());
                    personNonMemberREST.create(getPersonNonMember());
                } else {
                    nonMemberREST.edit(getPersonNonMember().getNonMemberId());
                }

                //System.out.println("Spouse person ID: " + getFamilyTreeSpouses().get(i).getPersonId());
                if (getMemberData().getMember().getGender().equals('M') || getAssociateData().getAssociate().getGender().equals('M')) {
                    try {
                        setFamilyTreeFamily((CoopFamily) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopFamily c "
                                + "WHERE COALESCE(c.husband.personId,0) =" + getFamilyTree().get(2).getPersonId() + " "
                                + "AND COALESCE(c.wife.personId,0) =" + getFamilyTreeSpouses().get(i).getPersonId() + " ").getResultList().get(0));
                        //+ "WHERE COALESCE(c.husband.personId,0) = " + familyTree.get(2).getPersonId() + " "
                        //+ "AND COALESCE(c.wife.personId,0) = " + familySpouse.getPersonId() + " ").getResultList().get(0);
                        //familyREST.edit(familyTreeFamily);
                    } catch (Exception f) {

                        getFamilyTreeFamily().setWife(personREST.find(getFamilyTreeSpouses().get(i).getPersonId()));
                        try {
                            familyREST.edit(getFamilyTreeFamily());
                        } catch (Exception e) {
                            familyREST.create(getFamilyTreeFamily());
                        }
                    }
                } else {
                    try {
                        setFamilyTreeFamily((CoopFamily) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopFamily c "
                                + "WHERE COALESCE(c.wife.personId,0) =" + getFamilyTree().get(2).getPersonId() + " "
                                + "AND COALESCE(c.husband.personId,0) =" + getFamilyTreeSpouses().get(i).getPersonId() + " ").getResultList().get(0));
                        //+ "WHERE COALESCE(c.husband.personId,0) = " + familyTree.get(2).getPersonId() + " "
                        //+ "AND COALESCE(c.wife.personId,0) = " + familySpouse.getPersonId() + " ").getResultList().get(0);
                        //familyREST.edit(familyTreeFamily);
                    } catch (Exception f) {

                        getFamilyTreeFamily().setHusband(personREST.find(getFamilyTreeSpouses().get(i).getPersonId()));
                        try {
                            familyREST.edit(getFamilyTreeFamily());
                        } catch (Exception e) {
                            familyREST.create(getFamilyTreeFamily());
                        }
                    }
                }
                //System.out.println("Family ID: " + getFamilyTreeFamily().getFamilyId());

            }
        }

        /*
         * =====================================================================
         * child
         * =====================================================================
         */
        setPersonNonMember(null);
        //put this class singleton here for no reason.
        classSingleton();
        for (int i = 0; i != getFamilyTreeChildren().size(); i++) {
            if (getFamilyTreeChildren().get(i).getLastName().length() > 0
                    && getFamilyTreeChildren().get(i).getFirstName().length() > 0
                    && getFamilyTreeChildren().get(i).getBirthdate().toString().length() > 0) {
                //this is where you gonna make changes
                cntr = 0;
                pId = "";
                lName = "";
                fName = "";
                bDate = "";
                typeId = "";
                getFamilyTreeChildren().get(i).setPersonId(0);
                if (getFamilyTreeChildren().get(i).getPersonTypeId() == null) {
                    getFamilyTreeChildren().get(i).setPersonTypeId(0);
                }
                if (getFamilyTreeChildren().get(i).getPersonId() == 0 && (getFamilyTreeChildren().get(i).getPersonTypeId() == 3 || getFamilyTreeChildren().get(i).getPersonTypeId() == 0)) {
                    System.out.println("Child" + i + " Non Member");
                    try {
                        //if father is existing in Non Member table
                        setPersonNonMember((CoopPersonNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonNonMember c "
                                + "WHERE LOWER(c.nonMemberId.lastName) = LOWER('" + getFamilyTreeChildren().get(i).getLastName() + "') "
                                + "AND LOWER(c.nonMemberId.firstName) = LOWER('" + getFamilyTreeChildren().get(i).getFirstName() + "') "
                                + "AND c.nonMemberId.birthdate = ?1").setParameter(1, getFamilyTreeChildren().get(i).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                        getFamilyTreeChildren().get(i).setPersonId(getPersonNonMember().getPersonId().getPersonId());
                    } catch (Exception e) {
                        getFamilyTreeChildren().get(i).setPersonId(0);
                    }
                }
                if (getFamilyTreeChildren().get(i).getPersonId() == 0 && (getFamilyTreeChildren().get(i).getPersonTypeId() == 2 || getFamilyTreeChildren().get(i).getPersonTypeId() == 0)) {
                    System.out.println("Spouse" + i + " Associate");
                    try {
                        //if father is existing in Associate table
                        setPersonAssociate((CoopPersonAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonAssociate c "
                                + "WHERE LOWER(c.associateNo.lastName) = LOWER('" + getFamilyTreeChildren().get(i).getLastName() + "') "
                                + "AND LOWER(c.associateNo.firstName) = LOWER('" + getFamilyTreeChildren().get(i).getFirstName() + "') "
                                + "AND c.associateNo.birthdate = ?1").setParameter(1, getFamilyTreeChildren().get(i).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                        getFamilyTreeChildren().get(i).setPersonId(getPersonAssociate().getPersonId().getPersonId());
                    } catch (Exception e) {
                        getFamilyTreeChildren().get(i).setPersonId(0);
                    }
                }
                if (getFamilyTreeChildren().get(i).getPersonId() == 0 && (getFamilyTreeChildren().get(i).getPersonTypeId() == 1 || getFamilyTreeChildren().get(i).getPersonTypeId() == 0)) {
                    System.out.println("Spouse" + i + " Member");
                    try {
                        //if father is existing in Member table
                        setPersonMem((CoopPersonMem) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopPersonMem c "
                                + "WHERE LOWER(c.memNo.lastName) = LOWER('" + getFamilyTreeChildren().get(i).getLastName() + "') "
                                + "AND LOWER(c.memNo.firstName) = LOWER('" + getFamilyTreeChildren().get(i).getFirstName() + "') "
                                + "AND c.memNo.birthdate = ?1").setParameter(1, getFamilyTreeChildren().get(i).getBirthdate(), TemporalType.TIMESTAMP).getResultList().get(0));
                        getFamilyTreeChildren().get(i).setPersonId(getPersonMem().getPersonId().getPersonId());
                    } catch (Exception e) {
                    }
                }

                getPersonNonMember().getNonMemberId().setLastName(getFamilyTreeChildren().get(i).getLastName());
                getPersonNonMember().getNonMemberId().setFirstName(getFamilyTreeChildren().get(i).getFirstName());
                getPersonNonMember().getNonMemberId().setMiddleName(getFamilyTreeChildren().get(i).getMiddleName());
                getPersonNonMember().getNonMemberId().setBirthdate(getFamilyTreeChildren().get(i).getBirthdate());
                getPersonNonMember().getNonMemberId().setGender(getFamilyTreeChildren().get(i).getGender());
                if (getFamilyTreeChildren().get(i).getPersonId() == 0) {
                    getPersonNonMember().getPersonId().setPersonTypeId(personTypeREST.find(3));
                    nonMemberREST.create(getPersonNonMember().getNonMemberId());
                    personREST.create(getPersonNonMember().getPersonId());
                    getFamilyTreeChildren().get(i).setPersonId(getPersonNonMember().getPersonId().getPersonId());
                    personNonMemberREST.create(getPersonNonMember());
                } else {
                    personREST.edit(getPersonNonMember().getPersonId());
                }

                try {
                    for (int x = 0; x != getChildSpouseVal().get(i).length(); x++) {
                        if (getChildSpouseVal().get(i).charAt(x) == ';') {
                            cntr++;
                            x++;
                        }
                        if (cntr == 0) {
                            pId += getChildSpouseVal().get(i).charAt(x);
                        } else if (cntr == 1) {
                            fName += getChildSpouseVal().get(i).charAt(x);
                        } else if (cntr == 2) {
                            lName += getChildSpouseVal().get(i).charAt(x);
                        } else if (cntr == 3) {
                            bDate += getChildSpouseVal().get(i).charAt(x);
                        } else if (cntr == 4) {
                            typeId += getChildSpouseVal().get(i).charAt(x);
                        }
                    }
                } catch (Exception e) {
                }

                if (pId.length() > 0) {
                    if (getFamilyTree().get(2).getGender().equals('M')) {
                        setFamilyTreeFamily((CoopFamily) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopFamily c "
                                + "WHERE c.wife.personId = " + pId + " "
                                + "AND c.husband.personId =" + getFamilyTree().get(2).getPersonId() + "").getResultList().get(0));
                        //+ "AND c.husband.personId = " + familyTree.get(2).getPersonId() + "").setParameter(1, dateStringParse.parse(bDate), TemporalType.TIMESTAMP).getResultList().get(0);
                        if (getChildSpouse().get(i).getFirstName() == null) {
                            getFamilyTreeFamily().setWife(null);
                        }
                    } else {
                        setFamilyTreeFamily((CoopFamily) entityManagerFactory.createEntityManager().createQuery("SELECT c FROM CoopFamily c "
                                + "WHERE c.husband.personId = " + pId + " "
                                + "AND c.wife.personId =" + getFamilyTree().get(2).getPersonId() + "").getResultList().get(0));
                        //+ "AND c.wife.personId = " + familyTree.get(2).getPersonId() + "").setParameter(1, dateStringParse.parse(bDate), TemporalType.TIMESTAMP).getResultList().get(0);
                        if (getChildSpouse().get(i).getFirstName() == null) {
                            getFamilyTreeFamily().setHusband(null);
                        }
                    }
                }

                getFamilyTreeChild().setFamilyId(getFamilyTreeFamily());
                getFamilyTreeChild().setPersonId(personREST.find(getFamilyTreeChildren().get(i).getPersonId()));
                childREST.create(getFamilyTreeChild());
            }
        }
        System.gc();
    }

    public void selectedFamilyLoad() {
        String familyTreeFamilyStr = "SELECT d FROM CoopChild c JOIN c.familyId d "
                + "WHERE c.personId.personId IN (SELECT j.personId.personId ";
        if (!getMode().isEditMode()) {
            loadFather();
            loadMother();
            loadSpouse();
        } else {
            try {
                if (getMembershipType().getTypeValue().equals(0)) {
                    familyTreeFamilyStr += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')";
                } else if (getMembershipType().getTypeValue().equals(1)) {
                    familyTreeFamilyStr += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')";
                }
                setFamilyTreeFamily((CoopFamily) entityManagerFactory.createEntityManager().createQuery(familyTreeFamilyStr).getResultList().get(0));

            } catch (Exception e) {
                loadFather();
                loadMother();
                loadSpouse();
            }
        }
        loadSibling();
        loadChild();
    }

    public void loadFather() {
        //Father
        String fatherStr = "SELECT c.personId FROM CoopPerson c "
                + "WHERE c.personId IN (SELECT n.husband.personId FROM CoopFamily n "
                + "WHERE n.familyId IN (SELECT x.familyId.familyId FROM CoopChild x "
                + "WHERE x.personId.personId IN (SELECT j.personId.personId ";
        setFatherData(null);
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                fatherStr += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')))";
            } else if (getMembershipType().getTypeValue().equals(1)) {
                fatherStr += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')))";
            }
            Integer father = (Integer) entityManagerFactory.createEntityManager().createQuery(fatherStr).getResultList().get(0);

            CoopPerson fatherPerson = personREST.find(father);
            getFatherData().setPersonId(father);
            getFatherData().setPersonType(fatherPerson.getPersonTypeId().getPersonType());
            getFatherData().setPersonTypeId(fatherPerson.getPersonTypeId().getPersonTypeId());
            if (fatherPerson.getPersonTypeId().getPersonTypeId() == 1) {
                CoopMember fatherData = (CoopMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.memNo FROM CoopPersonMem c "
                        + "WHERE c.personId.personId = " + fatherPerson.getPersonId() + "").getResultList().get(0);
                getFatherData().setLastName(fatherData.getLastName());
                getFatherData().setFirstName(fatherData.getFirstName());
                getFatherData().setMiddleName(fatherData.getMiddleName());
                getFatherData().setBirthdate(fatherData.getBirthdate());
                getFatherData().setGender(fatherData.getGender());
                getFatherData().setMemNo(fatherData.getMemNo());
            } else if (fatherPerson.getPersonTypeId().getPersonTypeId() == 2) {
                CoopAssociate fatherData = (CoopAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c.associateNo FROM CoopPersonAssociate c "
                        + "WHERE c.personId.personId = " + fatherPerson.getPersonId() + "").getResultList().get(0);
                getFatherData().setLastName(fatherData.getLastName());
                getFatherData().setFirstName(fatherData.getFirstName());
                getFatherData().setMiddleName(fatherData.getMiddleName());
                getFatherData().setBirthdate(fatherData.getBirthdate());
                getFatherData().setGender(fatherData.getGender());
                getFatherData().setMemNo(fatherData.getMemNo());
            } else if (fatherPerson.getPersonTypeId().getPersonTypeId() == 3) {
                CoopNonMember fatherData = (CoopNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.nonMemberId FROM CoopPersonNonMember c "
                        + "WHERE c.personId.personId = " + fatherPerson.getPersonId() + "").getResultList().get(0);
                getFatherData().setLastName(fatherData.getLastName());
                getFatherData().setFirstName(fatherData.getFirstName());
                getFatherData().setMiddleName(fatherData.getMiddleName());
                getFatherData().setBirthdate(fatherData.getBirthdate());
                getFatherData().setGender(fatherData.getGender());
                getMotherData().setMemNo("");
            }
            getFamilyTree().set(0, getFatherData());
        } catch (Exception e) {
            getFamilyTree().get(0).setPersonId(0);
        }
    }

    public void loadMother() {
        //Mother
        String motherStr = "SELECT c.personId FROM CoopPerson c "
                + "WHERE c.personId IN (SELECT n.wife.personId FROM CoopFamily n "
                + "WHERE n.familyId IN (SELECT x.familyId.familyId FROM CoopChild x "
                + "WHERE x.personId.personId IN (SELECT j.personId.personId ";
        setMotherData(null);
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                motherStr += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')))";
            } else if (getMembershipType().getTypeValue().equals(1)) {
                motherStr += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')))";
            }
            Integer mother = (Integer) entityManagerFactory.createEntityManager().createQuery(motherStr).getResultList().get(0);
            CoopPerson motherPerson = personREST.find(mother);
            getMotherData().setPersonId(mother);
            getMotherData().setPersonType(motherPerson.getPersonTypeId().getPersonType());
            getMotherData().setPersonTypeId(motherPerson.getPersonTypeId().getPersonTypeId());
            if (motherPerson.getPersonTypeId().getPersonTypeId() == 1) {
                CoopMember motherData = (CoopMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.memNo FROM CoopPersonMem c "
                        + "WHERE c.personId.personId = " + motherPerson.getPersonId() + "").getResultList().get(0);
                getMotherData().setLastName(motherData.getLastName());
                getMotherData().setFirstName(motherData.getFirstName());
                getMotherData().setMiddleName(motherData.getMiddleName());
                getMotherData().setBirthdate(motherData.getBirthdate());
                getMotherData().setGender(motherData.getGender());
                getMotherData().setMemNo(motherData.getMemNo());
            } else if (motherPerson.getPersonTypeId().getPersonTypeId() == 2) {
                CoopAssociate motherData = (CoopAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c.associateNo FROM CoopPersonAssociate c "
                        + "WHERE c.personId.personId = " + motherPerson.getPersonId() + "").getResultList().get(0);
                getMotherData().setLastName(motherData.getLastName());
                getMotherData().setFirstName(motherData.getFirstName());
                getMotherData().setMiddleName(motherData.getMiddleName());
                getMotherData().setBirthdate(motherData.getBirthdate());
                getMotherData().setGender(motherData.getGender());
                getMotherData().setMemNo(motherData.getMemNo());
            } else if (motherPerson.getPersonTypeId().getPersonTypeId() == 3) {
                CoopNonMember motherData = (CoopNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.nonMemberId FROM CoopPersonNonMember c "
                        + "WHERE c.personId.personId = " + motherPerson.getPersonId() + "").getResultList().get(0);
                getMotherData().setLastName(motherData.getLastName());
                getMotherData().setFirstName(motherData.getFirstName());
                getMotherData().setMiddleName(motherData.getMiddleName());
                getMotherData().setBirthdate(motherData.getBirthdate());
                getMotherData().setGender(motherData.getGender());
                getMotherData().setMemNo("");
            }
            getFamilyTree().set(1, getMotherData());
        } catch (Exception e) {
            getFamilyTree().get(1).setPersonId(0);
        }
    }

    public void loadSpouse() {
        //Spouse
        String spouseSQL = "";
        try {
            if (getMemberData().getMember().getGender().equals('M')) {
                spouseSQL = "SELECT c FROM CoopPerson c ";
                spouseSQL += "WHERE c.personId IN (SELECT n.wife.personId FROM CoopFamily n ";
                spouseSQL += "WHERE n.familyId IN (SELECT x.familyId FROM CoopFamily x ";
                spouseSQL += "WHERE x.husband.personId IN (SELECT j.personId.personId ";
                if (getMembershipType().getTypeValue().equals(0)) {
                    spouseSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')))";
                } else if (getMembershipType().getTypeValue().equals(1)) {
                    spouseSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')))";
                }
                System.out.print("Reg Male! ");
            } else {
                spouseSQL = "SELECT c FROM CoopPerson c ";
                spouseSQL += "WHERE c.personId IN (SELECT n.husband.personId FROM CoopFamily n ";
                spouseSQL += "WHERE n.familyId IN (SELECT x.familyId FROM CoopFamily x ";
                spouseSQL += "WHERE x.wife.personId IN (SELECT j.personId.personId ";
                if (getMembershipType().getTypeValue().equals(0)) {
                    spouseSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')))";
                } else if (getMembershipType().getTypeValue().equals(1)) {
                    spouseSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')))";
                }
                System.out.print("Reg Female!");
            }
        } catch (Exception e) {
            System.out.print("familyData.loadSpouse()1 " + e);
        }
        try {
            if (getAssociateData().getAssociate().getGender().equals('M')) {
                spouseSQL = "SELECT c FROM CoopPerson c ";
                spouseSQL += "WHERE c.personId IN (SELECT n.wife.personId FROM CoopFamily n ";
                spouseSQL += "WHERE n.familyId IN (SELECT x.familyId FROM CoopFamily x ";
                spouseSQL += "WHERE x.husband.personId IN (SELECT j.personId.personId ";
                if (getMembershipType().getTypeValue().equals(0)) {
                    spouseSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')))";
                } else if (getMembershipType().getTypeValue().equals(1)) {
                    spouseSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')))";
                }
                System.out.print("Assoc Male!");
            } else {
                spouseSQL = "SELECT c FROM CoopPerson c ";
                spouseSQL += "WHERE c.personId IN (SELECT n.husband.personId FROM CoopFamily n ";
                spouseSQL += "WHERE n.familyId IN (SELECT x.familyId FROM CoopFamily x ";
                spouseSQL += "WHERE x.wife.personId IN (SELECT j.personId.personId ";
                if (getMembershipType().getTypeValue().equals(0)) {
                    spouseSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')))";
                } else if (getMembershipType().getTypeValue().equals(1)) {
                    spouseSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')))";
                }
                System.out.print("Assoc Female!");
            }
        } catch (Exception e) {
            System.out.print("familyData.loadSpouse()2 " + e);
        }
        //else
        spouseSQL += "ORDER BY c.personId DESC";
        Query CoopFamilyTreeSpouse = entityManagerFactory.createEntityManager().createQuery(spouseSQL);
        setFamilyTreeList((List<CoopPerson>) CoopFamilyTreeSpouse.getResultList());
        setFamilyTreeModel((DataModel<CoopPerson>) new ListDataModel(getFamilyTreeList()));
        if (getFamilyTreeModel().getRowCount() > 0) {
            for (int x = 0; x != getFamilyTreeModel().getRowCount(); x++) {
                setSpouseData(null);
                getSpouseData().setPersonId(getFamilyTreeList().get(x).getPersonId());
                getSpouseData().setPersonType(getFamilyTreeList().get(x).getPersonTypeId().getPersonType());
                getSpouseData().setPersonTypeId(getFamilyTreeList().get(x).getPersonTypeId().getPersonTypeId());
                if (getFamilyTreeList().get(x).getPersonTypeId().getPersonTypeId() == 1) {
                    CoopMember spouseData = (CoopMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.memNo FROM CoopPersonMem c "
                            + "WHERE c.personId.personId = " + getFamilyTreeList().get(x).getPersonId() + "").getResultList().get(0);
                    getSpouseData().setLastName(spouseData.getLastName());
                    getSpouseData().setFirstName(spouseData.getFirstName());
                    getSpouseData().setMiddleName(spouseData.getMiddleName());
                    getSpouseData().setBirthdate(spouseData.getBirthdate());
                    getSpouseData().setGender(spouseData.getGender());
                    getSpouseData().setMemNo(spouseData.getMemNo());
                } else if (getFamilyTreeList().get(x).getPersonTypeId().getPersonTypeId() == 2) {
                    CoopAssociate spouseData = (CoopAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c.associateNo FROM CoopPersonAssociate c "
                            + "WHERE c.personId.personId = " + getFamilyTreeList().get(x).getPersonId() + "").getResultList().get(0);
                    getSpouseData().setLastName(spouseData.getLastName());
                    getSpouseData().setFirstName(spouseData.getFirstName());
                    getSpouseData().setMiddleName(spouseData.getMiddleName());
                    getSpouseData().setBirthdate(spouseData.getBirthdate());
                    getSpouseData().setGender(spouseData.getGender());
                    getSpouseData().setMemNo(spouseData.getMemNo());
                } else if (getFamilyTreeList().get(x).getPersonTypeId().getPersonTypeId() == 3) {
                    CoopNonMember spouseData = (CoopNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.nonMemberId FROM CoopPersonNonMember c "
                            + "WHERE c.personId.personId = " + getFamilyTreeList().get(x).getPersonId() + "").getResultList().get(0);
                    getSpouseData().setLastName(spouseData.getLastName());
                    getSpouseData().setFirstName(spouseData.getFirstName());
                    getSpouseData().setMiddleName(spouseData.getMiddleName());
                    getSpouseData().setBirthdate(spouseData.getBirthdate());
                    getSpouseData().setGender(spouseData.getGender());
                    getSpouseData().setMemNo("");
                }

                if (x == 0) {
                    getFamilyTreeSpouses().set(0, getSpouseData());
                } else {
                    try {
                        getFamilyTreeSpouses().set(x, getSpouseData());
                    } catch (Exception e) {
                        getFamilyTreeSpouses().add(getSpouseData());
                    }
                }
                try {
                    getFamilyTreeOldSpouses().set(x, getSpouseData());
                } catch (Exception e) {
                    getFamilyTreeOldSpouses().add(getSpouseData());
                }
            }
        }
    }

    public void loadSibling() {
        //Siblings
        String siblingSQL = "SELECT c FROM CoopPerson c ";
        siblingSQL += "WHERE c.personId IN (SELECT n.personId.personId FROM CoopChild n ";
        siblingSQL += "WHERE n.familyId.familyId IN (SELECT x.familyId FROM CoopFamily x ";
        if (getFamilyTree().get(0).getPersonId() > 0 || getFamilyTree().get(1).getPersonId() > 0) {
            siblingSQL += "WHERE ";
        }
        if (getFamilyTree().get(0).getPersonId() > 0) {
            siblingSQL += "x.husband.personId = " + getFamilyTree().get(0).getPersonId() + " ";
        }
        if (getFamilyTree().get(0).getPersonId() > 0 && getFamilyTree().get(1).getPersonId() > 0) {
            siblingSQL += "AND ";
        }
        if (getFamilyTree().get(1).getPersonId() > 0) {
            siblingSQL += "x.wife.personId = " + getFamilyTree().get(1).getPersonId() + " ";
        }
        siblingSQL += ")) ";
        siblingSQL += "AND c.personId NOT IN (SELECT j.personId.personId ";
        if (getMembershipType().getTypeValue().equals(0)) {
            siblingSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')";
        } else if (getMembershipType().getTypeValue().equals(1)) {
            siblingSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')";
        }

        Query CoopFamilyTreeSiblings = entityManagerFactory.createEntityManager().createQuery(siblingSQL);
        CoopChild familyID = null;
        setFamilyTreeList((List<CoopPerson>) CoopFamilyTreeSiblings.getResultList());
        setFamilyTreeModel((DataModel<CoopPerson>) new ListDataModel(getFamilyTreeList()));
        String familyChildStr = "SELECT c FROM CoopChild c "
                + "WHERE c.personId.personId IN (SELECT j.personId.personId ";
        if (getFamilyTreeModel().getRowCount() > 0) {
            try {
                if (getMembershipType().getTypeValue().equals(0)) {
                    familyChildStr += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')";
                } else if (getMembershipType().getTypeValue().equals(1)) {
                    familyChildStr += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')";
                }
                //get familyID of the member
                familyID = (CoopChild) entityManagerFactory.createEntityManager().createQuery(familyChildStr).getResultList().get(0);
            } catch (Exception e) {
            }
            for (int x = 0; x != getFamilyTreeModel().getRowCount(); x++) {
                String familySiblingsStr = "";
                if (getMode().isEditMode()) {
                    familySiblingsStr = "SELECT n FROM CoopChild n "
                            //+ "WHERE n.familyId.familyId = : " + familyID.getFamilyId().getFamilyId() + " ").getResultList().get(0);
                            + "WHERE n.familyId.familyId = " + familyID.getFamilyId().getFamilyId() + " "
                            + "AND n.personId.personId NOT IN (SELECT j.personId.personId ";
                    if (getMembershipType().getTypeValue().equals(0)) {
                        familySiblingsStr += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')";
                    } else if (getMembershipType().getTypeValue().equals(1)) {
                        familySiblingsStr += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')";
                    }

                    try {
                        CoopChild siblings = (CoopChild) entityManagerFactory.createEntityManager().createQuery(familySiblingsStr).getResultList().get(0);
                        //System.out.println("Siblings remove: " + siblings.getPersonId().getPersonId());
                        //childREST.remove(siblings);
                    } catch (Exception e1) {
                        break;
                    }
                } else {
                    familySiblingsStr = "SELECT n.personId FROM CoopChild n "
                            + "WHERE n.personId.personId NOT IN (SELECT x.personId.personId FROM CoopPersonMem x "
                            + "WHERE x.memNo.memNo = '" + getMemberData().getMember().getMemNo() + "') "
                            + "AND n.familyId.familyId = (SELECT c.familyId.familyId FROM CoopChild c "
                            + "WHERE c.personId.personId IN (SELECT j.personId.personId ";
                    if (getMembershipType().getTypeValue().equals(0)) {
                        familySiblingsStr += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "'))";
                    } else if (getMembershipType().getTypeValue().equals(1)) {
                        familySiblingsStr += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "'))";
                    }
                    try {
                        setSiblingsData(null);
                        //select sibling if parents are null
                        CoopPerson siblings = (CoopPerson) entityManagerFactory.createEntityManager().createQuery(familySiblingsStr).getResultList().get(x);
                        getSiblingsData().setPersonId(siblings.getPersonId());
                        getSiblingsData().setPersonType(siblings.getPersonTypeId().getPersonType());
                        getSiblingsData().setPersonTypeId(siblings.getPersonTypeId().getPersonTypeId());
                        if (siblings.getPersonTypeId().getPersonTypeId() == 1) {
                            CoopMember siblingsData = (CoopMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.memNo FROM CoopPersonMem c "
                                    + "WHERE c.personId.personId = " + siblings.getPersonId() + "").getResultList().get(0);
                            getSiblingsData().setLastName(siblingsData.getLastName());
                            getSiblingsData().setFirstName(siblingsData.getFirstName());
                            getSiblingsData().setMiddleName(siblingsData.getMiddleName());
                            getSiblingsData().setBirthdate(siblingsData.getBirthdate());
                            getSiblingsData().setGender(siblingsData.getGender());
                            getSiblingsData().setMemNo(siblingsData.getMemNo());
                        } else if (siblings.getPersonTypeId().getPersonTypeId() == 2) {
                            CoopAssociate siblingsData = (CoopAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c.associateNo FROM CoopPersonAssociate c "
                                    + "WHERE c.personId.personId = " + siblings.getPersonId() + "").getResultList().get(0);
                            getSiblingsData().setLastName(siblingsData.getLastName());
                            getSiblingsData().setFirstName(siblingsData.getFirstName());
                            getSiblingsData().setMiddleName(siblingsData.getMiddleName());
                            getSiblingsData().setBirthdate(siblingsData.getBirthdate());
                            getSiblingsData().setGender(siblingsData.getGender());
                            getSiblingsData().setMemNo(siblingsData.getMemNo());
                        } else if (siblings.getPersonTypeId().getPersonTypeId() == 3) {
                            CoopNonMember siblingsData = (CoopNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.nonMemberId FROM CoopPersonNonMember c "
                                    + "WHERE c.personId.personId = " + siblings.getPersonId() + "").getResultList().get(0);
                            getSiblingsData().setLastName(siblingsData.getLastName());
                            getSiblingsData().setFirstName(siblingsData.getFirstName());
                            getSiblingsData().setMiddleName(siblingsData.getMiddleName());
                            getSiblingsData().setBirthdate(siblingsData.getBirthdate());
                            getSiblingsData().setGender(siblingsData.getGender());
                            getSiblingsData().setMemNo("");
                        }
                        if (x == 0) {
                            getFamilyTreeSiblings().set(x, getSiblingsData());
                        } else {
                            try {
                                getFamilyTreeSiblings().set(x, getSiblingsData());
                            } catch (Exception e) {
                                getFamilyTreeSiblings().add(getSiblingsData());
                            }
                        }
                    } catch (Exception e1) {
                        break;
                    }
                }
            }
        }
    }

    public void loadChild() {
        //Child
        String childSQL = "";
        try {
            if (getMemberData().getMember().getGender().equals('M')) {
                childSQL = "SELECT c FROM CoopPerson c ";
                childSQL += "WHERE c.personId IN (SELECT n.personId.personId FROM CoopChild n ";
                childSQL += "WHERE n.familyId.familyId IN (SELECT d.familyId FROM CoopFamily d ";
                childSQL += "WHERE d.husband.personId IN (SELECT j.personId.personId ";
                if (getMembershipType().getTypeValue().equals(0)) {
                    childSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')))";
                } else if (getMembershipType().getTypeValue().equals(1)) {
                    childSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')))";
                }
            } else {
                childSQL = "SELECT c FROM CoopPerson c ";
                childSQL += "WHERE c.personId IN (SELECT n.personId.personId FROM CoopChild n ";
                childSQL += "WHERE n.familyId.familyId IN (SELECT d.familyId FROM CoopFamily d ";
                childSQL += "WHERE d.wife.personId IN (SELECT j.personId.personId ";
                if (getMembershipType().getTypeValue().equals(0)) {
                    childSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')))";
                } else if (getMembershipType().getTypeValue().equals(1)) {
                    childSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')))";
                }
            }
        } catch (Exception e) {
            System.out.print("familyData.loadChild()1 " + e);
        }
        try {
            if (getAssociateData().getAssociate().getGender().equals('M')) {
                childSQL = "SELECT c FROM CoopPerson c ";
                childSQL += "WHERE c.personId IN (SELECT n.personId.personId FROM CoopChild n ";
                childSQL += "WHERE n.familyId.familyId IN (SELECT d.familyId FROM CoopFamily d ";
                childSQL += "WHERE d.husband.personId IN (SELECT j.personId.personId ";
                if (getMembershipType().getTypeValue().equals(0)) {
                    childSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')))";
                } else if (getMembershipType().getTypeValue().equals(1)) {
                    childSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')))";
                }
            } else {
                childSQL = "SELECT c FROM CoopPerson c ";
                childSQL += "WHERE c.personId IN (SELECT n.personId.personId FROM CoopChild n ";
                childSQL += "WHERE n.familyId.familyId IN (SELECT d.familyId FROM CoopFamily d ";
                childSQL += "WHERE d.wife.personId IN (SELECT j.personId.personId ";
                if (getMembershipType().getTypeValue().equals(0)) {
                    childSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')))";
                } else if (getMembershipType().getTypeValue().equals(1)) {
                    childSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')))";
                }
            }
        } catch (Exception e) {
            System.out.print("familyData.loadChild()2 " + e);
        }
        Query CoopFamilyTreeChildren = entityManagerFactory.createEntityManager().createQuery(childSQL);
        setFamilyTreeList((List<CoopPerson>) CoopFamilyTreeChildren.getResultList());
        setFamilyTreeModel((DataModel<CoopPerson>) new ListDataModel(getFamilyTreeList()));
        if (getFamilyTreeModel().getRowCount() > 0) {
            for (int x = 0; x != getFamilyTreeModel().getRowCount(); x++) {
                setChildData(null);
                if (getMode().isEditMode()) {
                    String ChildchildSQL = "SELECT n FROM CoopChild n ";
                    ChildchildSQL += "WHERE n.familyId.familyId IN (SELECT d.familyId FROM CoopFamily d ";
                    if (getMemberData().getMember().getGender().equals('M')/* || getAssociateData().getAssociate().getGender().equals('M')*/) {
                        ChildchildSQL += "WHERE d.husband.personId IN (SELECT j.personId.personId ";
                        if (getMembershipType().getTypeValue().equals(0)) {
                            ChildchildSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "'))";
                        } else if (getMembershipType().getTypeValue().equals(1)) {
                            ChildchildSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "'))";
                        }
                    } else {
                        ChildchildSQL += "WHERE d.wife.personId IN (SELECT j.personId.personId ";
                        if (getMembershipType().getTypeValue().equals(0)) {
                            ChildchildSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "'))";
                        } else if (getMembershipType().getTypeValue().equals(1)) {
                            ChildchildSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "'))";
                        }
                    }
                    CoopChild children = (CoopChild) entityManagerFactory.createEntityManager().createQuery(ChildchildSQL).getResultList().get(0);
                    //childREST.remove(children);
                } else {
                    CoopPerson children = (CoopPerson) entityManagerFactory.createEntityManager().createQuery(childSQL).getResultList().get(x);

                    String childrenSpouseSQL = "SELECT ";
                    if (getMemberData().getMember().getGender().equals('M')/* || getAssociateData().getAssociate().getGender().equals('M')*/) {
                        childrenSpouseSQL += "c.familyId.wife ";
                    } else {
                        childrenSpouseSQL += "c.familyId.husband ";
                    }
                    childrenSpouseSQL += "FROM CoopChild c ";
                    childrenSpouseSQL += "WHERE c.personId.personId = " + children.getPersonId() + " ";
                    if (getMemberData().getMember().getGender().equals('M')/* || getAssociateData().getAssociate().getGender().equals('M')*/) {
                        childrenSpouseSQL += "AND c.familyId.husband.personId IN (SELECT j.personId.personId ";
                        if (getMembershipType().getTypeValue().equals(0)) {
                            childrenSpouseSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')";
                        } else if (getMembershipType().getTypeValue().equals(1)) {
                            childrenSpouseSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')";
                        }
                    } else {
                        childrenSpouseSQL += "AND c.familyId.wife.personId IN (SELECT j.personId.personId ";
                        if (getMembershipType().getTypeValue().equals(0)) {
                            childrenSpouseSQL += "FROM CoopPersonMem j WHERE j.memNo.memNo ='" + getMemberData().getMember().getMemNo() + "')";
                        } else if (getMembershipType().getTypeValue().equals(1)) {
                            childrenSpouseSQL += "FROM CoopPersonAssociate j WHERE j.associateNo.associateNo ='" + getAssociateData().getAssociate().getAssociateNo() + "')";
                        }
                    }
                    getChildData().setPersonId(children.getPersonId());
                    getChildData().setPersonType(children.getPersonTypeId().getPersonType());
                    getChildData().setPersonTypeId(children.getPersonTypeId().getPersonTypeId());
                    if (children.getPersonTypeId().getPersonTypeId() == 1) {
                        CoopMember ChildrenData = (CoopMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.memNo FROM CoopPersonMem c "
                                + "WHERE c.personId.personId = " + children.getPersonId() + "").getResultList().get(0);
                        getChildData().setLastName(ChildrenData.getLastName());
                        getChildData().setFirstName(ChildrenData.getFirstName());
                        getChildData().setMiddleName(ChildrenData.getMiddleName());
                        getChildData().setBirthdate(ChildrenData.getBirthdate());
                        getChildData().setGender(ChildrenData.getGender());
                        getChildData().setMemNo(ChildrenData.getMemNo());
                    } else if (children.getPersonTypeId().getPersonTypeId() == 2) {
                        CoopAssociate ChildrenData = (CoopAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c.associateNo FROM CoopPersonAssociate c "
                                + "WHERE c.personId.personId = " + children.getPersonId() + "").getResultList().get(0);
                        getChildData().setLastName(ChildrenData.getLastName());
                        getChildData().setFirstName(ChildrenData.getFirstName());
                        getChildData().setMiddleName(ChildrenData.getMiddleName());
                        getChildData().setBirthdate(ChildrenData.getBirthdate());
                        getChildData().setGender(ChildrenData.getGender());
                        getChildData().setMemNo(ChildrenData.getMemNo());
                    } else if (children.getPersonTypeId().getPersonTypeId() == 3) {
                        CoopNonMember ChildrenData = (CoopNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.nonMemberId FROM CoopPersonNonMember c "
                                + "WHERE c.personId.personId = " + children.getPersonId() + "").getResultList().get(0);
                        getChildData().setLastName(ChildrenData.getLastName());
                        getChildData().setFirstName(ChildrenData.getFirstName());
                        getChildData().setMiddleName(ChildrenData.getMiddleName());
                        getChildData().setBirthdate(ChildrenData.getBirthdate());
                        getChildData().setGender(ChildrenData.getGender());
                        getChildData().setMemNo("");
                    }
                    if (x == 0) {
                        getFamilyTreeChildren().set(x, getChildData());
                    } else {
                        try {
                            getFamilyTreeChildren().set(x, getChildData());
                        } catch (Exception e) {
                            getFamilyTreeChildren().add(getChildData());
                        }
                    }

                    setSpouseData(new personData());
                    try {
                        CoopPerson childrenSpouse = (CoopPerson) entityManagerFactory.createEntityManager().createQuery(childrenSpouseSQL).getResultList().get(0);
                        getSpouseData().setPersonId(childrenSpouse.getPersonId());
                        getSpouseData().setPersonType(childrenSpouse.getPersonTypeId().getPersonType());
                        getSpouseData().setPersonTypeId(childrenSpouse.getPersonTypeId().getPersonTypeId());
                        if (childrenSpouse.getPersonTypeId().getPersonTypeId() == 1) {
                            CoopMember ChildrenSpouseData = (CoopMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.memNo FROM CoopPersonMem c "
                                    + "WHERE c.personId.personId = " + childrenSpouse.getPersonId() + "").getResultList().get(0);
                            getSpouseData().setLastName(ChildrenSpouseData.getLastName());
                            getSpouseData().setFirstName(ChildrenSpouseData.getFirstName());
                            getSpouseData().setMiddleName(ChildrenSpouseData.getMiddleName());
                            getSpouseData().setBirthdate(ChildrenSpouseData.getBirthdate());
                            getSpouseData().setGender(ChildrenSpouseData.getGender());
                            getSpouseData().setMemNo(ChildrenSpouseData.getMemNo());
                        } else if (childrenSpouse.getPersonTypeId().getPersonTypeId() == 2) {
                            CoopAssociate ChildrenSpouseData = (CoopAssociate) entityManagerFactory.createEntityManager().createQuery("SELECT c.associateNo FROM CoopPersonAssociate c "
                                    + "WHERE c.personId.personId = " + childrenSpouse.getPersonId() + "").getResultList().get(0);
                            getSpouseData().setLastName(ChildrenSpouseData.getLastName());
                            getSpouseData().setFirstName(ChildrenSpouseData.getFirstName());
                            getSpouseData().setMiddleName(ChildrenSpouseData.getMiddleName());
                            getSpouseData().setBirthdate(ChildrenSpouseData.getBirthdate());
                            getSpouseData().setGender(ChildrenSpouseData.getGender());
                            getSpouseData().setMemNo(ChildrenSpouseData.getMemNo());
                        } else if (childrenSpouse.getPersonTypeId().getPersonTypeId() == 3) {
                            CoopNonMember ChildrenSpouseData = (CoopNonMember) entityManagerFactory.createEntityManager().createQuery("SELECT c.nonMemberId FROM CoopPersonNonMember c "
                                    + "WHERE c.personId.personId = " + childrenSpouse.getPersonId() + "").getResultList().get(0);
                            getSpouseData().setLastName(ChildrenSpouseData.getLastName());
                            getSpouseData().setFirstName(ChildrenSpouseData.getFirstName());
                            getSpouseData().setMiddleName(ChildrenSpouseData.getMiddleName());
                            getSpouseData().setBirthdate(ChildrenSpouseData.getBirthdate());
                            getSpouseData().setGender(ChildrenSpouseData.getGender());
                            getSpouseData().setMemNo("");
                        }
                    } catch (Exception e) {
                    }
                    if (x == 0) {
                        getChildSpouse().set(x, getSpouseData());
                        getChildSpouseVal().set(x, getSpouseData().getPersonId() + ";" + getSpouseData().getFirstName() + ";" + getSpouseData().getLastName() + ";" + getSpouseData().getBirthdate() + ";" + getSpouseData().getPersonTypeId());
                    } else {
                        try {
                            getChildSpouse().set(x, getSpouseData());
                            getChildSpouseVal().set(x, getSpouseData().getPersonId() + ";" + getSpouseData().getFirstName() + ";" + getSpouseData().getLastName() + ";" + getSpouseData().getBirthdate() + ";" + getSpouseData().getPersonTypeId());
                        } catch (Exception e) {
                            getChildSpouse().add(getSpouseData());
                            getChildSpouseVal().add(getSpouseData().getPersonId() + ";" + getSpouseData().getFirstName() + ";" + getSpouseData().getLastName() + ";" + getSpouseData().getBirthdate() + ";" + getSpouseData().getPersonTypeId());
                        }
                    }
                }
            }
        }

        //remove Spouse
        boolean found = false;
        String spouseSql = "";
        if (getMode().isEditMode()) {
            for (int i = 0; i != getFamilyTreeOldSpouses().size(); i++) {
                spouseSql = "SELECT c FROM CoopFamily c ";
                for (int x = 0; x != getFamilyTreeSpouses().size(); x++) {
                    found = false;
                    if (getFamilyTreeSpouses().get(x).getPersonId() != null) {
                        if (Objects.equals(getFamilyTreeSpouses().get(x).getPersonId(), getFamilyTreeOldSpouses().get(i).getPersonId())
                                && Objects.equals(getFamilyTreeSpouses().get(x).getLastName(), getFamilyTreeOldSpouses().get(i).getLastName())
                                && Objects.equals(getFamilyTreeSpouses().get(x).getFirstName(), getFamilyTreeOldSpouses().get(i).getFirstName())
                                && Objects.equals(getFamilyTreeSpouses().get(x).getBirthdate(), getFamilyTreeOldSpouses().get(i).getBirthdate())) {

                            System.out.println(i + " SpouseOld: " + getFamilyTreeOldSpouses().get(i).getLastName()
                                    + ", " + getFamilyTreeOldSpouses().get(i).getFirstName() + " "
                                    + getFamilyTreeOldSpouses().get(i).getMiddleName() + " ; "
                                    + getFamilyTreeOldSpouses().get(i).getPersonId());
                            System.out.println(x + " Spouse: " + getFamilyTreeSpouses().get(x).getLastName()
                                    + ", " + getFamilyTreeSpouses().get(x).getFirstName() + " "
                                    + getFamilyTreeOldSpouses().get(i).getMiddleName() + " ; "
                                    + getFamilyTreeOldSpouses().get(i).getPersonId());

                            found = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }

                if (!found) {
                    if (getFamilyTree().get(2).getGender().equals('M')) {
                        spouseSql += "WHERE c.wife.personId =" + getFamilyTreeOldSpouses().get(i).getPersonId() + " ";
                        spouseSql += "AND c.husband.personId =" + getFamilyTree().get(2).getPersonId() + " ";
                    } else {
                        spouseSql += "WHERE c.husband.personId =" + getFamilyTreeOldSpouses().get(i).getPersonId() + " ";
                        spouseSql += "AND c.wife.personId =" + getFamilyTree().get(2).getPersonId() + " ";
                    }
                    CoopFamily familySpouse = (CoopFamily) entityManagerFactory.createEntityManager().createQuery(spouseSql).getResultList().get(0);
                    //familyREST.remove(familySpouse);
                    System.out.println("Spouse removed: " + getFamilyTreeOldSpouses().get(i).getLastName() + " ; " + getFamilyTreeOldSpouses().get(i).getPersonId());
                    System.out.println("Family ID: " + familySpouse.getFamilyId());
                }
            }
        }
    }

}
