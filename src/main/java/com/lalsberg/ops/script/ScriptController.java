package com.lalsberg.ops.script;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lalsberg.ops.SolutionDTO;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

@RestController
public class ScriptController {

//	@PersistenceContext(unitName = "foo")
//	private EntityManager em;

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

		binding.setProperty("orderFreteId", new Integer(2));
		binding.setProperty("em", em);

		GroovyShell shell = new GroovyShell(this.getClass().getClassLoader(), binding, compilerConfiguration);

//		Script script = shell.parse(getScript());
		Script script = shell.parse(theScript.getScript());


		Object run = script.run();
		System.out.println(run);

		return null;
	}

	public static String getScript() {
		return "matchId = runQuery(\"select * from ref_frete\", em); print(matchId[1])";
	}

}
