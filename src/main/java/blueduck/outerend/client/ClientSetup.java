package blueduck.outerend.client;

import blueduck.outerend.client.colors.items.SpawnEggColors;
import blueduck.outerend.client.entity.renderer.DragonflyEntityRenderer;
import blueduck.outerend.client.entity.renderer.HimmeliteRenderer;
import blueduck.outerend.client.entity.renderer.PurpurGolemRenderer;
import blueduck.outerend.registry.BlockRegistry;
import blueduck.outerend.registry.EntityRegistry;
import blueduck.outerend.registry.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
	public static void onSetup(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(BlockRegistry.AZURE_GRASS.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegistry.AZURE_SAPLING.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegistry.AZURE_DOOR.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegistry.AZURE_TRAPDOOR.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegistry.ENDER_ROOTS.get(), RenderType.getCutout());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DRAGONFLY.get(), DragonflyEntityRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.HIMMELITE.get(), HimmeliteRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.PURPUR_GOLEM.get(), PurpurGolemRenderer::new);
		
		Minecraft.getInstance().getItemColors().register(new SpawnEggColors(), ItemRegistry.SPECTRAFLY_SPAWN_EGG::get);
		Minecraft.getInstance().getItemColors().register(new SpawnEggColors(), ItemRegistry.HIMMELITE_SPAWN_EGG::get);
		Minecraft.getInstance().getItemColors().register(new SpawnEggColors(), ItemRegistry.PURPUR_GOLEM_SPAWN_EGG::get);
	}
}
