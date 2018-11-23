package com.ole.rentalstore.business.service.page_strategy;

public enum PageStrategyFactory {
	
	WEB_GET_MOVIES(20, new PageWebGetMovies()),
	MOBILE_GET_MOVIES(10, new PageMobileGetMovies()),
	WEB_SIMILAR_MOVIES(8, new PageWebSimilarMovies()),
	MOBILE_SIMILAR_MOVIES(5, new PageMobileSimilarMovies());
	
	private Integer amount;
	private PageStrategy pageStrategy;
	
	PageStrategyFactory(Integer amount, PageStrategy pageStrategy) {
		this.amount = amount;
		this.pageStrategy = pageStrategy;
	}

	public Integer getAmount() {
		return amount;
	}
	
	public PageStrategy getPageStrategy() {
		return pageStrategy;
	}
	
	public static PageStrategy getPageStrategyByAmount(Integer amount) {
		for (PageStrategyFactory pageStrategyFactory : PageStrategyFactory.values()) {
			if (pageStrategyFactory.getAmount().equals(amount)) {
				return pageStrategyFactory.getPageStrategy();
			}
		}
		return null;
	}
}