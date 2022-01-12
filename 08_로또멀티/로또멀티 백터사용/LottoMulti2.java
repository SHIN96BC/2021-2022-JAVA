import java.io.*;
import java.util.*;

class LottoMulti2 {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	FileReader fr;
	Vector<String> v1 = new Vector<String>();
	Vector<String> v2 = new Vector<String>();
	String fName;
	int listNum;
	int num;
	Random r = new Random();

	LottoMulti2(){
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
			show();
			out();
		}
		catch (FileNotFoundException fe){
			pln("������ �������� �ʽ��ϴ�.\n");
			new LottoMulti2();
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
		while((line = brFile.readLine()) != null){
			if(line != null) line = line.trim();
			if(line.length() != 0) v1.add(line);
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
	void show(){     // ���� �ΰ� ����ؼ� v2�� �̸��ѹ� ������ v1������ �� �̸��� �������༭ �ߺ��� ����
		for(int i=0; i<num; i++){
			int k = r.nextInt(v1.size());
			v2.add(v1.get(k));
			v1.remove(k);
		}
	}
	void out(){
		for(String name: v2) pln("��÷��: " + name);
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args){
		new LottoMulti2();
	}
}
