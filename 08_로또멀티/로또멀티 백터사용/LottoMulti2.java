import java.io.*;
import java.util.*;

class LottoMulti2 {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	FileReader fr;
	Vector<String> v1 = new Vector<String>();
	Vector<String> v2 = new Vector<String>();
	String fName;
	int listNum;
	int num;
	Random r = new Random();

	LottoMulti2(){
		try{
			p("파일의 이름을 입력하세요: ");
			fName = br.readLine();
			if(fName != null) fName.trim();
			if(fName.length() == 0) fName = "우리반";
			fr = new FileReader(fName + ".txt");
			brFile = new BufferedReader(fr);
			setIndex();
			readF();
			lotto();
			show();
			out();
		}
		catch (FileNotFoundException fe){
			pln("파일이 존재하지 않습니다.\n");
			new LottoMulti2();
		}catch(IOException ie){
			pln("파일을 읽어오던중 예외발생");
		}
	}
	void setIndex() throws IOException{
		String line = null;
		while((line = brFile.readLine()) != null){
			listNum++;
		}
	}
	void readF() throws IOException{
		fr = new FileReader(fName + ".txt");
		brFile = new BufferedReader(fr);
		String line = "";
		while((line = brFile.readLine()) != null){
			if(line != null) line = line.trim();
			if(line.length() != 0) v1.add(line);
		}
	}
	void lotto() throws IOException{
		p("몇명을 뽑으시겠습니까?");
		try{
			String line = br.readLine();
			num = Integer.parseInt(line);
			if(listNum<num){
				pln("총수보다 많은 수를 입력하셨습니다. (총수는:" + listNum + "명 입니다.)");
				lotto();
			}else if(num<0){
				pln("0보다 작은수를 입력하셨습니다. (총수는:" + listNum + "명 입니다.)");
				lotto();
			}else if(num == 0){
				pln("뽑을 인원수를 입력해주세요. (총수는:" + listNum + "명 입니다.)");
				lotto();
			}
		}
		catch(NumberFormatException ne){
			pln("정수만 입력해주세요\n");
			lotto();
		}
	}
	void show(){     // 백터 두개 사용해서 v2에 이름한번 넣을때 v1에서는 그 이름을 삭제해줘서 중복을 방지
		for(int i=0; i<num; i++){
			int k = r.nextInt(v1.size());
			v2.add(v1.get(k));
			v1.remove(k);
		}
	}
	void out(){
		for(String name: v2) pln("당첨자: " + name);
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args){
		new LottoMulti2();
	}
}
