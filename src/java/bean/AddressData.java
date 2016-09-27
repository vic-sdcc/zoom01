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
import model.CoopAddlAddress;
import model.CoopAddlAddressAssoc;
import model.CoopAddlAddressMem;
import service.CoopAddlAddressFacadeREST;
import service.CoopAddlAddressAssocFacadeREST;
import service.CoopAddlAddressMemFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class AddressData implements Serializable {

    /**
     * Creates a new instance of AddressData
     */
    public AddressData() {
        System.out.print("AddressData constructed");
        newSet();
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    @EJB
    private CoopAddlAddressFacadeREST addlAddressREST;
    private CoopAddlAddress addlAddress;
    private ArrayList<CoopAddlAddress> addlAddressAL;
    private List<CoopAddlAddress> addlAddressList;
    private DataModel<CoopAddlAddress> addlAddressModel;
    @EJB
    private CoopAddlAddressAssocFacadeREST addlAddressAssocREST;
    private CoopAddlAddressAssoc addlAddressAssoc;
    private ArrayList<CoopAddlAddressAssoc> addlAddressAssocAL;
    @EJB
    private CoopAddlAddressMemFacadeREST addlAddressMemREST;
    private CoopAddlAddressMem addlAddressMem;
    private ArrayList<CoopAddlAddressMem> addlAddressMemAL;
    @ManagedProperty(value = "#{memberData}")
    private MemberData memberData;
    @ManagedProperty(value = "#{associateData}")
    private AssociateData associateData;
    @ManagedProperty(value = "#{membershipType}")
    private MembershipType membershipType;
    @ManagedProperty(value = "#{mode}")
    private Mode mode;
    @ManagedProperty(value = "#{panelToggle}")
    private PanelToggle panelToggle;

    /*
     * getter setter
     */
    public CoopAddlAddress getAddlAddress() {
        if (addlAddress == null) {
            addlAddress = new CoopAddlAddress();
        }
        return addlAddress;
    }

    public void setAddlAddress(CoopAddlAddress addlAddress) {
        this.addlAddress = addlAddress;
    }

    public ArrayList<CoopAddlAddress> getAddlAddressAL() {
        if (addlAddressAL == null) {
            addlAddressAL = new ArrayList<>();
        }
        return addlAddressAL;
    }

    public void setAddlAddressAL(ArrayList<CoopAddlAddress> addlAddressAL) {
        this.addlAddressAL = addlAddressAL;
    }

    public List<CoopAddlAddress> getAddlAddressList() {
        return addlAddressList;
    }

    public void setAddlAddressList(List<CoopAddlAddress> addlAddressList) {
        this.addlAddressList = addlAddressList;
    }

    public DataModel<CoopAddlAddress> getAddlAddressModel() {
        if (addlAddressModel == null) {
            addlAddressModel = new ListDataModel<>(getAddlAddressList());
        }
        return addlAddressModel;
    }

    public void setAddlAddressModel(DataModel<CoopAddlAddress> addlAddressModel) {
        this.addlAddressModel = addlAddressModel;
    }

    public CoopAddlAddressAssoc getAddlAddressAssoc() {
        if (addlAddressAssoc == null) {
            addlAddressAssoc = new CoopAddlAddressAssoc();
        }
        return addlAddressAssoc;
    }

    public void setAddlAddressAssoc(CoopAddlAddressAssoc addlAddressAssoc) {
        this.addlAddressAssoc = addlAddressAssoc;
    }

    public ArrayList<CoopAddlAddressAssoc> getAddlAddressAssocAL() {
        if (addlAddressAssocAL == null) {
            addlAddressAssocAL = new ArrayList<>();
        }
        return addlAddressAssocAL;
    }

    public void setAddlAddressAssocAL(ArrayList<CoopAddlAddressAssoc> addlAddressAssocAL) {
        this.addlAddressAssocAL = addlAddressAssocAL;
    }

    public CoopAddlAddressMem getAddlAddressMem() {
        if (addlAddressMem == null) {
            addlAddressMem = new CoopAddlAddressMem();
        }
        return addlAddressMem;
    }

    public void setAddlAddressMem(CoopAddlAddressMem addlAddressMem) {
        this.addlAddressMem = addlAddressMem;
    }

    public ArrayList<CoopAddlAddressMem> getAddlAddressMemAL() {
        if (addlAddressMemAL == null) {
            addlAddressMemAL = new ArrayList<>();
        }
        return addlAddressMemAL;
    }

    public void setAddlAddressMemAL(ArrayList<CoopAddlAddressMem> addlAddressMemAL) {
        this.addlAddressMemAL = addlAddressMemAL;
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

    public PanelToggle getPanelToggle() {
        if (panelToggle == null) {
            panelToggle = new PanelToggle();
        }
        return panelToggle;
    }

    public void setPanelToggle(PanelToggle panelToggle) {
        this.panelToggle = panelToggle;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("addressData", null);
    }

    public void clearAL() {
        getAddlAddressAL().clear();
        getAddlAddressAssocAL().clear();
        getAddlAddressMemAL().clear();
    }

    public void setAL() {
        getAddlAddressAL();
        getAddlAddressAssocAL();
        getAddlAddressMemAL();
    }

    public void newSet() {
        getPanelToggle().setAdd2(false);
        getPanelToggle().setAdd3(false);
        getPanelToggle().setAdd4(false);
        getAddlAddressAL().add(new CoopAddlAddress());
        getAddlAddressAL().add(new CoopAddlAddress());
        getAddlAddressAL().add(new CoopAddlAddress());
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                //getAddlAddressMemAL().add(new CoopAddlAddressMem());
                //getAddlAddressMemAL().add(new CoopAddlAddressMem());
                //getAddlAddressMemAL().add(new CoopAddlAddressMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getAddlAddressAssocAL().add(new CoopAddlAddressAssoc());
                getAddlAddressAssocAL().add(new CoopAddlAddressAssoc());
                getAddlAddressAssocAL().add(new CoopAddlAddressAssoc());
            }
        } catch (Exception e) {
            System.out.print("AddressData.java newSet() " + e);
        }
    }

    public void myLoop(int i) {
        getAddlAddress().setStreet(getAddlAddressAL().get(i).getStreet());
        getAddlAddress().setBarangay(getAddlAddressAL().get(i).getBarangay());
        getAddlAddress().setCityMun(getAddlAddressAL().get(i).getCityMun());
        getAddlAddress().setProvince(getAddlAddressAL().get(i).getProvince());
        getAddlAddress().setRegion(getAddlAddressAL().get(i).getRegion());
        getAddlAddress().setZipcode(getAddlAddressAL().get(i).getZipcode());
        addlAddressREST.create(getAddlAddress());
        if (getMembershipType().getTypeValue().equals(0)) {
            getAddlAddressMem().setMemNo(getMemberData().getMember());
            getAddlAddressMem().setAddlAddressNum(getAddlAddress());
            addlAddressMemREST.create(getAddlAddressMem());
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getAddlAddressAssoc().setAssociateNo(getAssociateData().getAssociate());
            getAddlAddressAssoc().setAddlAddressNum(getAddlAddress());
            addlAddressAssocREST.create(getAddlAddressAssoc());
        }
    }

    public void addressLoad() {
        int x;
        if (getMode().isEditMode()) {
            selectedAddressLoad();
        }
        if (getPanelToggle().isAdd2()) {
            x = 0;
            getAddlAddress().setAddressType("ALTERNATE");
            myLoop(x);
        }
        if (getPanelToggle().isAdd3()) {
            x = 1;
            getAddlAddress().setAddressType("PREVIOUS");
            myLoop(x);
        }
        if (getPanelToggle().isAdd4()) {
            x = 2;
            getAddlAddress().setAddressType("PROVINCIAL");
            myLoop(x);
        }
    }

    public void selectedAddressLoad() {
        String t1 = "", t2 = "", endString = "";
        CoopAddlAddress am = null;
        CoopAddlAddressMem m = null;
        CoopAddlAddressAssoc a = null;
        Query CoopAddlAddressM = null, CoopAddlAddressA = null;
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                endString = "JOIN t2.addlAddressNum t1 "
                        + "JOIN t2.memNo t3 "
                        + "WHERE t3.memNo = '" + getMemberData().getMember().getMemNo() + "'";
                t1 = "SELECT t1 FROM CoopAddlAddressMem t2 " + endString;
                t2 = "SELECT t2 FROM CoopAddlAddressMem t2 " + endString;
                CoopAddlAddressM = entityManagerFactory.createEntityManager().createQuery(t1);
                setAddlAddressList(CoopAddlAddressM.getResultList());
                if (getAddlAddressList().size() > 0) {
                    for (int i = 0; i != getAddlAddressList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopAddlAddress) CoopAddlAddressM.getResultList().get(0);
                            m = (CoopAddlAddressMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            addlAddressMemREST.remove(m);
                            addlAddressREST.remove(am);
                        } else {
                            am = (CoopAddlAddress) CoopAddlAddressM.getResultList().get(i);
                            //m = (CoopAddlAddressMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (null != am.getAddressType()) {
                                switch (am.getAddressType()) {
                                    case "ALTERNATE":
                                        getPanelToggle().setAdd2(true);
                                        //getAddlAddressMemAL().set(0, m);
                                        getAddlAddressAL().set(0, am);
                                        break;
                                    case "PREVIOUS":
                                        getPanelToggle().setAdd3(true);
                                        //getAddlAddressMemAL().set(1, m);
                                        getAddlAddressAL().set(1, am);
                                        break;
                                    case "PROVINCIAL":
                                        getPanelToggle().setAdd4(true);
                                        //getAddlAddressMemAL().set(2, m);
                                        getAddlAddressAL().set(2, am);
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("AddressData.java selectedAddressLoad()-1 " + e);
        }
        try {
            if (getMembershipType().getTypeValue().equals(1)) {
                endString = "JOIN t2.addlAddressNum t1 "
                        + "JOIN t2.associateNo t3 "
                        + "WHERE t3.associateNo = '" + getAssociateData().getAssociate().getAssociateNo() + "'";
                t1 = "SELECT t1 FROM CoopAddlAddressAssoc t2 " + endString;
                t2 = "SELECT t2 FROM CoopAddlAddressAssoc t2 " + endString;
                CoopAddlAddressA = entityManagerFactory.createEntityManager().createQuery(t1);
                setAddlAddressList(CoopAddlAddressA.getResultList());
                if (getAddlAddressList().size() > 0) {
                    for (int i = 0; i != getAddlAddressList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopAddlAddress) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            a = (CoopAddlAddressAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            addlAddressAssocREST.remove(a);
                            addlAddressREST.remove(am);
                        } else {
                            am = (CoopAddlAddress) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            a = (CoopAddlAddressAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (null != am.getAddressType()) {
                                switch (am.getAddressType()) {
                                    case "ALTERNATE":
                                        getPanelToggle().setAdd2(true);
                                        //getAddlAddressAssocAL().set(0, a);
                                        getAddlAddressAL().set(0, am);
                                        break;
                                    case "PREVIOUS":
                                        getPanelToggle().setAdd3(true);
                                        getAddlAddressAssocAL().set(1, a);
                                        getAddlAddressAL().set(1, am);
                                        break;
                                    case "PROVINCIAL":
                                        getPanelToggle().setAdd4(true);
                                        getAddlAddressAssocAL().set(2, a);
                                        getAddlAddressAL().set(2, am);
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("AddressData.java selectedAddressLoad()-2 " + e);
        }
    }

}
