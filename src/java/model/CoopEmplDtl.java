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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "coop_empl_dtl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopEmplDtl.findAll", query = "SELECT c FROM CoopEmplDtl c"),
    @NamedQuery(name = "CoopEmplDtl.findByEmploymentStat", query = "SELECT c FROM CoopEmplDtl c WHERE c.employmentStat = :employmentStat"),
    @NamedQuery(name = "CoopEmplDtl.findByRankPos", query = "SELECT c FROM CoopEmplDtl c WHERE c.rankPos = :rankPos"),
    @NamedQuery(name = "CoopEmplDtl.findByCompBracket", query = "SELECT c FROM CoopEmplDtl c WHERE c.compBracket = :compBracket"),
    @NamedQuery(name = "CoopEmplDtl.findByEmploymentDate", query = "SELECT c FROM CoopEmplDtl c WHERE c.employmentDate = :employmentDate"),
    @NamedQuery(name = "CoopEmplDtl.findByWorkplaceEmailAdd", query = "SELECT c FROM CoopEmplDtl c WHERE c.workplaceEmailAdd = :workplaceEmailAdd"),
    @NamedQuery(name = "CoopEmplDtl.findByEmplBizName", query = "SELECT c FROM CoopEmplDtl c WHERE c.emplBizName = :emplBizName"),
    @NamedQuery(name = "CoopEmplDtl.findByEmplContactNo", query = "SELECT c FROM CoopEmplDtl c WHERE c.emplContactNo = :emplContactNo"),
    @NamedQuery(name = "CoopEmplDtl.findByEmplDtlNo", query = "SELECT c FROM CoopEmplDtl c WHERE c.emplDtlNo = :emplDtlNo"),
    @NamedQuery(name = "CoopEmplDtl.findByEmplStreet", query = "SELECT c FROM CoopEmplDtl c WHERE c.emplStreet = :emplStreet"),
    @NamedQuery(name = "CoopEmplDtl.findByEmplBarangay", query = "SELECT c FROM CoopEmplDtl c WHERE c.emplBarangay = :emplBarangay"),
    @NamedQuery(name = "CoopEmplDtl.findByEmplCityMun", query = "SELECT c FROM CoopEmplDtl c WHERE c.emplCityMun = :emplCityMun"),
    @NamedQuery(name = "CoopEmplDtl.findByEmplEmail", query = "SELECT c FROM CoopEmplDtl c WHERE c.emplEmail = :emplEmail"),
    @NamedQuery(name = "CoopEmplDtl.findByAcctno", query = "SELECT c FROM CoopEmplDtl c WHERE c.acctno = :acctno"),
    @NamedQuery(name = "CoopEmplDtl.findByEDate", query = "SELECT c FROM CoopEmplDtl c WHERE c.eDate = :eDate"),
    @NamedQuery(name = "CoopEmplDtl.findByEmploymentEndDate", query = "SELECT c FROM CoopEmplDtl c WHERE c.employmentEndDate = :employmentEndDate"),
    @NamedQuery(name = "CoopEmplDtl.findByEmplDtlNum", query = "SELECT c FROM CoopEmplDtl c WHERE c.emplDtlNum = :emplDtlNum")})
