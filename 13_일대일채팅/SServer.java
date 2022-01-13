import java.net.*;
import java.io.*;

//TCP
class SServer extends Thread {
	ServerSocket ss;
	int port = 3500;
	Socket s;
	String ipClient;
	
	InputStream is;
	DataInputStream dis;
	OutputStream os;
	DataOutputStream dos;

	SServer(){
		try{
			ss = new ServerSocket(port);
			pln(port + "번 포트에서 서버 대기중...");
			
			s = ss.accept();   // 프로그럄이 끝나지않게 잡고 있는것 (커서가 깜빡깜빡) 잡고있다가 클라이언트의 소켓을 만나면 연결된다
			ipClient = s.getInetAddress().getHostAddress();
			pln("클라이언트("+ipClient+")입장!!");
			
			is = s.getInputStream();
			os = s.getOutputStream();
			start();
			speak();
		}catch(IOException ie){
			pln("ie:" + ie);
		}finally{
			try{
				if(s != null) s.close();
				if(ss != null) ss.close();
			}catch(IOException ie){
			}
		}
	}
	public void run(){
		listen();
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	void speak(){
		dos = new DataOutputStream(os);
		try{
			String msg = "";
			while(true){
				msg = br.readLine();
				dos.writeUTF(msg);
				dos.flush();
			}
		}catch(IOException ie){
			pln("클라이언트("+ipClient+")퇴장");
		}finally{
			try{
				if(dos != null) dos.close();
				if(os != null) os.close();
			}catch(IOException ie){
			}
		}
	}
	void listen(){
		dis = new DataInputStream(is);
		try{
			while(true){
				String msg = dis.readUTF();
				pln("클라이언트("+ipClient+")>>> " + msg);
			}
		}catch(IOException ie){
			pln("클라이언트("+ipClient+")퇴장!");
		}finally{
			try{
				if(dis != null) dis.close();
				if(is != null) is.close();
			}catch(IOException ie){
			}
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new SServer();
	}
}
