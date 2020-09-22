package 테스트용;

public class 메인 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// ThreadTest => extends 받음.
		ThreadTest Ta = new ThreadTest();
		
//		Ta.run();
//		Ta.run();
		Ta.start();
//		Ta.start();
		Ta.run();
		// run() 1번씩 차례대로 해서 1~10 2번 나오고.
		// 같은 thread 동시 start 시에 하나는 오류.
		
		// run을 만나면 run 내용 안을 다 하고 넘어감.
		// start는 바로 실행하고 다음으로 넘어감.
	}

}
