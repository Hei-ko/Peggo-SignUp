package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javacode.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;

/**
 * This class to display ClientForm for User "Client" to sign-up for bus pass
 * @author Denis Rozit
 *
 */
public class ClientForm extends JFrame {

		/** to display form */
	private JPanel contentPane;
		/** form's field to display ClientName*/
	private JTextField fldClientName;
		/** form's field to display ClientID*/
	private JTextField fldClientID;
		/** form's field to display Client's Email*/
	private JTextField fldEmail;
		/** form's field to display Client's Card# - CardID*/
	private JTextField fldCardID;
		/** variable to store Client's information, type Client */
	private Client client;
		/** variable to store Month Status*/
	private static MonthStatus ms;
		/** variable type DataController for connecting to database */
	private DataController dc;
		/** variable to store Sign Status for specific month*/
	private static SignStatus ss;
	

	/**
	 * Launch the application.
	 * @param client Client
	 * @param ms MonthStatus
	 * @param dc DataController
	 * @param runMessage boolean - needs to let ClientForm know if it was launched by AdminForm or not - should it show text pop-up window or not
	 * 
	 */
	public static void main(Client client, MonthStatus ms, DataController dc, boolean runMessage) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientForm frame = new ClientForm(client, ms, dc, runMessage);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param client Client
	 * @param ms MonthStatus
	 * @param dc DataController
	 * @param runMessage boolean - needs to let ClientForm know if it was launched by AdminForm or not - should it show text pop-up window or not
	 * 
	 */

	public ClientForm(Client client, MonthStatus ms, DataController dc, boolean runMessage) {
		
		SignStatus ss = new SignStatus();
		this.client=client;
		this.ms=ms;
		this.dc=dc;
		this.ss=ss;

		ms=dc.getMonthStatus(ms); //updating MonthStatus
		ss=dc.getSignStatus(ss, client); //updating Sign-Up status
		
		setResizable(false);
		setTitle("PEGGO Sign-in Sheet");
		ImageIcon iconY = new ImageIcon("./images/signed.jpg");
		ImageIcon iconN = new ImageIcon("./images/available.jpg");
		
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(0);
		
		setBounds(100, 100, 473, 485);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		fldClientName = new JTextField();
		fldClientName.setBackground(Color.WHITE);
		fldClientName.setEditable(false);
		fldClientName.setBounds(95, 22, 329, 20);
		contentPane.add(fldClientName);
		fldClientName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblName.setBounds(37, 25, 48, 14);
		contentPane.add(lblName);
		
		JLabel lblClientID = new JLabel("ID #");
		lblClientID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblClientID.setBounds(37, 56, 48, 14);
		contentPane.add(lblClientID);
		
		JLabel lblCardID = new JLabel("Card #");
		lblCardID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCardID.setBounds(37, 87, 48, 14);
		contentPane.add(lblCardID);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmail.setBounds(218, 56, 31, 14);
		contentPane.add(lblEmail);
		
		fldClientID = new JTextField();
		fldClientID.setBackground(Color.WHITE);
		fldClientID.setEditable(false);
		fldClientID.setBounds(95, 53, 96, 20);
		contentPane.add(fldClientID);
		fldClientID.setColumns(10);
		
		fldEmail = new JTextField();
		fldEmail.setBackground(Color.WHITE);
		fldEmail.setEditable(false);
		fldEmail.setBounds(259, 53, 165, 20);
		contentPane.add(fldEmail);
		fldEmail.setColumns(10);
		
		fldCardID = new JTextField();
		fldCardID.setBackground(Color.WHITE);
		fldCardID.setEditable(false);
		fldCardID.setBounds(95, 84, 96, 20);
		contentPane.add(fldCardID);
		fldCardID.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 117, 421, 7);
		contentPane.add(separator);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSignUp.setBounds(37, 135, 96, 29);
		contentPane.add(lblSignUp);
		
		JLabel lblJanuary = new JLabel("January");
		lblJanuary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblJanuary.setBounds(110, 175, 73, 21);
		contentPane.add(lblJanuary);
		
		JLabel lblFebruary = new JLabel("February");
		lblFebruary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFebruary.setBounds(110, 207, 73, 21);
		contentPane.add(lblFebruary);
		
		JLabel lblMarch = new JLabel("March");
		lblMarch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMarch.setBounds(110, 239, 73, 21);
		contentPane.add(lblMarch);
		
		JLabel lblApril = new JLabel("April");
		lblApril.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApril.setBounds(110, 271, 73, 21);
		contentPane.add(lblApril);
		
		JLabel lblMay = new JLabel("May");
		lblMay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMay.setBounds(110, 303, 73, 21);
		contentPane.add(lblMay);
		
		JLabel lblJune = new JLabel("June");
		lblJune.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblJune.setBounds(110, 335, 73, 21);
		contentPane.add(lblJune);
		
		JLabel lblJuly = new JLabel("July");
		lblJuly.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblJuly.setBounds(304, 175, 73, 21);
		contentPane.add(lblJuly);
		
		JLabel lblAugust = new JLabel("August");
		lblAugust.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAugust.setBounds(304, 207, 73, 21);
		contentPane.add(lblAugust);
		
		JLabel lblSeptember = new JLabel("September");
		lblSeptember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeptember.setBounds(304, 239, 73, 21);
		contentPane.add(lblSeptember);
		
		JLabel lblOctober = new JLabel("October");
		lblOctober.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOctober.setBounds(304, 271, 73, 21);
		contentPane.add(lblOctober);
		
