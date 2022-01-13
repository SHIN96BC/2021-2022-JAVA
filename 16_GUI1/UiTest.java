import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

class UiTest extends JFrame implements ActionListener {
	Container cp;
	JPanel pN;
	JButton bE, bW, bS, bN;
	JButton bCbNE, bCbNW, bCbSE, bCbSW;
	ImageIcon ii;
	ImageIcon i1, i2, i3, i4;
	void init(){

		pN = new JPanel(new GridLayout(2,2));
		

		cp = getContentPane();
		
		i1 = new ImageIcon(getClass().getResource("imgs\\p_girl.PNG"));
		i2 = new ImageIcon(getClass().getResource("imgs\\p_hello.png"));
		i3 = new ImageIcon(getClass().getResource("imgs\\p_play.png"));
		i4 = new ImageIcon(getClass().getResource("imgs\\p_study.png"));

		/*ActionListener listener = new MouseListener(){
			int i = 0;
			public void 
		};*/


		


		bCbNE = new JButton(i1);
		bCbNW = new JButton(i2);
		bCbSE = new JButton(i3);
		bCbSW = new JButton(i4);
		
		
		bE = new JButton("동쪽");
		bE.addActionListener(this);
		bW = new JButton("서쪽");
		bW.addActionListener(this);
		bS = new JButton("남쪽");
		bS.addActionListener(this);
		bN = new JButton("북쪽");
		bN.addActionListener(this);

		pN.add(bCbNE);
		pN.add(bCbNW);
		pN.add(bCbSE);
		pN.add(bCbSW);


		cp.add(bE, BorderLayout.EAST);
		cp.add(bW, BorderLayout.WEST);
		cp.add(bS, BorderLayout.SOUTH);
		cp.add(bN, BorderLayout.NORTH);
		cp.add(pN, BorderLayout.CENTER);
		

		setUI();


	}
	@Override
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == bE){
			JOptionPane.showMessageDialog(null, "메세지 내용", "제목", JOptionPane.QUESTION_MESSAGE, i1);

		}else if(obj == bW){
			int answer = JOptionPane.showConfirmDialog(null, "종료할까요?", "선택", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, i3);

		}else if(obj == bS){
			int answer = JOptionPane.showConfirmDialog(null, "오늘 기분 어때?", "질문", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, i4);

		}else{ // obj == bN 일때
			JOptionPane.showMessageDialog(null, null, "Message", JOptionPane.INFORMATION_MESSAGE, i2);
			
		}
		
	}
	void setUI(){
		setTitle("Event Handling");
		pack();
		setLocation(200,100);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		UiTest ut = new UiTest();
		ut.init();
	}
}
