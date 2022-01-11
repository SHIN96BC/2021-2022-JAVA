import java.io.*;

class Grade5 {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String[] subject = new String[6];
	String grade;
	int[] score = new int[6];
	int total;
	int avg;

	Grade5(){
		pln("\n#알림#  학점계산기를 실행합니다.\n");
		inputSb("1. 먼저 과목명을 6개 입력해주세요.");
		inputSc("\n 2. 다음으로 점수를 입력해주세요.");
		cal();
		show("\n#알림#  최종결과입니다.\n");
		re("\n#알림#  학점계산기를 재실행 하시려면 1을, 종료하시려면 숫자를 아무거나 입력해주세요.");
	}	
	void inputSb(String sb){
		pln(sb);
		try{
			for(int i=0; i < subject.length; i++){
				p((i + 1) + "번째 과목: ");
				String line = br.readLine();
				line = line.trim();
				subject[i] = line;
			}
		}
		catch (IOException ie){}
	}
	void inputSc(String sc){
		pln(sc);
		try{
			for(int j=0; j < subject.length; j++){
				p(subject[j] + ": ");
				String line = br.readLine();
				line = line.trim();
				int i = Integer.parseInt(line);
				if(i < 0 || i > 100){
					pln("0~100의 숫자만 입력해주세요.");
					inputSc(sc);
				}else{
					score[j] = i;
					total += score[j];
				}
			}
		}
		catch (IOException ie){}
		catch (NumberFormatException ne){
			pln("\n정수만 입력해주세요.");
			inputSc(sc);
		}
	}
	void cal(){
		avg = total/subject.length;
		int av = avg/10;
		switch(av){
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
		case 5: 
			grade = "E";
			break;
		default : grade = "F";
		}
	}
	void show(String sw){
		pln(sw);
		for(int k=0; k < subject.length; k++){
			pln(subject[k] + ": " + score[k] + "점");
		}
		pln("\n당신의 학점은 " + grade + "입니다. (총점 : " + total + "점, 평균 : " + avg + "점)");
	}
	void re(String reset){
		pln(reset);
		try{
			String line = br.readLine();
			line = line.trim();
			int i = Integer.parseInt(line);
			if(i != 1){
				pln("\n#알림#  학점계산기를 종료합니다.");
			}else {
				pln("\n#알림#  학점계산기를 재실행 합니다.\n\n");
				new Grade5();
			}
		}
		catch(IOException ie){}
		catch(NumberFormatException ne){
			pln("정수만 입력해주세요.\n");
			re(reset);
		}
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args){
		new Grade5();
	}	
}