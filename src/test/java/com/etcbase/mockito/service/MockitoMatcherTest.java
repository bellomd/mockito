package com.etcbase.mockito.service;

import static org.hamcrest.CoreMatchers.notNullValue;

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

public class MockitoMatcherTest {

	@Mock
	private EtcBaseDao etcBaseDao;

	@InjectMocks
	private EtcBaseService etcBaseService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Using matcher to satisfy dependencies.
	 */
	@Test
	public void successCreateNewBranch() {

		// Prepare the objects you need.
		final EtcBase etcBase = new EtcBase(); 

		// when
		Mockito.when(etcBaseDao.save(Mockito.any(EtcBase.class))).thenReturn(etcBase); // then

		// We then call our service
		final EtcBase savedEtcBase = etcBaseService.save(etcBase);

		// Assert
		Assert.assertThat(savedEtcBase, CoreMatchers.is(notNullValue()));
	}
	
	/**
	 * Using Answer to create and return an object.
	 */
	@Test
	public void successCreateNewBranchUsingAnswer() {

		// Prepare the objects you need.
		final EtcBase etcBase = new EtcBase(); // we are passing an empty instance

		Mockito.when(etcBaseDao.save(Mockito.any(EtcBase.class))).thenAnswer(new Answer<EtcBase>() {

			// Prepare an answer
			@Override
			public EtcBase answer(InvocationOnMock invocationOnMock) throws Throwable {

				Object[] arguments = invocationOnMock.getArguments();

				if (arguments != null && arguments.length > 0 && arguments[0] != null) {

					final EtcBase etcBase = (EtcBase) arguments[0]; // we are return instance with some values.
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
}
