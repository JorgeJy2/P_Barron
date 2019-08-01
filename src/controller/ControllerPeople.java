package controller;

import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.SQLException;
import java.util.concurrent.Executor;

import javax.swing.JScrollBar;

import gui.content.people.PeopleGuiView;
import gui.dialogs.Messages;
import model.dto.DtoPeople;
import model.list.ListPeople;
import model.list.interador.Interator;

public class ControllerPeople extends ControllerWindow{

	private PeopleGuiView view;
	
	private ListPeople listPeople;
	
	private ScrollableTable scrollableTable;
	
	
	public ControllerPeople(PeopleGuiView view) {
		this.view = view;
		listPeople = ListPeople.getInstance();
		scrollableTable = new ScrollableTable(this);
		
		addScrollTable();
		reloadData();
	}

	
	@Override
	public boolean saveRegistry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean filter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRegistry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRegistry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getDataOfView() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setDataOfView() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reloadData() {
		try {
        	listPeople.loadList();
        	String[][] data= new String[listPeople.sizeDtos()][5]; 
            Interator<DtoPeople> interator =  listPeople.getAll(); 
    		while(interator.hasNext()) {
    			int countPeoples = interator.now();
    			DtoPeople people= interator.next();
            	data[countPeoples][0] = people.getName();
            	data[countPeoples][1] = people.getLastName();
            	data[countPeoples][2] = people.getFirstName();
            	data[countPeoples][3] = people.getEmail();
            	data[countPeoples][4] = people.getTelephone();
    		} 
        	view.setModelTable(data);
        	//view.getTable().addKeyListener(this);
        	//view.getTable().addMouseListener(mauseClickedOnTable);
        	
        	return true;
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError("  "+e.getMessage());
			return false;
		}  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addListener() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void addScrollTable() {
		view.getScrollPaneTable().getVerticalScrollBar().addAdjustmentListener(scrollableTable);	
	}
	
	public void loadNextCars () {
		Executor executor = new Executor() {
			@Override
			public void execute(Runnable arg0) {
				arg0.run();
			}
		};

		executor.execute(() -> {
			try {
				if(listPeople.reloadNext()) 
					reloadData();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showError(" "+e.getLocalizedMessage());
			}
		});
	}
 
	
	private class ScrollableTable implements AdjustmentListener {
		
		private ControllerPeople controller;
		
		public ScrollableTable(ControllerPeople controller) {
			this.controller = controller;
		}
		
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			  if(!e.getValueIsAdjusting()){    
	                JScrollBar source = (JScrollBar) e.getAdjustable();
	                int extent = source.getModel().getExtent();
	                int maximum = source.getModel().getMaximum();
	                if(e.getValue() + extent == maximum){
	                	System.out.println("Final");
	                	controller.loadNextCars();
	                }
	            }
		}
		
	}

}
