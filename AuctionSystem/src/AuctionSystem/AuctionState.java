package AuctionSystem;

public interface AuctionState {
	public String toString();
	public boolean isOpenForBidding();
	public boolean isOpenForPayment();
}
