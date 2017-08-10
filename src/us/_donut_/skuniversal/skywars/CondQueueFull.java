package us._donut_.skuniversal.skywars;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.daboross.bukkitdev.skywars.api.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class CondQueueFull extends Condition {

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "queue is full";
    }

    @Override
    public boolean check(Event e) {
        SkyWars sw = (SkyWars) Bukkit.getPluginManager().getPlugin("SkyWars");
        return sw.getGameQueue().isQueueFull();
    }
}
