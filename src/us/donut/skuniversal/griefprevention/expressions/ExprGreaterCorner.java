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
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.griefprevention.GriefPreventionHook.getClaim;

@Name("GriefPrevention - Greater Corner")
@Description("Returns the greater corner of a claim.")
@Examples({"send \"%the greater corner of the claim with id (id of the basic claim at player)%\""})
public class ExprGreaterCorner extends SimpleExpression<Location> {

    static {
        Skript.registerExpression(ExprGreaterCorner.class, Location.class, ExpressionType.COMBINED, "[the] greater [boundary] corner [loc[ation]] of [the] [G[rief]P[revention]] claim [with ID] %number%");
    }

    private Expression<Number> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<Number>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "greater boundary corner of claim with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        Claim claim = getClaim(id.getSingle(e).longValue());
        return claim == null ? null : new Location[]{claim.getGreaterBoundaryCorner()};
    }

}