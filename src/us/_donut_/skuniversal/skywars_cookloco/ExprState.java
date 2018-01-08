package us._donut_.skuniversal.skywars_cookloco;

import ak.CookLoco.SkyWars.arena.ArenaManager;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("SkyWars (CookLoco) - Arena State")
@Description("Returns the state of a skywars arena.")
@Examples({"set {state} to the state of the skywars arena named \"cool\""})
public class ExprState extends SimpleExpression<String> {

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "state of SkyWars arena named " + name.getSingle(e);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (name.getSingle(e) != null) {
            return new String[]{ArenaManager.getGame(name.getSingle(e)).getState().toString()};
        } else {
            Skript.error("Must provide a string,s please refer to the syntax");
            return null;
        }
    }
}
