package com.example.scexcel.dao;

import com.example.scexcel.entity.PersonDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonJpa extends JpaRepository<PersonDo, String> {

}
