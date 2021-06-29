package us.donut.skuniversal.prisonmines.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.mine.Mine;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.prisonmines.PrisonMinesHook.mineAPI;

@Name("PrisonMines - All Mines")
@Description("Returns the names of all mines.")
@Examples({"send \"%the names of all mines%\""})
public class ExprMines extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprMines.class, String.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] [names of [all [the]]] [PrisonMines] mines");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "names of all mines";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return mineAPI.getMines().stream().map(Mine::getName).toArray(String[]::new);
    }
}
