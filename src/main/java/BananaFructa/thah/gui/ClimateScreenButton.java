package BananaFructa.thah.gui;

import BananaFructa.thah.Climates;
import BananaFructa.thah.Thah;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ClimateScreenButton extends GuiButton {

    public Climates climate;

    public ClimateScreenButton(int buttonId, int x, int y, int widthIn, int heightIn, Climates climate) {
        super(buttonId, x, y,widthIn,heightIn, climate.name.substring(0,1).toUpperCase() + climate.name.substring(1));
        this.climate = climate;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {

            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            mc.renderEngine.bindTexture(new ResourceLocation(Thah.MODID, climate.resourceLocation));
            this.drawTexturedModalRect(this.x - 19, this.y, 0, 0, 16, 16);

            FontRenderer fontrenderer = mc.fontRenderer;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

            if (hovered) {
                drawRect(this.x, this.y + 1, this.x + width, this.y + height, 0xaadddddd);
            }

            drawHorizontalLine(this.x + 1, this.x + this.width - 2, this.y, 0xffffffff);
            drawHorizontalLine(this.x + 1, this.x + this.width - 2, this.y + height, 0xffffffff);
            drawVerticalLine(this.x, this.y, this.y + height, 0xffffffff);
            drawVerticalLine(this.x + this.width - 1, this.y, this.y + height, 0xffffffff);

            this.mouseDragged(mc, mouseX, mouseY);

            this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2 - 4, this.y + (this.height - 8) / 2, 0xffffff);
        }
    }

    public boolean isHovered() {
        return this.hovered;
    }
}
