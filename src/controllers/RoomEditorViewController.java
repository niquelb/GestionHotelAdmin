package controllers;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import models.RoomModel;
import views.RoomEditorView;

public class RoomEditorViewController implements ActionListener, MouseListener {

	private final RoomEditorView view;
	private final RoomModel room;

	public RoomEditorViewController(RoomEditorView view, String r_id) {
		super();
		this.view = view;
		room=RoomModel.getRoom(r_id);
		if (room!=null) {
			updateFields();
		} else {
			System.err.println("Room is null.");
			view.dispose();
		}
	}
	
	public void updateFields() {
		view.getTextFieldName().setText(room.getName());
		view.getTextAreaDescription().setText(room.getDescription()!=null?room.getDescription():"");
		view.getTextFieldQuantity().setText(String.valueOf(room.getQuantity()));
		view.getTextFieldPrice().setText(String.valueOf(room.getPrice()));
		view.getTextFieldMax_guests().setText(String.valueOf(room.getMax_guests()));
		view.getTextFieldNum_beds().setText(String.valueOf(room.getNum_beds()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "submit":
			String name=(String.valueOf(view.getTextFieldName().getText()).equals(""))?null:String.valueOf(view.getTextFieldName().getText());
			String description=String.valueOf(view.getTextAreaDescription().getText());
			int quantity=1;
			try {
				quantity=
						(Integer.parseInt(String.valueOf(view.getTextFieldQuantity().getText()))<=0)
						?1
						:Integer.parseInt(String.valueOf(view.getTextFieldQuantity().getText()));
			} catch (NumberFormatException e2) {}
			double price=0;
			try {
				price=
						(Double.parseDouble(String.valueOf(view.getTextFieldPrice().getText()))<=0)
						?0
						:Double.parseDouble(String.valueOf(view.getTextFieldPrice().getText()));
			} catch (NumberFormatException e2) {}
			int max_guests=1;
			try {
				max_guests=
						(Integer.parseInt(String.valueOf(view.getTextFieldMax_guests().getText()))<=0)
						?1
						:Integer.parseInt(String.valueOf(view.getTextFieldMax_guests().getText()));
			} catch (NumberFormatException e2) {}
			int num_beds=1;
			try {
				num_beds=
						(Integer.parseInt(String.valueOf(view.getTextFieldNum_beds().getText()))<=0)
						?1
						:Integer.parseInt(String.valueOf(view.getTextFieldNum_beds().getText()));
			} catch (NumberFormatException e2) {}
			
			if (name==null) {
				JOptionPane.showMessageDialog(view,
					    "El nombre no puede estar vacio.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			String[] arr= {"Confirmar","Cancelar"};
			int n = JOptionPane.showOptionDialog(view,
				    "Desea confirmar la edicion del usuario "+room.getName()+'?',
				    "Confirmar Edicion?",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.PLAIN_MESSAGE,
				    null,
				    arr,
				    arr[0]
				    );
			
			if (n==0) {
				room.setName(name);
				room.setDescription(description);
				room.setMax_guests(max_guests);
				room.setNum_beds(num_beds);
				room.setPrice(price);
				room.setQuantity(quantity);
				
				RoomModel.updateRoom(room);
				view.dispose();
			} else {
				view.dispose();
			}
			
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().getClass()==JLabel.class) {			
			view.dispose();
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
