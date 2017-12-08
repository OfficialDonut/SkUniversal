package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.ryanhamshire.GriefPrevention.events.*;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import us._donut_.skuniversal.SkUniversalEvent;

public class GriefPreventionRegister {

    public static void registerGriefPrevention() {
        //Expressions
        Skript.registerExpression(ExprAllClaimIDs.class, Number.class, ExpressionType.SIMPLE, "[the] IDs of [all] [the] [GriefPrevention] claims");
        Skript.registerExpression(ExprClaimIDsOfPlayer.class, Number.class, ExpressionType.SIMPLE, "[the] IDs of [all] [the] [GriefPrevention] claims of %offlineplayer%");
        Skript.registerExpression(ExprOwnerOfClaim.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] owner of [the] [GriefPrevention] claim [with id] %number%");
        Skript.registerExpression(ExprLesserCorner.class, Location.class, ExpressionType.SIMPLE, "[the] lesser boundary corner [loc[ation]] of [the] [GriefPrevention] claim [with id] %number%");
        Skript.registerExpression(ExprGreaterCorner.class, Location.class, ExpressionType.SIMPLE, "[the] greater boundary corner [loc[ation]] of [the] [GriefPrevention] claim [with id] %number%");
        Skript.registerExpression(ExprClaimWidth.class, Number.class, ExpressionType.SIMPLE, "[the] width of [the] [GriefPrevention] claim [with id] %number%");
        Skript.registerExpression(ExprClaimHeight.class, Number.class, ExpressionType.SIMPLE, "[the] height of [the] [GriefPrevention] claim [with id] %number%");
        Skript.registerExpression(ExprAccruedClaimBlocks.class, Number.class, ExpressionType.COMBINED, "[the] [GriefPrevention] accrued claim blocks of %offlineplayer%", "%offlineplayer%'s [GriefPrevention] accrued claim blocks");
        Skript.registerExpression(ExprClaimBlockLimit.class, Number.class, ExpressionType.COMBINED, "[the] [GriefPrevention] claim block limit of %offlineplayer%", "%offlineplayer%'s [GriefPrevention] claim block limit");
        Skript.registerExpression(ExprRemainingClaimBlocks.class, Number.class, ExpressionType.COMBINED, "[the] remaining [GriefPrevention] claim blocks of %offlineplayer%", "%offlineplayer%'s remaining [GriefPrevention] claim blocks");
        Skript.registerExpression(ExprBonusClaimBlocks.class, Number.class, ExpressionType.COMBINED, "[the] bonus [GriefPrevention] claim blocks of %offlineplayer%", "%offlineplayer%'s bonus [GriefPrevention] claim blocks");

        //Events
        Skript.registerEvent("GriefPrevention Claim Deletion", SkUniversalEvent.class, ClaimDeletedEvent.class, "[GriefPrevention] claim delet(e|ion)");
        EventValues.registerEventValue(ClaimDeletedEvent.class, Number.class, new Getter<Number, ClaimDeletedEvent>() {
            public Number get(ClaimDeletedEvent e) {
                return e.getClaim().getID();
            }
        }, 0);
        Skript.registerEvent("GriefPrevention Claim Expiration", SkUniversalEvent.class, ClaimExpirationEvent.class, "[GriefPrevention] claim expir(e|ation)");
        EventValues.registerEventValue(ClaimExpirationEvent.class, Number.class, new Getter<Number, ClaimExpirationEvent>() {
            public Number get(ClaimExpirationEvent e) {
                return e.getClaim().getID();
            }
        }, 0);
        Skript.registerEvent("GriefPrevention Prevent Block Break", SkUniversalEvent.class, PreventBlockBreakEvent.class, "[GriefPrevention] prevent block [from] break[ing]");
        EventValues.registerEventValue(PreventBlockBreakEvent.class, Player.class, new Getter<Player, PreventBlockBreakEvent>() {
            public Player get(PreventBlockBreakEvent e) {
                return e.getInnerEvent().getPlayer();
            }
        }, 0);
        Skript.registerEvent("GriefPrevention Prevent PvP", SkUniversalEvent.class, PreventPvPEvent.class, "[GriefPrevention] prevent pvp");
        Skript.registerEvent("GriefPrevention Protect Drops", SkUniversalEvent.class, PreventPvPEvent.class, "[GriefPrevention] protect [death] drops");
        Skript.registerEvent("GriefPrevention Save Trapped Player", SkUniversalEvent.class, SaveTrappedPlayerEvent.class, "[GriefPrevention] save trapped player");
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