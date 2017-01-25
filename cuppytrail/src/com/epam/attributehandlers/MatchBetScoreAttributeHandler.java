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
package com.epam.attributehandlers;

/**
 *
 */
import de.hybris.platform.cuppy.model.MatchBetModel;
import de.hybris.platform.cuppy.services.MatchService;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MatchBetScoreAttributeHandler extends AbstractDynamicAttributeHandler<Integer, MatchBetModel>
{

	@Autowired
	private MatchService matchService;

	@Override
	public Integer get(final MatchBetModel model)
	{
		try
		{
			return Integer.valueOf(matchService.getScore(model));
		}
		catch (final IllegalStateException e)
		{
			return null;
		}
	}

	public void setMatchService(final MatchService matchService)
	{
		this.matchService = matchService;
	}
}