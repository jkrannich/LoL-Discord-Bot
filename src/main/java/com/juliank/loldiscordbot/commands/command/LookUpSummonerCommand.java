package com.juliank.loldiscordbot.commands.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliank.loldiscordbot.commands.BotCommand;
import com.juliank.loldiscordbot.lolapi.RiotApiService;
import com.juliank.loldiscordbot.lolapi.dtos.AccountDto;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class LookUpSummonerCommand implements BotCommand {

    private final RiotApiService riotApiService;

    public LookUpSummonerCommand(RiotApiService riotApiService) {
        this.riotApiService = riotApiService;
    }

    @Override
    public String getName() {
        return "lookupsummoner";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) throws IOException, InterruptedException {
        event.deferReply().queue();

        String region = event.getOption("region").getAsString();
        String name = event.getOption("name").getAsString();
        String tag = event.getOption("tag").getAsString();

        try {
            AccountDto dto = riotApiService.getAccountByRiotId(region, tag, name);
            event.getHook()
                    .sendMessageEmbeds(
                            new EmbedBuilder()
                                    .setTitle(dto.getGameName() + "#" + dto.getTagLine())
                                    .addField("PUUID", dto.getPuuid(), false)
                                    .build()
                    )
                    .queue();         // ✅
        } catch (NoSuchElementException e) {
            event.getHook()
                    .sendMessage("❌ Summoner “" + name + "#" + tag + "” not found.")
                    .setEphemeral(true)
                    .queue();         // ✅
        } catch (Exception e) {
            event.getHook()
                    .sendMessage("⚠️ Error fetching from Riot: " + e.getMessage())
                    .setEphemeral(true)
                    .queue();         // ✅
        }
    }
}
