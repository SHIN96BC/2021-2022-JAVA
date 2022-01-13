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
			pln(port + "�� ��Ʈ���� ���� �����...");
			
			s = ss.accept();   // ���α׎c�� �������ʰ� ��� �ִ°� (Ŀ���� ��������) ����ִٰ� Ŭ���̾�Ʈ�� ������ ������ ����ȴ�
			ipClient = s.getInetAddress().getHostAddress();
			pln("Ŭ���̾�Ʈ("+ipClient+")����!!");
			
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
			pln("Ŭ���̾�Ʈ("+ipClient+")����");
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
				pln("Ŭ���̾�Ʈ("+ipClient+")>>> " + msg);
			}
		}catch(IOException ie){
			pln("Ŭ���̾�Ʈ("+ipClient+")����!");
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
