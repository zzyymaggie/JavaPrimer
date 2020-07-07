package xyz.zzyymaggie.spring.ioc.ctor;

public class ClassSerivce {
    private StudentService studentService;
    public ClassSerivce(StudentService studentService) {
        this.studentService = studentService;
    }
}
