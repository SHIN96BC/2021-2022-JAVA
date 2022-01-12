import java.io.*;

class DirCopy2 {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String oPath;    // 복사할 파일 또는 디렉토리
	String cPath;    // 복사할 디렉토리 위치
	String oFname;   // 복사할 오리지널 파일이름
	String dName;    // 원본 메인 폴더명
	FileInputStream fis;
	FileOutputStream fos;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	File fo;
	File fc;
	FileInputStream fis2;
	String myPath;    // 현재 디렉토리위치


	void inputOri(){
		try{
			File ff = new File("");              // 현재파일 위치를 찾는 로직
			myPath = ff.getCanonicalPath();
			

			p("복사할 파일 또는 디렉토리: ");
			oPath = br.readLine();              // 복사할 디렉토리/파일 을 입력받음
			if(oPath != null) oPath = oPath.trim();
			if(oPath.length() == 0){
				pln("입력은 필수입니다.");
				inputOri();
			}
			fo = new File(oPath);
			if(fo.exists()){
				if(fo.isDirectory()){
					int d = oPath.lastIndexOf("\\");
					dName = oPath.substring(d+1);      //원본 메인폴더의 이름을 잘라냄
					
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
				pln("파일 또는 디렉토리가 존재하지 않습니다.");
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
	/*void inputCopy(){     //폴더 위치 설정용
		try{
			p("복사할 폴더 위치: ");
			cPath = br.readLine();
			if(cPath != null) cPath = cPath.trim();
			if(cPath.length() == 0) {
				pln("입력은 필수입니다.");
				inputCopy();
			}
			fc = new File(cPath);
			if(fc.isDirectory()){
				
			}else{
				pln("폴더만 입력가능");
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
				pln("디렉토리");
				
			}else{
				pln("파일");
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
			pln("디렉토리 복사완료");

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
		pln("복사완료");

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
