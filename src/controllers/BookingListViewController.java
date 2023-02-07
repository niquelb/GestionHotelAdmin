package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.BookingModel;
import views.BookingCreatorView;
import views.BookingListView;

public class BookingListViewController implements ActionListener{

	private final BookingListView view;
	

	private int total_pages=(int) Math.ceil(BookingModel.getTotalRows()/10)-1;
	
	public BookingListViewController(BookingListView view) {
		super();
		this.view = view;
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
