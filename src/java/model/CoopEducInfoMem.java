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
@Table(name = "coop_educ_info_mem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopEducInfoMem.findAll", query = "SELECT c FROM CoopEducInfoMem c"),
    @NamedQuery(name = "CoopEducInfoMem.findByEducInfoMemId", query = "SELECT c FROM CoopEducInfoMem c WHERE c.educInfoMemId = :educInfoMemId")})
public class CoopEducInfoMem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "educ_info_mem_id")
    private Integer educInfoMemId;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember memNo;
    @JoinColumn(name = "educ_info_num", referencedColumnName = "educ_info_num")
    @ManyToOne
    private CoopEducInfo educInfoNum;

    public CoopEducInfoMem() {
    }

    public CoopEducInfoMem(Integer educInfoMemId) {
        this.educInfoMemId = educInfoMemId;
    }

    public Integer getEducInfoMemId() {
        return educInfoMemId;
    }

    public void setEducInfoMemId(Integer educInfoMemId) {
        this.educInfoMemId = educInfoMemId;
    }

    public CoopMember getMemNo() {
        return memNo;
    }

    public void setMemNo(CoopMember memNo) {
        this.memNo = memNo;
    }

    public CoopEducInfo getEducInfoNum() {
        return educInfoNum;
    }

    public void setEducInfoNum(CoopEducInfo educInfoNum) {
        this.educInfoNum = educInfoNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (educInfoMemId != null ? educInfoMemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopEducInfoMem)) {
            return false;
        }
        CoopEducInfoMem other = (CoopEducInfoMem) object;
        if ((this.educInfoMemId == null && other.educInfoMemId != null) || (this.educInfoMemId != null && !this.educInfoMemId.equals(other.educInfoMemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopEducInfoMem[ educInfoMemId=" + educInfoMemId + " ]";
    }
    
}
