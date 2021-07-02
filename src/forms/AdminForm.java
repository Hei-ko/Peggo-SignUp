package forms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javacode.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

/**
 * This class is displaying AdminForm (GUI) for Finance dep purposes
 * to manage client info, send text message, create simple report
 * @author Denis Rozit
 *
 */
public class AdminForm extends JFrame {
	
		/** to display form */
	private JPanel contentPane;
		/** form's field to display ClientID*/
	private JTextField fldClientID;
		/** form's field to display ClientName*/
	private JTextField fldClientName;
		/** form's field to display Client's Email*/
	private JTextField fldEmail;
		/** form's field to display Client's Card# - CardID*/
	private JTextField fldCardID;
		/** variable to store Month Status, boolean */
	private static MonthStatus ms;
		/** variable to store Client's information, type Client */
	private static Client client;
		/** variable type DataController for connecting to database */
	private DataController dc;
		/** array of arrays String to store monthly report information */
	private static String[][] mr;
		/** form's JTable to display monthly report information */
	private JTable tableMonthReport;
		/** form's field to display ClientPIN*/
	private JTextField fldClientPIN;
		/** form's field to display Month Status*/
	private JTextField fldMonthStatus;
	
		/**HashMap to identify month number by month name*/
	private HashMap<String, Integer> monthNumber = new HashMap<String, Integer>(){{
	    put("January", 1);
	    put("February", 2);  
	    put("March", 3);
	    put("April", 4);
	    put("May", 5);
	    put("June", 6);
	    put("July", 7);
	    put("August", 8);
	    put("September", 9);
	    put("October", 10);
	    put("November", 11);
	    put("December", 12);
		}};
	
		/**String to set header for JTable*/
	String[] columnNames = { "ID#", "Name", "Card#", "SignStatus"};
		

