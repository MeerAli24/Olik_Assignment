package com.olik.rentalSystem.Controller;

public class BookingRequest {
	  private Long productId;
	    private int durationInDays;
		public BookingRequest(Long productId, int durationInDays) {
			super();
			this.productId = productId;
			this.durationInDays = durationInDays;
		}
		public Long getProductId() {
			return productId;
		}
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		public int getDurationInDays() {
			return durationInDays;
		}
		public void setDurationInDays(int durationInDays) {
			this.durationInDays = durationInDays;
		}
		public BookingRequest() {
			super();
		}

	    
	    
}
