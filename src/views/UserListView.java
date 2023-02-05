package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.UserListViewController;
import utils.ComponentInit;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class UserListView extends JPanel {
	
	private final UserListViewController controller=new UserListViewController(this);
	
	private JTable table;
	private JScrollPane pane;
	private JPanel filters_panel;
	
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JButton btnSubmitFilters;
	private JButton btnClearFilters;
	
	/**
	 * Create the panel.
	 */
	public UserListView() {	
		table=new JTable();
		pane=new JScrollPane();
		filters_panel = new JPanel();
		filters_panel.setForeground(new Color(255, 255, 255));
		filters_panel.setBackground(new Color(45, 45, 45));
		
		GroupLayout gl_filters_panel = new GroupLayout(filters_panel);
		
		filters_panel.setLayout(gl_filters_panel);
		setLayout(null);

		add(pane);
		add(filters_panel);
		
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pane.setBounds(10, 164, 706, 439);
		
		pane.setBackground(Color.DARK_GRAY);
		pane.setForeground(new Color(45, 45, 45));
		
		filters_panel.setBounds(10, 11, 706, 142);
		
		JLabel lblTitle = new JLabel("Refinar Busqueda");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
		
		JLabel lblName = new JLabel("Nombre");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		btnSubmitFilters = new JButton("Filtrar");
		ComponentInit.setButtonProperties(btnSubmitFilters,controller);
		btnSubmitFilters.setActionCommand("filter");
		
		btnClearFilters = new JButton("Limpiar");
		ComponentInit.setButtonProperties(btnClearFilters,controller);
		btnClearFilters.setActionCommand("clear");
		btnClearFilters.setVisible(false);
		
		gl_filters_panel.setHorizontalGroup(
			gl_filters_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filters_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitle)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblEmail))
							.addGap(18)
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldEmail)
								.addComponent(textFieldName, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
							.addGap(47)
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnClearFilters, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(btnSubmitFilters, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(149, Short.MAX_VALUE))
		);
		gl_filters_panel.setVerticalGroup(
			gl_filters_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filters_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addComponent(lblName)
							.addGap(18)
							.addComponent(lblEmail))
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_filters_panel.createSequentialGroup()
									.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(7))
								.addGroup(gl_filters_panel.createSequentialGroup()
									.addComponent(btnClearFilters, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldEmail, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSubmitFilters, Alignment.TRAILING))))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		
		controller.buildTable(null, null);
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
