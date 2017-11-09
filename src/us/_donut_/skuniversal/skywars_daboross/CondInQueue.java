package us._donut_.skuniversal.skywars_daboross;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.daboross.bukkitdev.skywars.api.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class CondInQueue extends Condition {

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.getSingle(e) + " is in queue";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) != null) {
            SkyWars sw = (SkyWars) Bukkit.getPluginManager().getPlugin("SkyWars");
            return sw.getGameQueue().inQueue(player.getSingle(e).getUniqueId());
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return false;
        }
    }
}
