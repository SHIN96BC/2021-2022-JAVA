import java.io.*;
import java.util.*;

class LottoD {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	FileReader fr;
	int index;
	Vector<String> v = new Vector<String>();
	String rateName;
	int rate;
	String name;
	Random r = new Random();

	LottoD(){
		try{
			setIndex();
			setName();
			lotto();
		}catch(IOException ie){
		}catch(NumberFormatException ne){
		}
	}
	void setIndex() throws IOException{
		fr = new FileReader("list.txt");
		brFile = new BufferedReader(fr);
		String line = "";
		while((line = brFile.readLine()) != null){
			line = line.trim();
			if(line.length() != 0){
				index++;
			}
		}
	}
	void setName() throws IOException, NumberFormatException{
		fr = new FileReader("list.txt");
		brFile = new BufferedReader(fr);
		String line = "";
		while((line = brFile.readLine()) != null){
			line = line.trim();
			if(line.length() > 3){
				int idx = line.lastIndexOf(" ");
				name = line.substring(0, idx);
				name = name.trim();
				String lineRate = line.substring(idx+1);
				lineRate = lineRate.trim();
				rate = Integer.parseInt(lineRate);
				if(rate<0 || rate > 100){
					pln("0~100까지의 숫자만 입력하세요");
					System.exit(0);
				}
				rateName = name;

			}else if(line.length() == 3){
				v.add(line);
			}
		}
	}
	void lotto(){
		int lo1 = r.nextInt(100);
		int lo2 = r.nextInt(index);
		if(rate>lo1){
			pln(rateName);
		}else{
			pln(v.get(lo2));
		}
	}
	/*void out(){
		for(String name2: v) pln(name2);
		System.out.println(rate);
		pln(rateName);
	}*/
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args){
		new LottoD();
	}
}
