package us.donut.skuniversal.prisonmines.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.mine.Mine;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.prisonmines.PrisonMinesHook.*;

@Name("PrisonMines - Reset Mine")
@Description("Resets a mine.")
@Examples({"reset the mine named \"cool\""})
public class EffReset extends Effect {

    static {
        Skript.registerEffect(EffReset.class, "reset [the] [PrisonMines] mine [(named|with name)] %string%");
    }

    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        name = (Expression<String>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "reset mine named " + name.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        Mine mine;
        if (name.getSingle(e) == null || (mine = mineAPI.getByName(name.getSingle(e))) == null) return;
        mine.reset();
    }
}
