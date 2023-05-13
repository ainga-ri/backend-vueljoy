package com.example.vueljoy.repository;

import com.example.vueljoy.model.Player;
import com.example.vueljoy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findBySeat(String seat);
}
