import java.io.*;

class Grade4 {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	int total;
	int avg;
	String grade;

	Grade4(){
		input("국어");
		input("영어");
		input("수학");
		input("일어");
		input("자바");
		input("파이썬");
		cal();
		show();

	}
	void input(String subject){
		p(subject + " : ");
		try{
			String line = br.readLine();
			line = line.trim();
			int i = Integer.parseInt(line);
			if(i > -1 && i < 101){
				total += i;
			}else {
				System.out.println("0~100까지의 숫자만 입력하세요.");
				input(subject);
			}
		}
		catch (IOException ie){}
		catch (NumberFormatException ne){
			System.out.println("정수만 입력해 주세요.");
			input(subject);
		}
	}
	void cal(){
		avg = total/6;
		int ag = avg/10;
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
	void show(){
		pln("\n당신의 학점은 " + grade + "입니다. (총점 : " + total + "평균 : " + avg + " 입니다.)");
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}

	public static void main(String[] args){
		new Grade4();
	}
}