package us._donut_.skuniversal.bedwars;
;
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

@Name("Bedwars - Start Game")
@Description("Starts a Bedwars game.")
@Examples({"start bedwars game (player's bedwars game)"})
public class EffStartGame extends Effect {

    private Expression<String> game;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        game = (Expression<String>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "start Bedwars game " + game.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        GameManager.getGame(game.getSingle(e)).start();
    }
}