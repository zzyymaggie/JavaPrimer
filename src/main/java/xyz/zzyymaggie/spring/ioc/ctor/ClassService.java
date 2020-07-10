package xyz.zzyymaggie.spring.ioc.ctor;

public class ClassService {
    private StudentService studentService;
    public ClassService(StudentService studentService) {
        this.studentService = studentService;
    }
}
