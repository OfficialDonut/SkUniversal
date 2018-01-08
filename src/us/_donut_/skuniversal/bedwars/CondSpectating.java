package us._donut_.skuniversal.bedwars;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import me.MineHome.Bedwars.Spectator.Spectator;
import javax.annotation.Nullable;

@Name("Bedwars - Is Spectating")
@Description("Checks if a player is spectating a Bedwars game.")
@Examples({"if loop-player is spectating a Bedwars game:"})
public class CondSpectating extends Condition {

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.getSingle(e) + " is spectating Bedwars game";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) != null) {
            return Spectator.is(player.getSingle(e));
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return false;
        }
    }
}