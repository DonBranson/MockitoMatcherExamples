package com.penwag.test;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;

import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MyClass_UsualApproach_UT {

	@Mock private MyDelegate mockDelegate;
	
	@InjectMocks private MyClass targetObject = new MyClass();
	
	@Test
	public void testThat_StateChange_Delegates() throws Exception {
		int expectedValue = 5;

		targetObject.nextState();
		
		verify(mockDelegate).execute(argThat(new IsPopulatedDataHolder(expectedValue)));
	}
	
	class IsPopulatedDataHolder extends ArgumentMatcher<DataHolder> {

		private final int expectedValue;
		private String failure;

		public IsPopulatedDataHolder(int expectedValue) {
			this.expectedValue = expectedValue;
		}

		@Override
		public boolean matches(Object argument) {
			DataHolder holder = (DataHolder) argument;
			if(holder.getValue() != expectedValue) {
				failure = "Held value does not match.  Expected " + expectedValue + " but was " + holder.getValue();
				return false;
			}
			return true;
		}
		
		@Override
		public void describeTo(Description description) {
			description.appendText(":" + failure);
		}
	}
}
