package com.inha.dice_game.DAO;

import com.inha.dice_game.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JPAMemberRepository extends JpaRepository<Member,Long> {

    boolean existsByuid(String u_id);

    boolean existsBynickname(String nickname);

    Member findByuid(String id);
}
