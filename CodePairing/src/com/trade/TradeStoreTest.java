package com.trade;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TradeStoreTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTradeDateLessThenMaturityDate() throws Exception {
		List<Trade> trades = new ArrayList<Trade>();
		Trade trade = new Trade();
		trade.setTradeId("T1");
		trade.setVersion(1);
		trade.setCounterParty(new CounterParty(1, "CP1"));
		trade.setBookId("B1");
		SimpleDateFormat sdfCurr = new SimpleDateFormat("dd/MM/yyyy");
		String dateStr = sdfCurr.format(new Date());
		Date currDate = sdfCurr.parse(dateStr);
		trade.setMaturityDate(currDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);
		cal.add(Calendar.DATE, -30);
		Date dateBefore30Days = cal.getTime();
		trade.setCreatedDate(currDate);		
		trade.setMaturityDate(dateBefore30Days);
		TradeStore store = new TradeStore();
		trades.add(trade);
		store.storeTrade(trades);
		Trade storedtrade = store.getTrade(trade.getTradeId(), trade.getVersion());
		assertNull(storedtrade);		
	}	

	@Test
	void testTradeDateGreaterThenMaturityDate() throws Exception {
		List<Trade> trades = new ArrayList<Trade>();
		Trade trade = new Trade();
		trade.setTradeId("T1");
		trade.setVersion(1);
		trade.setCounterParty(new CounterParty(1, "CP1"));
		trade.setBookId("B1");		
		Calendar calCurrDate = Calendar.getInstance();
		calCurrDate.setTime(new Date());
		SimpleDateFormat sdfCurr = new SimpleDateFormat("dd/MM/yyyy");
		String dateStr = sdfCurr.format(new Date());
		Date currDate = sdfCurr.parse(dateStr);
		trade.setMaturityDate(currDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);
		cal.add(Calendar.DATE, 30);
		Date dateAfter30Days = cal.getTime();
		trade.setCreatedDate(dateAfter30Days);
		TradeStore store = new TradeStore();
		trades.add(trade);
		store.storeTrade(trades);
		Trade storedtrade = store.getTrade(trade.getTradeId(), trade.getVersion());
		assertNotNull(storedtrade);
		assertEquals("Y", storedtrade.getExpired());
	}
	
	@Test
	void testOverrideExistingVersion() throws Exception {
		List<Trade> trades = new ArrayList<Trade>();
		Trade trade1 = new Trade();
		trade1.setTradeId("T1");
		trade1.setVersion(1);
		trade1.setCounterParty(new CounterParty(1, "CP1"));
		trade1.setBookId("B1");		
		Calendar calCurrDate = Calendar.getInstance();
		calCurrDate.setTime(new Date());
		SimpleDateFormat sdfCurr = new SimpleDateFormat("dd/MM/yyyy");
		String dateStr = sdfCurr.format(new Date());
		Date currDate = sdfCurr.parse(dateStr);
		trade1.setMaturityDate(currDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);
		cal.add(Calendar.DATE, 30);
		//Date dateAfter30Days = cal.getTime();
		trade1.setCreatedDate(currDate);		
		TradeStore store = new TradeStore();
		trades.add(trade1);	
		Trade trade2 = new Trade();
		trade2.setTradeId("T1");
		trade2.setVersion(1);
		trade2.setCounterParty(new CounterParty(1, "CP1"));
		trade2.setBookId("B2");		
		trade2.setMaturityDate(currDate);
		trade2.setCreatedDate(currDate);		
		trades.add(trade2);			
		store.storeTrade(trades);
		Trade storedtrade = store.getTrade(trade1.getTradeId(), trade1.getVersion());
		assertNotNull(storedtrade);
		assertEquals("B2", storedtrade.getBookId());
	}

}