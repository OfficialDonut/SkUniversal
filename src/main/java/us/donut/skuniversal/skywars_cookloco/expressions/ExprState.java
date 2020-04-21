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

@Name("SkyWars (CookLoco) - Arena State")
@Description("Returns the state of a skywars arena.")
@Examples({"set {state} to the state of the skywars arena named \"cool\""})
public class ExprState extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprState.class, String.class, ExpressionType.COMBINED,
                "[the] [current] [game] state of [the] [SkyWars] arena [(named|with name)] %string%",
                "[the] [SkyWars] arena [(named|with name)] %string%'s [current] [game] state");
    }

    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "state of SkyWars arena named " + name.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        Arena arena;
        if (name.getSingle(e) == null || (arena = ArenaManager.getGame(name.getSingle(e))) == null) return null;
        return new String[]{arena.getState().toString()};
    }
}
