package bc.file;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.text.*;


class UiTest2 extends JFrame implements ActionListener{
	BufferedReader br;
	FileWriter fw;
	PrintWriter pw;
	FileReader fr;
	Container cp;
	JPanel jp;
	JFileChooser fc = new JFileChooser(".");
	JButton bOpen, bSave,bClear;
	JTextPane jt;
	JScrollPane js;
	
	void init(){
		cp = getContentPane();

		jt = new JTextPane();
		js = new JScrollPane(jt);
		js.setPreferredSize(new Dimension(400,300));

		jp = new JPanel(new GridLayout(1,2));
		bOpen = new JButton("Open");
		bSave = new JButton("Save");
		bClear = new JButton("Clear");

		jp.add(bOpen);
		jp.add(bSave);
		
		bOpen.addActionListener(this);
		bSave.addActionListener(this);
		bClear.addActionListener(this);
		
		cp.add(bClear, BorderLayout.NORTH);
		cp.add(jp, BorderLayout.SOUTH);
		cp.add(js);
		
		setUI();
	}
	void setUI(){
		setTitle("JFrameChooser Testing");
		setSize(500,400);
		setLocationRelativeTo(null); //해상도와 상관없이 화면의 가운데에 띄움
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == bOpen){   // 열기기능
			int i = fc.showOpenDialog(this);
			switch(i){
				case JFileChooser.CANCEL_OPTION: break;
				case JFileChooser.APPROVE_OPTION: 
					try{
						String msg = fc.getSelectedFile().getAbsolutePath();
						fr = new FileReader(msg);
						br = new BufferedReader(fr);
						String str="";
						
						while((str = br.readLine()) != null){
							jt.replaceSelection(str);
							jt.replaceSelection("\r\n");
						}
					}catch(IOException ie){}
					break;
				case JFileChooser.ERROR_OPTION: break;
			}
		}
		if(obj==bSave){  //저장기능
			int j = fc.showSaveDialog(this);
			switch(j){
				case JFileChooser.CANCEL_OPTION: break;
				case JFileChooser.APPROVE_OPTION: 
					try{
						String msg = fc.getSelectedFile().getAbsolutePath();
						File f2 = new File(msg);
						fw = new FileWriter(f2);
						pw = new PrintWriter(fw,true);
						String text = jt.getText();
						pw.println(text);
					}catch(IOException ie){}
					break;
				case JFileChooser.ERROR_OPTION: break;
			}
		}
		if(obj == bClear){  // 클리어기능
			jt.setText("");
		}
	}
	public static void main(String[] args) {
		UiTest2 ui = new UiTest2();
		ui.init();
	}
}
