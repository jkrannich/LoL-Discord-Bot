package com.juliank.loldiscordbot.commands;

import com.juliank.loldiscordbot.commands.command.EmoteCommand;
import com.juliank.loldiscordbot.commands.command.GameInfoCommand;
import com.juliank.loldiscordbot.commands.command.GiveRoleCommand;
import com.juliank.loldiscordbot.commands.command.LookUpSummonerCommand;
import com.juliank.loldiscordbot.commands.command.RolesCommand;
import com.juliank.loldiscordbot.commands.command.SayCommand;
import com.juliank.loldiscordbot.commands.command.WelcomeCommand;
import com.juliank.loldiscordbot.lolapi.RiotApiService;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager extends ListenerAdapter {

    private final Map<String, BotCommand> commands = new HashMap<>();

    public CommandManager(RiotApiService riotApiService) {
        commands.put("welcome", new WelcomeCommand());
        commands.put("giverole", new GiveRoleCommand());
        commands.put("emote", new EmoteCommand());
        commands.put("roles", new RolesCommand());
        commands.put("say", new SayCommand());
        commands.put("lookupsummoner", new LookUpSummonerCommand(riotApiService));
        commands.put("gameinfo", new GameInfoCommand(riotApiService));
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

        OptionData option6 = new OptionData(OptionType.STRING, "region", "Enter region of summoner", true)
                .addChoice("EUW/EUNE", "europe").addChoice("NA/LAN/LAS/BR", "americas").addChoice("KR/JP/VN", "asia");
        OptionData option7 = new OptionData(OptionType.STRING, "name", "Summoner name", true);
        OptionData option8 = new OptionData(OptionType.STRING, "tag", "Summoner tag", true);
        commandData.add(Commands.slash("lookupsummoner", "Look up summoner").addOptions(option6, option7, option8));

        OptionData option9 = new OptionData(OptionType.STRING, "region", "Enter region of summoner", true)
                .addChoice("EUW/EUNE", "europe").addChoice("NA/LAN/LAS/BR", "americas").addChoice("KR/JP/VN", "asia");
        OptionData option10 = new OptionData(OptionType.STRING, "name", "Summoner name", true);
        OptionData option11 = new OptionData(OptionType.STRING, "tag", "Summoner tag", true);
        commandData.add(Commands.slash("gameinfo", "Look up live game info").addOptions(option9, option10, option11));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        BotCommand cmd = commands.get(event.getName());
        if (cmd != null) {
            try {
                cmd.execute(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Unknown command: " + event.getName());
        }
    }
}
