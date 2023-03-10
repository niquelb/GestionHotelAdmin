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
import java.awt.BorderLayout;

public class UserListView extends JPanel {
	
	private final UserListViewController controller=new UserListViewController(this);
	
	private JScrollPane pane;
	private JTable table;
	
	private JTextField textFieldCurrentPage;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	
	private JButton btnClearFilters;
	private JButton btnSubmitFilters;
	private JButton btnCreate_user;
	
	/**
	 * Create the panel.
	 */
	public UserListView() {
		setBackground(new Color(45, 45, 45));
		setLayout(new BorderLayout(0, 0));
		
		JPanel filters_panel = new JPanel();
		filters_panel.setPreferredSize(new Dimension(this.getWidth(), 175));
		filters_panel.setBackground(new Color(45, 45, 45));
		add(filters_panel, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Refinar Busqueda");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
		
		btnClearFilters = new JButton("Limpiar");
		btnClearFilters.setEnabled(false);
		btnClearFilters.setActionCommand("clear");
		
		btnSubmitFilters = new JButton("Filtrar");
		btnSubmitFilters.setActionCommand("filter");
		
		JLabel lblName = new JLabel("Nombre");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		GroupLayout gl_filters_panel = new GroupLayout(filters_panel);
		gl_filters_panel.setHorizontalGroup(
			gl_filters_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filters_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTitle)
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
							.addGap(156)
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnClearFilters, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
								.addComponent(btnSubmitFilters, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_filters_panel.setVerticalGroup(
			gl_filters_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filters_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSubmitFilters, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addComponent(btnClearFilters, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addGap(98))
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(22))))
		);
		filters_panel.setLayout(gl_filters_panel);
		
		JPanel list_panel = new JPanel();
		add(list_panel, BorderLayout.CENTER);
		list_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel top_panel = new JPanel();
		top_panel.setBackground(new Color(45, 45, 45));
		list_panel.add(top_panel, BorderLayout.NORTH);
		
		btnCreate_user = new JButton("Crear Usuario");
		btnCreate_user.setPreferredSize(new Dimension(250, 20));
		btnCreate_user.setActionCommand("add_user");
		top_panel.add(btnCreate_user);
		
		JPanel bottom_navi = new JPanel();
		bottom_navi.setPreferredSize(new Dimension(0, 100));
		bottom_navi.setBackground(new Color(45, 45, 45));
		list_panel.add(bottom_navi, BorderLayout.SOUTH);
		
		JButton btnFirstPage = new JButton("<<");
		btnFirstPage.setActionCommand("first_page");
		bottom_navi.add(btnFirstPage);
		
		JButton btnPrevPage = new JButton("<");
		btnPrevPage.setActionCommand("prev_page");
		bottom_navi.add(btnPrevPage);
		
		textFieldCurrentPage = new JTextField();
		textFieldCurrentPage.setText("1");
		textFieldCurrentPage.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCurrentPage.setEditable(false);
		textFieldCurrentPage.setColumns(10);
		bottom_navi.add(textFieldCurrentPage);
		
		JButton btnNextPage = new JButton(">");
		btnNextPage.setActionCommand("next_page");
		bottom_navi.add(btnNextPage);
		
		JButton btnLastPage = new JButton(">>");
		btnLastPage.setActionCommand("last_page");
		bottom_navi.add(btnLastPage);
		
		pane= new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		list_panel.add(pane, BorderLayout.CENTER);
		table=new JTable();
		
		btnFirstPage.addActionListener(controller);
		btnFirstPage.setActionCommand("first_page");
		btnPrevPage.addActionListener(controller);
		btnPrevPage.setActionCommand("prev_page");
		btnNextPage.addActionListener(controller);
		btnNextPage.setActionCommand("next_page");
		btnLastPage.addActionListener(controller);
		btnLastPage.setActionCommand("last_page");

		ComponentInit.setButtonProperties(btnClearFilters, controller);
		ComponentInit.setButtonProperties(btnSubmitFilters, controller);
		ComponentInit.setButtonProperties(btnCreate_user, controller);
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
	public JButton getBtnCreate_user() {
		return btnCreate_user;
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
	public void setBtnCreate_user(JButton btnAddUser) {
		this.btnCreate_user = btnAddUser;
	}

	/**
	 * @param textFieldCurrentPage the textFieldCurrentPage to set
	 */
	public void setTextFieldCurrentPage(JTextField textFieldCurrentPage) {
		this.textFieldCurrentPage = textFieldCurrentPage;
	}
}
