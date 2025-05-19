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
import java.util.List;

public class CommandManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("welcome")) {
            String userTag = event.getUser().getAsTag();
            event.reply("Welcome to the server, **" + userTag + "**!").setEphemeral(true).queue();
        } else if (command.equals("roles")) {
            event.deferReply().setEphemeral(true).queue();
            String response = "";
            for (Role role: event.getGuild().getRoles()){
                response += role.getAsMention() + "\n";
            }
            event.getHook().sendMessage(response).queue();
        } else if (command.equals("say")) {
            OptionMapping messageOption = event.getOption("message");
            if (messageOption != null) {
                String message = messageOption.getAsString();

                MessageChannel channel;
                OptionMapping channelOption = event.getOption("targetchannel");
                if (channelOption != null) {
                    channel = channelOption.getAsChannel().asGuildMessageChannel();
                } else {
                    channel = event.getChannel();
                }

                channel.sendMessage(message).queue();
                event.reply("Your message was sent").setEphemeral(true).queue();
            }
        } else if (command.equals("emote")) {
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
        } else if (command.equals("giverole")) {
            Member member = event.getOption("user").getAsMember();
            Role role = event.getOption("role").getAsRole();

            event.getGuild().addRoleToMember(member, role).queue();
            event.reply(member.getAsMention() + " has been given the " + role.getAsMention() + " role!").queue();
        }
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
