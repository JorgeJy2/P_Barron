package gui.content.people;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import gui.dialogs.Messages;
import gui.resource.ResourcesGui;

import model.dto.DtoPeople;
import model.list.ListPeople;
import model.list.interador.Interator;
import observer.IObserver;

public class PeopleGuiView  extends  JPanel implements IObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = { "Nombre", "Apellido P", "Apellido M", "Correo", "Teléfono" }; 
	
	private static final String TITLE = "Personas registradas";
	private static final String FILTER = "Filtrar personas";
	private static final String BTN_FILTER  = "Filtrar"; 
	
	
	private ListPeople listPeople;
	
	// GUI
	private JTable table;
	
	
	private JPanel pTitle;
	private JPanel pfilter;
	
	private JLabel lbTitle;
	private JLabel lbFilter;
	
	private JComboBox<String> cbxFilter;
	
	private JTextField txtFilter;

	private JButton btnFilter;
	
	
	public PeopleGuiView() {
		listPeople = ListPeople.getInstance();
        try {
			listPeople.loadList();
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}
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
		// Data to be displayed in the JTable 

		Interator<DtoPeople> interator = listPeople.getAll();
        String[][] data= new String[listPeople.sizeDtos()][5]; 
 
        while (interator.hasNext()) {
        	int countPeoples = interator.now();
			DtoPeople people=interator.next();
        	data[countPeoples][0] = people.getName();
        	data[countPeoples][1] = people.getLastName();
        	data[countPeoples][2] = people.getFirstName();
        	data[countPeoples][3] = people.getEmail();
        	data[countPeoples][4] = people.getTelephone();
        }
        
        // Initializing the JTable 
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}