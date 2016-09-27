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
 * @author vic
 */
@Entity
@Table(name = "dlfileentry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dlfileentry.findAll", query = "SELECT d FROM Dlfileentry d"),
    @NamedQuery(name = "Dlfileentry.findByUuid", query = "SELECT d FROM Dlfileentry d WHERE d.uuid = :uuid"),
    @NamedQuery(name = "Dlfileentry.findByFileentryid", query = "SELECT d FROM Dlfileentry d WHERE d.fileentryid = :fileentryid"),
    @NamedQuery(name = "Dlfileentry.findByGroupid", query = "SELECT d FROM Dlfileentry d WHERE d.groupid = :groupid"),
    @NamedQuery(name = "Dlfileentry.findByCompanyid", query = "SELECT d FROM Dlfileentry d WHERE d.companyid = :companyid"),
    @NamedQuery(name = "Dlfileentry.findByUserid", query = "SELECT d FROM Dlfileentry d WHERE d.userid = :userid"),
    @NamedQuery(name = "Dlfileentry.findByUsername", query = "SELECT d FROM Dlfileentry d WHERE d.username = :username"),
    @NamedQuery(name = "Dlfileentry.findByCreatedate", query = "SELECT d FROM Dlfileentry d WHERE d.createdate = :createdate"),
    @NamedQuery(name = "Dlfileentry.findByModifieddate", query = "SELECT d FROM Dlfileentry d WHERE d.modifieddate = :modifieddate"),
    @NamedQuery(name = "Dlfileentry.findByClassnameid", query = "SELECT d FROM Dlfileentry d WHERE d.classnameid = :classnameid"),
    @NamedQuery(name = "Dlfileentry.findByClasspk", query = "SELECT d FROM Dlfileentry d WHERE d.classpk = :classpk"),
    @NamedQuery(name = "Dlfileentry.findByRepositoryid", query = "SELECT d FROM Dlfileentry d WHERE d.repositoryid = :repositoryid"),
    @NamedQuery(name = "Dlfileentry.findByFolderid", query = "SELECT d FROM Dlfileentry d WHERE d.folderid = :folderid"),
    @NamedQuery(name = "Dlfileentry.findByTreepath", query = "SELECT d FROM Dlfileentry d WHERE d.treepath = :treepath"),
    @NamedQuery(name = "Dlfileentry.findByName", query = "SELECT d FROM Dlfileentry d WHERE d.name = :name"),
    @NamedQuery(name = "Dlfileentry.findByExtension", query = "SELECT d FROM Dlfileentry d WHERE d.extension = :extension"),
    @NamedQuery(name = "Dlfileentry.findByMimetype", query = "SELECT d FROM Dlfileentry d WHERE d.mimetype = :mimetype"),
    @NamedQuery(name = "Dlfileentry.findByTitle", query = "SELECT d FROM Dlfileentry d WHERE d.title = :title"),
    @NamedQuery(name = "Dlfileentry.findByDescription", query = "SELECT d FROM Dlfileentry d WHERE d.description = :description"),
    @NamedQuery(name = "Dlfileentry.findByExtrasettings", query = "SELECT d FROM Dlfileentry d WHERE d.extrasettings = :extrasettings"),
    @NamedQuery(name = "Dlfileentry.findByFileentrytypeid", query = "SELECT d FROM Dlfileentry d WHERE d.fileentrytypeid = :fileentrytypeid"),
    @NamedQuery(name = "Dlfileentry.findByVersion", query = "SELECT d FROM Dlfileentry d WHERE d.version = :version"),
    @NamedQuery(name = "Dlfileentry.findBySize", query = "SELECT d FROM Dlfileentry d WHERE d.size = :size"),
    @NamedQuery(name = "Dlfileentry.findByReadcount", query = "SELECT d FROM Dlfileentry d WHERE d.readcount = :readcount"),
    @NamedQuery(name = "Dlfileentry.findBySmallimageid", query = "SELECT d FROM Dlfileentry d WHERE d.smallimageid = :smallimageid"),
    @NamedQuery(name = "Dlfileentry.findByLargeimageid", query = "SELECT d FROM Dlfileentry d WHERE d.largeimageid = :largeimageid"),
    @NamedQuery(name = "Dlfileentry.findByCustom1imageid", query = "SELECT d FROM Dlfileentry d WHERE d.custom1imageid = :custom1imageid"),
    @NamedQuery(name = "Dlfileentry.findByCustom2imageid", query = "SELECT d FROM Dlfileentry d WHERE d.custom2imageid = :custom2imageid"),
    @NamedQuery(name = "Dlfileentry.findByManualcheckinrequired", query = "SELECT d FROM Dlfileentry d WHERE d.manualcheckinrequired = :manualcheckinrequired")})
