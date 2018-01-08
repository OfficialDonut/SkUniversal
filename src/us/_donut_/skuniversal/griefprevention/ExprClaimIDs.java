package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Name("GriefPrevention - Claim IDs")
@Description("Returns the claim IDs of claims.")
@Examples({"send \"%the ids of the basic claims of player%\""})
public class ExprClaimIDs extends SimpleExpression<Number> {

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
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
        Collection<Claim> allClaims = GriefPrevention.instance.dataStore.getClaims();
        List<Claim> claimsThatAreCorrectType = new ArrayList<>();
        for (Claim claim : allClaims) {
            if (claimType.equalsIgnoreCase("normal") && !claim.isAdminClaim() && claim.parent == null) {
                claimsThatAreCorrectType.add(claim);
            }
            if (claimType.equalsIgnoreCase("admin") && claim.isAdminClaim()) {
                claimsThatAreCorrectType.add(claim);
            }
            if (claimType.equalsIgnoreCase("sub-claim") && claim.parent != null) {
                claimsThatAreCorrectType.add(claim);
            }
            if (claimType.equalsIgnoreCase("all")) {
                claimsThatAreCorrectType.add(claim);
            }
        }

        List<Number> claimIDs = new ArrayList<>();
        for (Claim claim : claimsThatAreCorrectType) {
            if (location != null) {
                if (claim.contains(location.getSingle(e), true, true)) {
                    claimIDs.add(claim.getID());
                }
            } else if (player != null) {
                if (claim.ownerID.equals(player.getSingle(e).getUniqueId())) {
                    claimIDs.add(claim.getID());
                }
            } else {
                claimIDs.add(claim.getID());
            }
        }

        return claimIDs.toArray(new Number[claimIDs.size()]);
    }

}