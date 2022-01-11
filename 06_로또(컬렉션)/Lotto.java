import java.io.*;
import java.util.*;

class Lotto{
	String fName = "우리반";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	Random r = new Random();
	int i=0;
	int is=0;
	TreeSet<String> ts = new TreeSet<String>();
	FileReader fr = null;

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
				i++;
			}
		}catch(IOException ie){
		}
	}
	void setName(){
		try{
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);
			for(int j=0; j<i; j++){
				String line = brFile.readLine();
					ts.add(line);
			}
		}catch(IOException ie){
		}
	}
	void list(){
		is = r.nextInt(i);
		Iterator<String> name = ts.descendingIterator();
		for(int j=0; j<i; j++){
			String str = name.next();
			if(j==is){
				pln(str);
				break;
			}
		}
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		Lotto li = new Lotto();
		li.inputFName();
		li.setIndex();
		li.setName();
		li.list();
	}
}
