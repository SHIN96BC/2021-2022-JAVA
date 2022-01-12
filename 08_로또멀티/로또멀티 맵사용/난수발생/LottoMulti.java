import java.io.*;
import java.util.*;

class LottoMulti {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	FileReader fr;
	Hashtable<Integer, String> h = new Hashtable<Integer, String>();
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
			lottonum();
			show();
			//out();
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
	void lottonum(){
		lonum = new int[listNum];
		int shuffle=0;
		for(int i=0; i<listNum; i++){
			lonum[i]=i;
		}
		for(int j=0; j<300; j++){
			int k = r.nextInt(listNum);
			shuffle = lonum[0];
			lonum[0] = lonum[k];
			lonum[k] = shuffle;
		}
	}
	void show(){
		for(int i=1; i<=num; i++){
			aa:
			for(int j=1; j<listNum+1; j++){
				Set<Integer> keys = h.keySet();
				for(int key: keys){
					if(lonum[i] == key){
						if(key<10){
							pln("번호: 0" + key + ", 이름: " + h.get(key));
							break aa;
						}else {
							pln("번호: " + key + ", 이름: " + h.get(key));
							break aa;
						}
					}
				}
			}
		}
	}
	
	void show2(){
		for(int i=0; i<num; i++){

		}
	}
	void out(){
		Set<Integer> keys = h.keySet();
		for(int key: keys) pln("["+ key +"]:" + h.get(key));

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
