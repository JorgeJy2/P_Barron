package gui.content.car;

import java.awt.BorderLayout;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JComboBox; 
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import gui.resource.ResourcesGui;

import observer.IObserver;

public class CarGuiView extends JPanel implements IObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = { "Modelo", "Placa", "Color"}; 
	
	private static final String TITLE = "Automoviles registrados";
	private static final String FILTER = "Filtrar Automoviles";
	private static final String BTN_FILTER  = "Filtrar"; 
 
	// GUI
	private JTable table;

	private JPanel pTitle;
	private JPanel pfilter;
	
	private JLabel lbTitle;
	private JLabel lbFilter;
	
	private JComboBox<String> cbxFilter;
	
	private JTextField txtFilter;

	private JButton btnFilter;

	private DefaultTableModel tableModel;

	private JScrollPane scrollPaneTable;
	
	public CarGuiView() {		
		createGui(); 
	}
	
	private  void createGui() {

		this.setLayout(new BorderLayout(ResourcesGui.DIMENS.getDistanteComponent(),ResourcesGui.DIMENS.getDistanteComponent()));
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setBorder(ResourcesGui.BORDER.getBorderConteinerMain());
		
		pTitle = new JPanel();
		pTitle.setBackground(ResourcesGui.COLOR.getSecondColor());
		pTitle.setLayout(new GridLayout(0,1));
		lbTitle = new JLabel(TITLE);
		lbTitle.setFont(ResourcesGui.FONT.geFontTitle());
		pTitle.add(lbTitle);
		
		// ================== FILTER start ==================
		
		pfilter = new JPanel();
		pfilter.setLayout(new GridLayout(1,0,ResourcesGui.DIMENS.getDistanteComponent(),ResourcesGui.DIMENS.getDistanteComponent()));
		pfilter.setBackground(ResourcesGui.COLOR.getSecondColor());
		
		lbFilter  = new  JLabel(FILTER);
		pTitle.add(lbFilter);
		
		cbxFilter = new JComboBox<String>(COLUMN_NAMES);
		pfilter.add(cbxFilter);
		
		txtFilter = new JTextField();
		txtFilter.setFont(ResourcesGui.FONT.getFontText());
		txtFilter.setBorder(ResourcesGui.BORDER.getBorderTxt());
		
		pfilter.add(txtFilter);
		
		btnFilter = new JButton(BTN_FILTER);
		btnFilter.setBackground(ResourcesGui.COLOR.getPrimaryColor());
		btnFilter.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnFilter.setForeground(ResourcesGui.COLOR.getSecondColor());
		pfilter.add(btnFilter);
 
		
		pTitle.add(pfilter);
		// ================== FILTER end ==================
		
		this.add(pTitle, BorderLayout.PAGE_START);
		
		tableModel=new DefaultTableModel(COLUMN_NAMES,0);
		
		table =new JTable(tableModel);
		
		//showDataInTable();
		
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setBackground(ResourcesGui.COLOR.getSecondColor());
        table.setSelectionBackground(ResourcesGui.COLOR.getPrimaryColor()); 
        table.setFont(ResourcesGui.FONT.getFontText() );
        JTableHeader header = table.getTableHeader();
        header.setBackground(ResourcesGui.COLOR.getPrimaryColor());
        header.setForeground(ResourcesGui.COLOR.getSecondColor());
        header.setFont(ResourcesGui.FONT.getFontText());
        
        scrollPaneTable = new JScrollPane();
        scrollPaneTable.setViewportView(table);
        this.add(scrollPaneTable, BorderLayout.CENTER); 
	}  
	  
	
	public void setModelTable(String[][] data) {
		DefaultTableModel modelo = new DefaultTableModel(data,COLUMN_NAMES);
		table.setModel(modelo);
		table.setRowHeight(30);
	}


	public JTable getTable() {
		return table;
	}

	

	public String getCbxFilter() {
		return cbxFilter.getItemAt(cbxFilter.getSelectedIndex());
	}

	public void setCbxFilter(JComboBox<String> cbxFilter) {
		this.cbxFilter = cbxFilter;
	}

	public JTextField getTxtFilter() {
		return txtFilter;
	}

	public void setTxtFilter(JTextField txtFilter) {
		this.txtFilter = txtFilter;
	}

	public JButton getBtnFilter() {
		return btnFilter;
	}

	@Override
	public void update() {
	}
	
	public JScrollPane getScrollPaneTable() {
		return scrollPaneTable;
	}
	
	/*
	private void showNewDataInTable() {
		
		//int random = (int) (10 * Math.random());
		//String [] row = { "Modelo..."+random,"Placa...."+random,"Color..."+random };
		//DefaultTableModel model = (DefaultTableModel) table.getModel();
		//model.addRow(row);
		
		Thread t   = new Thread(() -> {

				try {
					if(listCar.reloadNext()) {
						
						reloadData();
						
					}else {
						System.out.println("No se pudo cargar..");
					}
					
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println("Ocurrio un error al recargar "+e.getLocalizedMessage());
				}
		});
		t.run();
		
		

		
		/*
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
		dm.fireTableDataChanged();
		
		Interator<DtoCar> inte =  listCar.getAll();
	    
		int random = (int) (10 * Math.random());
		
		while(inte.hasNext()) {
			DtoCar car =inte.next();
			String[] carRow = {car.getModelo() + random, car.getPlaca() + random, car.getColor() + random};
	        dm.addRow(carRow);
		}
		
		String [] row = { "Modelo..."+random,"Placa...."+random,"Color..."+random };
		dm.addRow(row);
		*/
		
		/*
		System.out.println( sp.getVerticalScrollBar().getMaximum());
		sp.getVerticalScrollBar().setValue( sp.getVerticalScrollBar().getMaximum()-100);
		*/
	/*
	}
	*/
	/*
	private void reloadData() {
		
		Interator<DtoCar> inte =  listCar.getAll();
		String[][] data= new String[listCar.sizeDtos()][3]; 
		
		while(inte.hasNext()) {
			int countPeoples = inte.now();
			DtoCar car =inte.next();
			data[countPeoples][0] = car.getModelo();
			data[countPeoples][1] = car.getPlaca();
			data[countPeoples][2] = car.getColor();
		}
		DefaultTableModel modelo = new DefaultTableModel(data,COLUMN_NAMES);
		table.setModel(modelo);
		table.setRowHeight(30);
	}
	*/
	/*
	private  void  showDataInTable() {
		
	    Interator<DtoCar> inte =  listCar.getAll();
	    
		while(inte.hasNext()) {
			DtoCar car =inte.next();
			String[] carRow = {car.getModelo(), car.getPlaca(), car.getColor()};
	        tableModel.addRow(carRow);
		}
	} */
}