package info.partonetrain;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.HTML;

public class Knifestock implements ModInitializer {

	public static final String MOD_ID = "knifestock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final TagKey<Item> ITEM_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "knives"));
	public static final TagKey<EntityType<?>> ENTITY_TYPE_TAG = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(MOD_ID, "livestock"));

	@Override
	public void onInitialize() {
		LOGGER.info("Knifestock is installed. If you can't damage animals, hold an item tagged with knifestock:knives");
	}
}