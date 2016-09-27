/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import model.CoopAddlContactInfo;
import model.CoopAddlContactInfoAssoc;
import model.CoopAddlContactInfoMem;
import service.CoopAddlContactInfoFacadeREST;
import service.CoopAddlContactInfoAssocFacadeREST;
import service.CoopAddlContactInfoMemFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class ContactsData implements Serializable {

    /**
     * Creates a new instance of ContactsData
     */
    public ContactsData() {
        System.out.print("ContactsData constructed");
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    @EJB
    private CoopAddlContactInfoFacadeREST addlContactInfoREST;
    private CoopAddlContactInfo addlContactInfo;
    private ArrayList<CoopAddlContactInfo> addlContactInfoAL;
    private List<CoopAddlContactInfo> addlContactInfoList;
    private DataModel<CoopAddlContactInfo> addlContactInfoModel;
    @EJB
    private CoopAddlContactInfoAssocFacadeREST addlContactInfoAssocREST;
    private CoopAddlContactInfoAssoc addlContactInfoAssoc;
    private ArrayList<CoopAddlContactInfoAssoc> addlContactInfoAssocAL;
    @EJB
    private CoopAddlContactInfoMemFacadeREST addlContactInfoMemREST;
    private CoopAddlContactInfoMem addlContactInfoMem;
    private ArrayList<CoopAddlContactInfoMem> addlContactInfoMemAL;
    @ManagedProperty(value = "#{memberData}")
    private MemberData memberData;
    @ManagedProperty(value = "#{associateData}")
    private AssociateData associateData;
    @ManagedProperty(value = "#{membershipType}")
    private MembershipType membershipType;
    @ManagedProperty(value = "#{mode}")
    private Mode mode;

    /*
     * getter setter
     */
    public CoopAddlContactInfo getAddlContactInfo() {
        if (addlContactInfo == null) {
            addlContactInfo = new CoopAddlContactInfo();
        }
        return addlContactInfo;
    }

    public void setAddlContactInfo(CoopAddlContactInfo addlContactInfo) {
        this.addlContactInfo = addlContactInfo;
    }

    public ArrayList<CoopAddlContactInfo> getAddlContactInfoAL() {
        if (addlContactInfoAL == null) {
            addlContactInfoAL = new ArrayList<>();
        }
        return addlContactInfoAL;
    }

    public void setAddlContactInfoAL(ArrayList<CoopAddlContactInfo> addlContactInfoAL) {
        this.addlContactInfoAL = addlContactInfoAL;
    }

    public List<CoopAddlContactInfo> getAddlContactInfoList() {
        return addlContactInfoList;
    }

    public void setAddlContactInfoList(List<CoopAddlContactInfo> addlContactInfoList) {
        this.addlContactInfoList = addlContactInfoList;
    }

    public DataModel<CoopAddlContactInfo> getAddlContactInfoModel() {
        if (addlContactInfoModel == null) {
            addlContactInfoModel = new ListDataModel<>(getAddlContactInfoList());
        }
        return addlContactInfoModel;
    }

    public void setAddlContactInfoModel(DataModel<CoopAddlContactInfo> addlContactInfoModel) {
        this.addlContactInfoModel = addlContactInfoModel;
    }

    public CoopAddlContactInfoAssoc getAddlContactInfoAssoc() {
        if (addlContactInfoAssoc == null) {
            addlContactInfoAssoc = new CoopAddlContactInfoAssoc();
        }
        return addlContactInfoAssoc;
    }

    public void setAddlContactInfoAssoc(CoopAddlContactInfoAssoc addlContactInfoAssoc) {
        this.addlContactInfoAssoc = addlContactInfoAssoc;
    }

    public ArrayList<CoopAddlContactInfoAssoc> getAddlContactInfoAssocAL() {
        if (addlContactInfoAssocAL == null) {
            addlContactInfoAssocAL = new ArrayList<>();
        }
        return addlContactInfoAssocAL;
    }

    public void setAddlContactInfoAssocAL(ArrayList<CoopAddlContactInfoAssoc> addlContactInfoAssocAL) {
        this.addlContactInfoAssocAL = addlContactInfoAssocAL;
    }

    public CoopAddlContactInfoMem getAddlContactInfoMem() {
        if (addlContactInfoMem == null) {
            addlContactInfoMem = new CoopAddlContactInfoMem();
        }
        return addlContactInfoMem;
    }

    public void setAddlContactInfoMem(CoopAddlContactInfoMem addlContactInfoMem) {
        this.addlContactInfoMem = addlContactInfoMem;
    }

    public ArrayList<CoopAddlContactInfoMem> getAddlContactInfoMemAL() {
        if (addlContactInfoMemAL == null) {
            addlContactInfoMemAL = new ArrayList<>();
        }
        return addlContactInfoMemAL;
    }

    public void setAddlContactInfoMemAL(ArrayList<CoopAddlContactInfoMem> addlContactInfoMemAL) {
        this.addlContactInfoMemAL = addlContactInfoMemAL;
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

    public Mode getMode() {
        if (mode == null) {
            mode = new Mode();
        }
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("contactsData", null);
    }

    public void clearAL() {
        getAddlContactInfoAL().clear();
        getAddlContactInfoAssocAL().clear();
        getAddlContactInfoMemAL().clear();
    }

    public void setAL() {
        getAddlContactInfoAL();
        getAddlContactInfoAssocAL();
        getAddlContactInfoMemAL();
    }

    public void newSet() {
        getAddlContactInfoAL().add(new CoopAddlContactInfo());
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                getAddlContactInfoMemAL().add(new CoopAddlContactInfoMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getAddlContactInfoAssocAL().add(new CoopAddlContactInfoAssoc());
            }
        } catch (Exception e) {
            System.out.print("ContactsData.java newSet() " + e);
        }
    }

    public void addContact() {
        if (getAddlContactInfoAL().size() < 4) {
            newSet();
        }
    }

    public void removeContact(int value) {
        getAddlContactInfoAL().remove(value);
        if (getMembershipType().getTypeValue().equals(0)) {
            getAddlContactInfoMemAL().remove(value);
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getAddlContactInfoAssocAL().remove(value);
        }
    }

    public void removeLoad() {
        try {
            if (getAddlContactInfoAL().get(0).getContactDetail() == null) {
                removeContact(0);
            }
        } catch (Exception e) {
            System.out.print("ContactsData.java removeLoad() " + e);
        }
    }

    public void contactsLoad() {
        if (getMode().isEditMode()) {
            selectedContactsLoad();
        }
        for (int i = 0; i != getAddlContactInfoAL().size(); i++) {
            getAddlContactInfo().setContactType(getAddlContactInfoAL().get(i).getContactType());
            getAddlContactInfo().setContactDetail(getAddlContactInfoAL().get(i).getContactDetail());
            addlContactInfoREST.create(getAddlContactInfo());
            if (getMembershipType().getTypeValue().equals(0)) {
                getAddlContactInfoMem().setMemNo(getMemberData().getMember());
                getAddlContactInfoMem().setContactInfoNum(getAddlContactInfo());
                addlContactInfoMemREST.create(getAddlContactInfoMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getAddlContactInfoAssoc().setAssociateNo(getAssociateData().getAssociate());
                getAddlContactInfoAssoc().setContactInfoNum(getAddlContactInfo());
                addlContactInfoAssocREST.create(getAddlContactInfoAssoc());
            }
        }
    }

    public void selectedContactsLoad() {
        String t1 = "", t2 = "", endString = "";
        CoopAddlContactInfo am = null;
        CoopAddlContactInfoMem m = null;
        CoopAddlContactInfoAssoc a = null;
        Query CoopAddlContactInfoM = null, CoopAddlContactInfoA = null;
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                endString = "JOIN t2.contactInfoNum t1 "
                        + "JOIN t2.memNo t3 "
                        + "WHERE t3.memNo = '" + getMemberData().getMember().getMemNo() + "'";
                t1 = "SELECT t1 FROM CoopAddlContactInfoMem t2 " + endString;
                t2 = "SELECT t2 FROM CoopAddlContactInfoMem t2 " + endString;
                CoopAddlContactInfoM = entityManagerFactory.createEntityManager().createQuery(t1);
                setAddlContactInfoList(CoopAddlContactInfoM.getResultList());
                if (getAddlContactInfoList().size() > 0) {
                    for (int i = 0; i != getAddlContactInfoList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopAddlContactInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            m = (CoopAddlContactInfoMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            addlContactInfoMemREST.remove(m);
                            addlContactInfoREST.remove(am);
                        } else {
                            am = (CoopAddlContactInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            m = (CoopAddlContactInfoMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (i == 0) {
                                getAddlContactInfoMemAL().set(i, m);
                                getAddlContactInfoAL().set(i, am);
                            } else {
                                getAddlContactInfoMemAL().add(m);
                                getAddlContactInfoAL().add(am);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("ContactsData.java selectedContactsLoad()-1 " + e);
        }
        try {
            if (getMembershipType().getTypeValue().equals(1)) {
                endString = "JOIN t2.contactInfoNum t1 "
                        + "JOIN t2.associateNo t3 "
                        + "WHERE t3.associateNo = '" + getAssociateData().getAssociate().getAssociateNo() + "'";
                t1 = "SELECT t1 FROM CoopAddlContactInfoAssoc t2 " + endString;
                t2 = "SELECT t2 FROM CoopAddlContactInfoAssoc t2 " + endString;
                CoopAddlContactInfoA = entityManagerFactory.createEntityManager().createQuery(t1);
                setAddlContactInfoList(CoopAddlContactInfoA.getResultList());
                if (getAddlContactInfoList().size() > 0) {
                    for (int i = 0; i != getAddlContactInfoList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopAddlContactInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            a = (CoopAddlContactInfoAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            addlContactInfoAssocREST.remove(a);
                            addlContactInfoREST.remove(am);
                        } else {
                            am = (CoopAddlContactInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            a = (CoopAddlContactInfoAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (i == 0) {
                                getAddlContactInfoAssocAL().set(i, a);
                                getAddlContactInfoAL().set(i, am);
                            } else {
                                getAddlContactInfoAssocAL().add(a);
                                getAddlContactInfoAL().add(am);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("ContactsData.java selectedContactsLoad()-2 " + e);
        }
        removeLoad();
    }

}
