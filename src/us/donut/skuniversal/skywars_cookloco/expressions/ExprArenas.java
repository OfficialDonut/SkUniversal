package us.donut.skuniversal.skywars_cookloco.expressions;

import ak.CookLoco.SkyWars.arena.Arena;
import ak.CookLoco.SkyWars.arena.ArenaManager;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("SkyWars (CookLoco) - All arenas")
@Description("Returns the names of all skywars arenas.")
@Examples({"set {arenas::*} to all skywars arenas"})
public class ExprArenas extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprArenas.class, String.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] [SkyWars] arenas");
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
    public String toString(@Nullable Event e, boolean b) {
        return "the SkyWars arenas";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return ArenaManager.getGames().stream().map(Arena::getName).toArray(String[]::new);
    }
}
