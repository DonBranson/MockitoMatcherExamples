package com.penwag.test;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MyClass_NewMatcher_UT {

	@Mock private MyDelegate mockDelegate;
	
	@InjectMocks private MyClass targetObject = new MyClass();
	
	@Test
	public void testThat_StateChange_Delegates() throws Exception {
		int expectedValue = 5;

		targetObject.nextState();
		
		verify(mockDelegate).execute(argThat(new IsPopulatedDataHolder(expectedValue)));
	}
	
	class IsPopulatedDataHolder extends AssertConvertingArgumentMatcher<DataHolder> {

		private final int expectedValue;

		public IsPopulatedDataHolder(int expectedValue) {
			this.expectedValue = expectedValue;
		}

		@Override
		protected void verify(DataHolder argument) {
			Assert.assertEquals("Held value", expectedValue, argument.getValue());
		}
	}
}
