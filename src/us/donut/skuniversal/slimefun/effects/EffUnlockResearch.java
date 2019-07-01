package us.donut.skuniversal.slimefun.effects;

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

@Name("Slimefun - Unlock Research")
@Description("Unlocks Slimefun research.")
@Examples({"unlock the slimefun research with id 2048 for player"})
public class EffUnlockResearch extends Effect {

    static {
        Skript.registerEffect(EffUnlockResearch.class, "unlock [the] [Slimefun] research [with ID] %integer% for %player%");
    }

    private Expression<Integer> id;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        id = (Expression<Integer>) e[0];
        player = (Expression<Player>) e[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "unlock Slimefun research with id " + id.toString(e, b) + " for player " + player.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (id.getSingle(e) == null || player.getSingle(e) == null) return;
        Research research = Research.getByID(id.getSingle(e));
        research.unlock(player.getSingle(e), true);
    }
}
