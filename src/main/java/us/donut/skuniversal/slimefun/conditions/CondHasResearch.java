package us.donut.skuniversal.slimefun.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.mrCookieSlime.Slimefun.Objects.Research;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Slimefun - Has Unlocked Research")
@Description("Checks if a player has a research unlocked.")
@Examples({"if the player has unlocked the research with id 2048:"})
public class CondHasResearch extends Condition {

    static {
        Skript.registerCondition(CondHasResearch.class,
                "offlineplayer% has [unlocked] [the] [Slimefun] research [with ID] %integer%",
                "offlineplayer% has(n't| not) [unlocked] [the] [Slimefun] research [with ID] %integer%");
    }

    private Expression<OfflinePlayer> player;
    private Expression<Integer> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        id = (Expression<Integer>) e[1];
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
        return Research.getByID(id.getSingle(e)).hasUnlocked(player.getSingle(e).getUniqueId()) != isNegated();
    }
}
