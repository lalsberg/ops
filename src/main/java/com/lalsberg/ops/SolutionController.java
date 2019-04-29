package com.lalsberg.ops;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
	@Autowired
	private SolutionUseRepository solutionUseRepository;

	@RequestMapping(method = GET, path = "/solutions")
	public List<SolutionDTO> list() {
		List<Solution> solutions = solutionRepository.findAllByArchivedFalse();

		System.out.println(solutions);

		return solutions.stream().map(solution -> new SolutionDTO(solution.getId(), solution.getTitle(), solution.getDescription(), solution.getFrequency(), solution.isArchived())).collect(toList());
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

		solution.get().markUsed(solutionUseRepository);
		solutionRepository.save(solution.get());
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = DELETE, path = "/solution/{solutionId}/frequency")
	public ResponseEntity<Void> decrementFrequency(@PathVariable long solutionId) {
		Optional<Solution> solution = solutionRepository.findById(solutionId);
		if (!solution.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		solution.get().unmarkUsed(solutionUseRepository);
		solutionRepository.save(solution.get());
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = GET, path = "/solution/{id}")
	public ResponseEntity<SolutionDTO> get(@PathVariable long id) {
		Optional<Solution> solutionOptional = solutionRepository.findById(id);
		if (!solutionOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Solution solution = solutionOptional.get();

		SolutionDTO solutionDTO = new SolutionDTO(solution.getId(), solution.getTitle(), solution.getDescription(), solution.getFrequency(), solution.isArchived());
		return ResponseEntity.ok(solutionDTO);
	}

	@RequestMapping(method = PUT, path = "/solution")
	public ResponseEntity<SolutionDTO> edit(@RequestBody SolutionDTO solutionDTO) {
		Optional<Solution> solutionOptional = solutionRepository.findById(solutionDTO.getId());
		if (!solutionOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Solution solution = solutionOptional.get();
		solution.setDescription(solutionDTO.getDescription());
		solution.setTitle(solutionDTO.getTitle());
		solutionRepository.save(solution);

		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = DELETE, path = "/solution/{id}")
	public ResponseEntity<SolutionDTO> archive(@PathVariable long id) {
		Optional<Solution> solutionOptional = solutionRepository.findById(id);
		if (!solutionOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Solution solution = solutionOptional.get();
		solution.archive();
		solutionRepository.save(solution);

		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = GET, path = "/solutions/stats", produces = "application/json")
	public ResponseEntity<StatsDTO> stats() {
		System.out.println("aaaaa");
		List<SolutionUse> solutionUses = solutionUseRepository.findAllByOrderByCreatedAt();

		Map<LocalDate, Long> map = solutionUses.stream()
				  .collect(Collectors.groupingBy(SolutionUse::getCreatedAtDate, Collectors.counting()));

		StatsDTO statsDTO = new StatsDTO(map);

		return ResponseEntity.ok(statsDTO);
	}
}
