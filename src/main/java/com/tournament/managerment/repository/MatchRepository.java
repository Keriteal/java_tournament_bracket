package com.tournament.managerment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tournament.managerment.entity.MatchDO;

@Repository
public interface MatchRepository extends JpaRepository<MatchDO, String> {
	//获取某队参加的所有tournamentId
	@Query(value = "SELECT M.tournamentId "
			+ "FROM MatchDO M "
			+ "WHERE M.teamOne = :teamName "
			+ "OR M.teamTwo = :teamName "
			+ "GROUP BY M.tournamentId ")
	List<String> getJoinedTournamentByTeam(@Param("teamName") String teamName);

	//通过tournamentId,round,table获取某一场的对战信息match
	@Query(value = "FROM MatchDO M WHERE M.tournamentId = :tourId AND M.matchRound = :roundId AND M.matchTable = :tableId")
	MatchDO findMatch(@Param("tourId") String tournamentId, @Param("roundId") Integer round,
			@Param("tableId") Integer table);

	//通过tournamentId获取这轮锦标赛的所有对战信息matches
	@Query(value = "FROM MatchDO M WHERE M.tournamentId = :tourId")
	List<MatchDO> findMatchByTournamentId(@Param("tourId") String tournamentId);

	//通过tournamentId获取这轮竞标赛的所有参赛队伍teams
	@Query(value = "(SELECT team_one FROM match_record WHERE tournament_id = :tourId )"
			+ " UNION "
			+ "(SELECT team_two FROM match_record WHERE tournament_id = :tourId )", nativeQuery = true)
	List<String> getTeamsByTournamentId(@Param("tourId") String tournamentId);

	//通过tournamentId获取这轮竞标赛最后一场对战信息match
	@Query(value = "FROM MatchDO M WHERE M.matchRound IN ( SELECT MAX(M.matchRound) FROM MatchDO M WHERE M.tournamentId = :tourId) AND M.tournamentId = :tourId")
	MatchDO getLastMatchByTournamentId(@Param("tourId") String tournamentId);

	//获取该tournament最后一场的轮次
	@Query(value = "SELECT MAX(M.matchRound) FROM MatchDO M WHERE M.tournamentId = :tournamentId")
	int getLastRoundByTournamentId(@Param("tournamentId") String tournamentId);

	//设置一场对战结果set result
	@Transactional
	@Modifying
	@Query(value = "UPDATE MatchDO M "
			+ "SET M.result = :result,"
			+ "M.status = 'FINISHED' "
			+ "WHERE M.tournamentId = :tourId "
			+ "AND M.matchRound = :roundId "
			+ "AND M.matchTable = :tableId "
			+ "AND M.status = 'READY' ")
	int setResult(@Param("tourId") String tournamentId, @Param("roundId") Integer round,
			@Param("tableId") Integer table,
			@Param("result") MatchDO.Result result);

	//添加某一场match的TeamOne队伍名
	@Transactional
	@Modifying
	@Query(value = "UPDATE MatchDO M "
			+ "SET M.teamOne = :teamName,"
			+ "M.status = ( CASE WHEN M.teamTwo IS NULL THEN 'PENDING' ELSE 'READY' END ) "
			+ "WHERE M.tournamentId = :tourId "
			+ "AND M.matchRound = :roundId "
			+ "AND M.matchTable = :tableId "
			+ "AND M.status = 'PENDING'")
	int setTeamOne(@Param("tourId") String tournamentId, @Param("roundId") Integer round,
			@Param("tableId") Integer table, @Param("teamName") String team);

	//添加某一场match的TeamTwo队伍名
	@Transactional
	@Modifying
	@Query(value = "UPDATE MatchDO M "
			+ "SET M.teamTwo = :teamName, "
			+ "M.status = ( CASE WHEN M.teamOne IS NULL THEN 'PENDING' ELSE 'READY' END ) "
			+ "WHERE M.tournamentId = :tourId "
			+ "AND M.matchRound = :roundId "
			+ "AND M.matchTable = :tableId "
			+ "AND M.status = 'PENDING'")
	int setTeamTwo(@Param("tourId") String tournamentId, @Param("roundId") Integer round,
			@Param("tableId") Integer table, @Param("teamName") String team);
}