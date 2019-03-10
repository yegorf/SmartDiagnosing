package com.yegorf.controllers.repos;

import com.yegorf.controllers.enities.Diagnose;
import org.springframework.data.repository.CrudRepository;

public interface DiagnoseRepo extends CrudRepository<Diagnose, Integer> {
}
