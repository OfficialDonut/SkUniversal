package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.MineAPI;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("PrisonMines - Amount mined")
@Description("Returns the amount of blocks mined in a mine.")
@Examples({"send \"%the amount of blocks mined in the mine at player%\""})
public class ExprAmountMined extends SimpleExpression<Number> {

    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "number of blocks mined in mine named " + name.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        MineAPI prisonMines = new MineAPI.PrisonMinesAPI();
        return new Number[]{prisonMines.getBlocksMined(prisonMines.getByName(name.getSingle(e)))};
    }
}
