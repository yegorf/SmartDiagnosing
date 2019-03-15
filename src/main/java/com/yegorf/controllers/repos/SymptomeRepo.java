package com.yegorf.controllers.repos;

import com.yegorf.controllers.enities.Symptome;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomeRepo extends CrudRepository<Symptome, Integer> {
}
