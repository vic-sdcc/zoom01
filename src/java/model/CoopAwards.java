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
@Table(name = "coop_awards")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAwards.findAll", query = "SELECT c FROM CoopAwards c"),
    @NamedQuery(name = "CoopAwards.findByAccAwdNo", query = "SELECT c FROM CoopAwards c WHERE c.accAwdNo = :accAwdNo"),
    @NamedQuery(name = "CoopAwards.findByAwardTitle", query = "SELECT c FROM CoopAwards c WHERE c.awardTitle = :awardTitle"),
    @NamedQuery(name = "CoopAwards.findByAwardDetails", query = "SELECT c FROM CoopAwards c WHERE c.awardDetails = :awardDetails"),
    @NamedQuery(name = "CoopAwards.findByAcctno", query = "SELECT c FROM CoopAwards c WHERE c.acctno = :acctno"),
    @NamedQuery(name = "CoopAwards.findByAccAwdNum", query = "SELECT c FROM CoopAwards c WHERE c.accAwdNum = :accAwdNum"),
    @NamedQuery(name = "CoopAwards.findByAwardsDate", query = "SELECT c FROM CoopAwards c WHERE c.awardsDate = :awardsDate")})
public class CoopAwards implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "acc_awd_no")
    private Integer accAwdNo;
    @Size(max = 2147483647)
    @Column(name = "award_title")
    private String awardTitle;
    @Size(max = 2147483647)
    @Column(name = "award_details")
    private String awardDetails;
    @Size(max = 2147483647)
    @Column(name = "acctno")
    private String acctno;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "acc_awd_num")
    private Integer accAwdNum;
    @Size(max = 2147483647)
    @Column(name = "awards_date")
    private String awardsDate;
    @OneToMany(mappedBy = "accAwdNum")
    private Collection<CoopAwardsAssoc> coopAwardsAssocCollection;
    @OneToMany(mappedBy = "accAwdNum")
    private Collection<CoopAwardsMem> coopAwardsMemCollection;

    public CoopAwards() {
    }

    public CoopAwards(Integer accAwdNum) {
        this.accAwdNum = accAwdNum;
    }

    public Integer getAccAwdNo() {
        return accAwdNo;
    }

    public void setAccAwdNo(Integer accAwdNo) {
        this.accAwdNo = accAwdNo;
    }

    public String getAwardTitle() {
        return awardTitle;
    }

    public void setAwardTitle(String awardTitle) {
        this.awardTitle = awardTitle;
    }

    public String getAwardDetails() {
        return awardDetails;
    }

    public void setAwardDetails(String awardDetails) {
        this.awardDetails = awardDetails;
    }

    public String getAcctno() {
        return acctno;
    }

    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }

    public Integer getAccAwdNum() {
        return accAwdNum;
    }

    public void setAccAwdNum(Integer accAwdNum) {
        this.accAwdNum = accAwdNum;
    }

    public String getAwardsDate() {
        return awardsDate;
    }

    public void setAwardsDate(String awardsDate) {
        this.awardsDate = awardsDate;
    }

    @XmlTransient
    public Collection<CoopAwardsAssoc> getCoopAwardsAssocCollection() {
        return coopAwardsAssocCollection;
    }

    public void setCoopAwardsAssocCollection(Collection<CoopAwardsAssoc> coopAwardsAssocCollection) {
        this.coopAwardsAssocCollection = coopAwardsAssocCollection;
    }

    @XmlTransient
    public Collection<CoopAwardsMem> getCoopAwardsMemCollection() {
        return coopAwardsMemCollection;
    }

    public void setCoopAwardsMemCollection(Collection<CoopAwardsMem> coopAwardsMemCollection) {
        this.coopAwardsMemCollection = coopAwardsMemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accAwdNum != null ? accAwdNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAwards)) {
            return false;
        }
        CoopAwards other = (CoopAwards) object;
        if ((this.accAwdNum == null && other.accAwdNum != null) || (this.accAwdNum != null && !this.accAwdNum.equals(other.accAwdNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAwards[ accAwdNum=" + accAwdNum + " ]";
    }
    
}
