package main.DAO;

import main.ApplyAssistance.ApplyController;
import main.DAO.Impl.*;
import main.achiev_submit.AdminAddStudent;
import main.achiev_submit.AdminVerifyController;
import main.achiev_submit.MentorVerifyController;
import main.achiev_submit.StudentSubmitController;
import main.pojo.*;
import main.graduate.graduateController;
import main.project_identify.projectController;
import main.project_identify.project_identifyController;
import java.util.List;
import java.util.Scanner;

public class test {
    private static Scanner sc=new Scanner(System.in);
    private static int cnt;
    private static graduateController con=new graduateController();
    private static project_identifyController pic=new project_identifyController();
    private static projectController pc=new projectController();
    private static ApplyController ac = new ApplyController();

    private static AdminAddStudent aad = new AdminAddStudent();
    private static AdminVerifyController avc = new AdminVerifyController();
    private static MentorVerifyController mvc = new MentorVerifyController();
    private static StudentSubmitController ssc = new StudentSubmitController();
    public static void AdminFunc(Admin temp){
        while(true){
            System.out.println("请选择功能：\n1.研究生毕业审核\n2.研究生项目认定审核\n3.研究生成果认定审核\n4.研究生学术交流审核\n5.学生录入\n6.助教分配\n7.关联导师和学生\n8.退出");
            cnt=sc.nextInt();
            sc.nextLine();
            int t;
            switch (cnt){
                case 1:
                    while(true){
                        System.out.println("请选择：\n1.显示所有学生毕业信息\n2.学生毕业审核\n3.退出");
                        t=sc.nextInt();
                        sc.nextLine();
                        if(t==1){
                            //查看所有
                            con.admingetall();
                        }
                        else if(t==2){
                            //审核毕业
                            System.out.println("请输入要审核是否满足毕业条件的学生id：");
                            con.trail(sc.nextLine());
                        }
                        else if(t==3){
                            break;
                        }
                    }
                    break;
                case 2:
                    while(true){
                        System.out.println("请选择：\n1.查询项目认证表\n2.查询项目表\n3.更新项目认定次数\n4.退出");
                        t=sc.nextInt();
                        sc.nextLine();
                        if(t==1){
                            //查询项目认证表
                            pic.adget();
                        }
                        else if(t==2){
                            //查询项目表
                            pc.adget();
                        }
                        else if(t==3){
                            //修改全部通过认证的学生毕业审核表中的项目认定次数
                            pic.updatepi_num();
                        }
                        else if(t==4){
                            break;
                        }
                    }
                    break;
                case 3:
                    //成果认定
                    System.out.println("请选择: \n" + "1. 查看成果审核信息\n" + "2. 查看提交成果详细信息\n" + "3. 修改成果终审状态\n");
                    Integer choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            avc.select(temp.getAd_id());
                            break;
                        case 2:
                            avc.verifyAchiev(temp.getAd_id());
                            break;
                        case 3:
                            avc.lastVerify(temp.getAd_id());
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:
                    //学术交流认证
                    System.out.println("显示学生学术交流情况表:\n");
                    System.out.println(DAOFactory.getInstance().getAcademicDao().verify());
                    break;
                case 5:
                    Student s=new Student();
                    System.out.println("请输入学生基本信息(编号 姓名 性别 电话 邮箱 类别 学科编号)");
                    //s.set
                    s.setSt_id(sc.nextLine());
                    s.setSt_name(sc.nextLine());
                    s.setSt_pwd(s.getSt_id());
                    s.setSt_sex(sc.nextLine());
                    s.setSt_tel(sc.nextLine());
                    s.setSt_email(sc.nextLine());
                    s.setSt_type(sc.nextLine());
                    s.setSu_id(sc.nextLine());
                    StudentDaoImpl dao=new StudentDaoImpl();
                    dao.addStudent(s);//新建学生
                    con.addGraduate(s.getSt_id());//新建毕业信息
                    break;
                case 6:
                    ac.Allocation();
                case 7://excel导入新生-导师
                    System.out.println("请选择：\n1.建立已有的导师和学生的联系\n2.excel导入新生导师联系表生成新生账号");
                    choice=sc.nextInt();
                    sc.nextLine();
                    switch (choice){
                        case 1:
                            aad.addMe_St();
                            break;
                        case 2:
                            aad.addMe_StByExcel();
                            break;
                    }
                    break;
                case 8:
                    break;
            }
            if(cnt==8){break;}
        }


    }
    public static void MentorFunc(Mentor temp){
        while(true){
            System.out.println("请选择功能：\n1.研究生项目认定审核\n2.研究生成果认定审核\n3.研究生学术交流审核\n4.退出");
            int t;
            cnt=sc.nextInt();
            sc.nextLine();
            switch (cnt){
                case 1:
                    //项目认定
                    while(true){
                        System.out.println("请选择功能：\n1.查询自己下属的各个项目信息\n2.导师查询自己下属的各个项目信息的相关学生认证表信息\n3.修改项目认证表签字状态\n4.退出");
                        t=sc.nextInt();
                        sc.nextLine();
                        switch (t){
                            case 1:
                                //查询自己下属的各个项目信息
                                pc.meget(temp.getMe_id());
                                break;
                            case 2:
                                //导师查询自己下属的各个项目信息的相关学生认证表信息
                                pic.meget(temp.getMe_id());
                                break;
                            case 3:
                                //修改签字状态
                                System.out.println("请输入项目认证表编号和是否通过(通过或不通过):");
                                String pi_id=sc.nextLine();
                                String state=sc.nextLine();
                                pic.sign(state,1,pi_id,temp.getMe_id());
                                break;
                            case 4:
                                break;
                        }
                        if(t==4){
                            break;
                        }
                    }
                    break;
                case 2:
                    //成果审核
                    System.out.println("请选择: \n" + "1. 查看成果审核信息\n" + "2. 查看提交成果详细信息\n" + "3. 修改成果初审状态\n");
                    Integer choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            mvc.selectBySelf(temp.getMe_id());
                            break;
                        case 2:
                            mvc.verifyAchiev(temp.getMe_id());
                            break;
                        case 3:
                            mvc.mentorFirstVerify(temp.getMe_id());
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    //学术交流认证
                    System.out.println("1.显示学生学术交流情况表\n2.审核签字");
                    String s2=sc.next();
                    if (s2.equals("1")) {
                        System.out.println(DAOFactory.getInstance().getAcademicDao().verify());
                    } else if (s2.equals("2")) {
                        System.out.println("请输学生学号：");
                        String st_id = sc.next();
                        System.out.println("签字（通过或不通过）：");
                        String sign = sc.next();
                        System.out.println("学术交流会议id：");
                        int i=sc.nextInt();
                        DAOFactory.getInstance().getAcademicDao().updateByMentor(st_id, sign,i);
                    }
                    break;
                case 4:
                    break;
            }
            if(cnt==4){
                break;
            }
        }
    }
    public static void StudentFunc(Student temp){
        while(true){
            System.out.println("请选择功能：\n1.研究生项目认定申请\n2.研究生成果认定申请\n3.研究生学术交流申请\n4.助教申请\n5.填写助教评定表\n6.退出");
            int t;
            cnt=sc.nextInt();
            sc.nextLine();
            switch (cnt){
                case 1:
                    while(true){
                        System.out.println("请选择功能：\n1.查询所有项目信息\n2.新建参与项目的认定信息\n3.查询自己申请的认证项目信息\n4.退出");
                        t=sc.nextInt();
                        sc.nextLine();
                        if(t==1){//查询所有项目信息
                            pc.stget();
                        }
                        else if(t==2){//新建参与项目的认定信息
                            pic.addProject_identify();
                        }
                        else if(t==3){//查询自己申请的认证项目信息
                            pic.stget(temp.getSt_id());
                        }
                        else if(t==4){
                            break;
                        }
                    }
                    break;
                case 2:
                    //成果认定
                    System.out.println("请选择:\n" + "1. 提交成果\n" + "2. 查看审核情况");
                    Integer choice = sc.nextInt();
                    switch(choice) {
                        case 1:
                            // if(student.getSt_type().equals("博士研究生"))  // 本地数据库没有放这个字段，汇总就可以用了
                            if(temp.getSt_id().equals("博士研究生"))     // 用一个本地有的字段随便测一测
                                ssc.PhDsubmitMenu(temp.getSt_id());
                            else
                                ssc.MDsubmitMenu(temp.getSt_id());
                            break;
                        case 2:
                            ssc.selectVerifyMenu(temp.getSt_id());
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    //学术交流
                    System.out.println("请输入学术交流信息：");
                    academic a = new academic();
                    /*System.out.println("学术交流id：");
                    a.setAc_id(sc.next());*/
//                    System.out.println("图片：");
//                    a.setAc_img(sc.next());
                    System.out.println("报告中英名称");
                    a.setAc_meetingName(sc.next());
                    System.out.println("会议地点：");
                    a.setAc_location(sc.next());
                    System.out.println("会议时间:");
                    a.setAc_time(sc.next());
                    System.out.println("学术交流活动名称:");
                    a.setAcName(sc.next());
                    System.out.println("备注");
                    a.setAc_remarks(sc.next());
                    System.out.println("学生学号：");
                    a.setSt_id(sc.next());
                    DAOFactory.getInstance().getAcademicDao().insertByStudent(a);
                    break;
                case 4:
                    ac.Apply(temp.getSt_id());
                    break;
                case 5:
                    ac.stuEvaluate(temp.getSt_id());
                    break;
                case 6:
                    break;
            }
            if(cnt==6){
                break;
            }
        }
        //项目认定

        //助教申请
        //成果审核
        //学术交流认证
    }
    public static void Project_leaderFunc(Project_leader temp){
        while(true){
            System.out.println("请选择功能：\n1.新建项目\n2.查询自己管理的各个项目信息\n3.查询自己管理的各个项目信息的相关学生认证表信息\n4.修改项目认证表签字状态\n5.退出");
            cnt=sc.nextInt();
            sc.nextLine();
            switch (cnt){
                case 1:
                    //新建项目
                    pc.addProject(temp.getPl_id());
                    break;
                case 2:
                    //查询自己管理的各个项目信息
                    pc.plget(temp.getPl_id());
                    break;
                case 3:
                    //查询自己管理的各个项目信息的相关学生认证表信息
                    pic.plget(temp.getPl_id());
                    break;
                case 4:
                    //修改项目认证表签字状态
                    System.out.println("请输入项目认证表编号和是否通过(通过或不通过):");
                    String pi_id=sc.nextLine();
                    String state=sc.nextLine();
                    pic.sign(state,0,pi_id,temp.getPl_id());
                    break;
                case 5:
                    break;
            }
            if(cnt==5){
                break;
            }
        }
    }


    public static void ChargeFunc(charge temp){
        while(true){
            System.out.println("1.显示学生学术交流情况表\n2.审核签字\n3.查看某位学生学术交流次数\n4.退出");
            String s1=sc.next();
            if (s1.equals("1")) {
                System.out.println(DAOFactory.getInstance().getAcademicDao().verify());
            } else if (s1.equals("2")) {
                System.out.println("请输学生学号：");
                String st_id = sc.next();
                System.out.println("签字（通过或不通过）：");
                String sign = sc.next();
                System.out.println("学术交流会议id：");
                int i=sc.nextInt();
                DAOFactory.getInstance().getAcademicDao().updateByCharge(st_id, sign,i);
            }else if (s1.equals("3")){
                System.out.println("请输学生学号：");
                String st_id = sc.next();
                int a=DAOFactory.getInstance().getAcademicDao().countByCharge(st_id);
                System.out.println("学号："+st_id+","+a+"次");
            }else if(s1.equals("4")){
                break;
            }
        }
    }

    public static void TeacherFunc(Teacher temp){
        while (true){
            System.out.println("1.选择助教\n2.填写助教评定表\n3.分配助教\n4.退出");
            System.out.println("请输入您要进行的操作:");
            cnt = sc.nextInt();
            switch (cnt){
                case 1:
                    ac.Judge(temp.getTe_id());
                    break;
                case 2:
                    ac.teaEvaluate(temp.getTe_id());
                    break;
                case 3:
                    ac.Allocation();
                    break;
                case 4:
                    break;
            }
            if(cnt == 4)
                break;
        }
    }
    public static void login(){
        System.out.println("请选择登录身份：\n1.研究生管理员\n2.导师\n3.学生\n4.项目负责人\n5.学科负责人\n6.教师\n");
        cnt=sc.nextInt();
        sc.nextLine();
        System.out.println("请输入用户名和密码：");
        String name=sc.nextLine();
        String pwd=sc.nextLine();
        int flag=0;
        while(true){
            switch (cnt){
                case 1:
                    AdminDaoImpl admin=new AdminDaoImpl();
                    Admin ad=new Admin();
                    ad.setAd_name(name);
                    ad.setAd_pwd(pwd);
//                    System.out.println("name:"+name+" pwd:"+pwd);
                    List<Admin>admins=admin.findAdmins(ad);
                    if(admins.size()!=0){ AdminFunc(admins.get(0)); }
                    else{
                        flag=1;
                        System.out.println("不存在该研究生管理员或密码错误！");
                    }
                    break;
                case 2:
                    MentorDaoImpl mentor=new MentorDaoImpl();
                    Mentor me=new Mentor();
                    me.setMe_name(name);
                    me.setMe_pwd(pwd);
                    List<Mentor>mentors=mentor.findMentors(me);
                    if(mentors.size()!=0){ MentorFunc(mentors.get(0)); }
                    else{
                        flag=1;
                        System.out.println("不存在该导师或密码错误！"); }

                    break;
                case 3:
                    StudentDaoImpl student=new StudentDaoImpl();
                    Student st=new Student();
                    st.setSt_name(name);
                    st.setSt_pwd(pwd);
                    List<Student>students=student.findStudents(st);
                    if(students.size()>0){ StudentFunc(students.get(0)); }
                    else{
                        flag=1;
                        System.out.println("不存在该学生或密码错误！");
                    }
                    break;
                case 4:
                    Project_leaderDaoImpl prol=new Project_leaderDaoImpl();
                    Project_leader pl=new Project_leader();
                    pl.setPl_name(name);
                    pl.setPl_pwd(pwd);
                    List<Project_leader>project_leaders=prol.findProject_leaders(pl);
                    if(project_leaders.size()>0){ Project_leaderFunc(project_leaders.get(0)); }
                    else{
                        flag=1;
                        System.out.println("不存在该项目管理员或密码错误！");
                    }
                    break;
                case 5:
                    ChargeDaoImpl charge=new ChargeDaoImpl();
                    charge c=new charge();
                    c.setC_name(name);
                    c.setC_pwd(pwd);
                    List<charge>charges=charge.findCharges(c);
                    if(charges.size()>0){ ChargeFunc(charges.get(0)); }
                    else{
                        flag=1;
                        System.out.println("不存在该学科负责人或密码错误！");
                    }
                    break;
                case 6:
                    TeacherDaoImpl teacher=new TeacherDaoImpl();
                    Teacher te=new Teacher();
                    te.setTe_name(name);
                    te.setTe_pwd(pwd);
                    List<Teacher>teachers=teacher.TEACHER_LIST(te);
                    if(teachers.size()>0){ TeacherFunc(teachers.get(0)); }
                    else{
                        flag=1;
                        System.out.println("不存在该学生或密码错误！");
                    }
                    break;
                default:
                    break;
            }
            if(flag==0){ break; }
            else if(flag==1){
                System.out.println("请输入用户名和密码：");
                name=sc.nextLine();
                pwd=sc.nextLine();
                flag=0;
            }
        }
    }
    public static void main(String[] args) {
        login();
//        System.out.println("in");
//        graduateController graduateController=new graduateController();
//        //新建毕业审核表
//        graduateController.addGraduate();
//        //查看所有毕业审核表信息
//        graduateController.admingetall();
//        //审核毕业
//        graduateController.trail("001");

//        project_identifyController project_identifyController=new project_identifyController();
//        //更新学生毕业审核表项目认定次数
//        project_identifyController.updatepi_num();
//        //学生新建认定的参与项目信息
//        project_identifyController.addProject_identify();
//          //项目管理员或导师修改签字状态
//        project_identifyController.sign("通过",0,"001","002");
//        project_identifyController.sign("通过",1,"001002","002");
//        //项目管理员查询自己管理的各个项目信息的相关学生认证表信息
//        project_identifyController.plget("002");
//        //导师查询自己下属的各个项目信息的相关学生认证表信息
//        project_identifyController.meget("002");
//        //学生查询自己申请的认证项目信息
//        project_identifyController.stget("001");
//        //研究生培养管理员查询所有项目认证表
//        project_identifyController.adget();

//        projectController projectController=new projectController();
        //新建
//        projectController.addProject("001");//002 基于图像分析进行水质研究 国家级项目 002
//        //查找
//        projectController.adget();
//        projectController.stget();
//        projectController.meget("002");
//        projectController.plget("002");

//        Project gr=new Project();
//        gr.setPr_id("002");
//        gr.setPr_name("计算机");
//        gr.setPr_type("国家级");
//        gr.setPl_id("001");
//        gr.setMe_id("001");
//        DAOFactory.getInstance().getProjectDAO().addProject(gr);
//        System.out.println("add:"+DAOFactory.getInstance().getProjectDAO().getProject("001").toString());
//
//        Project grup=new Project();
//        grup.setPr_id("001");
//        grup.setPr_name("英语");
//        grup.setPr_type("校级");
//        grup.setPl_id("002");
//        grup.setMe_id("002");
//        DAOFactory.getInstance().getProjectDAO().updateProject(grup);
//        System.out.println("update:"+DAOFactory.getInstance().getProjectDAO().getProject("001").toString());
//
//        System.out.println("get:"+DAOFactory.getInstance().getProjectDAO().getProject("001").toString());
//        Project grfinds=new Project();
//        grfinds.setPr_id("0");
//        List<Project> list=DAOFactory.getInstance().getProjectDAO().findProjects(grfinds);
//        for(Project a:list){ System.out.println(a); }
//
//        DAOFactory.getInstance().getProjectDAO().deleteProject("001");
//        System.out.println("delete:"+DAOFactory.getInstance().getProjectDAO().getProject("002").toString());


//        Project gr=new Project();
//        gr.setPr_id("002");
//        gr.setPr_name("计算机");
//        gr.setPr_type("国家级");
//        gr.setPl_id("001");
//        gr.setMe_id("001");
//        DAOFactory.getInstance().getProjectDAO().addProject(gr);
//        System.out.println("add:"+DAOFactory.getInstance().getProjectDAO().getProject("001").toString());
//
//        Project grup=new Project();
//        grup.setPr_id("001");
//        grup.setPr_name("英语");
//        grup.setPr_type("校级");
//        grup.setPl_id("002");
//        grup.setMe_id("002");
//        DAOFactory.getInstance().getProjectDAO().updateProject(grup);
//        System.out.println("update:"+DAOFactory.getInstance().getProjectDAO().getProject("001").toString());
//
//        System.out.println("get:"+DAOFactory.getInstance().getProjectDAO().getProject("001").toString());
//        Project grfinds=new Project();
//        grfinds.setPr_id("0");
//        List<Project> list=DAOFactory.getInstance().getProjectDAO().findProjects(grfinds);
//        for(Project a:list){ System.out.println(a); }
//
//        DAOFactory.getInstance().getProjectDAO().deleteProject("001");
//        System.out.println("delete:"+DAOFactory.getInstance().getProjectDAO().getProject("002").toString());
    }
}
