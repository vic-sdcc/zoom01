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
@Table(name = "coop_educ_info_assoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopEducInfoAssoc.findAll", query = "SELECT c FROM CoopEducInfoAssoc c"),
    @NamedQuery(name = "CoopEducInfoAssoc.findByEducInfoAssocId", query = "SELECT c FROM CoopEducInfoAssoc c WHERE c.educInfoAssocId = :educInfoAssocId")})
public class CoopEducInfoAssoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "educ_info_assoc_id")
    private Integer educInfoAssocId;
    @JoinColumn(name = "educ_info_num", referencedColumnName = "educ_info_num")
    @ManyToOne
    private CoopEducInfo educInfoNum;
    @JoinColumn(name = "associate_no", referencedColumnName = "associate_no")
    @ManyToOne
    private CoopAssociate associateNo;

    public CoopEducInfoAssoc() {
    }

    public CoopEducInfoAssoc(Integer educInfoAssocId) {
        this.educInfoAssocId = educInfoAssocId;
    }

    public Integer getEducInfoAssocId() {
        return educInfoAssocId;
    }

    public void setEducInfoAssocId(Integer educInfoAssocId) {
        this.educInfoAssocId = educInfoAssocId;
    }

    public CoopEducInfo getEducInfoNum() {
        return educInfoNum;
    }

    public void setEducInfoNum(CoopEducInfo educInfoNum) {
        this.educInfoNum = educInfoNum;
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
        hash += (educInfoAssocId != null ? educInfoAssocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopEducInfoAssoc)) {
            return false;
        }
        CoopEducInfoAssoc other = (CoopEducInfoAssoc) object;
        if ((this.educInfoAssocId == null && other.educInfoAssocId != null) || (this.educInfoAssocId != null && !this.educInfoAssocId.equals(other.educInfoAssocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopEducInfoAssoc[ educInfoAssocId=" + educInfoAssocId + " ]";
    }
    
}
