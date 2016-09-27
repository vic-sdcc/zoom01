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
import model.CoopEmplDtl;
import model.CoopEmplDtlAssoc;
import model.CoopEmplDtlMem;
import model.CoopOccupation;
import service.CoopEmplDtlFacadeREST;
import service.CoopEmplDtlAssocFacadeREST;
import service.CoopEmplDtlMemFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class EmploymentData implements Serializable {

    /**
     * Creates a new instance of EmploymentData
     */
    public EmploymentData() {
        System.out.print("EmploymentData constructed");
    }

    private ArrayList<String> stringAL;
    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private ArrayList<CoopOccupation> occupationAL;
    @EJB
    private CoopEmplDtlFacadeREST emplDtlREST;
    private CoopEmplDtl emplDtl;
    private ArrayList<CoopEmplDtl> emplDtlAL;
    private List<CoopEmplDtl> emplDtlList;
    private DataModel<CoopEmplDtl> emplDtlModel;
    @EJB
    private CoopEmplDtlAssocFacadeREST emplDtlAssocREST;
    private CoopEmplDtlAssoc emplDtlAssoc;
    private ArrayList<CoopEmplDtlAssoc> emplDtlAssocAL;
    @EJB
    private CoopEmplDtlMemFacadeREST emplDtlMemREST;
    private CoopEmplDtlMem emplDtlMem;
    private ArrayList<CoopEmplDtlMem> emplDtlMemAL;
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
    @ManagedProperty(value = "#{tabs}")
    private Tabs tabs;

    /*
     * getter setter
     */
    public ArrayList<String> getStringAL() {
        if (stringAL == null) {
            stringAL = new ArrayList<>();
        }
        return stringAL;
    }

    public void setStringAL(ArrayList<String> stringAL) {
        this.stringAL = stringAL;
    }

    public CoopEmplDtl getEmplDtl() {
        if (emplDtl == null) {
            emplDtl = new CoopEmplDtl();
        }
        return emplDtl;
    }

    public void setEmplDtl(CoopEmplDtl emplDtl) {
        this.emplDtl = emplDtl;
    }

    public ArrayList<CoopEmplDtl> getEmplDtlAL() {
        if (emplDtlAL == null) {
            emplDtlAL = new ArrayList<>();
        }
        return emplDtlAL;
    }

    public void setEmplDtlAL(ArrayList<CoopEmplDtl> emplDtlAL) {
        this.emplDtlAL = emplDtlAL;
    }

    public List<CoopEmplDtl> getEmplDtlList() {
        return emplDtlList;
    }

    public void setEmplDtlList(List<CoopEmplDtl> emplDtlList) {
        this.emplDtlList = emplDtlList;
    }

    public DataModel<CoopEmplDtl> getEmplDtlModel() {
        if (emplDtlModel == null) {
            emplDtlModel = new ListDataModel<>(getEmplDtlList());
        }
        return emplDtlModel;
    }

    public void setEmplDtlModel(DataModel<CoopEmplDtl> emplDtlModel) {
        this.emplDtlModel = emplDtlModel;
    }

    public CoopEmplDtlAssoc getEmplDtlAssoc() {
        if (emplDtlAssoc == null) {
            emplDtlAssoc = new CoopEmplDtlAssoc();
        }
        return emplDtlAssoc;
    }

    public void setEmplDtlAssoc(CoopEmplDtlAssoc emplDtlAssoc) {
        this.emplDtlAssoc = emplDtlAssoc;
    }

    public ArrayList<CoopEmplDtlAssoc> getEmplDtlAssocAL() {
        if (emplDtlAssocAL == null) {
            emplDtlAssocAL = new ArrayList<>();
        }
        return emplDtlAssocAL;
    }

    public void setEmplDtlAssocAL(ArrayList<CoopEmplDtlAssoc> emplDtlAssocAL) {
        this.emplDtlAssocAL = emplDtlAssocAL;
    }

    public CoopEmplDtlMem getEmplDtlMem() {
        if (emplDtlMem == null) {
            emplDtlMem = new CoopEmplDtlMem();
        }
        return emplDtlMem;
    }

    public void setEmplDtlMem(CoopEmplDtlMem emplDtlMem) {
        this.emplDtlMem = emplDtlMem;
    }

    public ArrayList<CoopEmplDtlMem> getEmplDtlMemAL() {
        if (emplDtlMemAL == null) {
            emplDtlMemAL = new ArrayList<>();
        }
        return emplDtlMemAL;
    }

    public void setEmplDtlMemAL(ArrayList<CoopEmplDtlMem> emplDtlMemAL) {
        this.emplDtlMemAL = emplDtlMemAL;
    }

    public ArrayList<CoopOccupation> getOccupationAL() {
        if (occupationAL == null) {
            occupationAL = new ArrayList<>();
        }
        return occupationAL;
    }

    public void setOccupationAL(ArrayList<CoopOccupation> occupationAL) {
        this.occupationAL = occupationAL;
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

    public Tabs getTabs() {
        if (tabs == null) {
            tabs = new Tabs();
        }
        return tabs;
    }

    public void setTabs(Tabs tabs) {
        this.tabs = tabs;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("employmentData", null);
    }

    public void clearAL() {
        getEmplDtlAL().clear();
        getOccupationAL().clear();
        getEmplDtlAssocAL().clear();
        getEmplDtlMemAL().clear();
        getStringAL().clear();
    }

    public void setAL() {
        getEmplDtlAL();
        getOccupationAL();
        getEmplDtlAssocAL();
        getEmplDtlMemAL();
        getStringAL();
    }

    public void newSet() {
        getEmplDtlAL().add(new CoopEmplDtl());
        getOccupationAL().add(new CoopOccupation());
        getStringAL().add(new String());
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                getEmplDtlMemAL().add(new CoopEmplDtlMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getEmplDtlAssocAL().add(new CoopEmplDtlAssoc());
            }
        } catch (Exception e) {
            System.out.print("EmploymentData.java newSet() " + e);
        }
    }

    public void addEmpl() {
        if (getEmplDtlAL().size() < 5) {
            newSet();
        }
    }

    public void removeEmpl(int value) {
        getEmplDtlAL().remove(value);
        getOccupationAL().remove(value);
        getStringAL().remove(value);
        if (getMembershipType().getTypeValue().equals(0)) {
            getEmplDtlMemAL().remove(value);
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getEmplDtlAssocAL().remove(value);
        }
    }

    public void removeLoad() {
        try {
            if (getEmplDtlAL().get(0).getEmplBizName() == null) {
                removeEmpl(0);
            }
        } catch (Exception e) {
            System.out.print("EmploymentData.java removeLoad() " + e);
        }
    }

    public void employmentLoad() {
        if (getMode().isEditMode()) {
            selectedEmploymentLoad();
        }
        for (int i = 0; i != getEmplDtlAL().size(); i++) {
            getOccupationAL().get(i).setOccupationCode((String) entityManagerFactory.createEntityManager().createQuery("SELECT o.occupationCode FROM CoopOccupation o WHERE o.unitName = '" + getOccupationAL().get(i).getUnitName() + "'").getResultList().get(0));
            //getOccupationAL().get(i).setOccupationCode((String) entityManagerFactory.createEntityManager().createQuery("SELECT o.occupationCode FROM CoopOccupation o WHERE CONCAT(UPPER(o.unitName),'(',UPPER(o.unitGroupCode.minorGroupCode.subMajorGroupCode.majorCode.majorName),',',UPPER(o.unitGroupCode.minorGroupCode.subMajorGroupCode.subMajorName),',',UPPER(o.unitGroupCode.minorGroupCode.minorGroupName),',',UPPER(o.unitGroupCode.unitGroupName),')') = '" + getStringAL().get(i) + "'").getResultList().get(0));
            getEmplDtl().setEmplBizName(getEmplDtlAL().get(i).getEmplBizName());
            getEmplDtl().setEmplContactNo(getEmplDtlAL().get(i).getEmplContactNo());
            getEmplDtl().setEmplEmail(getEmplDtlAL().get(i).getEmplEmail());
            getEmplDtl().setEmplStreet(getEmplDtlAL().get(i).getEmplStreet());
            getEmplDtl().setEmplBarangay(getEmplDtlAL().get(i).getEmplBarangay());
            getEmplDtl().setEmplCityMun(getEmplDtlAL().get(i).getEmplCityMun());
            getEmplDtl().setEmploymentDate(getEmplDtlAL().get(i).getEmploymentDate());
            getEmplDtl().setEmploymentEndDate(getEmplDtlAL().get(i).getEmploymentEndDate());
            getEmplDtl().setEmploymentStat(getEmplDtlAL().get(i).getEmploymentStat());
            getEmplDtl().setWorkplaceEmailAdd(getEmplDtlAL().get(i).getWorkplaceEmailAdd());
            getEmplDtl().setCompBracket(getEmplDtlAL().get(i).getCompBracket());
            getEmplDtl().setEmplRankPos(getOccupationAL().get(i));
            emplDtlREST.create(getEmplDtl());
            if (getMembershipType().getTypeValue().equals(0)) {
                getEmplDtlMem().setMemNo(getMemberData().getMember());
                getEmplDtlMem().setEmplDtlNum(getEmplDtl());
                emplDtlMemREST.create(getEmplDtlMem());
            }
            if (getMembershipType().getTypeValue().equals(1)) {
                getEmplDtlAssoc().setAssociateNo(getAssociateData().getAssociate());
                getEmplDtlAssoc().setEmplDtlNum(getEmplDtl());
                emplDtlAssocREST.create(getEmplDtlAssoc());
            }
        }
    }

    public void selectedEmploymentLoad() {
        String t1 = "", t2 = "", t4 = "", t6 = "", endString = "";
        String d = null;
        CoopEmplDtl am = null;
        CoopEmplDtlMem m = null;
        CoopEmplDtlAssoc a = null;
        CoopOccupation o = null;
        Query CoopEmplDtlM = null, CoopEmplDtlA = null;
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                endString = "JOIN t2.emplDtlNum t1 "
                        + "JOIN t2.memNo t3 "
                        + "JOIN t1.emplRankPos t4 "
                        + "WHERE t3.memNo = '" + getMemberData().getMember().getMemNo() + "'";
                t1 = "SELECT t1 FROM CoopEmplDtlMem t2 " + endString;
                t2 = "SELECT t2 FROM CoopEmplDtlMem t2 " + endString;
                t4 = "SELECT t4 FROM CoopEmplDtlMem t2 " + endString;
                //t6 = "SELECT CONCAT(t4,'(',t7,',',t8,',',t9,',',t10,')') FROM CoopEmplDtlMem t2 " + endString;
                CoopEmplDtlM = entityManagerFactory.createEntityManager().createQuery(t1);
                setEmplDtlList(CoopEmplDtlM.getResultList());
                if (getEmplDtlList().size() > 0) {
                    for (int i = 0; i != getEmplDtlList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopEmplDtl) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            m = (CoopEmplDtlMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            emplDtlMemREST.remove(m);
                            emplDtlREST.remove(am);
                        } else {
                            am = (CoopEmplDtl) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            m = (CoopEmplDtlMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            o = (CoopOccupation) entityManagerFactory.createEntityManager().createQuery(t4).getResultList().get(i);
                            //d = (String) entityManagerFactory.createEntityManager().createQuery(t6).getResultList().get(i);
                            if (i == 0) {
                                getEmplDtlMemAL().set(i, m);
                                getEmplDtlAL().set(i, am);
                                getOccupationAL().set(i, o);
                                //getStringAL().set(i, d);
                            } else {
                                getEmplDtlMemAL().add(m);
                                getEmplDtlAL().add(am);
                                getOccupationAL().add(o);
                                //getStringAL().add(d);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("EmploymentData.java selectedEmploymentLoad()-1 " + e);
        }
        try {
            if (getMembershipType().getTypeValue().equals(1)) {
                endString = "JOIN t2.emplDtlNum t1 "
                        + "JOIN t2.associateNo t3 "
                        + "JOIN t1.emplRankPos t4 "
                        + "WHERE t3.associateNo = '" + getAssociateData().getAssociate().getAssociateNo() + "'";
                t1 = "SELECT t1 FROM CoopEmplDtlAssoc t2 " + endString;
                t2 = "SELECT t2 FROM CoopEmplDtlAssoc t2 " + endString;
                t4 = "SELECT t4 FROM CoopEmplDtlAssoc t2 " + endString;
                CoopEmplDtlA = entityManagerFactory.createEntityManager().createQuery(t1);
                setEmplDtlList(CoopEmplDtlA.getResultList());
                if (getEmplDtlList().size() > 0) {
                    for (int i = 0; i != getEmplDtlList().size(); i++) {
                        if (getMode().isEditMode()) {
                            am = (CoopEmplDtl) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                            a = (CoopEmplDtlAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                            emplDtlAssocREST.remove(a);
                            emplDtlREST.remove(am);
                        } else {
                            am = (CoopEmplDtl) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(i);
                            a = (CoopEmplDtlAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(i);
                            o = (CoopOccupation) entityManagerFactory.createEntityManager().createQuery(t4).getResultList().get(i);
                            if (i == 0) {
                                getEmplDtlAssocAL().set(i, a);
                                getEmplDtlAL().set(i, am);
                                getOccupationAL().set(i, o);
                            } else {
                                getEmplDtlAssocAL().add(a);
                                getEmplDtlAL().add(am);
                                getOccupationAL().add(o);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.print("EmploymentData.java selectedEmploymentLoad()-2 " + e);
        }
        removeLoad();
    }

}
