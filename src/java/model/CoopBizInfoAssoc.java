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
@Table(name = "coop_biz_info_assoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopBizInfoAssoc.findAll", query = "SELECT c FROM CoopBizInfoAssoc c"),
    @NamedQuery(name = "CoopBizInfoAssoc.findByBizInfoAssocId", query = "SELECT c FROM CoopBizInfoAssoc c WHERE c.bizInfoAssocId = :bizInfoAssocId")})
public class CoopBizInfoAssoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "biz_info_assoc_id")
    private Integer bizInfoAssocId;
    @JoinColumn(name = "biz_info_num", referencedColumnName = "biz_info_num")
    @ManyToOne
    private CoopBizInfo bizInfoNum;
    @JoinColumn(name = "associate_no", referencedColumnName = "associate_no")
    @ManyToOne
    private CoopAssociate associateNo;

    public CoopBizInfoAssoc() {
    }

    public CoopBizInfoAssoc(Integer bizInfoAssocId) {
        this.bizInfoAssocId = bizInfoAssocId;
    }

    public Integer getBizInfoAssocId() {
        return bizInfoAssocId;
    }

    public void setBizInfoAssocId(Integer bizInfoAssocId) {
        this.bizInfoAssocId = bizInfoAssocId;
    }

    public CoopBizInfo getBizInfoNum() {
        return bizInfoNum;
    }

    public void setBizInfoNum(CoopBizInfo bizInfoNum) {
        this.bizInfoNum = bizInfoNum;
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
        hash += (bizInfoAssocId != null ? bizInfoAssocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopBizInfoAssoc)) {
            return false;
        }
        CoopBizInfoAssoc other = (CoopBizInfoAssoc) object;
        if ((this.bizInfoAssocId == null && other.bizInfoAssocId != null) || (this.bizInfoAssocId != null && !this.bizInfoAssocId.equals(other.bizInfoAssocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopBizInfoAssoc[ bizInfoAssocId=" + bizInfoAssocId + " ]";
    }
    
}
