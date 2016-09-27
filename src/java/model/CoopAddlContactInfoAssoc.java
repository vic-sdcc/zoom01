/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_addl_contact_info_assoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAddlContactInfoAssoc.findAll", query = "SELECT c FROM CoopAddlContactInfoAssoc c"),
    @NamedQuery(name = "CoopAddlContactInfoAssoc.findByAddlContactInfoAssocId", query = "SELECT c FROM CoopAddlContactInfoAssoc c WHERE c.addlContactInfoAssocId = :addlContactInfoAssocId")})
public class CoopAddlContactInfoAssoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "addl_contact_info_assoc_id")
    private Integer addlContactInfoAssocId;
    @JoinColumn(name = "associate_no", referencedColumnName = "associate_no")
    @ManyToOne
    private CoopAssociate associateNo;
    @JoinColumn(name = "contact_info_num", referencedColumnName = "contact_info_num")
    @ManyToOne
    private CoopAddlContactInfo contactInfoNum;

    public CoopAddlContactInfoAssoc() {
    }

    public CoopAddlContactInfoAssoc(Integer addlContactInfoAssocId) {
        this.addlContactInfoAssocId = addlContactInfoAssocId;
    }

    public Integer getAddlContactInfoAssocId() {
        return addlContactInfoAssocId;
    }

    public void setAddlContactInfoAssocId(Integer addlContactInfoAssocId) {
        this.addlContactInfoAssocId = addlContactInfoAssocId;
    }

    public CoopAssociate getAssociateNo() {
        return associateNo;
    }

    public void setAssociateNo(CoopAssociate associateNo) {
        this.associateNo = associateNo;
    }

    public CoopAddlContactInfo getContactInfoNum() {
        return contactInfoNum;
    }

    public void setContactInfoNum(CoopAddlContactInfo contactInfoNum) {
        this.contactInfoNum = contactInfoNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addlContactInfoAssocId != null ? addlContactInfoAssocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAddlContactInfoAssoc)) {
            return false;
        }
        CoopAddlContactInfoAssoc other = (CoopAddlContactInfoAssoc) object;
        if ((this.addlContactInfoAssocId == null && other.addlContactInfoAssocId != null) || (this.addlContactInfoAssocId != null && !this.addlContactInfoAssocId.equals(other.addlContactInfoAssocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAddlContactInfoAssoc[ addlContactInfoAssocId=" + addlContactInfoAssocId + " ]";
    }
    
}
