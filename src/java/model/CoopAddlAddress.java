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
@Table(name = "coop_addl_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopAddlAddress.findAll", query = "SELECT c FROM CoopAddlAddress c"),
    @NamedQuery(name = "CoopAddlAddress.findByAddlAddressNo", query = "SELECT c FROM CoopAddlAddress c WHERE c.addlAddressNo = :addlAddressNo"),
    @NamedQuery(name = "CoopAddlAddress.findByAddressType", query = "SELECT c FROM CoopAddlAddress c WHERE c.addressType = :addressType"),
    @NamedQuery(name = "CoopAddlAddress.findByStreet", query = "SELECT c FROM CoopAddlAddress c WHERE c.street = :street"),
    @NamedQuery(name = "CoopAddlAddress.findByBarangay", query = "SELECT c FROM CoopAddlAddress c WHERE c.barangay = :barangay"),
    @NamedQuery(name = "CoopAddlAddress.findByCityMun", query = "SELECT c FROM CoopAddlAddress c WHERE c.cityMun = :cityMun"),
    @NamedQuery(name = "CoopAddlAddress.findByRegion", query = "SELECT c FROM CoopAddlAddress c WHERE c.region = :region"),
    @NamedQuery(name = "CoopAddlAddress.findByProvince", query = "SELECT c FROM CoopAddlAddress c WHERE c.province = :province"),
    @NamedQuery(name = "CoopAddlAddress.findByZipcode", query = "SELECT c FROM CoopAddlAddress c WHERE c.zipcode = :zipcode"),
    @NamedQuery(name = "CoopAddlAddress.findByAcctno", query = "SELECT c FROM CoopAddlAddress c WHERE c.acctno = :acctno"),
    @NamedQuery(name = "CoopAddlAddress.findByAddlAddressNum", query = "SELECT c FROM CoopAddlAddress c WHERE c.addlAddressNum = :addlAddressNum")})
public class CoopAddlAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "addl_address_no")
    private Integer addlAddressNo;
    @Size(max = 2147483647)
    @Column(name = "address_type")
    private String addressType;
    @Size(max = 2147483647)
    @Column(name = "street")
    private String street;
    @Size(max = 2147483647)
    @Column(name = "barangay")
    private String barangay;
    @Size(max = 2147483647)
    @Column(name = "city_mun")
    private String cityMun;
    @Size(max = 2147483647)
    @Column(name = "region")
    private String region;
    @Size(max = 2147483647)
    @Column(name = "province")
    private String province;
    @Size(max = 2147483647)
    @Column(name = "zipcode")
    private String zipcode;
    @Size(max = 2147483647)
    @Column(name = "acctno")
    private String acctno;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "addl_address_num")
    private Integer addlAddressNum;
    @OneToMany(mappedBy = "addlAddressNum")
    private Collection<CoopAddlAddressAssoc> coopAddlAddressAssocCollection;
    @OneToMany(mappedBy = "addlAddressNum")
    private Collection<CoopAddlAddressMem> coopAddlAddressMemCollection;

    public CoopAddlAddress() {
    }

    public CoopAddlAddress(Integer addlAddressNum) {
        this.addlAddressNum = addlAddressNum;
    }

    public Integer getAddlAddressNo() {
        return addlAddressNo;
    }

    public void setAddlAddressNo(Integer addlAddressNo) {
        this.addlAddressNo = addlAddressNo;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCityMun() {
        return cityMun;
    }

    public void setCityMun(String cityMun) {
        this.cityMun = cityMun;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAcctno() {
        return acctno;
    }

    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }

    public Integer getAddlAddressNum() {
        return addlAddressNum;
    }

    public void setAddlAddressNum(Integer addlAddressNum) {
        this.addlAddressNum = addlAddressNum;
    }

    @XmlTransient
    public Collection<CoopAddlAddressAssoc> getCoopAddlAddressAssocCollection() {
        return coopAddlAddressAssocCollection;
    }

    public void setCoopAddlAddressAssocCollection(Collection<CoopAddlAddressAssoc> coopAddlAddressAssocCollection) {
        this.coopAddlAddressAssocCollection = coopAddlAddressAssocCollection;
    }

    @XmlTransient
    public Collection<CoopAddlAddressMem> getCoopAddlAddressMemCollection() {
        return coopAddlAddressMemCollection;
    }

    public void setCoopAddlAddressMemCollection(Collection<CoopAddlAddressMem> coopAddlAddressMemCollection) {
        this.coopAddlAddressMemCollection = coopAddlAddressMemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addlAddressNum != null ? addlAddressNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopAddlAddress)) {
            return false;
        }
        CoopAddlAddress other = (CoopAddlAddress) object;
        if ((this.addlAddressNum == null && other.addlAddressNum != null) || (this.addlAddressNum != null && !this.addlAddressNum.equals(other.addlAddressNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopAddlAddress[ addlAddressNum=" + addlAddressNum + " ]";
    }
    
}
