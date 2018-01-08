package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.MineAPI;
import net.lightshard.prisonmines.mine.Mine;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Name("PrisonMines - All Mines")
@Description("Returns the names of all mines.")
@Examples({"send \"%the names of all mines%\""})
public class ExprMines extends SimpleExpression<String> {

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "names of all mines";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        MineAPI.PrisonMinesAPI prisonMines = new MineAPI.PrisonMinesAPI();
        List<String> names = new ArrayList<>();
        for (Mine mine : prisonMines.getMines()) {
            names.add(mine.getName());
        }
        return names.toArray(new String[names.size()]);
    }
}
