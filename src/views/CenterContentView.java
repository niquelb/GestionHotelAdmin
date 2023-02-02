package views;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.CenterContentViewController;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

public class CenterContentView extends JPanel {
	
	private final CenterContentViewController controller=new CenterContentViewController(this);
	
	private JTable table;
	private JScrollPane pane;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JButton btnSubmitFilters;
	private JButton btnClearFilters;
	
	/**
	 * Create the panel.
	 */
	public CenterContentView() {	
		table=new JTable();
		pane=new JScrollPane();
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setBounds(10, 164, 706, 439);
		
		table.setSize(pane.getWidth(), pane.getHeight());
		setLayout(null);
		add(pane);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(109, 84, 222, 20);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblTitle = new JLabel("Refinar Busqueda");
		lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitle.setBounds(20, 35, 222, 38);
		add(lblTitle);
		
		JLabel lblName = new JLabel("Nombre");
		lblName.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblName.setBounds(30, 90, 69, 14);
		add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		lblEmail.setBounds(30, 128, 69, 14);
		add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(109, 122, 222, 20);
		add(textFieldEmail);
		
		btnSubmitFilters = new JButton("Filtrar");
		setButtonProperties(btnSubmitFilters);
		btnSubmitFilters.setActionCommand("filter");
		btnSubmitFilters.setBounds(380, 111, 154, 31);
		add(btnSubmitFilters);
		
		btnClearFilters = new JButton("Limpiar");
		setButtonProperties(btnClearFilters);
		btnClearFilters.setActionCommand("clear");
		btnClearFilters.setBounds(380, 73, 154, 31);
		btnClearFilters.setVisible(false);
		add(btnClearFilters);
		
		controller.buildTable();
	}
	
	public void setButtonProperties(JButton button) {
		button.setForeground(new Color(0, 0, 0));
		button.setBackground(new Color(255, 255, 255));
		button.setFocusable(false);
		button.setFont(new Font("Roboto Light", Font.BOLD, 18));
		
		button.addActionListener(controller);
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @return the pane
	 */
	public JScrollPane getPane() {
		return pane;
	}

	/**
	 * @return the textFieldFiltersName
	 */
	public JTextField getTextFieldFiltersName() {
		return textFieldName;
	}

	/**
	 * @return the textFieldFiltersEmail
	 */
	public JTextField getTextFieldFiltersEmail() {
		return textFieldEmail;
	}

	/**
	 * @return the btnSubmitFilters
	 */
	public JButton getBtnSubmitFilters() {
		return btnSubmitFilters;
	}

	/**
	 * @return the btnClearFilters
	 */
	public JButton getBtnClearFilters() {
		return btnClearFilters;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * @param pane the pane to set
	 */
	public void setPane(JScrollPane pane) {
		this.pane = pane;
	}

	/**
	 * @param textFieldFiltersName the textFieldFiltersName to set
	 */
	public void setTextFieldFiltersName(JTextField textFieldFiltersName) {
		this.textFieldName = textFieldFiltersName;
	}

	/**
	 * @param textFieldFiltersEmail the textFieldFiltersEmail to set
	 */
	public void setTextFieldFiltersEmail(JTextField textFieldFiltersEmail) {
		this.textFieldEmail = textFieldFiltersEmail;
	}

	/**
	 * @param btnSubmitFilters the btnSubmitFilters to set
	 */
	public void setBtnSubmitFilters(JButton btnSubmitFilters) {
		this.btnSubmitFilters = btnSubmitFilters;
	}

	/**
	 * @param btnClearFilters the btnClearFilters to set
	 */
	public void setBtnClearFilters(JButton btnClearFilters) {
		this.btnClearFilters = btnClearFilters;
	}

	

	
}
