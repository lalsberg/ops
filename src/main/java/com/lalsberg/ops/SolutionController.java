package com.lalsberg.ops;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolutionController {

	@Autowired
	private SolutionRepository solutionRepository;

	@RequestMapping(method = GET, path = "/solutions")
	public List<SolutionDTO> findAllSolutions() {
		List<Solution> solutions = solutionRepository.findAllByOrderByFrequencyDesc();

		System.out.println(solutions);

		return solutions.stream().map(solution -> new SolutionDTO(solution.getId(), solution.getTitle(), solution.getDescription(), solution.getFrequency())).collect(toList());
	}

	@RequestMapping(method = POST, path = "/solution/create")
	public void createSolution(@RequestBody SolutionDTO solutionDTO) {
		System.out.println(solutionDTO);
		
		Solution resolution = new Solution(solutionDTO.getTitle(), solutionDTO.getDescription());
		solutionRepository.save(resolution);
	}

	@RequestMapping(method = POST, path = "/solution/{solutionId}/frequency")
	public ResponseEntity<Void> incrementFrequency(@PathVariable long solutionId) {
		Optional<Solution> solution = solutionRepository.findById(solutionId);
		if (!solution.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		solution.get().incrementFrequency();
		solutionRepository.save(solution.get());
		return ResponseEntity.ok().build();
	}
}
