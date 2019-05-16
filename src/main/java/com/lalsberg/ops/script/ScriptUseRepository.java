package com.lalsberg.ops.script;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScriptUseRepository extends JpaRepository<ScriptUse, Long> {

	Optional<ScriptUse> findTopByOrderByIdDesc();

	List<ScriptUse> findAllByOrderByCreatedAt();

}
