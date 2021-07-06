package sid;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = SIDModInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SIDModFeatures {
    public static ConfiguredFeature<?, ?> SIDOBIAN_CONFIG;

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        SIDOBIAN_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, SIDMod.sidmodLocation("ore_sidobian"),
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                                SIDBlocks.SIDOBIAN.get().getDefaultState(), 33)
                ).range(256).square().func_242731_b(10)
        );
    }
}
