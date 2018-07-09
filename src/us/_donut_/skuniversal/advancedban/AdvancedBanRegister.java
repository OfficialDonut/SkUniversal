package us._donut_.skuniversal.advancedban;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import org.bukkit.OfflinePlayer;
import us._donut_.skuniversal.SkUniversalEvent;

public class AdvancedBanRegister {

   public AdvancedBanRegister() {
        //Conditions
        Skript.registerCondition(CondBanned.class, "%offlineplayer% is banned [by AdvancedBan]");
        Skript.registerCondition(CondMuted.class, "%offlineplayer% is muted [by AdvancedBan]");

        //Expressions
        Skript.registerExpression(ExprWarns.class, Number.class, ExpressionType.COMBINED, "[the] [(number|amount) of] [AdvancedBan] warns of %offlineplayer%", "%offlineplayer%'s [(number|amount) of] [AdvancedBan] warns");
        Skript.registerExpression(ExprPunishments.class, String.class, ExpressionType.COMBINED, "[the] [active] [AdvancedBan] punishments of %offlineplayer%", "%offlineplayer%'s [active] [AdvancedBan] punishments");
        Skript.registerExpression(ExprBanReason.class, String.class, ExpressionType.COMBINED, "[the] [AdvancedBan] ban reason of %offlineplayer%", "%offlineplayer%'s [AdvancedBan] ban reason");
        Skript.registerExpression(ExprBanTime.class, String.class, ExpressionType.COMBINED, "[the] remaining [AdvancedBan] ban time of %offlineplayer%", "%offlineplayer%'s remaining [AdvancedBan] ban time");
        Skript.registerExpression(ExprBanner.class, String.class, ExpressionType.COMBINED, "[the] [AdvancedBan] banner of %offlineplayer%", "%offlineplayer%'s [AdvancedBan] banner");
        Skript.registerExpression(ExprMuteReason.class, String.class, ExpressionType.COMBINED, "[the] [AdvancedBan] mute reason of %offlineplayer%", "%offlineplayer%'s [AdvancedBan] mute reason");
        Skript.registerExpression(ExprMuteTime.class, String.class, ExpressionType.COMBINED, "[the] remaining [AdvancedBan] mute time of %offlineplayer%", "%offlineplayer%'s remaining [AdvancedBan] mute time");
        Skript.registerExpression(ExprMuter.class, String.class, ExpressionType.COMBINED, "[the] [AdvancedBan] muter of %offlineplayer%", "%offlineplayer%'s [AdvancedBan] muter");
        Skript.registerExpression(ExprPunishedPlayer.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] [AdvancedBan] punished player");
        Skript.registerExpression(ExprPunisher.class, String.class, ExpressionType.SIMPLE, "[the] [AdvancedBan] punisher");
        Skript.registerExpression(ExprPunishmentType.class, String.class, ExpressionType.SIMPLE, "[the] [AdvancedBan] punish[ment] type");
        Skript.registerExpression(ExprPunishReason.class, String.class, ExpressionType.SIMPLE, "[the] [AdvancedBan] punish[ment] reason");
        Skript.registerExpression(ExprPunishmentLength.class, String.class, ExpressionType.SIMPLE, "[the] [AdvancedBan] punish[ment] (length|duration)");

        //Events
        Skript.registerEvent("AdvancedBan - Punishment", SkUniversalEvent.class, PunishmentEvent.class, "[AdvancedBan] punish[ment]")
				.description("Called when a player is punished.\n\n" +
						"**Event Expressions:**\n" +
						"`[the] [AdvancedBan] punished player`\n" +
						"`[the] [AdvancedBan] punisher`\n" +
						"`[the] [AdvancedBan] punish[ment] type`\n" +
						"`[the] [AdvancedBan] punish[ment] reason`\n" +
						"`[the] [AdvancedBan] punish[ment] (length|duration)`")
				.examples("on punishment:", "\tif punish type is \"mute\":", "\t\tbroadcast \"%punished player% has been muted by %punisher% for %punish duration% with reason %punish reason%!\"");
    }
}
