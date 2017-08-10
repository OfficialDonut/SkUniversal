package us._donut_.skuniversal.combatlog;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.iiSnipez.CombatLog.CombatLog;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class CondTagged extends Condition {

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player is tagged";
    }

    @Override
    public boolean check(Event e) {
        CombatLog cl = ((CombatLog) Bukkit.getPluginManager().getPlugin("CombatLog"));
        return !cl.taggedPlayers.isEmpty() && cl.taggedPlayers.containsKey(player.getSingle(e).getName());
    }
}
