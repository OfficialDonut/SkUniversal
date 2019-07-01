package us.donut.skuniversal.bedwars.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Bedwars - Players in Game")
@Description("Returns a list of all players in a Bedwars game.")
@Examples({"loop players in bedwars game {_game}:",
		"\tforce loop-player to leave bedwars game {_game}"})
public class ExprPlayers extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(ExprPlayers.class, Player.class, ExpressionType.COMBINED, "[(all [[of] the]|the)] players in [the] [Bedwars] game [(named|with name)] %string%");
    }

    private Expression<String> game;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        game = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "players in Bedwars game " + game.toString(e, b);
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        if (game == null) return null;
        return GameManager.getGame(game.getSingle(e)).getPlayers().toArray(new Player[0]);
    }
}