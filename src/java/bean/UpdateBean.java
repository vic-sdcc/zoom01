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
public class UpdateBean extends UpdateController implements Serializable {

    /**
     * Creates a new instance of UpdateBean
     */
    public UpdateBean() {
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
        super.getAddressData().addressLoad();
        super.getContactsData().contactsLoad();
        //super.getFamilyData().familyLoad();
        super.getEducationData().educationLoad();
        super.getEmploymentData().employmentLoad();
        super.getBusinessData().businessLoad();
        super.getSoiData().soiLoad();
        super.getSkillsData().skillLoad();
        super.getPositionTenureData().positionTenureLoad();
        super.getAwardsData().awardsLoad();
        super.getImgSigData().editImgSig();
    }

    public void save() {
        if (super.getMembershipType().getTypeValue().equals(0)) {
            super.getMemberData().editOu();
            super.getMemberData().editMemberStatus();
            memberREST.edit(super.getMemberData().getMember());
            loadAddlTables();
            getMyMessages().messages("Update " + super.getMemberData().getMember().getMemberType());
        }
        if (super.getMembershipType().getTypeValue().equals(1)) {
            super.getAssociateData().editOu();
            super.getAssociateData().editMemberStatus();
            associateREST.edit(super.getAssociateData().getAssociate());
            loadAddlTables();
            getMyMessages().messages("Update " + super.getAssociateData().getAssociate().getMemberType());
        }
        super.reset();
    }

}
