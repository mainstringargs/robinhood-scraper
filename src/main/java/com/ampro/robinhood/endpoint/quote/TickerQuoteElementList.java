package com.ampro.robinhood.endpoint.quote;


import com.ampro.robinhood.endpoint.quote.data.TickerQuoteElement;
import com.ampro.robinhood.endpoint.quote.methods.GetTickerGetQuoteList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A list of {@link TickerQuoteElement TickerQuoteElements} that Robinhood
 * returns from {@link GetTickerGetQuoteList GetTickerGetQuoteList}.
 * @author Jonathan Augustine
 */
public class TickerQuoteElementList {
    @SerializedName("results")
    @Expose
    private List<TickerQuoteElement> results;
    private final static long serialVersionUID = 1097478353426776156L;

    /** @return An unmodifieable list of {@link TickerQuoteElement quotes} */
    public List<TickerQuoteElement> getQuotes() {
        results = results == null ? new ArrayList<>() : results;
        return Collections.unmodifiableList(results);
    }

}
