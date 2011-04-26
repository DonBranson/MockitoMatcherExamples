package com.penwag.test;

public class MyClass {

	private MyDelegate delegate;
	
	public void setDelegate(MyDelegate delegate) {
		this.delegate = delegate;
	}

	public void nextState() {
		delegate.execute(new DataHolder(10));
	}
}