package AuctionSystem;

public class TagVirtual implements Tag{
	private String name;
	private String desc;
	private static TagVirtual instance =new TagVirtual();
	public static TagVirtual getInstance() {return instance;}
	private TagVirtual() {
		name="Virtual";
		desc="";
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getDesc() {
		return desc;
	}
}
