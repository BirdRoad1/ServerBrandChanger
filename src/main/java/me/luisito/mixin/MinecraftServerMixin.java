package me.luisito.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.luisito.ServerBrandChanger;
import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
	@Inject(at = @At("RETURN"), method = "getServerModName", remap = false, cancellable = true)
	private void init(CallbackInfoReturnable<String> info) {
		info.setReturnValue(ServerBrandChanger.brand);
	}
}