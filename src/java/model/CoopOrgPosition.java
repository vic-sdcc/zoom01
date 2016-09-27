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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_org_position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopOrgPosition.findAll", query = "SELECT c FROM CoopOrgPosition c"),
    @NamedQuery(name = "CoopOrgPosition.findByOrgPosId", query = "SELECT c FROM CoopOrgPosition c WHERE c.orgPosId = :orgPosId")})
public class CoopOrgPosition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "org_pos_id")
    private Integer orgPosId;
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    @ManyToOne
    private CoopPosition positionId;
    @JoinColumn(name = "ou_code", referencedColumnName = "ou_code")
    @ManyToOne
    private CoopOrgUnit ouCode;
    @OneToMany(mappedBy = "orgPosId")
    private Collection<CoopMemOuPos> coopMemOuPosCollection;

    public CoopOrgPosition() {
    }

    public CoopOrgPosition(Integer orgPosId) {
        this.orgPosId = orgPosId;
    }

    public Integer getOrgPosId() {
        return orgPosId;
    }

    public void setOrgPosId(Integer orgPosId) {
        this.orgPosId = orgPosId;
    }

    public CoopPosition getPositionId() {
        return positionId;
    }

    public void setPositionId(CoopPosition positionId) {
        this.positionId = positionId;
    }

    public CoopOrgUnit getOuCode() {
        return ouCode;
    }

    public void setOuCode(CoopOrgUnit ouCode) {
        this.ouCode = ouCode;
    }

    @XmlTransient
    public Collection<CoopMemOuPos> getCoopMemOuPosCollection() {
        return coopMemOuPosCollection;
    }

    public void setCoopMemOuPosCollection(Collection<CoopMemOuPos> coopMemOuPosCollection) {
        this.coopMemOuPosCollection = coopMemOuPosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orgPosId != null ? orgPosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopOrgPosition)) {
            return false;
        }
        CoopOrgPosition other = (CoopOrgPosition) object;
        if ((this.orgPosId == null && other.orgPosId != null) || (this.orgPosId != null && !this.orgPosId.equals(other.orgPosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopOrgPosition[ orgPosId=" + orgPosId + " ]";
    }
    
}
