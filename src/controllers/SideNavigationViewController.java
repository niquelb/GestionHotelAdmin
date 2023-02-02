package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.SideNavigationView;

public class SideNavigationViewController implements ActionListener{
	
	private SideNavigationView view;

	public SideNavigationViewController(SideNavigationView view) {
		super();
		this.view = view;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			//TODO finish this switch
			
		}
		
	}

}
