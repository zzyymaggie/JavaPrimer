package xyz.zzyymaggie.spring.ioc.setter;

public class StudentService {

    private ClassService classService;

    public void setClassService(ClassService classService) {
        this.classService = classService;
    }

    public void run() {
        System.out.println(classService);
    }
}
