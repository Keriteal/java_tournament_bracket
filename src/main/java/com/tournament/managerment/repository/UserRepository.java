package com.tournament.managerment.repository;

import com.tournament.managerment.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDO, String> {
    //通过输入的userName获取user信息
    @Query(value = "FROM UserDO user WHERE user.userName = :userName")
    UserDO getUserByUserName(@Param("userName") String userName);

    //通过输入的userName和secretKey获取user信息
    @Query(value = "FROM UserDO user WHERE user.userName = :userName AND user.secretKey = :secretKey")
    UserDO getUserByNameAndPass(@Param("userName") String userName, @Param("secretKey") String secretKey);

    //通过userName从tournament_record表中获取记录,该用户主办的tournament
    @Query(value = "SELECT tour.tournamentId FROM TournamentDO tour, UserDO user WHERE tour.userId = user.userId AND user.userName = :userName")
    List<String> getHostedTournamentByUserName(@Param("userName") String userName);

    //获取userName对应的userId
    @Query(value = "SELECT user.userId FROM UserDO user WHERE user.userName = :userName")
    int getUserIdByUserName(@Param("userName") String userName);

}
