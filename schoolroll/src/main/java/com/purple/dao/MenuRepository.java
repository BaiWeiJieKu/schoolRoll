package com.purple.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.purple.entities.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Override
    Page<Menu> findAll(Pageable pageable);

    List<Menu> findByType(Integer type);

    @Override
    <S extends Menu> S save(S s);

    @Override
    void deleteById(Integer integer);

}
