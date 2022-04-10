package com.revature.Track2gether.service;

import com.revature.Track2gether.model.Category;
import com.revature.Track2gether.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CategoryImp implements CategoryService {

    @Autowired
    private CategoryRepository catrepo;

    @Override
    public Category getCategoryById(int id) {
        Category newCat = catrepo.getById(id);
        return newCat;
    }
}
