package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("GriefPrevention - Delete Claim")
@Description("Deletes claim(s).")
@Examples({"delete claims with ids (ids of the claims at player)"})
public class EffDeleteClaim extends Effect {

    private Expression<Number> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        id = (Expression<Number>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "delete claim(s) with id(s) " + id.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (id != null) {
            for (Number i : id.getArray(e)) {
                GriefPrevention.instance.dataStore.deleteClaim(GriefPrevention.instance.dataStore.getClaim(i.longValue()));
            }
        } else {
            Skript.error("Must provide a number, please refer to the syntax");
        }
    }

}