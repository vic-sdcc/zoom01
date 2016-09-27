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
import model.CoopEducInfo;
import model.CoopEducInfoAssoc;
import model.CoopEducInfoMem;
import service.CoopEducInfoFacadeREST;
import service.CoopEducInfoAssocFacadeREST;
import service.CoopEducInfoMemFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class EducationData implements Serializable {

    /**
     * Creates a new instance of EducationData
     */
    public EducationData() {
        System.out.print("EducationData constructed");
        newSet();
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    @EJB
    private CoopEducInfoFacadeREST educInfoREST;
    private CoopEducInfo educInfo;
    private ArrayList<CoopEducInfo> educInfoAL;
    private List<CoopEducInfo> educInfoList;
    private DataModel<CoopEducInfo> educInfoModel;
    @EJB
    private CoopEducInfoAssocFacadeREST educInfoAssocREST;
    private CoopEducInfoAssoc educInfoAssoc;
    private ArrayList<CoopEducInfoAssoc> educInfoAssocAL;
    @EJB
    private CoopEducInfoMemFacadeREST educInfoMemREST;
    private CoopEducInfoMem educInfoMem;
    private ArrayList<CoopEducInfoMem> educInfoMemAL;
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
    public CoopEducInfo getEducInfo() {
        if (educInfo == null) {
            educInfo = new CoopEducInfo();
        }
        return educInfo;
    }

    public void setEducInfo(CoopEducInfo educInfo) {
        this.educInfo = educInfo;
    }

    public ArrayList<CoopEducInfo> getEducInfoAL() {
        if (educInfoAL == null) {
            educInfoAL = new ArrayList<>();
        }
        return educInfoAL;
    }

    public void setEducInfoAL(ArrayList<CoopEducInfo> educInfoAL) {
        this.educInfoAL = educInfoAL;
    }

    public List<CoopEducInfo> getEducInfoList() {
        return educInfoList;
    }

    public void setEducInfoList(List<CoopEducInfo> educInfoList) {
        this.educInfoList = educInfoList;
    }

    public DataModel<CoopEducInfo> getEducInfoModel() {
        if (educInfoModel == null) {
            educInfoModel = new ListDataModel<>(getEducInfoList());
        }
        return educInfoModel;
    }

    public void setEducInfoModel(DataModel<CoopEducInfo> educInfoModel) {
        this.educInfoModel = educInfoModel;
    }

    public CoopEducInfoAssoc getEducInfoAssoc() {
        if (educInfoAssoc == null) {
            educInfoAssoc = new CoopEducInfoAssoc();
        }
        return educInfoAssoc;
    }

    public void setEducInfoAssoc(CoopEducInfoAssoc educInfoAssoc) {
        this.educInfoAssoc = educInfoAssoc;
    }

    public ArrayList<CoopEducInfoAssoc> getEducInfoAssocAL() {
        if (educInfoAssocAL == null) {
            educInfoAssocAL = new ArrayList<>();
        }
        return educInfoAssocAL;
    }

    public void setEducInfoAssocAL(ArrayList<CoopEducInfoAssoc> educInfoAssocAL) {
        this.educInfoAssocAL = educInfoAssocAL;
    }

    public CoopEducInfoMem getEducInfoMem() {
        if (educInfoMem == null) {
            educInfoMem = new CoopEducInfoMem();
        }
        return educInfoMem;
    }

    public void setEducInfoMem(CoopEducInfoMem educInfoMem) {
        this.educInfoMem = educInfoMem;
    }

    public ArrayList<CoopEducInfoMem> getEducInfoMemAL() {
        if (educInfoMemAL == null) {
            educInfoMemAL = new ArrayList<>();
        }
        return educInfoMemAL;
    }

    public void setEducInfoMemAL(ArrayList<CoopEducInfoMem> educInfoMemAL) {
        this.educInfoMemAL = educInfoMemAL;
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
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("educationData", null);
    }

    public void clearAL() {
        getEducInfoAL().clear();
        getEducInfoAssocAL().clear();
        getEducInfoMemAL().clear();
    }

    public void setAL() {
        getEducInfoAL();
        getEducInfoAssocAL();
        getEducInfoMemAL();
    }

    public void newSet() {
        getEducInfoAL().add(new CoopEducInfo());
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                getEducInfoMemAL().add(new CoopEducInfoMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getEducInfoAssocAL().add(new CoopEducInfoAssoc());
            }
        } catch (Exception e) {
            System.out.print("EducationData.java newSet() " + e);
        }
    }

    public void addEducation() {
        if (getEducInfoAL().size() < 12) {
            newSet();
        }
    }

    public void removeEducation(int value) {
        getEducInfoAL().remove(value);
        if (getMembershipType().getTypeValue().equals(0)) {
            getEducInfoMemAL().remove(value);
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getEducInfoAssocAL().remove(value);
        }
    }

    public void removeLoad() {
        try {
            if (getEducInfoAL().get(0).getSchoolName() == null) {
                removeEducation(0);
            }
        } catch (Exception e) {
            System.out.print("EducationData.java removeLoad() " + e);
        }
    }

    public void educationLoad() {
        if (getMode().isEditMode()) {
            selectedEducationLoad();
        }
        for (int i = 0; i != getEducInfoAL().size(); i++) {
            getEducInfo().setSchoolName(getEducInfoAL().get(i).getSchoolName());
            getEducInfo().setCourse(getEducInfoAL().get(i).getCourse());
            getEducInfo().setSchoolLevel(getEducInfoAL().get(i).getSchoolLevel());
            getEducInfo().setGraduated(getEducInfoAL().get(i).getGraduated());
            getEducInfo().setYearLastAttended(getEducInfoAL().get(i).getYearLastAttended());
            educInfoREST.create(getEducInfo());
            if (getMembershipType().getTypeValue().equals(0)) {
                getEducInfoMem().setMemNo(getMemberData().getMember());
                getEducInfoMem().setEducInfoNum(getEducInfo());
                educInfoMemREST.create(getEducInfoMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getEducInfoAssoc().setAssociateNo(getAssociateData().getAssociate());
                getEducInfoAssoc().setEducInfoNum(getEducInfo());
                educInfoAssocREST.create(getEducInfoAssoc());
            }
        }
    }

    public void selectedEducationLoad() {
        String t1 = "", t2 = "", endString = "";
        CoopEducInfo am = null;
        CoopEducInfoMem m = null;
        CoopEducInfoAssoc a = null;
        Query CoopEducInfoM = null, CoopEducInfoA = null;
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                endString = "JOIN t2.educInfoNum t1 "
                        + "JOIN t2.memNo t3 "
                        + "WHERE t3.memNo = '" + getMemberData().getMember().getMemNo() + "'";
                t1 = "SELECT t1 FROM CoopEducInfoMem t2 " + endString;
                t2 = "SELECT t2 FROM CoopEducInfoMem t2 " + endString;
                CoopEducInfoM = entityManagerFactory.createEntityManager().createQuery(t1);
                setEducInfoList(CoopEducInfoM.getResultList());
                if (getEducInfoList().size() > 0) {
                    for (int i = 0; i != getEducInfoList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopEducInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            m = (CoopEducInfoMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            educInfoMemREST.remove(m);
                            educInfoREST.remove(am);
                        } else {
                            am = (CoopEducInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            m = (CoopEducInfoMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (i == 0) {
                                getEducInfoMemAL().set(i, m);
                                getEducInfoAL().set(i, am);
                            } else {
                                getEducInfoMemAL().add(m);
                                getEducInfoAL().add(am);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("EducationData.java selectedEducationLoad()-1 " + e);
        }
        try {
            if (getMembershipType().getTypeValue().equals(1)) {
                endString = "JOIN t2.educInfoNum t1 "
                        + "JOIN t2.associateNo t3 "
                        + "WHERE t3.associateNo = '" + getAssociateData().getAssociate().getAssociateNo() + "'";
                t1 = "SELECT t1 FROM CoopEducInfoAssoc t2 " + endString;
                t2 = "SELECT t2 FROM CoopEducInfoAssoc t2 " + endString;
                CoopEducInfoA = entityManagerFactory.createEntityManager().createQuery(t1);
                setEducInfoList(CoopEducInfoA.getResultList());
                if (getEducInfoList().size() > 0) {
                    for (int i = 0; i != getEducInfoList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopEducInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            a = (CoopEducInfoAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            educInfoAssocREST.remove(a);
                            educInfoREST.remove(am);
                        } else {
                            am = (CoopEducInfo) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            a = (CoopEducInfoAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            if (i == 0) {
                                getEducInfoAssocAL().set(i, a);
                                getEducInfoAL().set(i, am);
                            } else {
                                getEducInfoAssocAL().add(a);
                                getEducInfoAL().add(am);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("EducationData.java selectedEducationLoad()-2 " + e);
        }
        removeLoad();
    }

}
