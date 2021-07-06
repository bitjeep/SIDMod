package sid;

import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SIDItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SIDModInfo.MOD_ID);

    public static final RegistryObject<Item> SIDOBIAN = ITEMS.register("sidobian", () ->
            new BlockItem(SIDBlocks.SIDOBIAN.get(), (new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> SID_SWORD = ITEMS.register("sidsword", () ->
            new SwordItem(SIDItemTier.SID_SWORD, 32, 4.0F, (new Item.Properties()).group(ItemGroup.COMBAT)));
}
