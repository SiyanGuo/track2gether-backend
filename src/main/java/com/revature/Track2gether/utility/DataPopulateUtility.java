package com.revature.Track2gether.utility;

import com.revature.Track2gether.model.Category;
import com.revature.Track2gether.model.Transaction;
import com.revature.Track2gether.model.Transactiontype;
import com.revature.Track2gether.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataPopulateUtility {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void populateIntialData() throws ParseException {
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
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setEmail("john@gmail.com");
        user1.setPassword("john123");
        em.persist(user1);

        Users user2 = new Users();
        user2.setFirstname("Emma");
        user2.setLastname("John");
        user2.setEmail("emma@gmail.com");
        user2.setPassword("emma123");
        em.persist(user2);

        user2.setSpouseId(user1);
        user1.setSpouseId(user2);

        // transaction

        String marDate1 = "03/09/2022";
        Date marDt1 = new SimpleDateFormat("MM/dd/yyyy").parse(marDate1);

        Transaction marchTrans1 = new Transaction();
        marchTrans1.setAmount(1840.73);
        marchTrans1.setDate(marDt1);
        marchTrans1.setDescription("Bi-weekly wage direct deposit");
        marchTrans1.setShared(false);
        marchTrans1.setCategory(salary);
        marchTrans1.setUser(user1);
        em.persist(marchTrans1);


        String marDate2 = "03/23/2022";
        Date marDt2 = new SimpleDateFormat("MM/dd/yyyy").parse(marDate2);

        Transaction marchTrans2 = new Transaction();
        marchTrans2.setAmount(1840.73);
        marchTrans2.setDate(marDt2);
        marchTrans2.setDescription("Bi-weekly wage direct deposit");
        marchTrans2.setShared(false);
        marchTrans2.setCategory(salary);
        marchTrans2.setUser(user1);
        em.persist(marchTrans2);

        String marDate3 = "03/12/2022";
        Date marDt3 = new SimpleDateFormat("MM/dd/yyyy").parse(marDate3);

        Transaction marchTrans3 = new Transaction();
        marchTrans3.setAmount(278.67);
        marchTrans3.setDate(marDt3);
        marchTrans3.setDescription("Expense for the spring clothing");
        marchTrans3.setShared(false);
        marchTrans3.setCategory(clothing);
        marchTrans3.setUser(user1);
        em.persist(marchTrans3);

        String marDate4 = "03/13/2022";
        Date marDt4 = new SimpleDateFormat("MM/dd/yyyy").parse(marDate4);

        Transaction marchTrans4 = new Transaction();
        marchTrans4.setAmount(213.87);
        marchTrans4.setDate(marDt4);
        marchTrans4.setDescription("Grocery shopping");
        marchTrans4.setShared(false);
        marchTrans4.setCategory(food);
        marchTrans4.setUser(user1);
        em.persist(marchTrans4);

        String marDate5 = "03/16/2022";
        Date marDt5 = new SimpleDateFormat("MM/dd/yyyy").parse(marDate5);

        Transaction marchTrans5 = new Transaction();
        marchTrans5.setAmount(66.89);
        marchTrans5.setDate(marDt5);
        marchTrans5.setDescription("Uber to DC");
        marchTrans5.setShared(false);
        marchTrans5.setCategory(transportation);
        marchTrans5.setUser(user1);
        em.persist(marchTrans5);

        String marDate6 = "03/31/2022";
        Date marDt6 = new SimpleDateFormat("MM/dd/yyyy").parse(marDate6);

        Transaction marchTrans6 = new Transaction();
        marchTrans6.setAmount(138.87);
        marchTrans6.setDate(marDt6);
        marchTrans6.setDescription("Electricity Bill");
        marchTrans6.setShared(false);
        marchTrans6.setCategory(utilities);
        marchTrans6.setUser(user1);
        em.persist(marchTrans6);

        String marDate7 = "03/11/2022";
        Date marDt7 = new SimpleDateFormat("MM/dd/yyyy").parse(marDate7);

        Transaction marchTrans7 = new Transaction();
        marchTrans7.setAmount(2300.67);
        marchTrans7.setDate(marDt7);
        marchTrans7.setDescription("Bi Weekly Salary");
        marchTrans7.setShared(false);
        marchTrans7.setCategory(salary);
        marchTrans7.setUser(user2);
        em.persist(marchTrans7);

        String marDate8 = "03/25/2022";
        Date marDt8 = new SimpleDateFormat("MM/dd/yyyy").parse(marDate8);

        Transaction marchTrans8 = new Transaction();
        marchTrans8.setAmount(2300.67);
        marchTrans8.setDate(marDt8);
        marchTrans8.setDescription("Bi Weekly Salary");
        marchTrans8.setShared(false);
        marchTrans8.setCategory(salary);
        marchTrans8.setUser(user2);
        em.persist(marchTrans8);

        String marDate9 = "03/12/2022";
        Date marDt9 = new SimpleDateFormat("MM/dd/yyyy").parse(marDate9);

        Transaction marchTrans9 = new Transaction();
        marchTrans9.setAmount(67.89);
        marchTrans9.setDate(marDt9);
        marchTrans9.setDescription("Gas");
        marchTrans9.setShared(false);
        marchTrans9.setCategory(transportation);
        marchTrans9.setUser(user2);
        em.persist(marchTrans9);

        String marDate10 = "03/13/2022";
        Date marDt10 = new SimpleDateFormat("MM/dd/yyyy").parse(marDate10);

        Transaction marchTrans10 = new Transaction();
        marchTrans10.setAmount(137.63);
        marchTrans10.setDate(marDt10);
        marchTrans10.setDescription("Dinner");
        marchTrans10.setShared(false);
        marchTrans10.setCategory(food);
        marchTrans10.setUser(user2);
        em.persist(marchTrans10);



        String aprDate1 = "04/11/2022";
        Date aprDt1 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate1);

        Transaction aprTrans1 = new Transaction();
        aprTrans1.setAmount(389.99);
        aprTrans1.setDate(aprDt1);
        aprTrans1.setDescription("grocery shopping");
        aprTrans1.setShared(false);
        aprTrans1.setCategory(food);
        aprTrans1.setUser(user1);
        em.persist(aprTrans1);

        String aprDate2 = "04/02/2022";
        Date aprDt2 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate2);

        Transaction aprTrans2 = new Transaction();
        aprTrans2.setAmount(2160.58);
        aprTrans2.setDate(aprDt2);
        aprTrans2.setDescription("Rent for apartment");
        aprTrans2.setShared(true);
        aprTrans2.setCategory(housing);
        aprTrans2.setUser(user1);
        em.persist(aprTrans2);

        String aprDate3 = "04/06/2022";
        Date aprDt3 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate3);

        Transaction aprTrans3 = new Transaction();
        aprTrans3.setAmount(1840.73);
        aprTrans3.setDate(aprDt3);
        aprTrans3.setDescription("Bi-weekly wage direct deposit");
        aprTrans3.setShared(false);
        aprTrans3.setCategory(salary);
        aprTrans3.setUser(user1);
        em.persist(aprTrans3);

        String aprDate4 = "04/20/2022";
        Date aprDt4 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate4);

        Transaction aprTrans4 = new Transaction();
        aprTrans4.setAmount(1840.73);
        aprTrans4.setDate(aprDt4);
        aprTrans4.setDescription("Bi-weekly wage direct deposit");
        aprTrans4.setShared(false);
        aprTrans4.setCategory(salary);
        aprTrans4.setUser(user1);
        em.persist(aprTrans4);

        String aprDate5 = "04/08/2022";
        Date aprDt5 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate5);

        Transaction aprTrans5 = new Transaction();
        aprTrans5.setAmount(289.63);
        aprTrans5.setDate(aprDt5);
        aprTrans5.setDescription("Dinner at Sushi Ogawa");
        aprTrans5.setShared(false);
        aprTrans5.setCategory(food);
        aprTrans5.setUser(user1);
        em.persist(aprTrans5);

        String aprDate6 = "04/08/2022";
        Date aprDt6 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate6);

        Transaction aprTrans6 = new Transaction();
        aprTrans6.setAmount(5890.73);
        aprTrans6.setDate(aprDt6);
        aprTrans6.setDescription("Liquidated asset from my stock account");
        aprTrans6.setShared(false);
        aprTrans6.setCategory(investment);
        aprTrans6.setUser(user1);
        em.persist(aprTrans6);

        String aprDate7 = "04/08/2022";
        Date aprDt7 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate7);

        Transaction aprTrans7 = new Transaction();
        aprTrans7.setAmount(2300.67);
        aprTrans7.setDate(aprDt7);
        aprTrans7.setDescription("Bi Weekly Salary");
        aprTrans7.setShared(false);
        aprTrans7.setCategory(salary);
        aprTrans7.setUser(user2);
        em.persist(aprTrans7);

        String aprDate8 = "04/22/2022";
        Date aprDt8 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate8);

        Transaction aprTrans8 = new Transaction();
        aprTrans8.setAmount(2300.67);
        aprTrans8.setDate(aprDt8);
        aprTrans8.setDescription("Bi Weekly Salary");
        aprTrans8.setShared(false);
        aprTrans8.setCategory(salary);
        aprTrans8.setUser(user2);
        em.persist(aprTrans8);

        String aprDate9 = "04/30/2022";
        Date aprDt9 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate9);

        Transaction aprTrans9 = new Transaction();
        aprTrans9.setAmount(128.89);
        aprTrans9.setDate(aprDt9);
        aprTrans9.setDescription("Electric Bill");
        aprTrans9.setShared(false);
        aprTrans9.setCategory(utilities);
        aprTrans9.setUser(user2);
        em.persist(aprTrans9);

        String aprDate10 = "04/29/2022";
        Date aprDt10 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate10);

        Transaction aprTrans10 = new Transaction();
        aprTrans10.setAmount(378.89);
        aprTrans10.setDate(aprDt9);
        aprTrans10.setDescription("Spring clothing shopping");
        aprTrans10.setShared(false);
        aprTrans10.setCategory(clothing);
        aprTrans10.setUser(user2);
        em.persist(aprTrans10);

        String aprDate11 = "05/12/2022";
        Date aprDt11 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate11);

        Transaction aprTrans11 = new Transaction();
        aprTrans11.setAmount(2000);
        aprTrans11.setDate(aprDt11);
        aprTrans11.setDescription("New kitchen appliance");
        aprTrans11.setShared(false);
        aprTrans11.setCategory(utilities);
        aprTrans11.setUser(user1);
        em.persist(aprTrans11);

        String aprDate12 = "01/01/2022";
        Date aprDt12 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate12);

        Transaction aprTrans12 = new Transaction();
        aprTrans12.setAmount(1000);
        aprTrans12.setDate(aprDt12);
        aprTrans12.setDescription("New cloth from zara");
        aprTrans12.setShared(false);
        aprTrans12.setCategory(clothing);
        aprTrans12.setUser(user2);
        em.persist(aprTrans12);

        String aprDate13 = "01/22/2022";
        Date aprDt13 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate13);

        Transaction aprTrans13 = new Transaction();
        aprTrans13.setAmount(80);
        aprTrans13.setDate(aprDt13);
        aprTrans13.setDescription("took uber to airport");
        aprTrans13.setShared(false);
        aprTrans13.setCategory(transportation);
        aprTrans13.setUser(user2);
        em.persist(aprTrans13);

        String aprDate14 = "10/02/2022";
        Date aprDt14 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate14);

        Transaction aprTrans14 = new Transaction();
        aprTrans14.setAmount(1000);
        aprTrans14.setDate(aprDt14);
        aprTrans14.setDescription("party at home");
        aprTrans14.setShared(false);
        aprTrans14.setCategory(other);
        aprTrans14.setUser(user2);
        em.persist(aprTrans14);

        String aprDate15 = "11/02/2022";
        Date aprDt15 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate15);

        Transaction aprTrans15 = new Transaction();
        aprTrans15.setAmount(200);
        aprTrans15.setDate(aprDt15);
        aprTrans15.setDescription("got bonus");
        aprTrans15.setShared(false);
        aprTrans15.setCategory(salary);
        aprTrans15.setUser(user2);
        em.persist(aprTrans15);


        String aprDate16 = "11/02/2022";
        Date aprDt16 = new SimpleDateFormat("MM/dd/yyyy").parse(aprDate16);

        Transaction aprTrans16 = new Transaction();
        aprTrans16.setAmount(500);
        aprTrans16.setDate(aprDt16);
        aprTrans16.setDescription("birthday food ordered");
        aprTrans16.setShared(false);
        aprTrans16.setCategory(food);
        aprTrans16.setUser(user2);
        em.persist(aprTrans16);
    }
}
