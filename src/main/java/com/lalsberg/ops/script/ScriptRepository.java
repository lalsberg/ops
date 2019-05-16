package com.lalsberg.ops.script;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScriptRepository extends JpaRepository<Script, Long> {

	List<Script> findAllByArchivedFalse();

}
