package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.MineAPI;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprPercentMined extends SimpleExpression<Number> {

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
    public String toString(@Nullable Event e, boolean arg1) {
        return "percentage of blocks mined";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        MineAPI.PrisonMinesAPI prisonMines = new MineAPI.PrisonMinesAPI();
        return new Number[]{prisonMines.getPercentMined(prisonMines.getByName(name.getSingle(e)))};
    }
}
