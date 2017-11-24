package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprClaimHeight extends SimpleExpression<Number> {

    private Expression<Number> id;

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<Number>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "height of claim with id " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (id.getSingle(e) != null) {
            return new Number[]{GriefPrevention.instance.dataStore.getClaim(id.getSingle(e).longValue()).getHeight()};
        } else {
            Skript.error("Must provide a number, please refer to the syntax");
            return null;
        }
    }

}