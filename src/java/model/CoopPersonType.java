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
@Table(name = "coop_person_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopPersonType.findAll", query = "SELECT c FROM CoopPersonType c"),
    @NamedQuery(name = "CoopPersonType.findByPersonTypeId", query = "SELECT c FROM CoopPersonType c WHERE c.personTypeId = :personTypeId"),
    @NamedQuery(name = "CoopPersonType.findByPersonType", query = "SELECT c FROM CoopPersonType c WHERE c.personType = :personType")})
public class CoopPersonType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_type_id")
    private Integer personTypeId;
    @Size(max = 2147483647)
    @Column(name = "person_type")
    private String personType;
    @OneToMany(mappedBy = "personTypeId")
    private Collection<CoopPerson> coopPersonCollection;

    public CoopPersonType() {
    }

    public CoopPersonType(Integer personTypeId) {
        this.personTypeId = personTypeId;
    }

    public Integer getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(Integer personTypeId) {
        this.personTypeId = personTypeId;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    @XmlTransient
    public Collection<CoopPerson> getCoopPersonCollection() {
        return coopPersonCollection;
    }

    public void setCoopPersonCollection(Collection<CoopPerson> coopPersonCollection) {
        this.coopPersonCollection = coopPersonCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personTypeId != null ? personTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopPersonType)) {
            return false;
        }
        CoopPersonType other = (CoopPersonType) object;
        if ((this.personTypeId == null && other.personTypeId != null) || (this.personTypeId != null && !this.personTypeId.equals(other.personTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopPersonType[ personTypeId=" + personTypeId + " ]";
    }
    
}
