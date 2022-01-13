import java.net.*;
import java.io.*;
import java.util.*;

//TCP
class MrServer extends Thread {
	ServerSocket ss;
	int port = 3500;
	Socket s;
	Vector<OneMoreServer> v = new Vector<OneMoreServer>();   // 소켓을 저장
	OneMoreServer oms;

	MrServer(){
		try{
			pln(port + "번 포트에서 대기중...");
			ss = new ServerSocket(port);
			start();
			
			while(true){
				s = ss.accept();             // 클라이언트가 계속 들어올 수 있게 해줌
				oms = new OneMoreServer(this);
				v.add(oms);
				oms.start();
			}
		}catch(IOException ie){
		}finally{
			try{
				if(ss != null) ss.close();
			}catch(IOException ie){}
		}
	}
	public void run(){
		speak();
	}
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	void speak(){
		String msg="";
		Socket s=null;
		OutputStream os;
		DataOutputStream dos;
		try{
			while(true){
				msg = br.readLine();
				if(msg.equalsIgnoreCase("option on")){
					pln("옵션 모드 온.");
					option();
				}
				if(msg.equalsIgnoreCase("option")) pln("option on을 입력하면 옵션 모드가 실행됩니다.");
				
				for(int i=0; i<v.size(); i++){
					OneMoreServer oms = v.get(i);
					s = oms.s;
					os = s.getOutputStream();
					dos = new DataOutputStream(os);
					dos.writeUTF("서버>>> " + msg); 
					dos.flush();
				}
			}
		}catch(IOException ie){
		}
	}
	void option(){
		String msg="";
		Socket s=null;
		OutputStream os;
		DataOutputStream dos;
		try{
			pln("1.option on  2.option off  3.list  4.wisper  5.out  ");
			while(true){
				msg = br.readLine();
				if(msg.equalsIgnoreCase("option off")){
					pln("옵션모드 오프");
					speak();
				}
				if(msg.equalsIgnoreCase("option")) pln("1.option on  2.option off  3.list  4.wisper  5.out  ");
				if(msg.equalsIgnoreCase("list")){
					for(int j=0; j<v.size(); j++){
						int k = 1;
						OneMoreServer omsli = v.get(j);
						pln(k + ") " +omsli.nic);
						k++;
					}
					option();
					return;
				}
				if(msg.equalsIgnoreCase("wisper")) wisper();
				if(msg.equalsIgnoreCase("out")) userOut();
				for(int i=0; i<v.size(); i++){
					OneMoreServer oms = v.get(i);
					s = oms.s;
					os = s.getOutputStream();
					dos = new DataOutputStream(os);
					dos.writeUTF("서버>>> " + msg); 
					dos.flush();
				}
			}
		}catch(IOException ie){
		}
	}
	void wisper(){
		String msg="";
		String wisperNic="";
		Socket s=null;
		OutputStream os;
		DataOutputStream dos;
		OneMoreServer oms=null;
		boolean flag=false;
		try{
			p("귓속말하고싶은 유저의 닉네임을 입력하세요: ");
			wisperNic = br.readLine();
			wisperNic = wisperNic.trim();
			for(int n=0; n<v.size(); n++){
				oms = v.get(n);
				if(oms.nic.equals(wisperNic)){
					flag = true;
					break;
				}
			}
			if(flag == false) {
				pln("그런 이름의 유저는 존재하지 않습니다.");
				wisper();
				return;
			}
			s = oms.s;
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			while(true){
				p(oms.nic +"에게: ");
				msg = br.readLine();
				if(msg.equalsIgnoreCase("option off")){
					pln("옵션모드 오프");
					speak();
				}
				dos.writeUTF("서버에서 귓속말>>> " + msg); 
				dos.flush();
			}
		}catch(IOException ie){
		}
	}
	void userOut(){
		String msg="";
		String outNic="";
		Socket s=null;
		OutputStream os;
		DataOutputStream dos;
		OneMoreServer oms=null;
		boolean flag=false;
		try{
			p("강퇴시키고싶은 유저의 닉네임을 입력하세요: ");
			outNic = br.readLine();
			outNic = outNic.trim();
			for(int n=0; n<v.size(); n++){
				oms = v.get(n);
				if(oms.nic.equals(outNic)){
					flag = true;
					v.remove(n);
					oms.allClose();
					pln(oms.nic+"님을 강퇴하였습니다.(남은 인원 "+v.size()+"명)");
					option();
				}
			}
			if(flag == false) {
				pln("그런 이름의 유저는 존재하지 않습니다.");
				userOut();
				return;
			}
			
		}catch(IOException ie){
		}
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new MrServer();
	}
}



