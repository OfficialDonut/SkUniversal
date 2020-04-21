package us.donut.skuniversal.cannons.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.cannons.CannonsHook.*;

@Name("Cannons - Cannon Limit")
@Description("Returns the cannon limit of a player.")
@Examples({"send \"%the cannon limit of %player%%\""})
public class ExprCannonLimit extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprCannonLimit.class, Number.class, ExpressionType.COMBINED,
                "[the] cannon [build] limit of %player%",
                "%player%'s cannon [build] limit");
    }

    private Expression<Player> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the cannon limit of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{cannons.getCannonManager().getCannonBuiltLimit(player.getSingle(e))};
    }

}
