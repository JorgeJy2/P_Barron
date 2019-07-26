package gui.content.car;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException; 

import javax.swing.JButton;
import javax.swing.JComboBox; 
import javax.swing.JLabel; 
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import controller.ControllerCar;
import gui.dialogs.Messages;
import gui.resource.ResourcesGui;
import model.dto.DtoCar;
import model.list.ListCar;
import model.list.interador.Interator;
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
	
	
	private ListCar listCar;
	
	// GUI
	private JTable table;
	
	
	private JPanel pTitle;
	private JPanel pfilter;
	
	private JLabel lbTitle;
	private JLabel lbFilter;
	
	private JComboBox<String> cbxFilter;
	
	private JTextField txtFilter;

	private JButton btnFilter;
	
	///private ControllerCar controller;
	public CarGuiView() {
		listCar = ListCar.getInstance();
        try {
			listCar.loadList();
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}
		createGui();
		btnFilter.addActionListener(ControllerCar.getInstance());
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
 
		btnFilter.addActionListener(ControllerCar.getInstance());
		
		pTitle.add(pfilter);
		// ================== FILTER end ==================
		
		this.add(pTitle, BorderLayout.PAGE_START);
		// Data to be displayed in the JTable 
        
        String[][] data= new String[listCar.sizeDtos()][5]; 
        Interator<DtoCar> inte =  listCar.getAll();
        
		while(inte.hasNext()) {
			int pointerCar = inte.now();
			DtoCar car =inte.next();
			data[pointerCar][0] = car.getModelo();
        	data[pointerCar][1] = car.getPlaca();
        	data[pointerCar][2] = car.getColor();
		}
   
        table = new JTable(data, COLUMN_NAMES); 
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setBackground(ResourcesGui.COLOR.getSecondColor());
        table.setSelectionBackground(ResourcesGui.COLOR.getPrimaryColor()); 
        table.setFont(ResourcesGui.FONT.getFontText() );

        JTableHeader header = table.getTableHeader();
        header.setBackground(ResourcesGui.COLOR.getPrimaryColor());
        header.setForeground(ResourcesGui.COLOR.getSecondColor());
        header.setFont(ResourcesGui.FONT.getFontText());
        
        JScrollPane sp = new JScrollPane(table); 
        this.add(sp, BorderLayout.CENTER); 
	}

	
	public ListCar getListCar() {
		return listCar;
	}

	public void setListCar(ListCar listCar) {
		this.listCar = listCar;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JComboBox<String> getCbxFilter() {
		return cbxFilter;
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
		// TODO Auto-generated method stub
		
	}
}