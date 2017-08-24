package xyz.zzyymaggie.spring.aop;

public class Test1Service implements TestServiceInter, TestServiceInter2 {
	private String name;

	public void sayHello() {
		System.out.println("Hi " + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void sayBye() {
		System.out.println("bye " + name);
//		int a = 1/0;
	}

}
