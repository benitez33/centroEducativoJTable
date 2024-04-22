package CentroEducativo.vistas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CentroEducativo.controladores.ControladorEstudiante;
import CentroEducativo.entidades.Estudiante;

public class Tabla extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	List<Estudiante> estudiantes = new ArrayList<Estudiante>();
	private DefaultTableModel dtm = null;
	private Object datosEnTabla[][] = getDatosDeTabla();
	private String titulosEnTabla[] = getTitulosColumnas();


	/**
	 * Create the panel.
	 */
	public Tabla() {
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane);
		
		this.dtm = getDefaultTableModelNoEditable();

		table = new JTable(dtm);
		splitPane.setLeftComponent(table);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getButton() == MouseEvent.BUTTON1) {
					int indiceFilaSel = table.getSelectedRow() + 1;
					Estudiante est = (Estudiante) ControladorEstudiante.getInstance().find(indiceFilaSel);
					
					PanelEstudiante panel = new PanelEstudiante(est);
					splitPane.setRightComponent(panel);
				}
			}
		});
		
	}
	
	/**
	 * 
	 * @return
	 */
	private Object[][] getDatosDeTabla() {
		estudiantes = (List<Estudiante>) ControladorEstudiante.getInstance().findAll();
		
		Object[][] matrizDatos = new Object[estudiantes.size()][4];
		
		for (int i = 0; i < estudiantes.size(); i++) {
			matrizDatos[i][0] = estudiantes.get(i).getId();
			matrizDatos[i][1] = estudiantes.get(i).getNombre();
			matrizDatos[i][2] = estudiantes.get(i).getApellido_1();
			matrizDatos[i][3] = estudiantes.get(i).getApellido_2();
		}
		
		return matrizDatos;
	}
	
	/**
	 * 
	 * @return
	 */
	private DefaultTableModel getDefaultTableModelNoEditable () {
		DefaultTableModel dtm = new DefaultTableModel(datosEnTabla, titulosEnTabla) {
			
			/**
			 * La sobreescritura de este m�todo nos permite controlar qu� celdas queremos que sean editables
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column != 1) {
					return false;
				}
				return true;
			}
		};
		return dtm;
	}
	
	/** 
	 * 
	 * @return
	 */
	public static String[] getTitulosColumnas() {
		return new String[] {"Id", "Nombre", "1� apellido", "2� apellido", "Fecha Nac.", "Edad", "Activo", "Provincia"};
	}

}
