package jsonbean;

import java.util.ArrayList;
import java.util.List;

public class GetJson {
//	private Interest interest;
//	private People people;
	private static List<Interest> list;
	private static List<Interest> list1;
	private static List<Interest> list2;
	private static List<Interest> list3;
	private static List<Interest> list4;
	private static List<Interest> list5;
	private static List<People> list6;
	
	
	
	
	public static JsonDataClass getJson(){
		//��һ��
		Interest interest1 = new Interest();
		interest1.setName("������");
		Interest interest2 = new Interest();
		interest2.setName("ˣ�ɵ�");
		Interest interest3 = new Interest();
		interest2.setName("�Ⱦ�");
		list = new ArrayList<Interest> ();
		list.add(interest1);
		list.add(interest2);
		list.add(interest3);
		People people1 = new People();
		people1.setName("С��ɵ�");
		people1.setAge(32);
		people1.setList(list);
		//�ڶ���
		
		list1 = new ArrayList<Interest> ();
		list1.add(interest1);
		list1.add(interest2);
		list1.add(interest3);
		People people2 = new People();
		people2.setName("����");
		people1.setAge(32);
		people1.setList(list1);
		//������
		
		list2 = new ArrayList<Interest> ();
		list2.add(interest1);
		list2.add(interest2);
		list2.add(interest3);
		People people3 = new People();
		people3.setName("�Ƿ�");
		people3.setAge(32);
		people3.setList(list2);
		//���ĸ�
		
		list3 = new ArrayList<Interest> ();
		list3.add(interest1);
		list3.add(interest2);
		list3.add(interest3);
		People people4 = new People();
		people4.setName("������");
		people4.setAge(32);
		people4.setList(list3);
		//�����
		
		list4 = new ArrayList<Interest> ();
		list4.add(interest1);
		list4.add(interest2);
		list4.add(interest3);
		People people5 = new People();
		people5.setName("���޼�");
		people5.setAge(32);
		people5.setList(list4);
		
	//��������
		JsonDataClass top = new JsonDataClass();
		list6 = new ArrayList<People>();
		list6.add(people1);
		list6.add(people2);
		list6.add(people3);
		list6.add(people4);
		list6.add(people5);
		top.setList(list6);
		return top;
	}
	

}
