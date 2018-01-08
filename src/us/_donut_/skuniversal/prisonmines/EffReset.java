package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.MineAPI;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("PrisonMines - Reset Mine")
@Description("Resets a mine.")
@Examples({"reset the mine named \"cool\""})
public class EffReset extends Effect {

    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        name = (Expression<String>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "reset mine named " + name.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (name.getSingle(e) != null) {
            MineAPI.PrisonMinesAPI prisonMines = new MineAPI.PrisonMinesAPI();
            prisonMines.getByName(name.getSingle(e)).reset();
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
        }
    }
}
