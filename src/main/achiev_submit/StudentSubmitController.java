package main.achiev_submit;

import main.DAO.DAO;
import main.DAO.DAOFactory;
import main.DAO.Impl.DAOSqlServer;
import main.pojo.*;
import main.searchcriteria.SearchCriteria;
import main.searchcriteria.VerifySearchCriteria;
import main.Util.LoadEvid;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentSubmitController {
    /**
     * 硕士研究生选择提交的成果
     */
    public void MDsubmitMenu(String st_id){
        System.out.println("硕士研究生可认定的成果包括: \n" +
                "1. 论文\n" +
                "2. 教材\n" +
                "3. 标准\n" +
                "4. 专利\n" +
                "5. 报告\n" +
                "6. 软硬件平台开发证明\n");
        System.out.println("输入选项前的序号选择要提交的成果类型: \n");
        Scanner sc = new Scanner(System.in);
        // 假设输入就是合法的，如果要不合法输入的情况之后再添
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                submitThesis(st_id);
                break;
            case 2:
                submitTextbook(st_id);
                break;
            case 3:
                submitStandard(st_id);
                break;
            case 4:
                submitPatent(st_id);
                break;
            case 5:
                submitReport(st_id);
                break;
            case 6:
                submitDevelopment(st_id);
                break;
            default:
                System.out.println("序号无效\n");
                break;
        }
    }

    /**
     * 博士研究生选择提交的成果
     */
    public void PhDsubmitMenu(String st_id) {
        System.out.println("博士研究生可认定的成果包括: \n" +
                "1. 论文\n" +
                "2. 奖励\n" +
                "3. 标准\n" +
                "4. 其他成果\n");
        System.out.println("输入选项前的序号选择要提交的成果类型: \n");
        Scanner sc = new Scanner(System.in);
        // 假设输入就是合法的，如果要不合法输入的情况之后再添
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                submitThesis(st_id);
                break;
            case 2:
                submitAward(st_id);
                break;
            case 3:
                submitStandard(st_id);
                break;
            case 4:
                submitOther(st_id);
                break;
            default:
                System.out.println("序号无效\n");
                break;
        }
    }

    /**
     * 学生选择查询的成果类型
     */
    public void selectVerifyMenu(String st_id){
        VerifySearchCriteria criteria = new VerifySearchCriteria();

        System.out.println("选择要查询的申请：\n" +
                "1. 全部\n" +
                "2. 论文\n" +
                "3. 奖励\n" +
                "4. 标准\n" +
                "5. 报告\n" +
                "6. 专利\n" +
                "7. 软硬件平台开发\n" +
                "8. 教材\n" +
                "9. 其他\n");
        Scanner sc = new Scanner(System.in);
        Integer choice = sc.nextInt();
        String prefix = null;

        switch(choice) {
            case 1:
                break;
            case 2:
                prefix = "TH";break;
            case 3:
                prefix = "AW"; break;
            case 4:
                prefix = "ST";break;
            case 5:
                prefix = "RE";break;
            case 6:
                prefix = "PA";break;
            case 7:
                prefix = "DE";break;
            case 8:
                prefix = "TE";break;
            case 9:
                prefix = "OT";break;
            default:
                break;
        }

        criteria.setAchiev_no(prefix);
        criteria.setSt_id(st_id);
        selectVerify(criteria);
    }



    /**
     * 查询某类成果审核情况(直接输出)
     */
    public void selectVerify(VerifySearchCriteria criteria) {
        List<Verify> verifies = new ArrayList<>();

        System.out.println("未审核成果: \n");
        criteria.setFirst_verify(0);
        criteria.setLast_verify(0);
        verifies = DAOFactory.getInstance().getVerifyDAO().selectByCriteria(criteria);
        System.out.println(verifies);

        System.out.println("未终审成果: \n");
        criteria.setFirst_verify(1);
        criteria.setLast_verify(0);
        verifies = DAOFactory.getInstance().getVerifyDAO().selectByCriteria(criteria);
        System.out.println(verifies);

        System.out.println("审核通过成果: \n");
        criteria.setFirst_verify(1);
        criteria.setLast_verify(1);
        verifies = DAOFactory.getInstance().getVerifyDAO().selectByCriteria(criteria);
        System.out.println(verifies);

        System.out.println("初审未通过成果:\n");
        criteria.setFirst_verify(-1);
        criteria.setLast_verify(0);
        verifies = DAOFactory.getInstance().getVerifyDAO().selectByCriteria(criteria);
        System.out.println(verifies);

        System.out.println("终审未通过成果: \n");
        criteria.setFirst_verify(1);
        criteria.setLast_verify(-1);
        verifies = DAOFactory.getInstance().getVerifyDAO().selectByCriteria(criteria);
        System.out.println(verifies);
    }

    /**
     * 论文成果提交
     */
    public void submitThesis(String st_id) {
        System.out.println("*注意以下提示填写的均为必填项*\n");
        synchronized (this) {
            Thesis thesis = new Thesis();
            Verify verify = new Verify();
            verify.setSt_id(st_id);
            // 生成成果编号
            String prefix = "TH";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            prefix += simpleDateFormat.format(date);
            Integer num = DAOFactory.getInstance().getVerifyDAO().selectByAchiev(prefix).size();
            String achiev_no = prefix + String.format("%06d", num + 1);
            verify.setAchiev_no(achiev_no);
            thesis.setAchiev_no(achiev_no);

            System.out.println("请输入论文名称:\n");
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            thesis.setTh_name(name);

            System.out.println("请输入论文发表刊物名称:\n");
            sc = new Scanner(System.in);
            String publi = sc.next();
            thesis.setTh_public(publi);

            System.out.println("请输入序号选择论文状态:\n" +
                    "1. 录用未发表\n" +
                    "2. 已发表\n");
            sc = new Scanner(System.in);
            Integer status = sc.nextInt();
            switch(status) {
                case 1:
                    thesis.setTh_status("录用未发表");
                    break;
                case 2:
                    thesis.setTh_status("已发表");
                    break;
                default:
                    thesis.setTh_status("录用未发表");
                    break;
            }

            System.out.println("请输入论文发表时间(例如2023-01-01):\n");
            sc = new Scanner(System.in);
            String time = sc.next();
            thesis.setTh_time(time);

            System.out.println("请输入论文索引类型:\n");
            sc = new Scanner(System.in);
            String index = sc.next();
            thesis.setTh_index(index);

            System.out.println("请输入序号选择论文归属库情况:\n" +
                    "1. 学院高质量论文库\n" +
                    "2. 学院核心论文库\n");
            sc = new Scanner(System.in);
            Integer lib = sc.nextInt();
            switch(lib) {
                case 1:
                    thesis.setTh_lib("学院高质量论文库");
                    break;
                case 2:
                    thesis.setTh_lib("学院核心论文库");
                    break;
                default:
                    thesis.setTh_lib("学院高质量论文库");
                    break;
            }

            System.out.println("请输入论文扫描或PDF材料本地存储路径:\n");
            sc = new Scanner(System.in);
            String evid = sc.next();
            thesis.setTh_evid(evid);

            // 如果取消直接退出
            System.out.println("确定提交吗？(y/n) ");
            String mark = sc.next();
            if(!mark.equals("y")) return;

            thesis.setTh_evid(LoadEvid.uploadEvid(evid));
            date = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            verify.setSubmit_time(s.format(date));

            DAOFactory.getInstance().getVerifyDAO().insert(verify);
            DAOFactory.getInstance().getThesisDAO().insert(thesis);
        }
    }

    /**
     * 奖励成果提交
     */
    public void submitAward(String st_id) {
        System.out.println("*注意以下提示填写的均为必填项*\n");
        synchronized (this) {
            Award award= new Award();
            Verify verify = new Verify();

            // 生成成果编号
            String prefix = "AW";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            prefix += simpleDateFormat.format(date);
            Integer num = DAOFactory.getInstance().getVerifyDAO().selectByAchiev(prefix).size();
            String achiev_no = prefix + String.format("%06d", num + 1);
            verify.setAchiev_no(achiev_no);
            award.setAchiev_no(achiev_no);

            System.out.println("请输入奖励名称:\n");
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            award.setAw_name(name);

            System.out.println("请输入序号选择奖励等级:\n" +
                    "1. 国家级\n" +
                    "2. 省部级\n" +
                    "3. 市级\n" +
                    "4. 其他\n");
            sc = new Scanner(System.in);
            Integer level = sc.nextInt();
            switch(level) {
                case 1:
                    award.setAw_level("国家级");
                    break;
                case 2:
                    award.setAw_level("省部级");
                    break;
                case 3:
                    award.setAw_level("市级");
                    break;
                case 4:
                    award.setAw_level("其他");
                    break;
                default:
                    award.setAw_level("其他");
                    break;
            }

            System.out.println("请输入序号选择获奖等级:\n" +
                    "1. 特等奖\n" +
                    "2. 一等奖\n" +
                    "3. 二等奖\n" +
                    "4. 三等奖\n");
            sc = new Scanner(System.in);
            Integer grade = sc.nextInt();
            switch(grade) {
                case 1:
                    award.setAw_grade("特等奖");
                    break;
                case 2:
                    award.setAw_grade("一等奖");
                    break;
                case 3:
                    award.setAw_grade("二等奖");
                    break;
                case 4:
                    award.setAw_grade("三等奖");
                    break;
                default:
                    award.setAw_grade("三等奖");
                    break;
            }

            System.out.println("请输入奖励排名(1,2,...):\n");
            sc = new Scanner(System.in);
            Integer rank = sc.nextInt();
            award.setAw_rank(rank);

            System.out.println("请输入获得奖励时间(例如2023-01-01):\n");
            sc = new Scanner(System.in);
            String time = sc.next();
            award.setAw_time(time);

            System.out.println("请输入佐证材料本地存储路径(.rar,.zip):\n");
            sc = new Scanner(System.in);
            String evid = sc.next();
            award.setAw_evid(evid);

            // 如果取消直接退出
            System.out.println("确定提交吗？(y/n) ");
            String mark = sc.next();
            if(!mark.equals("y")) return;
//C:\Users\20133\Desktop\achieve.zip
            award.setAw_evid(LoadEvid.uploadEvid(evid));
            date = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            verify.setSubmit_time(s.format(date));

            DAOFactory.getInstance().getVerifyDAO().insert(verify);
            DAOFactory.getInstance().getAwardDAO().insert(award);
        }
    }

    /**
     * 标准成果提交
     */
    public void submitStandard(String st_id) {
        System.out.println("*注意以下提示填写的均为必填项*\n");
        synchronized (this) {
            Standard achiev= new Standard();
            Verify verify = new Verify();

            // 生成成果编号
            String prefix = "ST";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            prefix += simpleDateFormat.format(date);
            Integer num = DAOFactory.getInstance().getVerifyDAO().selectByAchiev(prefix).size();
            String achiev_no = prefix + String.format("%06d", num + 1);
            verify.setAchiev_no(achiev_no);
            achiev.setAchiev_no(achiev_no);

            System.out.println("请输入标准名称:\n");
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            achiev.setSt_name(name);

            System.out.println("请输入序号选择标准等级:\n" +
                    "1. 国家标准\n" +
                    "2. 省部级地方标准\n" +
                    "3. 行业标准\n" +
                    "4. 团队标准\n");
            sc = new Scanner(System.in);
            Integer level = sc.nextInt();
            switch(level) {
                case 1:
                    achiev.setSt_level("国家标准");
                    break;
                case 2:
                    achiev.setSt_level("省部级地方标准");
                    break;
                case 3:
                    achiev.setSt_level("行业标准");
                    break;
                case 4:
                    achiev.setSt_level("团队标准");
                    break;
                default:
                    achiev.setSt_level("团队标准");
                    break;
            }

            System.out.println("请输入标准发布时间(例如2023-01-01):\n");
            sc = new Scanner(System.in);
            String time = sc.next();
            achiev.setSt_time(time);

            System.out.println("请输入佐证材料本地存储路径(.rar,.zip):\n");
            sc = new Scanner(System.in);
            String evid = sc.next();
            achiev.setSt_evid(evid);

            // 如果取消直接退出
            System.out.println("确定提交吗？(y/n) ");
            String mark = sc.next();
            if(!mark.equals("y")) return;

            achiev.setSt_evid(LoadEvid.uploadEvid(evid));
            date = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            verify.setSubmit_time(s.format(date));

            DAOFactory.getInstance().getVerifyDAO().insert(verify);
            DAOFactory.getInstance().getStandardDAO().insert(achiev);
        }
    }

    /**
     * 标准成果提交
     */
    public void submitReport(String st_id) {
        System.out.println("*注意以下提示填写的均为必填项*\n");
        synchronized (this) {
            Report achiev= new Report();
            Verify verify = new Verify();

            // 生成成果编号
            String prefix = "RE";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            prefix += simpleDateFormat.format(date);
            Integer num = DAOFactory.getInstance().getVerifyDAO().selectByAchiev(prefix).size();
            String achiev_no = prefix + String.format("%06d", num + 1);
            verify.setAchiev_no(achiev_no);
            achiev.setAchiev_no(achiev_no);

            System.out.println("请输入报告名称:\n");
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            achiev.setRe_name(name);

            System.out.println("请输入序号选择报告类型:\n" +
                    "1. 规划类\n" +
                    "2. 设计类\n" +
                    "3. 服务类\n" +
                    "4. 其他\n");
            sc = new Scanner(System.in);
            Integer level = sc.nextInt();
            switch(level) {
                case 1:
                    achiev.setRe_type("规划类");
                    break;
                case 2:
                    achiev.setRe_type("设计类");
                    break;
                case 3:
                    achiev.setRe_type("服务类");
                    break;
                case 4:
                    achiev.setRe_type("其他");
                    break;
                default:
                    achiev.setRe_type("其他");
                    break;
            }

            System.out.println("请输入报告服务单位:\n");
            sc = new Scanner(System.in);
            String unit = sc.next();
            achiev.setRe_unit(unit);

            System.out.println("请输入报告时间(例如2023-01-01):\n");
            sc = new Scanner(System.in);
            String time = sc.next();
            achiev.setRe_time(time);

            System.out.println("请输入报告贡献度排名（整数）:\n");
            sc = new Scanner(System.in);
            Integer contri = sc.nextInt();
            achiev.setRe_contri(contri);

            System.out.println("请输入佐证材料本地存储路径(.rar,.zip):\n");
            sc = new Scanner(System.in);
            String evid = sc.next();
            achiev.setRe_evid(evid);

            // 如果取消直接退出
            System.out.println("确定提交吗？(y/n) ");
            String mark = sc.next();
            if(!mark.equals("y")) return;

            achiev.setRe_evid(LoadEvid.uploadEvid(evid));
            date = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            verify.setSubmit_time(s.format(date));

            DAOFactory.getInstance().getVerifyDAO().insert(verify);
            DAOFactory.getInstance().getReportDAO().insert(achiev);
        }
    }

    /**
     * 专利成果提交
     */
    public void submitPatent(String st_id) {
        System.out.println("*注意以下提示填写的均为必填项*\n");
        synchronized (this) {
            Patent achiev= new Patent();
            Verify verify = new Verify();

            // 生成成果编号
            String prefix = "PA";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            prefix += simpleDateFormat.format(date);
            Integer num = DAOFactory.getInstance().getVerifyDAO().selectByAchiev(prefix).size();
            String achiev_no = prefix + String.format("%06d", num + 1);
            verify.setAchiev_no(achiev_no);
            achiev.setAchiev_no(achiev_no);

            System.out.println("请输入专利名称:\n");
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            achiev.setPa_name(name);

            System.out.println("请输入序号选择专利类型:\n" +
                    "1. 发明专利\n" +
                    "2. 实用新型专利\n");
            sc = new Scanner(System.in);
            Integer type = sc.nextInt();
            switch(type) {
                case 1:
                    achiev.setPa_type("发明专利");
                    break;
                case 2:
                    achiev.setPa_type("实用新型专利");
                    break;
                default:
                    achiev.setPa_type("实用新型专利");
                    break;
            }

            System.out.println("请输入专利号:\n");
            sc = new Scanner(System.in);
            String no = sc.next();
            achiev.setPa_no(no);

            System.out.println("请输入专利发布时间(例如2023-01-01):\n");
            sc = new Scanner(System.in);
            String time = sc.next();
            achiev.setPa_time(time);

            System.out.println("请输入专利状态:\n");
            sc = new Scanner(System.in);
            String status = sc.next();
            achiev.setPa_status(status);

            System.out.println("请输入专利贡献度排名（整数）:\n");
            sc = new Scanner(System.in);
            Integer contri = sc.nextInt();
            achiev.setPa_contri(contri);

            System.out.println("请输入佐证材料本地存储路径(.rar,.zip):\n");
            sc = new Scanner(System.in);
            String evid = sc.next();
            achiev.setPa_evid(evid);

            // 如果取消直接退出
            System.out.println("确定提交吗？(y/n) ");
            String mark = sc.next();
            if(!mark.equals("y")) return;

            achiev.setPa_evid(LoadEvid.uploadEvid(evid));
            date = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            verify.setSubmit_time(s.format(date));

            DAOFactory.getInstance().getVerifyDAO().insert(verify);
            DAOFactory.getInstance().getPatentDAO().insert(achiev);
        }
    }

    /**
     * 软硬件开发平台成果
     */
    public void submitDevelopment(String st_id) {
        System.out.println("*注意以下提示填写的均为必填项*\n");
        synchronized (this) {
            Development achiev= new Development();
            Verify verify = new Verify();

            // 生成成果编号
            String prefix = "DE";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            prefix += simpleDateFormat.format(date);
            Integer num = DAOFactory.getInstance().getVerifyDAO().selectByAchiev(prefix).size();
            String achiev_no = prefix + String.format("%06d", num + 1);
            verify.setAchiev_no(achiev_no);
            achiev.setAchiev_no(achiev_no);

            System.out.println("请输入平台名称:\n");
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            achiev.setDe_name(name);

            System.out.println("请输入平台服务单位:\n");
            sc = new Scanner(System.in);
            String no = sc.next();
            achiev.setDe_unit(no);

            System.out.println("请输入平台上线时间(例如2023-01-01):\n");
            sc = new Scanner(System.in);
            String time = sc.next();
            achiev.setDe_time(time);

            System.out.println("请输入专利贡献度排名（整数）:\n");
            sc = new Scanner(System.in);
            Integer contri = sc.nextInt();
            achiev.setDe_contri(contri);

            System.out.println("请输入佐证材料本地存储路径(.rar,.zip):\n");
            sc = new Scanner(System.in);
            String evid = sc.next();
            achiev.setDe_evid(evid);

            // 如果取消直接退出
            System.out.println("确定提交吗？(y/n) ");
            String mark = sc.next();
            if(!mark.equals("y")) return;

            achiev.setDe_evid(LoadEvid.uploadEvid(evid));
            date = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            verify.setSubmit_time(s.format(date));

            DAOFactory.getInstance().getVerifyDAO().insert(verify);
            DAOFactory.getInstance().getDevelopmentDAO().insert(achiev);
        }
    }

    /**
     * 教材
     */
    public void submitTextbook(String st_id) {
        System.out.println("*注意以下提示填写的均为必填项*\n");
        synchronized (this) {
            Textbook achiev= new Textbook();
            Verify verify = new Verify();

            // 生成成果编号
            String prefix = "TE";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            prefix += simpleDateFormat.format(date);
            Integer num = DAOFactory.getInstance().getVerifyDAO().selectByAchiev(prefix).size();
            String achiev_no = prefix + String.format("%06d", num + 1);
            verify.setAchiev_no(achiev_no);
            achiev.setAchiev_no(achiev_no);

            System.out.println("请输入教材名称:\n");
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            achiev.setTe_name(name);

            System.out.println("请输入教材出版社:\n");
            sc = new Scanner(System.in);
            String no = sc.next();
            achiev.setTe_press(no);

            System.out.println("请输入教材出版时间(例如2023-01-01):\n");
            sc = new Scanner(System.in);
            String time = sc.next();
            achiev.setTe_presstime(time);

            System.out.println("请输入教材贡献度（整数）:\n");
            sc = new Scanner(System.in);
            Integer contri = sc.nextInt();
            achiev.setTe_contri(contri);

            System.out.println("请输入佐证材料本地存储路径(.rar,.zip):\n");
            sc = new Scanner(System.in);
            String evid = sc.next();
            achiev.setTe_evid(evid);

            // 如果取消直接退出
            System.out.println("确定提交吗？(y/n) ");
            String mark = sc.next();
            if(!mark.equals("y")) return;

            achiev.setTe_evid(LoadEvid.uploadEvid(evid));
            date = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            verify.setSubmit_time(s.format(date));

            DAOFactory.getInstance().getVerifyDAO().insert(verify);
            DAOFactory.getInstance().getTextbookDAO().insert(achiev);
        }
    }

    /**
     * 其他成果
     */
    public void submitOther(String st_id) {
        System.out.println("*注意以下提示填写的均为必填项*\n");
        synchronized (this) {
            Other achiev= new Other();
            Verify verify = new Verify();

            // 生成成果编号
            String prefix = "OT";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            prefix += simpleDateFormat.format(date);
            Integer num = DAOFactory.getInstance().getVerifyDAO().selectByAchiev(prefix).size();
            String achiev_no = prefix + String.format("%06d", num + 1);
            verify.setAchiev_no(achiev_no);
            achiev.setAchiev_no(achiev_no);

            System.out.println("请输入成果名称:\n");
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            achiev.setOt_name(name);

            System.out.println("请输入成果描述（50字以内）:\n");
            sc = new Scanner(System.in);
            String desc = sc.next();
            achiev.setOt_desc(desc);

            System.out.println("请输入成果发布时间(例如2023-01-01):\n");
            sc = new Scanner(System.in);
            String time = sc.next();
            achiev.setOt_time(time);

            System.out.println("请输入佐证材料本地存储路径(.rar,.zip):\n");
            sc = new Scanner(System.in);
            String evid = sc.next();
            achiev.setOt_evid(evid);

            // 如果取消直接退出
            System.out.println("确定提交吗？(y/n) ");
            String mark = sc.next();
            if(!mark.equals("y")) return;

            achiev.setOt_evid(LoadEvid.uploadEvid(evid));
            date = new Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            verify.setSubmit_time(s.format(date));

            DAOFactory.getInstance().getVerifyDAO().insert(verify);
            DAOFactory.getInstance().getOtherDAO().insert(achiev);
        }
    }

}