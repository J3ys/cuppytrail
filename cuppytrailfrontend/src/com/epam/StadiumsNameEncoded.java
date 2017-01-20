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
package com.epam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;


public class StadiumsNameEncoded
{
	private final static Logger LOG = Logger.getLogger(StadiumsNameEncoded.class);

	public static String getNameEncoded(final String name)
	{
		try
		{
			return URLEncoder.encode(name, "UTF-8");
		}
		catch (final UnsupportedEncodingException e)
		{
			LOG.error("Problems while encoding", e);
			return "";
		}
	}
}
