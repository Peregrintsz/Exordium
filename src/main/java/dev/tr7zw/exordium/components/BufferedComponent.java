package dev.tr7zw.exordium.components;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.GpuFormat;
import com.mojang.blaze3d.systems.RenderSystem;
import org.joml.Vector4f;

import dev.tr7zw.exordium.ExordiumModBase;
import dev.tr7zw.exordium.util.ScreenTracker;
import lombok.Getter;

public class BufferedComponent {

    @Getter
    private final RenderTarget guiTarget = new TextureTarget("BufferedComponent", 100, 100, true, GpuFormat.RGBA8_UNORM);
    private final ScreenTracker screenTracker = new ScreenTracker(guiTarget);

    public void captureComponent() {
        if (screenTracker.hasChanged()) {
            screenTracker.updateState();
        }
        RenderSystem.getDevice().createCommandEncoder().clearColorAndDepthTextures(guiTarget.getColorTexture(), new Vector4f(0, 0, 0, 0),
                guiTarget.getDepthTexture(), 1.0);
        ExordiumModBase.instance.setTemporaryScreenOverwrite(guiTarget);
    }

    public void finishCapture() {
        ExordiumModBase.instance.setTemporaryScreenOverwrite(null);
    }

}
