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
@Table(name = "coop_awards_mem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAwardsMem.findAll", query = "SELECT c FROM CoopAwardsMem c"),
    @NamedQuery(name = "CoopAwardsMem.findByAwardsMemId", query = "SELECT c FROM CoopAwardsMem c WHERE c.awardsMemId = :awardsMemId")})
public class CoopAwardsMem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "awards_mem_id")
    private Integer awardsMemId;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember memNo;
    @JoinColumn(name = "acc_awd_num", referencedColumnName = "acc_awd_num")
    @ManyToOne
    private CoopAwards accAwdNum;

    public CoopAwardsMem() {
    }

    public CoopAwardsMem(Integer awardsMemId) {
        this.awardsMemId = awardsMemId;
    }

    public Integer getAwardsMemId() {
        return awardsMemId;
    }

    public void setAwardsMemId(Integer awardsMemId) {
        this.awardsMemId = awardsMemId;
    }

    public CoopMember getMemNo() {
        return memNo;
    }

    public void setMemNo(CoopMember memNo) {
        this.memNo = memNo;
    }

    public CoopAwards getAccAwdNum() {
        return accAwdNum;
    }

    public void setAccAwdNum(CoopAwards accAwdNum) {
        this.accAwdNum = accAwdNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (awardsMemId != null ? awardsMemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAwardsMem)) {
            return false;
        }
        CoopAwardsMem other = (CoopAwardsMem) object;
        if ((this.awardsMemId == null && other.awardsMemId != null) || (this.awardsMemId != null && !this.awardsMemId.equals(other.awardsMemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAwardsMem[ awardsMemId=" + awardsMemId + " ]";
    }
    
}
