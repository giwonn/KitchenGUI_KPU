package testCom;

public class test {
	public static void main(String arg[])
	{
		String dd=null;
		String asd="아메리카노(R)(ICE)*2//카페라떼(L)(HOT)*3";
		String temp[]=asd.split("//");
		String menu=temp[0];
		if(menu.contains("(R)"))
		{
			int a=menu.indexOf("(R)");
			dd=menu.substring(a+1,a+2);
			
			System.out.println(dd);
		}
		
	}

}
