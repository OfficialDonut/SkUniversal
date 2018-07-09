package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.Cannons;
import at.pavlov.cannons.Enum.BreakCause;
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

@Name("Cannons - Destroy Cannon")
@Description("Destroys a cannon.")
@Examples({"destroy cannon with id (id of cannon at player)"})
public class EffDestroyCannon extends Effect {

    private Expression<String> id;
    private Expression<Boolean> breakBlocks;
    private Expression<Boolean> canExplode;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
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
        Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).destroyCannon(breakBlocks.getSingle(e), canExplode.getSingle(e), BreakCause.Other);
    }
}
