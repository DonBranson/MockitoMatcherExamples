package com.penwag.test;

import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;

public abstract class AssertConvertingArgumentMatcher<T> extends ArgumentMatcher<T> {

	private String failure = null;
	
	@Override
	public void describeTo(Description description) {
		description.appendText(failure);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean matches(Object argument) {
		try {
			verify((T) argument);
		} catch (AssertionError e) {
			failure = e.getMessage();
			return false;
		}
		return true;
	}

	protected abstract void verify(T argument);
}