	/**
	 * Launch the application.
	 * @param ms MonthStatus
	 * @param dc DataController
	 * 
	 */
	public static void main(MonthStatus ms, DataController dc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminForm frame = new AdminForm(ms, dc);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param ms MonthStatus
	 * @param dc DataController
	 * 
	 */
	public AdminForm(MonthStatus ms, DataController dc) {
		setResizable(false);
		
		Client client = new Client();
		this.client=client;
		this.ms = ms;
		this.dc=dc;
		
		ms=dc.getMonthStatus(ms);
		
		setResizable(false);
		setTitle("Admin form");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1112, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblClientID = new JLabel("Client ID");
		lblClientID.setBounds(10, 11, 48, 14);
		contentPane.add(lblClientID);
		
		fldClientID = new JTextField();
		fldClientID.setBounds(68, 8, 96, 20);
		contentPane.add(fldClientID);
		fldClientID.setColumns(10);
		
		JLabel lblClientName = new JLabel("Name");
		lblClientName.setBounds(10, 51, 48, 14);
		contentPane.add(lblClientName);
		
		fldClientName = new JTextField();
		fldClientName.setBounds(68, 48, 207, 20);
		contentPane.add(fldClientName);
		fldClientName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 76, 48, 14);
		contentPane.add(lblEmail);
		
		fldEmail = new JTextField();
		fldEmail.setBounds(68, 73, 207, 20);
		contentPane.add(fldEmail);
		fldEmail.setColumns(10);
		
		JLabel lblCardID = new JLabel("Card #");
		lblCardID.setBounds(10, 101, 48, 14);
		contentPane.add(lblCardID);
		
		fldCardID = new JTextField();
		fldCardID.setBounds(68, 98, 207, 20);
		contentPane.add(fldCardID);
		fldCardID.setColumns(10);
		
		JCheckBox chckbxAdmin = new JCheckBox("");
		chckbxAdmin.setBounds(65, 147, 97, 23);
		contentPane.add(chckbxAdmin);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(10, 151, 48, 14);
		contentPane.add(lblAdmin);
		
		
		JButton btnSaveClient = new JButton("Save");
		btnSaveClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {				
				AdminForm.client.setClientID(Integer.parseInt(fldClientID.getText()));
				AdminForm.client.setClientName(fldClientName.getText());				
				AdminForm.client.setClientEmail(fldEmail.getText());
				AdminForm.client.setCardID(Integer.parseInt(fldCardID.getText()));
				AdminForm.client.setClientPIN(fldClientPIN.getText());
				
				AdminForm.client.setIsAdmin(chckbxAdmin.isSelected());
				AdminForm.client.setIsExist(true);
				
				dc.updateClient(AdminForm.client);
			}catch(Exception err) {
				JOptionPane.showConfirmDialog(null, "Something went wrong", "Error", JOptionPane.DEFAULT_OPTION, 0, null);
			}
			}
		});
		btnSaveClient.setBounds(435, 147, 89, 23);
		contentPane.add(btnSaveClient);
		
		JButton btnStatusClient = new JButton("Status"); // to call ClientForm with SignIn information
		btnStatusClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Client client = AdminForm.client;
				if (client.getIsExist()) {
					boolean runMessage=false;
					ClientForm.main(client, AdminForm.ms, dc, runMessage);
				}else {
					JOptionPane.showConfirmDialog(null, "Client ID not found", "Error", JOptionPane.DEFAULT_OPTION, 0, null);
				}
			}
		});
		
		btnStatusClient.setBounds(336, 147, 89, 23);
		contentPane.add(btnStatusClient);
		
		class Local{
			void infoUpdate(int monthNumber){
				if(AdminForm.ms.getMonthStatus(monthNumber)) {fldMonthStatus.setText("Open");}else{fldMonthStatus.setText("Closed");};
			}
			
			void clientInfoUpdate() {
				fldClientName.setText(AdminForm.client.getClientName());
				fldEmail.setText(AdminForm.client.getClientEmail());
				fldCardID.setText(String.valueOf(AdminForm.client.getCardID()));
				fldClientPIN.setText(AdminForm.client.getClientPIN());
				chckbxAdmin.setSelected(AdminForm.client.getIsAdmin());
			}
		}
	
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(595, 11, 48, 14);
		contentPane.add(lblMonth);
		
		JComboBox boxMonths = new JComboBox();
		
		boxMonths.setModel(new DefaultComboBoxModel(new String[] {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		boxMonths.setToolTipText("");
		boxMonths.setMaximumRowCount(13);
		boxMonths.setBounds(636, 7, 96, 22);
		contentPane.add(boxMonths);
		
		boxMonths.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				new Local().infoUpdate(
						monthNumber.get(boxMonths.getSelectedItem().toString())
				);
			}
		});
		
		JButton btnLockMonth = new JButton("Lock");
		btnLockMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminForm.ms=dc.setMonthStatus(
						monthNumber.get(boxMonths.getSelectedItem().toString()), 
						false, 
						AdminForm.ms);
				
				new Local().infoUpdate(
						monthNumber.get(boxMonths.getSelectedItem().toString())
				);
			}
		});
		btnLockMonth.setBounds(899, 7, 89, 23);
		contentPane.add(btnLockMonth);
		
		JButton btnUnlockMonth = new JButton("Unlock");
		btnUnlockMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminForm.ms=dc.setMonthStatus(
						monthNumber.get(boxMonths.getSelectedItem().toString()), 
						true, 
						AdminForm.ms);

				new Local().infoUpdate(
						monthNumber.get(boxMonths.getSelectedItem().toString())
				);
			}
		});
		btnUnlockMonth.setBounds(998, 7, 89, 23);
		contentPane.add(btnUnlockMonth);
		
	    DefaultTableModel modelMonthReport = new DefaultTableModel(AdminForm.mr, columnNames);
	    tableMonthReport = new JTable(modelMonthReport);
		JScrollPane scrollPane = new JScrollPane(tableMonthReport);
		tableMonthReport.setAutoCreateRowSorter(true);
		
