package com.yegorf.controllers.repos;

import com.yegorf.controllers.enities.Diagnose;
import com.yegorf.controllers.enities.Symptome;
import org.springframework.data.repository.CrudRepository;

public interface SymptomeRepo extends CrudRepository<Symptome, Long> {
}
