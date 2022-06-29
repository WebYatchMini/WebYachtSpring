package com.inha.dice_game.DAO;

import com.inha.dice_game.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAMemberRepository extends JpaRepository<Member,Long> {

    boolean existsByuid(String u_id);

    boolean existsBynickname(String nickname);

    @Query("SELECT m FROM Member m WHERE m.id =:id")
    Member findByu_id(@Param("id")String id);
}
