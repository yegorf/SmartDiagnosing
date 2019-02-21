package com.yegorf.controllers.repos;

import com.yegorf.controllers.enities.Diagnose;
import com.yegorf.controllers.enities.Match;
import com.yegorf.controllers.enities.Matches;
import org.springframework.data.repository.CrudRepository;

public interface MatchesRepo extends CrudRepository<Matches, Long> {
}
