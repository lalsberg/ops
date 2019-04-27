package com.lalsberg.ops;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionUseRepository extends JpaRepository<SolutionUse, Long> {

	Optional<SolutionUse> findTopByOrderByIdDesc();

	List<SolutionUse> findAllByOrderByCreatedAt();

}
