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
@Table(name = "coop_addl_address_assoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAddlAddressAssoc.findAll", query = "SELECT c FROM CoopAddlAddressAssoc c"),
    @NamedQuery(name = "CoopAddlAddressAssoc.findByAddlAddressAssocId", query = "SELECT c FROM CoopAddlAddressAssoc c WHERE c.addlAddressAssocId = :addlAddressAssocId")})
public class CoopAddlAddressAssoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "addl_address_assoc_id")
    private Integer addlAddressAssocId;
    @JoinColumn(name = "associate_no", referencedColumnName = "associate_no")
    @ManyToOne
    private CoopAssociate associateNo;
    @JoinColumn(name = "addl_address_num", referencedColumnName = "addl_address_num")
    @ManyToOne
    private CoopAddlAddress addlAddressNum;

    public CoopAddlAddressAssoc() {
    }

    public CoopAddlAddressAssoc(Integer addlAddressAssocId) {
        this.addlAddressAssocId = addlAddressAssocId;
    }

    public Integer getAddlAddressAssocId() {
        return addlAddressAssocId;
    }

    public void setAddlAddressAssocId(Integer addlAddressAssocId) {
        this.addlAddressAssocId = addlAddressAssocId;
    }

    public CoopAssociate getAssociateNo() {
        return associateNo;
    }

    public void setAssociateNo(CoopAssociate associateNo) {
        this.associateNo = associateNo;
    }

    public CoopAddlAddress getAddlAddressNum() {
        return addlAddressNum;
    }

    public void setAddlAddressNum(CoopAddlAddress addlAddressNum) {
        this.addlAddressNum = addlAddressNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addlAddressAssocId != null ? addlAddressAssocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAddlAddressAssoc)) {
            return false;
        }
        CoopAddlAddressAssoc other = (CoopAddlAddressAssoc) object;
        if ((this.addlAddressAssocId == null && other.addlAddressAssocId != null) || (this.addlAddressAssocId != null && !this.addlAddressAssocId.equals(other.addlAddressAssocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAddlAddressAssoc[ addlAddressAssocId=" + addlAddressAssocId + " ]";
    }
    
}
