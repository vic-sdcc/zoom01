/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_position_tenure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopPositionTenure.findAll", query = "SELECT c FROM CoopPositionTenure c"),
    @NamedQuery(name = "CoopPositionTenure.findByPositionTenureId", query = "SELECT c FROM CoopPositionTenure c WHERE c.positionTenureId = :positionTenureId"),
    @NamedQuery(name = "CoopPositionTenure.findByTermStart", query = "SELECT c FROM CoopPositionTenure c WHERE c.termStart = :termStart"),
    @NamedQuery(name = "CoopPositionTenure.findByTermEnd", query = "SELECT c FROM CoopPositionTenure c WHERE c.termEnd = :termEnd")})
public class CoopPositionTenure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "position_tenure_id")
    private Integer positionTenureId;
    @Column(name = "term_start")
    @Temporal(TemporalType.DATE)
    private Date termStart;
    @Column(name = "term_end")
    @Temporal(TemporalType.DATE)
    private Date termEnd;
    @JoinColumn(name = "tenure_id", referencedColumnName = "tenure_id")
    @ManyToOne
    private CoopTenure tenureId;
    @JoinColumn(name = "mem_ou_pos_id", referencedColumnName = "mem_ou_pos_id")
    @ManyToOne
    private CoopMemOuPos memOuPosId;

    public CoopPositionTenure() {
    }

    public CoopPositionTenure(Integer positionTenureId) {
        this.positionTenureId = positionTenureId;
    }

    public Integer getPositionTenureId() {
        return positionTenureId;
    }

    public void setPositionTenureId(Integer positionTenureId) {
        this.positionTenureId = positionTenureId;
    }

    public Date getTermStart() {
        return termStart;
    }

    public void setTermStart(Date termStart) {
        this.termStart = termStart;
    }

    public Date getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(Date termEnd) {
        this.termEnd = termEnd;
    }

    public CoopTenure getTenureId() {
        return tenureId;
    }

    public void setTenureId(CoopTenure tenureId) {
        this.tenureId = tenureId;
    }

    public CoopMemOuPos getMemOuPosId() {
        return memOuPosId;
    }

    public void setMemOuPosId(CoopMemOuPos memOuPosId) {
        this.memOuPosId = memOuPosId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionTenureId != null ? positionTenureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopPositionTenure)) {
            return false;
        }
        CoopPositionTenure other = (CoopPositionTenure) object;
        if ((this.positionTenureId == null && other.positionTenureId != null) || (this.positionTenureId != null && !this.positionTenureId.equals(other.positionTenureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopPositionTenure[ positionTenureId=" + positionTenureId + " ]";
    }
    
}
