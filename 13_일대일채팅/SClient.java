import java.net.*;
import java.io.*;

//TCP
class SClient extends Thread{   //IP: 192.168.0.28
	Socket s;
	String ip = "192.168.0.28";
	int port = 3500;

	OutputStream os;
	DataOutputStream dos;
	InputStream is;
	DataInputStream dis;

	SClient(){
		try{
			s = new Socket(ip, port);
			pln("서버("+ip+")와 접속 성공!!");

			os = s.getOutputStream();
			is = s.getInputStream();
			start();
			speak();
		}catch(IOException ie){
			pln("네트워크에서 해당 서버("+ip+")를 찾을수 없음");
		}finally{
			try{
				if(s != null) s.close();
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
			pln("서버가 다운됨.. 2초 후에 종료하겠습니다");
			try{
				Thread.sleep(2000);
			}catch(InterruptedException iee){}
			System.exit(0);
		}finally{
			try{
				if(dos != null) dos.close();
				if(os != null) os.close();
			}catch(IOException ie){}
		}
	}
	void listen(){
		dis = new DataInputStream(is);
		try{
			String msg = "";
			while(true){
				msg = dis.readUTF();
				pln("server("+ip+")>>> "+ msg);
			}
		}catch(IOException ie){
			pln("서버가 다운됨.. 2초 후에 종료하겠습니다.");
			try{
				Thread.sleep(2000);
			}catch(InterruptedException iee){}
			System.exit(0);
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
		new SClient();
	}
}
