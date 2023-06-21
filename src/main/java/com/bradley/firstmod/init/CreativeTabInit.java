package com.bradley.firstmod.init;

import com.bradley.firstmod.FirstMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabInit {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirstMod.MOD_ID);

    public static final List<Supplier<? extends ItemLike>> CUSTOM_TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> CUSTOM_TAB = TABS.register("custom_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.custom_tab"))
                    .icon(ItemInit.FIRST_ITEM.get()::getDefaultInstance)
                    .displayItems((displayParams, output) -> {
                        CUSTOM_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get()));
                    })
                    .withSearchBar(89)
                    .build()
    );

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        CUSTOM_TAB_ITEMS.add(itemLike);
        return itemLike;
    }

    public static void register(IEventBus eventBus) {TABS.register(eventBus);
    }

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ItemInit.EXAMPLE_BLOCK_ITEM);
            event.getEntries().putAfter(Items.ACACIA_LOG.getDefaultInstance(), ItemInit.EXAMPLE_BLOCK_ITEM.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        if(event.getTab() == CUSTOM_TAB.get()) {
            event.accept(Items.CROSSBOW);
        }
    }
}
