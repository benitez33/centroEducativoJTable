package CentroEducativo.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import CentroEducativo.entidades.Sexo;

public class ControladorSexo  extends SuperControlador {
	private static ControladorSexo instance = null;

	public ControladorSexo() {
		super("sexo", Sexo.class);
	}
	
	public static ControladorSexo getInstance() {
		if (instance == null) {
			instance = new ControladorSexo();
		}
		return instance;
	}
	
	public static List<Sexo> getTodos () {
		List<Sexo> entities = new ArrayList<Sexo>();
		EntityManager em = getEntityManager();
		try {			
			Query q = em.createNativeQuery("SELECT * FROM tipologiasexo", Sexo.class);
			entities = (List<Sexo>) q.getResultList();
		}
		catch (NoResultException nrEx) {
		}
		em.close();
		return entities;
	}
}
