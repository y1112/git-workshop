package inheritance;

public class IpTv extends Tv {
	String ipTvName = "daumipTv";

	void display() {
		super.display();
		System.out.println(ipTvName + "IpTv를 봅니다");
	}
}
