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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "coop_mem_ou_pos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopMemOuPos.findAll", query = "SELECT c FROM CoopMemOuPos c"),
    @NamedQuery(name = "CoopMemOuPos.findByAcctno", query = "SELECT c FROM CoopMemOuPos c WHERE c.acctno = :acctno"),
    @NamedQuery(name = "CoopMemOuPos.findByMemOuPosId", query = "SELECT c FROM CoopMemOuPos c WHERE c.memOuPosId = :memOuPosId")})
public class CoopMemOuPos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "acctno")
    private String acctno;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mem_ou_pos_id")
    private Integer memOuPosId;
    @OneToMany(mappedBy = "memOuPosId")
    private Collection<CoopPositionTenure> coopPositionTenureCollection;
    @JoinColumn(name = "org_pos_id", referencedColumnName = "org_pos_id")
    @ManyToOne
    private CoopOrgPosition orgPosId;
    @JoinColumn(name = "mem_no", referencedColumnName = "mem_no")
    @ManyToOne
    private CoopMember memNo;

    public CoopMemOuPos() {
    }

    public CoopMemOuPos(Integer memOuPosId) {
        this.memOuPosId = memOuPosId;
    }

    public String getAcctno() {
        return acctno;
    }

    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }

    public Integer getMemOuPosId() {
        return memOuPosId;
    }

    public void setMemOuPosId(Integer memOuPosId) {
        this.memOuPosId = memOuPosId;
    }

    @XmlTransient
    public Collection<CoopPositionTenure> getCoopPositionTenureCollection() {
        return coopPositionTenureCollection;
    }

    public void setCoopPositionTenureCollection(Collection<CoopPositionTenure> coopPositionTenureCollection) {
        this.coopPositionTenureCollection = coopPositionTenureCollection;
    }

    public CoopOrgPosition getOrgPosId() {
        return orgPosId;
    }

    public void setOrgPosId(CoopOrgPosition orgPosId) {
        this.orgPosId = orgPosId;
    }

    public CoopMember getMemNo() {
        return memNo;
    }

    public void setMemNo(CoopMember memNo) {
        this.memNo = memNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memOuPosId != null ? memOuPosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopMemOuPos)) {
            return false;
        }
        CoopMemOuPos other = (CoopMemOuPos) object;
        if ((this.memOuPosId == null && other.memOuPosId != null) || (this.memOuPosId != null && !this.memOuPosId.equals(other.memOuPosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopMemOuPos[ memOuPosId=" + memOuPosId + " ]";
    }
    
}
