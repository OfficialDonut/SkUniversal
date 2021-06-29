package us.donut.skuniversal.griefprevention.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.griefprevention.GriefPreventionHook.getClaim;

@Name("GriefPrevention - Claim Type")
@Description("Returns the type of a claim.")
@Examples({"send \"%the type of the claim with id (id of the basic claim at player)%\""})
public class ExprClaimType extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprClaimType.class, String.class, ExpressionType.COMBINED, "[the] [claim] type of [the] [G[rief]P[revention]] claim [with ID] %number%");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
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
        Claim claim = getClaim(id.getSingle(e).longValue());
        if (claim == null) return null;
        if (claim.isAdminClaim()) return new String[]{"admin"};
        if (claim.parent != null) return new String[]{"sub"};
        return new String[]{"basic"};
    }

}