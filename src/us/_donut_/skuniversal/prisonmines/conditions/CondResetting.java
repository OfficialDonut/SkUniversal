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

import static us._donut_.skuniversal.prisonmines.PrisonMinesHook.*;

@Name("PrisonMines - Is Resetting")
@Description("Checks if a mine is currently resetting.")
@Examples({"if the mine with name \"cool\" is resetting:"})
public class CondResetting extends Condition {

    static {
        Skript.registerCondition(CondResetting.class,
                "[the] [PrisonMines] mine [(named|with name)] %string% is (resetting|being reset)",
                "[the] [PrisonMines] mine [(named|with name)] %string% is(n't| not) (resetting|being reset)");
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
        return "mine named " + name.toString(e, b) + " is resetting";
    }

    @Override
    public boolean check(Event e) {
        Mine mine;
        if (name.getSingle(e) == null || (mine = mineAPI.getByName(name.getSingle(e))) == null) return isNegated();
        return mine.isResetting() != isNegated();
    }
}
