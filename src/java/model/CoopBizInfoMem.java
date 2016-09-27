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
@Table(name = "coop_biz_info_mem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopBizInfoMem.findAll", query = "SELECT c FROM CoopBizInfoMem c"),
    @NamedQuery(name = "CoopBizInfoMem.findByBizInfoMemId", query = "SELECT c FROM CoopBizInfoMem c WHERE c.bizInfoMemId = :bizInfoMemId")})
public class CoopBizInfoMem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "biz_info_mem_id")
    private Integer bizInfoMemId;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember memNo;
    @JoinColumn(name = "biz_info_num", referencedColumnName = "biz_info_num")
    @ManyToOne
    private CoopBizInfo bizInfoNum;

    public CoopBizInfoMem() {
    }

    public CoopBizInfoMem(Integer bizInfoMemId) {
        this.bizInfoMemId = bizInfoMemId;
    }

    public Integer getBizInfoMemId() {
        return bizInfoMemId;
    }

    public void setBizInfoMemId(Integer bizInfoMemId) {
        this.bizInfoMemId = bizInfoMemId;
    }

    public CoopMember getMemNo() {
        return memNo;
    }

    public void setMemNo(CoopMember memNo) {
        this.memNo = memNo;
    }

    public CoopBizInfo getBizInfoNum() {
        return bizInfoNum;
    }

    public void setBizInfoNum(CoopBizInfo bizInfoNum) {
        this.bizInfoNum = bizInfoNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bizInfoMemId != null ? bizInfoMemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopBizInfoMem)) {
            return false;
        }
        CoopBizInfoMem other = (CoopBizInfoMem) object;
        if ((this.bizInfoMemId == null && other.bizInfoMemId != null) || (this.bizInfoMemId != null && !this.bizInfoMemId.equals(other.bizInfoMemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopBizInfoMem[ bizInfoMemId=" + bizInfoMemId + " ]";
    }
    
}
