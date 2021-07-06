package sid;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SIDModInfo.MOD_ID)
public class SIDMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger(SIDModInfo.MOD_ID);

    public SIDMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register for biome loading event
        MinecraftForge.EVENT_BUS.addListener(this::onBiomeLoading);

        // Register for entity joined event
        MinecraftForge.EVENT_BUS.addListener(this::onEntityJoinedWorld);

        SIDBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        SIDItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO from setup");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("sidmod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void onBiomeLoading(final BiomeLoadingEvent biome) {
        LOGGER.info("HELLO from biome loading");
        if (biome.getCategory() == Biome.Category.NETHER || biome.getCategory() == Biome.Category.THEEND) return;

        biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
                .add(() -> SIDModFeatures.SIDOBIAN_CONFIG);
    }

    @SubscribeEvent
    public void onEntityJoinedWorld(final EntityJoinWorldEvent join) {
        ResourceLocation location = new ResourceLocation("sidmod", "welcome_to_sid");
        SoundEvent event = new SoundEvent(location);
        join.getEntity().playSound(event, 1.0f, 1.0f);
    }

    public static ResourceLocation sidmodLocation(String resourceName) {
        return new ResourceLocation(SIDModInfo.MOD_ID, resourceName);
    }
}
