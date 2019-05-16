package com.lalsberg.ops.script;
import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

@RestController
public class ScriptController {

	@PersistenceContext(unitName = "marketplace")
	private EntityManager em;

	@Autowired
	private ScriptRepository scriptRepository;

	@Autowired
	private ScriptUseRepository scriptUseRepository;

	@RequestMapping(method = GET, path = "/ops-scripts")
	public List<ScriptDTO> list() {
		List<Script> scripts = scriptRepository.findAllByArchivedFalse();

		return scripts.stream().map(script -> 
			new ScriptDTO(script.getId(), script.getTitle(), script.getContent(), script.getSolution(), script.getFrequency(), script.isArchived())
		).collect(toList());
	}

	@RequestMapping(method = POST, path = "/script/{scriptId}/frequency")
	public ResponseEntity<Void> incrementFrequency(@PathVariable long scriptId) {
		Optional<Script> script = scriptRepository.findById(scriptId);
		if (!script.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		script.get().markUsed(scriptUseRepository);
		scriptRepository.save(script.get());
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = DELETE, path = "/script/{scriptId}/frequency")
	public ResponseEntity<Void> decrementFrequency(@PathVariable long scriptId) {
		Optional<Script> script = scriptRepository.findById(scriptId);
		if (!script.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		script.get().unmarkUsed(scriptUseRepository);
		scriptRepository.save(script.get());
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = GET, path = "/script/{id}")
	public ResponseEntity<ScriptDTO> get(@PathVariable long id) {
		Optional<Script> scriptOptional = scriptRepository.findById(id);
		if (!scriptOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Script script = scriptOptional.get();

		ScriptDTO ScriptDTO = new ScriptDTO(script.getId(), script.getTitle(), script.getContent(), script.getSolution(), script.getFrequency(), script.isArchived());
		return ResponseEntity.ok(ScriptDTO);
	}

	@RequestMapping(method = PUT, path = "/script")
	public ResponseEntity<ScriptDTO> edit(@RequestBody ScriptDTO scriptDTO) {
		Optional<Script> scriptOptional = scriptRepository.findById(scriptDTO.getId());
		if (!scriptOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Script script = scriptOptional.get();
		script.setContent(scriptDTO.getContent());
		script.setSolution(scriptDTO.getSolution());
		script.setTitle(scriptDTO.getTitle());
		scriptRepository.save(script);

		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = DELETE, path = "/script/{id}")
	public ResponseEntity<ScriptDTO> archive(@PathVariable long id) {
		Optional<Script> scriptOptional = scriptRepository.findById(id);
		if (!scriptOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Script script = scriptOptional.get();
		script.archive();
		scriptRepository.save(script);

		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = GET, path = "/scripts/stats", produces = "application/json")
	public ResponseEntity<StatsDTO> stats() {
		List<ScriptUse> scriptUses = scriptUseRepository.findAllByOrderByCreatedAt();

		Map<LocalDate, Long> map = scriptUses.stream()
				  .collect(Collectors.groupingBy(ScriptUse::getCreatedAtDate, Collectors.counting()));

		StatsDTO statsDTO = new StatsDTO(map);

		return ResponseEntity.ok(statsDTO);
	}

	@RequestMapping(method = GET, path = "/script/{scriptId}/execute")
	public List<ScriptDTO> execute(@PathVariable long scriptId) {

		com.lalsberg.ops.script.Script theScript = scriptRepository.findById(scriptId).get();

		CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
		compilerConfiguration.setScriptBaseClass(MyScript.class.getName());

		// call groovy expressions from Java code
		Binding binding = new Binding();
		binding.setProperty("em", em);

		GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), binding, compilerConfiguration);

		groovy.lang.Script script = shell.parse(theScript.getContent());


		Object run = script.run();
		System.out.println(run);

		return null;
	}

	@RequestMapping(method = POST, path = "/scripts")
	public ResponseEntity<Void> create(@RequestBody ScriptDTO scriptDTO) {
		Script script = new Script(scriptDTO.getTitle(), scriptDTO.getContent(), scriptDTO.getSolution());
		scriptRepository.save(script);

		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = GET, path = "/scripts/discover")
	public List<ScriptDTO> discover(@RequestParam("matchId") String matchId) {
		List<Script> scripts = scriptRepository.findAll();

		List<ScriptDTO> foundScripts = new ArrayList<>();

		for (Script script : scripts) {
			CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
			compilerConfiguration.setScriptBaseClass(MyScript.class.getName());

			Binding binding = new Binding();
			binding.setProperty("em", em);
			binding.setProperty("MATCH_ID", matchId);

			GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), binding, compilerConfiguration);

			groovy.lang.Script groovyScript = shell.parse(script.getContent());

			Object run = groovyScript.run();

			if (run != null) {
				ScriptDTO foundScript = new ScriptDTO(script.getId(), script.getTitle(), script.getContent(), script.getSolution(), script.getFrequency(), script.isArchived());
				foundScripts.add(foundScript);
			}
		}

		return foundScripts;
	}

}
