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

import de.hybris.platform.cuppy.model.MatchModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.epam.data.MatchSummaryData;
import com.epam.data.StadiumData;
import com.epam.facades.StadiumFacade;
import com.epam.model.StadiumModel;
import com.epam.service.StadiumService;


public class DefaultStadiumFacade implements StadiumFacade
{
	private StadiumService stadiumService;

	@Override
	public List<StadiumData> getStadiums()
	{
		final List<StadiumModel> stadiumModels = stadiumService.getStadiums();
		final List<StadiumData> stadiumFacadeData = new ArrayList<StadiumData>();
		for (final StadiumModel sm : stadiumModels)
		{
			final StadiumData sfd = new StadiumData();
			sfd.setName(sm.getCode());
			sfd.setCapacity(sm.getCapacity().toString());
			stadiumFacadeData.add(sfd);
		}
		return stadiumFacadeData;
	}

	@Override
	public StadiumData getStadium(final String name)
	{
		StadiumModel stadium = null;
		if (name != null)
		{
			stadium = stadiumService.getStadiumForCode(name);
			if (stadium == null)
			{
				return null;
			}
		}
		else
		{
			throw new IllegalArgumentException("Stadium with name " + name + " not found.");
		}

		// Create a list of MatchSummaryData from the matches
		final List<MatchSummaryData> matchSummary = new ArrayList<MatchSummaryData>();
		if (stadium.getMatches() != null)
		{
			final Iterator<MatchModel> matchesIterator = stadium.getMatches().iterator();

			while (matchesIterator.hasNext())
			{
				final MatchModel match = matchesIterator.next();
				final MatchSummaryData summary = new MatchSummaryData();
				final String homeTeam = match.getHomeTeam().getName();
				final String guestTeam = match.getGuestTeam().getName();
				final Date matchDate = match.getDate();
				// If no goals are specified provide a user-friendly "-" instead of null
				final String guestGoals = match.getGuestGoals() == null ? "-" : match.getGuestGoals().toString();
				final String homeGoals = match.getHomeGoals() == null ? "-" : match.getHomeGoals().toString();
				summary.setHomeTeam(homeTeam);
				summary.setGuestTeam(guestTeam);
				summary.setDate(matchDate);
				summary.setGuestGoals(guestGoals);
				summary.setHomeGoals(homeGoals);
				final String formattedDate = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(matchDate);
				final String matchSummaryFormatted = homeTeam + ":( " + homeGoals + " ) " + guestTeam + " ( " + guestGoals + " ) "
						+ formattedDate;
				summary.setMatchSummaryFormatted(matchSummaryFormatted);
				matchSummary.add(summary);
			}
		}
		// Now we can create the StadiumData transfer object
		final StadiumData stadiumData = new StadiumData();
		stadiumData.setName(stadium.getCode());
		stadiumData.setCapacity(stadium.getCapacity().toString());
		stadiumData.setMatches(matchSummary);
		return stadiumData;
	}

	@Required
	public void setStadiumService(final StadiumService stadiumService)
	{
		this.stadiumService = stadiumService;
	}
}
