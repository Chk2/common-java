package com.github.chk2.common.crud;

import com.github.chk2.common.crud.doubles.AnEntity;
import com.github.chk2.common.crud.repository.CRUDRepository;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public abstract class AbstractCRUDTest implements CrudTest {
    protected AnEntity element1;
    protected AnEntity element2;
    protected AnEntity element3;

    protected CRUDRepository<AnEntity, Integer> givenNoElementDao;
    protected CRUDRepository<AnEntity, Integer> givenOneElementDao;
    protected CRUDRepository<AnEntity, Integer> givenSeveralElementDao;

    public abstract CRUDRepository<AnEntity, Integer> getDaoInstance();

    @Before
    public void given() throws Exception {
        element1 = new AnEntity(ELEMENT_ID_1, ELEMENT_VALUE_1);
        element2 = new AnEntity(ELEMENT_ID_2, ELEMENT_VALUE_2);
        element3 = new AnEntity(ELEMENT_ID_3, ELEMENT_VALUE_3);

        givenNoElementDao = getDaoInstance();
        assertEquals(0, givenNoElementDao.findAll().size());

        givenOneElementDao = getDaoInstance();
        givenOneElementDao.save(element1);
        assertEquals(1, givenOneElementDao.findAll().size());

        givenSeveralElementDao = getDaoInstance();
        givenSeveralElementDao.save(element1);
        givenSeveralElementDao.save(element2);
        givenSeveralElementDao.save(element3);
        assertEquals(3, givenSeveralElementDao.findAll().size());
    }
}
