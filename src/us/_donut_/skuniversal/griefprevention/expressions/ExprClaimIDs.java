package us._donut_.skuniversal.griefprevention.expressions;

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
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static us._donut_.skuniversal.griefprevention.GriefPreventionHook.*;

@Name("GriefPrevention - Claim IDs")
@Description("Returns the claim IDs of claims.")
@Examples({"send \"%the ids of the basic claims of player%\""})
public class ExprClaimIDs extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprClaimIDs.class, Number.class, ExpressionType.COMBINED, "[(all [[of] the]|the)]] [IDs of [all] [the]] [G[rief]P[revention]] (0¦(basic|normal) |1¦admin |2¦sub[(-| )]|3¦)claim[s] [(4¦at %location%|8¦of %offlineplayer%)]");

    }

    private Expression<Location> location;
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
            case 0: claimType = "normal";
                    break;
            case 1: claimType = "admin";
                    break;
            case 2: claimType = "sub-claim";
                    break;
            case 3: claimType = "all";
                    break;
            case 4: claimType = "normal";
                    location = (Expression<Location>) e[0];
                    break;
            case 5: claimType = "admin";
                    location = (Expression<Location>) e[0];
                    break;
            case 6: claimType = "sub-claim";
                    location = (Expression<Location>) e[0];
                  break;
            case 7: claimType = "all";
                    location = (Expression<Location>) e[0];
                    break;
            case 8: claimType = "normal";
                    player = (Expression<OfflinePlayer>) e[1];
                    break;
            case 9: claimType = "admin";
                    player = (Expression<OfflinePlayer>) e[1];
                    break;
            case 10: claimType = "sub-claim";
                    player = (Expression<OfflinePlayer>) e[1];
                    break;
            case 11: claimType = "all";
                    player = (Expression<OfflinePlayer>) e[1];
                    break;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg) {
        return "claim IDs";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        List<Claim> claims = new ArrayList<>(dataStore.getClaims());
        for (Claim claim : dataStore.getClaims()) {
            claims.addAll(claim.children);
        }

        if (claimType.equalsIgnoreCase("normal"))
            claims = claims.stream().filter(claim -> !claim.isAdminClaim() && claim.parent == null).collect(Collectors.toList());
        else if (claimType.equalsIgnoreCase("admin"))
            claims = claims.stream().filter(Claim::isAdminClaim).collect(Collectors.toList());
        else if (claimType.equalsIgnoreCase("sub-claim"))
            claims = claims.stream().filter(claim -> claim.parent != null).collect(Collectors.toList());

        if (location != null) {
            if (location.getSingle(e) == null) return null;
            claims = claims.stream().filter(claim -> claim.contains(location.getSingle(e), true, false)).collect(Collectors.toList());
        } else if (player != null) {
            if (player.getSingle(e) == null) return null;
            claims = claims.stream().filter(claim -> claim.ownerID.equals(player.getSingle(e).getUniqueId())).collect(Collectors.toList());
        }

        return claims.stream().map(Claim::getID).toArray(Number[]::new);
    }

}