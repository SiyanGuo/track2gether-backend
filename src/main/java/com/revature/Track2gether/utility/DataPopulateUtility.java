package com.revature.Track2gether.utility;

import com.revature.Track2gether.model.Category;
import com.revature.Track2gether.model.Transactiontype;
import com.revature.Track2gether.model.Users;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class DataPopulateUtility {
    @PersistenceContext
    private EntityManager em;


    @Transactional
    public void populateIntialData(){
        Transactiontype income =new Transactiontype();
        income.setType("income");
        em.persist(income);

        Transactiontype expense =new Transactiontype();
        expense.setType("expenses");
        em.persist(expense);

    //Category

    Category salary =new Category();
    salary.setCategoryname("salary");
    salary.setTranstype(income);
    em.persist(salary);

    Category investment =new Category();
    investment.setCategoryname("investment");
    investment.setTranstype(income);
    em.persist(investment);

    Category other =new Category();
    other.setCategoryname("other");
    other.setTranstype(income);
    em.persist(other);

    Category otherexp =new Category();
    otherexp.setCategoryname("other");
    otherexp.setTranstype(expense);
    em.persist(other);

    Category housing =new Category();
    housing.setCategoryname("housing");
    housing.setTranstype(expense);;
    em.persist(housing);

    Category utilities =new Category();
    utilities.setCategoryname("utilities");
    utilities.setTranstype(expense);;
    em.persist(utilities);

    Category food =new Category();
    food.setCategoryname("food");
    food.setTranstype(expense);
    em.persist(food);

    Category transportation =new Category();
    transportation.setCategoryname("transportation");
    transportation.setTranstype(expense);
    em.persist(transportation);

    Category clothing =new Category();
    clothing.setCategoryname("clothing");
    clothing.setTranstype(expense);
    em.persist(clothing);

    Users user1 = new Users();
    user1.setFirstname("soma");
    user1.setLastname("jan");
    user1.setEmail("soma.j8@gmail.com");
    user1.setPassword("soma123");
    em.persist(user1);

    Users user2 = new Users();
    user2.setFirstname("lixy");
    user2.setLastname("mat");
    user2.setEmail("abc.j8@gmail.com");
    user2.setPassword("lixy123");
    em.persist(user2);

    user2.setSpouseId(user1);
    user1.setSpouseId(user2);




}   }
