package com.purple.dao;

import com.purple.entities.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {

    Speciality findOneById(Integer id);

    @Override
    Page<Speciality> findAll(Pageable pageable);

    List<Speciality> findAll();

    @Override
    <S extends Speciality> S save(S s);

    @Override
    void deleteById(Integer integer);

    List<Speciality> findAllByIdIn(Collection<Integer> id);

}
