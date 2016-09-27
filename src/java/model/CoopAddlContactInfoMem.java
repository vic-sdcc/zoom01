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
@Table(name = "coop_addl_contact_info_mem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAddlContactInfoMem.findAll", query = "SELECT c FROM CoopAddlContactInfoMem c"),
    @NamedQuery(name = "CoopAddlContactInfoMem.findByAddlContactInfoMemId", query = "SELECT c FROM CoopAddlContactInfoMem c WHERE c.addlContactInfoMemId = :addlContactInfoMemId")})
public class CoopAddlContactInfoMem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "addl_contact_info_mem_id")
    private Integer addlContactInfoMemId;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember memNo;
    @JoinColumn(name = "contact_info_num", referencedColumnName = "contact_info_num")
    @ManyToOne
    private CoopAddlContactInfo contactInfoNum;

    public CoopAddlContactInfoMem() {
    }

    public CoopAddlContactInfoMem(Integer addlContactInfoMemId) {
        this.addlContactInfoMemId = addlContactInfoMemId;
    }

    public Integer getAddlContactInfoMemId() {
        return addlContactInfoMemId;
    }

    public void setAddlContactInfoMemId(Integer addlContactInfoMemId) {
        this.addlContactInfoMemId = addlContactInfoMemId;
    }

    public CoopMember getMemNo() {
        return memNo;
    }

    public void setMemNo(CoopMember memNo) {
        this.memNo = memNo;
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
        hash += (addlContactInfoMemId != null ? addlContactInfoMemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAddlContactInfoMem)) {
            return false;
        }
        CoopAddlContactInfoMem other = (CoopAddlContactInfoMem) object;
        if ((this.addlContactInfoMemId == null && other.addlContactInfoMemId != null) || (this.addlContactInfoMemId != null && !this.addlContactInfoMemId.equals(other.addlContactInfoMemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAddlContactInfoMem[ addlContactInfoMemId=" + addlContactInfoMemId + " ]";
    }
    
}
