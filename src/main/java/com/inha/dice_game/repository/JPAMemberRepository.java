package com.inha.dice_game.repository;

import com.inha.dice_game.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAMemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m FROM Member m WHERE m.Id = :id AND m.Pw = :pw")
    List<Member> findByIdAndPw(@Param("id") String Id,@Param("pw") String Pw);
}
