package us._donut_.skuniversal.bedwars;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.MineHome.Bedwars.Game.GameAPI;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Name("Bedwars - Existing Games")
@Description("Returns a list of all Bedwars games.")
@Examples({"loop all existing bedwars games:",
		"\tstop bedwars game loop-value"})
public class ExprGames extends SimpleExpression<String> {

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
        return "all Bedwars games";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        List<String> games = new ArrayList<>();
        for (GameAPI game : GameManager.getGames()) {
            games.add(game.getName());
        }
        return games.toArray(new String[games.size()]);
    }
}
