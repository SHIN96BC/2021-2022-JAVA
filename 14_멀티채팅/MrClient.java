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
			p("����IP(�⺻ 192.168.0.28): ");
			String ip = br.readLine();
			ip = ip.trim();
			if(ip.length() == 0) ip = "127.0.0.1";
			
			p("��Ʈ(�⺻ 3500): ");
			String portStr = br.readLine();
			portStr = portStr.trim();
			if(portStr.length() == 0) portStr = "3500";
			port = Integer.parseInt(portStr);
			if(port<0 || port>65535){
				pln("��ȿ�� ������ �ƴմϴ�. �ٽ� �Է��Ͻÿ�.");
				connect();
			}

			s = new Socket(ip, port);
			pln("����("+ip+")�� ���� ����!!");

			os = s.getOutputStream();
			is = s.getInputStream();
			name();
			start();
			speak();
		}catch(UnknownHostException uhe){
			pln("��Ʈ��ũ���� �ش� ����("+ip+")�� ã���� ����");
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
			p("�г����� �Է��ϼ���: ");
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
			pln("������ �ٿ��.. 2�� �Ŀ� �����ϰڽ��ϴ�");
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
			pln("������ �ٿ��.. 2�� �Ŀ� �����ϰڽ��ϴ�.");
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
