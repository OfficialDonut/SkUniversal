package us._donut_.skuniversal.skywars_cookloco.conditions;

import ak.CookLoco.SkyWars.api.SkyWarsAPI;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("SkyWars (CookLoco) - Is Spectating")
@Description("Checks if a player is spectating.")
@Examples({"if player is spectating a skywars game:"})
public class CondSpectating extends Condition {

    static {
        Skript.registerCondition(CondSpectating.class,
                "%player% is [currently] spectating [a] SkyWars [game]",
                "%player% is [currently] [a] [SkyWars] spectator",
                "%player% is(n't| not) [currently] spectating [a] SkyWars [game]",
                "%player% is(n't| not) [currently] [a] [SkyWars] spectator");
    }

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        setNegated(matchedPattern == 2 || matchedPattern == 3);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " is spectating a SkyWars game";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return SkyWarsAPI.getSkyPlayer(player.getSingle(e)).isSpectating();
    }
}
