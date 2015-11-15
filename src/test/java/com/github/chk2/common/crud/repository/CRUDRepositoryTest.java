package com.github.chk2.common.crud.repository;

import com.github.chk2.common.crud.AbstractCRUDTest;
import com.github.chk2.common.crud.doubles.AnEntity;
import com.github.chk2.common.crud.CrudTest;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
public class CRUDRepositoryTest extends AbstractCRUDTest implements CrudTest {

    @Override
    public CRUDRepository<AnEntity, Integer> getDaoInstance() {
        return new InMemoryCRUDRepository<>();
    }

    @Before
    public void setUp() throws Exception {
    }

    public class Create implements QuarterCrudTest {

        @Test
        @Override
        public void givenNoElements() {
            givenNoElementDao.save(new AnEntity(FIRST_ENTITY_ID, FIRST_ENTITY_TEXT));

            List<AnEntity> all = givenNoElementDao.findAll();
            assertEquals(1, all.size());
            assertEquals(FIRST_ENTITY_ID, (int) all.get(0).getId());
            assertEquals(FIRST_ENTITY_TEXT, all.get(0).getTxt());
        }

        @Test
        @Override
        public void givenOneExistingElement() {
            givenOneElementDao.save(new AnEntity(FIRST_ENTITY_ID, FIRST_ENTITY_TEXT));

            List<AnEntity> all = givenOneElementDao.findAll();
            assertEquals(2, all.size());
            assertEquals(ELEMENT_ID_1, (int) all.get(0).getId());
            assertEquals(ELEMENT_VALUE_1, all.get(0).getTxt());
            assertEquals(FIRST_ENTITY_ID, (int) all.get(1).getId());
            assertEquals(FIRST_ENTITY_TEXT, all.get(1).getTxt());
        }

        @Test
        @Override
        public void givenSeveralExistingElements() {
            givenSeveralElementDao.save(new AnEntity(FIRST_ENTITY_ID, FIRST_ENTITY_TEXT));

            List<AnEntity> all = givenSeveralElementDao.findAll();
            assertEquals(4, all.size());
            assertEquals(ELEMENT_ID_1, (int) all.get(0).getId());
            assertEquals(ELEMENT_VALUE_1, all.get(0).getTxt());
            assertEquals(ELEMENT_ID_2, (int) all.get(1).getId());
            assertEquals(ELEMENT_VALUE_2, all.get(1).getTxt());
            assertEquals(ELEMENT_ID_3, (int) all.get(2).getId());
            assertEquals(ELEMENT_VALUE_3, all.get(2).getTxt());
            assertEquals(FIRST_ENTITY_ID, (int) all.get(3).getId());
            assertEquals(FIRST_ENTITY_TEXT, all.get(3).getTxt());
        }
    }

    public class ReadOne implements QuarterCrudTest {

        @Test
        @Override
        public void givenNoElements() {
            AnEntity anEntity = givenNoElementDao.findById(ELEMENT_ID_1);
            assertNull(anEntity);
        }

        @Test
        @Override
        public void givenOneExistingElement() {
            AnEntity anEntity = givenOneElementDao.findById(ELEMENT_ID_1);
            assertEquals(ELEMENT_ID_1, (int) anEntity.getId());
            assertEquals(ELEMENT_VALUE_1, anEntity.getTxt());
        }

        @Test
        @Override
        public void givenSeveralExistingElements() {
            AnEntity anEntity = givenSeveralElementDao.findById(ELEMENT_ID_1);
            assertEquals(ELEMENT_ID_1, (int) anEntity.getId());
            assertEquals(ELEMENT_VALUE_1, anEntity.getTxt());
        }
    }

    public class ReadAll implements QuarterCrudTest {

        @Test
        @Override
        public void givenNoElements() {
            List<AnEntity> all = givenNoElementDao.findAll();

            assertEquals(0, all.size());
        }

        @Test
        @Override
        public void givenOneExistingElement() {
            List<AnEntity> all = givenOneElementDao.findAll();

            assertEquals(1, all.size());
            assertEquals(ELEMENT_ID_1, (int) all.get(0).getId());
            assertEquals(ELEMENT_VALUE_1, all.get(0).getTxt());
        }

        @Test
        @Override
        public void givenSeveralExistingElements() {
            List<AnEntity> all = givenSeveralElementDao.findAll();

            assertEquals(3, all.size());
            assertEquals(ELEMENT_ID_1, (int) all.get(0).getId());
            assertEquals(ELEMENT_VALUE_1, all.get(0).getTxt());
            assertEquals(ELEMENT_ID_2, (int) all.get(1).getId());
            assertEquals(ELEMENT_VALUE_2, all.get(1).getTxt());
            assertEquals(ELEMENT_ID_3, (int) all.get(2).getId());
            assertEquals(ELEMENT_VALUE_3, all.get(2).getTxt());
        }
    }

