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
			p("������ �̸��� �Է��ϼ���: ");
			fName = br.readLine();
			if(fName != null) fName.trim();
			if(fName.length() == 0) fName = "�츮��";
			fr = new FileReader(fName + ".txt");
			brFile = new BufferedReader(fr);
			setIndex();
			readF();
			lotto();
			show2();
			out();
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
				pln("��ȣ: 0" + key2 + ", �̸�: " + h2.get(key2));
			}else {
				pln("��ȣ: " + key2 + ", �̸�: " + h2.get(key2));
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
