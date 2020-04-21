package us.donut.skuniversal.griefprevention.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.griefprevention.GriefPreventionHook.*;

@Name("GriefPrevention - Delete Claim")
@Description("Deletes claim(s).")
@Examples({"delete claims with ids (ids of the claims at player)"})
public class EffDeleteClaim extends Effect {

    static {
        Skript.registerEffect(EffDeleteClaim.class, "(delete|remove) [the] [G[rief]P[revention]] claim[s] [with ID[s]] %numbers%");
    }

    private Expression<Number> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        id = (Expression<Number>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "delete claim(s) with id(s) " + id.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (id.getArray(e) == null) return;
        for (Number claimID : id.getArray(e)) {
            Claim claim = getClaim(claimID.longValue());
            if (claim == null) return;
            dataStore.deleteClaim(claim);
        }
    }

}