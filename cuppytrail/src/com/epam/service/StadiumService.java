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
package com.epam.service;

import java.util.List;

import com.epam.model.StadiumModel;


public interface StadiumService
{
	/**
	 * Gets all stadiums of the system.
	 *
	 * @return all stadiums of system
	 */
	List<StadiumModel> getStadiums();

	/**
	 * Gets the stadium for given code.
	 *
	 * @throws de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException
	 *            in case more then one stadium for given code is found
	 * @throws de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException
	 *            in case no stadium for given code can be found
	 */
	StadiumModel getStadiumForCode(String code);

	/**
	 * Gets the url for an image with the given format
	 *
	 * @param format
	 *           format to be taken to identify the image
	 * @throws de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException
	 *            in case no format can be found
	 * @throws de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException
	 *            in case more than one format is found
	 * @throws IllegalArgumentException
	 *            if given <code>format</code> is null
	 */
	String getImageUrlFromStadium(StadiumModel stadium, String format);

}