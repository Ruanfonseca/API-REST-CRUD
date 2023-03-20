package br.com.springboot.curso_java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_java.model.Usuario;
import br.com.springboot.curso_java.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/mostraNome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Hello " + name + "!";
	}

	@GetMapping(value = "listatodos")
	@ResponseBody /* Retorna os dados para o corpo da resposta (JSON) */
	public ResponseEntity<List<Usuario>> listaUsuario() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@PostMapping(value = "salvar")
	@ResponseBody /* descreve a resposta */
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {/* recebe os dados para salvar */
		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "delete")
	@ResponseBody /* descreve a resposta */
	public ResponseEntity<String> delete(@RequestParam Long iduser) {/* recebe o id para deletar */
		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
	}

	@GetMapping(value = "buscarUserId")
	@ResponseBody /* descreve a resposta */
	public ResponseEntity<Usuario> buscarUserId(
			@RequestParam(name = "iduser") Long iduser) {/* recebe o id para buscar */

		Usuario usuario = usuarioRepository.findById(iduser).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	

	@PutMapping(value = "atualizar")
	@ResponseBody /* descreve a resposta */
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {/* recebe os dados para salvar */
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não informado", HttpStatus.OK);
		}
		Usuario user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);

	}


	@GetMapping(value = "buscarPorNome")
	@ResponseBody /* descreve a resposta */
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name="name")String name){			
		List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}



}
