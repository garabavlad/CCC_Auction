package com.classes;

import java.util.ArrayList;
import java.util.List;

class History
{
    private List<HistoryPair> bidHistory;

    History(int initialProductPrice)
    {
        bidHistory = new ArrayList<>();
        bidHistory.add(new HistoryPair("-",initialProductPrice));
    }

    void AddBid(String bidderName,Integer bidderCost)
    {
        HistoryPair newPair = new HistoryPair(bidderName,bidderCost);
        bidHistory.add(newPair);
    }

    void AddBid(Client client, Integer bidderCost)
    {
        String bidderName = client.GetClientName();
        AddBid(bidderName,bidderCost);
    }

    String GetHistory()
    {
        StringBuilder historyToReturn = new StringBuilder();

        for (HistoryPair element : bidHistory) {
            historyToReturn.append(",");
            historyToReturn.append(element.getName());
            historyToReturn.append(",");
            historyToReturn.append(element.getBid());
        }

        // Removing first comma.
        historyToReturn.delete(0,1);

        return historyToReturn.toString();
    }
}