		JLabel lblNovember = new JLabel("November");
		lblNovember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNovember.setBounds(304, 303, 73, 21);
		contentPane.add(lblNovember);
		
		JLabel lblDecember = new JLabel("December");
		lblDecember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDecember.setBounds(304, 335, 73, 21);
		contentPane.add(lblDecember);
		
		
		/**
		 * setting labels with icons for each month - sign up status
		 */
		JLabel lblIconJanuary = new JLabel();
		lblIconJanuary.setBounds(65, 175, 21, 21);
		contentPane.add(lblIconJanuary);
		
		JLabel lblIconFebruary = new JLabel();
		lblIconFebruary.setBounds(65, 207, 21, 21);
		contentPane.add(lblIconFebruary);
		
		JLabel lblIconJuly = new JLabel();
		lblIconJuly.setBounds(259, 175, 21, 21);
		contentPane.add(lblIconJuly);
		
		JLabel lblIconAugust = new JLabel();
		lblIconAugust.setBounds(259, 207, 21, 21);
		contentPane.add(lblIconAugust);
		
		JLabel lblIconMarch = new JLabel();
		lblIconMarch.setBounds(65, 239, 21, 21);
		contentPane.add(lblIconMarch);
		
		JLabel lblIconApril = new JLabel();
		lblIconApril.setBounds(65, 271, 21, 21);
		contentPane.add(lblIconApril);
		
		JLabel lblIconMay = new JLabel();
		lblIconMay.setBounds(65, 303, 21, 21);
		contentPane.add(lblIconMay);
		
		JLabel lblIconJune = new JLabel();
		lblIconJune.setBounds(65, 335, 21, 21);
		contentPane.add(lblIconJune);
		
		JLabel lblIconSeptember = new JLabel();
		lblIconSeptember.setBounds(259, 239, 21, 21);
		contentPane.add(lblIconSeptember);
		
		JLabel lblIconOctober = new JLabel();
		lblIconOctober.setBounds(259, 271, 21, 21);
		contentPane.add(lblIconOctober);
		
		JLabel lblIconNovember = new JLabel();
		lblIconNovember.setBounds(259, 303, 21, 21);
		contentPane.add(lblIconNovember);
		
		JLabel lblIconDecember = new JLabel();
		lblIconDecember.setBounds(259, 335, 21, 21);
		contentPane.add(lblIconDecember);
		
		
			/**
			 * filling created fields with information
			 */
			
			fldClientName.setText(client.getClientName());
			fldClientID.setText(String.valueOf(client.getClientID()));
			fldEmail.setText(client.getClientEmail());
			fldCardID.setText(String.valueOf(client.getCardID()));
			
			class Local{
				void infoUpdate(){
					if (ClientForm.ss.getSignStatus(1)) {lblIconJanuary.setIcon(iconY);}else{lblIconJanuary.setIcon(iconN);};
					if (ClientForm.ss.getSignStatus(2)) {lblIconFebruary.setIcon(iconY);}else{lblIconFebruary.setIcon(iconN);};
					if (ClientForm.ss.getSignStatus(3)) {lblIconMarch.setIcon(iconY);}else{lblIconMarch.setIcon(iconN);};
					if (ClientForm.ss.getSignStatus(4)) {lblIconApril.setIcon(iconY);}else{lblIconApril.setIcon(iconN);};
					if (ClientForm.ss.getSignStatus(5)) {lblIconMay.setIcon(iconY);}else{lblIconMay.setIcon(iconN);};
					if (ClientForm.ss.getSignStatus(6)) {lblIconJune.setIcon(iconY);}else{lblIconJune.setIcon(iconN);};
					if (ClientForm.ss.getSignStatus(7)) {lblIconJuly.setIcon(iconY);}else{lblIconJuly.setIcon(iconN);};
					if (ClientForm.ss.getSignStatus(8)) {lblIconAugust.setIcon(iconY);}else{lblIconAugust.setIcon(iconN);};
					if (ClientForm.ss.getSignStatus(9)) {lblIconSeptember.setIcon(iconY);}else{lblIconSeptember.setIcon(iconN);};
					if (ClientForm.ss.getSignStatus(10)) {lblIconOctober.setIcon(iconY);}else{lblIconOctober.setIcon(iconN);};
					if (ClientForm.ss.getSignStatus(11)) {lblIconNovember.setIcon(iconY);}else{lblIconNovember.setIcon(iconN);};
					if (ClientForm.ss.getSignStatus(12)) {lblIconDecember.setIcon(iconY);}else{lblIconDecember.setIcon(iconN);};
				}
			}
		
			new Local().infoUpdate();
			
			//starting parallel thread to read txt file and display text message
			ThreadController tc = new ThreadController();
			if(runMessage) {
				Thread messageCheck = new Thread(tc);
				messageCheck.start();
			}
			
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//stop parallel thread
				tc.stopThis();
				dispose();		
			}
		});
		btnExit.setBounds(335, 408, 89, 23);
		contentPane.add(btnExit);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SignForm.signForm(dc, ClientForm.ss, ClientForm.ms, client);
				ClientForm.ss=dc.getSignStatus(ClientForm.ss, client); //updating SignUp information
				
				new Local().infoUpdate();
				
			}
		});
		
		btnSignUp.setBounds(218, 408, 89, 23);
		contentPane.add(btnSignUp);
		
		
	}
}
