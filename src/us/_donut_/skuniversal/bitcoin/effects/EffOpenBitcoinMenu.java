package us._donut_.skuniversal.bitcoin.effects;

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
import us._donut_.bitcoin.BitcoinAPI;

import javax.annotation.Nullable;

@Name("Bitcoin - Open Main Menu")
@Description("Opens the bitcoin menu to a player.")
@Examples({"open the bitcoin menu to player"})
public class EffOpenBitcoinMenu extends Effect {

    static {
        Skript.registerEffect(EffOpenBitcoinMenu.class, "open [the] bitcoin [main] menu to %player%");
    }

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "open bitcoin menu to " + player.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) == null) return;
        BitcoinAPI.openMainMenu(player.getSingle(e));
    }
}
