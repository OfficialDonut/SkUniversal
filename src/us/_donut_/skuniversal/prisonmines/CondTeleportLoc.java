package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.MineAPI;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("PrisonMines - Has Teleport Location")
@Description("Checks if a mine has a teleport location.")
@Examples({"if the mine with name \"cool\" has a teleport location:"})
public class CondTeleportLoc extends Condition {

    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "mine named " + name.toString(e, b) + " has teleport location";
    }

    @Override
    public boolean check(Event e) {
        return new MineAPI.PrisonMinesAPI().getByName(name.getSingle(e)).hasTeleportLocation();
    }
}
