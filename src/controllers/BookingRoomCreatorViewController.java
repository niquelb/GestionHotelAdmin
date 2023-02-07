package controllers;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.BookingModel;
import models.BookingRoomModel;
import models.RoomModel;
import utils.ButtonEditor;
import utils.ButtonRenderer;
import views.BookingRoomCreatorView;

public class BookingRoomCreatorViewController implements ActionListener, MouseListener {
	private final BookingRoomCreatorView view;
	private final BookingModel booking;
	private int page_num=0;
	private JTable table;
	private ArrayList<RoomModel> al;
	private ArrayList<RoomModel> al2=new ArrayList<>();

	public BookingRoomCreatorViewController(BookingRoomCreatorView view, int booking_id) {
		super();
		this.view = view;
		this.table=view.getTable();
		this.booking=BookingModel.getBooking(booking_id);
		
		buildTable();
	}
	
	public void buildTable() {
		JScrollPane pane=view.getScrollPane();
		
		al=RoomModel.getRoomList(null, 0, 0, page_num);
		
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
		table.addMouseListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().getClass()==JLabel.class) {			
			view.dispose();
		}
		
		if (e.getSource().getClass()==JTable.class) {
			System.out.println(al.get(view.getRow()).getName());
			RoomModel selected_room=RoomModel.getRoom(al.get(view.getRow()).getId());
			
			
			String[] arr= {"Confirmar","Cancelar"};
			int n = JOptionPane.showOptionDialog(view,
				    "Desea confirmar la seleccion de la habitacion "+selected_room.getName()+'?',
				    "Confirmar Seleccion?",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.PLAIN_MESSAGE,
				    null,
				    arr,
				    arr[0]
				    );
			
			if (n==0) {
				al2.add(selected_room);
				
				System.out.println(al2);
				
				String[] arr2= {"Si","No"};
				int m = JOptionPane.showOptionDialog(view,
					    "Desea añadir mas habitaciones?",
					    "Confirmar Seleccion?",
					    JOptionPane.YES_NO_OPTION,
					    JOptionPane.PLAIN_MESSAGE,
					    null,
					    arr2,
					    arr2[0]
					    );
				if (m!=0) {
					int o = JOptionPane.showOptionDialog(view,
						    "Desea confirmar la creacion de la reserva del usuario "+booking.getUser_id()+'?',
						    "Confirmar Seleccion?",
						    JOptionPane.YES_NO_OPTION,
						    JOptionPane.PLAIN_MESSAGE,
						    null,
						    arr,
						    arr[0]
						    );
					if (o==0) {
						for (RoomModel rm : al2) {
							BookingRoomModel brm=new BookingRoomModel(rm.getId(), booking.getId(), al.size()-1, rm.getPrice());
							
							BookingRoomModel.createBooking(brm);
						}
						
					}
					
					view.dispose();
				}
			}
			
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
