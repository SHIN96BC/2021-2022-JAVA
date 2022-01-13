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
		this.s = us.s;            // MrServer의 소켓 
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
			broadcast(nic+"님이 입장하셨습니다(총 인원 "+us.v.size()+"명)");
			us.pln(nic+"님이 입장하셨습니다(총 인원 " + us.v.size()+"명)");
			while(true){	
				msg = dis.readUTF();
				broadcast(msg);
				us.pln(msg);
			}
		}catch(IOException ie){
			us.v.remove(this);
			broadcast(nic+"님이 퇴장하셨습니다(총 인원 " + us.v.size()+"명)");
			us.pln(nic+"님이 퇴장하셨습니다(총 인원 " + us.v.size()+"명)");
		}finally{
			allClose();
		}
	}
	void broadcast(String msg){   // 클라이언트에게서 읽어온 메세지를 파라미터로 받아온다
		try{
			for(OneMoreServer oms: us.v){   // v1에 들어있는 클라이언트 객체를 하나당 하나씩 메세지 전송
				oms.dos.writeUTF(msg);      // 클라이언트 하나당 생성된 객체에서 OutputStream을 빼내서 써준다
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