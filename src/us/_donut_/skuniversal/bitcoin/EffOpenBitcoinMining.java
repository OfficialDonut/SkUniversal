package us._donut_.skuniversal.bitcoin;

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
import us._donut_.bitcoin.Bitcoin;
import javax.annotation.Nullable;

@Name("Bitcoin - Open Mining Menu")
@Description("Opens the bitcoin mining menu to a player.")
@Examples({"open the bitcoin mining menu to player"})
public class EffOpenBitcoinMining extends Effect {

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "open bitcoin mining menu to " + player.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) != null) {
            Bitcoin.getAPI().openMiningInterface(player.getSingle(e));
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
        }
    }
}