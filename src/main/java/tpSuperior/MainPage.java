package tpSuperior;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import tpSuperior.Lagrange;
import tpSuperior.MetodoUtilizado;
import tpSuperior.NewtonGregory;
import tpSuperior.Punto;

public class MainPage {

	private JFrame frame;
	private JTable tblPuntos;
	private int filasMinimas=2;
	private DefaultTableModel modelPuntos;
	JLabel lblPasosPolinomio;
	private JComboBox<Object> cmbMetodo;
	private int auxCont=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 689, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		tblPuntos =new JTable(new DefaultTableModel(new Object[]{"Columnas X", "Columna Y"}, 0));
		tblPuntos.setCellSelectionEnabled(true);
		tblPuntos.setBounds(11, 96, 413, 208);
		frame.getContentPane().add(tblPuntos);
		modelPuntos = (DefaultTableModel) tblPuntos.getModel();
		JTableHeader puntosHeader=new JTableHeader();
		tblPuntos.setTableHeader(puntosHeader);
		modelPuntos.addRow(new Object[]{"", ""});
		modelPuntos.addRow(new Object[]{"", ""});
		tblPuntos.getModel().addTableModelListener(
				new TableModelListener() 
				{
					@Override
					public void tableChanged(TableModelEvent e) {
						chequearYAgregarCeldas();
						calcularDeSerPosible();
					}
				});

		for (int i =0; i<modelPuntos.getColumnCount();i++) {
			tblPuntos.setDefaultRenderer(tblPuntos.getColumnClass(i), new PuntosCellRenderer());
		}
		

		JLabel lblTitulo = new JLabel("El titulo");
		lblTitulo.setBounds(10, 28, 275, 14);
		frame.getContentPane().add(lblTitulo);

		cmbMetodo = new JComboBox<Object>();
		cmbMetodo.setBounds(392, 45, 246, 25);
		frame.getContentPane().add(cmbMetodo);
		cmbMetodo.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	auxCont++;
	        	if(auxCont%2==0) {
	        		calcularDeSerPosible();
	        	}
	        }
	    });
		cmbMetodo.addItem(new Progresivo());
		cmbMetodo.addItem(new Regresivo());
		cmbMetodo.addItem(new Lagrange());
		
		JLabel lblInformacionMetodo = new JLabel("Descripcion de metodo elegido:");
		lblInformacionMetodo.setBounds(450, 126, 188, 30);
		frame.getContentPane().add(lblInformacionMetodo);

		JButton btnAyuda = new JButton("?");
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clickeaste el boton!");
			}
		});
		
		btnAyuda.setBounds(434, 268, 89, 23);
		frame.getContentPane().add(btnAyuda);

		JButton btnFinalizar = new JButton("X");
		btnFinalizar.setBounds(533, 268, 89, 23);
		frame.getContentPane().add(btnFinalizar);

		JLabel lblMetodo = new JLabel("Metodo");
		lblMetodo.setBounds(399, 28, 46, 14);
		frame.getContentPane().add(lblMetodo);

		lblPasosPolinomio = new JLabel("Pasos y Polinomio:");
		lblPasosPolinomio.setBounds(32, 310, 300, 64);
		frame.getContentPane().add(lblPasosPolinomio);

		
	}

	protected void chequearYAgregarCeldas() {
		int filaModificada=tblPuntos.getSelectedRow();
		int columnaModificada=tblPuntos.getSelectedColumn();

		System.out.println("Ha modificado ("+filaModificada+";"+columnaModificada+")");
		String nuevoValor=(String)tblPuntos.getModel().getValueAt(tblPuntos.getSelectedRow(), tblPuntos.getSelectedColumn());

		if(tblPuntos.getSelectedRow()==(tblPuntos.getRowCount()-1)) {//Modifico ultima
			if(!celdaVacia(nuevoValor)) {
				//Editaste la ultima, agrego una fila
				modelPuntos.addRow(new Object[]{"", ""});
			}
		}

		// 2 Si borraste una, elimino si la otra esta en blanco, sino marco en rojo
		//No borro menos de 2
		String celdaDeAlLado=(String)modelPuntos.getValueAt(tblPuntos.getSelectedRow(),
				tblPuntos.getSelectedColumn()==1 ? 0 : 1);
		System.out.println("La celda de al lado es :("+tblPuntos.getSelectedRow()+";"+
				(tblPuntos.getSelectedColumn()==1 ? 0 : 1)+") con valor "+celdaDeAlLado);

		if(celdaVacia(nuevoValor) && modelPuntos.getRowCount()!=filasMinimas && celdaVacia(celdaDeAlLado)) {
			System.out.println("Esta vacia!");
			modelPuntos.removeRow(tblPuntos.getSelectedRow());
		}
		// Si una tiene contenido y otra no
	}
	private void calcularDeSerPosible() {
		Double x;
		Double y;
		String valorX;
		String valorY;
		Punto p;
		ArrayList<Punto> listadoPuntos=new ArrayList<Punto>();
		
		System.out.println("Intentando calcular!");
		
		for(int i=0;i<tblPuntos.getRowCount();i++) {
			
			valorX=(String)tblPuntos.getModel().getValueAt(i,0);
			valorY=(String)tblPuntos.getModel().getValueAt(i,1);
			
			if(puntoValido(valorX,valorY)) {
				
				x=Double.parseDouble(valorX);
				y=Double.parseDouble(valorY);
				p= new Punto(x,y);
				listadoPuntos.add(p);
			}
		}
		
		if(listadoPuntos.size()<2) {
			lblPasosPolinomio.setText("Puntos insuficientes!");
		}else {
			lblPasosPolinomio.setText("Calculando con "+(MetodoUtilizado)cmbMetodo.getSelectedItem());
			//(MetodoUtilizado)cmbMetodo.getSelectedItem().inicializar(listadoPuntos);
		}
	}
	
	class PuntosCellRenderer extends DefaultTableCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(
				JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column){
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			String cellValue=(String)tableModel.getValueAt(row, column);
			 if(celdaVacia(cellValue)) {
				setBackground(Color.YELLOW);
			}else if(!isNumeric(cellValue)) {
				setBackground(Color.RED);
			}else{
				setBackground(Color.WHITE);
			}

			return super.getTableCellRendererComponent(table, value, isSelected,
					hasFocus, row, column);
		}
	}
	
	class ItemChangeListener implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	         // Object item = event.getItem();
	          System.out.println("Cambio!");
	       }
	    }       
	}
	private boolean isNumeric(String strNum) {
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}

	protected boolean celdaVacia(String miCelda) {
		boolean celdaNula=miCelda==null;
		boolean celdaEnBlanco="".equals(miCelda);
		return (celdaNula || celdaEnBlanco);
	}
	private boolean puntoValido(String x,String y) {
		return isNumeric(x) && isNumeric(y);
	}
}
