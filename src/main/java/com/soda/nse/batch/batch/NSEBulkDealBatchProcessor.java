package com.soda.nse.batch.batch;

import com.soda.nse.batch.model.NSEBulkDeal;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NSEBulkDealBatchProcessor implements ItemProcessor<NSEBulkDeal, NSEBulkDeal> {

    private static final Map<String, String> NSE_BULK_DEAL_ITEM_MAP = new HashMap<>();

    @Override
    public NSEBulkDeal process(NSEBulkDeal item) throws Exception {
        String date = item.getDate();
        date.replace("\"","");
        item.setDate(date);
        String symbol = item.getSymbol();
        symbol.replace("\"","");
        item.setSymbol(symbol);
        String securityName = item.getSecurityName();
        securityName.replace("\"","");
        item.setSecurityName(securityName);
        String clientName = item.getClientName();
        clientName.replace("\"","");
        item.setClientName(clientName);
        String transactionType = item.getTransactionType();
        transactionType.replace("\"","");
        item.setTransactionType(transactionType);
        String quantityTraded = item.getQuantityTraded();
        quantityTraded.replace("\"","");
        item.setQuantityTraded(quantityTraded);
        String tradePrice = item.getTradePrice();
        tradePrice.replace("\"","");
        item.setTradePrice(tradePrice);
        String remark = item.getRemark();
        remark.replace("\"","");
        item.setRemark(remark);

        return item;
    }
}
