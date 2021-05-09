package com.trade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class TradeStore {
	
	private static final Logger logger = Logger.getLogger(TradeStore.class.getName());  

	Map<TradeKey, Trade> map = new HashMap<TradeKey, Trade>();
	
	/**
	 * Store trades
	 * @param trades
	 * @throws Exception 
	 */
	public void storeTrade(List<Trade> trades) throws Exception {
		logger.info("Entering method storeTrade ");
		for(Trade trade : trades) {
		    if(trade.getCreatedDate().compareTo(trade.getMaturityDate()) > 0 ){
			   logger.info("Trade date is greater then maturity date..Updating the expiry flag");
			   trade.setExpired("Y");
		   }		   
		   TradeKey tradeKey = new TradeKey(trade.getTradeId(),trade.getVersion());
		   //if trade already exist for version. override it 
		   if(map.get(tradeKey)!=null) {
			   Trade existingTrade = map.get(tradeKey);
			   if(existingTrade.getVersion() > existingTrade.getVersion()) {
				   throw new Exception("Higher version already existing for trade :"+trade.getTradeId());
			   } 
			   map.put(tradeKey, trade);
		   } else {
			   SimpleDateFormat sdfCurr = new SimpleDateFormat("dd/MM/yyyy");
				String dateStr = sdfCurr.format(new Date());
				Date currDate = sdfCurr.parse(dateStr);
			   if(trade.getMaturityDate().compareTo(currDate) < 0 ){
				   logger.info("Trade date has less maturity date then today date. Ignoring");			   
			   } else {
			       map.put(tradeKey, trade);
			   }
		   }
		}
		logger.info("Exiting method storeTrade ");
	}

	/**
	 * Get the trade
	 * @param tradeId
	 * @param version
	 * @return
	 */
	public Trade getTrade(String tradeId, Integer version) {
	  logger.info("Entering method getTrade ");
	  TradeKey tradeKey = new TradeKey(tradeId, version);
	  logger.info("Exiting method getTrade ");
	  return map.get(tradeKey);	  
	}
	
}
