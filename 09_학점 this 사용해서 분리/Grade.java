import java.io.*;
import java.util.*;

class Input{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Vector<String> v1 = new Vector<String>();
	Vector<Integer> v2 = new Vector<Integer>();
	int index;
	Show sw;

	Input(Show sw){
		try{
			this.sw = sw;
			inputIndex("\n과목의 개수를 입력해주세요: ");
			inputSb("\n과목을 입력해주세요: ");
			inputSc("\n과목의 점수를 입력해주세요: ");
			new Cla(this);
		}catch(IOException ie){}
	}
	void inputIndex(String ipid) throws IOException{
		try{
			sw.p(ipid);
			String line = br.readLine();
			line = line.trim();
			int index = Integer.parseInt(line);
			this.index = index;
		}catch(NumberFormatException ne){
			sw.pln("정수만 입력해주세요");
			inputIndex(ipid);
		}	
	}
	void inputSb(String sb) throws IOException{
		for(int i=0; i<index; i++){
			sw.p(sb);
			String sbj = br.readLine();
			sbj = sbj.trim();
			v1.add(sbj);
		}
	}
	void inputSc(String sc) throws IOException{
		try{
			sw.pln(sc);
			for (int j=0; j<v1.size(); j++){
				sw.p(v1.get(j) + ": ");
				String line = br.readLine();
				line = line.trim();
				int sbSc = Integer.parseInt(line);
				if(sbSc<0 || sbSc>100){
					sw.pln("0~100의 숫자만 입력해주세요.");
					inputSc(sc);
				}else{
					v2.add(sbSc);
				}
			}
		}catch(NumberFormatException ne){
			sw.pln("정수만 입력해주세요");
			inputSc(sc);
		}	
	}
}

class Cla{
	int total;
	int avg;
	String grade;
	Input ip;
	
	Cla(Input ip){
		this.ip = ip;
		sum();
		gradeSet();
		new Show(this);
	}
	void sum(){
		for(int i=0; i<ip.v2.size(); i++){
			total += ip.v2.get(i);
		}
		avg = total/ip.v2.size();
	}
	void gradeSet(){
		if(avg >= 98){
			grade = "A+";
		}else if(avg >= 95){
			grade = "A";
		}else if(avg >= 90){
			grade = "A-";
		}else if(avg >= 88){
			grade = "B+";
		}else if(avg >= 85){
			grade = "B";
		}else if(avg >= 80){
			grade = "B-";
		}else if(avg >= 78){
			grade = "C+";
		}else if(avg >= 75){
			grade = "C";
		}else if(avg >= 70){
			grade = "C-";
		}else if(avg >= 60){
			grade = "D";
		}else if(avg >= 50){
			grade = "E";
		}else{
			grade = "F";
		}
	}
}

class Show{
	Cla c;
	Show(){
		new Input(this);
	}
	Show(Cla c){
		this.c = c;
		show();
	}
	void show(){
		pln("\n당신의 학점은: " + c.grade + "(총점: " + c.total + "점, 평균: " + c.avg + "점 입니다.)");
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
}

class Grade {
	public static void main(String[] args){
		new Show();
	}
}
