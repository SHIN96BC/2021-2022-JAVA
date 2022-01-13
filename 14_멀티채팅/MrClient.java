import java.net.*;
import java.io.*;

//TCP
class MrClient extends Thread{   //IP: 192.168.0.28    127.0.0.1
	Socket s;
	String ip;
	int port;

	OutputStream os;
	DataOutputStream dos;
	InputStream is;
	DataInputStream dis;
	String nic;

	MrClient(){
		connect();
	}

	void connect(){
		try{
			p("서버IP(기본 192.168.0.28): ");
			String ip = br.readLine();
			ip = ip.trim();
			if(ip.length() == 0) ip = "127.0.0.1";
			
			p("포트(기본 3500): ");
			String portStr = br.readLine();
			portStr = portStr.trim();
			if(portStr.length() == 0) portStr = "3500";
			port = Integer.parseInt(portStr);
			if(port<0 || port>65535){
				pln("유효한 범위가 아닙니다. 다시 입력하시오.");
				connect();
			}

			s = new Socket(ip, port);
			pln("서버("+ip+")와 접속 성공!!");

			os = s.getOutputStream();
			is = s.getInputStream();
			name();
			start();
			speak();
		}catch(UnknownHostException uhe){
			pln("네트워크에서 해당 서버("+ip+")를 찾을수 없음");
			connect();
		}catch(IOException ie){
			connect();
		}
	}
	public void run(){
		listen();
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	void name(){
		try{
			dos = new DataOutputStream(os);
			p("닉네임을 입력하세요: ");
			nic = br.readLine();
			dos.writeUTF(nic);
			dos.flush();
		}catch(IOException ie){}
	}
	void speak(){
		dos = new DataOutputStream(os);
		try{
			String msg = "";
			while(true){
				msg = br.readLine();
				dos.writeUTF(nic+">>> "+msg);
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
				pln(msg);
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
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new MrClient();
	}
}
