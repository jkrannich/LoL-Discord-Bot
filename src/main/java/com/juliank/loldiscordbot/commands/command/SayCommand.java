package com.juliank.loldiscordbot.commands.command;

import com.juliank.loldiscordbot.commands.BotCommand;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class SayCommand implements BotCommand {
    @Override
    public String getName() {
        return "say";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
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
    }
}
