import java.io.*;
import java.util.*;

class Listing4{
	String fName = "우리반";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	Random r = new Random();
	Vector<String> v = new Vector<String>();
	int listNum=0;
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
		try{
			String line = "";
			while((line = brFile.readLine()) != null){
				listNum++;
			}
		}catch(IOException ie){
		}
	}
	void setName(){
		try{
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);
			for(int j=0; j<listNum; j++){
				String line = brFile.readLine();
					v.add(line);
			}
		}catch(IOException ie){
		}
	}
	void lotto(){
		lottoNum = new int[listNum];
		for(int t=0; t<listNum; t++){
			lottoNum[t]=t;
		}
		for(int m=0; m<300; m++){
			int k = r.nextInt(listNum);
			shuffle=lottoNum[0];
			lottoNum[0]=lottoNum[k];
			lottoNum[k]=shuffle;
		}
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
		for(int y=0; y<num; y++){
			String str = v.get(lottoNum[y]);
			pln(str);
		}
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		Listing4 li = new Listing4();
		li.inputFName();
		li.number();
		li.setIndex();
		li.setName();
		li.lotto();
		li.show();
	}
}
