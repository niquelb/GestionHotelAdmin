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
import javax.swing.SwingConstants;

public class UserListView extends JPanel {
	
	private final UserListViewController controller=new UserListViewController(this);
	
	private JTable table;
	private JScrollPane pane;
	private JPanel filters_panel;
	
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JButton btnSubmitFilters;
	private JButton btnClearFilters;
	private JButton btnAddUser;
	
	/**
	 * Page Selector
	 */
	private JTextField textFieldCurrentPage;
	private JButton btnPrevPage;
	private JButton btnFirstPage;
	private JButton btnNextPage;
	private JButton btnLastPage;
	
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
		pane.setBounds(10, 254, 706, 200);
		
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
		
		btnAddUser = new JButton("AÑADIR USUARIO");
		btnAddUser.setBounds(10, 164, 706, 29);
		ComponentInit.setButtonProperties(btnAddUser, controller);
		btnAddUser.setActionCommand("add_user");
		add(btnAddUser);
		
		JPanel page_selector_panel = new JPanel();
		page_selector_panel.setBounds(10, 204, 706, 39);
		add(page_selector_panel);
		
		textFieldCurrentPage = new JTextField();
		textFieldCurrentPage.setEditable(false);
		textFieldCurrentPage.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCurrentPage.setColumns(10);
		textFieldCurrentPage.setText(String.valueOf(1));
		
		btnPrevPage = new JButton("<");
		btnFirstPage = new JButton("<<");
		btnNextPage = new JButton(">");
		btnLastPage = new JButton(">>");
		
		ComponentInit.setButtonProperties(btnPrevPage, controller);
		btnPrevPage.setActionCommand("prev_page");
		ComponentInit.setButtonProperties(btnFirstPage, controller);
		btnFirstPage.setActionCommand("first_page");
		ComponentInit.setButtonProperties(btnNextPage, controller);
		btnNextPage.setActionCommand("next_page");
		ComponentInit.setButtonProperties(btnLastPage, controller);
		btnLastPage.setActionCommand("last_page");
		
		GroupLayout gl_page_selector_panel = new GroupLayout(page_selector_panel);
		gl_page_selector_panel.setHorizontalGroup(
			gl_page_selector_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_page_selector_panel.createSequentialGroup()
					.addGap(204)
					.addComponent(btnFirstPage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPrevPage, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldCurrentPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNextPage, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLastPage, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(204, Short.MAX_VALUE))
		);
		gl_page_selector_panel.setVerticalGroup(
			gl_page_selector_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_page_selector_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_page_selector_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldCurrentPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPrevPage)
						.addComponent(btnFirstPage)
						.addComponent(btnNextPage)
						.addComponent(btnLastPage))
					.addGap(8))
		);
		page_selector_panel.setLayout(gl_page_selector_panel);
		
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
	 * @return the textFieldName
	 */
	public JTextField getTextFieldName() {
		return textFieldName;
	}

	/**
	 * @return the textFieldEmail
	 */
	public JTextField getTextFieldEmail() {
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
	 * @return the btnAddUser
	 */
	public JButton getBtnAddUser() {
		return btnAddUser;
	}

	/**
	 * @return the textFieldCurrentPage
	 */
	public JTextField getTextFieldCurrentPage() {
		return textFieldCurrentPage;
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
	 * @param textFieldName the textFieldName to set
	 */
	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	/**
	 * @param textFieldEmail the textFieldEmail to set
	 */
	public void setTextFieldEmail(JTextField textFieldEmail) {
		this.textFieldEmail = textFieldEmail;
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

	/**
	 * @param btnAddUser the btnAddUser to set
	 */
	public void setBtnAddUser(JButton btnAddUser) {
		this.btnAddUser = btnAddUser;
	}

	/**
	 * @param textFieldCurrentPage the textFieldCurrentPage to set
	 */
	public void setTextFieldCurrentPage(JTextField textFieldCurrentPage) {
		this.textFieldCurrentPage = textFieldCurrentPage;
	}

	
}
