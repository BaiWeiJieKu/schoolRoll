package com.purple.dao;

import com.purple.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    Page<User> findAll(Pageable pageable);

    User findUserByNoAndPassword(String no, String password);

    List<User> findAllByNoNotAndTypeNot(String no, Integer type);

    Page<User> findAllByNoNot(String no, Pageable pageable);

    @Override
    <S extends User> S save(S s);

    @Override
    void deleteById(Integer integer);

    User findUserByNo(String no);

    User findUserById(Integer id);

    List<User> findUserByType(Integer type);
    
    List<User> findUserByParentId(Integer id); 

    List<User> findAllByParentIdIn(Collection<Integer> parentIds);

    List<User> findUserByNoNotAndTypeGreaterThan(String no, Integer type);
}
