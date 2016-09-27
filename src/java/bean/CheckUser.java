/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import model.CoopMemberUser;

/**
 *
 * @author mis05
 */
@ManagedBean
@SessionScoped
public class CheckUser implements Serializable {

    /**
     * Creates a new instance of CheckUser
     */
    public CheckUser() {
    }

    private LiferayFacesContext liferayFacesContext;
    private long currentUserId;
    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    private List<CoopMemberUser> memberUserList;
    private DataModel<CoopMemberUser> memberUserModel;
    private boolean showContent;

    /*
     * getter setter
     */
    public LiferayFacesContext getLiferayFacesContext() {
        liferayFacesContext = LiferayFacesContext.getInstance();
        return liferayFacesContext;
    }

    public void setLiferayFacesContext(LiferayFacesContext liferayFacesContext) {
        this.liferayFacesContext = liferayFacesContext;
    }

    public long getCurrentUserId() {
        currentUserId = getLiferayFacesContext().getUser().getUserId();
        return currentUserId;
    }

    public void setCurrentUserId(long currentUserId) {
        this.currentUserId = currentUserId;
    }

    public List<CoopMemberUser> getMemberUserList() {
        memberUserList = entityManagerFactory.createEntityManager().createQuery("SELECT m FROM CoopMemberUser m").getResultList();
        return memberUserList;
    }

    public void setMemberUserList(List<CoopMemberUser> memberUserList) {
        this.memberUserList = memberUserList;
    }

    public DataModel<CoopMemberUser> getMemberUserModel() {
        if (memberUserModel == null) {
            memberUserModel = new ListDataModel<>(getMemberUserList());
        }
        return memberUserModel;
    }

    public void setMemberUserModel(DataModel<CoopMemberUser> memberUserModel) {
        this.memberUserModel = memberUserModel;
    }

    public boolean isShowContent() {
        return showContent;
    }

    public void setShowContent(boolean showContent) {
        this.showContent = showContent;
    }

    /*
     * methods
     */
    public void init() {
        for (int x = 0; x <= getMemberUserModel().getRowCount() - 1; x++) {
            if (getMemberUserList().get(x).getUserId().longValue() == getCurrentUserId()) {
                setShowContent(true);
                break;
            }
        }
    }
}
