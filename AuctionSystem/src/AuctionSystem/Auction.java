package AuctionSystem;

public class Auction {
	private int auctID;
	private static int latestID = 0;
	private Item it;
	private double floorPrice;
	private double curPrice;
	private double noBargainPrice;
	private User highestPriceUser;
	// AuctionState state;

	/// 1. Basic methods (Class User & Item)
	public Auction(Item aIt, double aFP, double aNBP) {
		auctID = ++latestID;
		it = aIt;
		floorPrice = aFP;
		curPrice = 0;
		noBargainPrice = aNBP;
		highestPriceUser = null;
		this.setState(new UnauditedState());
		AuctionList al = AuctionList.getInstance();
		al.newAuction(this);
	}

	/// Get & Set & Check
	public int getID() {
		return auctID;
	}

	public String getName() {
		return it.getName();
	}

	public AuctionState getState() {
		return it.getState();
	}

	public String toString() {// return detailed information of this Auction
		String temp = "";
		temp += "Auction ID: " + auctID + "    State: " + it.getState() + "\n";
		temp += "Item information:\n" + it + "\n";
		temp += "Floor price: " + floorPrice + "    No-bargain price: " + noBargainPrice + "\n";
		if (highestPriceUser != null) {
			temp += "Current Price: " + curPrice + "    Hold User: " + highestPriceUser + "\n";
		}
		return temp;
	}

	public boolean isOwner(User u) {
		if (it.getOwner() == u)
			return true;
		return false;
	}

	public boolean isHasTag(String tagName) {
		return this.it.findTagByName(tagName);
	}

	/// Bidding
	private User setHighestPriceUser(User u) {
		if (highestPriceUser != null) {
			this.alertHighestChange(u);// Alert previous highestPriceUser
		}
		highestPriceUser = u;
		return highestPriceUser;
	}

	public int bid(double bidPrice, User bidder) {
		if (!this.it.getState().isOpenForBidding()) {
			System.out.println("Bidding failed. The auction (ID: " + auctID + ") is not available for bidding.");
			return -1;// Auction is unavailable
		}
		if (bidPrice < floorPrice) {
			System.out.println("Bidding failed. Your price is lower than the floor price.");
			return 0;// Bidding is invalid
		} else if (bidPrice <= curPrice) {
			System.out.println("Bidding failed. Your price is not higher than the current price.");
			return 0;// Bidding is invalid
		} else {
			System.out.println("Your price is the highest now.");
			this.setHighestPriceUser(bidder);
			if (bidPrice >= noBargainPrice) {
				System.out.println("The no-bargain price is reached! You got the item!");
				curPrice = noBargainPrice;
				this.setState(new PendingForPayState());
				this.alertOwnNBPrice();
			} else {
				curPrice = bidPrice;
			}
			return 1;// Bidding succeeds
		}
	}

	public boolean isCanBeSold() {// Return whether this auction is ready to do the transaction
		if (this.it.getState().isOpenForBidding() && curPrice > floorPrice)
			return true;
		else
			return false;
	}

	public int confirmTransaction() {
		if (this.isCanBeSold()) {
			this.setState(new PendingForPayState());
			this.alertBuyerToPay();
			System.out.println(
					"Confirmation of auction (ID:" + this.auctID + ") succeeds. Message has been sent to the buyer.");
			return 1;
		}
		return 0;
	}

	public int finishPayment() {
		double amount = curPrice;
		if (!this.getState().isOpenForPayment()) {
			System.out.println("This auction is not available for finishing payment.");
			return 0;// indicates unavailable for transaction
		}
		User buyer = highestPriceUser;
		User seller = this.it.getOwner();
		if (buyer.transaction(-1 * amount) == 1) {
			if (seller.transaction(amount) == 1) {
				System.out.println("Transaction between buyer (ID: " + buyer.getID() + ") and seller (ID: "
						+ seller.getID() + ") is done.");
				System.out.println("Paying amount is " + amount + ".");
				this.setState(new ClosedState());
				this.alertAuctionDone();// Send msgs to seller and buyer
				return 1;// indicates success
			} else {
				buyer.transaction(amount);// Reverse the transaction of buyer's account
				System.out.println("Transaction failed on seller side! Payment is sudpended.");
				return -3;// indicates failure of seller's transaction
			}
		} else {
			System.out.println("Transaction failed on buyer side! Payment is sudpended.");
			return -2;// indicates failure of buyer's transaction
		}

	}

	/// Send message to User
	public void alertOwnDelete() {
		User o = it.getOwner();
		String msg1 = "System: " + "Your auction (ID: " + this.auctID + ") is deleted.";
		o.receiveMsg(msg1);
		if (highestPriceUser != null) {
			String msg2 = "System: " + "The auction (ID: " + this.auctID + ") you held the highest price is deleted.";
			highestPriceUser.receiveMsg(msg2);
		}
		setState(new UnpublishedState());
	}

	public void alertOwnApprove() {
		User o = it.getOwner();
		String msg = "System: " + "Your auction (ID: " + this.auctID + ") is approved by the admin.";
		o.receiveMsg(msg);
	}

	public void alertOwnDecline() {
		User o = it.getOwner();
		String msg = "System: " + "Your auction (ID: " + this.auctID + ") is declined by the admin.";
		o.receiveMsg(msg);
	}

	public void alertHighestChange(User newH) {
		User buyer = this.highestPriceUser;
		if(buyer==newH)
			return;
		String msg = "System: " + "Your highest price in auction " + this.auctID
				+ " is replaced by a higher price from " + newH.getName() + " (ID: " + newH.getID() + ").";
		buyer.receiveMsg(msg);
	}

	public void alertOwnNBPrice() {
		User o = it.getOwner();
		String msg = "System: " + "The no-bargain price of your auction (ID: " + this.auctID
				+ ") has been reached. Waiting for the buyer to finish payment.";
		o.receiveMsg(msg);
	}

	public void alertBuyerToPay() {
		User buyer = this.highestPriceUser;
		String msg = "System: " + "Your bidding in auction (ID: " + this.auctID
				+ ") is confirmed by the seller. Please remember to finish the payment.";
		buyer.receiveMsg(msg);
	}

	public void alertAuctionDone() {
		User seller = this.it.getOwner();
		User buyer = highestPriceUser;

		String msg1 = "System: " + "Your auction (ID: " + this.auctID + ") is done. Transaction price is "
				+ this.curPrice + ".";
		seller.receiveMsg(msg1);
		String msg2 = "System: " + "You won the auction (ID: " + this.auctID + "). Transaction price is "
				+ this.curPrice + ".";
		buyer.receiveMsg(msg2);
	}

	/// Change state for both this Auction and linked Item
	public AuctionState setState(AuctionState newState) {
		this.it.setState(newState);
		return it.getState();// For testing maybe
	}
	
	///for testing
	public static void init() {
		latestID=0;
	}
}
