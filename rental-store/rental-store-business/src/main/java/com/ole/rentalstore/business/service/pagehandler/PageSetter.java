package com.ole.rentalstore.business.service.pagehandler;

public enum PageSetter {
	
	WEB_GET_MOVIES(20, new PageWebGetMovies()),
	MOBILE_GET_MOVIES(10, new PageMobileGetMovies()),
	WEB_SIMILAR_MOVIES(8, new PageWebSimilarMovies()),
	MOBILE_SIMILAR_MOVIES(5, new PageMobileSimilarMovies());
	
	private Integer amount;
	private PageHandler pageHandler;
	
	PageSetter(Integer amount, PageHandler pageHandler) {
		this.amount = amount;
		this.pageHandler = pageHandler;
	}

	public Integer getAmount() {
		return amount;
	}
	
	public PageHandler getPageHandler() {
		return pageHandler;
	}
	
	public static PageHandler getPageHandlerByAmount(Integer amount) {
		for (PageSetter pageSetter : PageSetter.values()) {
			if (pageSetter.getAmount().equals(amount)) {
				return pageSetter.getPageHandler();
			}
		}
		return null;
	}
}