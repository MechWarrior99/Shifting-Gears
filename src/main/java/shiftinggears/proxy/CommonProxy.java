package shiftinggears.proxy;

import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;

/**
 * @author shadowfacts
 */
public class CommonProxy extends AbstractProxy {

	@Override
	public void registerItemModel(Item item, int meta, String name, String variant) {
	}

	@Override
	@SuppressWarnings("deprecated")
	public String localize(String key, Object... params) {
		return I18n.translateToLocalFormatted(key, params);
	}
}
