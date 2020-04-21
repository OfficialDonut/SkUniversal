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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.autorank.AutorankHook.*;

@Name("Autorank - Current Path")
@Description("Returns the current path of a player.")
@Examples({"send \"Your current path: %current path of player%\""})
public class ExprCurrentPath extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprCurrentPath.class, String.class, ExpressionType.COMBINED,
                "[the] [(current|active)] [Autorank] path of %player%",
                "%player%'s [(current|active)] [Autorank] path");
    }

    private Expression<Player> player;

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
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "current path of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new String[]{autorankAPI.getActivePath(player.getSingle(e).getUniqueId()).getDisplayName()};
    }
}
