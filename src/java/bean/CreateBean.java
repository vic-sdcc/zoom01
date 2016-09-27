/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import service.CoopAssociateFacadeREST;
import service.CoopMemberFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@RequestScoped
public class CreateBean extends CreateController implements Serializable {

    /**
     * Creates a new instance of CreateBean
     */
    public CreateBean() {
    }

    @EJB
    private CoopMemberFacadeREST memberREST;
    @EJB
    private CoopAssociateFacadeREST associateREST;
    @ManagedProperty(value = "#{myMessages}")
    private MyMessages myMessages;

    /*
     * getter setter
     */
    public MyMessages getMyMessages() {
        if (myMessages == null) {
            myMessages = new MyMessages();
        }
        return myMessages;
    }

    public void setMyMessages(MyMessages myMessages) {
        this.myMessages = myMessages;
    }

    /*
     * methods
     */
    public void loadAddlTables() {
        super.getApplicantRelation().applicantRelationLoad();
        super.getAddressData().addressLoad();
        super.getContactsData().contactsLoad();
        //super.getFamilyCreate().familyLoad();
        super.getEducationData().educationLoad();
        super.getEmploymentData().employmentLoad();
        super.getBusinessData().businessLoad();
        super.getSoiData().soiLoad();
        super.getSkillsData().skillLoad();
        super.getAwardsData().awardsLoad();
    }

    public void submit() {
        if (super.getApplicantData().getApplicant().getApplicantType().equals("REGULAR")) {
            super.getMemberData().memberLoad();
            memberREST.create(super.getMemberData().getMember());
        }
        if (super.getApplicantData().getApplicant().getApplicantType().equals("ASSOCIATE")) {
            super.getAssociateData().associateLoad();
            associateREST.create(super.getAssociateData().getAssociate());
        }
        loadAddlTables();
        getMyMessages().messages("Create " + super.getApplicantData().getApplicant().getApplicantType());
        super.getTabs().takeFinalStep();
    }

    public void saveToDisk() {
        super.getImgSigData().imgSigLoad();
        super.reset();
    }

}
