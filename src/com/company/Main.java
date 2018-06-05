package com.company;

import com.classes.Auction;
import com.classes.Client;

public class Main {

    public static void main(String[] args) {
	    // Getting input:
        String input = "1,47,6a,17,kl,5,kl,10,kl,15,cs,28,kl,20,kl,25,hr,35,hr,40,hr,41,hl,42,hr,43,hr,44,hl,44,hl,49,hr,47";

        if(input.length()<1)
        {
            System.out.println("FATAL ERROR: Input string is empty!");
            return ;
        }

        // Getting Product initial price:
        int initialProductPrice;
        initialProductPrice = Integer.parseInt(
                input.substring(0,input.indexOf(',',0))
        );

        // Setting a StringBuilder which will help to get bid stream further:
        StringBuilder bidStream = new StringBuilder(
                input.substring(input.indexOf(',',0)+1)+","
        );

        // Getting Product sell price:
        int sellProductPrice;
        sellProductPrice = Integer.parseInt(
                bidStream.substring(0,bidStream.indexOf(",",0))
        );
        bidStream.delete(0,bidStream.indexOf(",")+1);

        // Auction initialization:
        Auction auction = new Auction(initialProductPrice, sellProductPrice);

        // Running bids into auction:
        int step = 0;
        String bidderName = "ERROR";
        int bidderBid = -1;
        while(bidStream.length()!=0)
        {
            // Getting name of bidder:
            if(step%2==0)
            {
                bidderName = bidStream.substring(0,bidStream.indexOf(","));
                bidStream.delete(0,bidStream.indexOf(",")+1);

                step++;
            }
            // Getting bid amount:
            else
            {
                bidderBid = Integer.parseInt(
                        bidStream.substring(0,bidStream.indexOf(","))
                );
                bidStream.delete(0,bidStream.indexOf(",")+1);

                step++;
            }

            // Running a new bid after it got Bidder's name and bid amount:
            if(step>0 && step%2==0)
            {
                auction.NewBid(bidderName,bidderBid);
            }
        }

        // Showing auction history:
        System.out.println();
        System.out.print(auction.GetHistory());
        System.out.println();

    }
}
