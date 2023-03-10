package controllers;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import models.ChatClientModel;
import utils.FAQ;
import views.ChatView;

public class ChatViewController implements MouseListener{

	private final ChatView view;
	
	private boolean isActive=true;
	
	private ChatClientModel model;
	
	private String lang;

	public ChatViewController(ChatView view) {
		super();
		this.view = view;
		
		do {
			String[] arr= {"Español","English"};
			int n = JOptionPane.showOptionDialog(view,
				    "Por favor, seleccione un idioma.\nPlease select a language.",
				    "Seleccionar lenguaje",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.PLAIN_MESSAGE,
				    null,
				    arr,
				    arr[0]
				    );
			if (n==0) {
				lang=ChatClientModel.ES;
				break;
			} else if (n==1) {
				lang=ChatClientModel.EN;
				break;
			}
			
		} while (true);
		
		model=new ChatClientModel();

		
		initChat();
	}
	
	public void initChat() {
		model.execConnection(this, lang);
		
	}
	
	public void getMsg(String msg) {
		view.displayMsg(msg, "Server");
	}
	
	public void endConv() {
		isActive=false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(view.getLblSend())) {			
			try {
				if (!view.getTextFieldChat_box().getText().equals("") && isActive) {
					model.writeData(view.getTextFieldChat_box().getText());
					view.displayMsg(view.getTextFieldChat_box().getText(), "Client");
					view.getTextFieldChat_box().setText("");
				}
			} catch (IOException e1) {
				System.err.println("Error while sending message. -> "+e1.getMessage());
			}
		} else if (e.getSource().equals(view.getLblCloseIcon())) {
			view.setVisible(false);
		}
			
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		view.setCursor(Cursor.HAND_CURSOR);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		view.setCursor(Cursor.DEFAULT_CURSOR);
	}
	
	
}
