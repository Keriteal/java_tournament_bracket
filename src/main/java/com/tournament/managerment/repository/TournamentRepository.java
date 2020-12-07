package com.tournament.managerment.repository;

import com.tournament.managerment.entity.TournamentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<TournamentDO, String> {

    //获取所有tournamentId
    @Query(value = "SELECT tour.tournamentId FROM TournamentDO tour")
    List<String> getTournamentIdList();

    //获取所有tournamentId对应的赛制format
    @Query(value = "SELECT tour.format FROM TournamentDO tour")
    List<String> getFormatList();

    //获取某个tournamentId对应的format
    @Query(value = "SELECT tour.format FROM TournamentDO tour WHERE tour.tournamentId = :tournamentId")
    String getFormatByTournamentId(@Param("tournamentId") String tournamentId);
}
