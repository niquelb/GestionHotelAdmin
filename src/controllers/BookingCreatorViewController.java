package controllers;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import models.BookingModel;
import utils.UserDataChecker;
import views.BookingCreatorView;

public class BookingCreatorViewController implements ActionListener, MouseListener{
	
	private final BookingCreatorView view;

	public BookingCreatorViewController(BookingCreatorView view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "submit":
			String user_id=(String.valueOf(view.getTextFieldUser_id().getText()).equals(""))?null:String.valueOf(view.getTextFieldUser_id().getText());
			Date start_date;
			try {
				// LocalDate is better than Date, but jdbc uses Date
				// ld gets the date from the text field, if it isn't valid it throws "DateTimeException" (handled)
				// then ld gets converted to Date format => "(Date) Date.from(LOCALDATEOBJECT.atStartOfDay(ZoneId.systemDefault()).toInstant())"
				LocalDate ld=LocalDate.parse(view.getTextFieldStart_date().getText());
				start_date=new Date(Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
			} catch (DateTimeException e2) {
				start_date=null;
			}
			
			Date end_date;
			try {
				// See ^
				LocalDate ld=LocalDate.parse(view.getTextFieldEnd_date().getText());
				end_date=new Date(Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
			} catch (DateTimeException e2) {
				end_date=null;
			}
			
			int num_adults=1;
			try {
				num_adults=
						(Integer.parseInt(String.valueOf(view.getTextFieldNum_adults().getText()))<=0)
						?1
						:Integer.parseInt(String.valueOf(view.getTextFieldNum_adults().getText()));
			} catch (Exception e2) {}
			int num_children=0;
			try {
				num_children=
						(Integer.parseInt(String.valueOf(view.getTextFieldNum_children().getText()))<0)
						?0
						:Integer.parseInt(String.valueOf(view.getTextFieldNum_children().getText()));
			} catch (Exception e2) {}
			
			System.out.println("Fecha:"+start_date+'/'+end_date);
			
			if (user_id==null) {
				JOptionPane.showMessageDialog(view,
					    "El email del usuario no puede estar vacio.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			if (!UserDataChecker.validateEmail(user_id)) {
				JOptionPane.showMessageDialog(view,
					    "El email no es correcto, asegurate de que sigue el siguiente patron:"
					    + "\nXXXXXXXX@XXXX.XXX Ej. correo@gmail.com",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			if (start_date==null || end_date==null) {
				JOptionPane.showMessageDialog(view,
					    "El formato de fechas es incorrecto, por favor, sigue el siguiente formato:\n"
					    + "AÑO-MES-DIA En valores numericos Ej. 2023-12-31",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			if (!end_date.after(start_date)) {
				JOptionPane.showMessageDialog(view,
					    "La fecha de salida debe ser mayor que la fecha de entrada.",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
				break;
			}
			
			try {
				BookingModel.createBooking(new BookingModel(num_adults, num_children, start_date, end_date, user_id));
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
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
