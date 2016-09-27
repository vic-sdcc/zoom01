/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class UpdateController implements Serializable {

    /**
     * Creates a new instance of UpdateController
     */
    public UpdateController() {
    }

    @ManagedProperty(value = "#{memberData}")
    private MemberData memberData;
    @ManagedProperty(value = "#{associateData}")
    private AssociateData associateData;
    @ManagedProperty(value = "#{addressData}")
    private AddressData addressData;
    @ManagedProperty(value = "#{contactsData}")
    private ContactsData contactsData;
    @ManagedProperty(value = "#{familyData}")
    private FamilyData familyData;
    @ManagedProperty(value = "#{educationData}")
    private EducationData educationData;
    @ManagedProperty(value = "#{employmentData}")
    private EmploymentData employmentData;
    @ManagedProperty(value = "#{businessData}")
    private BusinessData businessData;
    @ManagedProperty(value = "#{positionTenureData}")
    private PositionTenureData positionTenureData;
    @ManagedProperty(value = "#{soiData}")
    private SoiData soiData;
    @ManagedProperty(value = "#{skillsData}")
    private SkillsData skillsData;
    @ManagedProperty(value = "#{orgUnitData}")
    private OrgUnitData orgUnitData;
    @ManagedProperty(value = "#{memStatusData}")
    private MemStatusData memStatusData;
    @ManagedProperty(value = "#{awardsData}")
    private AwardsData awardsData;
    @ManagedProperty(value = "#{membershipType}")
    private MembershipType membershipType;
    @ManagedProperty(value = "#{navigation}")
    private Navigation navigation;
    @ManagedProperty(value = "#{tabs}")
    private Tabs tabs;
    @ManagedProperty(value = "#{panelToggle}")
    private PanelToggle panelToggle;
    @ManagedProperty(value = "#{imagesToggle}")
    private ImagesToggle imagesToggle;
    @ManagedProperty(value = "#{mode}")
    private Mode mode;
    @ManagedProperty(value = "#{selectedMember}")
    private selectedMember selectedMember;
    @ManagedProperty(value = "#{memberDataComp}")
    private memberDataComp memberDataComp;
    @ManagedProperty(value = "#{associateDataComp}")
    private associateDataComp associateDataComp;
    @ManagedProperty(value = "#{memberProfile}")
    private memberProfile memberProfile;
    @ManagedProperty(value = "#{imgSigData}")
    private ImgSigData imgSigData;

    /*
     * getter setter
     */
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

    public AddressData getAddressData() {
        if (addressData == null) {
            addressData = new AddressData();
        }
        return addressData;
    }

    public void setAddressData(AddressData addressData) {
        this.addressData = addressData;
    }

    public ContactsData getContactsData() {
        if (contactsData == null) {
            contactsData = new ContactsData();
        }
        return contactsData;
    }

    public void setContactsData(ContactsData contactsData) {
        this.contactsData = contactsData;
    }

    public FamilyData getFamilyData() {
        if (familyData == null) {
            familyData = new FamilyData();
        }
        return familyData;
    }

    public void setFamilyData(FamilyData familyData) {
        this.familyData = familyData;
    }

    public EducationData getEducationData() {
        if (educationData == null) {
            educationData = new EducationData();
        }
        return educationData;
    }

    public void setEducationData(EducationData educationData) {
        this.educationData = educationData;
    }

    public EmploymentData getEmploymentData() {
        if (employmentData == null) {
            employmentData = new EmploymentData();
        }
        return employmentData;
    }

    public void setEmploymentData(EmploymentData employmentData) {
        this.employmentData = employmentData;
    }

    public BusinessData getBusinessData() {
        if (businessData == null) {
            businessData = new BusinessData();
        }
        return businessData;
    }

    public void setBusinessData(BusinessData businessData) {
        this.businessData = businessData;
    }

    public PositionTenureData getPositionTenureData() {
        if (positionTenureData == null) {
            positionTenureData = new PositionTenureData();
        }
        return positionTenureData;
    }

    public void setPositionTenureData(PositionTenureData positionTenureData) {
        this.positionTenureData = positionTenureData;
    }

    public SoiData getSoiData() {
        if (soiData == null) {
            soiData = new SoiData();
        }
        return soiData;
    }

    public void setSoiData(SoiData soiData) {
        this.soiData = soiData;
    }

    public SkillsData getSkillsData() {
        if (skillsData == null) {
            skillsData = new SkillsData();
        }
        return skillsData;
    }

    public void setSkillsData(SkillsData skillsData) {
        this.skillsData = skillsData;
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

    public AwardsData getAwardsData() {
        if (awardsData == null) {
            awardsData = new AwardsData();
        }
        return awardsData;
    }

    public void setAwardsData(AwardsData awardsData) {
        this.awardsData = awardsData;
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

    public Navigation getNavigation() {
        if (navigation == null) {
            navigation = new Navigation();
        }
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
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

    public PanelToggle getPanelToggle() {
        if (panelToggle == null) {
            panelToggle = new PanelToggle();
        }
        return panelToggle;
    }

    public void setPanelToggle(PanelToggle panelToggle) {
        this.panelToggle = panelToggle;
    }

    public ImagesToggle getImagesToggle() {
        if (imagesToggle == null) {
            imagesToggle = new ImagesToggle();
        }
        return imagesToggle;
    }

    public void setImagesToggle(ImagesToggle imagesToggle) {
        this.imagesToggle = imagesToggle;
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

    public selectedMember getSelectedMember() {
        if (selectedMember == null) {
            selectedMember = new selectedMember();
        }
        return selectedMember;
    }

    public void setSelectedMember(selectedMember selectedMember) {
        this.selectedMember = selectedMember;
    }

    public memberDataComp getMemberDataComp() {
        if (memberDataComp == null) {
            memberDataComp = new memberDataComp();
        }
        return memberDataComp;
    }

    public void setMemberDataComp(memberDataComp memberDataComp) {
        this.memberDataComp = memberDataComp;
    }

    public associateDataComp getAssociateDataComp() {
        if (associateDataComp == null) {
            associateDataComp = new associateDataComp();
        }
        return associateDataComp;
    }

    public void setAssociateDataComp(associateDataComp associateDataComp) {
        this.associateDataComp = associateDataComp;
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

    public ImgSigData getImgSigData() {
        if (imgSigData == null) {
            imgSigData = new ImgSigData();
        }
        return imgSigData;
    }

    public void setImgSigData(ImgSigData imgSigData) {
        this.imgSigData = imgSigData;
    }

    /*
     * methods
     */
    public void init() {
    }

    public void reset() {
        getMembershipType().beanclear();
        clear();
        getNavigation().navigateToMainUpdate();
    }

    public void beanclear() {
        getOrgUnitData().beanclear();
        getMemStatusData().beanclear();
        getPanelToggle().beanclear();
        getImagesToggle().beanclear();
        getMemberData().beanclear();
        getAssociateData().beanclear();
        getAddressData().beanclear();
        getContactsData().beanclear();
        //getFamilyData().beanclear();
        getEducationData().beanclear();
        getEmploymentData().beanclear();
        getBusinessData().beanclear();
        getSoiData().beanclear();
        getSkillsData().beanclear();
        getPositionTenureData().beanclear();
        getAwardsData().beanclear();
        getImgSigData().beanclear();
    }

    public void clear() {
        beanclear();
        clearAL();
        setAL();
        set();
        getMode().setEditMode(false);
    }

    public void clearAL() {
        getAddressData().clearAL();
        getContactsData().clearAL();
        //getFamilyData().clearAL();//comment to work with view
        getEducationData().clearAL();
        getEmploymentData().clearAL();
        getBusinessData().clearAL();
        getSoiData().clearAL();
        getSkillsData().clearAL();
        getPositionTenureData().clearAL();
        getAwardsData().clearAL();
    }

    public void setAL() {
        getAddressData().setAL();
        getContactsData().setAL();
        //getFamilyData().setAL();//comment to work with view
        getEducationData().setAL();
        getEmploymentData().setAL();
        getBusinessData().setAL();
        getSoiData().setAL();
        getSkillsData().setAL();
        getPositionTenureData().setAL();
        getAwardsData().setAL();
    }

    public void set() {
        getAddressData().newSet();
        getContactsData().newSet();
        //getFamilyData().newSet();//comment to work with view
        getEducationData().newSet();
        getEmploymentData().newSet();
        getBusinessData().newSet();
        getSoiData().newSet();
        getSkillsData().newSet();
        getPositionTenureData().newSet();
        getAwardsData().newSet();
    }

    public void selectedLoad() {
        if (getMembershipType().getTypeValue().equals(0)) {
            getMemberData().setMember(getSelectedMember().regular(getMemberDataComp().getMemProfileList().get(getMemberDataComp().getIndexList()).getMemNo()));
            getMemberProfile().setStatusIdTemp(getMemberData().getMember().getStatusId().getStatusId());
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getAssociateData().setAssociate(getSelectedMember().associate(getAssociateDataComp().getMemProfileList().get(getAssociateDataComp().getIndexList()).getMemNo()));
            getMemberProfile().setStatusIdTemp(getAssociateData().getAssociate().getStatusId().getStatusId());
        }
        
        getAddressData().selectedAddressLoad();
        getContactsData().selectedContactsLoad();
        //getFamilyData().selectedFamilyLoad();
        getEducationData().selectedEducationLoad();
        getEmploymentData().selectedEmploymentLoad();
        getBusinessData().selectedBusinessLoad();
        getSoiData().selectedSoiLoad();
        getSkillsData().selectedSkillLoad();
        getSkillsData().removeLoad();
        getPositionTenureData().selectedPositionTenureLoad();
        getPositionTenureData().removeLoad();
        getAwardsData().selectedAwardsLoad();
        getImgSigData().selectedImgSigLoad();
    }

    public void loadM() {
        getMembershipType().assignTypeM();
        loadData();
        getNavigation().navigateToTabView0();
    }

    public void loadA() {
        getMembershipType().assignTypeA();
        loadData();
        getNavigation().navigateToTabView1();
    }

    public void loadM2() {
        getMembershipType().assignTypeM();
        loadData();
    }

    public void loadA2() {
        getMembershipType().assignTypeA();
        loadData();
    }

    public void loadData() {
        clearAL();
        getMode().setEditMode(false);
        if (getMembershipType().getTypeValue().equals(0)) {
            getMemberData().getMember();
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getAssociateData().getAssociate();
        }
        set();
        selectedLoad();
        getMode().setEditMode(true);
    }

    public void prntPage() {
        getNavigation().printPage();
    }

    public void prntRecord() {
        getNavigation().printRecord();
    }

    public void mainMem() {
        getNavigation().mainMember();
    }

    public void mainViewMem() {
        getNavigation().viewMainPage();
    }

}
