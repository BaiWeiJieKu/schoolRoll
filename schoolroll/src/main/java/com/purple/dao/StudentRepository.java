package com.purple.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.purple.entities.Student;
import com.purple.entities.User;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findStudentById(Integer id);

    Page<Student> findAllByNameLike(String name, Pageable pageable);

    Page<Student> findAllBySpeciality_Name(String name, Pageable pageable);

    @Override
    <S extends Student> S save(S s);
    
    @Override
    void deleteById(Integer integer);
    // 查询所有通过审核且未毕业的学生
    Page<Student> findAllByUserIn(Collection<User> users, Pageable pageable);
    
    Page<Student> findAllByUserInAndOperatorAndPassNot(Collection<User> users, int operator, int pass ,Pageable pageable);
    
    Page<Student> findAllByUserInAndPassLessThanAndPassGreaterThanEqualAndIsStateAndOperatorNot(Collection<User> users, int min, int max, int isState, int operator, Pageable pageable);
    
    List<Student> findAllByIdIn(Collection<Integer> ids);
    
}
