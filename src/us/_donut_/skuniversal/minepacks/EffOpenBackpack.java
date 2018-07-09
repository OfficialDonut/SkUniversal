package us._donut_.skuniversal.minepacks;

import at.pcgamingfreaks.MinePacks.MinePacks;
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

@Name("MinePacks - Open Backpack")
@Description("Open backpack to player.")
@Examples({"open editable false backpack of player to player"})
public class EffOpenBackpack extends Effect {

    private Expression<Boolean> editable;
    private Expression<Player> backpackOwner;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        editable = (Expression<Boolean>) e[0];
        backpackOwner = (Expression<Player>) e[1];
        player = (Expression<Player>) e[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "open backpack of player " + backpackOwner.toString(e, b) + " to " + player.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        MinePacks mp = MinePacks.getInstance();
        mp.openBackpack(player.getSingle(e), backpackOwner.getSingle(e), editable.getSingle(e));
    }
}
