package com.bradley.firstmod.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "firstmod");

    public static final RegistryObject<Item> FIRST_ITEM = CreativeTabInit.addToTab(ITEMS.register("first_item",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationMod(0.2f)
                            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 1.0f)
                            .build())
                    .rarity(Rarity.EPIC)
            )));

    public static final RegistryObject<BlockItem> EXAMPLE_BLOCK_ITEM = CreativeTabInit.addToTab(ITEMS.register("custom_block",
            () -> new BlockItem(BlockInit.CUSTOM_BLOCK.get(),
                    new Item.Properties()
                            .rarity(Rarity.UNCOMMON)
            )));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
