package com.lalsberg.ops;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private ResolutionResolver resolutionResolver;

	@RequestMapping(method = POST, path = "/resolution")
	public List<ResolutionDTO> findResolution(@RequestBody OpsDTO opsDTO) {
		Ops ops = new Ops(opsDTO.getName(), opsDTO.getDescription());

		List<Resolution> resolutions = resolutionResolver.findPossibleResolutions(ops);
		
		System.out.println(resolutions);

		List<ResolutionDTO> collect = resolutions.stream().map(resolution -> new ResolutionDTO(resolution.getDescription())).collect(toList());
		System.out.println(collect);
		return collect;
	}

}
