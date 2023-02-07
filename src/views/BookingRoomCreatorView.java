package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controllers.BookingRoomCreatorViewController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class BookingRoomCreatorView extends JFrame {

	private BookingRoomCreatorViewController controller;
	
	private javaswingdev.GoogleMaterialIcon iconClose=new javaswingdev.GoogleMaterialIcon();
	
	private JScrollPane scrollPane;
	private JTable table=new JTable();
	private JPanel pane;

	/**
	 * Create the frame.
	 */
	public BookingRoomCreatorView(int booking_id) {
		setTitle("Crear Reserva");
		pane = new JPanel();
		pane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//		setResizable(false);
		setUndecorated(true);
		setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setVisible(true);
        setMinimumSize(new Dimension(700,700));
        setSize(1000,700);
        
		setContentPane(pane);
		pane.setLayout(new BorderLayout(5, 5));
		
		JPanel top_panel = new JPanel();
		top_panel.setPreferredSize(new Dimension(pane.getWidth(), 75));
		pane.add(top_panel, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Crear Reserva | Seleccionar Habitaciones");
		lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
		
		iconClose.setColor1(new java.awt.Color(111, 111, 111));
        iconClose.setColor2(new java.awt.Color(215, 215, 215));
        iconClose.setIcon(javaswingdev.GoogleMaterialDesignIcon.CLOSE);
        iconClose.setSize(18);
        
        JLabel lblCloseBtn = new JLabel("");
		lblCloseBtn.setIcon(iconClose.toIcon());
		
		GroupLayout gl_top_panel = new GroupLayout(top_panel);
		gl_top_panel.setHorizontalGroup(
			gl_top_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_top_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED, 485, Short.MAX_VALUE)
					.addComponent(lblCloseBtn, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_top_panel.setVerticalGroup(
			gl_top_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_top_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_top_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCloseBtn, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		top_panel.setLayout(gl_top_panel);
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setPreferredSize(new Dimension(pane.getWidth(), 25));
		pane.add(bottom_panel, BorderLayout.SOUTH);
		
		JPanel main_panel = new JPanel();
		main_panel.setBackground(new Color(45, 45, 45));
		pane.add(main_panel, BorderLayout.CENTER);
		main_panel.setLayout(new BorderLayout(5, 5));
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		main_panel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel east_panel = new JPanel();
		east_panel.setPreferredSize(new Dimension(10, pane.getHeight()));
		pane.add(east_panel, BorderLayout.EAST);
		
		JPanel west_panel = new JPanel();
		west_panel.setPreferredSize(new Dimension(10, pane.getHeight()));
		pane.add(west_panel, BorderLayout.WEST);
		
		controller=new BookingRoomCreatorViewController(this, booking_id);
		
		lblCloseBtn.addMouseListener(controller);
	}
	
	public int getRow() {
		return table.getSelectedRow();
	}

	/**
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param scrollPane the scrollPane to set
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}
	
	

}
