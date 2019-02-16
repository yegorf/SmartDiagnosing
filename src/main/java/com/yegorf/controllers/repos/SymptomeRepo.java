package com.yegorf.controllers.repos;

import com.yegorf.controllers.enities.Diagnose;
import com.yegorf.controllers.enities.Symptome;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.List;

@Repository
public interface SymptomeRepo extends CrudRepository<Symptome, Long> {

}