public class Dlfileentry implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 75)
    @Column(name = "uuid_")
    private String uuid;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "fileentryid")
    private Long fileentryid;
    @Column(name = "groupid")
    private BigInteger groupid;
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
    @Column(name = "classnameid")
    private BigInteger classnameid;
    @Column(name = "classpk")
    private BigInteger classpk;
    @Column(name = "repositoryid")
    private BigInteger repositoryid;
    @Column(name = "folderid")
    private BigInteger folderid;
    @Size(max = 2147483647)
    @Column(name = "treepath")
    private String treepath;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 75)
    @Column(name = "extension")
    private String extension;
    @Size(max = 75)
    @Column(name = "mimetype")
    private String mimetype;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Size(max = 2147483647)
    @Column(name = "extrasettings")
    private String extrasettings;
    @Column(name = "fileentrytypeid")
    private BigInteger fileentrytypeid;
    @Size(max = 75)
    @Column(name = "version")
    private String version;
    @Column(name = "size_")
    private BigInteger size;
    @Column(name = "readcount")
    private Integer readcount;
    @Column(name = "smallimageid")
    private BigInteger smallimageid;
    @Column(name = "largeimageid")
    private BigInteger largeimageid;
    @Column(name = "custom1imageid")
    private BigInteger custom1imageid;
    @Column(name = "custom2imageid")
    private BigInteger custom2imageid;
    @Column(name = "manualcheckinrequired")
    private Boolean manualcheckinrequired;

    public Dlfileentry() {
    }

    public Dlfileentry(Long fileentryid) {
        this.fileentryid = fileentryid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getFileentryid() {
        return fileentryid;
    }

    public void setFileentryid(Long fileentryid) {
        this.fileentryid = fileentryid;
    }

    public BigInteger getGroupid() {
        return groupid;
    }

    public void setGroupid(BigInteger groupid) {
        this.groupid = groupid;
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

    public BigInteger getClassnameid() {
        return classnameid;
    }

    public void setClassnameid(BigInteger classnameid) {
        this.classnameid = classnameid;
    }

    public BigInteger getClasspk() {
        return classpk;
    }

    public void setClasspk(BigInteger classpk) {
        this.classpk = classpk;
    }

    public BigInteger getRepositoryid() {
        return repositoryid;
    }

    public void setRepositoryid(BigInteger repositoryid) {
        this.repositoryid = repositoryid;
    }

    public BigInteger getFolderid() {
        return folderid;
    }

    public void setFolderid(BigInteger folderid) {
        this.folderid = folderid;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtrasettings() {
        return extrasettings;
    }

    public void setExtrasettings(String extrasettings) {
        this.extrasettings = extrasettings;
    }

    public BigInteger getFileentrytypeid() {
        return fileentrytypeid;
    }

    public void setFileentrytypeid(BigInteger fileentrytypeid) {
        this.fileentrytypeid = fileentrytypeid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    public Integer getReadcount() {
        return readcount;
    }

    public void setReadcount(Integer readcount) {
        this.readcount = readcount;
    }

    public BigInteger getSmallimageid() {
        return smallimageid;
    }

    public void setSmallimageid(BigInteger smallimageid) {
        this.smallimageid = smallimageid;
    }

    public BigInteger getLargeimageid() {
        return largeimageid;
    }

    public void setLargeimageid(BigInteger largeimageid) {
        this.largeimageid = largeimageid;
    }

    public BigInteger getCustom1imageid() {
        return custom1imageid;
    }

    public void setCustom1imageid(BigInteger custom1imageid) {
        this.custom1imageid = custom1imageid;
    }

    public BigInteger getCustom2imageid() {
        return custom2imageid;
    }

    public void setCustom2imageid(BigInteger custom2imageid) {
        this.custom2imageid = custom2imageid;
    }

    public Boolean getManualcheckinrequired() {
        return manualcheckinrequired;
    }

    public void setManualcheckinrequired(Boolean manualcheckinrequired) {
        this.manualcheckinrequired = manualcheckinrequired;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fileentryid != null ? fileentryid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dlfileentry)) {
            return false;
        }
        Dlfileentry other = (Dlfileentry) object;
        if ((this.fileentryid == null && other.fileentryid != null) || (this.fileentryid != null && !this.fileentryid.equals(other.fileentryid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Dlfileentry[ fileentryid=" + fileentryid + " ]";
    }
    
}
