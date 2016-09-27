/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_associate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAssociate.findAll", query = "SELECT c FROM CoopAssociate c"),
    @NamedQuery(name = "CoopAssociate.findByAssociateNo", query = "SELECT c FROM CoopAssociate c WHERE c.associateNo = :associateNo"),
    @NamedQuery(name = "CoopAssociate.findByMemIdNo", query = "SELECT c FROM CoopAssociate c WHERE c.memIdNo = :memIdNo"),
    @NamedQuery(name = "CoopAssociate.findByLastName", query = "SELECT c FROM CoopAssociate c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "CoopAssociate.findByFirstName", query = "SELECT c FROM CoopAssociate c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "CoopAssociate.findByMiddleName", query = "SELECT c FROM CoopAssociate c WHERE c.middleName = :middleName"),
    @NamedQuery(name = "CoopAssociate.findByNickname", query = "SELECT c FROM CoopAssociate c WHERE c.nickname = :nickname"),
    @NamedQuery(name = "CoopAssociate.findByBirthdate", query = "SELECT c FROM CoopAssociate c WHERE c.birthdate = :birthdate"),
    @NamedQuery(name = "CoopAssociate.findByBirthplace", query = "SELECT c FROM CoopAssociate c WHERE c.birthplace = :birthplace"),
    @NamedQuery(name = "CoopAssociate.findByResidenceType", query = "SELECT c FROM CoopAssociate c WHERE c.residenceType = :residenceType"),
    @NamedQuery(name = "CoopAssociate.findByStreet", query = "SELECT c FROM CoopAssociate c WHERE c.street = :street"),
    @NamedQuery(name = "CoopAssociate.findByBarangay", query = "SELECT c FROM CoopAssociate c WHERE c.barangay = :barangay"),
    @NamedQuery(name = "CoopAssociate.findByCityMun", query = "SELECT c FROM CoopAssociate c WHERE c.cityMun = :cityMun"),
    @NamedQuery(name = "CoopAssociate.findByRegion", query = "SELECT c FROM CoopAssociate c WHERE c.region = :region"),
    @NamedQuery(name = "CoopAssociate.findByProvince", query = "SELECT c FROM CoopAssociate c WHERE c.province = :province"),
    @NamedQuery(name = "CoopAssociate.findByCivilStatus", query = "SELECT c FROM CoopAssociate c WHERE c.civilStatus = :civilStatus"),
    @NamedQuery(name = "CoopAssociate.findByContactNumber", query = "SELECT c FROM CoopAssociate c WHERE c.contactNumber = :contactNumber"),
    @NamedQuery(name = "CoopAssociate.findByEmail", query = "SELECT c FROM CoopAssociate c WHERE c.email = :email"),
    @NamedQuery(name = "CoopAssociate.findByMemNo", query = "SELECT c FROM CoopAssociate c WHERE c.memNo = :memNo"),
    @NamedQuery(name = "CoopAssociate.findByMemDate", query = "SELECT c FROM CoopAssociate c WHERE c.memDate = :memDate"),
    @NamedQuery(name = "CoopAssociate.findByMemStatus", query = "SELECT c FROM CoopAssociate c WHERE c.memStatus = :memStatus"),
    @NamedQuery(name = "CoopAssociate.findByStatusDate", query = "SELECT c FROM CoopAssociate c WHERE c.statusDate = :statusDate"),
    @NamedQuery(name = "CoopAssociate.findByMemStatRem", query = "SELECT c FROM CoopAssociate c WHERE c.memStatRem = :memStatRem"),
    @NamedQuery(name = "CoopAssociate.findByOldOuCode", query = "SELECT c FROM CoopAssociate c WHERE c.oldOuCode = :oldOuCode"),
    @NamedQuery(name = "CoopAssociate.findByTaxIdNo", query = "SELECT c FROM CoopAssociate c WHERE c.taxIdNo = :taxIdNo"),
    @NamedQuery(name = "CoopAssociate.findByZipCode", query = "SELECT c FROM CoopAssociate c WHERE c.zipCode = :zipCode"),
    @NamedQuery(name = "CoopAssociate.findByPreoccupation", query = "SELECT c FROM CoopAssociate c WHERE c.preoccupation = :preoccupation"),
    @NamedQuery(name = "CoopAssociate.findByReligion", query = "SELECT c FROM CoopAssociate c WHERE c.religion = :religion"),
    @NamedQuery(name = "CoopAssociate.findByPPrefix", query = "SELECT c FROM CoopAssociate c WHERE c.pPrefix = :pPrefix"),
    @NamedQuery(name = "CoopAssociate.findBySuffix", query = "SELECT c FROM CoopAssociate c WHERE c.suffix = :suffix"),
    @NamedQuery(name = "CoopAssociate.findByPersonStatus", query = "SELECT c FROM CoopAssociate c WHERE c.personStatus = :personStatus"),
    @NamedQuery(name = "CoopAssociate.findByBlood", query = "SELECT c FROM CoopAssociate c WHERE c.blood = :blood"),
    @NamedQuery(name = "CoopAssociate.findByNotifyName", query = "SELECT c FROM CoopAssociate c WHERE c.notifyName = :notifyName"),
    @NamedQuery(name = "CoopAssociate.findByNotifyAddress", query = "SELECT c FROM CoopAssociate c WHERE c.notifyAddress = :notifyAddress"),
    @NamedQuery(name = "CoopAssociate.findByNotifyPhone", query = "SELECT c FROM CoopAssociate c WHERE c.notifyPhone = :notifyPhone"),
    @NamedQuery(name = "CoopAssociate.findByNotifyRelation", query = "SELECT c FROM CoopAssociate c WHERE c.notifyRelation = :notifyRelation"),
    @NamedQuery(name = "CoopAssociate.findByOwnedBusiness", query = "SELECT c FROM CoopAssociate c WHERE c.ownedBusiness = :ownedBusiness"),
    @NamedQuery(name = "CoopAssociate.findByNationality", query = "SELECT c FROM CoopAssociate c WHERE c.nationality = :nationality"),
    @NamedQuery(name = "CoopAssociate.findByApplicantNo", query = "SELECT c FROM CoopAssociate c WHERE c.applicantNo = :applicantNo"),
    @NamedQuery(name = "CoopAssociate.findByMemberType", query = "SELECT c FROM CoopAssociate c WHERE c.memberType = :memberType"),
    @NamedQuery(name = "CoopAssociate.findByScAcctno", query = "SELECT c FROM CoopAssociate c WHERE c.scAcctno = :scAcctno"),
    @NamedQuery(name = "CoopAssociate.findByHeight", query = "SELECT c FROM CoopAssociate c WHERE c.height = :height"),
    @NamedQuery(name = "CoopAssociate.findByWeight", query = "SELECT c FROM CoopAssociate c WHERE c.weight = :weight"),
    @NamedQuery(name = "CoopAssociate.findByPhilhealth", query = "SELECT c FROM CoopAssociate c WHERE c.philhealth = :philhealth"),
    @NamedQuery(name = "CoopAssociate.findBySss", query = "SELECT c FROM CoopAssociate c WHERE c.sss = :sss"),
    @NamedQuery(name = "CoopAssociate.findByPagIbig", query = "SELECT c FROM CoopAssociate c WHERE c.pagIbig = :pagIbig"),
    @NamedQuery(name = "CoopAssociate.findByGender", query = "SELECT c FROM CoopAssociate c WHERE c.gender = :gender"),
    @NamedQuery(name = "CoopAssociate.findByGsis", query = "SELECT c FROM CoopAssociate c WHERE c.gsis = :gsis")})
