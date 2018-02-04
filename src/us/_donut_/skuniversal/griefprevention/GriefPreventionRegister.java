package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.ryanhamshire.GriefPrevention.events.*;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import us._donut_.skuniversal.SkUniversalEvent;

public class GriefPreventionRegister {

    public GriefPreventionRegister() {
        //Expressions
        Skript.registerExpression(ExprClaimIDs.class, Number.class, ExpressionType.SIMPLE, "[[the] ID[s] of] [all [of]] [the] [G[rief]P[revention]] (0¦(basic|normal) |1¦admin |2¦sub[(-| )]|3¦)claim[s] [(4¦at %location%|8¦of %offlineplayer%)]");
        Skript.registerExpression(ExprClaimType.class, String.class, ExpressionType.SIMPLE, "[the] [claim] type of [G[rief]P[revention]] claim [with id] %number%");
        Skript.registerExpression(ExprClaimWorld.class, World.class, ExpressionType.SIMPLE, "[the] world of [G[rief]P[revention]] claim [with id] %number%");
        Skript.registerExpression(ExprExplosionStatus.class, Boolean.class, ExpressionType.SIMPLE, "[the] explosion[s] status of [the] [G[rief]P[revention]] claim [with id] %number%");
        Skript.registerExpression(ExprTrustedPlayers.class, OfflinePlayer.class, ExpressionType.SIMPLE, "(0¦builder|1¦container|2¦access|3¦manager) trusted [players] (of|on) [G[rief]P[revention]] claim [with id] %number%");
        Skript.registerExpression(ExprOwnerOfClaim.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] owner of [the] [G[rief]P[revention]] claim [with id] %number%");
        Skript.registerExpression(ExprLesserCorner.class, Location.class, ExpressionType.SIMPLE, "[the] lesser boundary corner [loc[ation]] of [the] [G[rief]P[revention]] claim [with id] %number%");
        Skript.registerExpression(ExprGreaterCorner.class, Location.class, ExpressionType.SIMPLE, "[the] greater boundary corner [loc[ation]] of [the] [G[rief]P[revention]] claim [with id] %number%");
        Skript.registerExpression(ExprClaimWidth.class, Number.class, ExpressionType.SIMPLE, "[the] width of [the] [G[rief]P[revention]] claim [with id] %number%");
        Skript.registerExpression(ExprClaimHeight.class, Number.class, ExpressionType.SIMPLE, "[the] height of [the] [G[rief]P[revention]] claim [with id] %number%");
        Skript.registerExpression(ExprAccruedClaimBlocks.class, Number.class, ExpressionType.COMBINED, "[the] [G[rief]P[revention]] accrued claim blocks of %offlineplayer%", "%offlineplayer%'s [G[rief]P[revention]] accrued claim blocks");
        Skript.registerExpression(ExprClaimBlockLimit.class, Number.class, ExpressionType.COMBINED, "[the] [G[rief]P[revention]] claim block limit of %offlineplayer%", "%offlineplayer%'s [G[rief]P[revention]] claim block limit");
        Skript.registerExpression(ExprRemainingClaimBlocks.class, Number.class, ExpressionType.COMBINED, "[the] remaining [G[rief]P[revention]] claim blocks of %offlineplayer%", "%offlineplayer%'s remaining [G[rief]P[revention]] claim blocks");
        Skript.registerExpression(ExprBonusClaimBlocks.class, Number.class, ExpressionType.COMBINED, "[the] bonus [G[rief]P[revention]] claim blocks of %offlineplayer%", "%offlineplayer%'s bonus [G[rief]P[revention]] claim blocks");

        //Effects
        Skript.registerEffect(EffDeleteClaim.class, "(delete|remove) [the] [G[rief]P[revention]] claim[s] [with id[s]] %numbers%");

        //Events
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