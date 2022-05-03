package com.example.kafkaexample.repositories;

import com.example.kafkaexample.entities.Holder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HolderRepository extends JpaRepository<Holder, UUID> {}
