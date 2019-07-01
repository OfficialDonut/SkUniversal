package us.donut.skuniversal.griefprevention.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.griefprevention.GriefPreventionHook.*;

@Name("GriefPrevention - Explosion Status")
@Description("Returns the explosion status of a claim.")
@Examples({"send \"%the explosion status of the claim with id (id of the basic claim at player)%\""})
public class ExprExplosionStatus extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprExplosionStatus.class, Boolean.class, ExpressionType.COMBINED, "[the] explosion[s] status of [the] [G[rief]P[revention]] claim [with ID] %number%");
    }

    private Expression<Number> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<Number>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "explosion status of claim with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected Boolean[] get(Event e) {
        Claim claim = getClaim(id.getSingle(e).longValue());
        return claim == null ? null : new Boolean[]{claim.areExplosivesAllowed};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Boolean status = (Boolean) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            Claim claim = getClaim(id.getSingle(e).longValue());
            if (claim == null) return;
            claim.areExplosivesAllowed = status;
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(Number.class) : null;
    }

}