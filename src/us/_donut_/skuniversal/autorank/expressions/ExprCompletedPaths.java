package us._donut_.skuniversal.autorank.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.armar.plugins.autorank.pathbuilder.Path;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.autorank.AutorankHook.*;

@Name("Autorank - Completed Paths")
@Description("Returns list of the completed paths of a player.")
@Examples({"send \"You have completed these paths: %completed paths of player%\""})
public class ExprCompletedPaths extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprCompletedPaths.class, String.class, ExpressionType.COMBINED,
                "[(all [[of] the]|the)] (completed|finished) [Autorank] paths of %player%",
                "[all of] %player%'s (completed|finished) [Autorank] paths");
    }

    private Expression<Player> player;

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
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "completed paths of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return autorankAPI.getCompletedPaths(player.getSingle(e).getUniqueId()).stream().map(Path::getDisplayName).toArray(String[]::new);
    }
}
