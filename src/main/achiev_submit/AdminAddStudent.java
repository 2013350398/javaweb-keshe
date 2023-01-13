
package main.achiev_submit;

import main.DAO.DAOFactory;
import main.pojo.Me_St;
import main.pojo.Student;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AdminAddStudent {
    // 建立导师和学生的联系
    public void addMe_St() {
        // 单个插入
        System.out.println("输入导师ID: ");
        Scanner sc = new Scanner(System.in);
        String me_id = sc.next();
        System.out.println("输入学生ID: ");
        sc = new Scanner(System.in);
        String st_id = sc.next();

        Me_St me_st = new Me_St(me_id, st_id);
        DAOFactory.getInstance().getMe_StDAO().insert(me_st);
    }

    public void addMe_StByExcel() {
        Scanner sc = new Scanner(System.in);
        String filePath = sc.next();
        try {
            Workbook workbook = Workbook.getWorkbook(new File(filePath));
            Sheet sheet = workbook.getSheet(0);
            for(int i = 1; i < sheet.getRows(); i ++) {
                String me_id = sheet.getCell(0, i).getContents();
                String st_id = sheet.getCell(1, i).getContents();
                DAOFactory.getInstance().getMe_StDAO().insert(new Me_St(me_id, st_id));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (BiffException e) {
            throw new RuntimeException(e);
        }
    }

    // 生成导师名下的学生账号
    public void addStUser(List<Student> students) {
        // 传入参数和生成形式？？？
        // 需要调学生管理（？）那边的接口，这边表没建全
    }
}