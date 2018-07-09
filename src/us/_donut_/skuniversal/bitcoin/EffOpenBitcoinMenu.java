package us._donut_.skuniversal.bitcoin;

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

@Name("Bitcoin - Open Main Menu")
@Description("Opens the bitcoin menu to a player.")
@Examples({"open the bitcoin menu to player"})
public class EffOpenBitcoinMenu extends Effect {

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "open bitcoin menu to " + player.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        Bitcoin.getAPI().openMainMenu(player.getSingle(e));
    }
}
