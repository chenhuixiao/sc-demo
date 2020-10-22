package com.example.scthread.dao;

import com.example.scthread.entity.PersonDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpa extends JpaRepository<PersonDo, String> {

}
