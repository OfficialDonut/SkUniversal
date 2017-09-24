package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.MineAPI;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class EffReset extends Effect {

    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        name = (Expression<String>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "reset mine";
    }

    @Override
    protected void execute(Event e) {
        MineAPI.PrisonMinesAPI prisonMines = new MineAPI.PrisonMinesAPI();
        prisonMines.getByName(name.getSingle(e)).reset();
    }
}
