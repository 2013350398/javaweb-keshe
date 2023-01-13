package main.DAO;

import main.pojo.Student;
import java.util.List;

//学生
public interface StudentDao {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(String st_id);
    Student getStudent(String st_id);
    List<Student> findStudents(Student student);
    List<Student> ISNOTAssistance();
}
