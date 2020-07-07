package xyz.zzyymaggie.spring.ioc.ctor;

public class StudentService {

    public StudentService(ClassSerivce classSerivce) {
        System.out.println(classSerivce);
    }
}
