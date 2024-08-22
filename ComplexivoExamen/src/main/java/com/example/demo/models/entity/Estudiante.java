package com.example.demo.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiantes")
public class Estudiante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEstudiante;
	
	private String cedula;
	
	private String nombreEstudiante;
	
	private String apellido;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMateria")
    private Materia materia;
	
	

	public Estudiante() {
		
	}


	public Estudiante(Long idEstudiante, String cedula, String nombreEstudiante, String apellido, Materia materia) {
		this.idEstudiante = idEstudiante;
		this.cedula = cedula;
		this.nombreEstudiante = nombreEstudiante;
		this.apellido = apellido;
		this.materia = materia;
	}



	public Long getIdEstudiante() {
		return idEstudiante;
	}



	public String getCedula() {
		return cedula;
	}
	
	





	public String getNombreEstudiante() {
		return nombreEstudiante;
	}


	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}


	public String getApellido() {
		return apellido;
	}



	public Materia getMateria() {
		return materia;
	}



	public void setIdEstudiante(Long idEstudiante) {
		this.idEstudiante = idEstudiante;
	}



	public void setCedula(String cedula) {
		this.cedula = cedula;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	
	
	
	
	
	
	
	

}
