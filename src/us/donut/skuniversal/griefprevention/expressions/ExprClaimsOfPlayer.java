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
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.griefprevention.GriefPreventionHook.dataStore;

@Name("GriefPrevention - Claim IDs of Player")
@Description("Returns the claim IDs of the claims of a player.")
@Examples({"send \"%the ids of the basic claims of player%\""})
public class ExprClaimsOfPlayer extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprClaimsOfPlayer.class, Number.class, ExpressionType.COMBINED, "[(all [[of] the]|the)]] [IDs of [all] [the]] [G[rief]P[revention]] (0¦(basic|normal) |1¦admin |2¦sub[(-| )]|3¦)claim[s] of %offlineplayer%");
    }

    private Expression<OfflinePlayer> player;
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
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "claim IDs of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return dataStore.getClaims()
                .stream()
                .filter(claim -> player.getSingle(e).getUniqueId().equals(claim.ownerID))
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
