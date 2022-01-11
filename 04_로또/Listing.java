import java.io.*;
import java.util.*;

class Listing{
	String fName = "우리반";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	Random r = new Random();
	String name;
	void inputFName(){
		FileReader fr = null;
		p("#읽을 파일 이름: ");
		try{
			fName = br.readLine();
			if(fName != null)
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);
		}catch(FileNotFoundException fe){
			pln("#파일을 찾을 수 없습니다.");
			inputFName();
		}catch(IOException fe){
		}
	}
	void list(){
		int i = r.nextInt(30);
		try{
			for(int j=0; j<30; j++){
				String line = brFile.readLine();
				if(j==i){
					pln(line);
					break;
				}
			}
		}catch(IOException ie){
		}
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		Listing li = new Listing();
		li.inputFName();
		li.list();
	}
}
