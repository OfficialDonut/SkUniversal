package us.donut.skuniversal.cannons.effects;

import at.pavlov.cannons.Enum.InteractAction;
import at.pavlov.cannons.cannon.Cannon;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.UUID;

import static us.donut.skuniversal.cannons.CannonsHook.cannons;

@Name("Cannons - Fire Cannon")
@Description("Fires a cannon.")
@Examples({"fire the cannon with id (id of cannon at player), auto load true, consume ammo true"})
public class EffFireCannon extends ch.njol.skript.lang.Effect {

    static {
        Skript.registerEffect(EffFireCannon.class, "fire [the] cannon [with ID] %string%[,] auto[ ]load %boolean%[,] (use|consume) amm(o|unition) %boolean%");
    }

    private Expression<String> id;
    private Expression<Boolean> autoLoad;
    private Expression<Boolean> useAmmo;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        autoLoad = (Expression<Boolean>) e[1];
        useAmmo = (Expression<Boolean>) e[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "fire the cannon with id " + id.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        Cannon cannon;
        if (id.getSingle(e) == null || autoLoad.getSingle(e) == null || useAmmo.getSingle(e) == null || (cannon = cannons.getCannon(UUID.fromString(id.getSingle(e)))) == null) return;
        cannons.getFireCannon().fire(cannon, null, autoLoad.getSingle(e), useAmmo.getSingle(e), InteractAction.fireOther);
    }
}
