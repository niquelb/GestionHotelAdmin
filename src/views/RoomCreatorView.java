package views;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controllers.RoomCreatorViewController;
import utils.ComponentInit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class RoomCreatorView extends JFrame {
	
	private final RoomCreatorViewController controller;
	
	private javaswingdev.GoogleMaterialIcon iconClose=new javaswingdev.GoogleMaterialIcon();

	private JPanel pane;
	private JTextField textFieldName;
	private JTextField textFieldQuantity;
	private JTextField textFieldPrice;
	private JTextField textFieldMax_guests;
	private JTextField textFieldNum_beds;
	private JTextArea textAreaDescription;

	/**
	 * Create the frame.
	 */
	public RoomCreatorView() {
		setTitle("Crear Habitacion");
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
		
		JLabel lblTitle = new JLabel("Crear Habitacion");
		lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
		
		iconClose.setColor1(new java.awt.Color(111, 111, 111));
        iconClose.setColor2(new java.awt.Color(215, 215, 215));
        iconClose.setIcon(javaswingdev.GoogleMaterialDesignIcon.CLOSE);
        iconClose.setSize(18);
        
        JLabel lblCloseBtn = new JLabel("");
		lblCloseBtn.setIcon(iconClose.toIcon());
		
		JLabel lblName = new JLabel("Nombre (Obligatorio)");
		lblName.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldName = new JTextField();
		lblName.setLabelFor(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblDescription = new JLabel("Descripcion");
		lblDescription.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textAreaDescription = new JTextArea();
		lblDescription.setLabelFor(textAreaDescription);
		
		JLabel lblQuantity = new JLabel("Cantidad");
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantity.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldQuantity = new JTextField();
		lblQuantity.setLabelFor(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		JLabel lblPrice = new JLabel("Precio");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldPrice = new JTextField();
		lblPrice.setLabelFor(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		JLabel lblMax_guests = new JLabel("Cap. Max.");
		lblMax_guests.setHorizontalAlignment(SwingConstants.CENTER);
		lblMax_guests.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldMax_guests = new JTextField();
		lblMax_guests.setLabelFor(textFieldMax_guests);
		textFieldMax_guests.setColumns(10);
		
		JLabel lblNum_beds = new JLabel("Num. Camas");
		lblNum_beds.setHorizontalAlignment(SwingConstants.CENTER);
		lblNum_beds.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldNum_beds = new JTextField();
		lblNum_beds.setLabelFor(textFieldNum_beds);
		textFieldNum_beds.setColumns(10);
		
		JButton btnSubmit = new JButton("Enviar");
		btnSubmit.setActionCommand("submit");
		GroupLayout gl_pane = new GroupLayout(pane);
		gl_pane.setHorizontalGroup(
			gl_pane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pane.createSequentialGroup()
							.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pane.createSequentialGroup()
									.addComponent(lblTitle)
									.addGap(266)
									.addComponent(lblCloseBtn, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pane.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblName)
										.addComponent(lblDescription)
										.addGroup(gl_pane.createSequentialGroup()
											.addGap(10)
											.addGroup(gl_pane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(textAreaDescription)
												.addComponent(textFieldName, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))))))
							.addContainerGap())
						.addGroup(gl_pane.createSequentialGroup()
							.addGap(50)
							.addGroup(gl_pane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textFieldQuantity, Alignment.LEADING, 0, 72, Short.MAX_VALUE)
								.addComponent(lblQuantity, Alignment.LEADING))
							.addGap(18)
							.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPrice, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
								.addComponent(textFieldPrice, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_pane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldMax_guests, 0, 79, Short.MAX_VALUE)
								.addComponent(lblMax_guests))
							.addGap(18)
							.addGroup(gl_pane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldNum_beds)
								.addComponent(lblNum_beds))
							.addGap(55))
						.addGroup(gl_pane.createSequentialGroup()
							.addGap(160)
							.addComponent(btnSubmit, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addGap(166))))
		);
		gl_pane.setVerticalGroup(
			gl_pane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCloseBtn)
						.addGroup(gl_pane.createSequentialGroup()
							.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textAreaDescription, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pane.createSequentialGroup()
									.addGroup(gl_pane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_pane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldMax_guests, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldNum_beds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblMax_guests, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNum_beds, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(188, Short.MAX_VALUE))
		);
		pane.setLayout(gl_pane);
		
		controller=new RoomCreatorViewController(this);
		
		lblCloseBtn.addMouseListener(controller);
		btnSubmit.setActionCommand("submit");
        ComponentInit.setButtonProperties(btnSubmit, controller);
	}

	/**
	 * @return the textFieldName
	 */
	public JTextField getTextFieldName() {
		return textFieldName;
	}

	/**
	 * @return the textFieldQuantity
	 */
	public JTextField getTextFieldQuantity() {
		return textFieldQuantity;
	}

	/**
	 * @return the textFieldPrice
	 */
	public JTextField getTextFieldPrice() {
		return textFieldPrice;
	}

	/**
	 * @return the textFieldMax_guests
	 */
	public JTextField getTextFieldMax_guests() {
		return textFieldMax_guests;
	}

	/**
	 * @return the textFieldNum_beds
	 */
	public JTextField getTextFieldNum_beds() {
		return textFieldNum_beds;
	}

	/**
	 * @return the textAreaDescription
	 */
	public JTextArea getTextAreaDescription() {
		return textAreaDescription;
	}

	/**
	 * @param textFieldName the textFieldName to set
	 */
	public void setTextFieldName(JTextField textFieldName) {
		this.textFieldName = textFieldName;
	}

	/**
	 * @param textFieldQuantity the textFieldQuantity to set
	 */
	public void setTextFieldQuantity(JTextField textFieldQuantity) {
		this.textFieldQuantity = textFieldQuantity;
	}

	/**
	 * @param textFieldPrice the textFieldPrice to set
	 */
	public void setTextFieldPrice(JTextField textFieldPrice) {
		this.textFieldPrice = textFieldPrice;
	}

	/**
	 * @param textFieldMax_guests the textFieldMax_guests to set
	 */
	public void setTextFieldMax_guests(JTextField textFieldMax_guests) {
		this.textFieldMax_guests = textFieldMax_guests;
	}

	/**
	 * @param textFieldNum_beds the textFieldNum_beds to set
	 */
	public void setTextFieldNum_beds(JTextField textFieldNum_beds) {
		this.textFieldNum_beds = textFieldNum_beds;
	}

	/**
	 * @param textAreaDescription the textAreaDescription to set
	 */
	public void setTextAreaDescription(JTextArea textAreaDescription) {
		this.textAreaDescription = textAreaDescription;
	}
	
	
}
