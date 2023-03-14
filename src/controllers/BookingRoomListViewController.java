package controllers;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.BookingModel;
import models.BookingRoomModel;
import models.RoomModel;
import views.BookingRoomListView;

public class BookingRoomListViewController implements MouseListener {
	
	private final BookingRoomListView view;
	private final BookingModel booking;
	private int page_num=0, booking_id;
	private JTable table;
	private ArrayList<RoomModel> al;


	public BookingRoomListViewController(BookingRoomListView view, int booking_id) {
		super();
		this.view = view;
		this.table=view.getTable();
		this.booking_id=booking_id;
		
		Map<String, Object> params=new HashMap<String, Object>();
		
		params.put("id", this.booking_id);
		
		this.booking=BookingModel.getBooking(params);
		
		view.getLblTitle().setText("Habitaciones Relacionadas con la Reserva "+booking_id);

		buildTable(booking_id);
	}
	
	public void buildTable(int booking_id) {
		JScrollPane pane=view.getScrollPane();

//		Map<String, Object> params=new HashMap<String, Object>();
//		
//		params.put("id", this.booking_id);
//		
//		al=RoomModel.getRoomList(params, page_num);
		
		al=BookingRoomModel.getRoomsByBooking(booking_id);
		
		DefaultTableModel model=new DefaultTableModel();
		table.setModel(model);
		
		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Descripcion");
		model.addColumn("Cantidad");
		model.addColumn("Precio");
		model.addColumn("Max. Personas");
		model.addColumn("Num. Camas");
		
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); <- this is the devil
		table.getTableHeader().setReorderingAllowed(false);
		
		for (RoomModel roomModel : al) {
			Object[] row=new Object[7];
			row[0]=roomModel.getId();
			row[1]=roomModel.getName();
			row[2]=roomModel.getDescription();
			row[3]=roomModel.getQuantity();
			row[4]=roomModel.getPrice();
			row[5]=roomModel.getMax_guests();
			row[6]=roomModel.getNum_beds();
			model.addRow(row);
			
		}
				
		pane.setViewportView(table);
		
		
		table.setPreferredScrollableViewportSize(table.getPreferredSize());

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
