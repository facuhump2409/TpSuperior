package tpSuperior;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTextField;
import java.awt.ScrollPane;

public class MainPage {

	private JFrame frame;
	private JTable tblPuntos;
	private int filasMinimas=2;
	private DefaultTableModel modelPuntos;
	private JComboBox<Object> cmbMetodo;
	private int auxCont=0;
	private JTextArea txtPolinomio;
	private JTextArea txtInformacionEjecucion;
	private JTextArea txtPasos;
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
		frame.setForeground(new Color(0, 0, 139));
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		tblPuntos =new JTable(new DefaultTableModel(new Object[]{"Columnas X", "Columna Y"}, 0));
		tblPuntos.setBackground(new Color(192, 192, 192));
		tblPuntos.setToolTipText("");
		tblPuntos.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tblPuntos.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(173, 216, 230), new Color(186, 85, 211), null, null));
		tblPuntos.setCellSelectionEnabled(true);
		//tblPuntos.setBounds(195, 204, 358, 230);
		//frame.getContentPane().add(tblPuntos);
		modelPuntos = (DefaultTableModel) tblPuntos.getModel();
		JTableHeader puntosHeader=new JTableHeader();
		tblPuntos.setTableHeader(puntosHeader);
		modelPuntos.addRow(new Object[]{"", ""});
		modelPuntos.addRow(new Object[]{"", ""});
		tblPuntos.getModel().addTableModelListener(
				new TableModelListener() 
				{
					
					public void tableChanged(TableModelEvent e) {
						chequearYAgregarCeldas();
						calcularDeSerPosible();
					}
				});

		for (int i =0; i<modelPuntos.getColumnCount();i++) {
			tblPuntos.setDefaultRenderer(tblPuntos.getColumnClass(i), new PuntosCellRenderer());
		}
		

		JLabel lblTitulo = new JLabel("Interpolaci\u00F3n");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Rockwell", Font.ITALIC, 40));
		lblTitulo.setBackground(new Color(75, 0, 130));
		lblTitulo.setForeground(new Color(100, 149, 237));
		lblTitulo.setBounds(10, 5, 347, 42);
		frame.getContentPane().add(lblTitulo);


		JLabel lblInformacionMetodo = new JLabel("Descripci\u00F3n del m\u00E9todo elegido:");
		lblInformacionMetodo.setFont(new Font("Verdana", Font.BOLD, 12));
		lblInformacionMetodo.setHorizontalAlignment(SwingConstants.LEFT);
		lblInformacionMetodo.setBounds(392, 102, 246, 30);
		frame.getContentPane().add(lblInformacionMetodo);
		
		
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

		
		JButton btnAyuda = new JButton("Ayuda");
		btnAyuda.setBackground(new Color(139, 0, 139));
		btnAyuda.setForeground(new Color(0, 0, 0));
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clickeaste el boton!");
				JOptionPane.showMessageDialog(null, "Recuerde cargar de a pares los numeros (x,y) y por lo menos 2", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
				 
			}
		});
		
		btnAyuda.setBounds(401, 232, 89, 23);
		frame.getContentPane().add(btnAyuda);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCerrar.setBackground(new Color(255, 0, 0));
		btnCerrar.setBounds(526, 232, 89, 23);
		frame.getContentPane().add(btnCerrar);

		JLabel lblMetodo = new JLabel("Seleccione el m\u00E9todo a utilizar..");
		lblMetodo.setFont(new Font("Verdana", Font.BOLD, 12));
		lblMetodo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMetodo.setBounds(367, 28, 271, 14);
		frame.getContentPane().add(lblMetodo);
		
		JLabel lblColumnaX = new JLabel("X");
		lblColumnaX.setFont(new Font("Perpetua", Font.BOLD, 20));
		lblColumnaX.setBounds(20, 63, 78, 14);
		frame.getContentPane().add(lblColumnaX);
		
		JLabel lblColumnaY = new JLabel("Y");
		lblColumnaY.setFont(new Font("Perpetua Titling MT", Font.BOLD, 20));
		lblColumnaY.setBounds(201, 58, 46, 14);
		frame.getContentPane().add(lblColumnaY);
		
		JLabel lblPolinomio = new JLabel("Polinomio:");
		lblPolinomio.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPolinomio.setBounds(10, 364, 126, 25);
		frame.getContentPane().add(lblPolinomio);
				
		
		JLabel lblPasosDescripcion = new JLabel("Pasos seguidos y descripcion:");
		lblPasosDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPasosDescripcion.setBounds(392, 292, 300, 25);
		frame.getContentPane().add(lblPasosDescripcion);
		
		txtPolinomio = new JTextArea();
		txtPolinomio.setEditable(false);
		txtPolinomio.setEnabled(false);
		txtPolinomio.setLineWrap(true);
		txtPolinomio.setBounds(10, 386, 365, 64);
		frame.getContentPane().add(txtPolinomio);
		txtPolinomio.setColumns(10);
		
		txtInformacionEjecucion = new JTextArea();
		txtInformacionEjecucion.setEnabled(false);
		txtInformacionEjecucion.setEditable(false);
		txtInformacionEjecucion.setLineWrap(true);
		txtInformacionEjecucion.setBounds(381, 143, 329, 78);
		frame.getContentPane().add(txtInformacionEjecucion);
		txtInformacionEjecucion.setColumns(10);
		
		txtPasos = new JTextArea();
		txtPasos.setEnabled(false);
		txtPasos.setEditable(false);
		txtPasos.setLineWrap(true);
		txtPasos.setBounds(401, 316, 291, 134);
		frame.getContentPane().add(txtPasos);
		txtPasos.setColumns(10);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(10, 76, 357, 290);
		frame.getContentPane().add(scrollPane);
		scrollPane.add(tblPuntos);

		
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
			txtInformacionEjecucion.setText("Puntos insuficientes!");
		}else {
			MetodoUtilizado metodo=(MetodoUtilizado)cmbMetodo.getSelectedItem();
			txtInformacionEjecucion.setText("Calculando con "+metodo.getNombreMetodo());
			metodo.inicializar(listadoPuntos);
			String mensaje=metodo.esEquispaciado()? "Los puntos son equiespaciados":"Los puntos no son equiespaciados";
			mensaje+= "\n Los pasos seguidos fueron:"+metodo.getPasos();
			mensaje+= "\n El grado del polinomio es :"+metodo.getGrado();
			txtPasos.setText(mensaje);
			txtPolinomio.setText(metodo.getPolinomio());
			
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
