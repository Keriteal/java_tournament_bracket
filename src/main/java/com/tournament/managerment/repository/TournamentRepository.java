package com.tournament.managerment.repository;

import com.tournament.managerment.entity.TournamentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<TournamentDO, String> {

}
