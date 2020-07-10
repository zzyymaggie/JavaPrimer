package xyz.zzyymaggie.spring.ioc.ctor;

public class StudentService {

    public StudentService(ClassService classService) {
        System.out.println(classService);
    }
}
