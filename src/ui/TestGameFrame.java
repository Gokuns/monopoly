package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.controller.GameController;
import domain.model.dice.Cup;
import domain.model.dice.faceValue;

@SuppressWarnings("serial")
public class TestGameFrame extends JFrame {

	private GameController controller;
	private JPanel contentPane;
	private JPanel rollPane;
	
	private JButton rollButton;
	private JLabel lblFaceValues;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGameFrame frame = new TestGameFrame();
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
		
//		int a = 1;
//		//asd
//		a=1;
	}

	/**
	 * Create the frame.
	 */
	public TestGameFrame() {
		controller = GameController.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		rollPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		rollButton = new JButton("ROLL");
		lblFaceValues = new JLabel("");
		
		rollPane.add(rollButton);
		rollPane.add(lblFaceValues);
		
		add(rollPane);
//		rollPane.setVisible(true);
		
//		contentPane.setVisible(true);
//		rollButton.setVisible(true);
		rollButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				controller.roll();
				Cup cup = Cup.getInstance();
				List<faceValue> faceValList = cup.getFaceValues();
				String str = "=>";
				for(int i=0; i<3;i++)
					str += " " + faceValList.get(i).toString();
				lblFaceValues.setText(str);
			}
		});
	}

}
