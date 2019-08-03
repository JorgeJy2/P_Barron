package gui.content.ticket;

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


	public class TicketGuiView extends JPanel implements IObserver {
		
		private static final long serialVersionUID = 1L;
		private static final String[] COLUMN_NAMES = { "Auto", "Persona", "F_Entrada", "F_Salida","Total", "Estatus"}; 
		
		private static final String TITLE = "Tickets registrados";
		private static final String FILTER = "Filtrar Ticktes";
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
		
		
		public TicketGuiView() {
			
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
	        
			tableModel=new DefaultTableModel(COLUMN_NAMES,0);
			
			table =new JTable(tableModel);
		
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

		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}

		public JTable getTable() {
			return table;
		}

		public DefaultTableModel getTableModel() {
			return tableModel;
		}

		public JScrollPane getScrollPaneTable() {
			return scrollPaneTable;
		}
		
		public void setModelTable(String[][] data) {
			DefaultTableModel modelo = new DefaultTableModel(data,COLUMN_NAMES);
			table.setModel(modelo);
			table.setRowHeight(30);
		}

		public String getCbxFilter() {
			String[] nomBDField = { "Auto", "Persona", "fecha_entrada","fecha_salida","total_pago", "estatus"}; 
			return nomBDField[cbxFilter.getSelectedIndex()];
		}

		public JButton getBtnFilter() {
			return btnFilter;
		}
		
		public JTextField getTxtFilter() {
			return txtFilter;
		}
		
	
	}