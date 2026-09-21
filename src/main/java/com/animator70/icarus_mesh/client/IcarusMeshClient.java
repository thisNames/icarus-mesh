package com.animator70.icarus_mesh.client;

// 我的类
import com.animator70.icarus_mesh.IcarusMesh;
import com.animator70.icarus_mesh.client.models.*;

// Minecraft 类
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.player.Player;

// // Forge 类
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 客户端注册
 * IcarusMeshClient
 */
@Mod.EventBusSubscriber(modid = IcarusMesh.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class IcarusMeshClient {
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(IcarusMeshModels.FEATHERED, FeatheredWingsModel::getLayerDefinition);
        event.registerLayerDefinition(IcarusMeshModels.LEATHER, LeatherWingsModel::getLayerDefinition);
        event.registerLayerDefinition(IcarusMeshModels.LIGHT, LightWingsModel::getLayerDefinition);
        event.registerLayerDefinition(IcarusMeshModels.FLANDRES, FlandresWingsModel::getLayerDefinition);
        event.registerLayerDefinition(IcarusMeshModels.DISCORDS, DiscordsWingsModel::getLayerDefinition);
        event.registerLayerDefinition(IcarusMeshModels.ZANZAS, ZanzasWingsModel::getLayerDefinition);
    }

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (String skin : event.getSkins()) {
            try {
                LivingEntityRenderer<Player, EntityModel<Player>> renderer = event.getSkin(skin);

                if (renderer != null) {
                    renderer.addLayer(new WingsLayer<>(renderer, event.getEntityModels()));
                }
            } catch (Exception ignore) {
                // 兼容 LexForge 等环境
            }
        }
    }
}
