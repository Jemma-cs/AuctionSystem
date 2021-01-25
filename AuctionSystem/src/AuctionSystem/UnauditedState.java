package AuctionSystem;

public class UnauditedState implements AuctionState {
	public UnauditedState() {}
	
	@Override
	public String toString() {
		return "Unaudited";
	}
	@Override
	public boolean isOpenForBidding() {
		return false;
	}
	@Override
	public boolean isOpenForPayment() {
		return false;
	}
}
