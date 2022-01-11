import java.io.*;

class Grade1 {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int total;
	int avg;
	String grade;

	Grade1(){
		input("국어");
		input("영어");
		input("수학");

		cal();
		show();
	}
	void input(String subject){
		p(subject + ": ");
		try{
			String line = br.readLine();
			line = line.trim();
			int i = Integer.parseInt(line);
			if(i<0 || i>100){
				pln("0~100점 가능합니다");
				input(subject);
			}else{
				total = total + i;
			}
		}catch(IOException ie){
		}catch(NumberFormatException ne){
			pln("숫자만 입력하세요");
			input(subject);
		}
	}
	/*
	void cal(){ //if문 
		avg = total/3;
		if(avg >= 90){
			grade = "A";
		}else if(avg >= 80) {
			grade = "B";
		}else if(avg >= 70) {
			grade = "C";
		}else if(avg >= 60) {
			grade = "D";
		}else{
			grade = "F";
		}
	}*/
    void cal(){ //switch문 
		avg = total/3;
		int po = avg/10;

		switch(po){
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
		pln("당신의 학점은 "+grade+"(총점:"+total+", 평균:"+avg+") 입니당");
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new Grade1();
	}
}

//점수입력(유효성체크) -> 총점/평균/학점 -> 출력 

