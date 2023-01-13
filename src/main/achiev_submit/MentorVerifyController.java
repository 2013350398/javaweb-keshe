package main.achiev_submit;

import main.DAO.*;
import main.pojo.*;
import main.searchcriteria.VerifySearchCriteria;
import main.Util.LoadEvid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MentorVerifyController {
    /**
     * 导师查看自己名下学生的审核申请
     */
    public void selectBySelf(String me_id) {
        VerifySearchCriteria criteria = new VerifySearchCriteria();
        List<Verify> verifies = new ArrayList<>();

        System.out.println("未审核:\n");
        criteria.setFirst_verify(0);
        criteria.setLast_verify(0);
        verifies = DAOFactory.getInstance().getVerifyDAO().salectByMentorCriteria(me_id, criteria);
        System.out.println(verifies);

        System.out.println("待终审:\n");
        criteria.setFirst_verify(1);
        criteria.setLast_verify(0);
        verifies = DAOFactory.getInstance().getVerifyDAO().salectByMentorCriteria(me_id, criteria);
        System.out.println(verifies);

        System.out.println("已通过:\n");
        criteria.setFirst_verify(1);
        criteria.setLast_verify(1);
        verifies = DAOFactory.getInstance().getVerifyDAO().salectByMentorCriteria(me_id, criteria);
        System.out.println(verifies);

        System.out.println("初审未通过: \n");
        criteria.setFirst_verify(-1);
        criteria.setLast_verify(0);
        verifies = DAOFactory.getInstance().getVerifyDAO().salectByMentorCriteria(me_id, criteria);
        System.out.println(verifies);

        System.out.println("终审未通过: \n");
        criteria.setFirst_verify(1);
        criteria.setLast_verify(-1);
        verifies = DAOFactory.getInstance().getVerifyDAO().salectByMentorCriteria(me_id, criteria);
        System.out.println(verifies);
    }

    /**
     * 导师审核
     */
    public void verifyAchiev(String me_id) {
        // 有 输入了不是自己名下学生提交的成果的成果编号 的可能性
        // 如果有前端可以直接每一条旁边搞个按钮，唉，命令行还要考虑不能让他查到其他的成果
        // 而且不十分友好，还得用户自己输编号
        System.out.println("输入成果编号: \n");
        Scanner sc = new Scanner(System.in);
        String achiev_no = sc.next();

        List result = new ArrayList<>();
        // 显示成果信息
        if(achiev_no.contains("TH")) {
            result = DAOFactory.getInstance().getThesisDAO().selectByNoMentor(achiev_no, me_id);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object thesis : result)
                    LoadEvid.downloadEvid(((Thesis)thesis).getTh_evid());
        }
        if(achiev_no.contains("AW")) {
            result = DAOFactory.getInstance().getAwardDAO().selectByNoMentor(achiev_no, me_id);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object award : result)
                    LoadEvid.downloadEvid(((Award) award).getAw_evid());
        }
        if(achiev_no.contains("ST")) {
            result = DAOFactory.getInstance().getStandardDAO().selectByNoMentor(achiev_no, me_id);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object standard : result)
                    LoadEvid.downloadEvid(((Standard) standard).getSt_evid());
        }
        if(achiev_no.contains("RE")) {
            result = DAOFactory.getInstance().getReportDAO().selectByNoMentor(achiev_no, me_id);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object report : result)
                    LoadEvid.downloadEvid(((Report) report).getRe_evid());
        }
        if(achiev_no.contains("PA")) {
            result = DAOFactory.getInstance().getReportDAO().selectByNoMentor(achiev_no, me_id);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object report : result)
                    LoadEvid.downloadEvid(((Report)report).getRe_evid());
        }
        if(achiev_no.contains("DE")) {
            result = DAOFactory.getInstance().getDevelopmentDAO().selectByNoMentor(achiev_no, me_id);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object achiev : result)
                    LoadEvid.downloadEvid(((Development) achiev).getDe_evid());
        }
        if(achiev_no.contains("TE")) {
            result = DAOFactory.getInstance().getTextbookDAO().selectByNoMentor(achiev_no, me_id);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object achiev : result)
                    LoadEvid.downloadEvid(((Textbook) achiev).getTe_evid());
        }
        if(achiev_no.contains("OT")) {
            result = DAOFactory.getInstance().getOtherDAO().selectByNoMentor(achiev_no, me_id);
            System.out.println(result);

            System.out.println("下载佐证材料到本地？ (y/n) ");
            String mark = sc.next();
            if(mark.equals("y"))
                for (Object achiev : result)
                    LoadEvid.downloadEvid(((Other) achiev).getOt_evid());
        }
    }

    public void mentorFirstVerify(String me_id) {
        System.out.println("输入成果编号: \n");
        Scanner sc = new Scanner(System.in);
        String achiev_no = sc.next();

        VerifySearchCriteria criteria = new VerifySearchCriteria();
        criteria.setAchiev_no(achiev_no);
        List<Verify> verifies =  DAOFactory.getInstance().getVerifyDAO().salectByMentorCriteria(me_id, criteria);
        System.out.println(verifies);

        System.out.println("修改审核状态为: \n" +
                "1. 通过\n" +
                "0. 取消\n" +
                "-1.拒绝申请");
        Integer choice = sc.nextInt();

        if (choice != 0) {
            for (Verify verify: verifies) {
                verify.setMe_id(me_id);
                verify.setLast_verify(choice);

                Date date = new Date();
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                verify.setLast_time(s.format(date));

                DAOFactory.getInstance().getVerifyDAO().updateStatus(verify);
            }
        }
    }
}