package us._donut_.skuniversal.skywars_cookloco;

import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.arena.ArenaManager;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.Arrays;

@Name("SkyWars (CookLoco) - All arenas")
@Description("Returns the names of all skywars arenas.")
@Examples({"set {arenas::*} to all skywars arenas"})
public class ExprArenas extends SimpleExpression<String> {

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
    public String toString(@Nullable Event e, boolean b) {
        return "the SkyWars arenas";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return Arrays.stream(ArenaManager.getGames()).map(Arena::getName).toArray(String[]::new);
    }
}
