package com.bradley.firstmod.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "firstmod");

    public static final RegistryObject<Block> CUSTOM_BLOCK = BLOCKS.register("custom_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ANVIL)
                    .mapColor(MapColor.COLOR_GREEN)
                    .strength(5.0f, 17f)
                    .instrument(NoteBlockInstrument.BANJO)
                    .lightLevel(state -> 10)
                    .requiresCorrectToolForDrops()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
