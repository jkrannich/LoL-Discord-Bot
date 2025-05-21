package com.juliank.loldiscordbot.commands.command;

import com.juliank.loldiscordbot.commands.BotCommand;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class RolesCommand implements BotCommand {

    @Override
    public String getName() {
        return "roles";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().setEphemeral(true).queue();
        String response = "";
        for (Role role: event.getGuild().getRoles()){
            response += role.getAsMention() + "\n";
        }
        event.getHook().sendMessage(response).queue();
    }
}
