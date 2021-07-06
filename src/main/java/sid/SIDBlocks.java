package sid;

import sid.blocks.SIDobian;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SIDBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SIDModInfo.MOD_ID);
    public static final RegistryObject<Block> SIDOBIAN  = BLOCKS.register("sidobian", SIDobian::new);
}
