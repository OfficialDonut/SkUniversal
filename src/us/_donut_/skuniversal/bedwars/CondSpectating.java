package us._donut_.skuniversal.bedwars;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import me.MineHome.Bedwars.Spectator.Spectator;
import javax.annotation.Nullable;

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
        return "player is spectating Bedwars game";
    }

    @Override
    public boolean check(Event e) {
        return Spectator.is(player.getSingle(e));
    }
}