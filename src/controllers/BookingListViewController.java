package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.BookingModel;
import models.RoomModel;
import utils.ButtonEditor;
import utils.ButtonRenderer;
import views.BookingCreatorView;
import views.BookingListView;

public class BookingListViewController implements ActionListener{

	private final BookingListView view;
	
	private int page_num=0;
	

	private int total_pages=(int) Math.ceil(BookingModel.getTotalRows()/10)-1;
	
	public BookingListViewController(BookingListView view) {
		super();
		this.view = view;
		
		buildTable();
	}
	
	public void buildTable() {
		JScrollPane pane=view.getPane();
		JTable table=view.getTable();
		
		ArrayList<BookingModel> al=new ArrayList<>();
		
		try {
			al=BookingModel.getBookingList(null, null, null, page_num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			new BookingCreatorView();
			
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
		
	}

}
