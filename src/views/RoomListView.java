package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;

import controllers.RoomListViewController;
import utils.ComponentInit;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class RoomListView extends JPanel {
	
	private final RoomListViewController controller;
	
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JTextField textFieldNum_guests;

	/**
	 * Create the panel.
	 */
	public RoomListView() {
		setLayout(new BorderLayout(10, 5));
		
		JPanel filters_panel = new JPanel();
		filters_panel.setPreferredSize(new Dimension(this.getWidth(), 175));
		filters_panel.setBackground(new Color(45, 45, 45));
		add(filters_panel, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Refinar Busqueda");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
		
		JLabel lblName = new JLabel("Nombre de la Habitacion");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Precio Maximo");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		
		JLabel lblNum_guests = new JLabel("Capacidad Minima (Num. Personas)");
		lblNum_guests.setForeground(Color.WHITE);
		lblNum_guests.setFont(new Font("Roboto Light", Font.PLAIN, 18));
		
		textFieldNum_guests = new JTextField();
		textFieldNum_guests.setColumns(10);
		
		JButton btnSubmit = new JButton("Filtrar");
		
		JButton btnClear = new JButton("Limpiar");
		btnClear.setEnabled(false);
		
		GroupLayout gl_filters_panel = new GroupLayout(filters_panel);
		gl_filters_panel.setHorizontalGroup(
			gl_filters_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filters_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addGroup(gl_filters_panel.createSequentialGroup()
									.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPrice)
										.addGroup(gl_filters_panel.createSequentialGroup()
											.addGap(10)
											.addComponent(textFieldPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(18)
									.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_filters_panel.createSequentialGroup()
											.addGap(10)
											.addComponent(textFieldNum_guests, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblNum_guests)))
								.addGroup(gl_filters_panel.createSequentialGroup()
									.addGap(10)
									.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblTitle))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSubmit, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(btnClear, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_filters_panel.setVerticalGroup(
			gl_filters_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filters_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSubmit, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(lblTitle))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_filters_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_filters_panel.createSequentialGroup()
							.addComponent(lblNum_guests, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldNum_guests, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		filters_panel.setLayout(gl_filters_panel);
		
		JPanel list_panel = new JPanel();
		add(list_panel, BorderLayout.CENTER);
		list_panel.setLayout(new BorderLayout(0, 5));
		
		JButton btnCreate_room = new JButton("Crear Habitacion");
		list_panel.add(btnCreate_room, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		list_panel.add(scrollPane, BorderLayout.CENTER);

		controller=new RoomListViewController(this);
		ComponentInit.setButtonProperties(btnCreate_room, controller);
		btnCreate_room.setActionCommand("create_room");
		ComponentInit.setButtonProperties(btnSubmit, controller);
		btnSubmit.setActionCommand("submit");
		ComponentInit.setButtonProperties(btnClear, controller);
		btnClear.setActionCommand("clear");
	}

}
