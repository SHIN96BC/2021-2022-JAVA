import java.io.*;
import java.util.*;

class MemberSearch2 {
	String fName;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	FileReader fr;
	Vector<String> v = new Vector<String>();
	String inputLine;

	MemberSearch2(){
		inputName();
		in();
		show();
	}
	void inputName(){
		p("#읽을 파일 이름(기본:우리반): ");
		try{
			fName = br.readLine();
			if(fName != null) fName = fName.trim();
			if(fName.length() == 0) fName = "우리반";
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);
		}catch(FileNotFoundException fe){
			pln("#파일을 찾을 수 없습니다.");
			inputName();
		}catch(IOException ie){}
	}
	void in(){
		try{
			String line = null;
			while((line = brFile.readLine()) != null){
				if(line != null) line = line.trim();
				if(line.length() != 0) v.add(line);
			}
		}catch(IOException ie){}
	}
	/*void out(){
		for(String name : v) pln(name);
	}*/
	void show(){
			try{
				aa:
				while(true){
					pln("\n#설명이 필요하시면 r을 입력하세요.\n");
					p("#검색할 이름: ");
					inputLine = br.readLine();
					if(inputLine != null) inputLine = inputLine.trim();
					if(inputLine.equals("r")){
						manual();
						show();
					}
					for(int i=0; i<v.size(); i++){
						String name = v.get(i);
						if(inputLine.equals(name)){
							pln("#존재하는 회원");
							show();
						}
					}
					if(inputLine.equals("list")){
						for(String name: v) pln(name);
						show();
					}else if(inputLine.equals("file")){
						new MemberSearch2();
					}else if(inputLine.equals("exit")){
						pln("#종료합니다.");
						System.exit(0);
					}else {
						pln("#존재하지않는 회원");
						show();
					}
				}
			}catch(IOException ie){}
		

		/*if(inputLine.equals("exit")){
			pln("#종료합니다.");
			return;
		}else{
			pln("#존재하지않는 회원");
			show();
		}*/
	}
	void manual(){
		pln("## 전부를 출력하시려면 list, 종료하시려면 Exit, 파일을 바꾸시려면 file을 입력해주세요.\n");
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args){
		new MemberSearch2();
		//m.out();
	}
}
