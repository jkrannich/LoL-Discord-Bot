package com.juliank.loldiscordbot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface BotCommand {
    String getName();
    void execute(SlashCommandInteractionEvent command);
}
