import java.net.*;
import java.io.*;
import java.util.*;

//TCP
class MrServer extends Thread {
	ServerSocket ss;
	int port = 3500;
	Socket s;
	Vector<OneMoreServer> v = new Vector<OneMoreServer>();   // ������ ����
	OneMoreServer oms;

	MrServer(){
		try{
			pln(port + "�� ��Ʈ���� �����...");
			ss = new ServerSocket(port);
			start();
			
			while(true){
				s = ss.accept();             // Ŭ���̾�Ʈ�� ��� ���� �� �ְ� ����
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
					pln("�ɼ� ��� ��.");
					option();
				}
				if(msg.equalsIgnoreCase("option")) pln("option on�� �Է��ϸ� �ɼ� ��尡 ����˴ϴ�.");
				
				for(int i=0; i<v.size(); i++){
					OneMoreServer oms = v.get(i);
					s = oms.s;
					os = s.getOutputStream();
					dos = new DataOutputStream(os);
					dos.writeUTF("����>>> " + msg); 
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
					pln("�ɼǸ�� ����");
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
					dos.writeUTF("����>>> " + msg); 
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
			p("�ӼӸ��ϰ���� ������ �г����� �Է��ϼ���: ");
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
				pln("�׷� �̸��� ������ �������� �ʽ��ϴ�.");
				wisper();
				return;
			}
			s = oms.s;
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			while(true){
				p(oms.nic +"����: ");
				msg = br.readLine();
				if(msg.equalsIgnoreCase("option off")){
					pln("�ɼǸ�� ����");
					speak();
				}
				dos.writeUTF("�������� �ӼӸ�>>> " + msg); 
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
			p("�����Ű����� ������ �г����� �Է��ϼ���: ");
			outNic = br.readLine();
			outNic = outNic.trim();
			for(int n=0; n<v.size(); n++){
				oms = v.get(n);
				if(oms.nic.equals(outNic)){
					flag = true;
					v.remove(n);
					oms.allClose();
					pln(oms.nic+"���� �����Ͽ����ϴ�.(���� �ο� "+v.size()+"��)");
					option();
				}
			}
			if(flag == false) {
				pln("�׷� �̸��� ������ �������� �ʽ��ϴ�.");
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



