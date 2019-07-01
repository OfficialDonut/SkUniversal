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

import static us.donut.skuniversal.griefprevention.GriefPreventionHook.dataStore;

@Name("GriefPrevention - Claim IDs at Location")
@Description("Returns the claim IDs of claims at a location.")
@Examples({"send \"%the ids of the basic claims at player%\""})
public class ExprClaimsAtLoc extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprClaimsAtLoc.class, Number.class, ExpressionType.COMBINED, "[(all [[of] the]|the)]] [IDs of [all] [the]] [G[rief]P[revention]] (0¦(basic|normal) |1¦admin |2¦sub[(-| )]|3¦)claim[s] at %location%");
    }

    private Expression<Location> location;
    private String claimType;

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        switch (pr.mark) {
            case 0: claimType = "normal"; break;
            case 1: claimType = "admin"; break;
            case 2: claimType = "sub-claim"; break;
            default: claimType = "all"; break;
        }
        location = (Expression<Location>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "claim IDs at location " + location.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (location.getSingle(e) == null) return null;
        return dataStore.getClaims()
                .stream()
                .filter(claim -> claim.contains(location.getSingle(e), true, false))
                .filter(claim -> {
                    switch (claimType) {
                        case "normal":
                            return !claim.isAdminClaim() && claim.parent == null;
                        case "admin":
                            return claim.isAdminClaim();
                        case "sub-claim":
                            return claim.parent != null;
                        default:
                            return true;
                    }
                })
                .map(Claim::getID).toArray(Number[]::new);
    }
}
