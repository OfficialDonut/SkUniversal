package us.donut.skuniversal.skywars_cookloco.conditions;

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

@Name("SkyWars (CookLoco) - Has Kit")
@Description("Checks if a player has a kit.")
@Examples({"if player has a skywars kit:"})
public class CondHasKit extends Condition {

    static {
        Skript.registerCondition(CondHasKit.class,
                "%player% [currently] has [a] [SkyWars] kit",
                "%player% does(n't| not) [currently] have [a] [SkyWars] kit");
    }

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " has a SkyWars kit";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return SkyWarsAPI.getSkyPlayer(player.getSingle(e)).hasKit() != isNegated();
    }
}
