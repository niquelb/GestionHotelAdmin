package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import utils.ComponentInit;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.BookingListViewController;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class BookingListView extends JPanel {
	
	private final BookingListViewController controller;
	
	private JTextField textFieldUser_id;
	private JTextField textFieldStart_date;
	private JTextField textFieldEnd_date;
	private JTextField textFieldCurrentPage;
	private JButton btnClear;
	
	private JTable table=new JTable();
	private JScrollPane pane;
	
	//TODO page selector

	/**
	 * Create the panel.
	 */
	public BookingListView() {
		setBackground(new Color(45, 45, 45));
		setLayout(new BorderLayout(10, 5));
		
		JPanel filters_panel = new JPanel();
		filters_panel.setPreferredSize(new Dimension(this.getWidth(), 175));
		filters_panel.setBackground(new Color(45, 45, 45));
		add(filters_panel, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Refinar Busqueda");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
		
		JLabel lblUser_id = new JLabel("Email del usuario");
		lblUser_id.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser_id.setForeground(Color.WHITE);
		lblUser_id.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldUser_id = new JTextField();
		lblUser_id.setLabelFor(textFieldUser_id);
		textFieldUser_id.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Fecha de Inicio (AÑO-MES-DIA)");
		lblStartDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartDate.setForeground(Color.WHITE);
		lblStartDate.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldStart_date = new JTextField();
		lblStartDate.setLabelFor(textFieldStart_date);
		textFieldStart_date.setColumns(10);
		
		JLabel lblEnd_date = new JLabel("Fecha Final (AÑO-MES-DIA)");
		lblEnd_date.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnd_date.setForeground(Color.WHITE);
		lblEnd_date.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldEnd_date = new JTextField();
		lblEnd_date.setLabelFor(textFieldEnd_date);
		textFieldEnd_date.setColumns(10);
		
		JButton btnSubmit = new JButton("Filtrar");
		btnSubmit.setActionCommand("submit");
		
		btnClear = new JButton("Limpiar");
		btnClear.setEnabled(false);
		btnClear.setActionCommand("clear");
		GroupLayout gl_filters_panel = new GroupLayout(filters_panel);
		gl_filters_panel.setHorizontalGroup(
			gl_filters_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filters_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblUser_id, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textFieldUser_id, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
								.addComponent(lblStartDate)
								.addComponent(textFieldStart_date, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addGap(181)
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnClear, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
								.addComponent(btnSubmit, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)))
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblEnd_date, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldEnd_date, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_filters_panel.setVerticalGroup(
			gl_filters_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filters_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblUser_id, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldUser_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(27)))
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addComponent(lblStartDate, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldStart_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addComponent(lblEnd_date, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldEnd_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		filters_panel.setLayout(gl_filters_panel);
		
		JPanel list_panel = new JPanel();
		list_panel.setBackground(new Color(45, 45, 45));
		add(list_panel, BorderLayout.CENTER);
		add(list_panel, BorderLayout.CENTER);
		list_panel.setLayout(new BorderLayout(0, 0));
		
		JPanel top_panel = new JPanel();
		top_panel.setBackground(new Color(45, 45, 45));
		list_panel.add(top_panel, BorderLayout.NORTH);
		
		JButton btnCreate_booking = new JButton("Crear Reserva");
		btnCreate_booking.setPreferredSize(new Dimension(250, 20));
		btnCreate_booking.setActionCommand("create_booking");
		top_panel.add(btnCreate_booking);
		
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
		
		pane = new JScrollPane();
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		list_panel.add(pane, BorderLayout.CENTER);
		
		controller=new BookingListViewController(this);
		
		ComponentInit.setButtonProperties(btnSubmit, controller);
		ComponentInit.setButtonProperties(btnClear, controller);
		ComponentInit.setButtonProperties(btnCreate_booking, controller);

	}

	/**
	 * @return the textFieldUser_id
	 */
	public JTextField getTextFieldUser_id() {
		return textFieldUser_id;
	}

	/**
	 * @return the textFieldStart_date
	 */
	public JTextField getTextFieldStart_date() {
		return textFieldStart_date;
	}

	/**
	 * @return the textFieldEnd_date
	 */
	public JTextField getTextFieldEnd_date() {
		return textFieldEnd_date;
	}

	/**
	 * @return the textFieldCurrentPage
	 */
	public JTextField getTextFieldCurrentPage() {
		return textFieldCurrentPage;
	}

	/**
	 * @return the btnClear
	 */
	public JButton getBtnClear() {
		return btnClear;
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
	 * @param textFieldUser_id the textFieldUser_id to set
	 */
	public void setTextFieldUser_id(JTextField textFieldUser_id) {
		this.textFieldUser_id = textFieldUser_id;
	}

	/**
	 * @param textFieldStart_date the textFieldStart_date to set
	 */
	public void setTextFieldStart_date(JTextField textFieldStart_date) {
		this.textFieldStart_date = textFieldStart_date;
	}

	/**
	 * @param textFieldEnd_date the textFieldEnd_date to set
	 */
	public void setTextFieldEnd_date(JTextField textFieldEnd_date) {
		this.textFieldEnd_date = textFieldEnd_date;
	}

	/**
	 * @param textFieldCurrentPage the textFieldCurrentPage to set
	 */
	public void setTextFieldCurrentPage(JTextField textFieldCurrentPage) {
		this.textFieldCurrentPage = textFieldCurrentPage;
	}

	/**
	 * @param btnClear the btnClear to set
	 */
	public void setBtnClear(JButton btnClear) {
		this.btnClear = btnClear;
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
