package su.domain.beans.impl;

public class Test {
	Test(){
		System.out.println("constr");
		
	}
	static{
		System.out.println("static");
	}
	public static void main(String[] gf)throws Exception{
		Class c = Test.class;
		c.newInstance();
	}

}
