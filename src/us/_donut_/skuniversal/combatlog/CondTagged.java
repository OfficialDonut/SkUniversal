package us._donut_.skuniversal.combatlog;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.iiSnipez.CombatLog.CombatLog;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("CombatLog - Is Tagged")
@Description("Checks if a player is combat tagged.")
@Examples({"if %player% is tagged:"})
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
        return "player " + player.toString(e, b) + " is tagged";
    }

    @Override
    public boolean check(Event e) {
        CombatLog cl = ((CombatLog) Bukkit.getPluginManager().getPlugin("CombatLog"));
        return cl.taggedPlayers.containsKey(player.getSingle(e).getName());
    }
}
