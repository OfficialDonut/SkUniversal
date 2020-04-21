package us.donut.skuniversal.combatlog.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.combatlog.CombatLogHook.*;

@Name("CombatLog - Tagged Players")
@Description("Returns the currently tagged players.")
@Examples({"send \"%the CombatLog tagged players%\""})
public class ExprTaggedPlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprTaggedPlayers.class, Player.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] [current[ly]] [CombatLog] tagged players");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the tagged players";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        return combatLog.taggedPlayers.keySet().stream().map(Bukkit::getPlayer).toArray(Player[]::new);
    }
}
