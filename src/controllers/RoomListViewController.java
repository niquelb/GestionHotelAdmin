package controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.RoomModel;
import utils.ButtonEditor;
import utils.ButtonRenderer;
import views.RoomCreatorView;
import views.RoomEditorView;
import views.RoomListView;

public class RoomListViewController implements ActionListener {
	
	private final RoomListView view;
	private String name;
	private int num_guests;
	private double price;
	private int page_num=0;
	private int total_pages=(int) Math.ceil(RoomModel.getTotalRows()/10);
	
	public RoomListViewController(RoomListView view) {
		super();
		this.view = view;
		
		buildTable(null, 0, 0);
		}

	/**
	 * Method for building the JTable
	 * @param name Room name for filtering
	 * @param price Room price for filtering
	 * @param num_guests Room num_guests for filtering
	 */
	public void buildTable(String name, double price, int num_guests) {
		JTable table=view.getTable();
		JScrollPane pane=view.getPane();
		
		Map<String, Object> params=new HashMap<String, Object>();

		if (name!=null && !name.equals("")) params.put("nombre", name);
		if (!(price<=0)) params.put("precio", price);
		if (!(num_guests<=0)) params.put("numero_maximo_personas", num_guests);
		
		ArrayList<RoomModel> al=RoomModel.getRoomList(params, page_num);
		
		DefaultTableModel model=new DefaultTableModel();
		table.setModel(model);
		
		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Descripcion");
		model.addColumn("Cantidad");
		model.addColumn("Precio");
		model.addColumn("Max. Personas");
		model.addColumn("Num. Camas");
		model.addColumn("Modificar");
		
		table.getTableHeader().setReorderingAllowed(false);
		
		for (RoomModel roomModel : al) {
			Object[] row=new Object[8];
			row[0]=roomModel.getId();
			row[1]=roomModel.getName();
			row[2]=roomModel.getDescription();
			row[3]=roomModel.getQuantity();
			row[4]=roomModel.getPrice();
			row[5]=roomModel.getMax_guests();
			row[6]=roomModel.getNum_beds();
			row[7]="Editar";
			model.addRow(row);
			
		}
		
		table.getColumn("Modificar").setCellRenderer(new ButtonRenderer());
		table.getColumn("Modificar").setCellEditor(new ButtonEditor(new JCheckBox(), table, null, al));
				
		pane.setViewportView(table);
		
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "submit":
			name=String.valueOf(view.getTextFieldName().getText());
			try {
				num_guests=Integer.parseInt(
						String.valueOf(view.getTextFieldNum_guests().getText())
						);
			} catch (NumberFormatException e2) {
				num_guests=0;
			}
			
			try {
				price=Double.parseDouble(
						String.valueOf(view.getTextFieldPrice().getText().equals("")?0:String.valueOf(view.getTextFieldPrice().getText()))
						);
			} catch (NumberFormatException e2) {
				price=0;
			}
			view.getBtnClear().setEnabled(true);
			buildTable(name, price, num_guests);
			
			break;
		case "clear":
			name=null;
			num_guests=0;
			price=0;
			
			view.getTextFieldName().setText("");
			view.getTextFieldNum_guests().setText("");
			view.getTextFieldPrice().setText("");
			
			view.getBtnClear().setEnabled(false);
			buildTable(null, 0, 0);
			
			break;
		case "create_room":
			new RoomCreatorView();
			break;
		
		/**
		 * Page Navigation
		 */
		case "prev_page":
			page_num=!(page_num==0)?--page_num:0;
			updatePageTextField();
			buildTable(name, price, num_guests);
			
			break;
		case "next_page":
			page_num=!(page_num==total_pages)?++page_num:total_pages;
			updatePageTextField();
			buildTable(name, price, num_guests);
			
			break;
		case "first_page":
			page_num=0;
			updatePageTextField();
			buildTable(name, price, num_guests);
			
			break;
		case "last_page":
			page_num=total_pages;
			updatePageTextField();
			buildTable(name, price, num_guests);
				
				break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + e.getActionCommand());
		}
	}
	
	public void updatePageTextField() {
		view.getTextFieldCurrentPage().setText(String.valueOf(page_num+1));
	}
	
	public static void updateRoom(String r_id) {
		new RoomEditorView(r_id);
	}

}
