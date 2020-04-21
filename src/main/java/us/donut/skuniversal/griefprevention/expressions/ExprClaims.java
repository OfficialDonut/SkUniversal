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
import us.donut.skuniversal.griefprevention.GriefPreventionHook;

import javax.annotation.Nullable;

@Name("GriefPrevention - All Claim IDs")
@Description("Returns the all claim IDs")
@Examples({"send \"%the ids of all basic claims\""})
public class ExprClaims extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprClaims.class, Number.class, ExpressionType.COMBINED, "[(all [[of] the]|the)]] [IDs of [all] [the]] [G[rief]P[revention]] (0¦(basic|normal) |1¦admin |2¦sub[(-| )]|3¦)claim[s]");
    }

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
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "all claim IDs";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return GriefPreventionHook.dataStore.getClaims()
                .stream()
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
