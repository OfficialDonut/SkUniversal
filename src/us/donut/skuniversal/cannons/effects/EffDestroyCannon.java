package us.donut.skuniversal.cannons.effects;

import at.pavlov.cannons.Enum.BreakCause;
import at.pavlov.cannons.cannon.Cannon;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.UUID;

import static us.donut.skuniversal.cannons.CannonsHook.cannons;

@Name("Cannons - Destroy Cannon")
@Description("Destroys a cannon.")
@Examples({"destroy cannon with id (id of cannon at player)"})
public class EffDestroyCannon extends Effect {

    static {
        Skript.registerEffect(EffDestroyCannon.class, "destroy [the] cannon [with ID] %string%[,] break[ing] blocks %boolean%[,] can explode %boolean%");
    }

    private Expression<String> id;
    private Expression<Boolean> breakBlocks;
    private Expression<Boolean> canExplode;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        breakBlocks = (Expression<Boolean>) e[1];
        canExplode = (Expression<Boolean>) e[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "destroy the cannon with id " + id.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        Cannon cannon;
        if (id.getSingle(e) == null || breakBlocks.getSingle(e) == null || canExplode.getSingle(e) == null || (cannon = cannons.getCannon(UUID.fromString(id.getSingle(e)))) == null) return;
        cannon.destroyCannon(breakBlocks.getSingle(e), canExplode.getSingle(e), BreakCause.Other);
    }
}
