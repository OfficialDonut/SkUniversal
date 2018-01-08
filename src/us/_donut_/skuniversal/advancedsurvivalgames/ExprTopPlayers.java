package us._donut_.skuniversal.advancedsurvivalgames;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import e.Game;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.List;

@Name("AdvancedSurvivalGames - Top Players")
@Description("Returns names of the top players.")
@Examples({"loop survival games top players:",
		"add 1 to {_number}",
		"send \"%{_number}%- %loop-value%\"",
})
public class ExprTopPlayers extends SimpleExpression<String> {

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
        return "the survival games top players";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        List<String> topPlayers = Game.getTopPlayers();
        if (topPlayers == null) {
            return null;
        }
        return topPlayers.toArray(new String[topPlayers.size()]);
    }
}
