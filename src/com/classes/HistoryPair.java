package com.classes;

/*
    This class is used to storage Pairs of Bidders with their name & bid.
    History class could contain same name or bid multiple times, due to can't use Maps.

    Overridden also some basic methods for future development (in case of).
 */

class HistoryPair<String,Integer> {

    private final String bidderName;
    private final Integer bidderBid;

    HistoryPair(String bidderName, Integer bidderBid) {
        this.bidderName = bidderName;
        this.bidderBid = bidderBid;
    }

    String getName() { return bidderName; }
    Integer getBid() { return bidderBid; }

    @Override
    public int hashCode() { return bidderName.hashCode() ^ bidderBid.hashCode(); }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof HistoryPair)) return false;
        HistoryPair pairObject = (HistoryPair) object;
        return this.bidderName.equals(pairObject.getName()) &&
                this.bidderBid.equals(pairObject.getBid());
    }

}
