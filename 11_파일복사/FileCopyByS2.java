import java.io.*;

class copyOption2{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	FileInputStream fis;
	FileOutputStream fos;
	String fName;   // ������ġ
	String fCopy;
	String oriFName;

	copyOption2(){
		try{
			pln("1. ����   2. �߶󳻱�");
			String line = br.readLine();
			line = line.trim();
			int op = Integer.parseInt(line);
			if(op == 1){
				input();
				copy();
			}else if(op == 2){	
				input();
				copy();
				del();
			}else{
				pln("1 �� 2 ���� �ϳ��� �Է����ּ���");
				new copyOption2();
			}
		}catch(IOException ie){
		}
	}
	void input() throws IOException{
		try{
			p("�����������(/�� �Է����ּ���): ");
			fName = br.readLine();
			int i = fName.lastIndexOf("\\");
			String fn = fName.substring(i+1);
			fis = new FileInputStream(fName);
			p("������ ���� ��ġ(/�� �Է����ּ���): ");
			fCopy = br.readLine();
			
			File f = new File(fCopy);
			if(f.exists()){
				String str = fCopy + "\\" + fn;
				fos = new FileOutputStream(str);
			}else{
				pln("���������ʴ� ���丮! ������?(y/n)");
				String flag = br.readLine();
				if(flag.equals("y")){
					f.mkdirs();
					fos = new FileOutputStream(fCopy);
				}else if(flag.equals("n")){
					pln("������ �������� �ʽ��ϴ�\n");
					input();
				}else{
					pln("�Է°��� ������ġ�� �ƴմϴ�.");
					input();
				}
			}
		}catch(FileNotFoundException fe){
			pln("������ �������� �ʽ��ϴ�.");
			input();
		}
	}
	void copy() throws IOException{
		
		byte bs[] = new byte[1024];
		int i = 0;
		while((i=fis.read(bs)) != -1){
			fos.write(bs, 0, i);
		}
		fos.flush();
		pln("����Ϸ�");

		fis.close();
		fos.close();
	}
	void del(){
		File f = new File(fName);
		f.delete();
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
}
class FileCopyByS2{

	public static void main(String[] args) {
		new copyOption2();
	}
}
