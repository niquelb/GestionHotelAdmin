package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;

import controllers.RoomListViewController;
import utils.ComponentInit;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class RoomListView extends JPanel {
	
	private final RoomListViewController controller;
	
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JTextField textFieldNum_guests;
	private JButton btnClear;
	
	private JTable table=new JTable();
	private JScrollPane pane;

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
	public RoomListView() {
		setBackground(new Color(45, 45, 45));
		setLayout(new BorderLayout(10, 5));
		
		JPanel filters_panel = new JPanel();
		filters_panel.setPreferredSize(new Dimension(this.getWidth(), 175));
		filters_panel.setBackground(new Color(45, 45, 45));
		add(filters_panel, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Refinar Busqueda");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
		
		JLabel lblName = new JLabel("Nombre de la Habitacion");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Precio Maximo");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		
		JLabel lblNum_guests = new JLabel("Capacidad Minima (Num. Personas)");
		lblNum_guests.setForeground(Color.WHITE);
		lblNum_guests.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldNum_guests = new JTextField();
		textFieldNum_guests.setColumns(10);
		
		JButton btnSubmit = new JButton("Filtrar");
		
		btnClear = new JButton("Limpiar");
		btnClear.setEnabled(false);
		
		GroupLayout gl_filters_panel = new GroupLayout(filters_panel);
		gl_filters_panel.setHorizontalGroup(
			gl_filters_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filters_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_filters_panel.createSequentialGroup()
									.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textFieldPrice)
										.addComponent(lblPrice, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textFieldNum_guests)
										.addComponent(lblNum_guests, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(gl_filters_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(textFieldName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))))
						.addComponent(lblTitle))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSubmit, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
						.addComponent(btnClear, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_filters_panel.setVerticalGroup(
			gl_filters_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filters_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSubmit, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(lblTitle))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNum_guests, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNum_guests, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		filters_panel.setLayout(gl_filters_panel);
		
		JPanel list_panel = new JPanel();
		list_panel.setBackground(new Color(45, 45, 45));
		add(list_panel, BorderLayout.CENTER);
		list_panel.setLayout(new BorderLayout(0, 5));
		
		pane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		list_panel.add(pane, BorderLayout.CENTER);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel bottom_navi = new JPanel();
		bottom_navi.setBackground(new Color(45, 45, 45));
		bottom_navi.setPreferredSize(new Dimension(list_panel.getWidth(), 100));
		list_panel.add(bottom_navi, BorderLayout.SOUTH);
		
		btnFirstPage = new JButton("<<");
		bottom_navi.add(btnFirstPage);
		
		btnPrevPage = new JButton("<");
		bottom_navi.add(btnPrevPage);
		
		textFieldCurrentPage = new JTextField();
		textFieldCurrentPage.setText("1");
		textFieldCurrentPage.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCurrentPage.setEditable(false);
		textFieldCurrentPage.setColumns(10);
		bottom_navi.add(textFieldCurrentPage);
		
		btnNextPage = new JButton(">");
		bottom_navi.add(btnNextPage);
		
		btnLastPage = new JButton(">>");
		bottom_navi.add(btnLastPage);
		
		JPanel top_panel = new JPanel();
		top_panel.setBackground(new Color(45, 45, 45));
		list_panel.add(top_panel, BorderLayout.NORTH);
		
		JButton btnCreate_room = new JButton("Crear Habitacion");
		btnCreate_room.setPreferredSize(new Dimension(250,20));
		
		top_panel.add(btnCreate_room);

		controller=new RoomListViewController(this);
		
		btnFirstPage.addActionListener(controller);
		btnFirstPage.setActionCommand("first_page");
		btnPrevPage.addActionListener(controller);
		btnPrevPage.setActionCommand("prev_page");
		btnNextPage.addActionListener(controller);
		btnNextPage.setActionCommand("next_page");
		btnLastPage.addActionListener(controller);
		btnLastPage.setActionCommand("last_page");
		
		ComponentInit.setButtonProperties(btnSubmit, controller);
		btnSubmit.setActionCommand("submit");
		ComponentInit.setButtonProperties(btnClear, controller);
		btnClear.setActionCommand("clear");
		ComponentInit.setButtonProperties(btnCreate_room, controller);
		btnCreate_room.setActionCommand("create_room");
	}
	
	

	/**
	 * @return the textFieldCurrentPage
	 */
	public JTextField getTextFieldCurrentPage() {
		return textFieldCurrentPage;
	}



	/**
	 * @return the btnPrevPage
	 */
	public JButton getBtnPrevPage() {
		return btnPrevPage;
	}



	/**
	 * @return the btnFirstPage
	 */
	public JButton getBtnFirstPage() {
		return btnFirstPage;
	}



	/**
	 * @return the btnNextPage
	 */
	public JButton getBtnNextPage() {
		return btnNextPage;
	}



	/**
	 * @return the btnLastPage
	 */
	public JButton getBtnLastPage() {
		return btnLastPage;
	}



	/**
	 * @param textFieldCurrentPage the textFieldCurrentPage to set
	 */
	public void setTextFieldCurrentPage(JTextField textFieldCurrentPage) {
		this.textFieldCurrentPage = textFieldCurrentPage;
	}



	/**
	 * @param btnPrevPage the btnPrevPage to set
	 */
	public void setBtnPrevPage(JButton btnPrevPage) {
		this.btnPrevPage = btnPrevPage;
	}



	/**
	 * @param btnFirstPage the btnFirstPage to set
	 */
	public void setBtnFirstPage(JButton btnFirstPage) {
		this.btnFirstPage = btnFirstPage;
	}



	/**
	 * @param btnNextPage the btnNextPage to set
	 */
	public void setBtnNextPage(JButton btnNextPage) {
		this.btnNextPage = btnNextPage;
	}



	/**
	 * @param btnLastPage the btnLastPage to set
	 */
	public void setBtnLastPage(JButton btnLastPage) {
		this.btnLastPage = btnLastPage;
	}



	/**
	 * @return the btnClear
	 */
	public JButton getBtnClear() {
		return btnClear;
	}

	/**
	 * @param btnClear the btnClear to set
	 */
	public void setBtnClear(JButton btnClear) {
		this.btnClear = btnClear;
	}

	/**
	 * @return the textFieldName
	 */
	public JTextField getTextFieldName() {
		return textFieldName;
	}

	/**
	 * @return the textFieldPrice
	 */
	public JTextField getTextFieldPrice() {
		return textFieldPrice;
	}

	/**
	 * @return the textFieldNum_guests
	 */
	public JTextField getTextFieldNum_guests() {
		return textFieldNum_guests;
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
	 * @param textFieldName the textFieldName to set
	 */
	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	/**
	 * @param textFieldPrice the textFieldPrice to set
	 */
	public void setTextFieldPrice(JTextField textFieldPrice) {
		this.textFieldPrice = textFieldPrice;
	}

	/**
	 * @param textFieldNum_guests the textFieldNum_guests to set
	 */
	public void setTextFieldNum_guests(JTextField textFieldNum_guests) {
		this.textFieldNum_guests = textFieldNum_guests;
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

	
}
