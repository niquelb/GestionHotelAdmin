package views;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controllers.BookingCreatorViewController;
import controllers.RoomCreatorViewController;
import utils.ComponentInit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BookingCreatorView extends JFrame {

	private final BookingCreatorViewController controller;
	private javaswingdev.GoogleMaterialIcon iconClose=new javaswingdev.GoogleMaterialIcon();
	
	private JPanel pane;
	private JTextField textFieldUser_id;
	private JTextField textFieldStart_date;
	private JTextField textFieldEnd_date;
	private JTextField textFieldNum_adults;
	private JTextField textFieldNum_children;

	/**
	 * Create the frame.
	 */
	public BookingCreatorView() {
		setTitle("Crear Reserva");
		pane = new JPanel();
		pane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setVisible(true);
        setSize(500,500);
        
		setContentPane(pane);
		
		JLabel lblTitle = new JLabel("Crear Reserva");
		lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
		
		iconClose.setColor1(new java.awt.Color(111, 111, 111));
        iconClose.setColor2(new java.awt.Color(215, 215, 215));
        iconClose.setIcon(javaswingdev.GoogleMaterialDesignIcon.CLOSE);
        iconClose.setSize(18);
        
        JLabel lblCloseBtn = new JLabel("");
		lblCloseBtn.setIcon(iconClose.toIcon());
		
		JLabel lblUser_id = new JLabel("Email del Usuario (Obligatorio)");
		lblUser_id.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser_id.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldUser_id = new JTextField();
		lblUser_id.setLabelFor(textFieldUser_id);
		textFieldUser_id.setColumns(10);
		
		JLabel lblStart_date = new JLabel("Fecha Entrada (Obligatorio)");
		lblStart_date.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart_date.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldStart_date = new JTextField();
		lblStart_date.setLabelFor(textFieldStart_date);
		textFieldStart_date.setColumns(10);
		
		JLabel lblEnd_date = new JLabel("Fecha Salida(Obligatorio)");
		lblEnd_date.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnd_date.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldEnd_date = new JTextField();
		lblEnd_date.setLabelFor(textFieldEnd_date);
		textFieldEnd_date.setColumns(10);
		
		JLabel lblNum_adults = new JLabel("Numero de Adultos");
		lblNum_adults.setHorizontalAlignment(SwingConstants.CENTER);
		lblNum_adults.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		JLabel lblNum_children = new JLabel("Numero de Niños");
		lblNum_children.setHorizontalAlignment(SwingConstants.CENTER);
		lblNum_children.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldNum_adults = new JTextField();
		lblNum_adults.setLabelFor(textFieldNum_adults);
		textFieldNum_adults.setColumns(10);
		
		textFieldNum_children = new JTextField();
		lblNum_children.setLabelFor(textFieldNum_children);
		textFieldNum_children.setColumns(10);
		
		JButton btnSubmit = new JButton("Siguiente");
		btnSubmit.setActionCommand("submit");
		GroupLayout gl_pane = new GroupLayout(pane);
		gl_pane.setHorizontalGroup(
			gl_pane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pane.createSequentialGroup()
							.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUser_id, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
								.addGroup(gl_pane.createSequentialGroup()
									.addComponent(lblTitle)
									.addPreferredGap(ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
									.addComponent(lblCloseBtn, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldUser_id, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
								.addGroup(gl_pane.createSequentialGroup()
									.addGroup(gl_pane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textFieldStart_date, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
										.addComponent(lblStart_date, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_pane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_pane.createSequentialGroup()
											.addGap(0, 0, Short.MAX_VALUE)
											.addComponent(lblEnd_date, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
											.addGap(4))
										.addGroup(gl_pane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
											.addComponent(textFieldEnd_date, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_pane.createSequentialGroup()
									.addComponent(lblNum_adults, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
									.addComponent(lblNum_children, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pane.createSequentialGroup()
									.addComponent(textFieldNum_adults, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
									.addComponent(textFieldNum_children, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_pane.createSequentialGroup()
							.addGap(160)
							.addComponent(btnSubmit, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addGap(166))))
		);
		gl_pane.setVerticalGroup(
			gl_pane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCloseBtn, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUser_id, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldUser_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pane.createSequentialGroup()
							.addComponent(lblStart_date, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldStart_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldEnd_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblEnd_date, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNum_adults, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNum_children, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldNum_adults, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldNum_children, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(218, Short.MAX_VALUE))
		);
		pane.setLayout(gl_pane);
		
		controller=new BookingCreatorViewController(this);
		
		lblCloseBtn.addMouseListener(controller);
		btnSubmit.setActionCommand("submit");
		ComponentInit.setButtonProperties(btnSubmit, controller);
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
	 * @return the textFieldNum_adults
	 */
	public JTextField getTextFieldNum_adults() {
		return textFieldNum_adults;
	}

	/**
	 * @return the textFieldNum_children
	 */
	public JTextField getTextFieldNum_children() {
		return textFieldNum_children;
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
	 * @param textFieldNum_adults the textFieldNum_adults to set
	 */
	public void setTextFieldNum_adults(JTextField textFieldNum_adults) {
		this.textFieldNum_adults = textFieldNum_adults;
	}

	/**
	 * @param textFieldNum_children the textFieldNum_children to set
	 */
	public void setTextFieldNum_children(JTextField textFieldNum_children) {
		this.textFieldNum_children = textFieldNum_children;
	}
	
	

}
