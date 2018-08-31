package us._donut_.skuniversal.skywars_daboross.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.skywars_daboross.SkyWarsDaborossHook.*;

@Name("SkyWars (Daboross) - Remove Player from Queue")
@Description("Removes a player from the game queue.")
@Examples({"remove player from the skywars game queue"})
public class EffRemoveFromQueue extends Effect {

    static {
        Skript.registerEffect(EffRemoveFromQueue.class, "remove %player% from [the] [SkyWars] [game] queue");
    }

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        player = (Expression<Player>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "remove player " + player.toString(e, b) + " from queue";
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) != null && skyWars.getGameQueue().inQueue(player.getSingle(e).getUniqueId()))
            skyWars.getGameQueue().removePlayer(player.getSingle(e));
    }
}
