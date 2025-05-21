package com.juliank.loldiscordbot.commands.command;

import com.juliank.loldiscordbot.commands.BotCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class WelcomeCommand implements BotCommand {

    @Override
    public String getName() {
        return "welcome";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String userTag = event.getUser().getAsTag();
        event.reply("Welcome to the server, **" + userTag + "**!")
                .setEphemeral(true)
                .queue();
    }
}
