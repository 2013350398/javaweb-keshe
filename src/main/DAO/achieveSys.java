package main.DAO;


import main.achiev_submit.AdminAddStudent;
import main.achiev_submit.AdminVerifyController;
import main.achiev_submit.MentorVerifyController;
import main.achiev_submit.StudentSubmitController;
import main.pojo.Admin;
import main.pojo.Mentor;
import main.pojo.Student;

        import java.util.Scanner;

public class achieveSys {
    // 其实是登录之后已经知道身份了
    public static void main(String[] args) {
        AdminAddStudent aad = new AdminAddStudent();
        AdminVerifyController avc = new AdminVerifyController();
        MentorVerifyController mvc = new MentorVerifyController();
        StudentSubmitController ssc = new StudentSubmitController();

        /**
         * 学生的业务逻辑
         */

        Student student = new Student();    // 假如这个是传进来的学生对象好了
        System.out.println("请选择:\n" +
                "1. 提交成果\n" +
                "2. 查看审核情况");
        Scanner sc = new Scanner(System.in);
        Integer choice = sc.nextInt();
        switch(choice) {
            case 1:
                // if(student.getSt_type().equals("博士研究生"))  // 本地数据库没有放这个字段，汇总就可以用了
                if(student.getSt_id().equals("博士"))     // 用一个本地有的字段随便测一测
                    ssc.PhDsubmitMenu(student.getSt_id());
                else
                    ssc.MDsubmitMenu(student.getSt_id());
                break;
            case 2:
                ssc.selectVerifyMenu(student.getSt_id());
                break;
            default:
                break;
        }

        /**
         * 导师的业务逻辑
         */
        /**
         Mentor mentor = new Mentor();   // 假如这个是传进来的导师
         System.out.println("请选择: \n" +
         "1. 查看成果审核信息\n" +
         "2. 查看提交成果详细信息\n" +
         "3. 修改成果初审状态\n");
         Scanner sc = new Scanner(System.in);
         Integer choice = sc.nextInt();

         switch (choice) {
         case 1:
         mvc.selectBySelf(mentor.getMe_id());
         break;
         case 2:
         mvc.verifyAchiev(mentor.getMe_id());
         break;
         case 3:
         mvc.mentorFirstVerify(mentor.getMe_id());
         break;
         default:
         break;
         }
         */

        /**
         * 研究生管理员的业务逻辑
         */
        /**
         Admin admin = new Admin();  // 加入这个是登录的研究生管理员
         System.out.println("请选择: \n" +
         "1. 查看成果审核信息\n" +
         "2. 查看提交成果详细信息\n" +
         "3. 修改成果终审状态\n");
         Scanner sc = new Scanner(System.in);
         Integer choice = sc.nextInt();

         switch (choice) {
         case 1:
         avc.select(admin.getAd_id());
         break;
         case 2:
         avc.verifyAchiev(admin.getAd_id());
         break;
         case 3:
         avc.lastVerify(admin.getAd_id());
         break;
         default:
         break;
         }
         */

        /**
         * 新建导师学生联系
         */
        /**
         System.out.println("请选择:\n" +
         "1. 单个输入\n" +
         "2. Excel文件批量导入\n");
         Scanner sc = new Scanner(System.in);
         Integer choice = sc.nextInt();

         switch(choice) {
         case 1:
         aad.addMe_St();
         break;
         case 2:
         aad.addMe_StByExcel();
         break;
         default:
         break;
         }
         */
    }
}