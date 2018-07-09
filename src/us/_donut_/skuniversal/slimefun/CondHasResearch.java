package us._donut_.skuniversal.slimefun;

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

    private Expression<OfflinePlayer> player;
    private Expression<Integer> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        id = (Expression<Integer>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " has research " + id.toString(e, b);
    }

    @Override
    public boolean check(Event e) {
        return Research.getByID(id.getSingle(e)).hasUnlocked(player.getSingle(e).getUniqueId());
    }
}
