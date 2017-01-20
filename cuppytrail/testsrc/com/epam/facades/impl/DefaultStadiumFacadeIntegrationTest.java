/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2017 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.epam.facades.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.epam.data.StadiumData;
import com.epam.facades.StadiumFacade;
import com.epam.model.StadiumModel;


/**
 * This test file tests and demonstrates the behavior of the StadiumFacade's methods getAllStadiums and getStadium.
 */
public class DefaultStadiumFacadeIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private StadiumFacade stadiumFacade;

	@Resource
	private ModelService modelService;

	private StadiumModel stadiumModel;
	private final String STADIUM_NAME = "wembley";
	private final Integer STADIUM_CAPACITY = Integer.valueOf(90000);

	@Before
	public void setUp()
	{
		// This instance of a StadiumModel will be used by the tests
		stadiumModel = new StadiumModel();
		stadiumModel.setCode(STADIUM_NAME);
		stadiumModel.setCapacity(STADIUM_CAPACITY);
	}

	/**
	 * Tests exception behavior by getting a stadium which doesn't exist
	 */
	@Test(expected = UnknownIdentifierException.class)
	public void testInvalidParameter()
	{
		stadiumFacade.getStadium(STADIUM_NAME);
	}

	/**
	 * Tests exception behavior by passing in a null parameter
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullParameter()
	{
		stadiumFacade.getStadium(null);
	}

	/**
	 * Tests and demonstrates the Facade's methods
	 */
	@Test
	public void testStadiumFacade()
	{
		List<StadiumData> stadiumListData = stadiumFacade.getStadiums();
		assertNotNull(stadiumListData);
		final int size = stadiumListData.size();
		modelService.save(stadiumModel);

		stadiumListData = stadiumFacade.getStadiums();
		assertNotNull(stadiumListData);
		assertEquals(size + 1, stadiumListData.size());
		assertEquals(STADIUM_NAME, stadiumListData.get(size).getName());
		assertEquals(STADIUM_CAPACITY.toString(), stadiumListData.get(size).getCapacity());

		final StadiumData persistedStadiumData = stadiumFacade.getStadium(STADIUM_NAME);
		assertNotNull(persistedStadiumData);
		assertEquals(STADIUM_NAME, persistedStadiumData.getName());
		assertEquals(STADIUM_CAPACITY.toString(), persistedStadiumData.getCapacity());
	}
}
