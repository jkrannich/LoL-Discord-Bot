package com.juliank.loldiscordbot.commands.command;

import com.juliank.loldiscordbot.commands.BotCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class GiveRoleCommand implements BotCommand {

    @Override
    public String getName() {
        return "giverole";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getOption("user").getAsMember();
        Role role = event.getOption("role").getAsRole();

        event.getGuild().addRoleToMember(member, role).queue();
        event.reply(member.getAsMention() + " has been given the " + role.getAsMention() + " role!").queue();
    }
}
