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
			p("������ �̸��� �Է��ϼ���: ");
			fName = br.readLine();
			if(fName != null) fName.trim();
			if(fName.length() == 0) fName = "�츮��";
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
			pln("������ �������� �ʽ��ϴ�.\n");
			new LottoMulti();
		}catch(IOException ie){
			pln("������ �о������ ���ܹ߻�");
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
		p("����� �����ðڽ��ϱ�?");
		try{
			String line = br.readLine();
			num = Integer.parseInt(line);
			if(listNum<num){
				pln("�Ѽ����� ���� ���� �Է��ϼ̽��ϴ�. (�Ѽ���:" + listNum + "�� �Դϴ�.)");
				lotto();
			}else if(num<0){
				pln("0���� �������� �Է��ϼ̽��ϴ�. (�Ѽ���:" + listNum + "�� �Դϴ�.)");
				lotto();
			}else if(num == 0){
				pln("���� �ο����� �Է����ּ���. (�Ѽ���:" + listNum + "�� �Դϴ�.)");
				lotto();
			}
		}
		catch(NumberFormatException ne){
			pln("������ �Է����ּ���\n");
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
							pln("��ȣ: 0" + key + ", �̸�: " + h.get(key));
							break aa;
						}else {
							pln("��ȣ: " + key + ", �̸�: " + h.get(key));
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
