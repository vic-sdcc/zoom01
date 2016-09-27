/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.portlet.RenderRequest;
import model.CoopAssociate;
import model.CoopEducInfo;
import model.CoopMember;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.portletfaces.liferay.faces.context.LiferayFacesContext;

/**
 *
 * @author vic
 */
@ManagedBean
@SessionScoped
public class exportData implements Serializable {

    /**
     * Creates a new instance of exportData
     */
    public exportData() {
    }

    private String filename;
    private List<Boolean> exportColumns;
    private DataConvert dConvert;
    Integer boolSize = 20;
    private returnObject rObjct;
    private dateFormat dFormat;
    @PersistenceUnit
    EntityManagerFactory emf;

    public void createFolder(RenderRequest renderRequest, ThemeDisplay themeDisplay, String name, String description) {
        long repositoryId = themeDisplay.getScopeGroupId();//repository id is same as groupId
        long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID; // or 0
        try {
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            ServiceContext serviceContext = liferayFacesContext.getServiceContext();
            DLAppServiceUtil.addFolder(repositoryId, parentFolderId, name, description, serviceContext);
        } catch (PortalException | SystemException e1) {
            System.out.println(e1);
        }
    }

    public void createDLFolder(RenderRequest renderRequest, ThemeDisplay themeDisplay, String folderName, String description) {
        long userId = themeDisplay.getUserId();
        long groupId = themeDisplay.getScopeGroupId();
        long repositoryId = themeDisplay.getScopeGroupId();//repository id is same as groupId
        boolean mountPoint = false;                       // mountPoint which is a boolean specifying whether the folder is a facade for mounting a third-party repository
        long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID; // or 0
        boolean hidden = false; // true if you want to hidden the folder 
        try {
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            ServiceContext serviceContext = liferayFacesContext.getServiceContext();
            DLFolderLocalServiceUtil.addFolder(userId, groupId, repositoryId, mountPoint, parentFolderId, folderName, description, hidden, serviceContext);
        } catch (PortalException | SystemException e1) {
            System.out.println(e1);
        }
    }

