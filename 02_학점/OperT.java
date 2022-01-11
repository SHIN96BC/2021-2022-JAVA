import java.util.Scanner;

class OperT {
	Scanner sc = new Scanner(System.in);    // 키보드로부터 입력받는다.

	void input(){
		int sum = 0;
		int ag = 0;
		int[] jumsu = new int[6];     // 각 과목의 점수를 담을 배열 jumsu를 선언
		String subject[] = {"국어", "영어", "수학", "과학", "자바", "물리"};    // 과목 종류가 들어있는 subject 배열을 선언 

		System.out.println("\n @알림@  학점계산기를 실행합니다.\n\n");      // 학점계산기 실행
		
		for (int i=0; i<subject.length; i++ ){                     // i<subject.length 이 조건을 만족할때하면 실행
			System.out.println(subject[i] + "의 점수를 입력 하세요 : ");  // subject의 i 번째 들어있는 값을 출력
			int str = sc.nextInt();                                 // int 타입으로 값을 입력 받아서 str에 저장

			jumsu[i] += str;     // == jumsu[i] = jumsu[i] + str;
			sum += jumsu[i];     // == sum = sum + jumsu[i];
		}
		ag += sum/subject.length;                                   // == ag = ag + sum / subject.length
		System.out.println("\n" + subject[0] + "의 점수는 " + jumsu[0] + "점 입니다.");
		System.out.println(subject[1] + "의 점수는 " + jumsu[1] + "점 입니다.");
		System.out.println(subject[2] + "의 점수는 " + jumsu[2] + "점 입니다.");
		System.out.println(subject[3] + "의 점수는 " + jumsu[3] + "점 입니다.");
		System.out.println(subject[4] + "의 점수는 " + jumsu[4] + "점 입니다.");
		System.out.println(subject[5] + "의 점수는 " + jumsu[5] + "점 입니다.\n");
		System.out.println("총 점수는 " + sum + "점 입니다.");
		System.out.println("전 과목 평균 점수는 " + (sum/subject.length));	
		
		if (ag >= 90){System.out.print("당신의 학점은 'A' 입니다");}
		else if (ag >= 80){System.out.print("당신의 학점은 'B' 입니다");}
		else if (ag >= 70){System.out.print("당신의 학점은 'C' 입니다");}
		else if (ag >= 60){System.out.print("당신의 학점은 'D' 입니다");}
		else if (ag >= 50){System.out.print("당신의 학점은 'E' 입니다");}
		else {System.out.print("당신의 학점은 'F' 입니다");}
	
		System.out.println("\n\n학점계산기를 재실행하고 싶으시면 1번을, 종료하시려면 아무 숫자나 눌러주세요.");
		int re = sc.nextInt();
		if (re == 1){     // 입력받은 값이 1이면 if문 안에 있는 코드를 실행한다.
			System.out.println(" @알림@  학점계산기를 재실행 합니다.");
			input();
		}
		else{             // 입력받은 값이 1이 아닌 다른 숫자이면 else문 안에 있는 코드를 실행한다.
			System.out.println("\n @알림@  학점계산기를 종료 합니다.");
		}
	}
	

	public static void main(String[] args){
		OperT ip = new OperT();
		ip.input();  
	}
}