package com.purple.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.purple.dao.SpecialityRepository;
import com.purple.entities.Speciality;
import com.purple.entities.User;

@Service
public class SpecialityService {

    @Resource
    private SpecialityRepository specialityRepository;
    @Resource
    private UserService userService;

    public Page<Speciality> findAll (Pageable pageable) {
        return specialityRepository.findAll(pageable);
    }

    public List<Speciality> findAll() {
        return specialityRepository.findAll();
    }

    public void save (Speciality speciality, Integer type) {
        speciality.setCreateTime(new Date());
        System.out.println(speciality);
        specialityRepository.save(speciality);
    }

    public void delete (Integer id, Integer type) {
        specialityRepository.deleteById(id);
    }

    public List<Speciality> findSpecialityById(Collection id) {
        return specialityRepository.findAllByIdIn(id);
    }

    public void update(User user, Integer[] specialities) {
        User u = userService.findById(user.getId());
        Set<Speciality> specialitySet = u.getSpeciality();
        List<Speciality> specialityList = specialityRepository.findAllByIdIn(Arrays.asList(specialities));
        specialitySet.clear();
        specialitySet.addAll(specialityList);
        u.setSpeciality(specialitySet);
        userService.save(u);
    }
}
