package com.ole.rentalstore.business.service.pagehandler;

public class PageHandlerBuilder {
	
	public static PageHandler buildChain() {
		PageHandler p1 = new PageMobile();
		PageHandler p2 = new PageWeb();
		p1.setNext(p2);
		return p1;
	}
}