package com.etcbase.mockito.service;

import static org.hamcrest.CoreMatchers.notNullValue;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.etcbase.mockito.EtcBase;
import com.etcbase.mockito.dao.EtcBaseDao;

public class EtcBaseServiceWithVerifyTest {

	// Mocking and Injecting our mocks dependencies.

	@Mock
	private EtcBaseDao etcBaseDao;

	@InjectMocks
	private EtcBaseService etcBaseService;
	
	@Captor
	private ArgumentCaptor<EtcBase> etcBaseArgumentCaptor;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Using Mockito.verify() to test methods
	 */
	@Test
	public void shouldSaveAndReturnNewBranch() {

		// Prepare the needed objects
		final EtcBase etcBase = new EtcBase();

		Mockito.when(etcBaseDao.save(etcBase)).thenReturn(etcBase);

		final EtcBase saveEtcBase = etcBaseService.save(etcBase);

		Assert.assertThat(saveEtcBase, CoreMatchers.is(etcBase));

		// Verify that the method was called
		Mockito.verify(etcBaseDao).save(etcBase);

		// verify that the method is called only one time
		Mockito.verify(etcBaseDao, Mockito.times(1)).save(etcBase);

		// Verify that another method has not been called
		Mockito.verify(etcBaseDao, Mockito.never()).update(etcBase);
	}

	/**
	 * In this test, because every EtcBase instance has to have an Id, before 
	 * persisting the object, here we want to capture the argument that is passed
	 * to generate id when it's called while saving an object.
	 */
	@Test
	public void shouldCheckIdentificationBeforeSaving() {

		// Prepare the needed objects
		final EtcBase etcBase = new EtcBase();
		
		Mockito.when(etcBaseDao.save(etcBase)).thenReturn(etcBase);
		
		final EtcBase savedEtcBase = etcBaseService.save(etcBase);
		
		Mockito.verify(etcBaseDao).checkId(etcBaseArgumentCaptor.capture());
		
		Assert.assertNotNull(savedEtcBase);
		Assert.assertNotNull(savedEtcBase.getId());
		Assert.assertThat(etcBaseArgumentCaptor.getValue().getId(), CoreMatchers.is(notNullValue()));
	}
}
