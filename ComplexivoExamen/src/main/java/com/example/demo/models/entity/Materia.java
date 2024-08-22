package com.example.demo.models.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "materias")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMateria;

	private String nombreMateria;

	@OneToMany(mappedBy = "materia")
	private List<Estudiante> estudiantes;

	public Materia() {

	}

	public Materia(Long idMateria, String nombreMateria, List<Estudiante> estudiantes) {

		this.idMateria = idMateria;
		this.nombreMateria = nombreMateria;
		this.estudiantes = estudiantes;
	}

	public Long getIdMateria() {
		return idMateria;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

}
