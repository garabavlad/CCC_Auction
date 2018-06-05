package com.classes;

public class Auction
{
    private Product product;
    private Client highestBidder;
    private History history;
    private SellSystem sellSystem=null;
    private boolean isAuctionFinished = false;

    public Auction(int initialProductPrice, int sellProductPrice)
    {
        product = new Product(initialProductPrice);
        history = new History(initialProductPrice);
        if(sellProductPrice!=0)
            sellSystem = new SellSystem(sellProductPrice);
    }

    // Gets new bid and checks it.
    public void NewBid(String bidderName, Integer bidderBid)
    {

        // Checks if actual auction is done:
        if(isAuctionFinished)
        {
            return ;
        }

        Client newBidder = new Client(bidderName,bidderBid);

        // Special case: First bid & Same amount as Product price:
        if(highestBidder==null && newBidder.GetClientBid()==product.GetProductPrice())
        {
            UpdateHighestBidder(newBidder);
            if(sellSystem != null)
            {
                if(sellSystem.IsEnoughToSell(product.GetProductPrice()))
                {
                    isAuctionFinished = true;
                    UpdateProductPrice(sellSystem.GetSellProductPrice());
                }
            }
            history.AddBid(newBidder,product.GetProductPrice());
        }

        // Compiler says these are worthless statements. I'll just leave them here.
        /*// New bid MUST be higher than Product price:
        else if(newBidder.GetClientBid()<=product.GetProductPrice())
        {
            return ;
        }

        // If New bid is exactly the same as the Highest bidder's:
        else if(highestBidder.equals(newBidder))
        {
            return ;
        }*/

        // First bid
        else if(highestBidder==null)
        {
            UpdateHighestBidder(newBidder);
            if(sellSystem != null)
            {
                if(sellSystem.IsEnoughToSell(product.GetProductPrice()))
                {
                    isAuctionFinished = true;
                    UpdateProductPrice(sellSystem.GetSellProductPrice());
                }
            }
            history.AddBid(newBidder,product.GetProductPrice());
        }
        // All cases:
        else
        {
            // Case when New bid is lower than Highest bid & higher than Product price:
            if(newBidder.GetClientBid() < highestBidder.GetClientBid())
            {
                UpdateProductPrice(newBidder.GetClientBid() + 1);
                if(sellSystem != null)
                {
                    if(sellSystem.IsEnoughToSell(product.GetProductPrice()))
                    {
                        isAuctionFinished = true;
                        UpdateProductPrice(sellSystem.GetSellProductPrice());
                    }
                }
                history.AddBid(highestBidder,product.GetProductPrice());
            }

            // Case when New bid is equal to Highest bid & higher than Product price:
            else if(newBidder.GetClientBid() == highestBidder.GetClientBid())
            {
                UpdateProductPrice(newBidder.GetClientBid());
                if(sellSystem != null)
                {
                    if(sellSystem.IsEnoughToSell(product.GetProductPrice()))
                    {
                        isAuctionFinished = true;
                        UpdateProductPrice(sellSystem.GetSellProductPrice());
                    }
                }
                history.AddBid(highestBidder,product.GetProductPrice());
            }

            // Case when New bid is higher than Highest bid & higher than Product price:
            else if(newBidder.GetClientBid() > highestBidder.GetClientBid())
            {
                // && New bidder is Highest bidder:
                if(newBidder.GetClientName().equals(highestBidder.GetClientName()))
                {
                    UpdateHighestBidder(newBidder);
                }
                // && New bidder is not Highest bidder:
                else
                {
                    UpdateProductPrice(highestBidder.GetClientBid() + 1);
                    UpdateHighestBidder(newBidder);
                    if(sellSystem != null)
                    {
                        if(sellSystem.IsEnoughToSell(product.GetProductPrice()))
                        {
                            isAuctionFinished = true;
                            UpdateProductPrice(sellSystem.GetSellProductPrice());
                        }
                    }
                    history.AddBid(newBidder,product.GetProductPrice());
                }
            }

            else
            {
                throw new RuntimeException("FATAL ERROR: Bid comparison exception.");
            }
        }
    }

    private void UpdateHighestBidder(Client newBidder)
    {
        this.highestBidder = newBidder;
    }

    private void UpdateProductPrice(int newProductPrice)
    {
        product.SetProductPrice(newProductPrice);
    }

    public Client GetHighestBidder()
    {
        return this.highestBidder;
    }

    public int GetProductPrice()
    {
        return this.product.GetProductPrice();
    }

    // Returns a string with Auction history.
    public String GetHistory()
    {
        return history.GetHistory();
    }
}