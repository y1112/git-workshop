package Array;

public class AccessArray {

	public static void main(String[] args) {
		// int 타입의 정수 데이터를 3개 저장할 수 있는 배열을 생성
		// 배열의 이름 arr로 선언
//		int arr[] = new int[3];
//		int[] arr=new int[3];
		
		int []arr;
		//배열 인스턴스 생성: 각 요소의 저장공간 생성
		
		arr=new int[3];
		
		//배열 요소의 참조(접근)
		arr[0]=1;
		arr[1]=2;
		arr[2]=3;
		
		int sum=arr[0]+arr[1]+arr[2];
		System.out.println(sum);
	}

}
