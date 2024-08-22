package com.example.demo.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class EstudianteDaoImpl implements IEstudianteDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Estudiante> findAll() {
		
		return em.createQuery("from Estudiante").getResultList();
	}
	
	@Transactional
	@Override
	public void save(Estudiante estudiante) {
		
		if(estudiante.getIdEstudiante()!=null && estudiante.getIdEstudiante()>0) {
			em.merge(estudiante);
		}else {
			em.persist(estudiante);
		}
		
	}

	@Override
	public Estudiante findOne(Long id) {
		
		return em.find(Estudiante.class, id);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		
		em.remove(findOne(id));
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Estudiante> findByNombreEstudiante(String nombreEstudiante) {
	    TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE e.nombreEstudiante LIKE :nombreEstudiante", Estudiante.class);
	    query.setParameter("nombreEstudiante", "%" + nombreEstudiante + "%");  
	    return query.getResultList();
	}


}
