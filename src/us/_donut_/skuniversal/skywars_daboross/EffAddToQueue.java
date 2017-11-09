package us._donut_.skuniversal.skywars_daboross;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.daboross.bukkitdev.skywars.api.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class EffAddToQueue extends Effect {

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        player = (Expression<Player>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "add player " + player.getSingle(e) + " to queue";
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) != null) {
            SkyWars sw = (SkyWars) Bukkit.getPluginManager().getPlugin("SkyWars");
            if (!sw.getGameQueue().inQueue(player.getSingle(e).getUniqueId())) {
                sw.getGameQueue().queuePlayer(player.getSingle(e));
            }
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
        }
    }
}
