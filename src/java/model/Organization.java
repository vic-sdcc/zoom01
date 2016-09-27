/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "organization_")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o"),
    @NamedQuery(name = "Organization.findByUuid", query = "SELECT o FROM Organization o WHERE o.uuid = :uuid"),
    @NamedQuery(name = "Organization.findByOrganizationid", query = "SELECT o FROM Organization o WHERE o.organizationid = :organizationid"),
    @NamedQuery(name = "Organization.findByCompanyid", query = "SELECT o FROM Organization o WHERE o.companyid = :companyid"),
    @NamedQuery(name = "Organization.findByUserid", query = "SELECT o FROM Organization o WHERE o.userid = :userid"),
    @NamedQuery(name = "Organization.findByUsername", query = "SELECT o FROM Organization o WHERE o.username = :username"),
    @NamedQuery(name = "Organization.findByCreatedate", query = "SELECT o FROM Organization o WHERE o.createdate = :createdate"),
    @NamedQuery(name = "Organization.findByModifieddate", query = "SELECT o FROM Organization o WHERE o.modifieddate = :modifieddate"),
    @NamedQuery(name = "Organization.findByParentorganizationid", query = "SELECT o FROM Organization o WHERE o.parentorganizationid = :parentorganizationid"),
    @NamedQuery(name = "Organization.findByTreepath", query = "SELECT o FROM Organization o WHERE o.treepath = :treepath"),
    @NamedQuery(name = "Organization.findByName", query = "SELECT o FROM Organization o WHERE o.name = :name"),
    @NamedQuery(name = "Organization.findByType", query = "SELECT o FROM Organization o WHERE o.type = :type"),
    @NamedQuery(name = "Organization.findByRecursable", query = "SELECT o FROM Organization o WHERE o.recursable = :recursable"),
    @NamedQuery(name = "Organization.findByRegionid", query = "SELECT o FROM Organization o WHERE o.regionid = :regionid"),
    @NamedQuery(name = "Organization.findByCountryid", query = "SELECT o FROM Organization o WHERE o.countryid = :countryid"),
    @NamedQuery(name = "Organization.findByStatusid", query = "SELECT o FROM Organization o WHERE o.statusid = :statusid"),
    @NamedQuery(name = "Organization.findByComments", query = "SELECT o FROM Organization o WHERE o.comments = :comments")})
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 75)
    @Column(name = "uuid_")
    private String uuid;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "organizationid")
    private Long organizationid;
    @Column(name = "companyid")
    private BigInteger companyid;
    @Column(name = "userid")
    private BigInteger userid;
    @Size(max = 75)
    @Column(name = "username")
    private String username;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "modifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    @Column(name = "parentorganizationid")
    private BigInteger parentorganizationid;
    @Size(max = 2147483647)
    @Column(name = "treepath")
    private String treepath;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 75)
    @Column(name = "type_")
    private String type;
    @Column(name = "recursable")
    private Boolean recursable;
    @Column(name = "regionid")
    private BigInteger regionid;
    @Column(name = "countryid")
    private BigInteger countryid;
    @Column(name = "statusid")
    private Integer statusid;
    @Size(max = 2147483647)
    @Column(name = "comments")
    private String comments;

    public Organization() {
    }

    public Organization(Long organizationid) {
        this.organizationid = organizationid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getOrganizationid() {
        return organizationid;
    }

    public void setOrganizationid(Long organizationid) {
        this.organizationid = organizationid;
    }

    public BigInteger getCompanyid() {
        return companyid;
    }

    public void setCompanyid(BigInteger companyid) {
        this.companyid = companyid;
    }

    public BigInteger getUserid() {
        return userid;
    }

    public void setUserid(BigInteger userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public BigInteger getParentorganizationid() {
        return parentorganizationid;
    }

    public void setParentorganizationid(BigInteger parentorganizationid) {
        this.parentorganizationid = parentorganizationid;
    }

    public String getTreepath() {
        return treepath;
    }

    public void setTreepath(String treepath) {
        this.treepath = treepath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRecursable() {
        return recursable;
    }

    public void setRecursable(Boolean recursable) {
        this.recursable = recursable;
    }

    public BigInteger getRegionid() {
        return regionid;
    }

    public void setRegionid(BigInteger regionid) {
        this.regionid = regionid;
    }

    public BigInteger getCountryid() {
        return countryid;
    }

    public void setCountryid(BigInteger countryid) {
        this.countryid = countryid;
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizationid != null ? organizationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organization)) {
            return false;
        }
        Organization other = (Organization) object;
        if ((this.organizationid == null && other.organizationid != null) || (this.organizationid != null && !this.organizationid.equals(other.organizationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Organization[ organizationid=" + organizationid + " ]";
    }
    
}
