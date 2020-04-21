package us.donut.skuniversal.skywars_daboross.effects;

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
import us.donut.skuniversal.skywars_daboross.SkyWarsDaborossHook;

import javax.annotation.Nullable;

@Name("SkyWars (Daboross) - Add Player to Queue")
@Description("Adds a player to the game queue.")
@Examples({"add player to the skywars game queue"})
public class EffAddToQueue extends Effect {

    static {
        Skript.registerEffect(EffAddToQueue.class, "add %player% to [the] [SkyWars] [game] queue");
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
        return "add player " + player.toString(e, b) + " to queue";
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) != null && !SkyWarsDaborossHook.skyWars.getGameQueue().inQueue(player.getSingle(e).getUniqueId()))
            SkyWarsDaborossHook.skyWars.getGameQueue().queuePlayer(player.getSingle(e));
    }
}
