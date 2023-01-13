package main.DAO;

import main.DAO.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    static {
        daoFactory = new DAOFactory();
    }
    private DAOFactory(){
    }
    public static DAOFactory getInstance(){
        return daoFactory;
    }
    public AdminDao getAdminDAO(){
        return new AdminDaoImpl();
    }
    public GraduateDao getGraduateDAO(){
        return new GraduateDaoImpl();
    }
    public MentorDao getMentorDAO(){
        return new MentorDaoImpl();
    }
    public ProjectDao getProjectDAO(){
        return new ProjectDaoImpl();
    }
    public Project_indentifyDao getProject_indentifyDAO(){
        return new Project_indentifyDaoImpl();
    }
    public Project_leaderDao getProject_leaderDAO(){ return new Project_leaderDaoImpl(); }
    public StudentDao getStudentDAO(){
        return new StudentDaoImpl();
    }
    public SubjectDao getSubjectDAO(){
        return new SubjectDaoImpl();
    }
    public AcademicDao getAcademicDao(){
        return new AcademicDaoImpl();
    }

    public CourseDao getCourseDAO(){
        return new CourseDaoImpl();
    }
    public TeacherDao getTeacherDao(){
        return new TeacherDaoImpl();
    }

    public ApplicationDao getApplicationDao(){
        return new ApplicationDaoImpl();
    }
    public EvaluateDao getEvaluateDao(){
        return new EvaluateDaoImpl();
    }


    public Me_StDAO getMe_StDAO() {
        return new Me_StDAOImpl();
    }

    public VerifyDAO getVerifyDAO() {
        return new VerifyDAOImpl();
    }
    public ThesisDAO getThesisDAO() {
        return new ThesisDAOImpl();
    }
    public AwardDAO getAwardDAO() {
        return new AwardDAOImpl();
    }
    public PatentDAO getPatentDAO() {
        return new PatentDAOImpl();
    }
    public ReportDAO getReportDAO() {
        return new ReportDAOImpl();
    }
    public StandardDAO getStandardDAO() {
        return new StandardDAOImpl();
    }
    public TextbookDAO getTextbookDAO() {
        return new TextbookDAOImpl();
    }
    public DevelopmentDAO getDevelopmentDAO() {
        return new DevelopmentDAOImpl();
    }
    public OtherDAO getOtherDAO() {
        return new OtherDAOImpl();
    }
}
