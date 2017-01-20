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
package com.epam.daos;

import java.util.List;

import com.epam.model.StadiumModel;


/**
 * This interface belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial
 * An interface for the Stadium DAO. This incorporates the CRUD functionality we require for our DAO tests to pass.
 */
public interface StadiumDAO
{
	/**
	 * Return a list of stadium models that are currently persisted. If none are found an empty list is returned.
	 *
	 * @return all Stadiums of system
	 */
	List<StadiumModel> findStadiums();

	/**
	 * Finds all stadiums with given code. If none is found, an empty list will be returned.
	 *
	 * @param code
	 *           the code to search for stadiums
	 * @return All stadiums with the given code.
	 */
	List<StadiumModel> findStadiumsByCode(String code);
}