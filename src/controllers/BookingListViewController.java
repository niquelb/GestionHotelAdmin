package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.DateTimeException;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.BookingModel;
import models.RoomModel;
import utils.ButtonEditor;
import utils.ButtonRenderer;
import utils.StringToDateConverter;
import views.BookingCreatorView;
import views.BookingListView;

public class BookingListViewController implements ActionListener{

	private final BookingListView view;
	
	private int page_num=0;
	private String user_id;

	private int total_pages=(int) Math.ceil(BookingModel.getTotalRows()/10)-1;
	
	public BookingListViewController(BookingListView view, String user_id) {
		super();
		this.view = view;
		this.user_id=user_id;
		
		try {
			buildTable(null, null, null);
		} catch (Exception e) {}
	}
	
	public void buildTable(String user_id, Date start_date, Date end_date) throws Exception {
		JScrollPane pane=view.getPane();
		JTable table=view.getTable();
		
		ArrayList<BookingModel> al=new ArrayList<>();
		
		al=BookingModel.getBookingList(user_id, start_date, end_date, page_num);
		
		DefaultTableModel model=new DefaultTableModel();
		table.setModel(model);
		
		model.addColumn("ID");
		model.addColumn("Fecha");
		model.addColumn("Fecha Entrada");
		model.addColumn("Fecha Salida");
		model.addColumn("Numero Adultos");
		model.addColumn("Numero Niños");
		model.addColumn("Usuario");
		
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); <- this is the devil
		table.getTableHeader().setReorderingAllowed(false);
		
		for (BookingModel bookingModel : al) {
			Object[] row=new Object[7];
			row[0]=bookingModel.getId();
			row[1]=bookingModel.getDate();
			row[2]=bookingModel.getDate_start();
			row[3]=bookingModel.getDate_end();
			row[4]=bookingModel.getNum_adults();
			row[5]=bookingModel.getNum_children();
			row[6]=bookingModel.getUser_id();
			model.addRow(row);
			
		}
		
//		table.getColumn("Modificar").setCellRenderer(new ButtonRenderer());
//		table.getColumn("Modificar").setCellEditor(new ButtonEditor(new JCheckBox(), table, null, al));
				
		pane.setViewportView(table);
		
		// The width is -18 to compensate for the vertical scrollbar
//		table.setPreferredSize(pane.getMinimumSize());
		//TODO fix table size
		
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "create_booking":
			new BookingCreatorView(user_id);
			
			break;
		case "submit":
			String user_id=(view.getTextFieldUser_id().getText().equals("")?null:view.getTextFieldUser_id().getText());
			Date start_date;
			try {
				start_date=StringToDateConverter.stringToDate(view.getTextFieldStart_date().getText());
			} catch (DateTimeException e2) {
				start_date=null;
			}
			Date end_date;
			try {
				end_date=StringToDateConverter.stringToDate(view.getTextFieldEnd_date().getText());
			} catch (DateTimeException e2) {
				end_date=null;
			}
			
			try {
				buildTable(user_id, start_date, end_date);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(view,
					    "La fecha de salida debe ser posterior a la fecha de entrada",
					    "Error",
					    JOptionPane.PLAIN_MESSAGE);
			}
			
			view.getBtnClear().setEnabled(true);
			break;
			
		case "clear":
			view.getBtnClear().setEnabled(false);
			user_id=null;
			start_date=null;
			end_date=null;
			
			view.getTextFieldUser_id().setText("");
			view.getTextFieldStart_date().setText("");
			view.getTextFieldEnd_date().setText("");
			
			try {
				buildTable(null, null, null);
			} catch (Exception e1) {}
			
			break;
		default:
			System.err.println("Unexpected Value");
		}
		
	}

}
