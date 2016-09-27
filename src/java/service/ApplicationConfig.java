/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author mis05
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        // following code can be used to customize Jersey 1.x JSON provider:
        try {
            Class jacksonProvider = Class.forName("org.codehaus.jackson.jaxrs.JacksonJsonProvider");
            resources.add(jacksonProvider);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.CoopAddlAddressAssocFacadeREST.class);
        resources.add(service.CoopAddlAddressFacadeREST.class);
        resources.add(service.CoopAddlAddressMemFacadeREST.class);
        resources.add(service.CoopAddlContactInfoAssocFacadeREST.class);
        resources.add(service.CoopAddlContactInfoFacadeREST.class);
        resources.add(service.CoopAddlContactInfoMemFacadeREST.class);
        resources.add(service.CoopApplicantFacadeREST.class);
        resources.add(service.CoopAssociateApplicantFacadeREST.class);
        resources.add(service.CoopAssociateFacadeREST.class);
        resources.add(service.CoopAwardsAssocFacadeREST.class);
        resources.add(service.CoopAwardsFacadeREST.class);
        resources.add(service.CoopAwardsMemFacadeREST.class);
        resources.add(service.CoopBizInfoAssocFacadeREST.class);
        resources.add(service.CoopBizInfoFacadeREST.class);
        resources.add(service.CoopBizInfoMemFacadeREST.class);
        resources.add(service.CoopChildFacadeREST.class);
        resources.add(service.CoopEducInfoAssocFacadeREST.class);
        resources.add(service.CoopEducInfoFacadeREST.class);
        resources.add(service.CoopEducInfoMemFacadeREST.class);
        resources.add(service.CoopEmplDtlAssocFacadeREST.class);
        resources.add(service.CoopEmplDtlFacadeREST.class);
        resources.add(service.CoopEmplDtlMemFacadeREST.class);
        resources.add(service.CoopFamilyFacadeREST.class);
        resources.add(service.CoopImgSigAssocFacadeREST.class);
        resources.add(service.CoopImgSigFacadeREST.class);
        resources.add(service.CoopImgSigMemFacadeREST.class);
        resources.add(service.CoopMajorOccuFacadeREST.class);
        resources.add(service.CoopMajorSkillsFacadeREST.class);
        resources.add(service.CoopMemOuPosFacadeREST.class);
        resources.add(service.CoopMemberApplicantFacadeREST.class);
        resources.add(service.CoopMemberFacadeREST.class);
        resources.add(service.CoopMemberStatusFacadeREST.class);
        resources.add(service.CoopMemberUserFacadeREST.class);
        resources.add(service.CoopMinorGroupOccuFacadeREST.class);
        resources.add(service.CoopNonMemberFacadeREST.class);
        resources.add(service.CoopOccupationFacadeREST.class);
        resources.add(service.CoopOrgPositionFacadeREST.class);
        resources.add(service.CoopOrgUnitFacadeREST.class);
        resources.add(service.CoopOtherSourceOfIncomeAssocFacadeREST.class);
        resources.add(service.CoopOtherSourceOfIncomeFacadeREST.class);
        resources.add(service.CoopOtherSourceOfIncomeMemFacadeREST.class);
        resources.add(service.CoopPersonAssociateFacadeREST.class);
        resources.add(service.CoopPersonFacadeREST.class);
        resources.add(service.CoopPersonMemFacadeREST.class);
        resources.add(service.CoopPersonNonMemberFacadeREST.class);
        resources.add(service.CoopPersonTypeFacadeREST.class);
        resources.add(service.CoopPositionFacadeREST.class);
        resources.add(service.CoopPositionTenureFacadeREST.class);
        resources.add(service.CoopSkillsAssocFacadeREST.class);
        resources.add(service.CoopSkillsFacadeREST.class);
        resources.add(service.CoopSkillsMemFacadeREST.class);
        resources.add(service.CoopSubMajorOccuFacadeREST.class);
        resources.add(service.CoopSubMajorSkillsFacadeREST.class);
        resources.add(service.CoopTenureFacadeREST.class);
        resources.add(service.CoopUnitGroupOccuFacadeREST.class);
        resources.add(service.OrganizationFacadeREST.class);
    }
    
}
