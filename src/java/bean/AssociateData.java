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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopAssociate;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class AssociateData implements Serializable {

    /**
     * Creates a new instance of AssociateData
     */
    public AssociateData() {
        newSet();
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private CoopAssociate associate, selectedAssociate;
    private List<CoopAssociate> associateList, associateViewList;
    private DataModel<CoopAssociate> associateModel;
    @ManagedProperty(value = "#{currentDate}")
    private Date currentDate;
    @ManagedProperty(value = "#{orgUnitData}")
    private OrgUnitData orgUnitData;
    @ManagedProperty(value = "#{memStatusData}")
    private MemStatusData memStatusData;
    @ManagedProperty(value = "#{applicantData}")
    private ApplicantData applicantData;
    @ManagedProperty(value = "#{memberProfile}")
    private memberProfile memberProfile;
    private String memIdNo, scAcctNo;

    /*
     * getter setter
     */
    public CoopAssociate getAssociate() {
        if (associate == null) {
            associate = new CoopAssociate();
        }
        return associate;
    }

    public void setAssociate(CoopAssociate associate) {
        this.associate = associate;
    }

    public CoopAssociate getSelectedAssociate() {
        if (selectedAssociate == null) {
            selectedAssociate = new CoopAssociate();
        }
        return selectedAssociate;
    }

    public void setSelectedAssociate(CoopAssociate selectedAssociate) {
        this.selectedAssociate = selectedAssociate;
    }

    public List<CoopAssociate> getAssociateList() {
        associateList = entityManagerFactory.createEntityManager().createQuery("SELECT a FROM CoopAssociate a ORDER BY a.associateNo").getResultList();
        return associateList;
    }

    public void setAssociateList(List<CoopAssociate> associateList) {
        this.associateList = associateList;
    }

    public List<CoopAssociate> getAssociateViewList() {
        if (associateViewList == null) {
            associateViewList = new ArrayList<>();
        }
        return associateViewList;
    }

    public void setAssociateViewList(List<CoopAssociate> associateViewList) {
        this.associateViewList = associateViewList;
    }

    public DataModel<CoopAssociate> getAssociateModel() {
        if (associateModel == null) {
            associateModel = new ListDataModel<>(getAssociateList());
        }
        return associateModel;
    }

    public void setAssociateModel(DataModel<CoopAssociate> associateModel) {
        this.associateModel = associateModel;
    }

    public Date getCurrentDate() {
        if (currentDate == null) {
            currentDate = new Date();
        }
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public OrgUnitData getOrgUnitData() {
        if (orgUnitData == null) {
            orgUnitData = new OrgUnitData();
        }
        return orgUnitData;
    }

    public void setOrgUnitData(OrgUnitData orgUnitData) {
        this.orgUnitData = orgUnitData;
    }

    public MemStatusData getMemStatusData() {
        if (memStatusData == null) {
            memStatusData = new MemStatusData();
        }
        return memStatusData;
    }

    public void setMemStatusData(MemStatusData memStatusData) {
        this.memStatusData = memStatusData;
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

    public memberProfile getMemberProfile() {
        if (memberProfile == null) {
            memberProfile = new memberProfile();
        }
        return memberProfile;
    }

    public void setMemberProfile(memberProfile memberProfile) {
        this.memberProfile = memberProfile;
    }

    public String getMemIdNo() {
        return memIdNo;
    }

    public void setMemIdNo(String memIdNo) {
        this.memIdNo = memIdNo;
    }

    public String getScAcctNo() {
        return scAcctNo;
    }

    public void setScAcctNo(String scAcctNo) {
        this.scAcctNo = scAcctNo;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("associateData", null);
    }

    public void newSet() {
        getAssociate().setMemDate(getCurrentDate());
        getAssociate().setStatusDate(getCurrentDate());
        getAssociate().setPersonStatus(true);
        getAssociate().setNationality(true);
        getAssociate().setMemberType(getApplicantData().getApplicant().getApplicantType());
        getMemStatusData().getMemberStatus().setStatusId(10);
        getAssociate().setStatusId(getMemStatusData().getMemberStatus());
        getAssociate().setMemStatRem("NEW MEMBER");
        getAssociate().setOwnedBusiness(false);
    }

    public String generateMemIdNo() {
        memIdNo = String.format("%06d", getAssociateModel().getRowCount() + 1);
        String a = (getCurrentDate().getYear() + "").substring(1);
        memIdNo = "00" + a + "-" + memIdNo;
        return memIdNo;
    }

    public String generateScAcctNo() {
        scAcctNo = String.format("%05d", getAssociateModel().getRowCount() + 1);
        scAcctNo = "12-" + scAcctNo;
        return scAcctNo;
    }

    public void associateLoad() {
        getAssociate().setMemDate(getAssociate().getMemDate());
        getAssociate().setMemNo(generateMemIdNo());
        getAssociate().setMemIdNo(generateMemIdNo());
        getAssociate().setScAcctno(generateScAcctNo());
        getAssociate().setOuCode(getApplicantData().getApplicant().getOuCode());
        getAssociate().setStatusDate(getAssociate().getStatusDate());
        getMemStatusData().getMemberStatus().setStatusId(10);
        getAssociate().setStatusId(getMemStatusData().getMemberStatus());
        getAssociate().setMemStatRem(getAssociate().getMemStatRem());
        getAssociate().setMemberType(getApplicantData().getApplicant().getApplicantType());
        getAssociate().setPPrefix(getAssociate().getPPrefix());
        getAssociate().setLastName(getApplicantData().getApplicant().getLastName());
        getAssociate().setFirstName(getApplicantData().getApplicant().getFirstName());
        getAssociate().setMiddleName(getApplicantData().getApplicant().getMiddleName());
        getAssociate().setSuffix(getAssociate().getSuffix());
        getAssociate().setNickname(getAssociate().getNickname());
        getAssociate().setHeight(getAssociate().getHeight());
        getAssociate().setWeight(getAssociate().getWeight());
        getAssociate().setBlood(getAssociate().getBlood());
        getAssociate().setGender(getApplicantData().getApplicant().getGender());
        getAssociate().setCivilStatus(getApplicantData().getApplicant().getCivilStatus().toString());
        getAssociate().setPersonStatus(getAssociate().getPersonStatus());
        getAssociate().setBirthdate(getApplicantData().getApplicant().getBirthdate());
        getAssociate().setBirthplace(getAssociate().getBirthplace());
        getAssociate().setReligion(getAssociate().getReligion());
        getAssociate().setNationality(getApplicantData().getApplicant().getNationality());
        getAssociate().setTaxIdNo(getAssociate().getTaxIdNo());
        getAssociate().setPagIbig(getAssociate().getPagIbig());
        getAssociate().setPhilhealth(getAssociate().getPhilhealth());
        getAssociate().setSss(getAssociate().getSss());
        getAssociate().setGsis(getAssociate().getGsis());
        getAssociate().setResidenceType(getAssociate().getResidenceType());
        getAssociate().setStreet(getApplicantData().getApplicant().getStreet());
        getAssociate().setBarangay(getApplicantData().getApplicant().getBarangay());
        getAssociate().setCityMun(getApplicantData().getApplicant().getCityMun());
        getAssociate().setRegion(getApplicantData().getApplicant().getRegion());
        getAssociate().setProvince(getApplicantData().getApplicant().getProvince());
        getAssociate().setZipCode(getAssociate().getZipCode());
        getAssociate().setEmail(getApplicantData().getApplicant().getEmail());
        getAssociate().setContactNumber(getApplicantData().getApplicant().getContactNumber());
        getAssociate().setPreoccupation(getApplicantData().getApplicant().getOccupation());
        getAssociate().setOwnedBusiness(getAssociate().getOwnedBusiness());
        getAssociate().setNotifyName(getAssociate().getNotifyName());
        getAssociate().setNotifyAddress(getAssociate().getNotifyAddress());
        getAssociate().setNotifyPhone(getAssociate().getNotifyPhone());
        getAssociate().setNotifyRelation(getAssociate().getNotifyRelation());
    }

    public void editOu() {
        getOrgUnitData().getOrgUnit().setOuCode(getAssociate().getOuCode().getOuCode());
        getAssociate().setOuCode(getOrgUnitData().getOrgUnit());
    }

    public void editMemberStatus() {
        getMemStatusData().getMemberStatus().setStatusId(getAssociate().getStatusId().getStatusId());
        getAssociate().setStatusId(getMemStatusData().getMemberStatus());
        getAssociate().setMemStatRem(getAssociate().getMemStatRem());
        getAssociate().setStatusDate(getCurrentDate());
    }

}
