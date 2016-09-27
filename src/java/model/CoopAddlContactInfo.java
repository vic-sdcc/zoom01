/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_addl_contact_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAddlContactInfo.findAll", query = "SELECT c FROM CoopAddlContactInfo c"),
    @NamedQuery(name = "CoopAddlContactInfo.findByContactInfoNo", query = "SELECT c FROM CoopAddlContactInfo c WHERE c.contactInfoNo = :contactInfoNo"),
    @NamedQuery(name = "CoopAddlContactInfo.findByContactType", query = "SELECT c FROM CoopAddlContactInfo c WHERE c.contactType = :contactType"),
    @NamedQuery(name = "CoopAddlContactInfo.findByContactDetail", query = "SELECT c FROM CoopAddlContactInfo c WHERE c.contactDetail = :contactDetail"),
    @NamedQuery(name = "CoopAddlContactInfo.findByAcctno", query = "SELECT c FROM CoopAddlContactInfo c WHERE c.acctno = :acctno"),
    @NamedQuery(name = "CoopAddlContactInfo.findByContactInfoNum", query = "SELECT c FROM CoopAddlContactInfo c WHERE c.contactInfoNum = :contactInfoNum")})
public class CoopAddlContactInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "contact_info_no")
    private Integer contactInfoNo;
    @Column(name = "contact_type")
    private Character contactType;
    @Size(max = 2147483647)
    @Column(name = "contact_detail")
    private String contactDetail;
    @Size(max = 2147483647)
    @Column(name = "acctno")
    private String acctno;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contact_info_num")
    private Integer contactInfoNum;
    @OneToMany(mappedBy = "contactInfoNum")
    private Collection<CoopAddlContactInfoMem> coopAddlContactInfoMemCollection;
    @OneToMany(mappedBy = "contactInfoNum")
    private Collection<CoopAddlContactInfoAssoc> coopAddlContactInfoAssocCollection;

    public CoopAddlContactInfo() {
    }

    public CoopAddlContactInfo(Integer contactInfoNum) {
        this.contactInfoNum = contactInfoNum;
    }

    public Integer getContactInfoNo() {
        return contactInfoNo;
    }

    public void setContactInfoNo(Integer contactInfoNo) {
        this.contactInfoNo = contactInfoNo;
    }

    public Character getContactType() {
        return contactType;
    }

    public void setContactType(Character contactType) {
        this.contactType = contactType;
    }

    public String getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(String contactDetail) {
        this.contactDetail = contactDetail;
    }

    public String getAcctno() {
        return acctno;
    }

    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }

    public Integer getContactInfoNum() {
        return contactInfoNum;
    }

    public void setContactInfoNum(Integer contactInfoNum) {
        this.contactInfoNum = contactInfoNum;
    }

    @XmlTransient
    public Collection<CoopAddlContactInfoMem> getCoopAddlContactInfoMemCollection() {
        return coopAddlContactInfoMemCollection;
    }

    public void setCoopAddlContactInfoMemCollection(Collection<CoopAddlContactInfoMem> coopAddlContactInfoMemCollection) {
        this.coopAddlContactInfoMemCollection = coopAddlContactInfoMemCollection;
    }

    @XmlTransient
    public Collection<CoopAddlContactInfoAssoc> getCoopAddlContactInfoAssocCollection() {
        return coopAddlContactInfoAssocCollection;
    }

    public void setCoopAddlContactInfoAssocCollection(Collection<CoopAddlContactInfoAssoc> coopAddlContactInfoAssocCollection) {
        this.coopAddlContactInfoAssocCollection = coopAddlContactInfoAssocCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactInfoNum != null ? contactInfoNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAddlContactInfo)) {
            return false;
        }
        CoopAddlContactInfo other = (CoopAddlContactInfo) object;
        if ((this.contactInfoNum == null && other.contactInfoNum != null) || (this.contactInfoNum != null && !this.contactInfoNum.equals(other.contactInfoNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAddlContactInfo[ contactInfoNum=" + contactInfoNum + " ]";
    }
    
}
