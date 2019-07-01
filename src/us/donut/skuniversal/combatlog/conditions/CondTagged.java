package us.donut.skuniversal.combatlog.conditions;

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

import static us.donut.skuniversal.combatlog.CombatLogHook.*;

@Name("CombatLog - Is Tagged")
@Description("Checks if a player is combat tagged.")
@Examples({"if %player% is tagged:"})
public class CondTagged extends Condition {

    static {
        Skript.registerCondition(CondTagged.class,
                "%player% is [currently] [CombatLog] tagged",
                "%player% is(n't| not) [currently] [CombatLog] tagged");
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
        return "player " + player.toString(e, b) + " is tagged";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return combatLog.taggedPlayers.containsKey(player.getSingle(e).getName()) != isNegated();
    }
}
