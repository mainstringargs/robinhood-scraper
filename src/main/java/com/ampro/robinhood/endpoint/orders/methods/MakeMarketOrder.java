package com.ampro.robinhood.endpoint.orders.methods;

import com.ampro.robinhood.Configuration;
import com.ampro.robinhood.endpoint.orders.enums.OrderTransactionType;
import com.ampro.robinhood.endpoint.orders.enums.TimeInForce;
import com.ampro.robinhood.throwables.RobinhoodApiException;
import com.ampro.robinhood.throwables.TickerNotFoundException;

/**
 * Created by SirensBell on 6/15/2017.
 */
public class MakeMarketOrder extends OrderMethod {

    private final String ticker;
    private final int quantity;
    private final OrderTransactionType orderType;
    private final String tickerInstrumentUrl;
    private final TimeInForce time;

    /**
     * TODO DOCS
     * @param ticker
     * @param quantity
     * @param orderType
     * @param time
     * @param config
     * @throws RobinhoodApiException
     * @throws TickerNotFoundException
     */
    public MakeMarketOrder(String ticker, int quantity, OrderTransactionType orderType,
                           TimeInForce time, Configuration config)
    throws TickerNotFoundException {
        super(config);
        this.ticker = ticker;
        this.quantity = quantity;
        this.orderType = orderType;
        this.time = time;

        //Set the normal parameters for this endpoint
        setEndpointParameters();

        //Set the order parameters
        setOrderParameters();

        //Verify the ticker, and add the instrument URL to be used for later
        this.tickerInstrumentUrl = verifyTickerData(this.ticker);

    }

    /**
     * Method which sets the URLParameters for correctly so the order is ran as a
     * Market order, given the settings from the constructor
     *
     * @throws com.ampro.robinhood.throwables.NotLoggedInException
     */
    private void setOrderParameters() {
        this.addFieldParameter("account", this.config.getAccountUrl());
        this.addFieldParameter("instrument", this.tickerInstrumentUrl);
        this.addFieldParameter("symbol", this.ticker);
        this.addFieldParameter("type", "market");
        this.addFieldParameter("time_in_force", this.time.toString());
        this.addFieldParameter("trigger", "immediate");
        this.addFieldParameter("quantity", String.valueOf(this.quantity));
        this.addFieldParameter("side", getOrderSideString(orderType));
    }

}
