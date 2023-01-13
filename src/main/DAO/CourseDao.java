package main.DAO;

import main.ApplyAssistance.CourseTeacher;
import main.pojo.Course;

import java.util.List;

public interface CourseDao {
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(String co_id);
    Course getCourse(String co_id);
    List<CourseTeacher> COURSE_LIST();
    List<Course> getCourses();
}