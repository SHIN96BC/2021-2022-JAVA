import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

class UiClient extends JFrame implements Runnable, ActionListener{
	Container cp;
	JTextArea ja;
	JScrollPane js;
	JButton jbIn, jbOut, jbCle;
	JLabel jlID;
	JTextField tfT, tfB;
	JPanel jpT, jpB;
	String msg;

	Socket s;
	String ip;
	int port;
	String nic;
	
	FileReader fr;
	BufferedReader br;
	OutputStream os;
	InputStream is;
	DataOutputStream dos;
	DataInputStream dis;
	
	UiClient(){
		init();
	}
	void init(){
		cp = getContentPane();
		
		
		jpT = new JPanel();
		jpB = new JPanel();

		jlID = new JLabel("MY ID  ");
		
		tfT = new JTextField(23);
		tfB = new JTextField(30);
		
		jbIn = new JButton("입장");
		jbCle = new JButton("CLEAER");
		jbOut = new JButton("퇴장");
		

		jbIn.addActionListener(this);
		tfT.addActionListener(this);
		tfB.addActionListener(this);
		jbCle.addActionListener(this);
		

		jpT.add(jlID); 
		jpT.add(tfT); 
		jpT.add(jbIn);
		jpB.add(tfB);
		jpB.add(jbCle, BorderLayout.WEST);

		ja = new JTextArea();
		ja.setBackground(new Color(189, 189, 189));

		js = new JScrollPane(ja);
		ja.setEditable(false);
		tfB.setEditable(false);
		js.setPreferredSize(new Dimension(100,100));
		
		
		cp.add(js);
		cp.add(jpB, BorderLayout.SOUTH);
		cp.add(jpT, BorderLayout.NORTH);
		
		setUI();
	}

	@Override
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == jbIn || obj == tfT){    // 입장버튼 또는 MY ID에서 엔터 눌렀을때
			nic = tfT.getText();
			nic = nic.trim();
			if(nic.length() == 0){
				JOptionPane.showMessageDialog(null, "닉네임은 필수입니다", "닉네임없음", JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				connect();
				name();
				Thread th = new Thread(this);
				tfT.setEditable(false);
				th.start();
				tfB.setEditable(true);
				jpT.remove(jbIn);
				jpT.add(jbOut);
				revalidate();
				repaint();
				jbOut.addActionListener(this);
			}
		}
		if(obj == tfB){     // 채팅 입력창
			msg = tfB.getText();
			tfB.setText("");
			speak();
		}
		if(obj == jbCle){
			ja.setText("");
		}
		if(obj == jbOut){
			int answer = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "질문", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(answer == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
	}
	void setUI(){
		setTitle("멀티채팅UI");
		setSize(450,400);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void connect(){
		try{
			String line="";
			fr = new FileReader("ipPort.txt");
			br = new BufferedReader(fr);
			while((line = br.readLine()) != null){
				line = line.trim();
				if(line.length() > 5){
					ip = line;
				}else{
					port = Integer.parseInt(line);
					if(port<0 || port>65535){	
						pln("포트가 유효한 범위가 아닙니다. 다시 입력해주세요");
					}
				}
			}
			s = new Socket(ip,port);
			pln("서버와 접속 성공!!");
			is = s.getInputStream();
			os = s.getOutputStream();
		}catch(FileNotFoundException fne){
		}catch(UnknownHostException uhe){
			pln("해당 서버를 찾을 수 없습니다.");
		}catch(IOException ie){}
	}
	void name(){
		try{
			dos = new DataOutputStream(os);
			dos.writeUTF(nic);
			dos.flush();
		}catch(IOException ie){}
	}

	@Override
	public void run(){
		listen();
	}
	void listen(){
		dis = new DataInputStream(is);
		try{
			String msg = "";
			while(true){
				msg = dis.readUTF();
				ja.append(msg+"\n");
			}
		}catch(IOException ie){
			ja.append("서버가 다운됨.. 2초 후에 종료하겠습니다"+"\n");
			try{
				Thread.sleep(2000);
			}catch(InterruptedException iee){}
			System.exit(0);
			allClose();
		}
	}
	void speak(){
		dos = new DataOutputStream(os);
		try{
			dos.writeUTF(nic+">>> "+msg);
			dos.flush();
		}catch(IOException ie){
			ja.append("서버가 다운됨.. 2초 후에 종료하겠습니다"+"\n");
			try{
				Thread.sleep(2000);
			}catch(InterruptedException iee){}
			System.exit(0);
			allClose();
		}
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
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
	public static void main(String[] args) {
		new UiClient();
	}
}
