package sid.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
//import net.minecraftforge.common.ToolType;

public class SIDobian extends OreBlock {
    public SIDobian() {
        super(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK)
                .setRequiresTool()
                .hardnessAndResistance(50.0F, 1200.0F)
                .sound(SoundType.METAL)
        );
    }
}
