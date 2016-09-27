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
@Table(name = "coop_position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopPosition.findAll", query = "SELECT c FROM CoopPosition c"),
    @NamedQuery(name = "CoopPosition.findByPositionName", query = "SELECT c FROM CoopPosition c WHERE c.positionName = :positionName"),
    @NamedQuery(name = "CoopPosition.findByPositionId", query = "SELECT c FROM CoopPosition c WHERE c.positionId = :positionId")})
public class CoopPosition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "position_name")
    private String positionName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "position_id")
    private Integer positionId;
    @OneToMany(mappedBy = "positionId")
    private Collection<CoopOrgPosition> coopOrgPositionCollection;

    public CoopPosition() {
    }

    public CoopPosition(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    @XmlTransient
    public Collection<CoopOrgPosition> getCoopOrgPositionCollection() {
        return coopOrgPositionCollection;
    }

    public void setCoopOrgPositionCollection(Collection<CoopOrgPosition> coopOrgPositionCollection) {
        this.coopOrgPositionCollection = coopOrgPositionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionId != null ? positionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopPosition)) {
            return false;
        }
        CoopPosition other = (CoopPosition) object;
        if ((this.positionId == null && other.positionId != null) || (this.positionId != null && !this.positionId.equals(other.positionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopPosition[ positionId=" + positionId + " ]";
    }
    
}
