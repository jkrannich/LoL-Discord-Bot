package com.juliank.loldiscordbot.commands.command;

import com.juliank.loldiscordbot.commands.BotCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class EmoteCommand implements BotCommand {

    @Override
    public String getName() {
        return "emote";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping option = event.getOption("type");
        String type = option.getAsString();

        String replyMessage = "";

        switch (type.toLowerCase()) {
            case "hug" -> {
                replyMessage = "You hug the closest person to you!";
            }
            case "laugh" -> {
                replyMessage = "You laugh hysterically!";
            }
            case "cry" -> {
                replyMessage = "You cant stop crying";
            }
        }
        event.reply(replyMessage).setEphemeral(true).queue();
    }
}
