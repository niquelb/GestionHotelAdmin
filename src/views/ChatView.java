package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

import controllers.ChatViewController;
import utils.ComponentInit;
import utils.title_bar.SimpleTitleBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;

public class ChatView extends JFrame {

	private final ChatViewController controller;
	
	private javaswingdev.GoogleMaterialIcon iconSend=new javaswingdev.GoogleMaterialIcon();
	private javaswingdev.GoogleMaterialIcon iconClose=new javaswingdev.GoogleMaterialIcon();

	private JPanel contentPane;
	private JTextField textFieldChat_box;
	private JPanel header;
	private JPanel main_content;
	private JPanel chat_box;
	private JScrollPane scrollPane;
	private JTextArea txtAreaChat;
	private JLabel lblSend;
	private JLabel lblCloseIcon;

	/**
	 * Create the frame.
	 */
	public ChatView() {
		initFrame();
		buildFrame();
		
		controller=new ChatViewController(this);
		
		lblSend.addMouseListener(controller);
        lblCloseIcon.addMouseListener(controller);
	}
	
	public void initFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//		setResizable(false);
		setUndecorated(true);
		setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setVisible(true);
        setSize(500,500);
	}
	
	public void buildFrame() {
		setBackground(new Color(45, 45, 45));
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		header = new JPanel();
		header.setBackground(new Color(45, 45, 45));
		header.setPreferredSize(new Dimension(contentPane.getWidth(), 50));
		contentPane.add(header, BorderLayout.NORTH);
		
		iconClose.setColor1(new java.awt.Color(111, 111, 111));
        iconClose.setColor2(new java.awt.Color(215, 215, 215));
        iconClose.setIcon(javaswingdev.GoogleMaterialDesignIcon.CLOSE);
        iconClose.setSize(18);
        
        lblCloseIcon = new JLabel("");
        lblCloseIcon.setIcon(iconClose.toIcon());
		GroupLayout gl_header = new GroupLayout(header);
		gl_header.setHorizontalGroup(
			gl_header.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_header.createSequentialGroup()
					.addContainerGap(444, Short.MAX_VALUE)
					.addComponent(lblCloseIcon)
					.addContainerGap())
		);
		gl_header.setVerticalGroup(
			gl_header.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_header.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCloseIcon)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		header.setLayout(gl_header);
		
		main_content = new JPanel();
		main_content.setBackground(new Color(45, 45, 45));
		contentPane.add(main_content, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(new LineBorder(new Color(45, 45, 45), 4));
		GroupLayout gl_main_content = new GroupLayout(main_content);
		gl_main_content.setHorizontalGroup(
			gl_main_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_main_content.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_main_content.setVerticalGroup(
			gl_main_content.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_main_content.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txtAreaChat = new JTextArea();
		txtAreaChat.setColumns(10);
		txtAreaChat.setEditable(false);
		txtAreaChat.setLineWrap(true);
		scrollPane.setViewportView(txtAreaChat);
		main_content.setLayout(gl_main_content);
		
		
		
		chat_box = new JPanel();
		chat_box.setBackground(new Color(45, 45, 45));
		chat_box.setPreferredSize(new Dimension(contentPane.getWidth(), 50));
		contentPane.add(chat_box, BorderLayout.SOUTH);
		
		textFieldChat_box = new JTextField();
		textFieldChat_box.setForeground(new Color(255, 255, 255));
		textFieldChat_box.setBackground(new Color(65, 65, 65));
		textFieldChat_box.setColumns(10);
		
		iconSend.setColor1(new java.awt.Color(111, 111, 111));
		iconSend.setColor2(new java.awt.Color(215, 215, 215));
		iconSend.setIcon(javaswingdev.GoogleMaterialDesignIcon.SEND);
		iconSend.setSize(18);
		
		lblSend = new JLabel("");
		lblSend.setIcon(iconSend.toIcon());
		
		iconClose.setColor1(new java.awt.Color(111, 111, 111));
        iconClose.setColor2(new java.awt.Color(215, 215, 215));
        iconClose.setIcon(javaswingdev.GoogleMaterialDesignIcon.CLOSE);
        iconClose.setSize(18);
		
		GroupLayout gl_chat_box = new GroupLayout(chat_box);
		gl_chat_box.setHorizontalGroup(
			gl_chat_box.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_chat_box.createSequentialGroup()
					.addContainerGap()
					.addComponent(textFieldChat_box, GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblSend)
					.addContainerGap())
		);
		gl_chat_box.setVerticalGroup(
			gl_chat_box.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_chat_box.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_chat_box.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldChat_box, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addComponent(lblSend, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		chat_box.setLayout(gl_chat_box);
	}
	
	public void displayMsg(String msg, String sender) {
		String past_msg=txtAreaChat.getText();
		String separator="---------------------------------------------";
		txtAreaChat.setText(past_msg+'\n'+separator+'\n'+sender+": "+msg+'\n');
		
		DefaultCaret caret = (DefaultCaret)txtAreaChat.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
//		scrollPane.getVerticalScrollBar().validate();
//		scrollPane.getVerticalScrollBar().setValue( scrollPane.getVerticalScrollBar().getMaximum() );
	}

	/**
	 * @return the textFieldChat_box
	 */
	public JTextField getTextFieldChat_box() {
		return textFieldChat_box;
	}

	/**
	 * @param textFieldChat_box the textFieldChat_box to set
	 */
	public void setTextFieldChat_box(JTextField textFieldChat_box) {
		this.textFieldChat_box = textFieldChat_box;
	}

	/**
	 * @return the lblSend
	 */
	public JLabel getLblSend() {
		return lblSend;
	}

	/**
	 * @return the lblCloseIcon
	 */
	public JLabel getLblCloseIcon() {
		return lblCloseIcon;
	}

	/**
	 * @param lblSend the lblSend to set
	 */
	public void setLblSend(JLabel lblSend) {
		this.lblSend = lblSend;
	}

	/**
	 * @param lblCloseIcon the lblCloseIcon to set
	 */
	public void setLblCloseIcon(JLabel lblCloseIcon) {
		this.lblCloseIcon = lblCloseIcon;
	}

	
	
	
}