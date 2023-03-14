package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import views.BookingRoomListView;

public class BookingListViewController implements ActionListener, MouseListener {

	private final BookingListView view;
	
	private int page_num=0;
	private String logged_in_user, user_id;
	private Date start_date, end_date;
	
	private ArrayList<BookingModel> al;

	private int total_pages=(int) Math.ceil(BookingModel.getTotalRows()/10);
	
	public BookingListViewController(BookingListView view, String user_id) {
		super();
		this.view = view;
		this.logged_in_user=user_id;
		
		try {
			buildTable(null, null, null);
		} catch (Exception e) {}
	}
	
	public void buildTable(String user_id, Date start_date, Date end_date) throws Exception {
		JScrollPane pane=view.getPane();
		JTable table=view.getTable();
		
		al=new ArrayList<>();
		
		Map<String, Object> params=new HashMap<String, Object>();
		
		if (user_id!=null && !user_id.equals("")) params.put("user_id", user_id);
		if (start_date!=null) params.put("fecha_entrada", start_date);
		if (end_date!=null) params.put("fecha_salida", end_date);
		
		al=BookingModel.getBookingList(params, page_num);
		
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
				
		pane.setViewportView(table);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "create_booking":
			new BookingCreatorView(logged_in_user);
			
			break;
		case "submit":
			user_id=(view.getTextFieldUser_id().getText().equals("")?null:view.getTextFieldUser_id().getText());
			try {
				start_date=StringToDateConverter.stringToDate(view.getTextFieldStart_date().getText());
			} catch (DateTimeException e2) {
				start_date=null;
			}
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
			
			logged_in_user=null;
			start_date=null;
			end_date=null;
			
			break;
			
		case "clear":
			view.getBtnClear().setEnabled(false);
			logged_in_user=null;
			start_date=null;
			end_date=null;
			
			view.getTextFieldUser_id().setText("");
			view.getTextFieldStart_date().setText("");
			view.getTextFieldEnd_date().setText("");
			
			try {
				buildTable(null, null, null);
			} catch (Exception e1) {}
			
			break;
		
		/**
		 * Page Navigation
		 */
		case "prev_page":
			page_num=!(page_num==0)?--page_num:0;
			updatePageTextField();
			try {
				buildTable(user_id, start_date, end_date);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		case "next_page":
			page_num=!(page_num==total_pages)?++page_num:total_pages;
			updatePageTextField();
			try {
				buildTable(user_id, start_date, end_date);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		case "first_page":
			page_num=0;
			updatePageTextField();
			try {
				buildTable(user_id, start_date, end_date);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		case "last_page":
			page_num=total_pages;
			updatePageTextField();
			try {
				buildTable(user_id, start_date, end_date);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
			
			break;
		default:
			System.err.println("Unexpected Value");
		}
		
	}
	

	public void updatePageTextField() {
		view.getTextFieldCurrentPage().setText(String.valueOf(page_num+1));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().getClass()==JTable.class) {
			new BookingRoomListView(al.get(view.getRow()).getId());
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
