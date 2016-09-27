/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mis05
 */
@Entity
@Table(name = "coop_biz_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopBizInfo.findAll", query = "SELECT c FROM CoopBizInfo c"),
    @NamedQuery(name = "CoopBizInfo.findByBizInfoNo", query = "SELECT c FROM CoopBizInfo c WHERE c.bizInfoNo = :bizInfoNo"),
    @NamedQuery(name = "CoopBizInfo.findByBizType", query = "SELECT c FROM CoopBizInfo c WHERE c.bizType = :bizType"),
    @NamedQuery(name = "CoopBizInfo.findByBizIncRange", query = "SELECT c FROM CoopBizInfo c WHERE c.bizIncRange = :bizIncRange"),
    @NamedQuery(name = "CoopBizInfo.findByBizNature", query = "SELECT c FROM CoopBizInfo c WHERE c.bizNature = :bizNature"),
    @NamedQuery(name = "CoopBizInfo.findByBizName", query = "SELECT c FROM CoopBizInfo c WHERE c.bizName = :bizName"),
    @NamedQuery(name = "CoopBizInfo.findByDateEstablished", query = "SELECT c FROM CoopBizInfo c WHERE c.dateEstablished = :dateEstablished"),
    @NamedQuery(name = "CoopBizInfo.findByBizStreet", query = "SELECT c FROM CoopBizInfo c WHERE c.bizStreet = :bizStreet"),
    @NamedQuery(name = "CoopBizInfo.findByBizBarangay", query = "SELECT c FROM CoopBizInfo c WHERE c.bizBarangay = :bizBarangay"),
    @NamedQuery(name = "CoopBizInfo.findByBizCityMun", query = "SELECT c FROM CoopBizInfo c WHERE c.bizCityMun = :bizCityMun"),
    @NamedQuery(name = "CoopBizInfo.findByBizContactNo", query = "SELECT c FROM CoopBizInfo c WHERE c.bizContactNo = :bizContactNo"),
    @NamedQuery(name = "CoopBizInfo.findByAcctno", query = "SELECT c FROM CoopBizInfo c WHERE c.acctno = :acctno"),
    @NamedQuery(name = "CoopBizInfo.findByBizProvince", query = "SELECT c FROM CoopBizInfo c WHERE c.bizProvince = :bizProvince"),
    @NamedQuery(name = "CoopBizInfo.findByBizRegion", query = "SELECT c FROM CoopBizInfo c WHERE c.bizRegion = :bizRegion"),
    @NamedQuery(name = "CoopBizInfo.findByRem", query = "SELECT c FROM CoopBizInfo c WHERE c.rem = :rem"),
    @NamedQuery(name = "CoopBizInfo.findByDateDissolved", query = "SELECT c FROM CoopBizInfo c WHERE c.dateDissolved = :dateDissolved"),
    @NamedQuery(name = "CoopBizInfo.findByBizInfoNum", query = "SELECT c FROM CoopBizInfo c WHERE c.bizInfoNum = :bizInfoNum")})
