import java.io.*;
import java.net.*;
import java.util.*;

// UDP
class UServerClient extends Thread {
	String ip = "127.0.0.1";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int port = 7000;
	Vector<String> v1 = new Vector<String>();
	Vector<String> v2 = new Vector<String>();
	FileReader fr;
	BufferedReader brFile;
	File f;
	
	UServerClient(){
		init();
	}
	void init(){
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		inputName();
		inputIp();
		start();

		try{
			Thread.sleep(100);
		}catch(InterruptedException ie){}

		try{
			ds = new DatagramSocket();
			while(true){
				//p("전달할메세지: ");
				String msg = br.readLine();
				msg = msg.trim();
				byte[] buf = msg.getBytes();
				InetAddress ia = InetAddress.getByName(ip);
				dp = new DatagramPacket(buf, buf.length, ia, port);
				ds.send(dp);
				//pln("전송완료!!");
			}
		}catch(SocketException se){
		}catch(UnknownHostException ue){
			pln("네트웍상에 해당서버("+ip+")를 찾을 수 없음");
			init();
		}catch(IOException ie){
		}finally{
			if(ds != null) ds.close();
		}
	}
	void inputName(){
		String file = "ips.txt";
		String msg="";
		String name="";
		String fileIp="";
		byte[] bs = new byte[1024];
		int fi = 0;
		int k = 0;
		
		try{
			f = new File(file);
			if(f.exists()){
				pln("파일이 존재합니다.");
			}else{
				pln("파일이 존재하지 않습니다.");
				inputName();
				return;
			}
			fr = new FileReader(f);
			brFile = new BufferedReader(fr);
			
			
			while((msg = brFile.readLine()) != null){
				msg = msg.trim();
				fi = msg.indexOf(" ");
				if(fi == -1) {
					name = msg.substring(0,3);
					v1.add(name);
					v2.add("IP없음");
					continue;
				}
				name = msg.substring(0,fi);
				fileIp = msg.substring(fi+1);
				fileIp = fileIp.trim();
				v1.add(name);
				v2.add(fileIp);
			}
		}catch(FileNotFoundException fe){
			pln("파일이 존재하지 않습니다.");
		}catch(IOException ie){}
	}
	void inputIp(){
		try{
			p("IP(기본:192.168.0.143): ");
			ip = br.readLine();
			ip = ip.trim();
			if(ip.length() == 0) ip="192.168.0.143";
		}catch(IOException ie){}
	}
	public void run(){
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		String name="";
		String ip="";
		try{
			ds = new DatagramSocket(port);
			pln(port+"번에서 UDP서버 대기중..");
			byte[] buf = new byte[2048];
			dp = new DatagramPacket(buf, buf.length);
			
			while(true){
				ds.receive(dp);
				InetAddress ia = dp.getAddress();
				String ipClient = ia.getHostAddress();
				for(int i=0; i<v2.size();i++){
					ip = v2.get(i);
					if(ipClient.equals(ip)){
						name = v1.get(i);
						break;
					}else{
						name = "무명";
					}
				}
				String msg = new String(buf);
				msg = msg.trim();
				pln(name+">> " + msg);
				for(int i=0; i<buf.length; i++) buf[i]=0;
			}
		}catch(SocketException se){
		}catch(IOException ie){
		}finally{
			if(ds != null) ds.close();
		}
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new UServerClient();
	}
}

