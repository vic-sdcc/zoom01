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
@Table(name = "coop_tenure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopTenure.findAll", query = "SELECT c FROM CoopTenure c"),
    @NamedQuery(name = "CoopTenure.findByTenureId", query = "SELECT c FROM CoopTenure c WHERE c.tenureId = :tenureId"),
    @NamedQuery(name = "CoopTenure.findByTenureValue", query = "SELECT c FROM CoopTenure c WHERE c.tenureValue = :tenureValue")})
public class CoopTenure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tenure_id")
    private Integer tenureId;
    @Size(max = 2147483647)
    @Column(name = "tenure_value")
    private String tenureValue;
    @OneToMany(mappedBy = "tenureId")
    private Collection<CoopPositionTenure> coopPositionTenureCollection;

    public CoopTenure() {
    }

    public CoopTenure(Integer tenureId) {
        this.tenureId = tenureId;
    }

    public Integer getTenureId() {
        return tenureId;
    }

    public void setTenureId(Integer tenureId) {
        this.tenureId = tenureId;
    }

    public String getTenureValue() {
        return tenureValue;
    }

    public void setTenureValue(String tenureValue) {
        this.tenureValue = tenureValue;
    }

    @XmlTransient
    public Collection<CoopPositionTenure> getCoopPositionTenureCollection() {
        return coopPositionTenureCollection;
    }

    public void setCoopPositionTenureCollection(Collection<CoopPositionTenure> coopPositionTenureCollection) {
        this.coopPositionTenureCollection = coopPositionTenureCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tenureId != null ? tenureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopTenure)) {
            return false;
        }
        CoopTenure other = (CoopTenure) object;
        if ((this.tenureId == null && other.tenureId != null) || (this.tenureId != null && !this.tenureId.equals(other.tenureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopTenure[ tenureId=" + tenureId + " ]";
    }
    
}
