package api;

public class StringTest {

	public static void main(String[] args) {
		String str1="My String";
		String str2="My String";
		String str3="Your String";
		
		//참조변수의 주소값 비교
		System.out.println(str1==str2);
		System.out.println(str1==str3);
		
		System.out.println("-----------------------------------");
		java.lang.String str="My name is JAVA";
		
		int strLength=str.length();
		System.out.println("문자열 str의 길이:"+strLength);
		
		int strLength2="한글의 길이는?".length();
		System.out.println("한글 문자열의 길이:"+strLength2);
		
	}

}
