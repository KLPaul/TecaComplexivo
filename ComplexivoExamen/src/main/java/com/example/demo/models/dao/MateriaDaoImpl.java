package com.example.demo.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Materia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MateriaDaoImpl implements IMateriaDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Materia> findAll() {
		
		return em.createQuery("from Materia").getResultList();
	}
	
	@Transactional
	@Override
	public void save(Materia materia) {
		
		if(materia.getIdMateria()!=null && materia.getIdMateria()>0) {
			em.merge(materia);
		}else {
			em.persist(materia);
		}
		
	}

	@Override
	public Materia findOne(Long id) {
		
		return em.find(Materia.class,id);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		
		em.remove(findOne(id));
		
	}

}