public class CoopAssociate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "associate_no")
    private Integer associateNo;
    @Size(max = 2147483647)
    @Column(name = "mem_id_no")
    private String memIdNo;
    @Size(max = 2147483647)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 2147483647)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 2147483647)
    @Column(name = "middle_name")
    private String middleName;
    @Size(max = 2147483647)
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Size(max = 2147483647)
    @Column(name = "birthplace")
    private String birthplace;
    @Size(max = 2147483647)
    @Column(name = "residence_type")
    private String residenceType;
    @Size(max = 2147483647)
    @Column(name = "street")
    private String street;
    @Size(max = 2147483647)
    @Column(name = "barangay")
    private String barangay;
    @Size(max = 2147483647)
    @Column(name = "city_mun")
    private String cityMun;
    @Size(max = 2147483647)
    @Column(name = "region")
    private String region;
    @Size(max = 2147483647)
    @Column(name = "province")
    private String province;
    @Size(max = 2147483647)
    @Column(name = "civil_status")
    private String civilStatus;
    @Size(max = 2147483647)
    @Column(name = "contact_number")
    private String contactNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Size(max = 2147483647)
    @Column(name = "mem_no")
    private String memNo;
    @Column(name = "mem_date")
    @Temporal(TemporalType.DATE)
    private Date memDate;
    @Size(max = 2147483647)
    @Column(name = "mem_status")
    private String memStatus;
    @Column(name = "status_date")
    @Temporal(TemporalType.DATE)
    private Date statusDate;
    @Size(max = 2147483647)
    @Column(name = "mem_stat_rem")
    private String memStatRem;
    @Size(max = 2147483647)
    @Column(name = "old_ou_code")
    private String oldOuCode;
    @Size(max = 2147483647)
    @Column(name = "tax_id_no")
    private String taxIdNo;
    @Size(max = 2147483647)
    @Column(name = "zip_code")
    private String zipCode;
    @Size(max = 2147483647)
    @Column(name = "preoccupation")
    private String preoccupation;
    @Size(max = 2147483647)
    @Column(name = "religion")
    private String religion;
    @Size(max = 2147483647)
    @Column(name = "p_prefix")
    private String pPrefix;
    @Size(max = 2147483647)
    @Column(name = "suffix")
    private String suffix;
    @Column(name = "person_status")
    private Boolean personStatus;
    @Size(max = 2147483647)
    @Column(name = "blood")
    private String blood;
    @Size(max = 2147483647)
    @Column(name = "notify_name")
    private String notifyName;
    @Size(max = 2147483647)
    @Column(name = "notify_address")
    private String notifyAddress;
    @Size(max = 2147483647)
    @Column(name = "notify_phone")
    private String notifyPhone;
    @Size(max = 2147483647)
    @Column(name = "notify_relation")
    private String notifyRelation;
    @Column(name = "owned_business")
    private Boolean ownedBusiness;
    @Column(name = "nationality")
    private Boolean nationality;
    @Column(name = "applicant_no")
    private Integer applicantNo;
    @Size(max = 2147483647)
    @Column(name = "member_type")
    private String memberType;
    @Size(max = 2147483647)
    @Column(name = "sc_acctno")
    private String scAcctno;
    @Size(max = 2147483647)
    @Column(name = "height")
    private String height;
    @Size(max = 2147483647)
    @Column(name = "weight")
    private String weight;
    @Size(max = 2147483647)
    @Column(name = "philhealth")
    private String philhealth;
    @Size(max = 2147483647)
    @Column(name = "sss")
    private String sss;
    @Size(max = 2147483647)
    @Column(name = "pag_ibig")
    private String pagIbig;
    @Column(name = "gender")
    private Character gender;
    @Size(max = 2147483647)
    @Column(name = "gsis")
    private String gsis;
    @OneToMany(mappedBy = "associateNo")
    private Collection<CoopImgSigAssoc> coopImgSigAssocCollection;
    @JoinColumn(name = "ou_code", referencedColumnName = "ou_code")
    @ManyToOne
    private CoopOrgUnit ouCode;
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne
    private CoopMemberStatus statusId;
    @JoinColumn(name = "reg_mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember regMemNo;

    public CoopAssociate() {
    }

    public CoopAssociate(Integer associateNo) {
        this.associateNo = associateNo;
    }

    public Integer getAssociateNo() {
        return associateNo;
    }

    public void setAssociateNo(Integer associateNo) {
        this.associateNo = associateNo;
    }

    public String getMemIdNo() {
        return memIdNo;
    }

    public void setMemIdNo(String memIdNo) {
        this.memIdNo = memIdNo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCityMun() {
        return cityMun;
    }

    public void setCityMun(String cityMun) {
        this.cityMun = cityMun;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }

    public Date getMemDate() {
        return memDate;
    }

    public void setMemDate(Date memDate) {
        this.memDate = memDate;
    }

    public String getMemStatus() {
        return memStatus;
    }

    public void setMemStatus(String memStatus) {
        this.memStatus = memStatus;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getMemStatRem() {
        return memStatRem;
    }

    public void setMemStatRem(String memStatRem) {
        this.memStatRem = memStatRem;
    }

    public String getOldOuCode() {
        return oldOuCode;
    }

    public void setOldOuCode(String oldOuCode) {
        this.oldOuCode = oldOuCode;
    }

    public String getTaxIdNo() {
        return taxIdNo;
    }

    public void setTaxIdNo(String taxIdNo) {
        this.taxIdNo = taxIdNo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPreoccupation() {
        return preoccupation;
    }

    public void setPreoccupation(String preoccupation) {
        this.preoccupation = preoccupation;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getPPrefix() {
        return pPrefix;
    }

    public void setPPrefix(String pPrefix) {
        this.pPrefix = pPrefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Boolean getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(Boolean personStatus) {
        this.personStatus = personStatus;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getNotifyName() {
        return notifyName;
    }

    public void setNotifyName(String notifyName) {
        this.notifyName = notifyName;
    }

    public String getNotifyAddress() {
        return notifyAddress;
    }

    public void setNotifyAddress(String notifyAddress) {
        this.notifyAddress = notifyAddress;
    }

    public String getNotifyPhone() {
        return notifyPhone;
    }

    public void setNotifyPhone(String notifyPhone) {
        this.notifyPhone = notifyPhone;
    }

    public String getNotifyRelation() {
        return notifyRelation;
    }

    public void setNotifyRelation(String notifyRelation) {
        this.notifyRelation = notifyRelation;
    }

    public Boolean getOwnedBusiness() {
        return ownedBusiness;
    }

    public void setOwnedBusiness(Boolean ownedBusiness) {
        this.ownedBusiness = ownedBusiness;
    }

    public Boolean getNationality() {
        return nationality;
    }

    public void setNationality(Boolean nationality) {
        this.nationality = nationality;
    }

    public Integer getApplicantNo() {
        return applicantNo;
    }

    public void setApplicantNo(Integer applicantNo) {
        this.applicantNo = applicantNo;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getScAcctno() {
        return scAcctno;
    }

    public void setScAcctno(String scAcctno) {
        this.scAcctno = scAcctno;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPhilhealth() {
        return philhealth;
    }

    public void setPhilhealth(String philhealth) {
        this.philhealth = philhealth;
    }

    public String getSss() {
        return sss;
    }

    public void setSss(String sss) {
        this.sss = sss;
    }

    public String getPagIbig() {
        return pagIbig;
    }

    public void setPagIbig(String pagIbig) {
        this.pagIbig = pagIbig;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getGsis() {
        return gsis;
    }

    public void setGsis(String gsis) {
        this.gsis = gsis;
    }

    @XmlTransient
    public Collection<CoopImgSigAssoc> getCoopImgSigAssocCollection() {
        return coopImgSigAssocCollection;
    }

    public void setCoopImgSigAssocCollection(Collection<CoopImgSigAssoc> coopImgSigAssocCollection) {
        this.coopImgSigAssocCollection = coopImgSigAssocCollection;
    }

    public CoopOrgUnit getOuCode() {
        return ouCode;
    }

    public void setOuCode(CoopOrgUnit ouCode) {
        this.ouCode = ouCode;
    }

    public CoopMemberStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(CoopMemberStatus statusId) {
        this.statusId = statusId;
    }

    public CoopMember getRegMemNo() {
        return regMemNo;
    }

    public void setRegMemNo(CoopMember regMemNo) {
        this.regMemNo = regMemNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (associateNo != null ? associateNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAssociate)) {
            return false;
        }
        CoopAssociate other = (CoopAssociate) object;
        if ((this.associateNo == null && other.associateNo != null) || (this.associateNo != null && !this.associateNo.equals(other.associateNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAssociate[ associateNo=" + associateNo + " ]";
    }
    
}
