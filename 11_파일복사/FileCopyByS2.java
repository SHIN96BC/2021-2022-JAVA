import java.io.*;

class copyOption2{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	FileInputStream fis;
	FileOutputStream fos;
	String fName;   // 파일위치
	String fCopy;
	String oriFName;

	copyOption2(){
		try{
			pln("1. 복사   2. 잘라내기");
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
				pln("1 과 2 둘중 하나를 입력해주세요");
				new copyOption2();
			}
		}catch(IOException ie){
		}
	}
	void input() throws IOException{
		try{
			p("복사원본파일(/로 입력해주세요): ");
			fName = br.readLine();
			int i = fName.lastIndexOf("\\");
			String fn = fName.substring(i+1);
			fis = new FileInputStream(fName);
			p("복사할 폴더 위치(/로 입력해주세요): ");
			fCopy = br.readLine();
			
			File f = new File(fCopy);
			if(f.exists()){
				String str = fCopy + "\\" + fn;
				fos = new FileOutputStream(str);
			}else{
				pln("존재하지않는 디렉토리! 만들까요?(y/n)");
				String flag = br.readLine();
				if(flag.equals("y")){
					f.mkdirs();
					fos = new FileOutputStream(fCopy);
				}else if(flag.equals("n")){
					pln("폴더를 생성하지 않습니다\n");
					input();
				}else{
					pln("입력값이 폴더위치가 아닙니다.");
					input();
				}
			}
		}catch(FileNotFoundException fe){
			pln("파일이 존재하지 않습니다.");
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
		pln("복사완료");

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
