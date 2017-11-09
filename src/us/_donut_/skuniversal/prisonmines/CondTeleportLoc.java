package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.MineAPI;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

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
        return "mine named " + name.getSingle(e) + " has teleport location";
    }

    @Override
    public boolean check(Event e) {
        if (name.getSingle(e) != null) {
            MineAPI.PrisonMinesAPI prisonMines = new MineAPI.PrisonMinesAPI();
            return prisonMines.getByName(name.getSingle(e)).hasTeleportLocation();
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return false;
        }
    }
}
