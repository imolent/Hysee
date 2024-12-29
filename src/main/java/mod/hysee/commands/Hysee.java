package mod.hysee.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import mod.hysee.utils.ChatUtil;
import mod.hysee.utils.MojangUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class Hysee extends CommandBase {

    @Override
    public String getCommandName() {
        return "hysee";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/hysee [player]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (sender != null) {
            String player = args[1];
            new Thread(() -> {
                String response = String.valueOf(ApiHelper.getJsonResponse("https://api.hypixel.net/v2/player?uuid=" + MojangUtil.getUUID(player) + "&key=" + MojangUtil.apiKey));

                try {
                    JsonObject json = new JsonParser().parse(response).getAsJsonObject();
                    if (json.get("success").getAsBoolean()) {
                        int networkExp = json.get("networkExp").getAsInt();
                        int networkLevel = (math.sqrt((2 * networkExperience) + 30625) / 50) - 2.5, 2
                        ChatUtil.sendChatMessage("Network Level Of " + player + " : " + networkLevel, "INFO");
                    } else {
                        ChatUtil.sendChatMessage("Failed to fetch network level", "ERROR");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ChatUtil.sendChatMessage("Parsing error on network level!", "ERROR");
                }
            }).start();
        }
    }


    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

}
