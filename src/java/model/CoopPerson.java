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
@Table(name = "coop_person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopPerson.findAll", query = "SELECT c FROM CoopPerson c"),
    @NamedQuery(name = "CoopPerson.findByPersonId", query = "SELECT c FROM CoopPerson c WHERE c.personId = :personId")})
public class CoopPerson implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_id")
    private Integer personId;
    @OneToMany(mappedBy = "personId")
    private Collection<CoopPersonMem> coopPersonMemCollection;
    @OneToMany(mappedBy = "personId")
    private Collection<CoopPersonAssociate> coopPersonAssociateCollection;
    @OneToMany(mappedBy = "personId")
    private Collection<CoopPersonNonMember> coopPersonNonMemberCollection;
    @OneToMany(mappedBy = "husband")
    private Collection<CoopFamily> coopFamilyCollection;
    @OneToMany(mappedBy = "wife")
    private Collection<CoopFamily> coopFamilyCollection1;
    @JoinColumn(name = "person_type_id", referencedColumnName = "person_type_id")
    @ManyToOne
    private CoopPersonType personTypeId;
    @OneToMany(mappedBy = "personId")
    private Collection<CoopChild> coopChildCollection;

    public CoopPerson() {
    }

    public CoopPerson(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @XmlTransient
    public Collection<CoopPersonMem> getCoopPersonMemCollection() {
        return coopPersonMemCollection;
    }

    public void setCoopPersonMemCollection(Collection<CoopPersonMem> coopPersonMemCollection) {
        this.coopPersonMemCollection = coopPersonMemCollection;
    }

    @XmlTransient
    public Collection<CoopPersonAssociate> getCoopPersonAssociateCollection() {
        return coopPersonAssociateCollection;
    }

    public void setCoopPersonAssociateCollection(Collection<CoopPersonAssociate> coopPersonAssociateCollection) {
        this.coopPersonAssociateCollection = coopPersonAssociateCollection;
    }

    @XmlTransient
    public Collection<CoopPersonNonMember> getCoopPersonNonMemberCollection() {
        return coopPersonNonMemberCollection;
    }

    public void setCoopPersonNonMemberCollection(Collection<CoopPersonNonMember> coopPersonNonMemberCollection) {
        this.coopPersonNonMemberCollection = coopPersonNonMemberCollection;
    }

    @XmlTransient
    public Collection<CoopFamily> getCoopFamilyCollection() {
        return coopFamilyCollection;
    }

    public void setCoopFamilyCollection(Collection<CoopFamily> coopFamilyCollection) {
        this.coopFamilyCollection = coopFamilyCollection;
    }

    @XmlTransient
    public Collection<CoopFamily> getCoopFamilyCollection1() {
        return coopFamilyCollection1;
    }

    public void setCoopFamilyCollection1(Collection<CoopFamily> coopFamilyCollection1) {
        this.coopFamilyCollection1 = coopFamilyCollection1;
    }

    public CoopPersonType getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(CoopPersonType personTypeId) {
        this.personTypeId = personTypeId;
    }

    @XmlTransient
    public Collection<CoopChild> getCoopChildCollection() {
        return coopChildCollection;
    }

    public void setCoopChildCollection(Collection<CoopChild> coopChildCollection) {
        this.coopChildCollection = coopChildCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopPerson)) {
            return false;
        }
        CoopPerson other = (CoopPerson) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopPerson[ personId=" + personId + " ]";
    }
    
}
