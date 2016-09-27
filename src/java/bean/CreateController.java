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
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class CreateController implements Serializable {

    /**
     * Creates a new instance of CreateController
     */
    public CreateController() {
    }

    @ManagedProperty(value = "#{applicantData}")
    private ApplicantData applicantData;
    @ManagedProperty(value = "#{memberData}")
    private MemberData memberData;
    @ManagedProperty(value = "#{associateData}")
    private AssociateData associateData;
    @ManagedProperty(value = "#{applicantRelation}")
    private ApplicantRelation applicantRelation;
    @ManagedProperty(value = "#{addressData}")
    private AddressData addressData;
    @ManagedProperty(value = "#{contactsData}")
    private ContactsData contactsData;
    @ManagedProperty(value = "#{familyCreate}")
    private FamilyCreate familyCreate;
    @ManagedProperty(value = "#{educationData}")
    private EducationData educationData;
    @ManagedProperty(value = "#{employmentData}")
    private EmploymentData employmentData;
    @ManagedProperty(value = "#{businessData}")
    private BusinessData businessData;
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
    @ManagedProperty(value = "#{imgSigData}")
    private ImgSigData imgSigData;
    @ManagedProperty(value = "#{personData}")
    private personData personData;

    /*
     * getter setter
     */
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

    public ApplicantRelation getApplicantRelation() {
        if (applicantRelation == null) {
            applicantRelation = new ApplicantRelation();
        }
        return applicantRelation;
    }

    public void setApplicantRelation(ApplicantRelation applicantRelation) {
        this.applicantRelation = applicantRelation;
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

    public FamilyCreate getFamilyCreate() {
        if (familyCreate == null) {
            familyCreate = new FamilyCreate();
        }
        return familyCreate;
    }

    public void setFamilyCreate(FamilyCreate familyCreate) {
        this.familyCreate = familyCreate;
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

    public ImgSigData getImgSigData() {
        if (imgSigData == null) {
            imgSigData = new ImgSigData();
        }
        return imgSigData;
    }

    public void setImgSigData(ImgSigData imgSigData) {
        this.imgSigData = imgSigData;
    }

    public personData getPersonData() {
        if (personData == null) {
            personData = new personData();
        }
        return personData;
    }

    public void setPersonData(personData personData) {
        this.personData = personData;
    }

    /*
     * methods
     */
    public void init() {
        clear();
        getMembershipType().setTypeValue(0);
    }

    public void reset() {
        init();
        getNavigation().navigateToMainCreate();
    }

    public void beanclear() {
        getOrgUnitData().beanclear();
        getMemStatusData().beanclear();
        getTabs().beanclear();
        getPanelToggle().beanclear();
        getImagesToggle().beanclear();
        ////getPersonData().beanclear();
        getApplicantData().beanclear();
        getMemberData().beanclear();
        getAssociateData().beanclear();
        getApplicantRelation().beanclear();
        getAddressData().beanclear();
        getContactsData().beanclear();
        //getFamilyCreate().beanclear();
        getEducationData().beanclear();
        getEmploymentData().beanclear();
        getBusinessData().beanclear();
        getSoiData().beanclear();
        getSkillsData().beanclear();
        getAwardsData().beanclear();
        getImgSigData().beanclear();
    }

    public void clear() {
        beanclear();
        clearAL();
        setAL();
        set();
    }

    public void clearAL() {
        getAddressData().clearAL();
        getContactsData().clearAL();
        //getFamilyCreate().clearAL();
        getEducationData().clearAL();
        getEmploymentData().clearAL();
        getBusinessData().clearAL();
        getSoiData().clearAL();
        getSkillsData().clearAL();
        getAwardsData().clearAL();
    }

    public void setAL() {
        getAddressData().setAL();
        getContactsData().setAL();
        //getFamilyCreate().setAL();
        getEducationData().setAL();
        getEmploymentData().setAL();
        getBusinessData().setAL();
        getSoiData().setAL();
        getSkillsData().setAL();
        getAwardsData().setAL();
    }

    public void set() {
        try {
            if (getApplicantData().getApplicant().getApplicantType().equals("REGULAR")) {
                getMemberData().newSet();
            }
            if (getApplicantData().getApplicant().getApplicantType().equals("ASSOCIATE")) {
                getAssociateData().newSet();
            }
        } catch (Exception e) {
            System.out.print("CreateController.java set()-1 " + e);
        }
        getAddressData().newSet();
        getEducationData().newSet();
/*
        getFamilyCreate().newSet();
        try {
            getFamilyCreate().initMap();
        } catch (Exception e) {
            System.out.print("CreateController.java set()-2 " + e);
        }
*/
    }

    public void changeTab(TabChangeEvent event) {
        clear();
        getMembershipType().assignType(event);
    }

    public void proceed() {
        clearAL();
        setAL();
        set();
        if (getApplicantData().getApplicant().getApplicantType().equals("REGULAR")) {
            getNavigation().navigateToWizard0();
        }
        if (getApplicantData().getApplicant().getApplicantType().equals("ASSOCIATE")) {
            getNavigation().navigateToWizard1();
        }
    }

}