    public class Update implements QuarterCrudTest {

        @Test
        @Override
        public void givenNoElements() {
            assertNull(givenNoElementDao.findById(ELEMENT_ID_1));

            givenNoElementDao.save(new AnEntity(ELEMENT_ID_1, UPDATED_VALUE));

            List<AnEntity> all = givenNoElementDao.findAll();
            assertEquals(1, all.size());
            assertEquals(ELEMENT_ID_1, (int) all.get(0).getId());
            assertEquals(UPDATED_VALUE, all.get(0).getTxt());
        }

        @Test
        @Override
        public void givenOneExistingElement() {
            AnEntity existing = givenOneElementDao.findById(ELEMENT_ID_1);
            assertEquals(ELEMENT_ID_1, (int) existing.getId());
            assertEquals(ELEMENT_VALUE_1, existing.getTxt());

            givenOneElementDao.save(new AnEntity(ELEMENT_ID_1, UPDATED_VALUE));

            List<AnEntity> all = givenOneElementDao.findAll();
            assertEquals(1, all.size());
            assertEquals(ELEMENT_ID_1, (int) all.get(0).getId());
            assertEquals(UPDATED_VALUE, all.get(0).getTxt());
        }

        @Test
        @Override
        public void givenSeveralExistingElements() {
            AnEntity existing = givenSeveralElementDao.findById(ELEMENT_ID_1);
            assertEquals(ELEMENT_ID_1, (int) existing.getId());
            assertEquals(ELEMENT_VALUE_1, existing.getTxt());

            givenSeveralElementDao.save(new AnEntity(ELEMENT_ID_1, UPDATED_VALUE));

            List<AnEntity> all = givenSeveralElementDao.findAll();
            assertEquals(3, all.size());
            assertEquals(ELEMENT_ID_1, (int) all.get(0).getId());
            assertEquals(UPDATED_VALUE, all.get(0).getTxt());
        }
    }

    public class Delete implements QuarterCrudTest {

        @Test
        @Override
        public void givenNoElements() {
            givenNoElementDao.delete(ELEMENT_ID_1);
            assertNull(givenNoElementDao.findById(ELEMENT_ID_1));
        }

        @Test
        @Override
        public void givenOneExistingElement() {
            givenNoElementDao.delete(ELEMENT_ID_1);
            assertNull(givenNoElementDao.findById(ELEMENT_ID_1));
        }

        @Test
        @Override
        public void givenSeveralExistingElements() {
            givenNoElementDao.delete(ELEMENT_ID_1);
            assertNull(givenNoElementDao.findById(ELEMENT_ID_1));
        }
    }

    public class Disable implements QuarterCrudTest {

        @Test
        @Override
        public void givenNoElements() {
            givenNoElementDao.disable(ELEMENT_ID_1);
            assertNull(givenNoElementDao.findById(ELEMENT_ID_1));
        }

        @Test
        @Override
        public void givenOneExistingElement() {
            testDisable(givenOneElementDao);
        }

        @Test
        @Override
        public void givenSeveralExistingElements() {
            testDisable(givenSeveralElementDao);
        }

        private void testDisable(CRUDRepository<AnEntity, Integer> dao) {
            AnEntity existing = dao.findById(ELEMENT_ID_1);
            assertTrue(existing.isEnabled());

            dao.disable(ELEMENT_ID_1);

            AnEntity anEntity = dao.findById(ELEMENT_ID_1);
            assertTrue(anEntity.isDisabled());
        }
    }

    public class Enable implements QuarterCrudTest {

        @Test
        @Override
        public void givenNoElements() {
            givenNoElementDao.enable(ELEMENT_ID_1);
            assertNull(givenNoElementDao.findById(ELEMENT_ID_1));
        }

        @Test
        @Override
        public void givenOneExistingElement() {
            testEnable(givenOneElementDao);
        }

        @Test
        @Override
        public void givenSeveralExistingElements() {
            testEnable(givenOneElementDao);
        }

        private void testEnable(CRUDRepository<AnEntity, Integer> givenOneElementDao) {
            AnEntity existing = givenOneElementDao.findById(ELEMENT_ID_1);
            existing.disable();
            givenOneElementDao.save(existing);
            assertTrue(givenOneElementDao.findById(ELEMENT_ID_1).isDisabled());

            givenOneElementDao.enable(ELEMENT_ID_1);

            AnEntity anEntity = givenOneElementDao.findById(ELEMENT_ID_1);
            assertTrue(anEntity.isEnabled());
        }
    }
}
