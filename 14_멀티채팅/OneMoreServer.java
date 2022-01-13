import java.net.*;
import java.io.*;

//TCP
class OneMoreServer extends Thread {
	String msg = "";
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	MrServer mrs;
	Socket s;
	String nic;

	OneMoreServer(MrServer mrs){
		this.mrs = mrs;
		this.s = mrs.s;            // MrServer�� ���� 
		try{
			is = s.getInputStream();
			os = s.getOutputStream();
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
		}catch(IOException ie){}
	}
	public void run(){
		listen();
	}
	void listen(){
		try{
			nic = dis.readUTF();
			broadcast(nic+"���� �����ϼ̽��ϴ�(�� �ο� "+mrs.v.size()+"��)");
			mrs.pln(nic+"���� �����ϼ̽��ϴ�(�� �ο� " + mrs.v.size()+"��)");
			while(true){	
				msg = dis.readUTF();
				broadcast(msg);
				mrs.pln(msg);
			}
		}catch(IOException ie){
		}finally{
			allClose();
		}
	}
	void broadcast(String msg){   // Ŭ���̾�Ʈ���Լ� �о�� �޼����� �Ķ���ͷ� �޾ƿ´�
		try{
			for(OneMoreServer oms: mrs.v){   // v1�� ����ִ� Ŭ���̾�Ʈ ��ü�� �ϳ��� �ϳ��� �޼��� ����
				oms.dos.writeUTF(msg);      // Ŭ���̾�Ʈ �ϳ��� ������ ��ü���� OutputStream�� ������ ���ش�
				oms.dos.flush();
			}
		}catch(IOException ie){}
	}
	void allClose(){
		try{
			if(dis != null) dis.close();
			if(dos != null) dos.close();
			if(is != null) is.close();
			if(os != null) os.close();
			if(s != null) s.close();
		}catch(IOException ie){}
	}
}