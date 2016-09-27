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
@Table(name = "coop_awards_assoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAwardsAssoc.findAll", query = "SELECT c FROM CoopAwardsAssoc c"),
    @NamedQuery(name = "CoopAwardsAssoc.findByAwardsAssocId", query = "SELECT c FROM CoopAwardsAssoc c WHERE c.awardsAssocId = :awardsAssocId")})
public class CoopAwardsAssoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "awards_assoc_id")
    private Integer awardsAssocId;
    @JoinColumn(name = "acc_awd_num", referencedColumnName = "acc_awd_num")
    @ManyToOne
    private CoopAwards accAwdNum;
    @JoinColumn(name = "associate_no", referencedColumnName = "associate_no")
    @ManyToOne
    private CoopAssociate associateNo;

    public CoopAwardsAssoc() {
    }

    public CoopAwardsAssoc(Integer awardsAssocId) {
        this.awardsAssocId = awardsAssocId;
    }

    public Integer getAwardsAssocId() {
        return awardsAssocId;
    }

    public void setAwardsAssocId(Integer awardsAssocId) {
        this.awardsAssocId = awardsAssocId;
    }

    public CoopAwards getAccAwdNum() {
        return accAwdNum;
    }

    public void setAccAwdNum(CoopAwards accAwdNum) {
        this.accAwdNum = accAwdNum;
    }

    public CoopAssociate getAssociateNo() {
        return associateNo;
    }

    public void setAssociateNo(CoopAssociate associateNo) {
        this.associateNo = associateNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (awardsAssocId != null ? awardsAssocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAwardsAssoc)) {
            return false;
        }
        CoopAwardsAssoc other = (CoopAwardsAssoc) object;
        if ((this.awardsAssocId == null && other.awardsAssocId != null) || (this.awardsAssocId != null && !this.awardsAssocId.equals(other.awardsAssocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAwardsAssoc[ awardsAssocId=" + awardsAssocId + " ]";
    }
    
}
