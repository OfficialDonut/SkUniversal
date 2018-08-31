package us._donut_.skuniversal.advancedban.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.advancedban.AdvancedBanHook.*;

@Name("AdvancedBan - Is Muted")
@Description("Checks if a player is muted.")
@Examples({"if the player is muted:"})
public class CondMuted extends Condition {

    static {
        Skript.registerCondition(CondMuted.class,
                "%offlineplayer% is muted [by AdvancedBan]",
                "%offlineplayer% is(n't| not) muted [by AdvancedBan]");
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
        return "player" + player.toString(e, b) + " is muted";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return punishmentManager.isMuted(uuidManager.getUUID(player.getSingle(e).getName())) != isNegated();
    }
}
