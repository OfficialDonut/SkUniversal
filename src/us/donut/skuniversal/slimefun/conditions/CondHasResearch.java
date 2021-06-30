package us.donut.skuniversal.slimefun.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import us.donut.skuniversal.slimefun.SlimefunHook;

import javax.annotation.Nullable;
import java.util.Optional;

@Name("Slimefun - Has Unlocked Research")
@Description("Checks if a player has a research unlocked.")
@Examples({"if the player has unlocked the research with id \"cool_research\":"})
public class CondHasResearch extends Condition {

    static {
        Skript.registerCondition(CondHasResearch.class,
                "%offlineplayer% has [unlocked] [the] [Slimefun] research [with ID] %string%",
                "%offlineplayer% has(n't| not) [unlocked] [the] [Slimefun] research [with ID] %string%");
    }

    private Expression<OfflinePlayer> player;
    private Expression<String> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        id = (Expression<String>) e[1];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " has research " + id.toString(e, b);
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null || id.getSingle(e) == null) return isNegated();
        Optional<PlayerProfile> profile = PlayerProfile.find(player.getSingle(e));
        if (profile.isPresent()) {
            return profile.get().hasUnlocked(SlimefunHook.getResearch(id.getSingle(e))) != isNegated();
        }
        return isNegated();
    }
}
