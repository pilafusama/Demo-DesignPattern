
public class Demo {
	public static void main(String[] args) throws InterruptedException {
		Person p = new Person();

		System.out.println(p.getName());
		System.out.println(p.getName().equals("123"));
	}
}

class Person {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}


