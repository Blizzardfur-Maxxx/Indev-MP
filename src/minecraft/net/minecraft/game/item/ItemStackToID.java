package net.minecraft.game.item;

public class ItemStackToID {

	public static int getItemID(ItemStack itemStack) {
		if (itemStack == null) {
			return -1;
		}
		
		return itemStack.itemID;
}
}