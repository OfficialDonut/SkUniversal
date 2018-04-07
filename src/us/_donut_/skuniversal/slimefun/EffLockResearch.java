package us._donut_.skuniversal.slimefun;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.mrCookieSlime.Slimefun.Objects.Research;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Slimefun - Lock Research")
@Description("Locks Slimefun research.")
@Examples({"lock the slimefun research with id 2048 for player"})
public class EffLockResearch extends Effect {

    private Expression<Integer> id;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        id = (Expression<Integer>) e[0];
        player = (Expression<Player>) e[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "lock Slimefun research";
    }

    @Override
    protected void execute(Event e) {
        if (id != null && player != null) {
            Research research = Research.getByID(id.getSingle(e));
            research.lock(player.getSingle(e));
        } else {
            Skript.error("Must provide a non-null value, please refer to the syntax");
        }
    }
}
