package us._donut_.skuniversal.combatlog;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.iiSnipez.CombatLog.CombatLog;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExprTaggedPlayers extends SimpleExpression<Player> {

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the tagged players";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        CombatLog cl = (CombatLog) Bukkit.getPluginManager().getPlugin("CombatLog");
        List<Player> players = new ArrayList<>();
        for (Map.Entry<String, Long> entry : cl.taggedPlayers.entrySet()) {
            players.add(Bukkit.getServer().getPlayer(entry.getKey()));
        }
        return players.toArray(new Player[players.size()]);
    }
}
