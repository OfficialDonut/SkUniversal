package us.donut.skuniversal.bedwars.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Bedwars - Is Playing")
@Description("Checks if a player is in a Bedwars game.")
@Examples({"if {_player} is playing in a Bedwars game:"})
public class CondInGame extends Condition {

    static {
        Skript.registerCondition(CondInGame.class,
                "%player% is [currently] [playing] in [a] [Bedwars] game",
                "%player% is(n't| not) [currently] [playing] in [a] [Bedwars] game");
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
        return "player " + player.toString(e, b) + " is in Bedwars game";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return GameManager.inGame(player.getSingle(e)) != isNegated();
    }
}
