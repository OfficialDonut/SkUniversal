package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ExprAllClaimIDs extends SimpleExpression<Number> {

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "ids of all claims";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        List<Number> ids = new ArrayList<>();
        for (Claim claim : GriefPrevention.instance.dataStore.getClaims()) {
            ids.add(claim.getID());
        }
        return ids.toArray(new Number[ids.size()]);
    }

}