package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("GriefPrevention - Claim Width")
@Description("Returns the width of a claim.")
@Examples({"send \"%the width of the claim with id (id of the basic claim at player)%\""})
public class ExprClaimWidth extends SimpleExpression<Number> {

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
        return "width of claim with id " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (id.getSingle(e) != null) {
            return new Number[]{GriefPrevention.instance.dataStore.getClaim(id.getSingle(e).longValue()).getWidth()};
        } else {
            Skript.error("Must provide a number, please refer to the syntax");
            return null;
        }
    }

}