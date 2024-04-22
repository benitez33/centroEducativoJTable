package CentroEducativo;

import javax.swing.JFrame;

import CentroEducativo.vistas.PanelEstudiante;
import CentroEducativo.vistas.Tabla;

public class Principal extends JFrame {

	static Principal instance = null;

	public static Principal getInstance() {
		if (instance == null) {
			instance = new Principal();
		}
		return instance;
	}
	
	PanelEstudiante panelEstudiante;

	public PanelEstudiante getPanelEstudiante() {
		return panelEstudiante;
	}

	public void setPanelValoracionMateria(PanelEstudiante panelEstudiante) {
		this.panelEstudiante = panelEstudiante;
	}

	public Principal() {
		super("Gestion estudiantes");
		this.setBounds(0, 0, 600, 450);

		Tabla tabla = new Tabla();

		this.getContentPane().add(tabla);

	}

	public static void main(String[] args) {
		Principal.getInstance().setVisible(true);
	}

}
