package us._donut_.skuniversal.bedwars.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.MineHome.Bedwars.Game.GameAPI;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Bedwars - Game of Player")
@Description("Returns the current game of a player.")
@Examples({"send \"Your current game is %player's bedwars game%.\""})
public class ExprGameOfPlayer extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprGameOfPlayer.class, String.class, ExpressionType.COMBINED,
                "[[the] name of] [the] [Bedwars] game of %player%",
                "[[the] name of] %player%'s [Bedwars] game");
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
        return "Bedwars game of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        GameAPI game = GameManager.getGame(player.getSingle(e));
        return game == null ? null : new String[]{GameManager.getGame(player.getSingle(e)).getName()};
    }
}
