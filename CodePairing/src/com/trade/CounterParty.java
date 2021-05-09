package com.trade;

public class CounterParty {

	private Integer counterPartyId;
	
	private String counterPartyName;
	
	

	public CounterParty(Integer counterPartyId, String counterPartyName) {
		super();
		this.counterPartyId = counterPartyId;
		this.counterPartyName = counterPartyName;
	}

	public Integer getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(Integer counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getCounterPartyName() {
		return counterPartyName;
	}

	public void setCounterPartyName(String counterPartyName) {
		this.counterPartyName = counterPartyName;
	}
	
	
}
