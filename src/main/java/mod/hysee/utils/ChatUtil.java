package mod.hysee.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatUtils {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void sendChatMessage(String message, String messageType) {
        if (mc.theWorld == null) return;
        EnumChatFormatting color;
        String prefix;

        switch (messageType) {
            case "ERROR":
                color = EnumChatFormatting.DARK_RED;
                prefix = "ERROR! ";
                break;
            case "INFO":
                color = EnumChatFormatting.YELLOW;
                prefix = "INFO! ";
                break;
            default:
                color = EnumChatFormatting.WHITE;
                prefix = "";
        }

        mc.thePlayer.addChatMessage(new ChatComponentText(color + "" + EnumChatFormatting.BOLD + prefix + EnumChatFormatting.WHITE + message));
    }
}
