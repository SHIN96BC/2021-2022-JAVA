import java.io.*;
import java.util.*;

class LottoMulti {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	FileReader fr;
	Hashtable<Integer, String> h = new Hashtable<Integer, String>();
	Hashtable<Integer, String> h2 = new Hashtable<Integer, String>();
	String fName;
	int listNum = 1;
	int num;
	Random r = new Random();
	int lonum[];

	LottoMulti(){
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
			show2();
			out();
		}
		catch (FileNotFoundException fe){
			pln("파일이 존재하지 않습니다.\n");
			new LottoMulti();
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
		for(int i=1; i<=listNum; i++){
			if((line = brFile.readLine()) != null){
				if(line != null) line = line.trim();
				if(line.length() != 0) h.put(i,line);
			}
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
	void show2(){
		int key2=0;
		int k = 0;
		int i = 0;
		try{
			Set<Integer> keys = h.keySet();
			for(i=0; i<num; i++){
				k = r.nextInt(listNum);
				for(int key: keys){
					key2 = key;
					if(key == k){
						h2.put(key,h.get(key));
						h.remove(key,h.get(key));
						break;
					}
				}
				if(key2 != k){
					i--;
				}
			}
		}catch(java.util.ConcurrentModificationException cf){
		}	
	}
	void out(){
		Set<Integer> keys2 = h2.keySet();
		for(int key2: keys2) {
			if(key2<10){
				pln("번호: 0" + key2 + ", 이름: " + h2.get(key2));
			}else {
				pln("번호: " + key2 + ", 이름: " + h2.get(key2));
			}
		}
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args){
		new LottoMulti();
	}
}
