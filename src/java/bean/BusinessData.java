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
import model.CoopBizInfo;
import model.CoopBizInfoAssoc;
import model.CoopBizInfoMem;
import service.CoopBizInfoFacadeREST;
import service.CoopBizInfoAssocFacadeREST;
import service.CoopBizInfoMemFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class BusinessData implements Serializable {

    /**
     * Creates a new instance of BusinessData
     */
    public BusinessData() {
        System.out.print("BusinessData constructed");
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    @EJB
    private CoopBizInfoFacadeREST bizInfoREST;
    private CoopBizInfo bizInfo;
    private ArrayList<CoopBizInfo> bizInfoAL;
    private List<CoopBizInfo> bizInfoList;
    private DataModel<CoopBizInfo> bizInfoModel;
    @EJB
    private CoopBizInfoAssocFacadeREST bizInfoAssocREST;
    private CoopBizInfoAssoc bizInfoAssoc;
    private ArrayList<CoopBizInfoAssoc> bizInfoAssocAL;
    @EJB
    private CoopBizInfoMemFacadeREST bizInfoMemREST;
    private CoopBizInfoMem bizInfoMem;
    private ArrayList<CoopBizInfoMem> bizInfoMemAL;
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
    public CoopBizInfo getBizInfo() {
        if (bizInfo == null) {
            bizInfo = new CoopBizInfo();
        }
        return bizInfo;
    }

    public void setBizInfo(CoopBizInfo bizInfo) {
        this.bizInfo = bizInfo;
    }

    public ArrayList<CoopBizInfo> getBizInfoAL() {
        if (bizInfoAL == null) {
            bizInfoAL = new ArrayList<>();
        }
        return bizInfoAL;
    }

    public void setBizInfoAL(ArrayList<CoopBizInfo> bizInfoAL) {
        this.bizInfoAL = bizInfoAL;
    }

    public List<CoopBizInfo> getBizInfoList() {
        return bizInfoList;
    }

    public void setBizInfoList(List<CoopBizInfo> bizInfoList) {
        this.bizInfoList = bizInfoList;
    }

    public DataModel<CoopBizInfo> getBizInfoModel() {
        if (bizInfoModel == null) {
            bizInfoModel = new ListDataModel<>(getBizInfoList());
        }
        return bizInfoModel;
    }

    public void setBizInfoModel(DataModel<CoopBizInfo> bizInfoModel) {
        this.bizInfoModel = bizInfoModel;
    }

    public CoopBizInfoAssoc getBizInfoAssoc() {
        if (bizInfoAssoc == null) {
            bizInfoAssoc = new CoopBizInfoAssoc();
        }
        return bizInfoAssoc;
    }

    public void setBizInfoAssoc(CoopBizInfoAssoc bizInfoAssoc) {
        this.bizInfoAssoc = bizInfoAssoc;
    }

    public ArrayList<CoopBizInfoAssoc> getBizInfoAssocAL() {
        if (bizInfoAssocAL == null) {
            bizInfoAssocAL = new ArrayList<>();
        }
        return bizInfoAssocAL;
    }

    public void setBizInfoAssocAL(ArrayList<CoopBizInfoAssoc> bizInfoAssocAL) {
        this.bizInfoAssocAL = bizInfoAssocAL;
    }

    public CoopBizInfoMem getBizInfoMem() {
        if (bizInfoMem == null) {
            bizInfoMem = new CoopBizInfoMem();
        }
        return bizInfoMem;
    }

    public void setBizInfoMem(CoopBizInfoMem bizInfoMem) {
        this.bizInfoMem = bizInfoMem;
    }

    public ArrayList<CoopBizInfoMem> getBizInfoMemAL() {
        if (bizInfoMemAL == null) {
            bizInfoMemAL = new ArrayList<>();
        }
        return bizInfoMemAL;
    }

    public void setBizInfoMemAL(ArrayList<CoopBizInfoMem> bizInfoMemAL) {
        this.bizInfoMemAL = bizInfoMemAL;
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("businessData", null);
    }

    public void clearAL() {
        getBizInfoAL().clear();
        getBizInfoAssocAL().clear();
        getBizInfoMemAL().clear();
    }

    public void setAL() {
        getBizInfoAL();
        getBizInfoAssocAL();
        getBizInfoMemAL();
    }

    public void newSet() {
        getBizInfoAL().add(new CoopBizInfo());
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                getBizInfoMemAL().add(new CoopBizInfoMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getBizInfoAssocAL().add(new CoopBizInfoAssoc());
            }
        } catch (Exception e) {
            System.out.print("BusinessData.java newSet() " + e);
        }
    }

    public void addBiz() {
        getMemberData().getMember().setOwnedBusiness(true);
        if (getBizInfoAL().size() < 10) {
            newSet();
        }
    }

    public void removeBiz(int value) {
        getBizInfoAL().remove(value);
        if (getMembershipType().getTypeValue().equals(0)) {
            getBizInfoMemAL().remove(value);
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getBizInfoAssocAL().remove(value);
        }
        if (getBizInfoAL().isEmpty()) {
            getMemberData().getMember().setOwnedBusiness(false);
        }
    }

    public void removeLoad() {
        try {
            if (getBizInfoAL().get(0).getBizName() == null) {
                removeBiz(0);
            }
        } catch (Exception e) {
            System.out.print("BusinessData.java removeLoad() " + e);
        }
    }

    public void businessLoad() {
        if (getMode().isEditMode()) {
            selectedBusinessLoad();
        }
        for (int i = 0; i != getBizInfoAL().size(); i++) {
            getBizInfo().setBizName(getBizInfoAL().get(i).getBizName());
            getBizInfo().setDateEstablished(getBizInfoAL().get(i).getDateEstablished());
            getBizInfo().setDateDissolved(getBizInfoAL().get(i).getDateDissolved());
            getBizInfo().setBizType(getBizInfoAL().get(i).getBizType());
            getBizInfo().setBizContactNo(getBizInfoAL().get(i).getBizContactNo());
            getBizInfo().setBizNature(getBizInfoAL().get(i).getBizNature());
            getBizInfo().setBizIncRange(getBizInfoAL().get(i).getBizIncRange());
            getBizInfo().setBizStreet(getBizInfoAL().get(i).getBizStreet());
            getBizInfo().setBizBarangay(getBizInfoAL().get(i).getBizBarangay());
            getBizInfo().setBizCityMun(getBizInfoAL().get(i).getBizCityMun());
            bizInfoREST.create(getBizInfo());
            if (getMembershipType().getTypeValue().equals(0)) {
                getBizInfoMem().setMemNo(getMemberData().getMember());
                getBizInfoMem().setBizInfoNum(getBizInfo());
                bizInfoMemREST.create(getBizInfoMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getBizInfoAssoc().setAssociateNo(getAssociateData().getAssociate());
                getBizInfoAssoc().setBizInfoNum(getBizInfo());
                bizInfoAssocREST.create(getBizInfoAssoc());
            }
        }
    }

    public void selectedBusinessLoad() {
        String t1 = "", t2 = "", endString = "";
        CoopBizInfo am = null;
        CoopBizInfoMem m = null;
        CoopBizInfoAssoc a = null;
        Query CoopBizInfoM = null, CoopBizInfoA = null;
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                endString = "JOIN t2.bizInfoNum t1 "
                        + "JOIN t2.memNo t3 "
                        + "WHERE t3.memNo = '" + getMemberData().getMember().getMemNo() + "'";
                t1 = "SELECT t1 FROM CoopBizInfoMem t2 " + endString;
                t2 = "SELECT t2 FROM CoopBizInfoMem t2 " + endString;
                CoopBizInfoM = entityManagerFactory.createEntityManager().createQuery(t1);
                setBizInfoList(CoopBizInfoM.getResultList());
                if (getBizInfoList().size() > 0) {
                    for (int i = 0; i != getBizInfoList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopBizInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            m = (CoopBizInfoMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            bizInfoMemREST.remove(m);
                            bizInfoREST.remove(am);
                        } else {
                            am = (CoopBizInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            m = (CoopBizInfoMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (i == 0) {
                                getMemberData().getMember().setOwnedBusiness(true);
                                getBizInfoMemAL().set(i, m);
                                getBizInfoAL().set(i, am);
                            } else {
                                getBizInfoMemAL().add(m);
                                getBizInfoAL().add(am);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("BusinessData.java selectedBusinessLoad()-1 " + e);
        }
        try {
            if (getMembershipType().getTypeValue().equals(1)) {
                endString = "JOIN t2.bizInfoNum t1 "
                        + "JOIN t2.associateNo t3 "
                        + "WHERE t3.associateNo = '" + getAssociateData().getAssociate().getAssociateNo() + "'";
                t1 = "SELECT t1 FROM CoopBizInfoAssoc t2 " + endString;
                t2 = "SELECT t2 FROM CoopBizInfoAssoc t2 " + endString;
                CoopBizInfoA = entityManagerFactory.createEntityManager().createQuery(t1);
                setBizInfoList(CoopBizInfoA.getResultList());
                if (getBizInfoList().size() > 0) {
                    for (int i = 0; i != getBizInfoList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopBizInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            a = (CoopBizInfoAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            bizInfoAssocREST.remove(a);
                            bizInfoREST.remove(am);
                        } else {
                            am = (CoopBizInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            a = (CoopBizInfoAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (i == 0) {
                                getAssociateData().getAssociate().setOwnedBusiness(true);
                                getBizInfoAssocAL().set(i, a);
                                getBizInfoAL().set(i, am);
                            } else {
                                getBizInfoAssocAL().add(a);
                                getBizInfoAL().add(am);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("BusinessData.java selectedBusinessLoad()-2 " + e);
        }
        removeLoad();
    }

}
