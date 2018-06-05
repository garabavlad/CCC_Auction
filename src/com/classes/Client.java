package com.classes;

public class Client
{
    private String clientName;
    private int bid;

    Client(String clientName, int bid)
    {
        this.bid = bid;
        this.clientName = clientName;
    }

    String GetClientName()
    {
        return clientName;
    }

    int GetClientBid()
    {
        return bid;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof HistoryPair)) return false;
        //class cast exception
        try
        {
            Client client = (Client) o;
            return this.clientName.equals(client.GetClientName())
                    && this.bid == client.GetClientBid();
        } catch (ClassCastException e1)
        {
            e1.printStackTrace();
        }
        return false;
    }
}
