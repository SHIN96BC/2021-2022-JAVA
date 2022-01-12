import java.io.*;

class DirCopy2 {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String oPath;    // ������ ���� �Ǵ� ���丮
	String cPath;    // ������ ���丮 ��ġ
	String oFname;   // ������ �������� �����̸�
	String dName;    // ���� ���� ������
	FileInputStream fis;
	FileOutputStream fos;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	File fo;
	File fc;
	FileInputStream fis2;
	String myPath;    // ���� ���丮��ġ


	void inputOri(){
		try{
			File ff = new File("");              // �������� ��ġ�� ã�� ����
			myPath = ff.getCanonicalPath();
			

			p("������ ���� �Ǵ� ���丮: ");
			oPath = br.readLine();              // ������ ���丮/���� �� �Է¹���
			if(oPath != null) oPath = oPath.trim();
			if(oPath.length() == 0){
				pln("�Է��� �ʼ��Դϴ�.");
				inputOri();
			}
			fo = new File(oPath);
			if(fo.exists()){
				if(fo.isDirectory()){
					int d = oPath.lastIndexOf("\\");
					dName = oPath.substring(d+1);      //���� ���������� �̸��� �߶�
					
					newDr();
					dCopy();
				}else if(fo.isFile()){
					fis = new FileInputStream(oPath);
					bis = new BufferedInputStream(fis, 2048);
					int i = oPath.lastIndexOf("\\");
					String oFname = oPath.substring(i+1);
					String copyPath = myPath+"\\"+oFname;
					fos = new FileOutputStream(copyPath);
					bos = new BufferedOutputStream(fos, 2048);
					fCopy();
				}
			}else{
				pln("���� �Ǵ� ���丮�� �������� �ʽ��ϴ�.");
				inputOri();
			}

			
		
		}catch(IOException ie){}
	}
	void newDr(){
		String dr = myPath + "\\" + dName;
		File cpDi = new File(dr);
		if(!(cpDi.exists())){
			cpDi.mkdirs();
		}
		
	}
	/*void inputCopy(){     //���� ��ġ ������
		try{
			p("������ ���� ��ġ: ");
			cPath = br.readLine();
			if(cPath != null) cPath = cPath.trim();
			if(cPath.length() == 0) {
				pln("�Է��� �ʼ��Դϴ�.");
				inputCopy();
			}
			fc = new File(cPath);
			if(fc.isDirectory()){
				
			}else{
				pln("������ �Է°���");
				inputCopy();
			}

		}catch (IOException ie){
		}
		
	}
	*/
	void dCopy() throws IOException{

		File kids[] = fo.listFiles();
		for(File kid: kids){
			if(kid.isDirectory()){
				pln("���丮");
				
			}else{
				pln("����");
				String cf = kid.getCanonicalPath();
				int i = cf.lastIndexOf("\\");
				String oFname = cf.substring(i+1);
				String copyPath = myPath+"\\"+ dName+"\\"+oFname;

				fis = new FileInputStream(cf);
				bis = new BufferedInputStream(fis, 2048);

				fos = new FileOutputStream(copyPath);
				bos = new BufferedOutputStream(fos, 2048);
				
				byte bs[] = new byte[1024];
				int j = 0;
				while((j=bis.read(bs)) != -1){
					bos.write(bs,0,j);
				}
				bos.flush();
			}
			pln("���丮 ����Ϸ�");

			bos.close();
			bis.close();
			fos.close();
			fis.close();
		}
	}
	void fCopy() throws IOException{
		byte bs[] = new byte[1024];
		int i = 0;
		while((i=bis.read(bs)) != -1){
			bos.write(bs,0,i);
		}
		bos.flush();
		pln("����Ϸ�");

		bos.close();
		bis.close();
		fos.close();
		fis.close();
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		DirCopy2 di = new DirCopy2();
		di.inputOri();
	}
}
