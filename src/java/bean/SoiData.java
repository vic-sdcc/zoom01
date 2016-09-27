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
import model.CoopOtherSourceOfIncome;
import model.CoopOtherSourceOfIncomeAssoc;
import model.CoopOtherSourceOfIncomeMem;
import service.CoopOtherSourceOfIncomeAssocFacadeREST;
import service.CoopOtherSourceOfIncomeFacadeREST;
import service.CoopOtherSourceOfIncomeMemFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class SoiData implements Serializable {

    /**
     * Creates a new instance of SoiData
     */
    public SoiData() {
        System.out.print("SoiData constructed");
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    @EJB
    private CoopOtherSourceOfIncomeFacadeREST soiREST;
    private CoopOtherSourceOfIncome soi;
    private ArrayList<CoopOtherSourceOfIncome> soiAL;
    private List<CoopOtherSourceOfIncome> soiList;
    private DataModel<CoopOtherSourceOfIncome> soiModel;
    @EJB
    private CoopOtherSourceOfIncomeAssocFacadeREST soiAssocREST;
    private CoopOtherSourceOfIncomeAssoc soiAssoc;
    private ArrayList<CoopOtherSourceOfIncomeAssoc> soiAssocAL;
    @EJB
    private CoopOtherSourceOfIncomeMemFacadeREST soiMemREST;
    private CoopOtherSourceOfIncomeMem soiMem;
    private ArrayList<CoopOtherSourceOfIncomeMem> soiMemAL;
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
    public CoopOtherSourceOfIncome getSoi() {
        if (soi == null) {
            soi = new CoopOtherSourceOfIncome();
        }
        return soi;
    }

    public void setSoi(CoopOtherSourceOfIncome soi) {
        this.soi = soi;
    }

    public ArrayList<CoopOtherSourceOfIncome> getSoiAL() {
        if (soiAL == null) {
            soiAL = new ArrayList<>();
        }
        return soiAL;
    }

    public void setSoiAL(ArrayList<CoopOtherSourceOfIncome> soiAL) {
        this.soiAL = soiAL;
    }

    public List<CoopOtherSourceOfIncome> getSoiList() {
        return soiList;
    }

    public void setSoiList(List<CoopOtherSourceOfIncome> soiList) {
        this.soiList = soiList;
    }

    public DataModel<CoopOtherSourceOfIncome> getSoiModel() {
        if (soiModel == null) {
            soiModel = new ListDataModel<>(getSoiList());
        }
        return soiModel;
    }

    public void setSoiModel(DataModel<CoopOtherSourceOfIncome> soiModel) {
        this.soiModel = soiModel;
    }

    public CoopOtherSourceOfIncomeAssoc getSoiAssoc() {
        if (soiAssoc == null) {
            soiAssoc = new CoopOtherSourceOfIncomeAssoc();
        }
        return soiAssoc;
    }

    public void setSoiAssoc(CoopOtherSourceOfIncomeAssoc soiAssoc) {
        this.soiAssoc = soiAssoc;
    }

    public ArrayList<CoopOtherSourceOfIncomeAssoc> getSoiAssocAL() {
        if (soiAssocAL == null) {
            soiAssocAL = new ArrayList<>();
        }
        return soiAssocAL;
    }

    public void setSoiAssocAL(ArrayList<CoopOtherSourceOfIncomeAssoc> soiAssocAL) {
        this.soiAssocAL = soiAssocAL;
    }

    public CoopOtherSourceOfIncomeMem getSoiMem() {
        if (soiMem == null) {
            soiMem = new CoopOtherSourceOfIncomeMem();
        }
        return soiMem;
    }

    public void setSoiMem(CoopOtherSourceOfIncomeMem soiMem) {
        this.soiMem = soiMem;
    }

    public ArrayList<CoopOtherSourceOfIncomeMem> getSoiMemAL() {
        if (soiMemAL == null) {
            soiMemAL = new ArrayList<>();
        }
        return soiMemAL;
    }

    public void setSoiMemAL(ArrayList<CoopOtherSourceOfIncomeMem> soiMemAL) {
        this.soiMemAL = soiMemAL;
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("soiData", null);
    }

    public void clearAL() {
        getSoiAL().clear();
        getSoiAssocAL().clear();
        getSoiMemAL().clear();
    }

    public void setAL() {
        getSoiAL();
        getSoiAssocAL();
        getSoiMemAL();
    }

    public void newSet() {
        getSoiAL().add(new CoopOtherSourceOfIncome());
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                getSoiMemAL().add(new CoopOtherSourceOfIncomeMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getSoiAssocAL().add(new CoopOtherSourceOfIncomeAssoc());
            }
        } catch (Exception e) {
            System.out.print("SoiData.java newSet() " + e);
        }
    }

    public void addSoi() {
        if (getSoiAL().size() < 5) {
            newSet();
        }
    }

    public void removeSoi(int value) {
        getSoiAL().remove(value);
        if (getMembershipType().getTypeValue().equals(0)) {
            getSoiMemAL().remove(value);
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getSoiAssocAL().remove(value);
        }
    }
    
    public void removeLoad() {
        try {
            if (getSoiAL().get(0).getSoiType() == null) {
                removeSoi(0);
            }
        } catch (Exception e) {
            System.out.print("SoiData.java removeLoad() " + e);
        }
    }

    public void soiLoad() {
        if (getMode().isEditMode()) {
            selectedSoiLoad();
        }
        for (int i = 0; i != getSoiAL().size(); i++) {
            getSoi().setSoiType(getSoiAL().get(i).getSoiType());
            getSoi().setRegularity(getSoiAL().get(i).getRegularity());
            getSoi().setSource(getSoiAL().get(i).getSource());
            getSoi().setAmount(getSoiAL().get(i).getAmount());
            soiREST.create(getSoi());
            if (getMembershipType().getTypeValue().equals(0)) {
                getSoiMem().setMemNo(getMemberData().getMember());
                getSoiMem().setOtherSoiNo(getSoi());
                soiMemREST.create(getSoiMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getSoiAssoc().setAssociateNo(getAssociateData().getAssociate());
                getSoiAssoc().setOtherSoiNo(getSoi());
                soiAssocREST.create(getSoiAssoc());
            }
        }
    }

    public void selectedSoiLoad() {
        String t1 = "", t2 = "", endString = "";
        CoopOtherSourceOfIncome am = null;
        CoopOtherSourceOfIncomeMem m = null;
        CoopOtherSourceOfIncomeAssoc a = null;
        Query CoopOtherSourceOfIncomeM = null, CoopOtherSourceOfIncomeA = null;
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                endString = "JOIN t2.otherSoiNo t1 "
                        + "JOIN t2.memNo t3 "
                        + "WHERE t3.memNo = '" + getMemberData().getMember().getMemNo() + "'";
                t1 = "SELECT t1 FROM CoopOtherSourceOfIncomeMem t2 " + endString;
                t2 = "SELECT t2 FROM CoopOtherSourceOfIncomeMem t2 " + endString;
                CoopOtherSourceOfIncomeM = entityManagerFactory.createEntityManager().createQuery(t1);
                setSoiList(CoopOtherSourceOfIncomeM.getResultList());
                if (getSoiList().size() > 0) {
                    for (int i = 0; i != getSoiList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopOtherSourceOfIncome) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            m = (CoopOtherSourceOfIncomeMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            soiMemREST.remove(m);
                            soiREST.remove(am);
                        } else {
                            am = (CoopOtherSourceOfIncome) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            m = (CoopOtherSourceOfIncomeMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (i == 0) {
                                getSoiMemAL().set(i, m);
                                getSoiAL().set(i, am);
                            } else {
                                getSoiMemAL().add(m);
                                getSoiAL().add(am);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("SoiData.java selectedSoiLoad() " + e);
        }
        try {
            if (getMembershipType().getTypeValue().equals(1)) {
                endString = "JOIN t2.otherSoiNo t1 "
                        + "JOIN t2.associateNo t3 "
                        + "WHERE t3.associateNo = '" + getAssociateData().getAssociate().getAssociateNo() + "'";
                t1 = "SELECT t1 FROM CoopOtherSourceOfIncomeAssoc t2 " + endString;
                t2 = "SELECT t2 FROM CoopOtherSourceOfIncomeAssoc t2 " + endString;
                CoopOtherSourceOfIncomeA = entityManagerFactory.createEntityManager().createQuery(t1);
                setSoiList(CoopOtherSourceOfIncomeA.getResultList());
                if (getSoiList().size() > 0) {
                    for (int i = 0; i != getSoiList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopOtherSourceOfIncome) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            a = (CoopOtherSourceOfIncomeAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            soiAssocREST.remove(a);
                            soiREST.remove(am);
                        } else {
                            am = (CoopOtherSourceOfIncome) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            a = (CoopOtherSourceOfIncomeAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (i == 0) {
                                getSoiAssocAL().set(i, a);
                                getSoiAL().set(i, am);
                            } else {
                                getSoiAssocAL().add(a);
                                getSoiAL().add(am);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("SoiData.java selectedSoiLoad()-2 " + e);
        }
        removeLoad();
    }

}
