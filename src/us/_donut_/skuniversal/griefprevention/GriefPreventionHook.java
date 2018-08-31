package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.DataStore;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.events.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import us._donut_.skuniversal.SkUniversalEvent;
import javax.annotation.Nullable;

public class GriefPreventionHook {

    public static DataStore dataStore = GriefPrevention.instance.dataStore;

    public static Claim getClaim(@Nullable Long id) {
        if (id == null) return null;
        Claim claim = dataStore.getClaim(id);
        if (claim != null) return claim;
        for (Claim aClaim : dataStore.getClaims()) {
            for (Claim childClaim : aClaim.children) {
                if (childClaim.getID().equals(id)) return childClaim;
            }
        }
        return null;
    }

    static {
        Skript.registerEvent("GriefPrevention - Claim Deletion", SkUniversalEvent.class, ClaimDeletedEvent.class, "[G[rief]P[revention]] claim delet(e|ion)")
                .description("Called when a claim is deleted.")
                .examples("on claim deletion:", "\tbroadcast \"Claim %event-number% was deleted!\"");
        EventValues.registerEventValue(ClaimDeletedEvent.class, Number.class, new Getter<Number, ClaimDeletedEvent>() {
            public Number get(ClaimDeletedEvent e) {
                return e.getClaim().getID();
            }
        }, 0);

        Skript.registerEvent("GriefPrevention - Claim Expiration", SkUniversalEvent.class, ClaimExpirationEvent.class, "[G[rief]P[revention]] claim expir(e|ation)")
                .description("Called when a claim expires.")
                .examples("on claim expiration:", "\tbroadcast \"Claim %event-number% has expired!\"");
        EventValues.registerEventValue(ClaimExpirationEvent.class, Number.class, new Getter<Number, ClaimExpirationEvent>() {
            public Number get(ClaimExpirationEvent e) {
                return e.getClaim().getID();
            }
        }, 0);

        Skript.registerEvent("GriefPrevention - Prevent Block Break", SkUniversalEvent.class, PreventBlockBreakEvent.class, "[G[rief]P[revention]] prevent block [from] break[ing]")
                .description("Called when a player tries to break a block.")
                .examples("on prevent block from breaking:", "\tbroadcast \"%player% tried to grief!\"");
        EventValues.registerEventValue(PreventBlockBreakEvent.class, Player.class, new Getter<Player, PreventBlockBreakEvent>() {
            public Player get(PreventBlockBreakEvent e) {
                return e.getInnerEvent().getPlayer();
            }
        }, 0);

        Skript.registerEvent("GriefPrevention - Prevent PvP", SkUniversalEvent.class, PreventPvPEvent.class, "[G[rief]P[revention]] prevent pvp")
                .description("Called when PvP is prevented.")
                .examples("on prevent pvp:", "\tbroadcast \"No PvP allowed!\"");

        Skript.registerEvent("GriefPrevention - Protect Drops", SkUniversalEvent.class, PreventPvPEvent.class, "[G[rief]P[revention]] protect [death] drops")
                .description("Called when death drops are protected.")
                .examples("on protect drops:", "\tbroadcast \"These drops are protected!\"");

        Skript.registerEvent("GriefPrevention - Save Trapped Player", SkUniversalEvent.class, SaveTrappedPlayerEvent.class, "[G[rief]P[revention]] save trapped player")
                .description("Called when a trapped player is saved.")
                .examples("on save trapped player:", "\tbroadcast \"A player was saved from claim %event-number%!\"");
        EventValues.registerEventValue(SaveTrappedPlayerEvent.class, Number.class, new Getter<Number, SaveTrappedPlayerEvent>() {
            public Number get(SaveTrappedPlayerEvent e) {
                return e.getClaim().getID();
            }
        }, 0);
        EventValues.registerEventValue(SaveTrappedPlayerEvent.class, Location.class, new Getter<Location, SaveTrappedPlayerEvent>() {
            public Location get(SaveTrappedPlayerEvent e) {
                return e.getDestination();
            }
        }, 0);
    }

}