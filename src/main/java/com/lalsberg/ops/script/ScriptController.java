package com.lalsberg.ops.script;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

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

import com.lalsberg.ops.SolutionDTO;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

@RestController
public class ScriptController {

	@PersistenceContext(unitName = "marketplace")
	private EntityManager em;

	@Autowired
	private ScriptRepository scriptRepository;

	@RequestMapping(method = GET, path = "/script/{scriptId}/execute")
	public List<SolutionDTO> execute(@PathVariable long scriptId) {

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
	public List<ScriptDTO> discover(@RequestParam("filter") String filter) {
		List<Script> scripts = scriptRepository.findAll();

		List<ScriptDTO> foundScripts = new ArrayList<>();

		for (Script script : scripts) {
			CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
			compilerConfiguration.setScriptBaseClass(MyScript.class.getName());

			Binding binding = new Binding();
			binding.setProperty("em", em);
			binding.setProperty("MATCH_ID", filter);

			GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), binding, compilerConfiguration);

			groovy.lang.Script groovyScript = shell.parse(script.getContent());

			Object run = groovyScript.run();

			if (run != null) {
				ScriptDTO foundScript = new ScriptDTO(script.getTitle(), script.getContent(), script.getSolution());
				foundScripts.add(foundScript);
			}
		}

		return foundScripts;
	}

}
