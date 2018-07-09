package us._donut_.skuniversal.combatlog;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.iiSnipez.CombatLog.CombatLog;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("CombatLog - Tagged Players")
@Description("Returns the currently tagged players.")
@Examples({"send \"%the CombatLog tagged players%\""})
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
    public String toString(@Nullable Event e, boolean b) {
        return "the tagged players";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        CombatLog cl = (CombatLog) Bukkit.getPluginManager().getPlugin("CombatLog");
        return cl.taggedPlayers.keySet().stream().map(Bukkit::getPlayer).toArray(Player[]::new);
    }
}
