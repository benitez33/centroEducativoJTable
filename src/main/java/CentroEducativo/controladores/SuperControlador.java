package CentroEducativo.controladores;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import CentroEducativo.entidades.Entidad;
import CentroEducativo.entidades.Estudiante;
import CentroEducativo.entidades.Materia;
import CentroEducativo.entidades.Profesor;
import CentroEducativo.entidades.ValoracionMateria;

public class SuperControlador {

	private static String nombreTabla = "";
	private Class tipoEntidad;
	private static EntityManager em = null;
	
	public SuperControlador(String nombreTabla, Class tipoEntidad) {
		this.nombreTabla = nombreTabla;
		this.tipoEntidad = tipoEntidad;
	}

	/**
	 * 
	 * @return
	 */
	protected static EntityManager getEntityManager() {
		if (em == null) {
			em = Persistence.createEntityManagerFactory("Centro Educativo").createEntityManager();
		}
		return em;
	}
	

	/**
	 * 
	 * @return
	 */
	public List<? extends Entidad> findAll() {
		return (List<Entidad>) getEntityManager()
				.createNativeQuery("SELECT * FROM " + nombreTabla + ";", this.tipoEntidad).getResultList();

	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Entidad find(int id) {
		EntityManager em;
		try {
			em = getEntityManager();
			Entidad entidad = (Entidad) em.find(this.tipoEntidad, id);
			return entidad;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param estudiante
	 * @param profesor
	 * @param materia
	 * @return
	 */
	public static ValoracionMateria obtenerNota(Estudiante estudiante, Profesor profesor, Materia materia) {
		try {
			return (ValoracionMateria) getEntityManager().createNativeQuery(
					"SELECT * FROM valoracionmateria where " + estudiante.getId() + " = idEstudiante and "
							+ profesor.getId() + " = idProfesor and " + materia.getId() + " = idMateria;",
					ValoracionMateria.class).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

	/**
	 * 
	 * @param estudiante
	 * @param profesor
	 * @param materia
	 * @param nota
	 * @return
	 */
	public static ValoracionMateria ordenarEstudiantes(Estudiante estudiante, Profesor profesor, Materia materia,
			Integer nota) {
		try {
			return (ValoracionMateria) getEntityManager().createNativeQuery("SELECT * FROM valoracionmateria where "
					+ estudiante.getId() + " = idEstudiante and " + profesor.getId() + " = idProfesor and "
					+ materia.getId() + " = idMateria and " + nota + " = valoracion;", ValoracionMateria.class)
					.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}

	}

	/**
	 * 
	 * @param estudiante
	 * @param profesor
	 * @param materia
	 * @param nota
	 * @param fecha
	 */
	public static void insert(Estudiante estudiante, Profesor profesor, Materia materia, Integer nota, Date fecha) {
		ValoracionMateria v = new ValoracionMateria();
		v.setIdEstudiante(estudiante.getId());
		v.setIdMateria(materia.getId());
		v.setIdProfesor(profesor.getId());
		v.setValoracion(nota);
		v.setFecha(fecha);

		em.getTransaction().begin();
		em.persist(v);
		em.getTransaction().commit();

	}

	/**
	 * 
	 * @param estudiante
	 * @param profesor
	 * @param materia
	 * @param nota
	 * @param fecha
	 */
	public static void update(Estudiante estudiante, Profesor profesor, Materia materia, Integer nota, Date fecha) {
		ValoracionMateria v = obtenerNota(estudiante, profesor, materia);

		v.setValoracion(nota);
		v.setFecha(fecha);

		em.getTransaction().begin();
		em.persist(v);
		em.getTransaction().commit();

	}
}