public class CoopEmplDtl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "employment_stat")
    private String employmentStat;
    @Size(max = 2147483647)
    @Column(name = "rank_pos")
    private String rankPos;
    @Size(max = 2147483647)
    @Column(name = "comp_bracket")
    private String compBracket;
    @Column(name = "employment_date")
    @Temporal(TemporalType.DATE)
    private Date employmentDate;
    @Size(max = 2147483647)
    @Column(name = "workplace_email_add")
    private String workplaceEmailAdd;
    @Size(max = 2147483647)
    @Column(name = "empl_biz_name")
    private String emplBizName;
    @Size(max = 2147483647)
    @Column(name = "empl_contact_no")
    private String emplContactNo;
    @Column(name = "empl_dtl_no")
    private Integer emplDtlNo;
    @Size(max = 2147483647)
    @Column(name = "empl_street")
    private String emplStreet;
    @Size(max = 2147483647)
    @Column(name = "empl_barangay")
    private String emplBarangay;
    @Size(max = 2147483647)
    @Column(name = "empl_city_mun")
    private String emplCityMun;
    @Size(max = 2147483647)
    @Column(name = "empl_email")
    private String emplEmail;
    @Size(max = 2147483647)
    @Column(name = "acctno")
    private String acctno;
    @Size(max = 2147483647)
    @Column(name = "e_date")
    private String eDate;
    @Column(name = "employment_end_date")
    @Temporal(TemporalType.DATE)
    private Date employmentEndDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "empl_dtl_num")
    private Integer emplDtlNum;
    @JoinColumn(name = "empl_rank_pos", referencedColumnName = "occupation_code")
    @ManyToOne
    private CoopOccupation emplRankPos;
    @OneToMany(mappedBy = "emplDtlNum")
    private Collection<CoopEmplDtlMem> coopEmplDtlMemCollection;
    @OneToMany(mappedBy = "emplDtlNum")
    private Collection<CoopEmplDtlAssoc> coopEmplDtlAssocCollection;

    public CoopEmplDtl() {
    }

    public CoopEmplDtl(Integer emplDtlNum) {
        this.emplDtlNum = emplDtlNum;
    }

    public String getEmploymentStat() {
        return employmentStat;
    }

    public void setEmploymentStat(String employmentStat) {
        this.employmentStat = employmentStat;
    }

    public String getRankPos() {
        return rankPos;
    }

    public void setRankPos(String rankPos) {
        this.rankPos = rankPos;
    }

    public String getCompBracket() {
        return compBracket;
    }

    public void setCompBracket(String compBracket) {
        this.compBracket = compBracket;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getWorkplaceEmailAdd() {
        return workplaceEmailAdd;
    }

    public void setWorkplaceEmailAdd(String workplaceEmailAdd) {
        this.workplaceEmailAdd = workplaceEmailAdd;
    }

    public String getEmplBizName() {
        return emplBizName;
    }

    public void setEmplBizName(String emplBizName) {
        this.emplBizName = emplBizName;
    }

    public String getEmplContactNo() {
        return emplContactNo;
    }

    public void setEmplContactNo(String emplContactNo) {
        this.emplContactNo = emplContactNo;
    }

    public Integer getEmplDtlNo() {
        return emplDtlNo;
    }

    public void setEmplDtlNo(Integer emplDtlNo) {
        this.emplDtlNo = emplDtlNo;
    }

    public String getEmplStreet() {
        return emplStreet;
    }

    public void setEmplStreet(String emplStreet) {
        this.emplStreet = emplStreet;
    }

    public String getEmplBarangay() {
        return emplBarangay;
    }

    public void setEmplBarangay(String emplBarangay) {
        this.emplBarangay = emplBarangay;
    }

    public String getEmplCityMun() {
        return emplCityMun;
    }

    public void setEmplCityMun(String emplCityMun) {
        this.emplCityMun = emplCityMun;
    }

    public String getEmplEmail() {
        return emplEmail;
    }

    public void setEmplEmail(String emplEmail) {
        this.emplEmail = emplEmail;
    }

    public String getAcctno() {
        return acctno;
    }

    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }

    public String getEDate() {
        return eDate;
    }

    public void setEDate(String eDate) {
        this.eDate = eDate;
    }

    public Date getEmploymentEndDate() {
        return employmentEndDate;
    }

    public void setEmploymentEndDate(Date employmentEndDate) {
        this.employmentEndDate = employmentEndDate;
    }

    public Integer getEmplDtlNum() {
        return emplDtlNum;
    }

    public void setEmplDtlNum(Integer emplDtlNum) {
        this.emplDtlNum = emplDtlNum;
    }

    public CoopOccupation getEmplRankPos() {
        return emplRankPos;
    }

    public void setEmplRankPos(CoopOccupation emplRankPos) {
        this.emplRankPos = emplRankPos;
    }

    @XmlTransient
    public Collection<CoopEmplDtlMem> getCoopEmplDtlMemCollection() {
        return coopEmplDtlMemCollection;
    }

    public void setCoopEmplDtlMemCollection(Collection<CoopEmplDtlMem> coopEmplDtlMemCollection) {
        this.coopEmplDtlMemCollection = coopEmplDtlMemCollection;
    }

    @XmlTransient
    public Collection<CoopEmplDtlAssoc> getCoopEmplDtlAssocCollection() {
        return coopEmplDtlAssocCollection;
    }

    public void setCoopEmplDtlAssocCollection(Collection<CoopEmplDtlAssoc> coopEmplDtlAssocCollection) {
        this.coopEmplDtlAssocCollection = coopEmplDtlAssocCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emplDtlNum != null ? emplDtlNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopEmplDtl)) {
            return false;
        }
        CoopEmplDtl other = (CoopEmplDtl) object;
        if ((this.emplDtlNum == null && other.emplDtlNum != null) || (this.emplDtlNum != null && !this.emplDtlNum.equals(other.emplDtlNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopEmplDtl[ emplDtlNum=" + emplDtlNum + " ]";
    }
    
}
