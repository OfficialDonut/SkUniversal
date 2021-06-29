package us.donut.skuniversal.advancedsurvivalgames.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.advancedsurvivalgames.AdvancedSurvivalGamesHook.playerManager;

@Name("AdvancedSurvivalGames - Is Spectating")
@Description("Checks if a player is spectating a game.")
@Examples({"if the player spectating survival games:"})
public class CondSpectator extends Condition {

    static {
        Skript.registerCondition(CondSpectator.class,
                "%offlineplayer% is spectating [[advanced] (survival games|sg)]",
                "%offlineplayer% is [a[n]] [[advanced] (survival games|sg)] spectator",
                "%offlineplayer% is(n't| not) spectating [[advanced] (survival games|sg)]",
                "%offlineplayer% is(n't| not) [a[n]] [[advanced] (survival games|sg)] spectator");
    }

    private Expression<OfflinePlayer> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        setNegated(matchedPattern == 2 || matchedPattern == 3);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " is spectating survival games";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return playerManager.getSGPlayer(player.getSingle(e)).isSpectator() != isNegated();
    }
}