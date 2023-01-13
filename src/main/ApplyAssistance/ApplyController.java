package main.ApplyAssistance;

import main.CustomType.ApaTeacher;
import main.DAO.DAOFactory;
import main.pojo.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class ApplyController {
    //研究生申请助教
    public void Apply(String st_id){
        Scanner sc = new Scanner(System.in);
        List<CourseTeacher> courseList= DAOFactory.getInstance().getCourseDAO().COURSE_LIST();
        Collections.sort(courseList, Comparator.comparing(CourseTeacher::getCo_num).reversed());
        System.out.println("一共"+courseList.size()+"条记录");
        System.out.println("========================================================");
        System.out.println("                           课程信息                       ");
        System.out.println("=========================================================");
        System.out.println("课程号\t课程名\t授课教师号\t教师姓名\t课程性质\t选课人数\t授课对象\t课程学时");
        for(CourseTeacher course:courseList){
            System.out.println(course.getCo_id()+'\t'+course.getCo_name()+'\t'+course.getCt_id()+'\t'+course.getTe_name()+'\t'+
                    course.getCo_nature()+'\t'+course.getCo_num()+'\t'+course.getCo_object()+'\t'+course.getCo_hours());
        }
        while (true){
            System.out.println("请输入您要申请的课程号:");
            String cid = sc.nextLine();
            Application application = new Application();
            application.setCo_id(cid);
            application.setSt_id(st_id);
            DAOFactory.getInstance().getApplicationDao().addApplication(application);
            System.out.println("申请成功!\n是否继续申请(是/否):");
            String str = sc.nextLine();
            if(str.equals("是"))
                continue;
            else break;
        }
    }

    public void Judge(String tid){
        Scanner sc = new Scanner(System.in);
        while(true){
            List<ApaTeacher> apaTeacherList=DAOFactory.getInstance().getApplicationDao().APA_TEACHER_LIST(tid);
            System.out.println("一共"+apaTeacherList.size()+"条记录");
            System.out.println("========================================");
            System.out.println("                  助教申请信息");
            System.out.println("========================================");
            System.out.println("申请编号\t课程号\t课程名\t学生学号\t学生姓名");
            for(ApaTeacher apaTeacher:apaTeacherList)
                System.out.println(String.valueOf(apaTeacher.getSa_id())+'\t'+apaTeacher.getCo_id()+'\t'+apaTeacher.getCo_name()
                        +'\t'+apaTeacher.getSt_id()+'\t'+apaTeacher.getSt_name());
            System.out.println("请输入您要选择的助教的申请表单号:");
            int sa_id = sc.nextInt();
            for(ApaTeacher apaTeacher:apaTeacherList){
                if(apaTeacher.getSa_id() == sa_id){
                    //更新课程表的助教编号字段
                    Course course=DAOFactory.getInstance().getCourseDAO().getCourse(apaTeacher.getCo_id());
                    course.setAssistance_id(apaTeacher.getSt_id());
                    DAOFactory.getInstance().getCourseDAO().updateCourse(course);
                    //更新学生表的是否为助教字段
                    Student student=DAOFactory.getInstance().getStudentDAO().getStudent(apaTeacher.getSt_id());
                    student.setIs_assistance(1);
                    DAOFactory.getInstance().getStudentDAO().updateStudent(student);
                    //创建助教评定表
                    Evaluate evaluate = new Evaluate();
                    evaluate.setCo_id(apaTeacher.getCo_id());
                    evaluate.setSt_id(apaTeacher.getSt_id());
                    DAOFactory.getInstance().getEvaluateDao().addEvaluate(evaluate);
                    //更新助教申请表
                    DAOFactory.getInstance().getApplicationDao().deleteApplication(apaTeacher.getCo_id());
                    System.out.println("选择成功!");
                    break;
                }
            }
            System.out.println("是否继续该操作(是/否):");
            Scanner sc1 = new Scanner(System.in);
            String str = sc1.nextLine();
            if(str.equals("是"))
                continue;
            else break;
        }
    }

    //助教填写助教评定表
    public void stuEvaluate(String st_id){
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        List<Evaluate> evaluateList=DAOFactory.getInstance().getEvaluateDao().EVALUATE_LIST(st_id);
        if(evaluateList.size()==0){
            System.out.println("您未担任助教，不可填写助教评定表!");
        }else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Evaluate evaluate:evaluateList){
                System.out.println("评定表单号："+evaluate.getEv_id());
                System.out.println("课程号:"+evaluate.getCo_id());
                System.out.println("学号："+evaluate.getSt_id());
                System.out.println("自我评价："+evaluate.getSelf_eva());
                System.out.println("提交日期："+evaluate.getSub_time());
                System.out.println("教师评语："+evaluate.getTe_eva());
                System.out.println("教师意见："+evaluate.getTe_idea());
                System.out.println("教师评价日期："+evaluate.getTe_time());
                System.out.println("==================================");
            }
            while (true){
                System.out.println("请输入您要填写的评定表单号:");
                int id = sc.nextInt();
                for(Evaluate evaluate:evaluateList){
                    if(id == evaluate.getEv_id()){
                        System.out.println("请输入您的自我评价(100字以内):");
                        String selt_eva=sc1.nextLine();
                        evaluate.setSelf_eva(selt_eva);
                        System.out.println("请输入提交日期(xx年xx月xx日):");
                        String time=sc1.nextLine();
                        evaluate.setSub_time(time);
                        DAOFactory.getInstance().getEvaluateDao().updateEvaluate(evaluate);
                        System.out.println("填写成功!");
                        break;
                    }
                }
                System.out.println("是否继续该操作(是/否):");
                String str = sc1.nextLine();
                if(str.equals("是"))
                    continue;
                else break;
            }
        }
    }

    public void teaEvaluate(String te_id){
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        List<Evaluate> evaluateList=DAOFactory.getInstance().getEvaluateDao().TEVALUATE_LIST(te_id);
        if(evaluateList.size()==0){
            System.out.println("您未担任助教，不可填写助教评定表!");
        }else {
            for (Evaluate evaluate:evaluateList){
                System.out.println("表单号:"+evaluate.getEv_id());
                System.out.println("助教编号:"+evaluate.getSt_id());
                System.out.println("课程编号:"+evaluate.getCo_id());
                System.out.println("助教自我评价:"+evaluate.getSelf_eva());
                System.out.println("提交日期："+evaluate.getSub_time());
                System.out.println("教师评语："+evaluate.getTe_eva());
                System.out.println("教师意见："+evaluate.getTe_idea());
                System.out.println("教师评价日期："+evaluate.getTe_time());
                System.out.println("====================================");
            }
            while (true){
                System.out.println("请输入您要填写的评定表单号:");
                int id = sc.nextInt();
                for(Evaluate evaluate:evaluateList){
                    if(id == evaluate.getEv_id()){
                        System.out.println("请输入您对助教的评价(100字以内):");
                        String te_eva=sc1.nextLine();
                        evaluate.setTe_eva(te_eva);
                        System.out.println("请输入您的建议(合格/不合格):");
                        String idea = sc1.nextLine();
                        evaluate.setTe_idea(idea);
                        System.out.println("请输入提交日期(xx年xx月xx日):");
                        String time=sc1.nextLine();
                        evaluate.setTe_time(time);
                        DAOFactory.getInstance().getEvaluateDao().updateEvaluate(evaluate);
                        System.out.println("填写成功!");
                        break;
                    }
                }
                System.out.println("是否继续该操作(是/否):");
                String str = sc1.nextLine();
                if(str.equals("是"))
                    continue;
                else break;
            }
        }

    }

    public void Allocation(){
        Scanner sc = new Scanner(System.in);
        while (true){
            List<Student> students = DAOFactory.getInstance().getStudentDAO().ISNOTAssistance();
            List<Course> courses = DAOFactory.getInstance().getCourseDAO().getCourses();
            int n;
            if(students.size()>=courses.size())
                n=courses.size();
            else
                n=students.size();
            System.out.println("共有"+students.size()+"位同学未选到助教课程");
            System.out.println("学号\t姓名");
            for (Student student:students){
                System.out.println(student.getSt_id()+'\t'+student.getSt_name());
            }
            for(int i=0;i<n;i++){
                Student student=students.get(i);
                student.setIs_assistance(1);
                //更新学生表
                DAOFactory.getInstance().getStudentDAO().updateStudent(student);
                Course course = courses.get(i);
                course.setAssistance_id(student.getSt_id());
                //创建助教评定表
                Evaluate evaluate = new Evaluate();
                evaluate.setCo_id(course.getCo_id());
                evaluate.setSt_id(student.getSt_id());
                DAOFactory.getInstance().getEvaluateDao().addEvaluate(evaluate);
                //更新课程表
                DAOFactory.getInstance().getCourseDAO().updateCourse(course);
            }
            System.out.println("分配成功!\n输入0退出：");
            int c = sc.nextInt();
            if(c == 0)
                break;
        }
    }
}