    public void fileUploadByDL(String fileStr, String folderName, ThemeDisplay themeDisplay, RenderRequest renderRequest) {
        File file = new File(fileStr);
        long userId = themeDisplay.getUserId();
        long groupId = themeDisplay.getScopeGroupId();
        long repositoryId = themeDisplay.getScopeGroupId();
        String mimeType = MimeTypesUtil.getContentType(file);
        String title = file.getName();
        String description = "This file is added via programatically";
        String changeLog = "Change log ver 1.0";
        String Msg = "";
        String Summary = "";
        FacesMessage.Severity Type = null;
        try {
            DLFolder dlFolder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(), 0, folderName);
            long fileEntryTypeId = dlFolder.getDefaultFileEntryTypeId();
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            ServiceContext serviceContext = liferayFacesContext.getServiceContext();
            InputStream is = new FileInputStream(file);

            Integer dlExist = (Integer) emf.createEntityManager().createQuery("SELECT c FROM Dlfileentry c "
                    + "WHERE c.title ='" + file.getName() + "' "
                    + "AND c.folderid ='" + dlFolder.getFolderId() + "'").getResultList().size();

            if (dlExist < 1) {
                DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.addFileEntry(userId, groupId, repositoryId, dlFolder.getFolderId(),
                        file.getName(), mimeType, title, description, changeLog, fileEntryTypeId, null, file, is, file.length(), serviceContext);
                //Change mode of Draft to Approved
                //DLFileEntryLocalServiceUtil.updateFileEntry(userId, dlFileEntry.getFileEntryId(), file.getName(),
                //        MimeTypesUtil.getContentType(file), title, description, "Draft to save", true,
                //        dlFileEntry.getFileEntryTypeId(), null, file, null, file.length(), serviceContext);
                System.out.println("Your excel file has been generated!");
                Type = FacesMessage.SEVERITY_INFO;
                Summary = "Successful";
                Msg = "Your excel file has been generated.";
            } else {
                Type = FacesMessage.SEVERITY_WARN;
                Summary = "Warning";
                Msg = "File name already exist.";
            }
            FacesMessage message = new FacesMessage(Type, Summary, Msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (PortalException | SystemException | FileNotFoundException e) {
            System.out.println("Exception" + e);
        }
    }

    public void memberList(List<CoopMember> memberData, memberFilterData filterData) {
        String fName = getFilename();
        ThemeDisplay themeDisplay = LiferayFacesContext.getInstance().getThemeDisplay();
        createFolder(null, themeDisplay, "Membership export", "Description");

        int x = 0, y = 0;
        if (getFilename() == null || getFilename().length() == 0) {
            setFilename("Filtered Data List(" + new Date() + ")");
        }

        try {
            setFilename("" + getFilename() + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);

            rowhead.createCell(y++).setCellValue("Member No.");
            rowhead.createCell(y++).setCellValue("Full Name");
            rowhead.createCell(y++).setCellValue("PT Number");

            if (getExportColumns().get(0)) {
                rowhead.createCell(y++).setCellValue("Address");
            }
            if (getExportColumns().get(1)) {
                rowhead.createCell(y++).setCellValue("Gender");
            }
            if (getExportColumns().get(2)) {
                rowhead.createCell(y++).setCellValue("Civil Status");
            }
            if (getExportColumns().get(3)) {
                rowhead.createCell(y++).setCellValue("Birthdate");
            }
            if (getExportColumns().get(4)) {
                rowhead.createCell(y++).setCellValue("TIN");
            }
            if (getExportColumns().get(5)) {
                rowhead.createCell(y++).setCellValue("Pag-Ibig");
            }
            if (getExportColumns().get(6)) {
                rowhead.createCell(y++).setCellValue("Philhealth");
            }
            if (getExportColumns().get(7)) {
                rowhead.createCell(y++).setCellValue("SSS");
            }
            if (getExportColumns().get(8)) {
                rowhead.createCell(y++).setCellValue("GSIS");
            }
            if (getExportColumns().get(9)) {
                rowhead.createCell(y++).setCellValue("Membership Date");
            }
            if (getExportColumns().get(10)) {
                rowhead.createCell(y++).setCellValue("Membership Status");
            }
            if (getExportColumns().get(11)) {
                rowhead.createCell(y++).setCellValue("Skill");
            }
            if (getExportColumns().get(12)) {
                rowhead.createCell(y++).setCellValue("Compensation Bracket");
            }
            if (getExportColumns().get(13)) {
                rowhead.createCell(y++).setCellValue("Educational Attainment");
            }
            if (getExportColumns().get(14)) {
                rowhead.createCell(y++).setCellValue("Rank Position");
            }

            for (int i = 0; i != memberData.size(); i++) {
                HSSFRow row = sheet.createRow((short) i + 1);
                row.createCell(x++).setCellValue(memberData.get(i).getMemNo());
                row.createCell(x++).setCellValue(getrObjct().fullname(memberData.get(i).getPPrefix(), memberData.get(i).getLastName(), memberData.get(i).getFirstName(), memberData.get(i).getMiddleName(), memberData.get(i).getSuffix()));
                try {
                    row.createCell(x++).setCellValue(memberData.get(i).getOuCode().getOuShortName());
                } catch (Exception e) {
                    row.createCell(x++).setCellValue("");
                }
                if (getExportColumns().get(0)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getStreet() + " " + memberData.get(i).getBarangay() + " " + memberData.get(i).getCityMun() + " " + (memberData.get(i).getProvince() != null ? memberData.get(i).getProvince() : ""));
                }
                if (getExportColumns().get(1)) {
                    row.createCell(x++).setCellValue(getdConvert().genderConv(memberData.get(i).getGender()));
                }
                if (getExportColumns().get(2)) {
                    row.createCell(x++).setCellValue(getdConvert().civilStatusConv(memberData.get(i).getCivilStatus()));
                }
                if (getExportColumns().get(3)) {
                    HSSFCell cell = row.createCell(x++);
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(getdFormat().formatDate(memberData.get(i).getBirthdate(), "MM/dd/YYYY"));
                    HSSFCellStyle dateCellStyle = workbook.createCellStyle();
                    short df = workbook.createDataFormat().getFormat("MM/dd/YYYY");
                    dateCellStyle.setDataFormat(df);
                    cell.setCellStyle(dateCellStyle);
                }
                if (getExportColumns().get(4)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getTaxIdNo());
                }
                if (getExportColumns().get(5)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getPagIbig());
                }
                if (getExportColumns().get(6)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getPhilhealth());
                }
                if (getExportColumns().get(7)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getSss());
                }
                if (getExportColumns().get(8)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getGsis());
                }
                if (getExportColumns().get(9)) {
                    HSSFCell cell = row.createCell(x++);
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(getdFormat().formatDate(memberData.get(i).getMemDate(), "MM/dd/YYYY"));
                    HSSFCellStyle dateCellStyle = workbook.createCellStyle();
                    short df = workbook.createDataFormat().getFormat("MM/dd/YYYY");
                    dateCellStyle.setDataFormat(df);
                    cell.setCellStyle(dateCellStyle);
                }
                if (getExportColumns().get(10)) {
                    row.createCell(x++).setCellValue(memberData.get(i).getStatusId().getStatusName());
                }
                if (getExportColumns().get(11)) {
                    try {
                        String skl = (String) emf.createEntityManager().createQuery("SELECT c.skillsCode.skillsName FROM CoopSkillsMem c "
                                + "WHERE UPPER(c.skillsCode.skillsName) LIKE UPPER('%" + filterData.getSkill() + "%') "
                                + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList().get(0);
                        row.createCell(x++).setCellValue(skl);
                    } catch (Exception e) {
                        row.createCell(x++).setCellValue("");
                    }
                }
                if (getExportColumns().get(12)) {
                    if (filterData.getCompensation() != null) {
                        row.createCell(x++).setCellValue(filterData.getCompensation());
                    } else {
                        try {
                            String cmpBrckt = (String) emf.createEntityManager().createQuery("SELECT c.emplDtlNum.compBracket FROM CoopEmplDtlMem c "
                                    + "WHERE c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList().get(0);
                            row.createCell(x++).setCellValue(cmpBrckt);
                        } catch (Exception e) {
                            row.createCell(x++).setCellValue("");
                        }
                    }
                }
                if (getExportColumns().get(13)) {
                    List<CoopEducInfo> educInfo = null;

                    //POST-GRADUATE
                    educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoMem c "
                            + "WHERE c.educInfoNum.schoolLevel ='POST-GRADUATE' "
                            + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList();
                    for (int a = 0; a != educInfo.size(); a++) {
                        rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                        row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                    }

                    //COLLEGE
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoMem c "
                                + "WHERE c.educInfoNum.schoolLevel ='COLLEGE' "
                                + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //VOCATIONAL
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoMem c "
                                + "WHERE c.educInfoNum.schoolLevel ='VOCATIONAL' "
                                + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //HIGH SCHOOL
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoMem c "
                                + "WHERE c.educInfoNum.schoolLevel ='HIGH SCHOOL' "
                                + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //ELEMENTARY
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoMem c "
                                + "WHERE c.educInfoNum.schoolLevel ='ELEMENTARY' "
                                + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    if (educInfo.isEmpty()) {
                        row.createCell(x++).setCellValue("");
                    }
                }
                if (getExportColumns().get(14)) {
                    try {
                        String rnkPos = (String) emf.createEntityManager().createQuery("SELECT c.emplDtlNum.emplRankPos.unitName FROM CoopEmplDtlMem c "
                                + "WHERE UPPER(c.emplDtlNum.emplRankPos.unitName) LIKE UPPER('%" + filterData.getRankPos() + "%') "
                                + "AND c.memNo.memNo ='" + memberData.get(i).getMemNo() + "'").getResultList().get(0);
                        row.createCell(x++).setCellValue(rnkPos);
                    } catch (Exception e) {
                        row.createCell(x++).setCellValue("");
                    }
                }
                x = 0;
            }

            FileOutputStream fileOut = new FileOutputStream(getFilename());
            workbook.write(fileOut);
            fileOut.close();

            fileUploadByDL(getFilename(), "Membership export", themeDisplay, null);

            //delete file
            File file = new File(getFilename());
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception ex) {
            System.out.println(ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while generating excel file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        setFilename("" + fName + "");
    }

    public void associateList(List<CoopAssociate> associateData, memberFilterData filterData) {
        String fName = getFilename();
        ThemeDisplay themeDisplay = LiferayFacesContext.getInstance().getThemeDisplay();
        createFolder(null, themeDisplay, "Membership export", "Description");

        int x = 0, y = 0;
        if (getFilename() == null || getFilename().length() == 0) {
            setFilename("Filtered Data List(" + new Date() + ")");
        }

        try {
            setFilename("" + getFilename() + ".xls");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);

            rowhead.createCell(y++).setCellValue("Member No.");
            rowhead.createCell(y++).setCellValue("Full Name");
            rowhead.createCell(y++).setCellValue("PT Number");

            if (getExportColumns().get(0)) {
                rowhead.createCell(y++).setCellValue("Address");
            }
            if (getExportColumns().get(1)) {
                rowhead.createCell(y++).setCellValue("Gender");
            }
            if (getExportColumns().get(2)) {
                rowhead.createCell(y++).setCellValue("Civil Status");
            }
            if (getExportColumns().get(3)) {
                rowhead.createCell(y++).setCellValue("Birthdate");
            }
            if (getExportColumns().get(4)) {
                rowhead.createCell(y++).setCellValue("TIN");
            }
            if (getExportColumns().get(5)) {
                rowhead.createCell(y++).setCellValue("Pag-Ibig");
            }
            if (getExportColumns().get(6)) {
                rowhead.createCell(y++).setCellValue("Philhealth");
            }
            if (getExportColumns().get(7)) {
                rowhead.createCell(y++).setCellValue("SSS");
            }
            if (getExportColumns().get(8)) {
                rowhead.createCell(y++).setCellValue("GSIS");
            }
            if (getExportColumns().get(9)) {
                rowhead.createCell(y++).setCellValue("Membership Date");
            }
            if (getExportColumns().get(10)) {
                rowhead.createCell(y++).setCellValue("Membership Status");
            }
            if (getExportColumns().get(11)) {
                rowhead.createCell(y++).setCellValue("Skill");
            }
            if (getExportColumns().get(12)) {
                rowhead.createCell(y++).setCellValue("Compensation Bracket");
            }
            if (getExportColumns().get(13)) {
                rowhead.createCell(y++).setCellValue("Educational Attainment");
            }
            if (getExportColumns().get(14)) {
                rowhead.createCell(y++).setCellValue("Rank Position");
            }

            for (int i = 0; i != associateData.size(); i++) {
                HSSFRow row = sheet.createRow((short) i + 1);
                row.createCell(x++).setCellValue(associateData.get(i).getMemNo());
                row.createCell(x++).setCellValue(getrObjct().fullname(associateData.get(i).getPPrefix(), associateData.get(i).getLastName(), associateData.get(i).getFirstName(), associateData.get(i).getMiddleName(), associateData.get(i).getSuffix()));
                try {
                    row.createCell(x++).setCellValue(associateData.get(i).getOuCode().getOuShortName());
                } catch (Exception e) {
                }
                if (getExportColumns().get(0)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getStreet() + " " + associateData.get(i).getBarangay() + " " + associateData.get(i).getCityMun() + " " + (associateData.get(i).getProvince() != null ? associateData.get(i).getProvince() : ""));
                }
                if (getExportColumns().get(1)) {
                    row.createCell(x++).setCellValue(getdConvert().genderConv(associateData.get(i).getGender()));
                }
                if (getExportColumns().get(2)) {
                    row.createCell(x++).setCellValue(getdConvert().civilStatusConv(associateData.get(i).getCivilStatus()));
                }
                if (getExportColumns().get(3)) {
                    HSSFCell cell = row.createCell(x++);
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(getdFormat().formatDate(associateData.get(i).getBirthdate(), "MM/dd/YYYY"));
                    HSSFCellStyle dateCellStyle = workbook.createCellStyle();
                    short df = workbook.createDataFormat().getFormat("MM/dd/YYYY");
                    dateCellStyle.setDataFormat(df);
                    cell.setCellStyle(dateCellStyle);
                }
                if (getExportColumns().get(4)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getTaxIdNo());
                }
                if (getExportColumns().get(5)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getPagIbig());
                }
                if (getExportColumns().get(6)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getPhilhealth());
                }
                if (getExportColumns().get(7)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getSss());
                }
                if (getExportColumns().get(8)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getGsis());
                }
                if (getExportColumns().get(9)) {
                    HSSFCell cell = row.createCell(x++);
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(getdFormat().formatDate(associateData.get(i).getMemDate(), "MM/dd/YYYY"));
                    HSSFCellStyle dateCellStyle = workbook.createCellStyle();
                    short df = workbook.createDataFormat().getFormat("MM/dd/YYYY");
                    dateCellStyle.setDataFormat(df);
                    cell.setCellStyle(dateCellStyle);
                }
                if (getExportColumns().get(10)) {
                    row.createCell(x++).setCellValue(associateData.get(i).getStatusId().getStatusName());
                }
                if (getExportColumns().get(11)) {
                    try {
                        String skl = (String) emf.createEntityManager().createQuery("SELECT c.skillsCode.skillsName FROM CoopSkillsAssoc c "
                                + "WHERE UPPER(c.skillsCode.skillsName) LIKE UPPER('%" + filterData.getSkill() + "%') "
                                + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList().get(0);
                        row.createCell(x++).setCellValue(skl);
                    } catch (Exception e) {
                        row.createCell(x++).setCellValue("");
                    }
                }
                if (getExportColumns().get(12)) {
                    if (filterData.getCompensation() != null) {
                        row.createCell(x++).setCellValue(filterData.getCompensation());
                    } else {
                        try {
                            String cmpBrckt = (String) emf.createEntityManager().createQuery("SELECT c.emplDtlNum.compBracket FROM CoopEmplDtlAssoc c "
                                    + "WHERE c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList().get(0);
                            row.createCell(x++).setCellValue(cmpBrckt);
                        } catch (Exception e) {
                            row.createCell(x++).setCellValue("");
                        }
                    }
                }
                if (getExportColumns().get(13)) {
                    List<CoopEducInfo> educInfo = null;

                    //POST-GRADUATE
                    educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoAssoc c "
                            + "WHERE c.educInfoNum.schoolLevel ='POST-GRADUATE' "
                            + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList();
                    for (int a = 0; a != educInfo.size(); a++) {
                        rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                        row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                    }

                    //COLLEGE
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoAssoc c "
                                + "WHERE c.educInfoNum.schoolLevel ='COLLEGE' "
                                + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //VOCATIONAL
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoAssoc c "
                                + "WHERE c.educInfoNum.schoolLevel ='VOCATIONAL' "
                                + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //HIGH SCHOOL
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoAssoc c "
                                + "WHERE c.educInfoNum.schoolLevel ='HIGH SCHOOL' "
                                + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    //ELEMENTARY
                    if (educInfo.isEmpty()) {
                        educInfo = emf.createEntityManager().createQuery("SELECT c.educInfoNum FROM CoopEducInfoAssoc c "
                                + "WHERE c.educInfoNum.schoolLevel ='ELEMENTARY' "
                                + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList();
                        for (int a = 0; a != educInfo.size(); a++) {
                            rowhead.createCell(x).setCellValue("Educational Attainment " + (a + 1));
                            row.createCell(x++).setCellValue(getrObjct().educInfo(educInfo.get(a)));
                        }
                    }

                    if (educInfo.isEmpty()) {
                        row.createCell(x++).setCellValue("");
                    }
                }
                if (getExportColumns().get(14)) {
                    try {
                        String rnkPos = (String) emf.createEntityManager().createQuery("SELECT c.emplDtlNum.emplRankPos.unitName FROM CoopEmplDtlAssoc c "
                                + "WHERE UPPER(c.emplDtlNum.emplRankPos.unitName) LIKE UPPER('%" + filterData.getRankPos() + "%') "
                                + "AND c.associateNo.memNo ='" + associateData.get(i).getMemNo() + "'").getResultList().get(0);
                        row.createCell(x++).setCellValue(rnkPos);
                    } catch (Exception e) {
                        row.createCell(x++).setCellValue("");
                    }
                }
                x = 0;
            }

            FileOutputStream fileOut = new FileOutputStream(getFilename());
            workbook.write(fileOut);
            fileOut.close();

            fileUploadByDL(getFilename(), "Membership export", themeDisplay, null);

            //delete file
            File file = new File(getFilename());
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception ex) {
            System.out.println(ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while generating excel file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        setFilename("" + fName + "");
    }

    public String getFilename() {
        if (filename == null) {
            return filename;
        } else {
            return filename.replace(":", "");
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<Boolean> getExportColumns() {
        if (exportColumns == null) {
            exportColumns = new ArrayList<>();
            for (int i = 0; i != boolSize; i++) {
                exportColumns.add(Boolean.FALSE);
            }
        }
        return exportColumns;
    }

    public void setExportColumns(List<Boolean> exportColumns) {
        this.exportColumns = exportColumns;
    }

    public DataConvert getdConvert() {
        if (dConvert == null) {
            dConvert = new DataConvert();
        }
        return dConvert;
    }

    public void setdConvert(DataConvert dConvert) {
        this.dConvert = dConvert;
    }

    public returnObject getrObjct() {
        if (rObjct == null) {
            rObjct = new returnObject();
        }
        return rObjct;
    }

    public void setrObjct(returnObject rObjct) {
        this.rObjct = rObjct;
    }

    public dateFormat getdFormat() {
        if (dFormat == null) {
            dFormat = new dateFormat();
        }
        return dFormat;
    }

    public void setdFormat(dateFormat dFormat) {
        this.dFormat = dFormat;
    }
}

