package dev.tr7zw.exordium.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.blaze3d.pipeline.RenderTarget;

import dev.tr7zw.exordium.ExordiumModBase;
import net.minecraft.client.renderer.GameRenderer;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "mainRenderTarget", at = @At("HEAD"), cancellable = true)
    public void mainRenderTarget(CallbackInfoReturnable<RenderTarget> ci) {
        if (ExordiumModBase.instance == null) {
            return;
        }
        RenderTarget target = ExordiumModBase.instance.getTemporaryScreenOverwrite();
        if (target != null) {
            ci.setReturnValue(target);
            ci.cancel();
        }
    }

}
