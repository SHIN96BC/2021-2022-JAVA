import java.io.*;
import java.util.*;

class Listing3{
	String fName = "우리반";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	Random r = new Random();
	int listNum=0;
	String name[];
	FileReader fr = null;
	int num=0;
	int lottoNum[];
	int shuffle=0;


	void inputFName(){
		p("#읽을 파일 이름(기본:우리반): ");
		try{
			fName = br.readLine();
			if(fName != null) fName = fName.trim();
			if(fName.length() == 0) fName = "우리반";
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);
		}catch(FileNotFoundException fe){
			pln("#파일을 찾을 수 없습니다.");
			inputFName();
		}catch(IOException fe){
		}
	}

	void setIndex(){
		int size = 0;
		try{
			String line = "";
			while((line = brFile.readLine()) != null){
				listNum++;
			}
		}catch(IOException ie){
		}
	}

	void setName(){
		name = new String[listNum];
		try{
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);
			for(int j=0; j<name.length; j++){
				String line = brFile.readLine();
					name[j] = line;
			}
		}catch(IOException ie){
		}
	}

	void lotto(){
		lottoNum = new int[name.length];
		for(int t=0; t<name.length; t++){
			lottoNum[t]=t;
		}
		for(int m=0; m<300; m++){
			int k = r.nextInt(name.length);
			shuffle=lottoNum[0];
			lottoNum[0]=lottoNum[k];
			lottoNum[k]=shuffle;
		}
		/*for(int k=0; k<lottoNum.length; k++){   // 번호 중복체크용
			System.out.println(lottoNum[k]);
		}*/
		
	}
	void number(){
		p("몇명을 뽑으시겠습니까?");
		try{
			String line = br.readLine();
			num = Integer.parseInt(line);
		}
		catch(IOException ie){}
		catch (NumberFormatException ne){
			pln("\n정수만 입력해주세요.");
			number();
		}
	}
	void show(){
		for(int h=0; h<num; h++){
			System.out.println(name[lottoNum[h]]);
		}
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		Listing3 li = new Listing3();
		li.inputFName();
		li.number();
		li.setIndex();
		li.setName();
		li.lotto();
		li.show();
	}
}
