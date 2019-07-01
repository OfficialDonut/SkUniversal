package us.donut.skuniversal.advancedban;

import ch.njol.skript.Skript;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import me.leoko.advancedban.manager.PunishmentManager;
import me.leoko.advancedban.manager.UUIDManager;
import us.donut.skuniversal.SkUniversalEvent;

public class AdvancedBanHook {

	public final static PunishmentManager punishmentManager = PunishmentManager.get();
	public final static UUIDManager uuidManager = UUIDManager.get();

	static {
		Skript.registerEvent("AdvancedBan - Punishment", SkUniversalEvent.class, PunishmentEvent.class, "[AdvancedBan] [player] punish[ment]")
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
