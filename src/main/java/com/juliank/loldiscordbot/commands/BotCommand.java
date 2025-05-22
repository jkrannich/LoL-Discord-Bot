package com.juliank.loldiscordbot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.IOException;

public interface BotCommand {
    String getName();
    void execute(SlashCommandInteractionEvent command) throws IOException, InterruptedException;
}
