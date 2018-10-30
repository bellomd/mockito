package com.etcbase.mockito.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Random;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.etcbase.mockito.EtcBase;
import com.etcbase.mockito.dao.EtcBaseDao;

public class EtcBaseServiceTest {

	// Mocking and Injecting our mocks dependencies.

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
	public void shouldSaveAndReturnNewBranch() {

		// Prepare the objects you need.
		final EtcBase etcBase = new EtcBase();

		// when
		Mockito.when(etcBaseDao.save(Mockito.any())).thenReturn(etcBase); // then

		// Mockito.doReturn(etcBaseService).when(etcBaseService).save(etcBase);

		// We then call our service
		final EtcBase savedEtcBase = etcBaseService.save(etcBase);

		// Assert
		Assert.assertThat(savedEtcBase, CoreMatchers.is(notNullValue()));
	}

	/**
	 * Using Answer to create and return an object.
	 */
	@Test
	public void shouldSaveAndReturnNewBranchUsingAnswer() {

		// Prepare the objects you need.
		final EtcBase etcBase = new EtcBase();

		Mockito.when(etcBaseDao.save(Mockito.any(EtcBase.class))).thenAnswer(new Answer<EtcBase>() {

			// Prepare an answer
			@Override
			public EtcBase answer(InvocationOnMock invocationOnMock) throws Throwable {

				Object[] arguments = invocationOnMock.getArguments();

				if (arguments != null && arguments.length > 0 && arguments[0] != null) {

					final EtcBase etcBase = (EtcBase) arguments[0];
					etcBase.setId(new Random().nextLong());
					etcBase.setBranchName("Maslak Branch");
					etcBase.setBranchAddress("ITU Ayazaga Maslak");

					return etcBase;
				}

				return null;
			}
		});

		// We then call our service
		final EtcBase savedEtcBase = etcBaseService.save(etcBase);

		// Assert
		Assert.assertThat(savedEtcBase, CoreMatchers.is(notNullValue()));
		Assert.assertThat("Maslak Branch", CoreMatchers.equalTo("Maslak Branch"));
	}

	/**
	 * Throwing an exception from a mocked method.
	 */
	@Test(expected = RuntimeException.class)
	public void shouldThrowRuntimeExceptionWhenSaveIsCalled() {

		// Prepare the objects you need.
		final EtcBase etcBase = new EtcBase();

		// when
		Mockito.when(etcBaseDao.save(Mockito.any())).thenThrow(RuntimeException.class); // then

		// Mockito.doReturn(etcBaseService).when(etcBaseService).save(etcBase);

		// 	We then call our service
		//	The above expected is watching our back, so we don't have to check for 
		//	whether the exception is thrown or not.
		etcBaseService.save(etcBase);
	}
	
	/**
	 * 	Mocking a void method using doAnswer. It's not used that much but this is an illustration 
	 * 	of the general usage.
	 */
	@Test
	public void shouldDeleteExistingEtcBaseBranch() {
		
		Mockito.doAnswer(new Answer<Void>() {
			
			@Override
			public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
				
				Object[] arguments = invocationOnMock.getArguments();

				if (arguments != null && arguments.length > 0 && arguments[0] != null) {

					final EtcBase etcBase = (EtcBase) arguments[0];
					etcBase.setId(new Random().nextLong());
					etcBase.setBranchName("Maslak Branch");
					etcBase.setBranchAddress("ITU Ayazaga Maslak");
				}

				return null;
			}
		}).when(etcBaseDao).delete(Mockito.any(Long.class));
		
		// Calling the service
		final EtcBase etcBase = etcBaseService.update(new EtcBase());
		
		// Asserting the result.
		Assert.assertThat(etcBase, CoreMatchers.is(nullValue()));
	}
	
	/**
	 * 	Throwing exception from a void method
	 */
	@Test(expected = RuntimeException.class)
	public void shouldThrowRuntimExceptionWhenDeleteIsCalled() {
		
		Mockito.doThrow(RuntimeException.class).when(etcBaseDao).delete(Mockito.anyLong());
		
		etcBaseService.delete(new Random().nextLong());
	}
}
