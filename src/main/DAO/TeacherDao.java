package main.DAO;

import main.pojo.Teacher;

import java.util.List;

public interface TeacherDao {
    void addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(String te_id);
    Teacher getTeacher(String te_id);
    List<Teacher> TEACHER_LIST(Teacher teacher);
}