import java.net.*;
import java.io.*;


class OneMoreServer extends Thread {
	String msg = "";
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	UiServer us;
	Socket s;
	String nic;

	OneMoreServer(UiServer us){
		this.us = us;
		this.s = us.s;            // MrServer�� ���� 
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
			broadcast(nic+"���� �����ϼ̽��ϴ�(�� �ο� "+us.v.size()+"��)");
			us.pln(nic+"���� �����ϼ̽��ϴ�(�� �ο� " + us.v.size()+"��)");
			while(true){	
				msg = dis.readUTF();
				broadcast(msg);
				us.pln(msg);
			}
		}catch(IOException ie){
			us.v.remove(this);
			broadcast(nic+"���� �����ϼ̽��ϴ�(�� �ο� " + us.v.size()+"��)");
			us.pln(nic+"���� �����ϼ̽��ϴ�(�� �ο� " + us.v.size()+"��)");
		}finally{
			allClose();
		}
	}
	void broadcast(String msg){   // Ŭ���̾�Ʈ���Լ� �о�� �޼����� �Ķ���ͷ� �޾ƿ´�
		try{
			for(OneMoreServer oms: us.v){   // v1�� ����ִ� Ŭ���̾�Ʈ ��ü�� �ϳ��� �ϳ��� �޼��� ����
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