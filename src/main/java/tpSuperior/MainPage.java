package tpSuperior;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MainPage {

	private JFrame frame;
	private JTable tblPuntos;
	private int filasMinimas=2;
	private DefaultTableModel modelPuntos;
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

					}
				});


		JLabel lblTitulo = new JLabel("El titulo");
		lblTitulo.setBounds(10, 28, 275, 14);
		frame.getContentPane().add(lblTitulo);

		JComboBox<Object> cmbMetodo = new JComboBox<Object>();
		cmbMetodo.setBounds(392, 45, 246, 25);
		frame.getContentPane().add(cmbMetodo);

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

		JLabel lblPasosPolinomio = new JLabel("Pasos y Polinomio:");
		lblPasosPolinomio.setBounds(32, 310, 300, 64);
		frame.getContentPane().add(lblPasosPolinomio);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(169, 151, -139, 130);
		frame.getContentPane().add(scrollPane);
	}

	protected boolean celdaVacia(Object miCelda) {
		boolean celdaNula=miCelda==null;
		boolean celdaEnBlanco="".equals(miCelda);
		return (celdaNula || celdaEnBlanco);
	}
	protected void chequearYAgregarCeldas() {
		System.out.println("Ha modificado ("+tblPuntos.getSelectedRow()+";"+tblPuntos.getSelectedColumn()+")");
		// 1 Si agregas contenido ultima, agrego 1
		CellEditor miCelda=tblPuntos.getCellEditor(tblPuntos.getSelectedRow(), tblPuntos.getSelectedColumn());
		if(tblPuntos.getSelectedRow()==tblPuntos.getRowCount()-1) {
			if(!celdaVacia(miCelda)) {
				//Editaste la ultima, agrego una fila
				modelPuntos.addRow(new Object[]{"", ""});
			}
		}
		
		// 2 Si borraste una, elimino si la otra esta en blanco, sino marco en rojo
		//No borro menos de 2
		
		if(celdaVacia(miCelda) && modelPuntos.getRowCount()!=2) {
			Object celdaDeAlLado=modelPuntos.getValueAt(tblPuntos.getSelectedRow(),
					tblPuntos.getSelectedColumn()==1 ? 0 : 1);
			System.out.println("La celda de al lado es :("+tblPuntos.getSelectedRow()+";"+
					(tblPuntos.getSelectedColumn()==1 ? 0 : 1)+") con valor "+celdaDeAlLado);
			if(celdaVacia(celdaDeAlLado)) {//Elimino fila
				System.out.println("Esta vacia!");
				int seleccionado=tblPuntos.getSelectedRow();
				modelPuntos.removeRow(tblPuntos.getSelectedRow());
			}
		}
	}
}
