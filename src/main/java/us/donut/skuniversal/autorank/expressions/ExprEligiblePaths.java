package us.donut.skuniversal.autorank.expressions;

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

import static us.donut.skuniversal.autorank.AutorankHook.*;

@Name("Autorank - Eligible Paths")
@Description("Returns list of the eligible paths of a player.")
@Examples({"send \"Your eligible paths: %eligible paths of player%\""})
public class ExprEligiblePaths extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprEligiblePaths.class, String.class, ExpressionType.COMBINED,
                "[(all [[of] the]|the)] (eligible|available) [Autorank] paths of %player%",
                "[all of] %player%'s (eligible|available) [Autorank] paths");
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
        return "eligible paths of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return autorankAPI.getEligiblePaths(player.getSingle(e)).stream().map(Path::getDisplayName).toArray(String[]::new);
    }
}
