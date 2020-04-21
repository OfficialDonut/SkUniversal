package us.donut.skuniversal.minepacks.effects;

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

import static us.donut.skuniversal.minepacks.MinePacksHook.*;

@Name("MinePacks - Open Backpack")
@Description("Open backpack to player.")
@Examples({"open editable false backpack of player to player"})
public class EffOpenBackpack extends Effect {

    static {
        Skript.registerEffect(EffOpenBackpack.class,
                "open [an] editable %boolean% (back|mine)pack of %player% to %player%",
                "open editable %boolean% %player%'s (back|mine)pack to %player%");
    }

    private Expression<Boolean> editable;
    private Expression<Player> backpackOwner;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
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
        if (player.getSingle(e) == null || backpackOwner.getSingle(e) == null || editable.getSingle(e) == null) return;
        minePacks.openBackpack(player.getSingle(e), backpackOwner.getSingle(e), editable.getSingle(e));
    }
}
