package org.bukkit.block;

import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Represents a furnace.
 */
public interface Furnace extends BlockState, InventoryHolder {

	/**
	 * Get burn time.
	 *
	 * @return Burn time
	 */
	short getBurnTime();

	/**
	 * Set burn time.
	 *
	 * @param burnTime Burn time
	 */
	void setBurnTime(short burnTime);

	/**
	 * Get cook time.
	 *
	 * @return Cook time
	 */
	short getCookTime();

	/**
	 * Set cook time.
	 *
	 * @param cookTime Cook time
	 */
	void setCookTime(short cookTime);

	FurnaceInventory getInventory();
}
