package com.etcbase.mockito.service;

import static org.hamcrest.CoreMatchers.notNullValue;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.etcbase.mockito.EtcBase;
import com.etcbase.mockito.dao.EtcBaseDao;

public class MockitoWhenThenReturnTest {

	@Mock
	private EtcBaseDao etcBaseDao;

	@InjectMocks
	private EtcBaseService etcBaseService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Using Mockito.when() and thenReturn() of Mockito to tests our EtcBaseService
	 * save method.
	 */
	@Test
	public void successCreateNewBranch() {

		// Prepare the objects you need.
		final EtcBase etcBase = new EtcBase(); // This can be mocked.

		// when
		Mockito.when(etcBaseDao.save(etcBase)).thenReturn(etcBase); // then

		// We then call our service
		final EtcBase savedEtcBase = etcBaseService.save(etcBase);

		// Assert
		Assert.assertThat(savedEtcBase, CoreMatchers.is(notNullValue()));
	}
}
