package us.donut.skuniversal.bedwars.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.MineHome.Bedwars.Game.GameManager;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Bedwars - Stop Game")
@Description("Stops a Bedwars game.")
@Examples({"stop bedwars game (player's bedwars game)"})
public class EffEndGame extends Effect {

    static {
        Skript.registerEffect(EffEndGame.class, "(end|stop) [the] [Bedwars] game [(named|with name)] %string%");
    }

    private Expression<String> game;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        game = (Expression<String>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "end Bedwars game " + game.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (game.getSingle(e) == null) return;
        GameManager.getGame(game.getSingle(e)).endGame();
    }
}