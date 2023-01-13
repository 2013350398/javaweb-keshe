package main.achiev_submit;


import main.DAO.DAOFactory;
import main.pojo.*;
import main.searchcriteria.VerifySearchCriteria;
import main.Util.LoadEvid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AdminVerifyController {
    /**
     * 查看审核申请
     */
    public void select(String ad_id) {
        VerifySearchCriteria criteria = new VerifySearchCriteria();
        List<Verify> verifies = new ArrayList<>();

        System.out.println("请选择: \n" +
                "1. 待终审\n" +
                "2. 终审通过\n" +
                "3. 终审未通过\n" +
                "4. 未初审\n" +
                "5. 初审未通过");
        Scanner sc = new Scanner(System.in);
        Integer choice = sc.nextInt();

        switch (choice) {
            case 1:
                criteria.setFirst_verify(1);
                criteria.setLast_verify(0);
                verifies = DAOFactory.getInstance().getVerifyDAO().selectByCriteria(criteria);
                System.out.println(verifies);
                break;
            case 2:
                criteria.setFirst_verify(1);
                criteria.setLast_verify(1);
                verifies = DAOFactory.getInstance().getVerifyDAO().selectByCriteria(criteria);
                System.out.println(verifies);
                break;
            case 3:
                criteria.setFirst_verify(1);
                criteria.setLast_verify(-1);
                verifies = DAOFactory.getInstance().getVerifyDAO().selectByCriteria(criteria);
                System.out.println(verifies);
            case 4:
                criteria.setFirst_verify(0);
                criteria.setLast_verify(0);
                verifies = DAOFactory.getInstance().getVerifyDAO().selectByCriteria(criteria);
                System.out.println(verifies);
            case 5:
                criteria.setFirst_verify(-1);
                criteria.setLast_verify(0);
                verifies = DAOFactory.getInstance().getVerifyDAO().selectByCriteria(criteria);
                System.out.println(verifies);
        }
    }

    public void verifyAchiev(String ad_id) {
        System.out.println("输入成果编号: \n");
        Scanner sc = new Scanner(System.in);
        String achiev_no = sc.next();

        List result = new ArrayList<>();
        // 显示成果信息
        if(achiev_no.contains("TH")) {
            result = DAOFactory.getInstance().getThesisDAO().selectByNo(achiev_no);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object thesis : result)
                    LoadEvid.downloadEvid(((Thesis)thesis).getTh_evid());
        }
        if(achiev_no.contains("AW")) {
            result = DAOFactory.getInstance().getAwardDAO().selectByNo(achiev_no);
            System.out.println(result);
            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object award : result)
                    LoadEvid.downloadEvid(((Award) award).getAw_evid());
        }
        if(achiev_no.contains("ST")) {
            result = DAOFactory.getInstance().getStandardDAO().selectByNo(achiev_no);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object standard : result)
                    LoadEvid.downloadEvid(((Standard) standard).getSt_evid());
        }
        if(achiev_no.contains("RE")) {
            result = DAOFactory.getInstance().getReportDAO().selectByNo(achiev_no);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object report : result)
                    LoadEvid.downloadEvid(((Report) report).getRe_evid());
        }
        if(achiev_no.contains("PA")) {
            result = DAOFactory.getInstance().getReportDAO().selectByNo(achiev_no);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object report : result)
                    LoadEvid.downloadEvid(((Report)report).getRe_evid());
        }
        if(achiev_no.contains("DE")) {
            result = DAOFactory.getInstance().getDevelopmentDAO().selectByNo(achiev_no);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object achiev : result)
                    LoadEvid.downloadEvid(((Development) achiev).getDe_evid());
        }
        if(achiev_no.contains("TE")) {
            result = DAOFactory.getInstance().getTextbookDAO().selectByNo(achiev_no);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object achiev : result)
                    LoadEvid.downloadEvid(((Textbook) achiev).getTe_evid());
        }
        if(achiev_no.contains("OT")) {
            result = DAOFactory.getInstance().getOtherDAO().selectByNo(achiev_no);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object achiev : result)
                    LoadEvid.downloadEvid(((Other) achiev).getOt_evid());
        }
    }

    public void lastVerify(String ad_id) {
        System.out.println("输入成果编号: \n");
        Scanner sc = new Scanner(System.in);
        String achiev_no = sc.next();

        VerifySearchCriteria criteria = new VerifySearchCriteria();
        criteria.setAchiev_no(achiev_no);
        List<Verify> verifies =  DAOFactory.getInstance().getVerifyDAO().selectByAchiev(achiev_no);
        System.out.println(verifies);

        System.out.println("修改审核状态为: \n" +
                "1. 通过\n" +
                "0. 取消\n" +
                "-1.拒绝申请");
        Integer choice = sc.nextInt();

        if(choice != 0) {
            for (Verify verify: verifies) {
                verify.setAd_id(ad_id);
                verify.setLast_verify(choice);

                Date date = new Date();
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                verify.setLast_time(s.format(date));

                DAOFactory.getInstance().getVerifyDAO().updateStatus(verify);
            }
            System.out.println("成功!");
        }
    }
}