package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprClaimType extends SimpleExpression<String> {

    private Expression<Number> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<Number>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "type of claim with id " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (id.getSingle(e) != null) {
            Claim claim = GriefPrevention.instance.dataStore.getClaim(id.getSingle(e).longValue());
            if (claim.isAdminClaim()) {
                return new String[]{"admin"};
            } else if (claim.parent != null) {
                return  new String[]{"sub"};
            } else {
                return new String[]{"basic"};
            }
        } else {
            Skript.error("Must provide a number, please refer to the syntax");
            return null;
        }
    }

}