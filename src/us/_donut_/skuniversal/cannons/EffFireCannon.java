package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.Cannons;
import at.pavlov.cannons.Enum.InteractAction;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.UUID;

public class EffFireCannon extends ch.njol.skript.lang.Effect {

    private Expression<String> id;
    private Expression<Boolean> autoLoad;
    private Expression<Boolean> useAmmo;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        id = (Expression<String>) e[0];
        autoLoad = (Expression<Boolean>) e[1];
        useAmmo = (Expression<Boolean>) e[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "fire the cannon with id " + id.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (id.getSingle(e) != null) {
            if (autoLoad.getSingle(e) != null && useAmmo.getSingle(e) != null) {
                if (Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))) != null) {
                    Cannons.getPlugin().getFireCannon().fire(Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))), null, autoLoad.getSingle(e), useAmmo.getSingle(e), InteractAction.fireOther);
                }
            } else {
                Skript.error("Must provide a boolean, please refer to the syntax");
            }
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
        }
    }


}
