package us._donut_.skuniversal.minepacks;

import at.pcgamingfreaks.MinePacks.MinePacks;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("MinePacks - Backpack Size")
@Description("Returns the size of a backpack.")
@Examples({"send \"%the size of the backpack of player%\""})
public class ExprBackpackSize extends SimpleExpression<Number> {

    private Expression<Player> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "size of backpack of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        MinePacks mp = MinePacks.getInstance();
        return new Number[]{mp.DB.getBackpack(player.getSingle(e)).getSize()};
    }
}