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
		p("#���� ���� �̸�(�⺻:�츮��): ");
		try{
			fName = br.readLine();
			if(fName != null) fName = fName.trim();
			if(fName.length() == 0) fName = "�츮��";
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);
		}catch(FileNotFoundException fe){
			pln("#������ ã�� �� �����ϴ�.");
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
					pln("\n#������ �ʿ��Ͻø� r�� �Է��ϼ���.\n");
					p("#�˻��� �̸�: ");
					inputLine = br.readLine();
					if(inputLine != null) inputLine = inputLine.trim();
					if(inputLine.equals("r")){
						manual();
						show();
					}
					for(int i=0; i<v.size(); i++){
						String name = v.get(i);
						if(inputLine.equals(name)){
							pln("#�����ϴ� ȸ��");
							show();
						}
					}
					if(inputLine.equals("list")){
						for(String name: v) pln(name);
						show();
					}else if(inputLine.equals("file")){
						new MemberSearch2();
					}else if(inputLine.equals("exit")){
						pln("#�����մϴ�.");
						System.exit(0);
					}else {
						pln("#���������ʴ� ȸ��");
						show();
					}
				}
			}catch(IOException ie){}
		

		/*if(inputLine.equals("exit")){
			pln("#�����մϴ�.");
			return;
		}else{
			pln("#���������ʴ� ȸ��");
			show();
		}*/
	}
	void manual(){
		pln("## ���θ� ����Ͻ÷��� list, �����Ͻ÷��� Exit, ������ �ٲٽ÷��� file�� �Է����ּ���.\n");
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
