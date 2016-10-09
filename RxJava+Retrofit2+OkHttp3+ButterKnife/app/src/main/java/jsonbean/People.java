package jsonbean;

import java.util.List;

public class People {
	private String name;
	private int age;
	private List<Interest> list;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Interest> getList() {
		return list;
	}
	public void setList(List<Interest> list) {
		this.list = list;
	}
	

}
