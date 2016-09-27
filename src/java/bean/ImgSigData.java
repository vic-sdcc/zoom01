/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopImgSig;
import model.CoopImgSigAssoc;
import model.CoopImgSigMem;
import org.primefaces.event.CaptureEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import service.CoopImgSigAssocFacadeREST;
import service.CoopImgSigFacadeREST;
import service.CoopImgSigMemFacadeREST;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class ImgSigData implements Serializable {

    /**
     * Creates a new instance of ImgSigData
     */
    public ImgSigData() {
    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    @EJB
    private CoopImgSigFacadeREST imgSigREST;
    @EJB
    private CoopImgSigMemFacadeREST imgSigMemREST;
    @EJB
    private CoopImgSigAssocFacadeREST imgSigAssocREST;
    private CoopImgSig imgSig;
    private CoopImgSigMem imgSigMem;
    private CoopImgSigAssoc imgSigAssoc;
    @ManagedProperty(value = "#{memberData}")
    private MemberData memberData;
    @ManagedProperty(value = "#{associateData}")
    private AssociateData associateData;
    @ManagedProperty(value = "#{membershipType}")
    private MembershipType membershipType;
    @ManagedProperty(value = "#{mode}")
    private Mode mode;
    private byte[] tempPic;
    private StreamedContent streamedContent1, streamedContent2;

    /*
     * getter setter
     */
    public CoopImgSig getImgSig() {
        if (imgSig == null) {
            imgSig = new CoopImgSig();
        }
        return imgSig;
    }

    public void setImgSig(CoopImgSig imgSig) {
        this.imgSig = imgSig;
    }

    public CoopImgSigMem getImgSigMem() {
        if (imgSigMem == null) {
            imgSigMem = new CoopImgSigMem();
        }
        return imgSigMem;
    }

    public void setImgSigMem(CoopImgSigMem imgSigMem) {
        this.imgSigMem = imgSigMem;
    }

    public CoopImgSigAssoc getImgSigAssoc() {
        if (imgSigAssoc == null) {
            imgSigAssoc = new CoopImgSigAssoc();
        }
        return imgSigAssoc;
    }

    public void setImgSigAssoc(CoopImgSigAssoc imgSigAssoc) {
        this.imgSigAssoc = imgSigAssoc;
    }

    public MemberData getMemberData() {
        if (memberData == null) {
            memberData = new MemberData();
        }
        return memberData;
    }

    public void setMemberData(MemberData memberData) {
        this.memberData = memberData;
    }

    public AssociateData getAssociateData() {
        if (associateData == null) {
            associateData = new AssociateData();
        }
        return associateData;
    }

    public void setAssociateData(AssociateData associateData) {
        this.associateData = associateData;
    }

    public MembershipType getMembershipType() {
        if (membershipType == null) {
            membershipType = new MembershipType();
        }
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public Mode getMode() {
        if (mode == null) {
            mode = new Mode();
        }
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public byte[] getTempPic() {
        return tempPic;
    }

    public void setTempPic(byte[] tempPic) {
        this.tempPic = tempPic;
    }

    public StreamedContent getStreamedContent1() {
        return streamedContent1;
    }

    public void setStreamedContent1(StreamedContent streamedContent1) {
        this.streamedContent1 = streamedContent1;
    }

    public StreamedContent getStreamedContent2() {
        return streamedContent2;
    }

    public void setStreamedContent2(StreamedContent streamedContent2) {
        this.streamedContent2 = streamedContent2;
    }

    /*
     * methods
     */
    public void beanclear() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("imgSigData", null);
    }

    public void oncapture(CaptureEvent captureEvent) {
        setTempPic(captureEvent.getData());
        setStreamedContent2(new DefaultStreamedContent(new ByteArrayInputStream(getTempPic()), "image/png"));
    }

    public void imgSigLoad() {
        getImgSig().setImg(getTempPic());
        getImgSig().setSig(getImgSig().getSig());
        imgSigREST.create(getImgSig());
        if (getMembershipType().getTypeValue().equals(0)) {
            getImgSigMem().setMemNo(getMemberData().getMember());
            getImgSigMem().setImgSigId(getImgSig());
            imgSigMemREST.create(getImgSigMem());
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getImgSigAssoc().setAssociateNo(getAssociateData().getAssociate());
            getImgSigAssoc().setImgSigId(getImgSig());
            imgSigAssocREST.create(getImgSigAssoc());
        }
    }

    public void editImgSig() {
        if (getTempPic() == null) {
            getImgSig().setImg(getImgSig().getImg());
        }
        if (getTempPic() != null) {
            getImgSig().setImg(getTempPic());
        }
        getImgSig().setSig(getImgSig().getSig());
        imgSigREST.edit(getImgSig());
        if (getMembershipType().getTypeValue().equals(0)) {
            getImgSigMem().setMemNo(getMemberData().getMember());
            getImgSigMem().setImgSigId(getImgSig());
            imgSigMemREST.edit(getImgSigMem());
        }
        if (getMembershipType().getTypeValue().equals(1)) {
            getImgSigAssoc().setAssociateNo(getAssociateData().getAssociate());
            getImgSigAssoc().setImgSigId(getImgSig());
            imgSigAssocREST.edit(getImgSigAssoc());
        }
    }

    public void selectedImgSigLoad() {
        String t1 = "", t2 = "", endString = "";
        CoopImgSig am = null;
        CoopImgSigMem m = null;
        CoopImgSigAssoc a = null;
        try {
            if (getMembershipType().getTypeValue().equals(0)) {
                endString = "JOIN t2.imgSigId t1 "
                        + "WHERE t2.memNo.memNo = '" + getMemberData().getMember().getMemNo() + "'";
                t1 = "SELECT t1 FROM CoopImgSigMem t2 " + endString;
                t2 = "SELECT t2 FROM CoopImgSigMem t2 " + endString;
                am = (CoopImgSig) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                m = (CoopImgSigMem) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                setImgSig(am);
                setImgSigMem(m);
                setStreamedContent1(new DefaultStreamedContent(new ByteArrayInputStream(imgSigREST.find(am.getImgSigId()).getImg()), "image/png"));
            }
        } catch (Exception e) {
            System.out.print("ImgSigData.java selectedImgSigLoad()-1 " + e);
        }
        try {
            if (getMembershipType().getTypeValue().equals(1)) {
                endString = "JOIN t2.imgSigId t1 "
                        + "WHERE t2.associateNo.associateNo = '" + getAssociateData().getAssociate().getAssociateNo() + "'";
                t1 = "SELECT t1 FROM CoopImgSigAssoc t2 " + endString;
                t2 = "SELECT t2 FROM CoopImgSigAssoc t2 " + endString;
                am = (CoopImgSig) entityManagerFactory.createEntityManager().createQuery(t1).getResultList().get(0);
                a = (CoopImgSigAssoc) entityManagerFactory.createEntityManager().createQuery(t2).getResultList().get(0);
                setImgSig(am);
                setImgSigAssoc(a);
                setStreamedContent1(new DefaultStreamedContent(new ByteArrayInputStream(imgSigREST.find(am.getImgSigId()).getImg()), "image/png"));
            }
        } catch (Exception e) {
            System.out.print("ImgSigData.java selectedImgSigLoad()-2 " + e);
        }
    }

}