//		tableMonthReport.setFillsViewportHeight(true);
//		tableMonthReport.setColumnSelectionAllowed(true);
//		tableMonthReport.setCellSelectionEnabled(true);
//		scrollPane.setViewportView(tableMonthReport);
//		scrollPane.setColumnHeaderView(tableMonthReport);
		
		scrollPane.setBounds(574, 76, 513, 338);
		contentPane.add(scrollPane);
		
		JButton btnReportMonth = new JButton("Report");
		btnReportMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdminForm.mr=dc.getMonthReport(monthNumber.get(boxMonths.getSelectedItem().toString()));
					tableMonthReport.setModel(new DefaultTableModel(AdminForm.mr, columnNames));
				}catch(Exception err) {
					//do nothing
				};
			}
		});
		btnReportMonth.setBounds(899, 42, 89, 23);
		contentPane.add(btnReportMonth);
		
		JButton btnFindClient = new JButton("Find");
		btnFindClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					AdminForm.client.setClientID(Integer.parseInt(fldClientID.getText()));
				}catch(Exception err) {
					AdminForm.client.setClientID(0);
				};
				
				AdminForm.client = dc.getClient(client);
				new Local().clientInfoUpdate();
				
				}
		});
		btnFindClient.setBounds(186, 7, 89, 23);
		contentPane.add(btnFindClient);
		
		JButton btnCreateClient = new JButton("Create");
		btnCreateClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				AdminForm.client.deleteInfo();
				new Local().clientInfoUpdate();
				AdminForm.client.setClientID(Integer.parseInt(fldClientID.getText()));
				dc.createNewClient(AdminForm.client);
			}catch(Exception err) {
				JOptionPane.showConfirmDialog(null, "No client ID", "Error", JOptionPane.DEFAULT_OPTION, 0, null);
			}

			}
		});
		btnCreateClient.setBounds(336, 7, 89, 23);
		contentPane.add(btnCreateClient);

		JButton btnDeleteClient = new JButton("Delete");
		btnDeleteClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminForm.client.setClientID(Integer.parseInt(fldClientID.getText()));
				dc.deleteClient(AdminForm.client);
				AdminForm.client.deleteInfo();
				new Local().clientInfoUpdate();
			}
		});
		btnDeleteClient.setBounds(435, 7, 89, 23);
		contentPane.add(btnDeleteClient);
		
		
		JLabel lblClientPIN = new JLabel("PIN");
		lblClientPIN.setBounds(10, 126, 48, 14);
		contentPane.add(lblClientPIN);
		
		fldClientPIN = new JTextField();
		fldClientPIN.setColumns(10);
		fldClientPIN.setBounds(68, 124, 207, 20);
		contentPane.add(fldClientPIN);
		
		fldMonthStatus = new JTextField();
		fldMonthStatus.setEditable(false);
		fldMonthStatus.setText(" Status");
		fldMonthStatus.setColumns(10);
		fldMonthStatus.setBounds(742, 8, 96, 20);
		contentPane.add(fldMonthStatus);
		
		JButton btnReportMonthSigned = new JButton("Signed");
		btnReportMonthSigned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//overall goal - to add search functionality to mr[][] array by "Signed"=true criteria
				//first - mapping Signed/Unsigned and location within mr[][] array
				HashMap <Integer, String> mappedReport = new HashMap<Integer, String>();
				for (int i=0;i<AdminForm.mr.length;i++) {
					mappedReport.put(i, AdminForm.mr[i][3]);
				}

				//searching within HashMap - result is Map with position in mr[][] array using lambda
				Map<Integer, String> rs = mappedReport.entrySet()
						.stream()
						.filter(map -> "true".equals(map.getValue()))
						.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
				
				//creating new array String[][] and transferring information from mr[][]
				String[][] filteredReport = new String[rs.size()][4];
				int i=0;
		        for (Integer position: rs.keySet()) {
			        filteredReport[i][0]=AdminForm.mr[position][0];
			        filteredReport[i][1]=AdminForm.mr[position][1];
			        filteredReport[i][2]=AdminForm.mr[position][2];
			        filteredReport[i][3]=AdminForm.mr[position][3];
			        i++;
		    	}
				
				tableMonthReport.setModel(new DefaultTableModel(filteredReport, columnNames));
				
			}
		});
		btnReportMonthSigned.setBounds(998, 41, 89, 23);
		contentPane.add(btnReportMonthSigned);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(546, 7, 9, 407);
		contentPane.add(separator_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Save text message", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(4, 290, 520, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JEditorPane fldTextMessage = new JEditorPane();
		fldTextMessage.setBounds(6, 16, 507, 74);
		panel.add(fldTextMessage);
		
		//reading from txt file and displaying in fldTextMessage field
		fldTextMessage.setText(TxtController.readTextMessage());
		
		JButton btnTextMessage = new JButton("Save");
		btnTextMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				TxtController.saveTextMessage(fldTextMessage.getText());
			}
		});
		btnTextMessage.setBounds(435, 391, 89, 23);
		contentPane.add(btnTextMessage);
		
		
	}
}
