package xyz.zzyymaggie.spring.ioc.setter;

public class ClassService {
    private StudentService studentService;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void run() {
        System.out.println(studentService);
    }
}
