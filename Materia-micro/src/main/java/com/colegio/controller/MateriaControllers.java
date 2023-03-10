package com.colegio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colegio.entity.Materia;
import com.colegio.service.IMateriaService;

@RestController
@RequestMapping("/mic-materias")
public class MateriaControllers {

	@Autowired
	private IMateriaService materiaService;
	
	@GetMapping("materia/{id}")
	public ResponseEntity<Materia> obtenerMateria(@PathVariable("id") Long id) {
		Materia materias = materiaService.getMateriaById(id);
		if (materias == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(materias);
	}
	
	@PostMapping("materia")
	public ResponseEntity<Materia> guardarMaterias(@RequestBody Materia materia) {
		Materia nuevaMateria = materiaService.save(materia);
		return ResponseEntity.ok(nuevaMateria);
	}
	
	@GetMapping("materias/usuario/{usuarioId}")
	public ResponseEntity<List<Materia>> listarMateriasPorUsuarioId(@PathVariable("usuarioId") int id) {
		List<Materia> materias = materiaService.byUsuarioId(id);
		if (materias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(materias);
	}
	
	@GetMapping("/usuario/MateriasAprobadas/{usuarioId}")
	public ResponseEntity<List<Materia>> listarMateriasAprobadas(@PathVariable("usuarioId") int id) {
		if (materiaService.listaMateriasAprobadas(id) == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(materiaService.listaMateriasAprobadas(id));
	}
	
	@GetMapping("/usuario/MateriasDesaprobadas/{usuarioId}")
	public ResponseEntity<List<Materia>> listarMateriasDesaprobadas(@PathVariable("usuarioId") int id) {
		if (materiaService.listaMateriasDesaprobadas(id) == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(materiaService.listaMateriasDesaprobadas(id));
	}
}