public class CoopBizInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "biz_info_no")
    private Integer bizInfoNo;
    @Size(max = 2147483647)
    @Column(name = "biz_type")
    private String bizType;
    @Size(max = 2147483647)
    @Column(name = "biz_inc_range")
    private String bizIncRange;
    @Size(max = 2147483647)
    @Column(name = "biz_nature")
    private String bizNature;
    @Size(max = 2147483647)
    @Column(name = "biz_name")
    private String bizName;
    @Column(name = "date_established")
    @Temporal(TemporalType.DATE)
    private Date dateEstablished;
    @Size(max = 2147483647)
    @Column(name = "biz_street")
    private String bizStreet;
    @Size(max = 2147483647)
    @Column(name = "biz_barangay")
    private String bizBarangay;
    @Size(max = 2147483647)
    @Column(name = "biz_city_mun")
    private String bizCityMun;
    @Size(max = 2147483647)
    @Column(name = "biz_contact_no")
    private String bizContactNo;
    @Size(max = 2147483647)
    @Column(name = "acctno")
    private String acctno;
    @Size(max = 2147483647)
    @Column(name = "biz_province")
    private String bizProvince;
    @Size(max = 2147483647)
    @Column(name = "biz_region")
    private String bizRegion;
    @Size(max = 2147483647)
    @Column(name = "rem")
    private String rem;
    @Column(name = "date_dissolved")
    @Temporal(TemporalType.DATE)
    private Date dateDissolved;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "biz_info_num")
    private Integer bizInfoNum;
    @OneToMany(mappedBy = "bizInfoNum")
    private Collection<CoopBizInfoMem> coopBizInfoMemCollection;
    @OneToMany(mappedBy = "bizInfoNum")
    private Collection<CoopBizInfoAssoc> coopBizInfoAssocCollection;

    public CoopBizInfo() {
    }

    public CoopBizInfo(Integer bizInfoNum) {
        this.bizInfoNum = bizInfoNum;
    }

    public Integer getBizInfoNo() {
        return bizInfoNo;
    }

    public void setBizInfoNo(Integer bizInfoNo) {
        this.bizInfoNo = bizInfoNo;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizIncRange() {
        return bizIncRange;
    }

    public void setBizIncRange(String bizIncRange) {
        this.bizIncRange = bizIncRange;
    }

    public String getBizNature() {
        return bizNature;
    }

    public void setBizNature(String bizNature) {
        this.bizNature = bizNature;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public Date getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(Date dateEstablished) {
        this.dateEstablished = dateEstablished;
    }

    public String getBizStreet() {
        return bizStreet;
    }

    public void setBizStreet(String bizStreet) {
        this.bizStreet = bizStreet;
    }

    public String getBizBarangay() {
        return bizBarangay;
    }

    public void setBizBarangay(String bizBarangay) {
        this.bizBarangay = bizBarangay;
    }

    public String getBizCityMun() {
        return bizCityMun;
    }

    public void setBizCityMun(String bizCityMun) {
        this.bizCityMun = bizCityMun;
    }

    public String getBizContactNo() {
        return bizContactNo;
    }

    public void setBizContactNo(String bizContactNo) {
        this.bizContactNo = bizContactNo;
    }

    public String getAcctno() {
        return acctno;
    }

    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }

    public String getBizProvince() {
        return bizProvince;
    }

    public void setBizProvince(String bizProvince) {
        this.bizProvince = bizProvince;
    }

    public String getBizRegion() {
        return bizRegion;
    }

    public void setBizRegion(String bizRegion) {
        this.bizRegion = bizRegion;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public Date getDateDissolved() {
        return dateDissolved;
    }

    public void setDateDissolved(Date dateDissolved) {
        this.dateDissolved = dateDissolved;
    }

    public Integer getBizInfoNum() {
        return bizInfoNum;
    }

    public void setBizInfoNum(Integer bizInfoNum) {
        this.bizInfoNum = bizInfoNum;
    }

    @XmlTransient
    public Collection<CoopBizInfoMem> getCoopBizInfoMemCollection() {
        return coopBizInfoMemCollection;
    }

    public void setCoopBizInfoMemCollection(Collection<CoopBizInfoMem> coopBizInfoMemCollection) {
        this.coopBizInfoMemCollection = coopBizInfoMemCollection;
    }

    @XmlTransient
    public Collection<CoopBizInfoAssoc> getCoopBizInfoAssocCollection() {
        return coopBizInfoAssocCollection;
    }

    public void setCoopBizInfoAssocCollection(Collection<CoopBizInfoAssoc> coopBizInfoAssocCollection) {
        this.coopBizInfoAssocCollection = coopBizInfoAssocCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bizInfoNum != null ? bizInfoNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopBizInfo)) {
            return false;
        }
        CoopBizInfo other = (CoopBizInfo) object;
        if ((this.bizInfoNum == null && other.bizInfoNum != null) || (this.bizInfoNum != null && !this.bizInfoNum.equals(other.bizInfoNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopBizInfo[ bizInfoNum=" + bizInfoNum + " ]";
    }
    
}
