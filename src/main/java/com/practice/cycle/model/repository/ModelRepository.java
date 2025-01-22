package com.practice.cycle.model.repository;

import com.practice.cycle.model.entity.Gender;
import com.practice.cycle.model.entity.Model;
import com.practice.cycle.model.repository.custom.ModelRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model,Long>, ModelRepositoryCustom {
}
