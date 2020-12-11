package com.tournament.managerment.repository;

import com.tournament.managerment.entity.TournamentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.security.PermitAll;
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

    //通过tournamentId获取该锦标赛信息
    @Query(value = "FROM TournamentDO tour WHERE tour.tournamentId = :tournamentId")
    TournamentDO getTournamentByTournamentId(@Param("tournamentId") String tournamentId);

    //通过userName和tournamentId获取其对应的Tournament对象
    @Query(value = "FROM TournamentDO tour, UserDO user WHERE tour.userId = user.userId AND tour.tournamentId = :tournamentId AND user.userName = :userName")
    TournamentDO getTournamentByUserNameAndTournamentId(@Param("tournamentId") String tournamentId, @Param("userName") String userName);
}
