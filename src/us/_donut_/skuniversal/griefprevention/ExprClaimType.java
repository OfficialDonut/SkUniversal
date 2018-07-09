package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("GriefPrevention - Claim Type")
@Description("Returns the type of a claim.")
@Examples({"send \"%the type of the claim with id (id of the basic claim at player)%\""})
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
    public String toString(@Nullable Event e, boolean b) {
        return "type of claim with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        Claim claim = GriefPreventionRegister.getClaim(id.getSingle(e).longValue());
        if (claim == null) {
            return null;
        } else if (claim.isAdminClaim()) {
            return new String[]{"admin"};
        } else if (claim.parent != null) {
            return new String[]{"sub"};
        } else {
            return new String[]{"basic"};
        }
    }

}