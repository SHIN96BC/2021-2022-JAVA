import java.io.*;

class Grade2 {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
	int total;
	int avg;
	int ag;
	String grade;

	Grade2(){
		input("학점 게산기를 시작합니다. (아무 숫자나 입력해주세요.)");
		input("\n일어");
		input("코딩");
		input("영어");
		cal();
		show();
		re();
	}
	void input(String subject){
		p(subject + " : ");
		try{
			String line = br.readLine();
			line = line.trim();
			int i = Integer.parseInt(line);
			if(i < 0 || i>100){
				pln("0~100까지의 숫자만 입력해주세요.");
				input(subject);
			}else {
				total += i;
			}	
		}catch(IOException ie){
		}catch(NumberFormatException e){
			pln("정수만 입력해주세요.");
			input(subject);
		}
	}
	void cal(){
		avg = total/3;
		ag = avg/10;
		switch(ag){
		case 10: 
		case 9: 
			grade = "A";
			break;
		case 8: 
			grade = "B";
			break;
		case 7: 
			grade = "C";
			break;
		case 6: 
			grade = "D";
			break;
		default : grade = "F";
		}
	}
	void re(){
		pln("\n학점 계산기를 재실행하고 싶으시면 1을 종료하고 싶으시면 아무 숫자나 입력해 주세요.");
		try{
			String line = br2.readLine();
			line = line.trim();
			int i = Integer.parseInt(line);
			if(i == 1){
				pln("학점 계산기를 재실행 합니다.\n");
				new Grade2();
			}else {
				pln("\n학점 계산기를 종료합니다.");
			}
		}catch(IOException ie){
		}catch(NumberFormatException e){
			pln("정수만 입력해주세요.");
			re();
		}
	}
	void show(){
		pln("당신의 학점은 " + grade +"( 총점 : " + total + "점, 평균 : " + ag + "점 입니다.)");
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new Grade2();
	}
}
