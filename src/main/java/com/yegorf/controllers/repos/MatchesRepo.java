package com.yegorf.controllers.repos;

import com.yegorf.controllers.enities.Diagnose;
import com.yegorf.controllers.enities.Match;
import com.yegorf.controllers.enities.Matches;
import com.yegorf.controllers.enities.Symptome;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MatchesRepo extends CrudRepository<Matches, Long> {
    ArrayList<Matches> findAllByDiagnose(Diagnose diagnose);
}
