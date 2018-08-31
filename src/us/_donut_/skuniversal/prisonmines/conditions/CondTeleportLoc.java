package us._donut_.skuniversal.prisonmines.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.mine.Mine;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.prisonmines.PrisonMinesHook.mineAPI;

@Name("PrisonMines - Has Teleport Location")
@Description("Checks if a mine has a teleport location.")
@Examples({"if the mine with name \"cool\" has a teleport location:"})
public class CondTeleportLoc extends Condition {

    static {
        Skript.registerCondition(CondTeleportLoc.class,
                "[the] [PrisonMines] mine [(named|with name)] %string% has [a] (teleport|tp) loc[ation]",
                "[the] [PrisonMines] mine [(named|with name)] %string% does(n't| not) have [a] (teleport|tp) loc[ation]");
    }

    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "mine named " + name.toString(e, b) + " has teleport location";
    }

    @Override
    public boolean check(Event e) {
        Mine mine;
        if (name.getSingle(e) == null || (mine = mineAPI.getByName(name.getSingle(e))) == null) return isNegated();
        return mine.hasTeleportLocation() != isNegated();
    }
}
