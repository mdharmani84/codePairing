package com.trade;
import java.util.Date;

public class Trade {
	
	String tradeId;
	Integer version;
	CounterParty counterParty;
    String bookId;
    Date maturityDate;
    Date createdDate;
    String expired;
    
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public Date getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
	public CounterParty getCounterParty() {
		return counterParty;
	}
	public void setCounterParty(CounterParty counterParty) {
		this.counterParty = counterParty;
	}
	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", version=" + version + ", counterParty=" + counterParty + ", bookId="
				+ bookId + ", maturityDate=" + maturityDate + ", createdDate=" + createdDate + ", expired=" + expired
				+ "]";
	}
	    
}
