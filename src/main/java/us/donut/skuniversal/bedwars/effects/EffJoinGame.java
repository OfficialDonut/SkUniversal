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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Bedwars - Join Game")
@Description("Forces a player to join a Bedwars game.")
@Examples({"make player join bedwars game text-argument"})
public class EffJoinGame extends Effect {

    static {
        Skript.registerEffect(EffJoinGame.class,
                "make %player% join [the] [Bedwars] game [(named|with name)] %string%",
                "force %player% to join [the] [Bedwars] game [(named|with name)] %string%");
    }

    private Expression<Player> player;
    private Expression<String> game;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        player = (Expression<Player>) e[0];
        game = (Expression<String>) e[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "make player " + player.toString(e, b) + " join Bedwars game " + game.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) == null || game.getSingle(e) == null) return;
        GameManager.getGame(game.getSingle(e)).join(player.getSingle(e));
    }
}