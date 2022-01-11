import java.io.*;
import java.util.*;

class Listing2{
	String fName = "우리반";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	Random r = new Random();
	int i=0;
	String name1[];
	FileReader fr = null;
	int num=0;
	String name2[];


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
		name1 = new String[i];
		try{
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);
			for(int j=0; j<name1.length; j++){
				String line = brFile.readLine();
					name1[j] = line;
			}
		}catch(IOException ie){
		}
	}

	void list(){
		name2 = new String[num];
		for(int k=0; k<num; k++){
			for(int m=0; m<name2.length; m++){
				i = r.nextInt(name1.length);
				name2[m] = name1[i];
			}
		}
		for(int h=0; h<name2.length; h++){
			for(int t=h+1; t<name2.length; t++){
					if(name2[h]==name2[t]){
					name2[h] = null;
				}
			}
		}
		for(int y=0; y<name2.length; y++){
			
		}
	}
	void show(){
		for(int g=0; g<name2.length; g++){
				pln(name2[g]);
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
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		Listing2 li = new Listing2();
		li.inputFName();
		li.number();
		li.setIndex();
		li.setName();
		li.list();
		li.show();
	}
}
