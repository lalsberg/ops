package com.lalsberg.ops;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {

	@Autowired
	private ResolutionResolver resolutionResolver;

	@Autowired
	private ResolutionRepository resolutionRepository;

	@RequestMapping(method = POST, path = "/resolution")
	public List<ResolutionDTO> findResolution(@RequestBody OpsDTO opsDTO) {
		Ops ops = new Ops(opsDTO.getName(), opsDTO.getDescription());

		List<Resolution> resolutions = resolutionResolver.findPossibleResolutions(ops);
		
		System.out.println(resolutions);

		List<ResolutionDTO> collect = resolutions.stream().map(resolution -> new ResolutionDTO(resolution.getTitle(), resolution.getDescription(), resolution.getFrequency())).collect(toList());
		System.out.println(collect);
		return collect;
	}

	@RequestMapping(method = GET, path = "/resolutions")
	public List<ResolutionDTO> findAllResolutions() {
		List<Resolution> resolutions = resolutionRepository.findAllByOrderByFrequencyDesc();
		
		System.out.println(resolutions);

		List<ResolutionDTO> collect = resolutions.stream().map(resolution -> new ResolutionDTO(resolution.getTitle(), resolution.getDescription(), resolution.getFrequency())).collect(toList());
		System.out.println(collect);
		return collect;
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(method = POST, path = "/resolution/create")
	public void createResolution(@RequestBody ResolutionDTO resolutionDTO) {
		System.out.println(resolutionDTO);
		
		Resolution resolution = new Resolution(resolutionDTO.getTitle(), resolutionDTO.getDescription());
		resolutionRepository.save(resolution);
	}
}
