package main.DAO;

import main.ApplyAssistance.ApplyController;
import main.DAO.Impl.*;
import main.pojo.*;
import main.graduate.graduateController;
import main.project_identify.projectController;
import main.project_identify.project_identifyController;
import java.util.List;
import java.util.Scanner;

public class assistSys {
    private static Scanner sc=new Scanner(System.in);
    private static int cnt=sc.nextInt();
    private static graduateController con=new graduateController();
    private static project_identifyController pic=new project_identifyController();
    private static projectController pc=new projectController();
    private static ApplyController ac = new ApplyController();
    public static void AdminFunc(Admin temp){
        while(true){
            System.out.println("请选择功能：\n1.研究生毕业审核\n2.研究生项目认定审核\n3.研究生成果认定审核\n4.研究生学术交流审核\n5.学生录入\n6.退出");
            cnt=sc.nextInt();
            int t;
            switch (cnt){
                case 1:
                    while(true){
                        System.out.println("请选择：\n1.显示所有学生毕业信息\n2.学生毕业审核\n3.退出");
                        t=sc.nextInt();
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
                    break;
                case 4:
                    //学术交流认证
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
                    break;
            }
            if(cnt==6){break;}
        }


    }
    public static void MentorFunc(Mentor temp){
        while(true){
            System.out.println("请选择功能：\n1.研究生项目认定审核\n2.研究生成果认定审核\n3.研究生学术交流审核\n4.退出");
            int t;
            cnt=sc.nextInt();
            switch (cnt){
                case 1:
                    //项目认定
                    while(true){
                        System.out.println("请选择功能：\n1.查询自己下属的各个项目信息\n2.导师查询自己下属的各个项目信息的相关学生认证表信息\n3.修改项目认证表签字状态\n4.退出");
                        t=sc.nextInt();
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
                    break;
                case 3:
                    //学术交流认证
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
            switch (cnt){
                case 1:
                    while(true){
                        System.out.println("请选择功能：\n1.查询所有项目信息\n2.新建参与项目的认定信息\n3.查询自己申请的认证项目信息\n4.退出");
                        t=sc.nextInt();
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
                    break;
                case 3:
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
    public static void Project_leaderFunc(Project_leader temp){
        while(true){
            System.out.println("请选择功能：\n1.新建项目\n2.查询自己管理的各个项目信息\n3.查询自己管理的各个项目信息的相关学生认证表信息\n4.修改项目认证表签字状态\n5.退出");
            cnt=sc.nextInt();
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
    public static void login(){
        System.out.println("请选择登录身份：\n1.研究生管理员\n2.导师\n3.学生\n4.项目负责人\n5.学科负责人\n6.教师");
        Scanner sc1 = new Scanner(System.in);
        cnt=sc.nextInt();
        System.out.println("请输入用户名：");
        String name=sc1.nextLine();
        System.out.println("请输入密码:");
        String pwd=sc1.nextLine();
        switch (cnt){
            case 1:
                AdminDaoImpl admin=new AdminDaoImpl();
                Admin ad=new Admin();
                ad.setAd_name(name);
                ad.setAd_pwd(pwd);
                List<Admin>admins=admin.findAdmins(ad);
                if(admins.size()!=0){ AdminFunc(admins.get(0)); }
                else{ System.out.println("不存在该研究生管理员或密码错误！"); }
                break;
            case 2:
                MentorDaoImpl mentor=new MentorDaoImpl();
                Mentor me=new Mentor();
                me.setMe_name(name);
                me.setMe_pwd(pwd);
                List<Mentor>mentors=mentor.findMentors(me);
                if(mentors.size()!=0){ MentorFunc(mentors.get(0)); }
                else{ System.out.println("不存在该导师或密码错误！"); }

                break;
            case 3:
                StudentDaoImpl student=new StudentDaoImpl();
                Student st=new Student();
                st.setSt_name(name);
                st.setSt_pwd(pwd);
                List<Student>students=student.findStudents(st);
                if(students.size()>0){ StudentFunc(students.get(0)); }
                else{ System.out.println("不存在该学生或密码错误！"); }
                break;
            case 4:
                Project_leaderDaoImpl prol=new Project_leaderDaoImpl();
                Project_leader pl=new Project_leader();
                pl.setPl_name(name);
                pl.setPl_pwd(pwd);
                List<Project_leader>project_leaders=prol.findProject_leaders(pl);
                if(project_leaders.size()>0){ Project_leaderFunc(project_leaders.get(0)); }
                else{ System.out.println("不存在该项目管理员或密码错误！"); }
                break;
            case 5:
                break;
            case 6:
                TeacherDaoImpl teacherDao = new TeacherDaoImpl();
                Teacher teacher = new Teacher();
                teacher.setTe_name(name);
                teacher.setTe_pwd(pwd);
                List<Teacher> teachers = teacherDao.TEACHER_LIST(teacher);
                if(teachers.size()>0)
                    TeacherFunc(teachers.get(0));
                else
                    System.out.println("用户名或密码输入错误!");
                break;
            default:
                break;
        }
    }
    public static void main(String[] args) {
        login();
    }
}