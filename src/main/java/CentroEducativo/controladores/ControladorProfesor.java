package CentroEducativo.controladores;

import CentroEducativo.entidades.Profesor;

public class ControladorProfesor extends SuperControlador {
	private static ControladorProfesor instance = null;

	public ControladorProfesor() {
		super("profesor", Profesor.class);
	}

	public static ControladorProfesor getInstance() {
		if (instance == null) {
			instance = new ControladorProfesor();
		}
		return instance;
	}

}
