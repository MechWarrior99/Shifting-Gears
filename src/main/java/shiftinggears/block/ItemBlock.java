package shiftinggears.block;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shadowfacts
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ItemBlock {

	Class<? extends net.minecraft.item.ItemBlock> value();

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface None {

	}

}
