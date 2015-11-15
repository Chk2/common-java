package com.github.chk2.common.crud;

import com.github.chk2.common.crud.doubles.AnEntity;
import com.github.chk2.common.crud.repository.CRUDRepository;

public interface CrudTest {

	String ELEMENT_VALUE_1 = "one";
	String ELEMENT_VALUE_2 = "two";
	String ELEMENT_VALUE_3 = "three";

	String FIRST_ENTITY_TEXT = "First entity";

	int ELEMENT_ID_1 = 1;
	int ELEMENT_ID_2 = 2;
	int ELEMENT_ID_3 = 3;

	int FIRST_ENTITY_ID = 100;

	String UPDATED_VALUE = "updated";

	CRUDRepository<AnEntity, Integer> getDaoInstance();

	interface QuarterCrudTest {

		void givenNoElements();

		void givenOneExistingElement();

		void givenSeveralExistingElements();
	}
}