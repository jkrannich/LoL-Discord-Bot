package com.juliank.loldiscordbot.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager extends ListenerAdapter {

    private final Map<String, BotCommand> commands = new HashMap<>();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("welcome", "Get welcomed by the bot"));
        commandData.add(Commands.slash("roles", "Display all roles on the server"));

        OptionData option1 = new OptionData(OptionType.STRING, "message", "Enter your message here", true);
        OptionData option2 = new OptionData(OptionType.CHANNEL, "targetchannel", "Enter the channel you want to send the message in", false)
                .setChannelTypes(ChannelType.TEXT);
        commandData.add(Commands.slash("say", "Make the bot say a message").addOptions(option1, option2));

        OptionData option3 = new OptionData(OptionType.STRING, "type", "Type of emotion", true)
                .addChoice("Hug", "hug").addChoice("Laugh", "laugh").addChoice("Cry", "cry");
        commandData.add(Commands.slash("emote", "Express your emotions").addOptions(option3));

        OptionData option4 = new OptionData(OptionType.USER, "user", "The user to give the role to", true);
        OptionData option5 = new OptionData(OptionType.ROLE, "role", "The role to be assigned", true);
        commandData.add(Commands.slash("giverole", "Give a user a role").addOptions(option4, option5));
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
