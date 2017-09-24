package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.lightshard.prisonmines.MineAPI;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class CondResetting extends Condition {

    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "mine has teleport location";
    }

    @Override
    public boolean check(Event e) {
        MineAPI.PrisonMinesAPI prisonMines = new MineAPI.PrisonMinesAPI();
        return prisonMines.getByName(name.getSingle(e)).isResetting();
    }
}
