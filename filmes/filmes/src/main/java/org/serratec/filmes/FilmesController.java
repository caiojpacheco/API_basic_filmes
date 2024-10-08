package org.serratec.filmes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmesController {
	
	@Autowired
	private FilmesRepository repositorio;
	
	@GetMapping
	public List <Filmes> obterFilme() {
		return repositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Filmes obterPorId(@PathVariable Long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	@PostMapping
	public Filmes cadastrarFilme(@RequestBody Filmes filme) {
		return repositorio.save(filme);
	}
	
	@DeleteMapping("/{id}")
	public void excluirFilme(@PathVariable Long id) {
		repositorio.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Filmes alterarFilme(@PathVariable Long id, @RequestBody Filmes filme) {
		if(repositorio.existsById(id)){
			filme.setId(id);
			repositorio.save(filme);
			return filme;
		}
		
		return null;
	}
}
