package com.revature.Track2gether.integration.repository;

import com.revature.Track2gether.model.Category;
import com.revature.Track2gether.model.Transactiontype;
import com.revature.Track2gether.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository catRepo;

    @Test
    public void test_findByCategoryByTranstype() {

        List<Category> actual = catRepo.findByCategoryBytranstype(1);

        Transactiontype newTransType = new Transactiontype();
        newTransType.setId(1);
        newTransType.setType("income");

        Category newCat = new Category();
        newCat.setId(1);
        newCat.setCategoryname("salary");
        newCat.setTranstype(newTransType);

        Category newCat1 = new Category();
        newCat1.setId(2);
        newCat1.setCategoryname("investment");
        newCat1.setTranstype(newTransType);

        Category newCat2 = new Category();

        newCat2.setId(3);
        newCat2.setCategoryname("other");
        newCat2.setTranstype(newTransType);


        List<Category> expected = new ArrayList<>();
        expected.add(newCat);
        expected.add(newCat1);
        expected.add(newCat2);

        Assertions.assertEquals(expected, actual);

    }
}
