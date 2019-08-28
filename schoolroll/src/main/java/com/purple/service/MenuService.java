package com.purple.service;

import com.purple.dao.MenuRepository;
import com.purple.entities.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {

    @Resource
    private MenuRepository menuRepository;

    public Page<Menu> findAll (Pageable pageable) {
        return menuRepository.findAll(pageable);
    }
    public void save (Menu menu) {
        menuRepository.save(menu);
    }
    public void deleteById (Integer id) {
        menuRepository.deleteById(id);
    }
    public List<Menu> findByType(Integer type) {
        return menuRepository.findByType(type);
    }

}
