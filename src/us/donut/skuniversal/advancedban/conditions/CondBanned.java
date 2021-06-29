package us.donut.skuniversal.advancedban.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.advancedban.AdvancedBanHook.punishmentManager;
import static us.donut.skuniversal.advancedban.AdvancedBanHook.uuidManager;

@Name("AdvancedBan - Is Banned")
@Description("Checks if a player is banned.")
@Examples({"if the player is banned:"})
public class CondBanned extends Condition {

    static {
        Skript.registerCondition(CondBanned.class,
                "%offlineplayer% is banned [by AdvancedBan]",
                "%offlineplayer% is(n't| not) banned [by AdvancedBan]");
    }

    private Expression<OfflinePlayer> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " is banned";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return punishmentManager.isBanned(uuidManager.getUUID(player.getSingle(e).getName())) != isNegated();
    }
}
