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
package com.epam.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;


/**
 * It is part of cuppytrail validation.<br/>
 * Defines custom validator.
 */
public class NotEmptyCuppyValidator implements ConstraintValidator<NotEmptyCuppy, String>
{
	@Override
	public void initialize(final NotEmptyCuppy constraintAnnotation)
	{
		// empty
	}

	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context)
	{
		return value != null && !StringUtils.isEmpty(value.trim()) && value.matches(".*[a-zA-Z].*");
	}
}