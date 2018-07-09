package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("GriefPrevention - Lesser Corner")
@Description("Returns the lesser corner of a claim.")
@Examples({"send \"%the lesser corner of the claim with id (id of the basic claim at player)%\""})
public class ExprLesserCorner extends SimpleExpression<Location> {

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<Number>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "lesser boundary corner of claim with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        Claim claim = GriefPreventionRegister.getClaim(id.getSingle(e).longValue());
        return claim == null ? null : new Location[]{claim.getLesserBoundaryCorner()};
    }

}