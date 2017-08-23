package xyz.zzyymaggie.spring.aop;

public class Test2Service implements TestServiceInter {
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

}
